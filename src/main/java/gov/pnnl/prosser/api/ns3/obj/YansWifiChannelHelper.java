/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The YansWifiChannelHelper (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf)
 * is a helper class that simplifies the setup of YansWifiChannels.
 * 
 * @author happ546
 *
 */
public class YansWifiChannelHelper extends AbstractNs3Object {
	
	/**
	 * Creates a named YansWifiChannelHelper
	 * 
	 * @param name
	 */
	public YansWifiChannelHelper(String name) {
		this.setName(name);
	}
	
	/**
	 * Sets the default parameters for this YansWifiChannelHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintObj(this.getName() + " = YansWifiChannelHelper::Default();\n");
	}

	/**
	 * Used as parameter in YansWifiPhyHelper method setChannel
	 */
	public YansWifiChannelHelper create() {
		return this;
	}

}
