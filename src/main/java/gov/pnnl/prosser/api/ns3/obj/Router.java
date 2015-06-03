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
	
	private boolean ipStackInstalled, addressed, ready, pcap;
	
	/**
	 * Create a new Router with the given Name
	 * @param name
	 */
	public Router(String name) {
		
		setNameString(name);
		node = new Node(name);
		channels = new ArrayList<>();
		ipStackInstalled = false;
		addressed = false;
		
	}

	/**
	 * @return node
	 * 			the node attached to this Router
	 */
	public Node getNode() {
		return this.node;
	}

	/**
	 * @return devices
	 * 				the NetDeviceContainer of all devices on this Router
	 */
	public NetDeviceContainer getDevices() {
		return devices;
	}

	/**
	 * Sets the addressed flag to true
	 */
	public void setAddressed() {
		addressed = true;
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
				"_devices_" + getName() + "_" + channel.getName());
		
		if (channel.getType().equals(NetworkType.CSMA)) {
			
			CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + getName() + "_" + channel.getName());
			csmaHelper.install(getNode(), (CsmaChannel) channel, tempDev);

			devices.addDevices(tempDev);

			// This router is ready for IP stack install
			ready = true;

			if (pcap) {
				csmaHelper.enablePcap(this.getName() + "_PCAP", tempDev, 0);
			}

		} else if (channel.getType().equals(NetworkType.P2P)) {

			PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + getName() + "_" + channel.getName());

			if (((PointToPointChannel) channel).getRouterA() == null) {
			
				((PointToPointChannel) channel).setRouterA(this);

				// This router is not ready for IP stack install; needs another p2p router attached to channel
				ready = false;

				// Checks if routerA attached to P2PChannel is same as this router
			} else if (!((PointToPointChannel) channel).getRouterA().equals(this)) {
				
				p2pHelper.install(((PointToPointChannel) channel).getRouterA().getNode(),
						this.getNode(), (PointToPointChannel) channel, tempDev);

				// This router is ready for IP stack install
				ready = true;
				
			} else {
				System.out.println("This PointToPointChannel " + channel.getName() + " is already attached to" +
						"this router.  Router not added to channel.");
			}

			if (pcap) {
				p2pHelper.enablePcap(this.getName() + "_PCAP", tempDev, 0);
			}
						
		}
		
		if (!ipStackInstalled && ready) {

			// Create NodeContainer for InternetStackHelper.Install
			NodeContainer nc = new NodeContainer("nodeContainerStackHelper_" + getName());
			nc.addNode(getNode());

			if (channel.getType().equals(NetworkType.P2P)) {
				nc.addNode(((PointToPointChannel) channel).getRouterA().getNode());
			}
			
			// Install IP stack on Router
			InternetStackHelper stackHelper = new InternetStackHelper("internetStackHelper_" + getName());
			stackHelper.install(nc);

			ipStackInstalled = true;
			
		}
		
		devices.addDevices(tempDev);
	}
	
	/**
	 * Stores the given NetDeviceContainer on this Node
	 * @param devices
	 */
	private void addDevices(NetDeviceContainer devices) {
		this.devices.addDevices(devices);
	}

	/**
	 *
	 * @param b a boolean flag to set PCAP tracing on/off
	 */
	public void setPcap(boolean b) {
		this.pcap = b;
	}
}
