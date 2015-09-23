import gov.pnnl.prosser.api.Experiment;
import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.Ns3Simulator;

/**
 * 
 */

/**
 * @author nord229
 *
 */
public class BasicSimulator extends Experiment {

    @Override
    public void experiment() {
        final Ns3Simulator ns3 = this.ns3Simulator("ns3-sim");
        final GldSimulator gld1 = this.gldSimulator("gld-sim1");
        final GldSimulator gld2 = this.gldSimulator("gld-sim2");
    }

}
