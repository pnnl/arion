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
	
	public NetworkHelper getHelper(NetworkType type) {
		
		if (type.name().equalsIgnoreCase("csma")) {
			return new CsmaHelper();
		} else if (type.name().equalsIgnoreCase("lte")) {
			return new LteHelper();
		} else if (type.name().equalsIgnoreCase("p2p")) {
			return new PointToPointHelper();
		} else {
			return new WifiHelper();
		}

	}
	
	
	
}
