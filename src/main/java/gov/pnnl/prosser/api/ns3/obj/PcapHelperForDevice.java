/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/**
 * @author happ546
 *
 */
public class PcapHelperForDevice extends NetworkHelper {
	
	/**
	 * @param prefix the filename prefix
	 */
	public void enablePcapAll(String prefix) {
		appendPrintObj(this.getName() + ".EnablePcapAll (\"" + prefix + "\", true);\n");
	}

	/**
	 *
	 * @param prefix the filename prefix
	 * @param devs the NetDeviceContainer
	 * @param index the index of the device in devs to do a PCAP on
	 */
	public void enablePcap(String prefix, NetDeviceContainer devs, int index) {
		appendPrintObj(this.getName() + ".EnablePcap (\"" + prefix + "\", " + devs.getName() +
			".Get (" + index + "));\n");
	}

}
