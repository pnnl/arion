/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The ns-3 Node is the basic building block of the network simulation
 * Devices can be installed on them to provide various communication methods and 
 * applications can be installed on the devices to provide various communication abilities 
 * (for example: point-to-point, WiFi, Internet protocols, etc.)
 * 
 * @author happ546
 *
 */
public class Node extends AbstractNs3Object {

	/**
	 * Create a nameless Node.
	 */
	public Node() {
		super();
	}
	
	/**
	 * Creates a new, empty named Node.
	 * Creates a Ptr (smart pointer) for this Node, 
	 * used in helper methods.
	 * @param name
	 */
	public Node(String name) {
		this.setName(name);
		this.getAsPointer();
	}
	
}
