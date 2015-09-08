/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.csma;

import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to help setup CSMA net devices and networks.
 * 
 * @author happ546
 *
 */
public class CsmaHelper extends NetworkHelper {
	
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	/**
	 * Create a new CsmaHelper
	 * @param name the string name
	 */
	public CsmaHelper(String name) {
		this.setName(name);
		this.channelAttributes = new HashMap<>();
		this.deviceAttributes = new HashMap<>();
	}
	
	/**
	 * @param node the Node to install the CSMA device on and connect to the channel
	 * @param channel the CsmaChannel to use
	 * @param destinationContainer the NetDeviceContainer to store the CsmaNetDevice in
	 */
	public void install(Node node, CsmaChannel channel,
			NetDeviceContainer destinationContainer) {
		
		appendPrintInfo(destinationContainer.getName() +
				".Add (" + this.getName() + ".Install (" +
				node.getName() + ", " + channel.getPointerName() + "));\n");
	}
	
	/**
	 * @param nodes the NodeContainer to install the CSMA devices on and connect to the channel
	 * @param channel the CsmaChannel to use
	 * @param destinationContainer the NetDeviceContainer to store the CsmaNetDevices in
	 */
	public void install(NodeContainer nodes, CsmaChannel channel,
			NetDeviceContainer destinationContainer) {
		
		appendPrintInfo(destinationContainer.getName() +
				".Add (" + this.getName() + ".Install (" +
				nodes.getName() + ", " + channel.getPointerName() + "));\n");
	}
	
	/**
	 * Set attributes for this CSMA channel
	 * @param attr the attribute to set the value to
	 * @param value the string value to set
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintInfo(this.getName() + ".SetChannelAttribute (\"" + attr +
				"\", StringValue (\"" + value + "\"));\n");
	}
	
	/**
	 * Set attributes for this CSMA device
	 * @param attr the attribute to set the value to
	 * @param value the string value to set
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		appendPrintInfo(this.getName() + ".SetDeviceAttribute (\"" + attr +
				"\", StringValue (\"" + value + "\"));\n");
	}

}
