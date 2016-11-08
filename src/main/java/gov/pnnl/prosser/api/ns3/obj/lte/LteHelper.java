/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
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
*/
package gov.pnnl.prosser.api.ns3.obj.lte;

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

/**
 * The LteHelper class simplifies setting up an LTE network, handling
 * the creation and attribute-setting of the wireless channel and connecting
 * the UE (User Equipment) end-point devices to the ENB (Evolved Node B)
 * cellular towers.
 * @author happ546
 *
 */
public class LteHelper extends NetworkHelper {

	/**
	 * Creates a new LteHelper with the given name
	 * @param name
	 */
	public LteHelper(String name) {
		this.setName(name);
	}

	/**
	 * Installs an LTE protocol stack on the Nodes in sourceNodes
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices
	 * 				after they have an LTE stack installed on them
	 * @param enbOrUe a string passed by the installUeDevice or installEnbDevice
	 * 				indicating which device type is contained in the sourceNodes
	 */
	private void install(NodeContainer sourceNodes,
				NetDeviceContainer destinationContainer,
				String enbOrUe) {
		destinationContainer.addNodes(sourceNodes);
		this.appendPrintInfo(destinationContainer.getName() + " = " + this.getName() + ".Install"
				+ enbOrUe + "Device(" + sourceNodes.getName() + ");\n");
	}

	/**
	 * Installs an LTE protocol stack on the eNB(s)
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices
	 * 				after they have an LTE stack installed on them
	 */
	public void installEnbDevice(NodeContainer sourceNodes,
				NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Enb");
	}

	/**
	 * Installs an LTE protocol stack on the UE(s)
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices
	 * 				after they have an LTE stack installed on them
	 */
	public void installUeDevice(NodeContainer sourceNodes,
				NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Ue");
	}

	/**
	 * Attaches the UE (user equipment) nodes to the eNB (base station) nodes
	 * @param ueDevices the NetDeviceContainer of UE nodes
	 * @param enbDevices the NetDeviceContainer of eNB nodes
	 */
	public void attach(NetDeviceContainer ueDevices,
				NetDeviceContainer enbDevices, int index) {
		this.appendPrintInfo(this.getName() + ".Attach(" + ueDevices.getName() + ", "
				+ enbDevices.getName() + ".Get(" + index + "));\n");
	}

	/**
	 * Adds the given EpsBearer to the given NetDeviceContainer of LTE devices
	 * For use with LTE only networks (i.e. no EPC nor IP protocols)
	 * @param ueDevices the NetDeviceContainer of LTE devices
	 * @param bearer the EpsBearer to attach to ueDevices
	 */
	public void activateDataRadioBearer(NetDeviceContainer ueDevices, EpsBearer bearer) {
		this.appendPrintInfo(this.getName() + ".ActivateDataRadioBearer(" + ueDevices.getName()
				+ ", " + bearer.getName() + ");\n");
	}

	/**
	 * @param epcHelperPointer the EPC (Evolved Packet Core) helper to use
	 */
	public void setEpcHelper(Pointer<PointToPointEpcHelper> epcHelperPointer) {
		this.appendPrintInfo(this.getName() + ".SetEpcHelper(" + epcHelperPointer.getName() + ");\n");
	}

}
