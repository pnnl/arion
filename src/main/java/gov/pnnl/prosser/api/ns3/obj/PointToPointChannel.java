/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * 
 * 
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
	 * Returns one of the end point Nodes of this PointToPointChannel
	 * 
	 * @return nodeA Pointer&lt;Node&gt;
	 */
	public Pointer<Node> getNodeA() {
		return nodeA;
	}
	
	/**
	 * @param node the Pointer&lt;Node&gt; to set nodeA to
	 */
	public void setNodeA(Pointer<Node> node) {
		nodeA = node;
		nodeA.setNameString(node.getName());
	}

	/**
	 * Returns one of the end point Nodes of this PointToPointChannel
	 * 
	 * @return nodeB Pointer&lt;Node&gt;
	 */
	public Pointer<Node> getNodeB() {
		return nodeB;
	}

	/**
	 * @param node the Pointer&lt;Node&gt; to set nodeB to
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
