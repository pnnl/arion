import gov.pnnl.prosser.api.Ns3SimulatorWriter;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.pwr.obj.Controller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

/**
 * @author nord229
 *
 */
public class ExperimentFncsTest {

    public static void main(final String[] args) throws IOException {
        final Path outPath = Paths.get(args[0]).toRealPath();
        final GldBaseFncsTest gldSim = new GldBaseFncsTest();

        // For this test there will be one object in each list but in general there could be multiple
        final List<AuctionObject> auctions = new ArrayList<>();
        final List<Controller> controllers = new ArrayList<>();
        gldSim.getObjects().forEach((o) -> {
            if (o instanceof AuctionObject) {
                auctions.add((AuctionObject) o);
            } else if (o instanceof Controller) {
                controllers.add((Controller) o);
            }
        });

        // TODO Do we still need this prefix?
        final String controllerPrefix = "F1_single_ramp_NIF_";
        final TestExperimentNs3Simulator ns3Simulator = new TestExperimentNs3Simulator();
        // ns3Simulator.setControllerNIs(controllerNIs);
        // ns3Simulator.setMarketNI(marketNI);
        ns3Simulator.setGldNodePrefix(controllerPrefix);
        Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve("ns3.cc"), ns3Simulator);
        // TODO FNCS Integration
    }

}
