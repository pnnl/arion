/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * @author happ546
 *
 */
public class PointToPointChannel extends Channel {
	
	/**
	 * Creates a nameless PointToPointChannel
	 */
	public PointToPointChannel() {
	}
	
	/**
	 * @param name
	 */
	public PointToPointChannel(String name) {
		this.setName(name);
	}

	/**
	 * @param device the PointToPointNetDevice to connect to this PointToPointChannel
	 */
	public void attach(Pointer<PointToPointNetDevice> device) {
		appendPrintObj(this.getName() + ".Attach(" + device.getName() + ");\n");
	}

}
