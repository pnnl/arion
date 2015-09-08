/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

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

		appendPrintInfo(this.getName() + ".Install(" + node.getName() + ");\n");

	}

	/**
	 * Installs an IP stack on the Net Devices attached to the Nodes in nodes
	 * 
	 * @param nodes the NodeContainer
	 */
	public void install(NodeContainer nodes) {
		appendPrintInfo(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}

	/**
	 * 
	 * @param list the Ipv4ListRoutingHelper to be this InternetStackHelper's routing helper
	 */
	public void setRoutingHelper(Ipv4ListRoutingHelper list) {
		appendPrintInfo(this.getName() + ".SetRoutingHelper(" + list.getName() + ");\n");
	}
	
}