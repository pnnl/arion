/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NetDeviceContainer extends AbstractNs3Object {
	
	private NodeContainer nodes;

	/**
	 * 
	 * @param sourceNodes the NodeContainer to store in this NetDeviceContainer's NodeContainer nodes
	 */
	public void setNodes(NodeContainer sourceNodes) {
		this.nodes = sourceNodes;
	}
	
}
