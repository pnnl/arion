/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class BridgeHelper extends AbstractNs3Object {
	
	private Map<String, String> deviceAttributes;

	/**
	 * Create a new BridgeHelper to create a connection between two communication devices 
	 * on a single Node
	 */
	public BridgeHelper() {
		this.deviceAttributes = new HashMap<String, String>();
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer holding the Nodes to install this Bridge device on
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + 
						".Install(" + sourceNodes.getName() + ");\n");
	}
	

	/**
	 * 
	 * @param node the Node on which to install the Bridge net device
	 * @param sourceDevices the container of net devices to add as Bridge ports
	 * @param destinationContainer the NetDeviceContainer to hold the Node and
	 * 			installed Bridge device
	 */
	public void install(NodeContainer sourceNodes, int index, 
						NetDeviceContainer sourceDevices, NetDeviceContainer destinationContainer) {
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + 
						".Install(" + sourceNodes.getName() + ".Get(" + index + "), " + 
						sourceDevices.getName() +");\n");
	}
	
	/**
	 * Set attributes for this Bridge device
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		this.deviceAttributes.put(attr, value);
	}


}
