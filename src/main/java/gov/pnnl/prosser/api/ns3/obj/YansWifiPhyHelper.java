/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
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
		appendPrintObj(this.getName() + " = YansWifiPhyHelper::Default();\n");
	}
	
	/**
	 * 
	 * @param channel the channel used by this YansWifiChannelHelper
	 */
	public void setChannel(YansWifiChannelHelper channel) {
		appendPrintObj(this.getName() + ".SetChannel(" + channel.getName() + ".Create());\n");
	}

	/**
	 * 
	 * @param dlType the PCAP data link type for this YansWifiPhyHelper
	 */
	public void setPcapDataLinkType(String dlType) {
		appendPrintObj(this.getName() + ".SetPcapDataLinkType(" + dlType + ");\n");
		
	}
	
}
