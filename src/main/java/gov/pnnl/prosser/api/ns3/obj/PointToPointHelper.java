/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

/**
 * The PointToPointHelper simplifies the setup of point-to-point (p2p) networks.
 * 
 * @author happ546
 *
 */
public class PointToPointHelper extends PcapHelperForDevice {
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	/**
	 * Construct a new point to point helper to handle creation of a p2p node/channel
	 * 
	 * @param name
	 */
	public PointToPointHelper(String name) {
		this.setName(name);
		this.channelAttributes = new HashMap<String, String>();
		this.deviceAttributes = new HashMap<String, String>();
	}
	

//	/**
//	 * Creates a PointToPointNetDevice on each Node and connects them with a p2p channel
//	 * 
//	 * @param node1 Pointer&lt;Node&gt;
//	 * @param node2 Pointer&lt;Node&gt;
//	 * @param destination the NetDeviceContainer to hold the created p2pNetDevices
//	 */
//	public void install(Pointer<Node> node1, Pointer<Node> node2, 
//			NetDeviceContainer destination) {
//		appendPrintObj(destination.getName() + " = " + this.getName() 
//				+ ".Install(" + node1.getName() + ", " + node2.getName() + ");\n");
//	}

	/**
	 * @param nodeA 
	 * @param nodeB 
	 * @param channel 
	 * @param p2pDevices
	 */
	public void install(Node nodeA, Node nodeB, 
			PointToPointChannel channel, NetDeviceContainer p2pDevices) {
		
		// Create ns-3 smart pointers for endpoint Nodes
		String nodePointerA = nodeA.getAsPointer();
		String nodePointerB = nodeB.getAsPointer();
		
		appendPrintObj(nodePointerA);
		appendPrintObj(nodePointerB);
		
		this.setChannelAttribute("Delay", channel.getDelay());
		this.setDeviceAttribute("DataRate", channel.getDataRate());
		
		appendPrintObj(p2pDevices.getName() + " = " + this.getName() +
				".Install(" + nodeA.getPointerName() + ", " + 
				nodeB.getPointerName() + ");\n");
	}

	/**
	 * Installs p2p NetDevices on the nodes in the given NodeContainer
	 * 
	 * @param nodes
	 */
	public void install(NodeContainer nodes) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	


	/**
	 * Sets attributes for this point to point channel
	 * 
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetChannelAttribute(\"" + attr 
				+ "\", StringValue(\"" + value + "\"));\n");
	}
	
	/**
	 * Sets attributes for this point to point device
	 * 
	 * @param attr the attribute to set the value to
	 * @param value the string value (e.g. DataRate)
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		String valueWrapperPrefix = "StringValue(\"";
		String valueWrapperSuffix = "\")";
		if (attr.equals("DataRate")) {
			valueWrapperPrefix = "DataRateValue(DataRate(\"";
			valueWrapperSuffix = "\"))";
		}
		appendPrintObj(this.getName() + ".SetDeviceAttribute(\"" + attr + "\", " 
				+ valueWrapperPrefix + value + valueWrapperSuffix + ");\n");
	}

	/**
	 * @param attr the attribute to set the value to
	 * @param value the integer value (e.g. MTU value)
	 */
	public void setDeviceAttribute(String attr, int value) {
		deviceAttributes.put(attr, "" + value);
		appendPrintObj(this.getName() + ".SetDeviceAttribute(\"" + attr 
				+ "\", UintegerValue(" + value + "));\n");
	}


	public void install(Node node, PointToPointChannel channel,
			NetDeviceContainer tempDev) {
		// TODO Auto-generated method stub
		
	}

}
