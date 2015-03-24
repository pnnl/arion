/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The YansWifiPhy (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf) 
 * is an implementation of the Wi-Fi 802.11a physical layer.
 * 
 * @author happ546
 *
 */
public class YansWifiPhy extends AbstractNs3Object {
	
	/**
	 * Creates a named YansWifiPhy
	 * 
	 * @param name
	 */
	public YansWifiPhy(String name) {
		this.setName(name);
	}

}
