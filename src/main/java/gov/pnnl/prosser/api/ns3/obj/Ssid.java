/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ssid extends AbstractNs3Object {

	/**
	 * 
	 * @param ssid the ssid (Service Set Identifier; basically the name of a WiFi network) value for this Ssid object
	 */
	public void setSsid(String ssid) {
		appendPrintObj(this.getName() + " = Ssid(\"" + ssid + "\");");
	}
	
}
