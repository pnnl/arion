/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NqosWifiMacHelper extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public NqosWifiMacHelper(String name) {
		this.setName(name);
	}

	/**
	 * Sets the default parameters for this NqosWifiMacHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintObj(this.getName() + " = NqosWifiMacHelper::Default();\n");
	}

	/**
	 * 
	 * @param type the MAC type for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 * @param activeProbing used by the StaWifiMac ns-3 class
	 */
	public void setType(String type, Ssid ssid, boolean activeProbing) {
		appendPrintObj(this.getName() + ".SetType(\"" + type + "\", "
				+ "\"Ssid\", SsidValue(" + ssid.getName() + "), "
						+ "\"ActiveProbing\", BooleanValue(" + activeProbing + "));\n");
	}

	/**
	 * 
	 * @param type the MAC type for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 */
	public void setType(String type, Ssid ssid) {
		appendPrintObj(this.getName() + ".SetType(\"" + type + "\", "
				+ "\"Ssid\", SsidValue(" + ssid.getName() + "));\n");
	} 
	
}
