/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is a container for IPV4 interfaces.
 * 
 * @author happ546
 *
 */
public class Ipv4InterfaceContainer extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4InterfaceContainer with the given name
	 * 
	 * @param name
	 */
	public Ipv4InterfaceContainer(String name) {
		this.setName(name);
	}

}
