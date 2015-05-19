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
	
	/**
	 * @param node
	 * @param channel 
	 * @param destinationContainer 
	 */
	public void install(Node node, CsmaChannel channel, 
			NetDeviceContainer destinationContainer) {
		
		appendPrintObj(destinationContainer.getName() + 
				" = " + this.getName() + ".Install(" + 
				node.getPointerName() + ", " + channel.getPointerName() + ");\n");
	}
	
	/**
	 * @param nodes
	 * @param channel 
	 * @param destinationContainer 
	 */
	public void install(NodeContainer nodes, CsmaChannel channel, 
			NetDeviceContainer destinationContainer) {
		
		appendPrintObj(destinationContainer.getName() + 
				" = " + this.getName() + ".Install(" + 
				nodes.getName() + ", " + channel.getPointerName() + ");\n");
	}
	
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
