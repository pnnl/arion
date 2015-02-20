/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * TODO copied from ns-3 doc; plagiarism or ok since open source?
 * 
 * This class represents a simple CSMA channel that can be used when 
 * many nodes are connected to one wire. It uses a single busy flag 
 * to indicate if the channel is currently in use. It does not take 
 * into account the distances between stations or the speed of 
 * light to determine collisions.
 * 
 * @author happ546
 *
 */
public class CsmaChannel extends AbstractNs3Object {

	/**
	 * @param device the CsmaNetDevice to connect to this CsmaChannel
	 */
	public void attach(Pointer<CsmaNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}
	
}
