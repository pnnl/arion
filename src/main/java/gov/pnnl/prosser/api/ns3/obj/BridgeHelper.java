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

	public BridgeHelper() {
		this.deviceAttributes = new HashMap<String, String>();
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer holding the Nodes to install this Bridge device on
	 * @param destinationContainer the NetDeviceContainer to add the Nodes from sourceNodes to
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		destinationContainer.addNodes(sourceNodes);
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + ".Install(" + sourceNodes.getName() + ");\n");
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
