/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * @author happ546
 *
 */
public class PointToPointChannel extends Channel {
	
	/**
	 * Nodes this Channel connects
	 */
	private Pointer<Node> nodeA, nodeB;
	
	/**
	 * Creates a nameless PointToPointChannel
	 */
	public PointToPointChannel() {
		nodeA = null;
		nodeB = null;
	}
	
	/**
	 * @param name
	 */
	public PointToPointChannel(String name) {
		this();
		this.setName(name);
	}
	
	/**
	 * @return nodeA Pointer<Node>
	 */
	public Pointer<Node> getNodeA() {
		return nodeA;
	}
	
	/**
	 * @param node the Pointer<Node> to set nodeA to
	 */
	public void setNodeA(Pointer<Node> node) {
		nodeA = node;
		nodeA.setNameString(node.getName());
	}

	/**
	 * @return nodeB Pointer<Node>
	 */
	public Pointer<Node> getNodeB() {
		return nodeB;
	}

	/**
	 * @param node the Pointer<Node> to set nodeB to
	 */
	public void setNodeB(Pointer<Node> node) {
		nodeB = node;
		nodeB.setNameString(node.getName());
	}

	/**
	 * @param device the PointToPointNetDevice to connect to this PointToPointChannel
	 */
	public void attach(Pointer<PointToPointNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}

}
