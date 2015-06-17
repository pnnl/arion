/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is used to help setup IPV4 static routing
 * 
 * @author happ546
 *
 */
public class Ipv4StaticRoutingHelper extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4StaticRoutingHelper with the given name
	 * 
	 * @param name
	 */
	public Ipv4StaticRoutingHelper(String name) {
		this.setName(name);
	}

	/**
	 * Adds a default route to the static routing table
	 * 
	 * @param addr the Ipv4Address to use
	 * @param interfaceIndex 
	 */
	public void setDefaultRoute(Ipv4Address addr, int interfaceIndex) {
		appendPrintInfo(this.getName() +
				".SetDefaultRoute(" + addr.getName() + ", " + interfaceIndex);
	}
	
}
