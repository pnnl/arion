/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * This class represents a CSMA channel connecting 2 CsmaNetDevices.
 * 
 * @author happ546
 *
 */
public class CsmaChannel extends Channel {

	/**
	 * Creates a nameless CsmaChannel
	 */
	public CsmaChannel() {
	}

	/**
	 * @param name
	 */
	public CsmaChannel(String name) {
		this.setName(name);
	}

	/**
	 * @param device the CsmaNetDevice to connect to this CsmaChannel
	 */
	public void attach(Pointer<CsmaNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}
	
}
