/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4ListRoutingHelper extends AbstractNs3Object {
	
	/**
	 * 
	 * @param staticRouting the Ipv4RoutingHelper to add to this Ipv4ListRoutingHelper
	 * @param priority the integer priority of the given Ipv4RoutingHelper
	 */
	public void add(AbstractNs3Object staticRouting, int priority) {
		appendPrintObj(this.getName() + ".Add(" + staticRouting.getName() + ", " + priority + ");\n");
	}

}
