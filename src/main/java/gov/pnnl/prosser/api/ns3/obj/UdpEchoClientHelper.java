/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

/**
 * The UdpEchoClientHelper is a client helper class to simplify the setup of 
 * UDP (User Datagram Protocol) ping devices to test network connectivity.
 * 
 * @author happ546
 *
 */
public class UdpEchoClientHelper extends UdpEchoHelper {
	/**
	 * A map of attributes and their values (e.g. "Interval" to 1.0 (seconds))
	 */
	private Map<String, Double> attributes;
	
	/**
	 * Creates a new UdpEchoClientHelper with the given name
	 * 
	 * @param name
	 */
	public UdpEchoClientHelper(String name) {
		this.setName(name);
		attributes = new HashMap<>();
	}

	/**
	 * Sets the given attribute to the given value
	 * 
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
		appendPrintInfo(this.getName() + ".SetAttribute(\"" + attr + "\", "
				+ modifier + "(" + this.getPort() + ");\n");
	}

	/**
	 * Sets the IP address for this UdpEchoClientHelper
	 * 
	 * @param address the IP address
	 */
	public void setAddress(String address) {
		appendPrintInfo("UdpEchoClientHelper " + this.getName() + "(" + address + ","
				+ this.getPort() + ");\n");
	}	

}
