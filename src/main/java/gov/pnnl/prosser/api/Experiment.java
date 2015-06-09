/**
 *
 */
package gov.pnnl.prosser.api;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Prosser Experiment comprising multiple GLD simulators and an NS-3 and FNCS Simulator
 *
 * @author nord229
 *
 */
public abstract class Experiment {

    private final List<GldSimulator> gldSimulators = new ArrayList<>();

    private Ns3Simulator ns3Simulator = null;

    private FncsSimulator fncsSimulator = null;

    private final List<Path> extraExperimentFiles = new ArrayList<>();


    /**
     * Get the GLD Simulators
     * 
     * @return the simulators
     */
    public List<GldSimulator> getGldSimulators() {
        return this.gldSimulators;
    }

    /**
     * Get the NS-3 Simulators
     * 
     * @return the simulators
     */
    public Ns3Simulator getNs3Simulator() {
        return this.ns3Simulator;
    }

    /**
     * Get the FNCS Simulators
     * 
     * @return the simulator
     */
    public FncsSimulator getFncsSimulator() {
        return this.fncsSimulator;
    }

    /**
     * Get the Extra Experiment files to be included when the experiment is compiled
     * 
     * @return the extra files
     */
    public List<Path> getExtraExperimentFiles() {
        return extraExperimentFiles;
    }

    /**
     * Get a new GLD Simulator
     * 
     * @param name
     *            the name of the simulator, the name of the file generated is based on this name
     * @return the simulator
     */
    public GldSimulator gldSimulator(final String name) {
        final GldSimulator sim = new GldSimulator(name);
        this.gldSimulators.add(sim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new NS-3 Simulator
     * 
     * @return the simulator
     */
    public Ns3Simulator ns3Simulator(String name) {
        this.ns3Simulator = new Ns3Simulator(name);
        this.ensureFncs();
        return this.ns3Simulator;
    }

    /**
     * Get a new FNCS Simulator
     * This experiment will only have one FNCS Simulator
     * 
     * @return the simulator
     */
    public FncsSimulator fncsSimulator() {
        if (this.fncsSimulator == null) {
            this.fncsSimulator = new FncsSimulator();
        }
        return this.fncsSimulator;
    }

    /**
     * Add an extra file to be included when the experiment is created
     * 
     * @param file
     *            the file to include
     */
    public void addExtraFiles(Path... file) {
        Arrays.stream(file).forEach((f) -> this.extraExperimentFiles.add(f));
    }

    /**
     * Put your generation code here for generating an experiment
     */
    public abstract void experiment();
    
    private void ensureFncs() {
        if(this.gldSimulators.size() > 1 || this.ns3Simulator != null) {
            this.fncsSimulator();
        }
    }

}
