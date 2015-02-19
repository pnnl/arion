import gov.pnnl.prosser.api.Ns3SimulatorWriter;
import gov.pnnl.prosser.api.gld.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.gld.obj.MarketNetworkInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

/**
 * @author happ546
 *
 */
public class TestExperiment {

    public static void main(final String[] args) throws IOException {
        final Path outPath = Paths.get(args[1]).toRealPath();
        if (!Files.exists(outPath)) {
            Files.createDirectories(outPath);
        }
        final int numNodes = 300;
        final String marketName = "MarkNIF1";
        final String controllerPrefix = "F1_single_ramp_NIF_";
        // Create GLD market and save reference for NS-3
        final List<MarketNetworkInterface> marketNIs = new ArrayList<MarketNetworkInterface>();
        final MarketNetworkInterface marketNI = new MarketNetworkInterface();
        marketNI.setName(marketName);
        marketNIs.add(marketNI);
        // Create Base Power Infrastructure
        // In a loop, generate houses and save list for NS-3
        final List<ControllerNetworkInterface> controllerNIs = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            // Generate house and save to list
            final String name = controllerPrefix + i;
            final ControllerNetworkInterface controllerNI = new ControllerNetworkInterface();
            controllerNI.setName(name);
            controllerNI.setMarketNI(marketNI);
            controllerNIs.add(controllerNI);
        }
        final TestExperimentNs3Simulator ns3Simulator = new TestExperimentNs3Simulator();
        ns3Simulator.setControllerNIs(controllerNIs);
        ns3Simulator.setMarketNIs(marketNIs);
        ns3Simulator.setGldNodePrefix(controllerPrefix);
        Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve("ns3.cc"), ns3Simulator);
        System.out.println("Written!");
        
        // TODO FNCS Integration
    }

}
