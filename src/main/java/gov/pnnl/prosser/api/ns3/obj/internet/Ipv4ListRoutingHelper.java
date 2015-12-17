/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * This class is used to help setup the routing tables for IP networking.
 * Used by InternetStackHelper.
 * 
 * @author happ546
 *
 */
public class Ipv4ListRoutingHelper extends AbstractNs3Object {
	
	/**
	 * Creates a new Ipv4ListRoutingHelper with the given name
	 * 
	 * @param name
	 */
	public Ipv4ListRoutingHelper(String name) {
		this.setName(name);
	}

	/**
	 * 
	 * @param staticRouting the Ipv4RoutingHelper to add to this Ipv4ListRoutingHelper
	 * @param priority the integer priority of the given Ipv4RoutingHelper
	 */
	public void add(AbstractNs3Object staticRouting, int priority) {
		appendPrintInfo(this.getName() + ".Add(" + staticRouting.getName() + ", " + priority + ");\n");
	}

}
