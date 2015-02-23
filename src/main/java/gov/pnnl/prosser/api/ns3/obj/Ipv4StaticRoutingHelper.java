/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4StaticRoutingHelper extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public Ipv4StaticRoutingHelper(String name) {
		this.setName(name);
	}

	/**
	 * Add a default route to the static routing table.
	 * This method tells the routing system what to do in the case 
	 * where a specific route to a destination is not found. 
	 * The system forwards packets to the specified node in the hope 
	 * that it knows better how to route the packet.
	 * 
	 * @param addr the Ipv4Address to use
	 * @param interfaceIndex 
	 */
	public void setDefaultRoute(Ipv4Address addr, int interfaceIndex) {
		appendPrintObj(this.getName() + 
				".SetDefaultRoute(" + addr.getName() + ", " + interfaceIndex);
	}
	
}
