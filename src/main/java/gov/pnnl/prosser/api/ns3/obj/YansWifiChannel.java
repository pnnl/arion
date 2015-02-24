/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * @author happ546
 *
 */
public class YansWifiChannel extends Channel {

	/**
	 * Creates a nameless YansWifiChannel
	 */
	public YansWifiChannel() {
	}
	
	/**
	 * @param name
	 */
	public YansWifiChannel(String name) {
		this.setName(name);
	}

	/**
	 * @param phy the YansWifiPhy object to add to this YansWifiChannel list of physical devices
	 */
	public void add(Pointer<YansWifiPhy> phy) {
		appendPrintObj(this.getName() + ".Add(" + phy.getName() + ");\n");
	}
	
}
