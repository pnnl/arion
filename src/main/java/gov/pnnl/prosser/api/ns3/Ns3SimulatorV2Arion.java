/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
 *    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
 *    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 *    and may permit others to do so, subject to the following conditions:
 *    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
 *       the following disclaimers.
 *    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *       the following disclaimer in the documentation and/or other materials provided with the distribution.
 *    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
 *       form whatsoever without the express written consent of Battelle.
 * 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 *    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *                                PACIFIC NORTHWEST NATIONAL LABORATORY
 *                                            operated by
 *                                              BATTELLE
 *                                              for the
 *                                  UNITED STATES DEPARTMENT OF ENERGY
 *                                   under Contract DE-AC05-76RL01830
 *******************************************************************************/
package gov.pnnl.prosser.api.ns3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import gov.pnnl.prosser.api.Experiment;
import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.MarketSetUp;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.House;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3SimulatorV2Arion extends AbstractNs3SimulatorV2 {
    private static final int MAX_COUNT = 500;
    private final List<GldSimulator> simulators = new ArrayList<>();

    public Ns3SimulatorV2Arion(final String name, Experiment experiment) {
        super(name, Paths.get("res/README.md"), experiment);
    }

    public void attachSimulator(GldSimulator sim) {
        this.simulators.add(sim);
    }

    @Override
    public void writeConfig(Path outDir) throws IOException {
        final StringBuilder sb = new StringBuilder();
        this.writeZplHeader(sb);
        final UndirectedGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        int nodeCount = 1;
        int routerCount = 1;
        String currentRouter = "router" + routerCount;
        graph.addVertex(currentRouter);
        // This may support using Auctions across GridLabD simulators but that is not feasible because of the implications on the powergrid
        final Set<String> names = new HashSet<>();
        for (final GldSimulator gldSim : this.simulators) {
            for (AbstractGldObject object : gldSim.getObjects()) {
                if (object instanceof AuctionObject) {
                    final AuctionObject auction = (AuctionObject) object;
                    if (!names.add(auction.getName())) {
                        throw new RuntimeException("Duplicate Name Detected: " + auction.getName());
                    }
                    graph.addVertex(auction.getName());
                    graph.addEdge(currentRouter, auction.getName());
                    nodeCount++;
                    if(nodeCount >= MAX_COUNT){
                        routerCount++;
                        String newRouter = "router" + routerCount;
                        graph.addVertex(newRouter);
                        graph.addEdge(currentRouter, newRouter);
                        currentRouter = newRouter;
                        nodeCount = 1;
                    }
                }
            }
            for (AbstractGldObject object : gldSim.getObjects()) {
                if (object instanceof House) {
                    final House house = (House) object;
                    final Controller controller = house.getController();
                    if(controller == null) {
                        continue;
                    }
                    if (!names.add(controller.getName())) {
                        throw new RuntimeException("Duplicate Name Detected: " + controller.getName());
                    }
                    graph.addVertex(controller.getName());
                    graph.addEdge(currentRouter, controller.getName());
                    nodeCount++;
                    if(nodeCount >= MAX_COUNT){
                        routerCount++;
                        String newRouter = "router" + routerCount;
                        graph.addVertex(newRouter);
                        graph.addEdge(currentRouter, newRouter);
                        currentRouter = newRouter;
                        nodeCount = 1;
                    }
                    if(controller.getAuction().getMarketSetUp().equals(MarketSetUp.NORMAL)){
                    	writeControllerToMarketVar(sb, gldSim, controller, "submit_bid_state");
                    } else if(controller.getAuction().getMarketSetUp().equals(MarketSetUp.AGGREGATE)){
                    	writeControllerToMarketVar(sb, gldSim, controller, "bid_price");
                    	writeControllerToMarketVar(sb, gldSim, controller, "bid_quantity");
                    	writeControllerToMarketVar(sb, gldSim, controller, "parent_unresponsive_load");
                    }
                    writeMarketToControllerVar(sb, gldSim, controller, "clearPrice");
                    writeMarketToControllerVar(sb, gldSim, controller, "mktID");
                    writeMarketToControllerVar(sb, gldSim, controller, "avgPrice");
                    writeMarketToControllerVar(sb, gldSim, controller, "stdevPrice");
                }
            }
        }
        final DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>(new StringNameProvider<>(), null, null);
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("graph.dot"), StandardCharsets.UTF_8)) {
            exporter.export(writer, graph);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("fncs.zpl"), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }

    }

    private static void writeMarketToControllerVar(StringBuilder sb, GldSimulator gldSim, Controller controller, String var) {
        writeMarketToControllerVar(sb, gldSim.getName(), controller.getAuction().getName(), controller.getName(), var);
    }

    private static void writeControllerToMarketVar(StringBuilder sb, GldSimulator gldSim, Controller controller, String var) {
        writeControllerToMarketVar(sb, gldSim.getName(), controller.getAuction().getName(), controller.getName(), var);
    }

}
