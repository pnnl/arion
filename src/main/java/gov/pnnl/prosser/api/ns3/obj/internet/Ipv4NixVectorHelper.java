/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is a specific type of IPV4 router.
 * Can be used by InternetStackHelper.
 * 
 * @author happ546
 *
 */
public class Ipv4NixVectorHelper extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4NixVectorHelper with the given name
	 * 
	 * @param name
	 */
	public Ipv4NixVectorHelper(String name) {
		this.setName(name);
	}

}
