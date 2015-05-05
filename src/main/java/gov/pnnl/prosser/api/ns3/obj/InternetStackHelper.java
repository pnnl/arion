/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is used to help configure Net Devices to use Internet Protocols.
 * 
 * @author happ546
 *
 */
public class InternetStackHelper extends AbstractNs3Object {

	
	/**
	 * Creates a nameless InternetStackHelper
	 */
	public InternetStackHelper() {
	}
	
	/**
	 * @param name
	 */
	public InternetStackHelper(String name) {
		this.setName(name);
	}
	
	
	/**
	 * @param node
	 */
	public void install(Node node) {
		
		// Get time to avoid name conflicts in output ns-3 file
		long currentTime = System.currentTimeMillis();
		
		String nodeName = "nodePtr_" + currentTime;
		
		// Creates smart pointer for node
		String nodePointer = "Ptr<Node> " + nodeName + " = " + node.getName() + ";\n";
		
		appendPrintObj(nodePointer);
		appendPrintObj(this.getName() + ".Install(" + nodeName + ");\n");

	}

/*	*//**
	 * Installs an IP stack on the Net Devices attached to the given Node
	 * 
	 * @param node the Pointer&lt;Node&gt;
	 *//*
	public void install(Pointer<Node> node) {
		appendPrintObj(this.getName() + ".Install(" + node.getName() + ");\n");
	}
*/

	/**
	 * Installs an IP stack on the Net Devices attached to the Nodes in nodes
	 * 
	 * @param nodes the NodeContainer
	 */
	public void install(NodeContainer nodes) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}

	/**
	 * 
	 * @param list the Ipv4ListRoutingHelper to be this InternetStackHelper's routing helper
	 */
	public void setRoutingHelper(Ipv4ListRoutingHelper list) {
		appendPrintObj(this.getName() + ".SetRoutingHelper(" + list.getName() + ");\n");
	}
	
}
