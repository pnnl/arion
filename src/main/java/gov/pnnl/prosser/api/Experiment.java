/**
 *
 */
package gov.pnnl.prosser.api;

import java.util.List;

/**
 * Prosser Experiment comprising multiple GLD simulators and an NS-3 and FNCS Simulator
 *
 * @author nord229
 *
 */
public interface Experiment {

    /**
     * Get the GLD Simulators
     */
    public List<GldSimulator> getGldSimulators();

    /**
     * Get the NS-3 Simulators
     */
    public Ns3Simulator getNs3Simulator();

    /**
     * Get the FNCS Simulators
     */
    public FncsSimulator getFncsSimulator();

}
