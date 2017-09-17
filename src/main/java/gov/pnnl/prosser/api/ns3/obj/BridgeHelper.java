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
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * The BridgeHelper is a helper class to simplify the setup of network bridges,
 * devices that connect one network device type to another (e.g. CSMA to point-to-point).
 *
 * @author happ546
 *
 */
public class BridgeHelper extends AbstractNs3Object {

	private Map<String, String> deviceAttributes;

	/**
	 * Creates a named BridgeHelper
	 *
	 * @param name
	 */
	public BridgeHelper(String name) {
		this.deviceAttributes = new HashMap<String, String>();
		this.setName(name);
	}

	/**
	 * Installs a Bridge device on each of the Nodes in sourceNodes
	 *
	 * @param sourceNodes the NodeContainer holding the Nodes to install this Bridge device on
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install(" + sourceNodes.getName() + ");\n");
	}


	/**
	 * Installs a Bridge device on each of the Nodes in sourceNodes using
	 * the sourceDevices as Bridge ports
	 *
	 * @param sourceNodes the Node on which to install the Bridge net device
	 * @param sourceDevices the container of net devices to add as Bridge ports
	 * @param destinationContainer the NetDeviceContainer to hold the Node and
	 * 			installed Bridge device
	 */
	public void install(NodeContainer sourceNodes, int index,
						NetDeviceContainer sourceDevices, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install(" + sourceNodes.getName() + ".Get(" + index + "), " +
				sourceDevices.getName() + ");\n");
	}

	/**
	 * Sets attributes for this Bridge device
	 *
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		this.deviceAttributes.put(attr, value);
	}


}
