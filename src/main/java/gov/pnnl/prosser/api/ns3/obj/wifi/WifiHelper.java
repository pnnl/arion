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
package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

/**
 * The WifiHelper is a helper class to simplify the setup of Wi-Fi networks.
 *
 * @author happ546
 *
 */
public class WifiHelper extends AbstractNs3Object {

	/**
	 * Creates a new WifiHelper with the given name
	 *
	 * @param name
	 */
	public WifiHelper(String name) {
		this.setName(name);
	}

	/**
	 * Sets the default parameters for this WifiHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = WifiHelper::Default();\n");
	}

	/**
	 *
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param node
	 * 			the ns-3 Node to install the Wi-Fi stack and devices on
	 * @param destinationContainer the NetDeviceContainer which will hold the installed
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac,
						Node node, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + ".Add (" + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ node.getPointerName() + "));\n");
	}

	/**
	 *
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param sourceNodes the NodeContainer containing the Nodes on which to install
	 * 			the WiFi devices
	 * @param destinationContainer the NetDeviceContainer which will hold the installed
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac,
			NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ sourceNodes.getName() + ");\n");
	}

	/**
	 *
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param sourceNodes the NodeContainer containing the Nodes on which to install
	 * 			the WiFi devices
	 * @param index the index of the Node in sourceNodes to install onto the destinationContainer
	 * @param destinationContainer the NetDeviceContainer which will hold the installed
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac,
			NodeContainer sourceNodes, int index, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ sourceNodes.getName() + ".Get (" + index + "));\n");
	}

	/**
	 * Sets the algorithm used by ns-3 to control the data rate on this WiFi network
	 * @param phyMode
	 * @param phyMode
	 * 			the WifiPhyMode to configure this remote station manager to use
	 */
	// TODO allow for all types of station managers
	public void setRemoteStationManager(WifiPhyMode phyMode) {
		appendPrintInfo(this.getName() + ".SetRemoteStationManager (\"ns3::ConstantRateWifiManager\", " +
				"\"DataMode\", StringValue (\"" + phyMode.toString() + "\"), " +
				"\"ControlMode\", StringValue (\"" + phyMode.toString() + "\"));\n");
	}

	/**
	 * Sets the Wi-Fi Physical layer standard to use for this Wi-Fi network
	 * @param std
	 * 		the WifiPhyStandard enum to set
	 */
	public void setStandard(WifiPhyStandard std) {
		appendPrintInfo(getName() + ".SetStandard (" + std.toString() + ");\n");
	}

}
