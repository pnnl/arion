/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * @author happ546
 *
 */
public class NetworkHelper extends AbstractNs3Object {
	
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
