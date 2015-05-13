/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to help setup CSMA net devices and networks.
 * 
 * @author happ546
 *
 */
public class CsmaHelper extends PcapHelperForDevice {
	
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	/**
	 * Create a new CsmaHelper
	 * @param name
	 */
	public CsmaHelper(String name) {
		this.setName(name);
		this.channelAttributes = new HashMap<String, String>();
		this.deviceAttributes = new HashMap<String, String>();
	}
	
/*	*//**
	 * @param nodePtr the Pointer<Node>
	 * @param channel the CsmaChannel
	 * @param destinationContainer
	 *//*
	public void install(Pointer<Node> nodePtr, Pointer<CsmaChannel> channel, 
						NetDeviceContainer destinationContainer) {
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + 
				".Install(" + nodePtr.getName() + ", " + channel.getName() + ");\n");
	}*/
	
	// Methods for avoiding Pointer<...> class
	
	/**
	 * @param node
	 * @param channel 
	 * @param destinationContainer 
	 */
	public void install(Node node, CsmaChannel channel, 
			NetDeviceContainer destinationContainer) {
		
		// Creates ns-3 smart pointers for Node and CsmaChannel
		String nodePointer = node.getAsPointer();
		String channelPointer = channel.getAsPointer();
		
		appendPrintObj(nodePointer);
		appendPrintObj(channelPointer);
		appendPrintObj(destinationContainer.getName() + 
				" = " + this.getName() + ".Install(" + 
				node.getName() + ", " + channel.getName() + ");\n");
	}
	
	/**
	 * @param nodes
	 * @param channel 
	 * @param destinationContainer 
	 */
	public void install(NodeContainer nodes, CsmaChannel channel, 
			NetDeviceContainer destinationContainer) {
		
		// Creates ns-3 smart pointer for CsmaChannel
		String channelPointer = channel.getAsPointer();
		
		appendPrintObj(channelPointer);
		appendPrintObj(destinationContainer.getName() + 
				" = " + this.getName() + ".Install(" + 
				nodes.getName() + ", " + channel.getName() + ");\n");
	}

/*	*//**
	 * @param sourceNodes the NodeContainer to install the CSMA devices on
	 * @param channel the Pointer<CsmaChannel> to connect the CSMA devices to
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 *//*
	public void install(NodeContainer sourceNodes,
			Pointer<CsmaChannel> channel, NetDeviceContainer destinationContainer) {
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + 
				".Install(" + sourceNodes.getName() + ", " + channel.getName() + ");\n");		
	}*/
	
	/**
	 * Set attributes for this CSMA channel
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetChannelAttribute(\"" + attr + 
				"\", StringValue(\"" + value + "\"));\n");
	}
	
	/**
	 * Set attributes for this CSMA device
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetDeviceAttribute(\"" + attr + 
				"\", StringValue(\"" + value + "\"));\n");
	}

}
