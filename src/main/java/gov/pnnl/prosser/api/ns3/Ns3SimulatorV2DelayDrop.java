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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import gov.pnnl.prosser.api.Experiment;
import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.House;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3SimulatorV2DelayDrop extends AbstractNs3SimulatorV2 {
    // Columns: id, aggregator, name
    private static final CSVFormat AGR_FORMAT = CSVFormat.DEFAULT.withRecordSeparator('\n');

    // Columns: id, delay, error
    private static final CSVFormat MD_FORMAT = CSVFormat.DEFAULT.withRecordSeparator('\n');

    private final List<GldSimulator> simulators = new ArrayList<>();

    public Ns3SimulatorV2DelayDrop(final String name, Experiment experiment) {
        super(name, Paths.get("res/delay-drop-fncs-config.cc"), experiment);
    }

    public void attachSimulator(GldSimulator sim) {
        this.simulators.add(sim);
    }

    @Override
    public void writeConfig(Path outDir) throws IOException {
        final StringBuilder sb = new StringBuilder();
        this.writeZplHeader(sb);
        try (CSVPrinter agrPrinter = AGR_FORMAT.print(Files.newBufferedWriter(outDir.resolve("aggregator.csv"), StandardCharsets.UTF_8));
                CSVPrinter mdPrinter = MD_FORMAT.print(Files.newBufferedWriter(outDir.resolve("metadata.csv"), StandardCharsets.UTF_8));) {
            // This may support using Auctions across GridLabD simulators but that is not feasible because of the implications on the powergrid
            final Map<String, Integer> auctionIdMap = new HashMap<>();
            int currentId = 1;
            for (final GldSimulator gldSim : this.simulators) {
                for (AbstractGldObject object : gldSim.getObjects()) {
                    if (object instanceof AuctionObject) {
                        final AuctionObject auction = (AuctionObject) object;
                        Integer id = auctionIdMap.get(auction.getName());
                        if (id != null) {
                            throw new RuntimeException("Duplicate Auction Name Detected");
                        }
                        id = currentId;
                        currentId++;
                        auctionIdMap.put(auction.getName(), id);
                        agrPrinter.printRecord(id, id, auction.getName());
                        mdPrinter.printRecord(id, 0, 0);
                    }
                }
                for (AbstractGldObject object : gldSim.getObjects()) {
                    if (object instanceof House) {
                        final House house = (House) object;
                        Integer auctionId = auctionIdMap.get(house.getController().getAuction().getName());
                        if (auctionId == null) {
                            throw new RuntimeException("Auction not declared in GLD Simulator");
                        }
                        int controllerId = currentId;
                        currentId++;
                        agrPrinter.printRecord(controllerId, auctionId, house.getController().getName());
                        mdPrinter.printRecord(controllerId, 0, 0);
                        writeControllerToMarketVar(sb, gldSim, house, "submit_bid_state");
                        writeMarketToControllerVar(sb, gldSim, house, "clearPrice");
                        writeMarketToControllerVar(sb, gldSim, house, "mktID");
                        writeMarketToControllerVar(sb, gldSim, house, "avgPrice");
                        writeMarketToControllerVar(sb, gldSim, house, "stdevPrice");
                    }
                }
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("fncs.zpl"), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }

    }

    private static void writeMarketToControllerVar(StringBuilder sb, GldSimulator gldSim, House house, String var) {
        writeMarketToControllerVar(sb, gldSim.getName(), house.getController().getAuction().getName(), house.getController().getName(), var);
    }

    private static void writeControllerToMarketVar(StringBuilder sb, GldSimulator gldSim, House house, String var) {
        writeControllerToMarketVar(sb, gldSim.getName(), house.getController().getAuction().getName(), house.getController().getName(), var);
    }

}
