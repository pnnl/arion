/**
 * 
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.AbstractNs3Object.AbstractBuilder;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer.Builder;

/**
 * @author happ546
 *
 */
public class Ns3Builder {
	
	public static NodeContainer.Builder nodeContainer() {
		return new NodeContainer.Builder();
	}

	public static AbstractBuilder<NodeContainer, Builder> pointToPointHelper() {
		// TODO Auto-generated method stub
		return null;
	}

}
