/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

/**
 * @author happ546
 *
 */
public class CsmaHelper extends NetworkHelper {
	
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	/**
	 * Create a new CsmaHelper
	 */
	public CsmaHelper() {
		this.channelAttributes = new HashMap<String, String>();
		this.deviceAttributes = new HashMap<String, String>();
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer holding the Nodes to install this CSMA device on
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		destinationContainer.addNodes(sourceNodes);
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + ".Install(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * Set attributes for this CSMA channel
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetChannelAttribute(\"" + attr + "\", StringValue(\"" + value + "\"));\n");
	}
	
	/**
	 * Set attributes for this CSMA device
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetDeviceAttribute(\"" + attr + "\", StringValue(\"" + value + "\"));\n");
	}
	
}
