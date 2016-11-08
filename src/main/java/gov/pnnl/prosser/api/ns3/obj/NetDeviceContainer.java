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
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NetDeviceContainer extends AbstractNs3Object {

	private NodeContainer nodes;

	/**
	 * Creates an empty NetDeviceContainer with the given name
	 * @param name
	 */
	public NetDeviceContainer(String name) {
		this.nodes = new NodeContainer();
		this.setName(name);
	}

	/**
	 *
	 * @param nodes the NodeContainer holding the Node to add to this NetDeviceContainer
	 * @param index the index of the Node to add to this NetDeviceContainer
	 */
	public void addDevice(NetDeviceContainer nodes, int index) {
		this.nodes.addNode(nodes.getDevice(index));
		appendPrintInfo(this.getName() + ".Add(" + nodes.getName() + ".Get(" + index + "));\n");
	}

	/**
	 *
	 * @param sourceNodes the NodeContainer to add to this NetDeviceContainer's
	 * 			NodeContainer
	 */
	public void addNodes(NodeContainer sourceNodes) {
		this.nodes.addNodeContainerNoPrint(sourceNodes);
	}

	/**
	 * @param sourceDevices the NetDeviceContainer to append to this container
	 */
	public void addDevices(NetDeviceContainer sourceDevices) {
		this.addDevicesNoPrint(sourceDevices);
		appendPrintInfo(this.getName() + ".Add(" + sourceDevices.getName() + ");\n");
	}

	/**
	 *
	 * @param sourceDevices the NodeContainer to append to this NetDeviceContainer
	 */
	public void addDevicesNoPrint(NetDeviceContainer sourceDevices) {
		this.nodes.addNetDeviceContainerNoPrint(sourceDevices);
	}

	/**
	 *
	 * @param sourceNodes the NodeContainer to store in this NetDeviceContainer
	 */
	public void setNodes(NodeContainer sourceNodes) {
		this.nodes = sourceNodes;
	}

	/**
	 *
	 * @param index
	 * @return the device Node in this net device at the given index
	 */
	public Node getDevice(int index) {
		return this.nodes.getNodeNoPrint(index);
	}


	/**
	 *
	 * @return the number of devices in this NetDeviceContainer
	 */
	public int getNumDevices() {
		return this.nodes.getNumNodes();
	}



}
