/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * A base class for the specific network helpers (Wi-Fi, LTE, CSMA, etc.)
 * 
 * @author happ546
 *
 */
public class NetworkHelper extends AbstractNs3Object {
	
	/**
	 * 
	 * @param nodes the NodeContainer to install this p2p channel on
	 * @param destination the NetDeviceContainer to install the p2p nodes on
	 * Equivalent statement: destination = this.install(nodes);
	 */
	public void install(NodeContainer nodes, NetDeviceContainer destination) {
		appendPrintObj(destination.getName() + " = " + this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	
	/**
	 * @param type
	 * @return the NetworkHelper of the given NetworkType
	 */
	public NetworkHelper getHelper(NetworkType type) {
		
		if (type.name().equalsIgnoreCase("csma")) {
			return new CsmaHelper(this.getName());
		} else if (type.name().equalsIgnoreCase("lte")) {
			return new LteHelper(this.getName());
		} else if (type.name().equalsIgnoreCase("p2p")) {
			return new PointToPointHelper(this.getName());
		} else {
			return new WifiHelper(this.getName());
		}

	}
	
}
