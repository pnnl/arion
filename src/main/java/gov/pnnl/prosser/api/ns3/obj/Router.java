/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * @author happ546
 *
 */
public class Router extends AbstractNs3Object {
	
	private Node node;
	private List<Channel> channels;
	private NetDeviceContainer devices;
	
	/**
	 * Create a new Router with the given Name
	 * @param name
	 */
	public Router(String name) {
		
		setNameString(name);
		node = new Node(name);
		channels = new ArrayList<>();
		
		// REVIEW Assuming all routers will use IP
		// Install IP stack on Router
		InternetStackHelper stackHelper = new InternetStackHelper(getName() + "internetStackHelper");
		stackHelper.install(getNode());
		
	}
	
	private Node getNode() {
		return this.node;
	}

	/**
	 * Stores the given Channel on this Node and
	 * installs the protocols specified by the Channel type
	 * @param channel
	 */
	public void setChannel(Channel channel) {
		
		channels.add(channel);
		long time = (System.nanoTime() / 100 ) % 10;
		
		if (devices == null) {
			devices = new NetDeviceContainer(channel.getType().toString() + 
					"_devices_" + getName() + "_" + time);
		}
		
		NetDeviceContainer tempDev = new NetDeviceContainer("TEMP_" + channel.getType().toString() + 
				"_devices_" + getName() + "_" + time);
		
		if (channel.getType().equals(NetworkType.CSMA)) {
			
			CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + time);
			csmaHelper.install(getNode(), (CsmaChannel) channel, tempDev);
			
			devices.addDevices(tempDev);
			
		} else if (channel.getType().equals(NetworkType.P2P)) {
			
			PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + time);
			p2pHelper.install(getNode(), (PointToPointChannel) channel, tempDev);
			// TODO connect only one node to p2p channel? or delay ns-3 connection
			
			devices.addDevices(tempDev);
			
		} 
	}
	
	/**
	 * Stores the given NetDeviceContainer on this Node
	 * @param devices
	 */
	public void addDevices(NetDeviceContainer devices) {
		this.devices.addDevices(devices);
	}

}
