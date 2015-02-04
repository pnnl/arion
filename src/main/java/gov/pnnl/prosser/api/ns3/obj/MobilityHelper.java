/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class MobilityHelper extends AbstractNs3Object {

	private String model;

	/**
	 * 
	 * @param model the mobility model used by this MobilityHelper
	 */
	public void setMobilityModel(String model) {
		this.model = model;
		appendPrintObj(this.getName() + ".SetMobilityModel(\"" + model + "\");\n");
	}
	
	/**
	 * 
	 * @param nodes the NodeContainer to install the Mobility model on
	 */
	public void install(NodeContainer nodes) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	
}
