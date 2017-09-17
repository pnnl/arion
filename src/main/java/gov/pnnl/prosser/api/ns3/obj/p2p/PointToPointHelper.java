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
package gov.pnnl.prosser.api.ns3.obj.p2p;

import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * The PointToPointHelper simplifies the setup of point-to-point (p2p) networks.
 *
 * @author happ546
 *
 */
public class PointToPointHelper extends NetworkHelper {
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;

	/**
	 * Construct a new point to point helper to handle creation of a p2p node/channel
	 *
	 * @param name
	 */
	public PointToPointHelper(String name) {
		this.setName(name);
		this.channelAttributes = new HashMap<>();
		this.deviceAttributes = new HashMap<>();
	}

	/**
	 * @param nodeA
	 * @param nodeB
	 * @param channel
	 * @param p2pDevices
	 */
	public void install(Node nodeA, Node nodeB,
			PointToPointChannel channel, NetDeviceContainer p2pDevices) {

		if (channel != null) {
			this.setChannelAttribute("Delay", channel.getDelay());
			this.setDeviceAttribute("DataRate", channel.getDataRate());
		}

		appendPrintInfo(p2pDevices.getName() + ".Add (" + this.getName() +
				".Install (" + nodeA.getName() + ", " +
				nodeB.getName() + "));\n");
	}

	/**
	 * @param nodeA
	 * @param nodeB
	 * @param p2pDevices
	 */
	public void install(Node nodeA, Node nodeB, NetDeviceContainer p2pDevices) {
		install(nodeA, nodeB, null, p2pDevices);
	}

	/**
	 * Installs p2p NetDevices on the nodes in the given NodeContainer
	 *
	 * @param nodes
	 */
	public void install(NodeContainer nodes) {
		appendPrintInfo(this.getName() + ".Install (" + nodes.getName() + ");\n");
	}



	/**
	 * Sets attributes for this point to point channel
	 *
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintInfo(this.getName() + ".SetChannelAttribute (\"" + attr
				+ "\", StringValue (\"" + value + "\"));\n");
	}

	/**
	 * Sets attributes for this point to point device
	 *
	 * @param attr the attribute to set the value to
	 * @param value the string value (e.g. DataRate)
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		String valueWrapperPrefix = "StringValue (\"";
		String valueWrapperSuffix = "\")";
		if (attr.equals("DataRate")) {
			valueWrapperPrefix = "DataRateValue (DataRate (\"";
			valueWrapperSuffix = "\"))";
		}
		appendPrintInfo(this.getName() + ".SetDeviceAttribute (\"" + attr + "\", "
				+ valueWrapperPrefix + value + valueWrapperSuffix + ");\n");
	}

	/**
	 * @param attr the attribute to set the value to
	 * @param value the integer value (e.g. MTU value)
	 */
	public void setDeviceAttribute(String attr, int value) {
		deviceAttributes.put(attr, "" + value);
		appendPrintInfo(this.getName() + ".SetDeviceAttribute (\"" + attr
				+ "\", UintegerValue (" + value + "));\n");
	}

}
