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
	 * Sets the default parameters for this NqosWifiMacHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintObj(this.getName() + " = NqosWifiMacHelper::Default();\n");
	}

	public void setType(String type, Ssid ssid, boolean activeProbing) {
		appendPrintObj(this.getName() + ".SetType(\"" + type + "\", "
				+ "\"Ssid\", SsidValue(" + ssid.getName() + "), "
						+ "\"ActiveProbing\", BooleanValue(" + activeProbing + "));\n");
	}

	public void setType(String type, Ssid ssid) {
		appendPrintObj(this.getName() + ".SetType(\"" + type + "\", "
				+ "\"Ssid\", SsidValue(" + ssid.getName() + "));\n");
	} 
	
}
