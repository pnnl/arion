/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.wifi;

/**
 * The YansWifiChannel (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf) 
 * is an implementation of the physical Wi-Fi channel.
 * 
 * @author happ546
 *
 */
public class YansWifiChannel extends WifiChannel {

	public YansWifiChannel() {
		super();
	}
	/**
	 * Creates a named YansWifiChannel
	 * 
	 * @param name
	 */
	public YansWifiChannel(String name) {
		super(name);
		this.setName(name);
		this.getAsPointer();
	}
}
