/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * This class represents a CSMA channel connecting CsmaNetDevices.
 * 
 * @author happ546
 *
 */
public class CsmaChannel extends Channel {

	/**
	 * Creates a nameless CsmaChannel
	 */
	public CsmaChannel() {
		super();
	}

	/**
	 * Creates a CsmaChannel with the given name
	 * @param name
	 */
	public CsmaChannel(String name) {
		super();
		this.setName(name);
	}

	/**
	 * @param device the Pointer<CsmaNetDevice> to connect to this CsmaChannel
	 */
	public void attach(Pointer<CsmaNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}
	
}
