/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.ns3.module.Module;

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
	private List<Module> modules; // List of ns-3 Modules used in this network
	private List<Node> nodes;
	private List<AbstractProsserObject> gldObjects;
	private double startTime, stopTime;
	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 */
	public Ns3Network() {
		this.type = null;
		this.modules = new ArrayList<Module>();
		this.nodes = new ArrayList<Node>();
		this.setGldObjects(new ArrayList<AbstractProsserObject>());
	}
	
	/**
	 * @return the type
	 */
	public NetworkType getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type the NetworkType for this Ns3Network
	 */
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
	 * @return the list of Modules used by this network
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * 
	 * @param name of the Module to add to the Modules list
	 */
	private void addModule(String name) {
		modules.add(new Module(name));
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
	
	/**
	 * Sets the ns-3 simulator's time to start running (0.0 is typical)
	 * @param time in seconds
	 */
	public void setStartTime(double time) {
		this.startTime = time;
	}
	
	/**
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
	}

	/**
	 * 
	 * @param stack the InternetStackHelper used to set up the IP routing tables
	 */
	private void setupIp(InternetStackHelper stack) {
		Ipv4NixVectorHelper nixRouting = new Ipv4NixVectorHelper();
		nixRouting.setName("nixRouting");
		
		Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper();
		staticRouting.setName("staticRouting");
		
		Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper();
		list.setName("list");
		list.add(staticRouting, 0);
		list.add(nixRouting, 10);
		
		stack.setRoutingHelper(list);
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * TODO need to evaluate how to create network with more than 1 type (WiFi <--> CSMA <--> LTE; etc)
	 * @return 
	 */
	public List<AbstractNs3Object> build() {
		
		List<AbstractNs3Object> objects = new ArrayList<AbstractNs3Object>();
		
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		
		// For FNCS
		this.addModule("fncs");
		this.addModule("fncsapplication-helper");
		
		switch (this.type) {
				
			case CSMA:
				
				this.addModule("csma-module");
				this.addModule("nix-vector-routing-module");
				
				// IP setup
				Ipv4AddressHelper addresses = new Ipv4AddressHelper();
				addresses.setName("addresses");
				objects.add(addresses);
				InternetStackHelper stack = new InternetStackHelper();
				stack.setName("stack");
				setupIp(stack);
				objects.add(stack);
				
				// max of 20 nodes per NodeContainer to prevent poor performance, according to researchers
				int numConts = numNodes/20 + 1;
				
				List<NodeContainer> csmaNodeConts = new ArrayList<NodeContainer>();
				
				for (int i = 0; i < numConts; i++) {
					NodeContainer temp = new NodeContainer();
					temp.setName("csmaNodes_" + i);
					temp.create(20); // Create 20 Nodes in this NodeContainer
					
					csmaNodeConts.add(temp);
					objects.add(temp);
					
					// Add each Node from temp NodeContainer to Nodes global list of nodes
					for (int j = 0; j < 21; j++) {
						nodes.add(temp.getNode(j));
					}
				}
				
				//stack.install(markcon); install IP stack on Market Container
				
				//TODO get dataRate and delay from user
				String dataRate = "10Mbps";
				String delay = "3ms";
				
				CsmaHelper csmaHelper = new CsmaHelper();
				csmaHelper.setName("csmaHelper");
				csmaHelper.setChannelAttribute("DataRate", dataRate);
				csmaHelper.setChannelAttribute("Delay", delay);
				objects.add(csmaHelper);
				
				List<NetDeviceContainer> netDeviceConts = new ArrayList<NetDeviceContainer>();
				
				for (int i = 0; i < numConts; i++) {
					NodeContainer csmaNodes = csmaNodeConts.get(i);
					
					String ipBase = this.addrBase + i + ".0"; // Sets IP address base to use for devices in this NetDeviceContainer
					NetDeviceContainer temp = new NetDeviceContainer();
					temp.setName("netDevices_" + i);
					
					csmaHelper.install(csmaNodes, temp); // Install the CSMA devices & channel onto the temp NodeContainer
					stack.install(csmaNodes); // Install the IP stack protocols on the CSMA Nodes
					
					addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
					addresses.assign(temp);
					
					netDeviceConts.add(temp);
					objects.add(temp);
				}
				
				break;
				
			case LTE:
				
				this.addModule("lte-module");
				
				LteHelper lte = new LteHelper();
				
				List<NodeContainer> lteNodeContainers = new ArrayList<NodeContainer>();
				
				NodeContainer enbNodes = new NodeContainer();
				enbNodes.setName("enbNodes");
				
				int numUeNodes = 200; //TODO get real value of LTE user equipment nodes
				int numEnbNodes = numUeNodes / 50; //TODO get real value of LTE base (towers) nodes
				
				NetDeviceContainer enbDevices = new NetDeviceContainer();
				enbDevices.setName("enbDevices");
				enbNodes.create(numEnbNodes);
				lte.installEnbDevice(enbNodes, enbDevices); //TODO check other implementations of .install and model after those
				
				for (int i = 0; i < numEnbNodes; i++) {
					NodeContainer ueNodes = new NodeContainer();
					ueNodes.setName("ueNodes_" + i);
					ueNodes.create(numUeNodes);
					
					NetDeviceContainer temp = new NetDeviceContainer();
					enbDevices.setName("ueDevices_" + i);
					lte.installUeDevice(ueNodes, temp);
					lte.attach(temp, enbDevices, i);
					
					lteNodeContainers.add(ueNodes);
				}
				
				break;
				
			case P2P:
				
				this.addModule("point-to-point-module");
				
				NodeContainer p2pNodes = new NodeContainer();
				//TODO finish this
				
				// Add all Nodes from each NodeContainer to Nodes global list of nodes
				for (int i = 0; i < p2pNodes.getNumNodes(); i++) {
					nodes.add(p2pNodes.getNode(i));
				}
				
				break;
				
			case WIFI:
				
				this.addModule("wifi-module");
				
				NodeContainer wifiStaNodes = new NodeContainer();
				wifiStaNodes.setName("wifiStaNodes");
				wifiStaNodes.create(this.numNodes); //TODO get appropriate number of nodes for WiFi devices
				objects.add(wifiStaNodes);
				
				NodeContainer wifiApNodes = new NodeContainer();
				wifiApNodes.setName("wifiApNodes");
				wifiApNodes.create(this.numNodes/20 + 1); //TODO get appropriate # of AP nodes
/*				if (p2pNodes != null) {
					wifiApNodes.addNode(p2pNodes, 0); //TODO get node from non-WiFi network to connect AP to
				}*/
				objects.add(wifiApNodes);
				
				YansWifiChannelHelper channel = new YansWifiChannelHelper();
				channel.setName("channel");
				objects.add(channel);
				
				YansWifiPhyHelper phy = new YansWifiPhyHelper();
				phy.setName("phy");
				phy.defaultParams();
				phy.setChannel(channel);
				objects.add(phy);
				
				WifiHelper wifi = new WifiHelper();
				wifi.setName("wifi");
				wifi.defaultParams();
				wifi.setRemoteStationManager("ns3::AarfWifiManager");
				objects.add(wifi);
				
				NqosWifiMacHelper mac = new NqosWifiMacHelper();
				mac.setName("mac");
				mac.defaultParams();
				objects.add(mac);
				
				Ssid ssid = new Ssid();
				ssid.setSsid("wifi1"); //TODO have user-entered param for this or auto generate?
				objects.add(ssid);
				
				// MAC helper for station nodes
				mac.setType("ns3::StaWifiMac", ssid, false); //Type, ssid, active probing
				
				NetDeviceContainer staDevices = new NetDeviceContainer();
				staDevices.setName("staDevices");
				objects.add(staDevices);
				
				wifi.install(phy, mac, wifiStaNodes, staDevices);
				
				// MAC helper for access-point node
				mac.setType("ns3::ApWifiMac", ssid);
				
				NetDeviceContainer apDevices = new NetDeviceContainer();
				apDevices.setName("apDevices");
				objects.add(apDevices);
				
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
				
			default:
				
				break;

		}
		
		AbstractNs3Object ns3 = nodes.get(0);
		
		ns3.appendPrintObj("\n");
		// Output simulator start, stop, run, & destroy
		ns3.appendPrintObj("Simulator::Start(Seconds(" + this.startTime + "));\n");
		ns3.appendPrintObj("Simulator::Stop(Seconds(" + this.stopTime + "));\n");
        // This stuff doesn't seem to vary from sim to sim
		ns3.appendPrintObj("Simulator::Run();\n");
		ns3.appendPrintObj("Simulator::Destroy();\n");
		ns3.appendPrintObj("return 0;\n");
		
		return objects;
	}
	
}
