/**
 *
 */
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

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.House;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3SimulatorV2Arion extends AbstractNs3SimulatorV2 {
    private final List<GldSimulator> simulators = new ArrayList<>();

    public Ns3SimulatorV2Arion(final String name) {
        super(name, Paths.get("res/download.sh"));
    }

    public void attachSimulator(GldSimulator sim) {
        this.simulators.add(sim);
    }

    @Override
    public void writeConfig(Path outDir) throws IOException {
        final StringBuilder sb = new StringBuilder();
        this.writeZplHeader(sb);
        final UndirectedGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("router1");
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
                    graph.addEdge("router1", auction.getName());
                }
            }
            for (AbstractGldObject object : gldSim.getObjects()) {
                if (object instanceof House) {
                    final House house = (House) object;
                    if (!names.add(house.getController().getName())) {
                        throw new RuntimeException("Duplicate Name Detected: " + house.getController().getName());
                    }
                    graph.addVertex(house.getController().getName());
                    graph.addEdge("router1", house.getController().getName());
                    writeControllerToMarketVar(sb, gldSim, house, "submit_bid_state");
                    writeMarketToControllerVar(sb, gldSim, house, "clearPrice");
                    writeMarketToControllerVar(sb, gldSim, house, "mktID");
                    writeMarketToControllerVar(sb, gldSim, house, "avgPrice");
                    writeMarketToControllerVar(sb, gldSim, house, "stdevPrice");
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

    private static void writeMarketToControllerVar(StringBuilder sb, GldSimulator gldSim, House house, String var) {
        writeMarketToControllerVar(sb, gldSim.getName(), house.getController().getAuction().getName(), house.getController().getName(), var);
    }

    private static void writeControllerToMarketVar(StringBuilder sb, GldSimulator gldSim, House house, String var) {
        writeControllerToMarketVar(sb, gldSim.getName(), house.getController().getAuction().getName(), house.getController().getName(), var);
    }

}
