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
	private String address;
	
	public UdpEchoClientHelper() {
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

	public void setAddress(String address) {
		this.address = address;
		setPrintObj("UdpEchoClientHelper " + this.getName() + "(" + this.address + "," + this.getPort() + ");\n");
	}	

}
