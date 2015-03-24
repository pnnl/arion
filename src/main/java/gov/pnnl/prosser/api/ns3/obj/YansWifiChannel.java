/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * The YansWifiChannel (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf) 
 * is an implementation of the physical Wi-Fi channel.
 * 
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
	 * Creates a named YansWifiChannel
	 * 
	 * @param name
	 */
	public YansWifiChannel(String name) {
		this.setName(name);
	}

	/**
	 * Adds the given YansWifiPhy object to this YansWifiChannel
	 * 
	 * @param phy the YansWifiPhy object
	 */
	public void add(Pointer<YansWifiPhy> phy) {
		appendPrintObj(this.getName() + ".Add(" + phy.getName() + ");\n");
	}
	
}
