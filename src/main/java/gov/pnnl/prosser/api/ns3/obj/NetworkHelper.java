/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaHelper;
import gov.pnnl.prosser.api.ns3.obj.lte.LteHelper;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointHelper;
import gov.pnnl.prosser.api.ns3.obj.wifi.WifiHelper;

/**
 * A base class for the specific network helpers (Wi-Fi, LTE, CSMA, etc.)
 * 
 * @author happ546
 *
 */
public class NetworkHelper extends AbstractNs3Object {

	private static final String DEBUG_DIRECTORY = "";

	/**
	 * @param prefix the filename prefix
	 */
	public void enablePcapAll(String prefix) {
		appendPrintInfo(this.getName() + ".EnablePcapAll (\"" + DEBUG_DIRECTORY + prefix + "\", true);\n");
	}

	/**
	 *
	 * @param prefix the filename prefix
	 * @param devs the NetDeviceContainer
	 * @param index the index of the device in devs to do a PCAP on
	 */
	public void enablePcap(String prefix, NetDeviceContainer devs, int index) {
		appendPrintInfo(this.getName() + ".EnablePcap (\"" + DEBUG_DIRECTORY + prefix + "\", " + devs.getName() +
				".Get (" + index + "));\n");
	}


	/**
	 * @param prefix the filename prefix
	 */
	public void enableAsciiAll(String prefix) {
		appendPrintInfo(this.getName() + ".EnableAsciiAll (\"" + DEBUG_DIRECTORY + prefix + "\");\n");
	}

	/**
	 *
	 * @param prefix the filename prefix
	 * @param devs the NetDeviceContainer
	 * @param index the index of the device in devs to do an Ascii trace of
	 */
	public void enableAscii(String prefix, NetDeviceContainer devs, int index) {
		appendPrintInfo(this.getName() + ".EnableAscii (\"" + DEBUG_DIRECTORY + prefix + "\", " + devs.getName() +
				".Get (" + index + "));\n");
	}
	
}
