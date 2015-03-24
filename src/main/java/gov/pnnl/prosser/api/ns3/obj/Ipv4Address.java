/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class represents an IPV4 address.
 * 
 * @author happ546
 *
 */
public class Ipv4Address extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4Address with the given name
	 * 
	 * @param name
	 */
	public Ipv4Address(String name) {
		this.setName(name);
	}

}
