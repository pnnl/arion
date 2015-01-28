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
		CSMA, LTE, P2P, WIFI  //TODO ipv4? IPV4 currently assumed to be used. others?
	}
	
	private NetworkType type;
	private String addrBase, addrMask;
	private int numNodes;
	private List<Node> nodes;
	private List<AbstractProsserObject> gldObjects;
	
	public Ns3Network() {
		this.type = null;
		this.nodes = new ArrayList<Node>();
		this.setGldObjects(new ArrayList<AbstractProsserObject>());
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
	 * @return the list of gldObjects used in this simulator
	 */
	public List<AbstractProsserObject> getGldObjects() {
		return gldObjects;
	}

	/**
	 * @param gldObjects the list of GridLab-D objects to set
	 */
	public void setGldObjects(List<AbstractProsserObject> gldObjects) {
		this.gldObjects = gldObjects;
	}

	private void setupIp(InternetStackHelper iHelper) {
		Ipv4NixVectorHelper nixRouting = new Ipv4NixVectorHelper();
		nixRouting.setName("nixRouting");
		
		Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper();
		staticRouting.setName("staticRouting");
		
		Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper();
		list.setName("list");
		list.add(staticRouting, 0);
		list.add(nixRouting, 10);
		
		iHelper.setRoutingHelper(list);
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * TODO need to evaluate how to create network with more than 1 type (WiFi <--> P2P <--> LTE; etc)
	 */
	public void build() {
		switch (this.type) {
		
			default:
				
				break;
				
			case CSMA:
				
				// IP setup
				Ipv4AddressHelper addresses = new Ipv4AddressHelper();
				addresses.setName("addresses");
				InternetStackHelper iHelper = new InternetStackHelper();
				iHelper.setName("iHelper");
				setupIp(iHelper);
				
				// max of 20 nodes per NodeContainer to prevent poor performance, according to researchers
				int numConts = numNodes/20 + 1;
				
				List<NodeContainer> csmaNodeConts = new ArrayList<NodeContainer>();
				
				for (int i = 0; i < numConts; i++) {
					NodeContainer temp = new NodeContainer();
					temp.setName("csmaNodes_" + i);
					temp.create(21); // Create 21 Nodes in this NodeContainer
					csmaNodeConts.add(temp);
					
					// Add each Node from temp NodeContainer to Nodes global list of nodes
					for (int j = 0; j < 21; j++) {
						nodes.add(temp.getNode(j));
					}
				}
				
				//ihelper.install(markcon); install IP stack on Market Container
				
				//TODO get dataRate and delay from user
				String dataRate = "10Mbps";
				String delay = "3ms";
				
				CsmaHelper csmaHelper = new CsmaHelper();
				csmaHelper.setName("csmaHelper");
				csmaHelper.setChannelAttribute("DataRate", dataRate);
				csmaHelper.setChannelAttribute("Delay", delay);
				
				List<NetDeviceContainer> netDeviceConts = new ArrayList<NetDeviceContainer>();
				
				for (int i = 0; i < numConts; i++) {
					String ipBase = this.addrBase + i + ".0"; // Sets IP address base to use for devices in this NetDeviceContainer
					NetDeviceContainer temp = new NetDeviceContainer();
					temp.setName("netDevices_" + i);
					csmaHelper.install(csmaNodeConts.get(i), temp); // Install the CSMA devices & channel onto the temp NodeContainer
					
					addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
					addresses.assign(temp);
					
					netDeviceConts.add(temp);
				}
				
				break;
				
			case LTE:
				
				
				
				break;
				
			case P2P:
				
				NodeContainer p2pNodes = new NodeContainer();
				//TODO finish this
				
				// Add all Nodes from each NodeContainer to Nodes global list of nodes
				for (int i = 0; i < p2pNodes.getNumNodes(); i++) {
					nodes.add(p2pNodes.getNode(i));
				}
				
				break;
				
			case WIFI:
				
				p2pNodes = new NodeContainer(); //TODO need p2pNodes, csmaNodes, or other NC from previous section
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
				mac.setType("ns3::StaWifiMac", ssid, false); //Type, ssid, active probing
				
				NetDeviceContainer staDevices = new NetDeviceContainer();
				staDevices.setName("staDevices");
				
				wifi.install(phy, mac, wifiStaNodes, staDevices);
				
				// MAC helper for access-point node
				mac.setType("ns3::ApWifiMac", ssid);
				
				NetDeviceContainer apDevices = new NetDeviceContainer();
				staDevices.setName("apDevices");
				
				wifi.install(phy, mac, wifiApNodes, apDevices);
				
				// End of WiFi setup unless simulated Mobility (random movement of staNodes) is required (likely not for most GLD objects)
				
				// Add all Nodes from each NodeContainer to Nodes global list of nodes
				for (int i = 0; i < wifiStaNodes.getNumNodes(); i++) {
					nodes.add(wifiStaNodes.getNode(i));
				}
				for (int i = 0; i < wifiApNodes.getNumNodes(); i++) {
					nodes.add(wifiApNodes.getNode(i));
				}				
				
				break;

		}
	}	
	
}
