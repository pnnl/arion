/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The Ssid (Service Set IDentifer) is used as a name for Wi-Fi networks.
 * 
 * @author happ546
 *
 */
public class Ssid extends AbstractNs3Object {
	
	/**
	 * Creates a new Ssid with the given name
	 * 
	 * @param name
	 */
	public Ssid(String name) {
		this.setName(name);
	}

	/**
	 * 
	 * @param ssid the ssid (Service Set Identifier; 
	 * 		basically the name of a WiFi network) value for this Ssid object
	 */
	public void setSsid(String ssid) {
		appendPrintInfo(this.getName() + " = Ssid(\"" + ssid + "\");");
	}
	
}
