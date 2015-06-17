/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The YansWifiPhyHelper (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf) 
 * is a helper class to simplify the setup of YansWifiPhy physical layer implementations of the 
 * Wi-Fi 802.11a physical layer.
 * 
 * @author happ546
 *
 */
public class YansWifiPhyHelper extends AbstractNs3Object {
	
	/**
	 * @param name
	 */
	public YansWifiPhyHelper(String name) {
		this.setName(name);
	}

	/**
	 * Sets the default parameters for this YansWifiPhyHelper (see ns-3 documentation for information) 
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = YansWifiPhyHelper::Default();\n");
	}
	
	/**
	 * 
	 * @param channel the channel used by this YansWifiChannelHelper
	 */
	public void setChannel(YansWifiChannelHelper channel) {
		appendPrintInfo(this.getName() + ".SetChannel(" + channel.getName() + ".Create());\n");
	}

	/**
	 * 
	 * @param dlType the PCAP data link type for this YansWifiPhyHelper
	 */
	public void setPcapDataLinkType(String dlType) {
		appendPrintInfo(this.getName() + ".SetPcapDataLinkType(" + dlType + ");\n");
		
	}
	
}
