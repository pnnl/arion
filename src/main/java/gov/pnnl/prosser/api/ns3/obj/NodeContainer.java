/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import java.util.ArrayList;

/**
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
		this.nodes = new ArrayList<Node>();
	}
	
	/**
	 * Initializes an empty NodeContainer
	 * @param name
	 */
	public NodeContainer(String name) {
		this.nodes = new ArrayList<Node>();
		this.setName(name);
	}

	/**
	 * Creates specified number of nodes in this NodeContainer
	 * @param numNodes
	 */
	public void create(int numNodes) {
		for (int i = 0; i < numNodes; i++) {
			this.addNode(new Node());
		}
		appendPrintObj(this.getName() + ".Create(" + numNodes + ");\n");
	}

	/**
	 * Appends a new node to the end of the nodes array
	 */
	void addNode(Node node) {
		this.nodes.add(node);
	}
	
	/**
	 * 
	 * @param container the NodeContainer containing the Node to add to this NodeContainer
	 * @param index the index of the Node to be added
	 */
	public void addNode(NodeContainer container, int index) {
		this.addNode(container.getNode(index));
		appendPrintObj(this.getName() + ".Add(" + container.getName() + ".Get(" + index + "));\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainer(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.nodes.add(sourceNodes.getNode(i));
		}
		appendPrintObj(this.getName() + ".Add(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainerNoPrint(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.nodes.add(sourceNodes.getNode(i));
		}
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
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
	 * 
	 * @param index the integer index of the Node to retrieve from the NodeContainer
	 * @return the Node at the given index or null if there is no node at that index
	 */
	public Node getNode(int index) {
		if (index >= this.nodes.size() || this.nodes.get(index) == null) {
			return null;
		}
		return nodes.get(index);
	}

}
