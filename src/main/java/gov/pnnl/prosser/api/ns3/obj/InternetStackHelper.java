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
	private NodeContainer nodes;
	private Ipv4ListRoutingHelper routingHelper;

	public void install(NodeContainer nodes) {
		this.nodes = nodes;
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}

	/**
	 * 
	 * @param list the Ipv4ListRoutingHelper to be this InternetStackHelper's routing helper
	 */
	public void setRoutingHelper(Ipv4ListRoutingHelper list) {
		this.routingHelper = list;
		appendPrintObj(this.getName() + ".SetRoutingHelper(" + list.getName() + ");\n");
	}
	
}
