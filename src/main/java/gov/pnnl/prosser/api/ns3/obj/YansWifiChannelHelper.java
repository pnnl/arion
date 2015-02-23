/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class YansWifiChannelHelper extends AbstractNs3Object {
	
	/**
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
