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
	 * @param devices 
	 * @param index 
	 */
	public void enablePcapSingleDevice(String prefix, NetDeviceContainer devices, int index) {
		appendPrintObj(this.getName() + ".EnablePcap(\"" + prefix + "\", " 
					+ devices.getName() + ".Get(" + index + "), true);\n");
	}
	
	/**
	 * @param prefix the filename prefix
	 */
	public void enablePcapAll(String prefix) {
		appendPrintObj(this.getName() + ".EnablePcapAll(\"" + prefix + "\", true);\n");
	}

}
