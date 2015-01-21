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
public class PointToPointHelper extends AbstractNs3Object {
	private NodeContainer nodes;
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	/**
	 * Contruct a new point to point helper to handle creation of a p2p node/channel
	 */
	public PointToPointHelper() {
		this.channelAttributes = new HashMap<String, String>();
		this.deviceAttributes = new HashMap<String, String>();
	}

	/**
	 * 
	 * @param nodes
	 */
	public void install(NodeContainer nodes) {
		this.nodes = nodes;
		setPrintObj(getPrintObj() + this.getName() + ".Install(" + nodes.getName() + ");\n");
	}

	/**
	 * Set attributes for this point to point channel
	 * @param attr
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
	}
	
	/**
	 * Set attributes for this point to point device
	 * @param attr
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
	}
	
}
