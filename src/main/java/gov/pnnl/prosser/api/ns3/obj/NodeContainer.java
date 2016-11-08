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

import java.util.ArrayList;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * This class provides a structure to efficiently hold Nodes in
 * and make it easier to configure NetDevices on multiple Nodes
 * simultaneously.
 *
 * @author happ546
 *
 */
public class NodeContainer extends AbstractNs3Object {

	/**
	 * An array containing the Routers in this NodeContainer
	 */
	private ArrayList<Router> routers;

	/**
	 * An array containing the Nodes in this NodeContainer
	 */
	public ArrayList<Node> nodes;

	/**
	 * Creates nameless NodeContainer; used in NetDeviceContainer
	 */
	public NodeContainer() {
		this.routers = new ArrayList<>();
		this.nodes = new ArrayList<>();
	}

	/**
	 * Initializes an empty NodeContainer
	 * @param name the string name
	 */
	public NodeContainer(String name) {
		this();
		this.setName(name);
	}

	/**
	 * Creates specified number of routers in this NodeContainer
	 * @param numNodes the number of routers to create
	 */
	public void create(int numNodes) {
		for (int i = 0; i < numNodes; i++) {
			String name = this.getName() + "_" + (i + 1);
			this.addRouterNoPrint(new Router(name.replace('.', '_')));
		}
		appendPrintInfo(this.getName() + ".Create (" + numNodes + ");\n");
	}

	/**
	 * @param router the Node to add to this NodeContainer
	 */
	public void addRouter(Router router) {
		this.addRouterNoPrint(router);
		appendPrintInfo(this.getName() + ".Add (" + router.getPointerName() + ");\n");
	}

	/**
	 *
	 * @param container the NodeContainer containing the Node to add to this NodeContainer
	 * @param index the index of the Node to be added
	 */
	public void addRouter(NodeContainer container, int index) {
		this.addRouter(container.getRouterNoPrint(index));
		appendPrintInfo(this.getName() + ".Add (" + container.getName() + ".Get (" + index + "));\n");
	}

	public void addRouterNoPrint(Router router) {
		this.routers.add(router);
	}

	public void addNode(Node node) {
		this.nodes.add(node);
		appendPrintInfo(this.getName() + ".Add (" + node.getName() + ");\n");
	}

	/**
	 *
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainer(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.routers.add(sourceNodes.getRouterNoPrint(i));
		}
		appendPrintInfo(this.getName() + ".Add (" + sourceNodes.getName() + ");\n");
	}

	/**
	 *
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainerNoPrint(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.routers.add(sourceNodes.getRouterNoPrint(i));
		}
	}

	public void addNetDeviceContainerNoPrint(NetDeviceContainer devs) {
		for (int i = 0; i < devs.getNumDevices(); i++) {
			this.nodes.add(devs.getDevice(i));
		}
	}

	/**
	 * @return the number of Nodes in this NodeContainer
	 */
	public int getNumNodes() {
		return this.routers.size();
	}

	/**
	 * Returns the Router at the given index without outputting any text to the C++ output file
	 *
	 * @param index the integer index of the Node to retrieve from the NodeContainer
	 * @return the Router at the given index or null if there is no node at that index
	 */
	public Router getRouterNoPrint(int index) {
		if (index >= this.routers.size() || this.routers.get(index) == null) {
			return null;
		}
		return routers.get(index);
	}

	public Node getNodeNoPrint(int index) {
		if (index >= this.nodes.size() || this.nodes.get(index) == null) {
			return null;
		}
		return nodes.get(index);
	}

}
