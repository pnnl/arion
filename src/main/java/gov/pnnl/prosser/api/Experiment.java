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
package gov.pnnl.prosser.api;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;
import gov.pnnl.prosser.api.ns3.Ns3SimulatorV2Arion;
import gov.pnnl.prosser.api.ns3.Ns3SimulatorV2DelayDrop;
import gov.pnnl.prosser.api.ns3.Ns3SimulatorV2FirstN;
import gov.pnnl.prosser.api.thirdparty.enums.SimType;

/**
 * Prosser Experiment comprising multiple GLD simulators and an NS-3 and FNCS Simulator
 *
 * @author nord229
 *
 */
public abstract class Experiment {

    private final List<GldSimulator> gldSimulators = new ArrayList<>();

    private Ns3Simulator ns3Simulator = null;

    private final List<AbstractNs3SimulatorV2> ns3SimulatorV2 = new ArrayList<>();

    private FncsSimulator fncsSimulator = null;

    private final List<Path> extraExperimentFiles = new ArrayList<>();

    private String experimentName;

    private final UUID uuid = UUID.randomUUID();

    private final List<ThirdPartySimulator> thirdPartySimulators = new ArrayList<>();

    private String sharedFolderName = "shared";

    /**
     * Get the ExperimentName
     *
     * @return general ExperimentName
     */
    public String getName() {
        return this.experimentName;
    }

    /**
     * Set the ExperimentName
     *
     */
    public void setName(String name) {
        this.experimentName = name;
    }

    /**
     * Get the ExperimentName
     *
     * @return general ExperimentName
     */
    public String getUUID() {
        return this.uuid.toString();
    }

    /**
     * @return the sharedFolderName
     */
    public String getSharedFolderName() {
        return sharedFolderName;
    }

    /**
     * @param sharedFolderName the sharedFolderName to set
     */
    public void setSharedFolderName(String sharedFolderName) {
        this.sharedFolderName = sharedFolderName;
    }

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
     * Get the NS-3 Simulators
     *
     * @return the simulators
    */
    public List<AbstractNs3SimulatorV2> getNs3SimulatorV2() {
        return this.ns3SimulatorV2;
    }

    /**
     * Get the FNCS Simulators
     *
     * @return the simulator

    public FncsSimulator getFncsSimulator() {
        return this.fncsSimulator;
    }*/

    /**
     * Get the third party simulators
     *
     * @return the simulators
     */
    public List<ThirdPartySimulator> getThirdPartySimulator() {
    	return this.thirdPartySimulators;
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
        final GldSimulator sim = new GldSimulator(name, null, this);
        this.gldSimulators.add(sim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new GLD Simulator
     *
     * @param name
     *            the name of the simulator, the name of the file generated is based on this name
     * @return the simulator
     */
    public GldSimulator gldSimulator(final String name, final AbstractNs3SimulatorV2 ns3Sim) {
        final GldSimulator sim = new GldSimulator(name, ns3Sim, this);
        this.gldSimulators.add(sim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new GLD Simulator
     *
     * @param name
     *            the name of the simulator, the name of the file generated is based on this name
     * @param ns3Sim
     * 				the ns3Sim that this simulator will communicate with
     * @param thirdPartySim
     * 						the thridPartySimulator that this simulator will communicate with
     * @return the simulator
     */
    public GldSimulator gldSimulator(final String name, final AbstractNs3SimulatorV2 ns3Sim, final ThirdPartySimulator thirdPartySim) {
        final GldSimulator sim = new GldSimulator(name, ns3Sim, this);
        this.gldSimulators.add(sim);
        sim.setThirdPartySim(thirdPartySim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new NS-3 Simulator
     *
     * @param name
     *            the name of the simulator
     * @return the simulator
     */
    public Ns3Simulator ns3Simulator(String name) {
        this.ns3Simulator = new Ns3Simulator(name);
        this.ensureFncs();
        return this.ns3Simulator;
    }

    /**
     * Get a new NS-3 Simulator
     *
     * @return the simulator
     */
    public Ns3SimulatorV2FirstN ns3SimulatorV2FirstN(final String name) {
        Ns3SimulatorV2FirstN sim = new Ns3SimulatorV2FirstN(name, this);
        this.ns3SimulatorV2.add(sim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new NS-3 Simulator
     *
     * @return the simulator
     */
    public Ns3SimulatorV2DelayDrop ns3SimulatorV2DelayDrop(final String name) {
        Ns3SimulatorV2DelayDrop sim = new Ns3SimulatorV2DelayDrop(name, this);
        this.ns3SimulatorV2.add(sim);
        this.ensureFncs();
        return sim;
    }

    /**
     * Get a new NS-3 Simulator
     *
     * @return the simulator
     */
    public Ns3SimulatorV2Arion ns3SimulatorV2Arion(final String name) {
        Ns3SimulatorV2Arion sim = new Ns3SimulatorV2Arion(name, this);
        this.ns3SimulatorV2.add(sim);
        this.ensureFncs();
        return sim;
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
     * Get a new third pary simulator
     * @return the simulator
     */
    public ThirdPartySimulator thirdPartySimulator(final String name, SimType simType){
    	ThirdPartySimulator sim = new ThirdPartySimulator(name, simType, this);
    	this.thirdPartySimulators.add(sim);
    	this.ensureFncs();
    	return sim;
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
        if (this.gldSimulators.size() > 1 || this.ns3Simulator != null) {
            this.fncsSimulator();
        }
    }

}
