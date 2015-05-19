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
		InternetStackHelper stackHelper = new InternetStackHelper(getName() + "_internetStackHelper");
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
		
		if (devices == null) {
			devices = new NetDeviceContainer(channel.getType().toString() + 
					"_devices_" + getName());
		}
		
		NetDeviceContainer tempDev = new NetDeviceContainer("TEMP_" + channel.getType().toString() + 
				"_devices_" + getName());
		
		if (channel.getType().equals(NetworkType.CSMA)) {
			
			CsmaHelper csmaHelper = new CsmaHelper("csmaHelper");
			csmaHelper.setChannelAttribute("Delay", channel.getDelay());
			csmaHelper.setChannelAttribute("DataRate", channel.getDataRate());
			csmaHelper.install(getNode(), (CsmaChannel) channel, tempDev);
			
			devices.addDevices(tempDev);
			
		} else if (channel.getType().equals(NetworkType.P2P)) {
			
			if (!((PointToPointChannel) channel).hasTwoNodes()) {
			
				((PointToPointChannel) channel).setNodeB(getNode());

				// TODO should we check here if router node being connected to channel
				// 		is same as nodeA?
			} else {
				
				PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
				p2pHelper.setChannelAttribute("Delay", channel.getDelay());
				p2pHelper.setDeviceAttribute("DataRate", channel.getDataRate());
				p2pHelper.install(((PointToPointChannel) channel).getNodeB(), 
						getNode(), (PointToPointChannel) channel, tempDev);
				
			}
			
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
