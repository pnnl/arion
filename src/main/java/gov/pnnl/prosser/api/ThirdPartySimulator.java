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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.pnnl.prosser.api.fncs.Subscription;
import gov.pnnl.prosser.api.thirdparty.enums.SimType;

/**
 * @author fish334
 *
 */
public class ThirdPartySimulator extends AbstractSimulator {

	private String broker;

	private final List<Path> modelFiles = new ArrayList<Path>();

	private GldSimulator gldSim;

	private Map<GldSimulator, String> gldSimulators = new HashMap<>();

	private final SimType simType;

	private final List<Subscription> userSubscriptions = new ArrayList<>();

	/**
	 * @param name
	 * @param simType The simulation type: either MATPOWER, MATLAB_AGGREGATOR
	 *
	 */
	public ThirdPartySimulator(String name, SimType simType, Experiment experiment) {
		super(name, experiment);
		this.broker = "tcp://localhost:5570";
		this.simType = simType;
	}

	/**
	 * @return the broker
	 */
	public String getBroker() {
		return broker;
	}

	/**
	 * @param broker the broker to set
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}

	/**
	 * @return the gldSim
	 */
	public GldSimulator getGldSim() {
		return gldSim;
	}

	/**
	 * @param gldSim the gldSim to set
	 */
	public void setGldSim(GldSimulator gldSim) {
		this.gldSim = gldSim;
	}

	/**
	 * add model files for the simulator
	 * @param modelFiles the model files
	 */
	public void addModelFiles(final Path... modelFiles){
		this.modelFiles.addAll(Arrays.asList(modelFiles));
	}

	/**
	 * Get the model files for the simulator
	 * @return the model files
	 */
	public List<Path> getModelFiles(){
		return this.modelFiles;
	}

	/**
	 * @return the gldSimulators
	 */
	public Map<GldSimulator, String> getGldSimulators() {
		return gldSimulators;
	}

	/**
	 * add a gridlabd to matpower bus connection
	 * @param gldSim
	 * 				The GldSimulator being connected to the matpower model
	 * @param busName
	 * 				The name of the bus being connected to the GldSimulator
	 */
	public void addGldSimulator(GldSimulator gldSim, String busName){
		this.gldSimulators.put(gldSim, busName);
	}

	/**
	 * @return the simType
	 */
	public SimType getSimType() {
		return simType;
	}

	/**
     * @return the userSubscriptions
     */
    public List<Subscription> getUserSubscriptions() {
        return userSubscriptions;
    }

    public void addSubscription(String localVariable, AbstractSimulator remoteSimulator, String remoteVariable) {
	    final Subscription sub = new Subscription();
	    sub.setLocalVariable(localVariable);
	    sub.setRemoteSimulator(remoteSimulator);
	    sub.setRemoteVariable(remoteVariable);
	    userSubscriptions.add(sub);
	}
}
