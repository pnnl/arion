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
public class UdpEchoClientHelper extends UdpEchoHelper {
	/**
	 * A map of attributes and their values (e.g. "Interval" to 1.0 (seconds))
	 */
	private Map<String, Double> attributes;
	
	/**
	 * Creates a new UdpEchoClientHelper
	 * @param name
	 */
	public UdpEchoClientHelper(String name) {
		this.setName(name);
		attributes = new HashMap<String, Double>();
	}

	/**
	 * Set the given attribute to the given value
	 * @param attr
	 * @param value
	 */
	public void setAttribute(String attr, double value) {
		attributes.put(attr, value);
		String modifier = "";
		if (attr.equalsIgnoreCase("interval") 
				|| attr.equalsIgnoreCase("starttime")
				|| attr.equalsIgnoreCase("stoptime")) {
			modifier = "TimeValue";
		} else if (attr.equalsIgnoreCase("maxpackets") 
				|| attr.equalsIgnoreCase("packetsize")
				|| attr.equalsIgnoreCase("remoteport")) {
			modifier = "UintegerValue";
		}
		setPrintObj(getPrintObj() + this.getName() + ".SetAttribute(\"" + attr + "\", " + modifier + "(" + this.getPort() + ");\n");
	}

	/**
	 * @param address the IP address to be used for this UDP echo client
	 */
	public void setAddress(String address) {
		setPrintObj("UdpEchoClientHelper " + this.getName() + "(" + address + "," + this.getPort() + ");\n");
	}	

}
