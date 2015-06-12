/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.Vector;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.enums.Qci;
import gov.pnnl.prosser.api.ns3.module.*;

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
	
	private NetworkType backboneType, auctionType;
	private String addrBase, addrMask;
	private String gldNodePrefix;
	private String backboneDataRate, backboneDelay;
	private int numBackboneNodes, numChannels;
	private double stopTime;
	private NodeContainer backboneNodes, allNodes;
	private InternetStackHelper iStackHelper;
	private List<Module> modules;
	private List<AuctionObject> auctions;
	private List<Controller> controllers;
	private List<AbstractNs3Object> ns3Objects;
	private List<Channel> channels, houseChannels;
	private Vector<String> allNames;

	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 * with default values for the parameters
	 */
	public Ns3Network() {
		this.backboneType = null;
		this.auctionType = null;
		this.addrBase = "10.1.1.0";
		this.addrMask = "255.255.255.0";
		this.stopTime = Double.MAX_VALUE;
		this.backboneNodes = new NodeContainer();
		this.allNodes = new NodeContainer();
		this.iStackHelper = new InternetStackHelper();
		this.modules = new ArrayList<>();
		this.auctions = new ArrayList<>();
		this.controllers = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
		this.channels = new ArrayList<>();
		this.houseChannels = new ArrayList<>();
	}
	
	/**
	 * @return the NetworkType of the Backbone infrastructure
	 */
	public NetworkType getBackboneType() {
		return backboneType;
	}
	
	/**
	 * 
	 * @param backboneType the NetworkType to set for the Backbone infrastructure
	 */
	public void setBackboneType(NetworkType backboneType) {
		this.backboneType = backboneType;
	}
	
	/**
	 * @return the NetworkType of the Auction market
	 */
	public NetworkType getAuctionType() {
		return auctionType;
	}
	
	/**
	 * 
	 * @param auctionType the NetworkType to set for the Auction market
	 */
	//TODO: Not sure this type of information should be stored here; Need convincing to allow simulator specific variables in NS3 objects
	public void setAuctionType(NetworkType auctionType) {
		this.auctionType = auctionType;
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
	 * @return the gldNodePrefix
	 */
	public String getGldNodePrefix() {
		return gldNodePrefix;
	}

	/**
	 * @param gldNodePrefix the gldNodePrefix to set
	 */
	public void setGldNodePrefix(String gldNodePrefix) {
		this.gldNodePrefix = gldNodePrefix;
	}
	
	/**
	 * @return the backboneDataRate
	 */
	public String getBackboneDataRate() {
		return backboneDataRate;
	}

	/**
	 * @param backboneDataRate the backboneDataRate to set
	 */
	public void setBackboneDataRate(String backboneDataRate) {
		this.backboneDataRate = backboneDataRate;
	}

	/**
	 * @return the backboneDelay
	 */
	public String getBackboneDelay() {
		return backboneDelay;
	}

	/**
	 * @param backboneDelay the backboneDelay to set
	 */
	public void setBackboneDelay(String backboneDelay) {
		this.backboneDelay = backboneDelay;
	}
	
	/**
	 * @return the list of Channels
	 */
	public List<Channel> getChannels() {
		return this.channels;
	}

	/**
	 *
	 * @return a List of house Channels for this Ns3Network
	 */
	public List<Channel> getHouseChannels() {
		return houseChannels;
	}

	/**
	 * @return the numChannels
	 */
	public int getNumChannels() {
		return numChannels;
	}

	/**
	 * @return the list of Modules used by this network
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * 
	 * @param mod name of the Module to add to the Modules list
	 */
	private void addModule(Module mod) {
		modules.add(mod);
	}	

	/**
	 * 
	 * @return allNodes the list of all allNodes in this network
	 */
	public NodeContainer getAllNodes() {
		return allNodes;
	}
	
	/**
	 * @param node the Node to add to the list of Nodes for this network
	 */
	public void addNode(Node node) {
		this.allNodes.addNode(node);
	}
	
	/**
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
	}

	/**
	 * @return the backboneNodes
	 */
	public NodeContainer getBackboneNodes() {
		return backboneNodes;
	}

	/**
	 * @param node the backboneNode to add
	 */
	public void addBackboneNode(Node node) {
		this.backboneNodes.addNode(node);
	}

	/**
	 * @return the AuctionObjects
	 */
	public List<AuctionObject> getAuctions() {
		return auctions;
	}

	/**
	 * @param auctions the List of AuctionObjects to set
	 */
	public void setAuctions(List<AuctionObject> auctions) {
		this.auctions = auctions;
	}

	/**
	 * @return the controllers
	 */
	public List<Controller> getControllers() {
		return controllers;
	}

	/**
	 * @param controllers the List of Controllers to set
	 */
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
		int numBackboneNodes = this.controllers.size() / 20;
		if (this.controllers.size() % 20 != 0) {
			numBackboneNodes++;
		}
		this.setNumBackboneNodes(numBackboneNodes);
	}

	/**
	 * @return the number of backbone Nodes
	 */
	public int getNumBackboneNodes() {
		return this.numBackboneNodes;
	}
	
	/**
	 * @param numBackboneNodes the number of backbone Nodes to set
	 */
	public void setNumBackboneNodes(int numBackboneNodes) {
		this.numBackboneNodes = numBackboneNodes;
	}
	
	/**
	 * @param chan the Channel to add to this Ns3Network
	 */
	public void addChannel(Channel chan) {
		this.channels.add(chan);
	}

	/**
	 * Adds a GLD house channel to the network
	 * @param houseChannel the house Channel to add to this
	 *                     Ns3Network
	 */
	public void addHouseChannel(CsmaChannel houseChannel) {
		this.houseChannels.add(houseChannel);
		this.channels.add(houseChannel);
	}
	
	/**
	 * @param i
	 * @return the Channel at index i
	 */
	private Channel getChannel(int i) {
		return this.channels.get(i);
	}
	
	/**
	 * @return the allNames
	 */
	public Vector<String> getAllNames() {
		return allNames;
	}

	/**
	 * @param name the name to add to the vector (list) of allNames for FNCSApplication
	 */
	public void addName(String name) {
		this.allNames.pushBack(name);
	}

	/**
	 * Connect the Controllers to the appropriate Channels
	 * TODO first channel is for Auction; any beyond that have up to 20 controllers on them
	 */
	public void connectControllerChannels() {
		
		// TODO debugging
		String vectors = "std::vector<NetDeviceContainer> csmaDeviceVector;\n";
		
		int numChannels = this.channels.size();
		
		ns3Objects.add(iStackHelper);
		Ipv4AddressHelper ipv4AddrHelper = new Ipv4AddressHelper("ipv4AddrHelper");
		
		// 0th channel is reserved for Auction
		for (int i = 1; i < numChannels; i++) {
			
			Channel channel = getChannel(i);
			
//			int ipThird = (i == 1) ? (i % 254) : (i % 254 + 1);
//			// Sets IP address base to use for devices in this NetDeviceContainer
//			String ipFirstTwo = (i / 64832 + 192) + "." + (i / 254 + 1) + ".";
//			String ipBase = ipFirstTwo + ipThird + ".0";
			
			String ipBase = channel.getAddressBase();
			
			// Set the base address and subnet mask for the IPv4 addresses
			ipv4AddrHelper.setBase(ipBase, "255.255.255.0");
			
			List<Controller> controllers = channel.getControllers();
			// TODO structure methods in build() so that backbone network & channels are created first
			// 	then given back to me after GLD stuff adds controllers to the channels
			
			if (channel.getClass().getSimpleName().equalsIgnoreCase("csmachannel")) {
//				Pointer<CsmaChannel> chanPtr  = new Pointer<CsmaChannel>("chanPtr_" + i, new CsmaChannel());
//				chanPtr.assign(channel.getPointer());
				
				CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + i);
				NodeContainer csmaNodes = new NodeContainer("csmaNodes_" + i);
				NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_" + i);
								
				// Create new CSMA Node for each Controller
				for (int j = 0; j < controllers.size(); j++) {
					
					Controller controller = controllers.get(j);					
			    	// Adds the name of the Controller to the allNames Vector
			    	allNames.pushBack(controller);
			    	
			    	Pointer<Node> csmaNodePtr = new Pointer<Node>("csmaControllerNodePtr_" + i + "_" + j);
			    	csmaNodePtr.createObject(new Node());

					// Add Node to NodeContainer for IP stuff later
					//csmaNodes.addNode(csmaNodePtr);
					


				}

				// Adds csmaNodes to global NodeContainer for FNCSApplicationHelper
				allNodes.addNodeContainer(csmaNodes);
								
				// Install CSMA protocol stack on csmaNodes & connect to ith Channel
				csmaHelper.install(csmaNodes, (CsmaChannel) channel, csmaDevices);
				
				// TODO debugging
				if (i == 1) {
					csmaHelper.enablePcapAll("csmaControllerDevice");
				}
								
				// Sets up IP routing tables, installs IP stack on allNodes, and assigns IP addresses
				//setupIp(ipv4AddrHelper, csmaNodes, csmaDevices);
				iStackHelper.install(csmaNodes);
				ipv4AddrHelper.assign(csmaDevices);
				
				// TODO debugging					
			    //String stuff = "\tcsmaDeviceVector.push_back(csmaDevices_" + i + ");\n";
			    //csmaHelper.appendPrintObj(stuff);
				
			} else if (channel.getClass().getSimpleName().equalsIgnoreCase("pointtopointchannel")) {
				Pointer<PointToPointChannel> chanPtr  = new Pointer<PointToPointChannel>("chanPtr_" + i);
				chanPtr.encapsulate(channel);
				
				PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + i);
				// P2PHelper doesn't take Channel param, so need to set attributes manually
				p2pHelper.setChannelAttribute("DataRate", channel.getAttribute("DataRate"));
				p2pHelper.setDeviceAttribute("Delay", channel.getAttribute("Delay"));
				
				NodeContainer p2pNodes = new NodeContainer("p2pNodes_" + i);
				NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_" + i);
				
				Pointer<Node> p2pNodePtr = new Pointer<Node>("p2pNode_" + i);
				
				for (int j = 0; j < controllers.size(); j++) {
					Node node = new Node("node_" + i + "_" + j);
					
					Controller controller = controllers.get(j);
					
					// Wrap node in a Pointer
					p2pNodePtr.encapsulate(node);

					// Add Node to NodeContainer for IP stuff later
					//p2pNodes.addNode(p2pNodePtr);
					
			    	// Adds the name of the Controller to the allNames Vector
			    	allNames.pushBack(controller);
			    	
				}
				
				// Adds p2pNodes to NodeContainer of all Nodes in this network
		    	allNodes.addNodeContainer(p2pNodes);
				
				// Install P2P protocol stack on p2pNodes
				p2pHelper.install(p2pNodes, p2pDevices);
				
				// Sets up IP routing tables, installs IP stack on allNodes, and assigns IP addresses
				setupIp(ipv4AddrHelper, p2pNodes, p2pDevices);
				
			} else if (channel.getClass().getSimpleName().equalsIgnoreCase("wifichannel_" + i)) {
				Pointer<YansWifiChannel> chanPtr  = new Pointer<YansWifiChannel>("chanPtr_" + i);
				chanPtr.encapsulate(channel);
				
				WifiHelper wifiHelper = new WifiHelper("wifiHelper_" + i);
				NodeContainer wifiNodes = new NodeContainer("wifiNodes_" + i);
				NetDeviceContainer wifiDevices = new NetDeviceContainer("wifiDevices_" + i);
				
				YansWifiPhyHelper phy = new YansWifiPhyHelper("phy_" + i);
				NqosWifiMacHelper mac = new NqosWifiMacHelper("mac");
				
				Pointer<Node> wifiNodePtr = new Pointer<Node>("wifiNode_" + i);
				//wifiNode.encapsulate(node);

				// Add Node to NodeContainer for IP stuff later
				//wifiNodes.addNode(wifiNodePtr);
				
				// TODO add other allNodes created to allNames Vector
				allNames.pushBack(wifiNodePtr);
				
				// Adds wifiNodes to global NodeContainer for FncsApplicationHelper.Install
				allNodes.addNodeContainer(wifiNodes);
				
				// Configure WiFi physical link and install protocol stack on wifiNodes
				wifiHelper.install(phy, mac, wifiNodes, wifiDevices);
				
				// Sets up IP routing tables, installs IP stack on allNodes, and assigns IP addresses
				setupIp(ipv4AddrHelper, wifiNodes, wifiDevices);
			}
		}
		
//		 TODO debugging
		
/*		AbstractNs3Object ns3 = ns3Objects.get(0);
		
		String stuff = "\n   Address dest;\n" +
		  "   std::string protocol;\n" +
		 "   if (false)\n" +
		  "   {\n" +
		 "    dest = InetSocketAddress(staInterfaceVector.at(0).GetAddress(1), 1025);\n" +
		  "    protocol = \"ns3::UdpSocketFactory\";\n" +
		  "   }\n" +
		 "   else\n" +
		  "   {\n" +
		   "   PacketSocketAddress tmp;\n" +
		   "   tmp.SetSingleDevice (staDeviceVector[0].Get(0)->GetIfIndex ());\n" +
		   "   tmp.SetPhysicalAddress (staDeviceVector[0].Get(0)->GetAddress ());\n" +
		   "   tmp.SetProtocol (0x807);\n" +
		   "   dest = tmp;\n" +
		   "   protocol = \"ns3::PacketSocketFactory\";\n" +
		   "   }\n" + 

		  "   OnOffHelper onoff = OnOffHelper (protocol, dest);\n" +
		  "   onoff.SetConstantRate (DataRate (\"500kb/s\"));\n" +
		  "   ApplicationContainer apps = onoff.Install (csmaNodeVector[0].Get (0));\n" +
		  "   apps.Start (Seconds (0.5));\n" +
		  "   apps.Stop (Seconds (3.0));\n" +
		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", csmaDeviceVector[0]);\n" +

		  "   if (true)\n" +
		    "   {\n" +
		      "   AsciiTraceHelper ascii;\n" +
		      "   MobilityHelper::EnableAsciiAll (ascii.CreateFileStream (\"wifi-wired-bridging.mob\"));\n" +
		    "   }\n";
		
		ns3.appendPrintObj(stuff + "\n");*/
		
	}
	
	/**
	 * Connect the Auctions to the Auction Channel
	 * @return a map of Auction name to Controller prefix
	 */
	public StringMap<String, String> connectAuctionChannels() {
		// Call after controllers connected to network
		// Create node for each auction
		Channel auctionChannel = channels.get(0);
		List<AuctionObject> auctions = auctionChannel.getAuctions();
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		NodeContainer auctionNodes = new NodeContainer("auctionNodes");
		NetDeviceContainer auctionDevices = new NetDeviceContainer("auctionDevices");
		
		for (int i = 0; i < auctions.size(); i++) {
			AuctionObject auction = auctions.get(i);
			
			Node node = new Node("auctionNode_" + i);
//			Pointer<Node> nodePtr = new Pointer<Node>("auctionNodePtr_" + i);
//			nodePtr.createObject(node);
					    
		    // Adds node to auctionNodes for IP stack install
		    auctionNodes.addNode(node);
		    
		    // Adds node to global list of allNodes
		    allNodes.addNode(node);
		    
			// IP setup
			Ipv4AddressHelper addresses = new Ipv4AddressHelper("auctionAddresses");
			addresses.setBase(auctionChannel.getAddressBase(), "255.255.255.0");
		    
		    if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("csmachannel")) {
		    	
		    	// Wraps auctionChannel in a CsmaChannel Pointer
//		    	Pointer<CsmaChannel> csmaChannelPtr = new Pointer<CsmaChannel>("csmaChannelPtr_" + i);
//		    	csmaChannelPtr.encapsulate(auctionChannel);
		    	
		    	// Installs CSMA stack and channel on Node
		    	CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + i);
		    	NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_" + i);
		    	csmaHelper.install(node, (CsmaChannel) auctionChannel, csmaDevices);
		    	auctionDevices.addDevices(csmaDevices);
		    	
				// TODO pcap tracing
				csmaHelper.enablePcapAll("csmaChannelDevices");

		    } else if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("pointtopointchannel")) {
		    	
		    	Pointer<PointToPointChannel> p2pChannelPtr = new Pointer<PointToPointChannel>("p2pChannelPtr_" + i);
		    	final PointToPointChannel auctionChannelP2p = (PointToPointChannel) auctionChannel;
		    	p2pChannelPtr.createObject(auctionChannelP2p);
		    	
		    	PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + i);
		    	p2pHelper.setDeviceAttribute("DataRate", auctionChannelP2p.getAttribute("DataRate"));
		    	p2pHelper.setChannelAttribute("Delay", auctionChannelP2p.getAttribute("Delay"));
		    	NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_" + i);
		    	
		    	// Adds the node as second node for p2p auctionChannel
		    	//auctionChannelP2p.setNodeB(node);
		    	
		    	// Adds the p2p channel's other node to auctionNodes for IP stack install
		    	//auctionNodes.addNode(auctionChannelP2p.getNodeA());
		    			    	
		    	//p2pHelper.install(auctionChannelP2p.getNodeA(),
		    	//		auctionChannelP2p.getNodeB(),
		    	//		auctionChannelP2p, p2pDevices);
		    	
		    	auctionDevices.addDevices(p2pDevices);
		    	
				// TODO pcap tracing
		    	p2pHelper.enablePcapAll("p2pChannelDevices");
		    	
		    } else if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("yanswifichannel")) {
		    	// TODO WiFi auction
		    }
		    
		    // Install the IP stack on the auctioNodes
		    iStackHelper.install(auctionNodes);
		    
		    // Assigns IPv4 address to devices
	    	addresses.assign(auctionDevices);
		    
		    // Adds Auction name to vector of allNames
		    allNames.pushBack(auction);
			// Maps the Auction NetworkInterfaceName to the GldNodePrefix
			marketToControllerMap.put(auction.getNetworkInterfaceName(), auction.getFncsControllerPrefix());
		}
		
		
		return marketToControllerMap;
	}

	/**
	 * 
	 * @param addr the Ipv4AddressHelper
	 * @param nodes the NodeContainer to enable for IP communication
	 * @param devices the NetDeviceContainer to hold the IP-enabled devices
	 */
	private void setupIp(Ipv4AddressHelper addr, NodeContainer nodes, 
						NetDeviceContainer devices) {
				
		// Install the IP stack protocols on the allNodes
		iStackHelper.install(nodes);
		
		// Assign IPv4 addresses to devices
		addr.assign(devices);
	}

	/**
	 * Sets up static routing on the global InternetStackHelper
	 */
	private void setupInternetStackAndRouting() {
		
		Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper("staticRoutingHelper");
		
		Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper("listRoutingHelper");
		list.add(staticRouting, 0);
		
		iStackHelper.setRoutingHelper(list);

	}
	
	/**
	 * @param latency
	 * @return objects a List of all AbstractNs3Objects created for this WiFi network
	 */
	public List<AbstractNs3Object> createWifi(String latency) {
		
		// Add the necessary modules for WiFi
		this.addModule(new Mobility());
		this.addModule(new Wifi());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new Internet());
		this.addModule(new Bridge());
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house allNodes
		NodeContainer gldNodes = new NodeContainer("gldNodes");
		
		// Setup the Internet protocols
		Ipv4InterfaceContainer apInterfaces = new Ipv4InterfaceContainer("apInterfaces");
		ns3Objects.add(apInterfaces);
		Ipv4InterfaceContainer staInterfaces = new Ipv4InterfaceContainer("staInterfaces");
		ns3Objects.add(staInterfaces);
		InternetStackHelper stack = new InternetStackHelper("stack");
		ns3Objects.add(stack);
		Ipv4AddressHelper addressHelper = new Ipv4AddressHelper("addressHelper");
		ns3Objects.add(addressHelper);
		
		// Generic helper for installing the selected network protocol on the backbone Nodes
		NetworkHelper backboneHelper = new NetworkHelper();		
		
		YansWifiPhyHelper wifiPhy = new YansWifiPhyHelper("wifiPhy");
		wifiPhy.defaultParams();
		wifiPhy.setPcapDataLinkType("YansWifiPhyHelper::DLT_IEEE802_11_RADIO");
		ns3Objects.add(wifiPhy);
		
		// TODO make these as actual Vector objects if vectors needed for more than testing
//		String vectors = "std::vector<NodeContainer> staNodeVector;\n" +
//						  "std::vector<NetDeviceContainer> staDeviceVector;\n" +
//						  "std::vector<NetDeviceContainer> apDeviceVector;\n" +
//						  "std::vector<Ipv4InterfaceContainer> staInterfaceVector;\n" +
//						  "std::vector<Ipv4InterfaceContainer> apInterfaceVector;\n";
		
//		stack.appendPrintObj(vectors);
		
		// Used by MobilityHelper's positionAllocator and setMobilityModel
		double wifiX = 0.0;
		
		// Create p2p channel for Auction
		// TODO generalize this to allow CSMA
		PointToPointChannel auctionChannel = new PointToPointChannel("auctionChannel");
		auctionChannel.setDataRate("1Gbps");
		auctionChannel.setDelay("500ns");
		addChannel(auctionChannel);
		
		final int numChannels = getNumChannels();
		
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		
		// Create backbone router
		NodeContainer backboneRouter = new NodeContainer("backboneRouter");
		backboneRouter.create(1);
		// NodeContainer for AP allNodes
		NodeContainer apNodes = new NodeContainer("apNodes");
		apNodes.create(numChannels);
		
		WifiHelper wifiHelper = new WifiHelper("wifiHelper");
		wifiHelper.defaultParams();
		
		NqosWifiMacHelper wifiMacHelper = new NqosWifiMacHelper("wifiMacHelper");
		wifiMacHelper.defaultParams();
		
		YansWifiChannelHelper wifiChannelHelper = new YansWifiChannelHelper("wifiChannelHelper");
		wifiChannelHelper.defaultParams();
		wifiPhy.setChannel(wifiChannelHelper.create());
		
		MobilityHelper mobility = new MobilityHelper("mobilityHelper");

		
		// TODO figure out how to implement own wifihelper.install method to be able to get specific channels out of wifi
		
		for (int i = 1; i < numChannels; i++) {
			
			
				String ssidBase = "wifi_" + i;
				Ssid ssid = new Ssid("ssid_" + i);
				ssid.setSsid(ssidBase);
				
				NetDeviceContainer apDevs = new NetDeviceContainer("apDev_" + i);
				ns3Objects.add(apDevs);
				
				Ipv4InterfaceContainer staInterface = new Ipv4InterfaceContainer("staInterface_" + i);
				ns3Objects.add(staInterface);
				Ipv4InterfaceContainer apInterface = new Ipv4InterfaceContainer("apInterface_" + i);
				ns3Objects.add(apInterface);
				
			    mobility.setPositionAllocator("ns3::GridPositionAllocator",
			    								wifiX, 0.0, 5.0, 5.0, 1,
			                                    "RowFirst");
			    // Setup the AP
			    mobility.setMobilityModel("ns3::ConstantPositionMobilityModel");
			    mobility.install(apNodes, i);
			    wifiMacHelper.setType("ns3::ApWifiMac", ssid);
			    
			    // Installs a WiFi device on the ith backbone Node to make the AP WiFi device
			    wifiHelper.install(wifiPhy, wifiMacHelper, apNodes, i, apDevs);
			   
			    
			    // TODO use this for sta node setup
//			    // Setup the Mobility model
//			    mobility.setMobilityModel("ns3::RandomWalk2dMobilityModel",
//			                               "Time", "2s", 
//			                               "ns3::ConstantRandomVariable[Constant=1.0]",
//			                               "RectangleValue(Rectangle(" + 
//			                               wifiX + ", " + wifiX + " + 5.0, 0.0, (" 
//			                               + numStaNodesPerApNode + " + 1) * 5.0))");
			    
			    // Saves everything in containers.
			    String stuff = "\n\tstaNodeVector.push_back(staNodes_" + i + ");\n" +
					    "\tapDeviceVector.push_back(apDev_" + i + ");\n" +
					    "\tapInterfaceVector.push_back(apInterface_" + i + ");\n" +
					    "\tstaDeviceVector.push_back(staDev_" + i + ");\n" +
					    "\tstaInterfaceVector.push_back(staInterface_" + i + ");\n";
			    
			    addressHelper.appendPrintObj(stuff);
	
			    wifiX += 20.0;
		}
			
		
//		AbstractNs3Object ns3 = ns3Objects.get(0);
//		
//		String stuff = "\n   Address dest;\n" +
//		  "   std::string protocol;\n" +
//		 "   if (false)\n" +
//		  "   {\n" +
//		 "    dest = InetSocketAddress(staInterfaceVector.at(0).GetAddress(1), 1025);\n" +
//		  "    protocol = \"ns3::UdpSocketFactory\";\n" +
//		  "   }\n" +
//		 "   else\n" +
//		  "   {\n" +
//		   "   PacketSocketAddress tmp;\n" +
//		   "   tmp.SetSingleDevice (staDeviceVector[0].Get(0)->GetIfIndex ());\n" +
//		   "   tmp.SetPhysicalAddress (staDeviceVector[0].Get(0)->GetAddress ());\n" +
//		   "   tmp.SetProtocol (0x807);\n" +
//		   "   dest = tmp;\n" +
//		   "   protocol = \"ns3::PacketSocketFactory\";\n" +
//		   "   }\n" + 
//
//		  "   OnOffHelper onoff = OnOffHelper (protocol, dest);\n" +
//		  "   onoff.SetConstantRate (DataRate (\"500kb/s\"));\n" +
//		  "   ApplicationContainer apps = onoff.Install (staNodeVector[0].Get (0));\n" +
//		  "   apps.Start (Seconds (0.5));\n" +
//		  "   apps.Stop (Seconds (3.0));\n" +
//		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", apDeviceVector[0]);\n" +
//
//		  "   if (true)\n" +
//		    "   {\n" +
//		      "   AsciiTraceHelper ascii;\n" +
//		      "   MobilityHelper::EnableAsciiAll (ascii.CreateFileStream (\"wifi-wired-bridging.mob\"));\n" +
//		    "   }\n";
		
//		ns3.appendPrintObj(stuff + "\n");
		
		return ns3Objects;
		
	}
	
	/**
	 * Creates the FNCS simulator for ns-3 to use
	 */
	public void setupFncsSimulator(final String marketNIPrefix) {

        Pointer<FncsSimulator> hb2 = new Pointer<>("hb2");

        // Setup FNCS simulator
		FncsSimulator fncsSim = new FncsSimulator("fncsSim");
		
		hb2.encapsulate(fncsSim);
		
		fncsSim.unref();
		fncsSim.setImplementation(hb2);
		
		// Add modules required for all simulations
		this.addModule(new Core());
		this.addModule(new Applications());
		this.addModule(new Mobility());
		this.addModule(new Wifi());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new Internet());
		this.addModule(new Bridge());
		this.addModule(new PointToPoint());
		this.addModule(new Fncs());
		this.addModule(new FncsApplication());

		// Instantiates global NodeContainer allNodes for use by FNCSApplicationHelper
		allNodes = new NodeContainer("allNodes");
		// Instantiates global Vector allNames for use by FNCSApplicationHelper
		allNames = new Vector<>("allNames", String.class);

		// Adds the market (auction) network interface prefix name to the list of
		// all names for the FNCSApplicationHelper
		allNames.addName(marketNIPrefix);

	}

	/**
	 * 
	 * @param nodeCont the Nodes to set an Auction and Controllers
	 * @param auction the AuctionObject to set on the first Node of nodeCont
	 * @param auctionIndex the current Auction count
	 * @param names a StringVector holding allNames of all Controllers for FNCSApplicationHelper
	 * @param gldNodes a NodeContainer holding all GLD (House) Nodes for FNCSApplicationHelper
	 * @return a StringVector of all Controller allNames
	 */
	private Vector<String> installAuctionsAndControllers(NodeContainer nodeCont, 
			AuctionObject auction, int auctionIndex, Vector<String> names, NodeContainer gldNodes) {
		
		final int nodeContSize = nodeCont.getNumNodes();
		
	    // Installs market on 1st (0th) node of nodeCont
	    Node firstNode = nodeCont.getNodeNoPrint(0);
	    gldNodes.addNodeContainer(nodeCont);
	    names.pushBack(auction);
		
		for (int i = 0; i < nodeContSize; i++) {
			// Adds each node to global List of Nodes
			addNode(nodeCont.getNodeNoPrint(i));
			// Gets the Controller at the index i offset by the current auctionIndex multiplied by the number of allNodes in nodeCont
	    	Controller tempCont = this.getControllers().get(auctionIndex*nodeContSize + i);
	    	// Adds the name of the Controller to the Names StringVector
	    	names.pushBack(tempCont);
	    	// Adds this Node to the NodeContainer of all GLD Nodes
	    	gldNodes.addNode(nodeCont, i);
		}
		
		return names;
	}
	
	/**
	 * Sets up a FNCSApplicationHelper and ApplicationContainer for the FNCS simulator and 
	 * starts the ns-3 simulator
	 */
	public void setupFncsApplicationHelper() {

        // Adds and prints all controller network interface allNames to list of all allNames
        addControllerNames();
        printControllerNames();

        // TODO will need to fix this if more than 1 auction allowed
        Channel channel = channels.get(0);
        AuctionObject auction = channel.getAuctions().get(0);

        // A map<string, string> mapping AuctionObject name to a Controller name
        StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
        // Maps the Auction NetworkInterfaceName to the GldNodePrefix
        marketToControllerMap.put(auction.getNetworkInterfaceName(), auction.getFncsControllerPrefix());

        ApplicationContainer fncsAps = new ApplicationContainer("fncsAps");
        FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper("fncsHelper");
		fncsHelper.setApps(this.allNames, this.allNodes, marketToControllerMap, fncsAps);
		fncsAps.start(0.0);
		fncsAps.stop(259200.0);
		
		// Run Simulator then clean up after it's done (according to FncsAps.stop(...))
        fncsHelper.appendPrintObj("Simulator::Run();\n");
        fncsHelper.appendPrintObj("Simulator::Destroy();\n");
        fncsHelper.appendPrintObj("return 0;\n");
	}

	/**
	 * @return a List of all objects created for this network
	 * 
	 */
	public List<AbstractNs3Object> createLte() {
		
		this.addModule(new Core());
		this.addModule(new Mobility());
		this.addModule(new Applications());
		this.addModule(new Wifi());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new Internet());
		this.addModule(new Bridge());
		this.addModule(new PointToPoint());
		// LTE specific
		this.addModule(new Lte());
		this.addModule(new Mobility());
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house allNodes
		NodeContainer gldNodes = new NodeContainer("gldNodes");
		
		LteHelper lteHelper = new LteHelper("lteHelper");
		ns3Objects.add(lteHelper);
		
		PointToPointEpcHelper epcHelper = new PointToPointEpcHelper();
		epcHelper.setNameString("epcHelper");
		ns3Objects.add(epcHelper);
		
		Pointer<PointToPointEpcHelper> epcHelperPointer = new Pointer<>("epcHelperPointer");
		epcHelperPointer.createObject(epcHelper);
		
		lteHelper.setEpcHelper(epcHelperPointer);
		
		Pointer<Node> pgw = new Pointer<Node>("pgw");
		pgw.setType(new Node());
		epcHelper.getPgwNode(pgw);

		//TODO fix backbonetype so don't have to do this
		this.setBackboneType(NetworkType.P2P);
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "100Gb/s");
		p2pHelper.setDeviceAttribute("Mtu", 1500); // TODO may be able to use string for this as well
		p2pHelper.setChannelAttribute("Delay", "10ms");
		ns3Objects.add(p2pHelper);
		
		// Internet helpers for IP setup
		InternetStackHelper iStackHelper = new InternetStackHelper("iStackHelper");
		ns3Objects.add(iStackHelper);
		
		Ipv4AddressHelper ipv4AddrHelper = new Ipv4AddressHelper("ipv4AddrHelper");
		ns3Objects.add(ipv4AddrHelper);
		
		Ipv4InterfaceContainer ipv4Interfaces = new Ipv4InterfaceContainer("ipv4Interfaces");
		ns3Objects.add(ipv4Interfaces);
		
		List<NetDeviceContainer> lteDeviceContainers = new ArrayList<NetDeviceContainer>();
		
		int numAuctions = this.getAuctions().size();
		// NumAuctioNodes is total for whole simulation, so calculate AP Nodes per Market
		int numEnbNodesPerAuction = this.getNumBackboneNodes() / numAuctions;
		// Then calculate station Nodes per AP Node
		int numUeNodesPerAuction = numAuctions;
		int numUeNodesPerEnbNode = numUeNodesPerAuction / numEnbNodesPerAuction;
		
		MobilityHelper mobilityHelper = new MobilityHelper("mobilityHelper");
		// TODO ConstantPositionMobilityModel sets all allNodes at origin (0,0,0)
		mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
		
		// Setup QoS Class Indicator 
		Qci q = Qci.GBR_CONV_VOICE;
		q.setName("q");
		
		EpsBearer bearer = new EpsBearer("bearer");
		bearer.setQci(q);
		ns3Objects.add(bearer);
		
		for (int i = 0; i < numAuctions; i++) {
			
			AuctionObject auction = this.getAuctions().get(i);
			
			NodeContainer enbNodes = new NodeContainer("enbNodes_" + i);
			enbNodes.create(numEnbNodesPerAuction);
			ns3Objects.add(enbNodes);
			
			NetDeviceContainer enbDevices = new NetDeviceContainer("enbDevices_" + i);
			ns3Objects.add(enbDevices);
			
			lteHelper.installEnbDevice(enbNodes, enbDevices);
			
			// TODO ConstantPositionMobilityModel sets all allNodes at origin (0,0,0)
			mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
			mobilityHelper.install(enbNodes);
			
			// Install the LTE protocol stack on the eNB allNodes; not for EPC
			//lteHelper.installEnbDevice(enbNodes, enbDevices); 
			
			lteDeviceContainers.add(enbDevices);
			
			// Create a point-to-point network between the enbNodes
			//createBackbone(p2pHelper, enbNodes, enbDevices, i);
			
			ipv4AddrHelper.setBase(getAddrBase() + i + ".0", getAddrMask());
			// Assign IP addresses to NetDevices in enbDevices
			ipv4AddrHelper.assign(enbDevices, ipv4Interfaces);
			
			for (int j = 0; j < numEnbNodesPerAuction; j++) {
				
				// Add all Nodes from this NodeContainer to Nodes global list of allNodes
				addNode(enbNodes.getNodeNoPrint(j));
				
				NodeContainer ueNodes = new NodeContainer("ueNodes_" + j);
				ueNodes.create(numUeNodesPerEnbNode);
				ns3Objects.add(ueNodes);
				
				// TODO ConstantPositionMobilityModel sets all allNodes at origin (0,0,0)
				mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
				mobilityHelper.install(ueNodes);
				
				// Install IP stack on UEs
				iStackHelper.install(ueNodes);
				
				NetDeviceContainer ueDevices = new NetDeviceContainer("ueDevices_" + j);
				lteHelper.installUeDevice(ueNodes, ueDevices); // Install the LTE protocol stack on the UE allNodes
				lteHelper.attach(ueDevices, enbDevices, j); // Attach the newly created UE devices to an eNB device
				//lteHelper.activateDataRadioBearer(ueDevices, bearer); // not used for EPC
				ns3Objects.add(ueDevices);
				
				lteDeviceContainers.add(ueDevices);
				
				Ipv4InterfaceContainer ueIpInterface = new Ipv4InterfaceContainer("ueIpInterface");
				ns3Objects.add(ueIpInterface);
				
				for (int k = 0; k < numUeNodesPerEnbNode; k++) {
					((PointToPointEpcHelper) epcHelperPointer.getObject()).assignUeIpv4Address(ueDevices, ueIpInterface);
				}

				
			    // Installs the Auctions and Controllers on each Node in ueNodes, 
			    //	adds allNames to Names StringVector and Nodes to gldNodes for fncsHelper below
				installAuctionsAndControllers(ueNodes, auction, j, allNames, gldNodes);
	
			}
			
			marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), this.getGldNodePrefix());
		}
		
		return ns3Objects;

	}
	
	/**
	 * @param dataRate
	 * @param delay
	 * @param pcapOutput
	 */
	public void createCsma(String dataRate, String delay, boolean pcapOutput) {
		
		this.addModule(new Core());
		this.addModule(new Applications());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new PointToPoint());
		this.addModule(new Internet());
		this.addModule(new NixVectorRouting());

		final int numChannels = getNumChannels();
		
		// IP setup
		Ipv4AddressHelper addresses = new Ipv4AddressHelper("backboneAddresses");
		
		// CSMA channel/device setup
		CsmaHelper csmaHelper = new CsmaHelper("csmaHelper");
		
		// Point to point channel/device setup
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		
		// Creates a channel for the Auctions & add it to list of channels
		// TODO allow user to select channel type
		// FIXME p2p channels can only have 2 devices on them; for 2+ auctions, need reference to backbonerouter
		PointToPointChannel auctionChannel = new PointToPointChannel("p2pAuctionChannel");
		auctionChannel.setDataRate(dataRate);
		auctionChannel.setDelay(delay);
		auctionChannel.setAddressBase("1.1.1.0"); // TODO implement user-settable Auction addrBase
		addChannel(auctionChannel);
		
		// Creates main backbone router
		Node backboneRouter = new Node("backboneRouter");
		
		// Stores backboneRouter in Pointer for p2pHelper.install
//		Pointer<Node> backboneRouterPtr = new Pointer<Node>("backboneRouterPtr", new Node());
//		backboneRouter.getNode(0, backboneRouterPtr);
		
		// Install IP stack on backboneRouterPtr
		iStackHelper.install(backboneRouter);
		
		// Adds the backbone router node to p2p auction channel
		//auctionChannel.setNodeA(backboneRouter);
		
		// Creates access point routers
//		NodeContainer apNodes = new NodeContainer("apNodes_backbone");
//		apNodes.create(numChannels - 1);
		
		// Adds backbone allNodes to global NodeContainer for FNCSApplicationHelper
		allNodes.addNode(backboneRouter);
		//allNodes.addNodeContainer(apNodes);
		
		// Adds node allNames to global Vector of allNames for FNCSApplicationHelper
		allNames.pushBack(backboneRouter);
		//allNames.pushBack(apNodes);

		
		for (int i = 0; i < numChannels - 1; i++) {
			
			Node apNode = new Node("apNode_" + i);
			allNames.pushBack(apNode);
			
			int ipThird = ((2 * i) % 254 + 1);
			// Sets IP address base to use for devices in this NetDeviceContainer
			String ipFirstTwo = (i / 65014 + 10) + "." + (i / 254 + 1) + ".";
			String ipBase = ipFirstTwo + ipThird + ".0";
			
			// Create the CSMA Channel & add it to list of channels
			CsmaChannel channel = new CsmaChannel("csmaChannel_backbone_" + i);
			channel.setDataRate(dataRate);
			channel.setDelay(delay);
			// Add IP address base to Channel for use later in Market/Controller integration
			channel.setAddressBase(ipBase); // TODO check if this works later
			addChannel(channel);
			
			NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_backbone_" + i);
			
			// Installs the CSMA protocols on the devices using the given channel
			csmaHelper.install(apNode, (CsmaChannel) channel, csmaDevices);
			// Installs the IP stack protocols on the CSMA allNodes
			iStackHelper.install(apNode);
			
			addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
			addresses.assign(csmaDevices);
			
			NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_backbone_" + i);
			
			// Install p2p devices on ap node and backbone router & connect via p2p channel
			p2pHelper.install(apNode, backboneRouter, auctionChannel, p2pDevices);
			
			ipThird = ((2 * i) % 254 + 2);
			
			ipBase = ipFirstTwo + ipThird + ".0";
			
			addresses.setBase(ipBase, "255.255.255.0");
			addresses.assign(p2pDevices);
			
		}
		
		csmaHelper.enablePcapAll("csmaBackboneRouter");
		p2pHelper.enablePcapAll("p2pBackboneRouter");
		
	}

	/**
	 * @param marketNIPrefix the market network interface prefix
	 * @param controllerNIPrefix the house controller network interface prefix
	 * @return ns3Objects a list of all objects created in this network
	 * 
	 */
	// TODO add all objects to ns3Objects or just add 1 (only need 1 to print all ns3 setup text)
	// TODO give params to Ns3Network, then call this to call all other necessary methods
	public List<AbstractNs3Object> buildBackbone(final String marketNIPrefix, final String controllerNIPrefix) {
		
		setupFncsSimulator(marketNIPrefix);
		
		// Sets name for global NodeContainer for use in FNCSApplicationHelper.setApps(...)
		allNodes.setName("allNodes");
		// Sets name for global InternetStackHelper (to utilize NixVectorRouting)
		iStackHelper.setName("iStackHelper");
		// Sets up iStackHelper with static and nix vector routing
		setupInternetStackAndRouting();
		
/*		// Instantiates global Vector allNames for use in FNCSApplicationHelper.setApps(...)
		allNames = new Vector<String>("allNames", String.class);*/
		
		// TODO get user input for this
		boolean pcapOutput = true;
		
		// Creates backbone network
		// TODO build appropriate network(s) type
		createCsma(this.getBackboneDataRate(), this.getBackboneDelay(), pcapOutput);
		
		return ns3Objects;
	}
	
	/**
	 * @return ns3Objects a list of all objects created in this network
	 */
	public List<AbstractNs3Object> buildFrontend() {
		
		// Connect the Controllers and Auction to the backbone network
		connectControllerChannels();
		
		StringMap<String, String> marketToControllerMap = connectAuctionChannels();
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelper();
		
		return ns3Objects;
	}

	/**
	 * Adds the controller network interface name for the controller attached to
	 * each house channel to the list of all allNames needed for the
	 * FncsApplicationHelper to setup the communication between the
	 * GLD simulator(s) and the ns-3 simulator(s).
	 */
	public void addControllerNames() {
		for (Channel chan : getHouseChannels()) {
			Controller controller = chan.getControllers().get(0);
			String controllerNIName = controller.getNetworkInterfaceName();
			allNames.addName(controllerNIName);
		}
	}

	/**
	 * Outputs the GLD Controller allNames and the ns-3 node allNames to the output
	 * ns-3 .cc file
	 * @return a string
	 */
	public String printControllerNames() {
		return allNames.printInfo();
	}

	public void setupGlobalRouting() {
		Ipv4GlobalRoutingHelper globalRtHelper = new Ipv4GlobalRoutingHelper("globalRoutingHelper");
		globalRtHelper.populate();
	}
}
