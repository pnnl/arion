/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

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
		this.setType(NetworkType.CSMA);
	}

	/**
	 * Creates a CsmaChannel with the given name.
	 * Creates a Ptr (smart pointer) for this CsmaChannel, 
	 * to be used in helper methods.
	 * @param name
	 */
	public CsmaChannel(String name) {
		this();
		this.setName(name);
		this.getAsPointer();
	}

	/**
	 * @param device the Pointer&lt;CsmaNetDevice&gt; to connect to this CsmaChannel
	 */
	public void attach(Pointer<CsmaNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}
	
}
