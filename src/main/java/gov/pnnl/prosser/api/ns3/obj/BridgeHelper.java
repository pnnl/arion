/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The BridgeHelper is a helper class to simplify the setup of network bridges, 
 * devices that connect one network device type to another (e.g. CSMA to point-to-point).
 * 
 * @author happ546
 *
 */
public class BridgeHelper extends AbstractNs3Object {
	
	private Map<String, String> deviceAttributes;

	/**
	 * Creates a named BridgeHelper
	 * 
	 * @param name
	 */
	public BridgeHelper(String name) {
		this.deviceAttributes = new HashMap<String, String>();
		this.setName(name);
	}
	
	/**
	 * Installs a Bridge device on each of the Nodes in sourceNodes
	 * 
	 * @param sourceNodes the NodeContainer holding the Nodes to install this Bridge device on
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + 
						".Install(" + sourceNodes.getName() + ");\n");
	}
	

	/**
	 * Installs a Bridge device on each of the Nodes in sourceNodes using 
	 * the sourceDevices as Bridge ports
	 * 
	 * @param sourceNodes the Node on which to install the Bridge net device
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
	 * Sets attributes for this Bridge device
	 * 
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		this.deviceAttributes.put(attr, value);
	}


}
