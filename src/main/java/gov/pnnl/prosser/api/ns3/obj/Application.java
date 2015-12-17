/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * The Application is a basic representation of network applications 
 * (e.g. UDP echo helpers) that send data over network components.
 * 
 * @author happ546
 *
 */
public class Application extends AbstractNs3Object {

	/**
	 * Creates a named Application
	 * 
	 * @param name
	 */
	public Application(String name) {
		this.setName(name);
	}

}
