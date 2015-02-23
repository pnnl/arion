/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class InternetStackHelper extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public InternetStackHelper(String name) {
		this.setName(name);
	}

	/**
	 * 
	 * @param nodes the NodeContainer to install the Internet protocol stack on
	 */
	public void install(NodeContainer nodes) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}

	/**
	 * 
	 * @param list the Ipv4ListRoutingHelper to be this InternetStackHelper's routing helper
	 */
	public void setRoutingHelper(Ipv4ListRoutingHelper list) {
		appendPrintObj(this.getName() + ".SetRoutingHelper(" + list.getName() + ");\n");
	}
	
}
