/**
 * 
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

/**
 * @author happ546
 *
 */
public interface IpAddress {
	
	public void assignAddr(String base, NodeContainer result);
	
}
