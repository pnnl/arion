/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

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
	private Node nodeA, nodeB;
	
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
	 * @return nodeA
	 */
	public Node getNodeA() {
		return nodeA;
	}
	
	/**
	 * @param node one of the endpoint Nodes for this p2p channel
	 */
	public void setNodeA(Node node) {
		nodeA = node;
		nodeA.setNameString(node.getName());
	}

	/**
	 * Returns one of the end point Nodes of this PointToPointChannel
	 * 
	 * @return nodeB
	 */
	public Node getNodeB() {
		return nodeB;
	}

	/**
	 * @param node one of the endpoint Nodes for this p2p channel
	 */
	public void setNodeB(Node node) {
		nodeB = node;
		nodeB.setNameString(node.getName());
	}

	/**
	 * @param device the PointToPointNetDevice to connect to this PointToPointChannel
	 */
	public void attach(PointToPointNetDevice device) {
		
		// Get time to avoid name conflicts in output ns-3 file
		long currentTime = System.currentTimeMillis();
		
		String pointer = "Ptr<PointToPointNetDevice> pointToPointNetDevicePointer_" 
				+ currentTime + " = " + device.getName() + ";\n";
		
		appendPrintObj(this.getName() + ".Attach(" + pointer + ");\n");
	}

}
