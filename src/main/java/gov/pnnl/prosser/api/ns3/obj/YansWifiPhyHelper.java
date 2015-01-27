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
	 * Sets the default parameters for this YansWifiPhyHelper (see ns-3 documentation for information) 
	 */
	public void defaultParams() {
		appendPrintObj(this.getName() + " = YansWifiPhyHelper::Default();\n");
	}
	
	public void setChannel(YansWifiChannelHelper channel) {
		appendPrintObj(this.getName() + ".SetChannel(" + channel.getName() + ".Create());\n");
	}
	
}
