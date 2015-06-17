/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * 
 * 
 * @author happ546
 *
 */
public class PointToPointChannel extends Channel {
	
	/**
	 * Routers this Channel connects
	 */
	private Router routerA, routerB;
	
	/**
	 * Creates a nameless PointToPointChannel
	 */
	public PointToPointChannel() {
		super();
		routerA = null;
		routerB = null;
		this.setType(NetworkType.P2P);
	}
	
	/**
	 * Creates a PointToPointChannel with the given name.
	 * Creates a Ptr (smart pointer) for this channel, used in 
	 * helper methods.
	 * @param name
	 */
	public PointToPointChannel(String name) {
		this();
		this.setName(name);
		this.getAsPointer();
	}
	
	/**
	 * Returns one of the end point Routers of this PointToPointChannel
	 * 
	 * @return routerA
	 */
	public Router getRouterA() {
		return routerA;
	}
	
	/**
	 * @param router one of the end point Routers for this p2p channel
	 */
	public void setRouterA(Router router) {
		routerA = router;
		routerA.setNameString(router.getName());
	}

	/**
	 * Returns one of the end point Routers of this PointToPointChannel
	 * 
	 * @return routerB
	 */
	public Router getRouterB() {
		return routerB;
	}

	/**
	 * @param router one of the end point Routers for this p2p channel
	 */
	public void setNodeB(Router router) {
		routerB = router;
		routerB.setNameString(router.getName());
	}

	/**
	 * @param device the PointToPointNetDevice to connect to this PointToPointChannel
	 */
	public void attach(PointToPointNetDevice device) {
		
		// Get time to avoid name conflicts in output ns-3 file
		long currentTime = System.currentTimeMillis();
		
		String pointer = "Ptr<PointToPointNetDevice> pointToPointNetDevicePointer_" 
				+ currentTime + " = " + device.getName() + ";\n";
		
		appendPrintInfo(this.getName() + ".Attach(" + pointer + ");\n");
	}

	/**
	 * @return true if this PointToPointChannel has 2 end point Routers, 
	 * false otherwise
	 */
	public boolean hasTwoNodes() {
		if (getRouterA() != null && getRouterB() != null) {
			return true;
		}
		return false;
	}

}
