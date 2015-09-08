/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.csma;

import gov.pnnl.prosser.api.ns3.obj.NetDevice;

/**
 * This class represents a CSMA net device.
 * 
 * @author happ546
 *
 */
public class CsmaNetDevice extends NetDevice {

	/**
	 * Creates a new CsmaNetDevice with the given name
	 * @param name
	 */
	public CsmaNetDevice(String name) {
		this.setName(name);
	}

}
