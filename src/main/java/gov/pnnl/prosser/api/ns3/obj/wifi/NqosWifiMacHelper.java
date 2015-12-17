/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * The NqosWifiMacHelper (No Quality-of-Service) is a helper class to simplify 
 * the setup of NQoS Wi-Fi MAC layers in Wi-Fi networks.
 * 
 * @author happ546
 *
 */
public class NqosWifiMacHelper extends AbstractNs3Object {

	private WifiMacType wifiMacType;

	/**
	 * @param name
	 */
	public NqosWifiMacHelper(String name) {
		this.setName(name);
		wifiMacType = null;
	}

	public NqosWifiMacHelper(String name, WifiMacType type) {
		this(name);
		wifiMacType = type;
	}

	/**
	 * Sets the default parameters for this NqosWifiMacHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = NqosWifiMacHelper::Default();\n");
	}

	/**
	 * 
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 * @param activeProbing used by the StaWifiMac ns-3 class
	 */
	public void setType(WifiMacType type, Ssid ssid, boolean activeProbing) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\", "
				+ "\"Ssid\", SsidValue (" + ssid.getName() + "), "
				+ "\"ActiveProbing\", BooleanValue (" + activeProbing + "));\n");
	}

	/**
	 * 
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 */
	public void setType(WifiMacType type, Ssid ssid) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\", "
				+ "\"Ssid\", SsidValue (" + ssid.getName() + "));\n");
	}

	/**
	 *
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 */
	public void setType(WifiMacType type) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\");\n");
	}

	public WifiMacType getWifiMacType() {
		return wifiMacType;
	}

	public void setWifiMacType(WifiMacType wifiMacType) {
		this.wifiMacType = wifiMacType;
	}
}
