/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;

import java.util.ArrayList;

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
	 * An array containing the Nodes in this NodeContainer
	 */
	private ArrayList<Node> nodes;
	
	/**
	 * Creates nameless NodeContainer; used in NetDeviceContainer
	 */
	public NodeContainer() {
		this.nodes = new ArrayList<>();
	}
	
	/**
	 * Initializes an empty NodeContainer
	 * @param name the string name
	 */
	public NodeContainer(String name) {
		this.nodes = new ArrayList<>();
		this.setName(name);
	}

	/**
	 * Creates specified number of nodes in this NodeContainer
	 * @param numNodes the number of nodes to create
	 */
	public void create(int numNodes) {
		for (int i = 0; i < numNodes; i++) {
			String name = this.getName() + "_container_node_" + (i + 1) * Math.random();
			this.addNode(new Node(name.replace('.', '_')));
		}
		appendPrintInfo(this.getName() + ".Create(" + numNodes + ");\n");
	}

	/**
	 * @param node the Node to add to this NodeContainer
	 */
	public void addNode(Node node) {
		appendPrintInfo(this.getName() + ".Add(" + node.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param container the NodeContainer containing the Node to add to this NodeContainer
	 * @param index the index of the Node to be added
	 */
	public void addNode(NodeContainer container, int index) {
		this.addNode(container.getNodeNoPrint(index));
		appendPrintInfo(this.getName() + ".Add(" + container.getName() + ".Get(" + index + "));\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainer(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.nodes.add(sourceNodes.getNodeNoPrint(i));
		}
		appendPrintInfo(this.getName() + ".Add(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainerNoPrint(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.nodes.add(sourceNodes.getNodeNoPrint(i));
		}
	}
	
	/**
	 * 
	 * @param sourceDevs the NodeContainer to append to this NodeContainer
	 */
	public void addNetDeviceContainerNoPrint(NetDeviceContainer sourceDevs) {
		for (int i = 0; i < sourceDevs.getNumDevices(); i++) {
			this.nodes.add(sourceDevs.getDevice(i));
		}
	}

	/**
	 * @return the number of Nodes in this NodeContainer
	 */
	public int getNumNodes() {
		return this.nodes.size();
	}
	
	/**
	 * @param index the index of the node to get
	 * @param backboneRouterPtr the Pointer&lt;Node&gt; to add the node at index to
	 */
	public void getNode(int index, Pointer<Node> backboneRouterPtr) {
		appendPrintInfo(backboneRouterPtr.getName() + " = " + this.getName()
				+ ".Get(" + index + ");\n");
	}

	/**
	 * Returns the Node at the given index without outputting any text to the C++ output file
	 * 
	 * @param index the integer index of the Node to retrieve from the NodeContainer
	 * @return the Node at the given index or null if there is no node at that index
	 */
	public Node getNodeNoPrint(int index) {
		if (index >= this.nodes.size() || this.nodes.get(index) == null) {
			return null;
		}
		return nodes.get(index);
	}

}
