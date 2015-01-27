/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class takes the basic network parameters from the user and constructs all of the 
 * ns-3 backbone to make it work.
 * 
 * @author happ546
 *
 */
public class Ns3Network {
	
	/**
	 * 
	 * @author happ546
	 *
	 */
	public enum NetworkType {
		CSMA, IPV4, LTE, P2P, WIFI  //TODO ipv6? others?
	}
	
	private NetworkType type;
	private String addrBase, addrMask;
	private int numNodes;
	private List<Node> nodes;
	private List<AbstractProsserObject> gldObjects;
	
	public Ns3Network() {
		this.type = null;
		this.nodes = new ArrayList<Node>();
		this.gldObjects = new ArrayList<AbstractProsserObject>();
	}
	
	/**
	 * @return the type
	 */
	public NetworkType getType() {
		return type;
	}
	
	
	public void setType(NetworkType type) {
		this.type = type;
	}

	/**
	 * @return the addrBase
	 */
	public String getAddrBase() {
		return addrBase;
	}

	/**
	 * @param addrBase the addrBase to set
	 */
	public void setAddrBase(String addrBase) {
		this.addrBase = addrBase;
	}

	/**
	 * @return the addrMask
	 */
	public String getAddrMask() {
		return addrMask;
	}

	/**
	 * @param addrMask the addrMask to set
	 */
	public void setAddrMask(String addrMask) {
		this.addrMask = addrMask;
	}

	/**
	 * @return the numNodes
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * @param numNodes the numNodes to set
	 */
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * 
	 * @return nodes the list of all nodes in this network
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * TODO need to evaluate how to create network with more than 1 type (WiFi <--> P2P <--> LTE; etc)
	 */
	public void build() {
		switch (this.type) {
			case CSMA:
				break;
				
			case IPV4:
				Ipv4NixVectorHelper nixRouting = new Ipv4NixVectorHelper();
				nixRouting.setName("nixRouting");
				Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper();
				staticRouting.setName("staticRouting");
				Ipv4AddressHelper adresses = new Ipv4AddressHelper();
				adresses.setName("adresses");
				InternetStackHelper iHelper = new InternetStackHelper();
				iHelper.setName("iHelper");
				Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper();
				list.setName("list");
				list.add(staticRouting, 0);
				list.add(nixRouting, 10);
				
				iHelper.setRoutingHelper(list);
				
				//TODO More IPV4 setup required after construction of base (CSMA, WIFI, etc.) nodes
				
				break;
				
			case LTE:
				break;
				
			case P2P:
				NodeContainer p2pNodes = new NodeContainer();
				break;
				
			case WIFI:
				p2pNodes = new NodeContainer();
				NodeContainer wifiStaNodes = new NodeContainer();
				wifiStaNodes.setName("wifiStaNodes");
				wifiStaNodes.create(this.numNodes); //TODO get appropriate number of nodes for WiFi devices
				NodeContainer wifiApNodes = new NodeContainer();
				wifiApNodes.setName("wifiApNodes");
				if (p2pNodes != null) {
					wifiApNodes.addNode(p2pNodes, 0); //TODO get node from non-WiFi network to connect AP to
				}
				
				YansWifiChannelHelper channel = new YansWifiChannelHelper();
				channel.setName("channel");
				
				YansWifiPhyHelper phy = new YansWifiPhyHelper();
				phy.setName("phy");
				phy.defaultParams();
				phy.setChannel(channel);
				
				WifiHelper wifi = new WifiHelper();
				wifi.setName("wifi");
				wifi.defaultParams();
				wifi.setRemoteStationManager("ns3::AarfWifiManager");
				
				NqosWifiMacHelper mac = new NqosWifiMacHelper();
				mac.setName("mac");
				mac.defaultParams();
				
				Ssid ssid = new Ssid();
				ssid.setSsid("wifi1"); //TODO have user-entered param for this or auto generate?
				
				// MAC helper for station nodes
				mac.setType("ns3::StaWifiMac", ssid, false); //Type, ssid value, active probing
				
				NetDeviceContainer staDevices = new NetDeviceContainer();
				staDevices.setName("staDevices");
				
				wifi.install(phy, mac, wifiStaNodes, staDevices);
				
				// MAC helper for access-point node
				mac.setType("ns3::ApWifiMac", ssid);
				
				NetDeviceContainer apDevices = new NetDeviceContainer();
				staDevices.setName("apDevices");
				
				wifi.install(phy, mac, wifiApNodes, apDevices);
				
				// End of WiFi setup unless simulated Mobility (random movement of staNodes) is required (likely not for most GLD objects)
				
				break;
			
			default:
				break;
		}
	}	
	
}
