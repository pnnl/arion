/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NetDeviceContainer extends AbstractNs3Object {
	
	private NodeContainer nodes;
	
	public NetDeviceContainer() {
		this.nodes = new NodeContainer();
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer nodes
	 */
	public void addNodes(NodeContainer sourceNodes) {
		this.nodes.addNodeContainer(sourceNodes);
	}

	/**
	 * 
	 * @param sourceNodes the NodeContainer to store in this NetDeviceContainer's NodeContainer nodes
	 */
	public void setNodes(NodeContainer sourceNodes) {
		this.nodes = sourceNodes;
	}

	/**
	 * 
	 * @param index
	 * @return the Node in this net device at the given index
	 */
	public Node getNode(int index) {
		return this.nodes.getNode(index);
	}
	
}
