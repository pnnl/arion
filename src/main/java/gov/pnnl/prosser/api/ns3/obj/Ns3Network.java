/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.StringVector;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.enums.Qci;
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
	
	private NetworkType backboneType, auctionType;
	private String addrBase, addrMask;
	private String gldNodePrefix;
	private double stopTime;
	private List<Module> modules;
	private List<Node> nodes;
	private List<AuctionObject> auctions;
	private List<Controller> controllers;
	private List<AbstractNs3Object> objects;
	private int numAuctionNodes, numBackboneNodes;

	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 */
	public Ns3Network() {
		this.backboneType = null;
		this.auctionType = null;
		this.addrBase = "10.1.0.0";
		this.addrMask = "255.255.255.0";
		this.stopTime = Double.MAX_VALUE;
		this.modules = new ArrayList<Module>();
		this.nodes = new ArrayList<Node>();
		this.auctions = new ArrayList<>();
		this.controllers = new ArrayList<>();
		this.objects = new ArrayList<AbstractNs3Object>();
	}
	
	/**
	 * @return the NetworkType of the Backbone infrastructure
	 */
	public NetworkType getBackboneType() {
		return backboneType;
	}
	
	/**
	 * 
	 * @param type the NetworkType to set for the Backbone infrastructure
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
	 * @param type the NetworkType to set for the Auction market
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
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
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
	}
	
	/**
	 * @return the number of Auction Nodes
	 */
	public int getNumAuctionNodes() {
		return this.numAuctionNodes;
	}

	/**
	 * @param numAuctionNodes the number of Auction Nodes to set
	 */
	public void setNumAuctionNodes(int numAuctionNodes) {
		this.numAuctionNodes = numAuctionNodes;
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
	 * 
	 * @param stack
	 * @param addresses
	 * @param baseNodes
	 * @param gldNodes a NodeContainer of GLD market and baseNodes to 
	 * 			pass to FNCSApplicationHelper.SetApps(...)
	 * @param names a StringVector<String> of all ControllerNetworkInterface names
	 * @param marketToControllerMap a StringMap<String, String> from MarketNetworkInterface 
	 * 			name to NetworkControllerInterface name
	 */
	private void setupMarket(InternetStackHelper stack, Ipv4AddressHelper addresses, 
								NodeContainer[] baseNodes, NodeContainer gldNodes, 
								StringVector<String> names,
								StringMap<String, String> marketToControllerMap) {
		
		this.addModule("point-to-point-module"); // To use PointToPoint network type
		
		int numAuctions = this.getAuctions().size();
		//int numConts = numNodes/20 + 1; // Number of NodeContainers to create
		int numConts = baseNodes.length;
		
		NodeContainer auctionCon = new NodeContainer();
		auctionCon.setName("marketCon");
		auctionCon.create(numAuctions);
		
		// Installs an IP protocol stack on the Nodes in the Market NodeContainer
		stack.install(auctionCon);
		
		// Add GLD market Nodes to container of GLD Nodes
		gldNodes.addNodeContainer(auctionCon);
		
		// Creates a point to point channel to connect the Market and network nodes
		PointToPointHelper p2pHelper = new PointToPointHelper();
		p2pHelper.setName("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "4Mbps");
		p2pHelper.setChannelAttribute("Delay", "2ms");
		
		for (int i = 0; i < numAuctions; i++) {
			
			// Select ith AuctionObject
			AuctionObject auction = this.getAuctions().get(i);
			// Set ith Node's MarketNI equal to mni
			auctionCon.getNode(i).setAuction(auction);
			
			for (int j = 0; j < numConts; j++) {
				
				NodeContainer tempCont = baseNodes[j];
				
				String addr = "11." + j + "." + i + ".0";
				
				NetDeviceContainer marketToNetwork = new NetDeviceContainer();
				marketToNetwork.setName("marketToNetwork_" + j);
				
				NodeContainer p2pInstall = new NodeContainer();
				p2pInstall.setName("p2pInstall_" + j);
				p2pInstall.addNode(auctionCon, i);
				p2pInstall.addNode(tempCont, 0);
				
				p2pHelper.install(p2pInstall, marketToNetwork);
				
				addresses.setBase(addr, "255.255.255.0");
				addresses.assign(marketToNetwork);
				
				// Add a ControllerNetworkInterface to each Node in baseNodes[j]
				for (int k = 0; k < tempCont.getNumNodes(); k++) {
					Controller controller = this.getControllers().get(k);
					Node temp = tempCont.getNode(k);
					temp.setController(controller);
					// Add baseNodes to container of GLD Nodes
					gldNodes.addNode(temp);
					// Add controller prefix name to names vector
					names.pushBack(controller);
					marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), controller.getNetworkInterfaceName());
				}
			}
		}
		
	}
	
	/**
	 * @param numApNodes equal to number of MarketNetworkInterfaces in the mni List
	 * @param numStaNodes total number of station nodes to create; 
	 * 			equal to number of ControllerNetworkInterfaces (GLD houses) in the cni List
	 * @param ipv4Address
	 * @param latency
	 * @return objects a List of all AbstractNs3Objects created for this WiFi network
	 */
	public List<AbstractNs3Object> createWifi(String ipv4Address, String latency) {
		
		setupFncsSimulator();

		
		int numAuctions = this.getAuctions().size();
		// Node #s passed in are total (for whole simulation), so calculate AP Nodes per Auction
		int numApNodesPerAuction = this.getNumBackboneNodes() / numAuctions;
		// Then calculate station Nodes per AP Node
		int numStaNodesPerAuction = this.getNumAuctionNodes() / numAuctions;
		int numStaNodesPerApNode = numStaNodesPerAuction / numApNodesPerAuction;
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>();
		marketToControllerMap.setName("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house nodes
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		
		// A vector<string> used to hold the names of GLD market and house nodes
		StringVector<String> names = new StringVector<String>();
		names.setName("names");
		
		// Add the necessary modules for WiFi
		this.addModule("core-module");
		this.addModule("mobility-module");
		this.addModule("applications-module");
		this.addModule("wifi-module");
		this.addModule("network-module");
		this.addModule("csma-module");
		this.addModule("internet-module");
		this.addModule("bridge-helper");
		
		
		// Setup the Internet protocols
		Ipv4InterfaceContainer apInterfaces = new Ipv4InterfaceContainer();
		apInterfaces.setName("apInterfaces");
		objects.add(apInterfaces);
		Ipv4InterfaceContainer staInterfaces = new Ipv4InterfaceContainer();
		staInterfaces.setName("staInterfaces");
		objects.add(staInterfaces);
		InternetStackHelper stack = new InternetStackHelper();
		stack.setName("stack");
		objects.add(stack);
		Ipv4AddressHelper addressHelper = new Ipv4AddressHelper();
		addressHelper.setName("addressHelper");
		objects.add(addressHelper);
		
		// Generic helper for installing the selected network protocol on the backbone Nodes
		NetworkHelper backboneHelper = new NetworkHelper();		
		
		YansWifiPhyHelper wifiPhy = new YansWifiPhyHelper();
		wifiPhy.setName("wifiPhy");
		wifiPhy.defaultParams();
		wifiPhy.setPcapDataLinkType("YansWifiPhyHelper::DLT_IEEE802_11_RADIO");
		objects.add(wifiPhy);
		
		// TODO make these as actual Vector objects if vectors needed for more than testing
		String vectors = "std::vector<NodeContainer> staNodeVector;\n" +
						  "std::vector<NetDeviceContainer> staDeviceVector;\n" +
						  "std::vector<NetDeviceContainer> apDeviceVector;\n" +
						  "std::vector<Ipv4InterfaceContainer> staInterfaceVector;\n" +
						  "std::vector<Ipv4InterfaceContainer> apInterfaceVector;\n";
		
		stack.appendPrintObj(vectors);
		
		// Used by MobilityHelper's positionAllocator and setMobilityModel
		double wifiX = 0.0;
		
		for (int i = 0 ; i < numAuctions; i++) {
			
			AuctionObject auction = this.auctions.get(i);
			
			NodeContainer backboneNodes = new NodeContainer();
			backboneNodes.setName("backboneNodes_" + i);
			backboneNodes.create(numApNodesPerAuction);
			stack.install(backboneNodes);
			objects.add(backboneNodes);
			
			NetDeviceContainer backboneDevices = new NetDeviceContainer();
			backboneDevices.setName("backboneDevices");
			createBackbone(backboneHelper, backboneNodes, backboneDevices, i);
			
			
			for (int j = 0; j < numApNodesPerAuction; j++) {
				String ssidBase = "wifi_" + auction.getNetworkInterfaceName() + "_" + i + "_" + j;
				Ssid ssid = new Ssid();
				ssid.setName("ssid_" + i + "_"  + j);
				ssid.setSsid(ssidBase);
				objects.add(ssid);
				
				NodeContainer staNodes = new NodeContainer();
				staNodes.setName("staNodes_" + i + "_"  + j);
				staNodes.create(numStaNodesPerApNode);
				objects.add(staNodes);
				
				NetDeviceContainer staDevs = new NetDeviceContainer();
				staDevs.setName("staDev_" + i + "_"  + j);
				objects.add(staDevs);
				NetDeviceContainer apDevs = new NetDeviceContainer();
				apDevs.setName("apDev_" + i + "_"  + j);
				objects.add(apDevs);
				
				Ipv4InterfaceContainer staInterface = new Ipv4InterfaceContainer();
				staInterface.setName("staInterface_" + i + "_"  + j);
				objects.add(staInterface);
				Ipv4InterfaceContainer apInterface = new Ipv4InterfaceContainer();
				apInterface.setName("apInterface_" + i + "_"  + j);
				objects.add(apInterface);
				
				MobilityHelper mobility = new MobilityHelper();
				mobility.setName("mobility_" + i + "_"  + j);
				objects.add(mobility);
				
				BridgeHelper bridge = new BridgeHelper();
				bridge.setName("bridge_" + i + "_"  + j);
				objects.add(bridge);
				
				WifiHelper wifi = new WifiHelper();
				wifi.setName("wifi_" + i + "_"  + j);
				wifi.defaultParams();
				objects.add(wifi);
				
				NqosWifiMacHelper wifiMac = new NqosWifiMacHelper();
				wifiMac.setName("wifiMac_" + i + "_"  + j);
				wifiMac.defaultParams();
				objects.add(wifiMac);
				
				YansWifiChannelHelper wifiChannel = new YansWifiChannelHelper();
				wifiChannel.setName("wifiChannel_" + i + "_"  + j);
				wifiChannel.defaultParams();
				wifiPhy.setChannel(wifiChannel.create());
				objects.add(wifiChannel);
				
			    mobility.setPositionAllocator("ns3::GridPositionAllocator",
			    								wifiX, 0.0, 5.0, 5.0, 1,
			                                    "RowFirst");
			    // Setup the AP
			    mobility.setMobilityModel("ns3::ConstantPositionMobilityModel");
			    mobility.install(backboneNodes, j);
			    wifiMac.setType("ns3::ApWifiMac", ssid);
			    
			    // Installs a WiFi device on the ith backbone Node to make the AP WiFi device
			    wifi.install(wifiPhy, wifiMac, backboneNodes, j, apDevs);
			   
			    NetDeviceContainer temp = new NetDeviceContainer();
			    temp.setName("temp_" + i + "_"  + j);
			    temp.addDevices(apDevs);
			    temp.addDevice(backboneDevices, j);
			    objects.add(temp);
			    
			    // Creates a Bridge to install on the AP WiFi device
			    NetDeviceContainer bridgeDevs = new NetDeviceContainer();
			    bridgeDevs.setName("bridgeDevs_" + i + "_"  + j);
			    objects.add(bridgeDevs);
			    // Installs a Bridge device onto the jth Node of backboneNodes and
			    //	attaches the bridgeDevs NetDevices as ports of the Bridge
			    bridge.install(backboneNodes, j, temp, bridgeDevs);
	
			    // Assigns AP IP address to Bridge, not WiFi device
			    addressHelper.assign(bridgeDevs, apInterface);
	
			    // Setup the WiFi station nodes
			    stack.install(staNodes);
			    
			    // Setup the Mobility model
			    mobility.setMobilityModel("ns3::RandomWalk2dMobilityModel",
			                               "Time", "2s", 
			                               "ns3::ConstantRandomVariable[Constant=1.0]",
			                               "RectangleValue(Rectangle(" + 
			                               wifiX + ", " + wifiX + " + 5.0, 0.0, (" 
			                               + numStaNodesPerApNode + " + 1) * 5.0))");
			    mobility.install(staNodes);
			    
			    wifiMac.setType("ns3::StaWifiMac", ssid, false);
			    wifi.install(wifiPhy, wifiMac, staNodes, staDevs);
			    addressHelper.assign(staDevs, staInterface);
			    
			    // Installs the Auctions and Controllers on each staNode, 
			    //	adds names to Names StringVector and Nodes to gldNodes for fncsHelper below
			    names = installAuctionsAndControllers(staNodes, auction, j, names, gldNodes);
			    
			    // Saves everything in containers.
			    String stuff = "\n\tstaNodeVector.push_back(staNodes_" + i + "_"  + j + ");\n" +
					    "\tapDeviceVector.push_back(apDev_" + i + "_"  + j + ");\n" +
					    "\tapInterfaceVector.push_back(apInterface_" + i + "_" + j +");\n" +
					    "\tstaDeviceVector.push_back(staDev_" + i + "_"  + j + ");\n" +
					    "\tstaInterfaceVector.push_back(staInterface_" + i + "_"  + j +");\n";
			    
			    addressHelper.appendPrintObj(stuff);
	
			    wifiX += 20.0;
			}
			
			// Map the Auction NetworkInterfaceName to the GldNodePrefix
			marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), this.getGldNodePrefix());

		}
		
		AbstractNs3Object ns3 = objects.get(0);
		
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
		  "   ApplicationContainer apps = onoff.Install (staNodeVector[0].Get (0));\n" +
		  "   apps.Start (Seconds (0.5));\n" +
		  "   apps.Stop (Seconds (3.0));\n" +
		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", apDeviceVector[0]);\n" +

		  "   if (true)\n" +
		    "   {\n" +
		      "   AsciiTraceHelper ascii;\n" +
		      "   MobilityHelper::EnableAsciiAll (ascii.CreateFileStream (\"wifi-wired-bridging.mob\"));\n" +
		    "   }\n";

		
		ns3.appendPrintObj(stuff + "\n");
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelperAndSimulator(names, gldNodes, marketToControllerMap);
		
		return objects;
		
	}
	
	private void setupFncsSimulator() {
		// Setup FNCS simulator
		FncsSimulator fncsSim = new FncsSimulator();
		fncsSim.setName("fncsSim");
		
		Pointer<FncsSimulator> hb2 = new Pointer<>();
		hb2.setName("hb2");
		hb2.encapsulate(fncsSim);
		
		fncsSim.unref();
		fncsSim.setImplementation(hb2);
		
	}

	/**
	 * 
	 * @param nodeCont the Nodes to set an Auction and Controllers
	 * @param auction the AuctionObject to set on the first Node of nodeCont
	 * @param auctionIndex the current Auction count
	 * @param names a StringVector holding names of all Controllers for FNCSApplicationHelper
	 * @param gldNodes a NodeContainer holding all GLD (House) Nodes for FNCSApplicationHelper
	 * @return a StringVector of all Controller names
	 */
	private StringVector<String> installAuctionsAndControllers(NodeContainer nodeCont, 
			AuctionObject auction, int auctionIndex, StringVector<String> names, NodeContainer gldNodes) {
		
		final int nodeContSize = nodeCont.getNumNodes();
		
	    // Installs market on 1st (0th) node of nodeCont
	    Node firstNode = nodeCont.getNode(0);
	    firstNode.setAuction(auction);
	    gldNodes.addNodeContainer(nodeCont);
	    names.pushBack(auction);
		
		for (int i = 0; i < nodeContSize; i++) {
			// Adds each node to global List of Nodes
			nodes.add(nodeCont.getNode(i));
			// Gets the Controller at the index i offset by the current auctionIndex multiplied by the number of nodes in nodeCont
	    	Controller tempCont = this.getControllers().get(auctionIndex*nodeContSize + i);
	    	// Assigns the Controller at index i offset by the index of the Auction times the # of Nodes per Auction
	    	nodeCont.getNode(i).setController(tempCont);
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
	private void setupFncsApplicationHelperAndSimulator(StringVector<String> names, NodeContainer gldNodes, StringMap<String, String> marketToControllerMap) {
		this.addModule("fncs");
		this.addModule("fncsapplication-helper");
		
		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper();
		fncsHelper.setName("fncsHelper");
		
		ApplicationContainer fncsAps = new ApplicationContainer();
		fncsAps.setName("fncsAps");
		
		fncsHelper.setApps(names, gldNodes, marketToControllerMap, fncsAps);
		fncsAps.start(0.0);
		fncsAps.stop(259200.0);
		
		// Run Simulator then clean up after it's done (according to FncsAps.stop(...))
		fncsAps.appendPrintObj("Simulator::Run();\n");
		fncsAps.appendPrintObj("Simulator::Destroy();\n");
		fncsAps.appendPrintObj("return 0;\n");
	}
	
	/**
	 * 
	 * @param backboneHelper
	 * @param backboneNodes
	 * @param backboneDevices
	 * @param index used for naming to prevent name conflicts when called in a loop
	 */
	private void createBackbone(NetworkHelper backboneHelper, NodeContainer backboneNodes,
			NetDeviceContainer backboneDevices, int index) {
		
		if (this.backboneType.name().equalsIgnoreCase("csma")) {
			backboneHelper = new CsmaHelper();
			backboneHelper.setName("csmaHelper_" + index);
			((CsmaHelper) backboneHelper).install(backboneNodes, backboneDevices);
			
		} else if (this.backboneType.name().equalsIgnoreCase("lte")) {
			backboneHelper = new LteHelper();
			backboneHelper.setName("lteHelper_" + index);
			((LteHelper) backboneHelper).installUeDevice(backboneNodes, backboneDevices);
			NodeContainer enbNodes = new NodeContainer();
			enbNodes.setName("enbNodes_" + index);
			
			// TODO finish LTE backbone; ask if we need this (LTE backbone?)
			
		} else if (this.backboneType.name().equalsIgnoreCase("p2p")) {
			backboneHelper = new PointToPointHelper();
			backboneHelper.setName("pointopointHelper_" + index);
			((PointToPointHelper) backboneHelper).install(backboneNodes, backboneDevices);

		} else {
			backboneHelper = new WifiHelper();
			backboneHelper.setName("wifiHelper_" + index);
			
			// TODO finish WiFi backbone; ask if we need this (WiFi backbone?)
			// If so, need to pass WifiPhyHelper
		}
	}

	/**
	 * @return a List of all objects created for this network
	 * 
	 */
	public List<AbstractNs3Object> createLte() {
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		this.addModule("point-to-point-module");
		
		this.addModule("lte-module");
		this.addModule("mobility-module");
		
		// Creates the FNCS simulator
		setupFncsSimulator();
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>();
		marketToControllerMap.setName("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house nodes
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		
		// A vector<string> used to hold the names of GLD market and house nodes
		StringVector<String> names = new StringVector<String>();
		names.setName("names");
		
		LteHelper lteHelper = new LteHelper();
		lteHelper.setName("lteHelper");
		objects.add(lteHelper);
		
		PointToPointEpcHelper epcHelper = new PointToPointEpcHelper();
		epcHelper.setNameString("epcHelper");
		objects.add(epcHelper);
		
		Pointer<PointToPointEpcHelper> epcHelperPointer = new Pointer<>();
		epcHelperPointer.setName("epcHelperPointer");
		epcHelperPointer.construct(epcHelper);
		
		lteHelper.setEpcHelper(epcHelperPointer);
		
		// Internet helpers for IP setup
		InternetStackHelper iStackHelper = new InternetStackHelper();
		iStackHelper.setName("iStackHelper");
		objects.add(iStackHelper);
		
		Ipv4AddressHelper ipv4AddrHelper = new Ipv4AddressHelper();
		ipv4AddrHelper.setName("ipv4AddrHelper");
		objects.add(ipv4AddrHelper);
		
		Ipv4InterfaceContainer ipv4Interfaces = new Ipv4InterfaceContainer();
		ipv4Interfaces.setName("ipv4Interfaces");
		objects.add(ipv4Interfaces);
		
		//TODO fix backbonetype so don't have to do this
		this.setBackboneType(NetworkType.P2P);
		PointToPointHelper p2pHelper = new PointToPointHelper();
		p2pHelper.setName("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "100Gb/s");
		p2pHelper.setDeviceAttribute("Mtu", 1500); // TODO may be able to use string for this as well
		p2pHelper.setChannelAttribute("Delay", "10ms");
		objects.add(p2pHelper);
		
		List<NetDeviceContainer> lteDeviceContainers = new ArrayList<NetDeviceContainer>();
		
		int numAuctions = this.getAuctions().size();
		// NumAuctioNodes is total for whole simulation, so calculate AP Nodes per Market
		int numEnbNodesPerAuction = this.getNumBackboneNodes() / numAuctions;
		// Then calculate station Nodes per AP Node
		int numUeNodesPerAuction = this.getNumAuctionNodes() / numAuctions;
		int numUeNodesPerEnbNode = numUeNodesPerAuction / numEnbNodesPerAuction;
		
		MobilityHelper mobilityHelper = new MobilityHelper();
		mobilityHelper.setName("mobilityHelper");
		// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
		mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
		
		// Setup QoS Class Indicator 
		Qci q = Qci.GBR_CONV_VOICE;
		q.setName("q");
		
		EpsBearer bearer = new EpsBearer();
		bearer.setName("bearer");
		bearer.setQci(q);
		objects.add(bearer);
		
		for (int i = 0; i < numAuctions; i++) {
			
			AuctionObject auction = this.getAuctions().get(i);
			
			NodeContainer enbNodes = new NodeContainer();
			enbNodes.setName("enbNodes_" + i);
			enbNodes.create(numEnbNodesPerAuction);
			objects.add(enbNodes);
			
			NetDeviceContainer enbDevices = new NetDeviceContainer();
			enbDevices.setName("enbDevices_" + i);
			objects.add(enbDevices);
			
			// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
			mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
			mobilityHelper.install(enbNodes);
			
			// Install the LTE protocol stack on the eNB nodes
			lteHelper.installEnbDevice(enbNodes, enbDevices); 
			/* FIXME assert failed. cond="uid <= m_information.size () && uid != 0", file=../src/core/model/type-id.cc, line=231
				terminate called without an active exception
				Aborted (core dumped)
				
				#0  0x00007ffff3f3c8c7 in raise () from /lib64/libc.so.6
				#1  0x00007ffff3f3e52a in abort () from /lib64/libc.so.6
				#2  0x00007ffff4844fdd in __gnu_cxx::__verbose_terminate_handler() ()
				   from /lib64/libstdc++.so.6
				#3  0x00007ffff4842e56 in ?? () from /lib64/libstdc++.so.6
				#4  0x00007ffff4842ea1 in std::terminate() () from /lib64/libstdc++.so.6
				#5  0x00007ffff5ea9150 in ns3::IidManager::LookupInformation (
				    this=this@entry=0x7ffff61ae3c0 <ns3::Singleton<ns3::IidManager>::Get()::object>, uid=uid@entry=0) at ../src/core/model/type-id.cc:231
				#6  0x00007ffff5eaa6b2 in ns3::IidManager::GetConstructor (
				    this=0x7ffff61ae3c0 <ns3::Singleton<ns3::IidManager>::Get()::object>, 
				    uid=uid@entry=0) at ../src/core/model/type-id.cc:331
				#7  0x00007ffff5eaf457 in ns3::TypeId::GetConstructor (
				    this=this@entry=0x7fffffffd638) at ../src/core/model/type-id.cc:726
				#8  0x00007ffff5f272e8 in ns3::ObjectFactory::Create (
				    this=this@entry=0x7fffffffd638) at ../src/core/model/object-factory.cc:93
				#9  0x00007ffff523df34 in ns3::LteHelper::DoInitialize (this=0x7fffffffd550)
				    at ../src/lte/helper/lte-helper.cc:91
				#10 0x00007ffff5ec1f99 in ns3::Object::Initialize (
				    this=this@entry=0x7fffffffd550) at ../src/core/model/object.cc:198
				#11 0x00007ffff523b0a6 in ns3::LteHelper::InstallEnbDevice (
				    this=0x7fffffffd550, c=...) at ../src/lte/helper/lte-helper.cc:350
				#12 0x0000000000404ac0 in main (argc=1, argv=0x7fffffffdf58) at ns3.cc:72

			*/

			
			lteDeviceContainers.add(enbDevices);
			
			// Create a point-to-point network between the enbNodes
			//createBackbone(p2pHelper, enbNodes, enbDevices, i);
			
			ipv4AddrHelper.setBase(getAddrBase() + i + ".0", getAddrMask());
			// Assign IP addresses to NetDevices in enbDevices
			ipv4AddrHelper.assign(enbDevices, ipv4Interfaces);
			
			for (int j = 0; j < numEnbNodesPerAuction; j++) {
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
				nodes.add(enbNodes.getNode(j));
				
				NodeContainer ueNodes = new NodeContainer();
				ueNodes.setName("ueNodes_" + j);
				ueNodes.create(numUeNodesPerEnbNode);
				objects.add(ueNodes);
				
				// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
				mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
				mobilityHelper.install(ueNodes);
				
				iStackHelper.install(ueNodes);
				
				NetDeviceContainer ueDevices = new NetDeviceContainer();
				ueDevices.setName("ueDevices_" + j);
				lteHelper.installUeDevice(ueNodes, ueDevices); // Install the LTE protocol stack on the UE nodes
				lteHelper.attach(ueDevices, enbDevices, j); // Attach the newly created UE devices to an eNB device
				lteHelper.activateDataRadioBearer(ueDevices, bearer);
				objects.add(ueDevices);
				
				lteDeviceContainers.add(ueDevices);
				
				Ipv4InterfaceContainer ueIpInterface = new Ipv4InterfaceContainer();
				ueIpInterface.setName("ueIpInterface");
				objects.add(ueIpInterface);
				
				for (int k = 0; k < numUeNodesPerEnbNode; k++) {
					((PointToPointEpcHelper) epcHelperPointer.getObject()).assignUeIpv4Address(ueDevices, ueIpInterface);
				}

				
			    // Installs the Auctions and Controllers on each Node in ueNodes, 
			    //	adds names to Names StringVector and Nodes to gldNodes for fncsHelper below
				installAuctionsAndControllers(ueNodes, auction, j, names, gldNodes);
	
			}
			
			marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), this.getGldNodePrefix());
		}
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelperAndSimulator(names, gldNodes, marketToControllerMap);
		
		return objects;

	}
	
	/**
	 * @param dataRate
	 * @param delay
	 * @return a List of all objects created for this network
	 */
	public List<AbstractNs3Object> createCsma(String dataRate, String delay) {
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		
		this.addModule("csma-module");
		this.addModule("nix-vector-routing-module");
		
		// Creates the FNCS simulator
		setupFncsSimulator();
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>();
		marketToControllerMap.setName("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house nodes
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		
		// A vector<string> used to hold the names of GLD market and house nodes
		StringVector<String> names = new StringVector<String>();
		names.setName("names");
		
		// IP setup
		Ipv4AddressHelper addresses = new Ipv4AddressHelper();
		addresses.setName("addresses");
		objects.add(addresses);
		InternetStackHelper stack = new InternetStackHelper();
		stack.setName("stack");
		setupIp(stack);
		objects.add(stack);
		
		int numAuctions = this.getAuctions().size();
		// Calculate NodeContainers per Auction (numBackboneNodes = numAuctionNodes / 20)
		int numNodeContainersPerAuction = this.getNumBackboneNodes() / numAuctions;
		// Calculate CSMA Nodes per Auction
		int numCsmaNodesPerAuction = this.getNumAuctionNodes() / numAuctions;
		int numCsmaNodesPerContainer = numCsmaNodesPerAuction / numNodeContainersPerAuction;
		
		// CSMA channel/device setup
		CsmaHelper csmaHelper = new CsmaHelper();
		csmaHelper.setName("csmaHelper");
		csmaHelper.setChannelAttribute("DataRate", dataRate);
		csmaHelper.setChannelAttribute("Delay", delay);
		objects.add(csmaHelper);
		
		for (int i = 0; i < numAuctions; i++) {
			AuctionObject auction = this.getAuctions().get(i);
			
			for (int j = 0; j < numNodeContainersPerAuction; j++) {
				
				NodeContainer csmaNodes = new NodeContainer();
				csmaNodes.setName("csmaNodes_" + i + "_" + j);
				csmaNodes.create(numCsmaNodesPerContainer); // Create 20 Nodes in this NodeContainer
				objects.add(csmaNodes);
				
				// Sets IP address base to use for devices in this NetDeviceContainer
				String ipBase = this.addrBase + i + ".0"; 
				NetDeviceContainer temp = new NetDeviceContainer();
				temp.setName("netDevices_" + i);
				
				// Install the CSMA devices & channel onto the temp NodeContainer
				csmaHelper.install(csmaNodes, temp);
				// Install the IP stack protocols on the CSMA Nodes
				stack.install(csmaNodes);
				
				addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
				addresses.assign(temp);
				objects.add(temp);
				
			    // Installs the Auctions and Controllers on each Node in csmaNodes, 
			    //	adds names to Names StringVector and Nodes to gldNodes for fncsHelper below
				installAuctionsAndControllers(csmaNodes, auction, j, names, gldNodes);
				
			}
		}
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelperAndSimulator(names, gldNodes, marketToControllerMap);
		
		return objects;
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * @return objects a List of all AbstractNs3Objects created in this network
	 */
	public List<AbstractNs3Object> build() {
		
		List<AbstractNs3Object> objects = new ArrayList<AbstractNs3Object>();
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		
		
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		
		StringVector<String> names = new StringVector<String>();
		names.setName("names");
		
		// For FNCS
		this.addModule("fncs");
		this.addModule("fncsapplication-helper");
		
		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper();
		fncsHelper.setName("fncsHelper");
		
		ApplicationContainer fncsAps = new ApplicationContainer();
		fncsAps.setName("fncsAps");
		
		//fncsHelper.setApps("names", gldNodes, "marketToControllerMap", fncsAps);
		fncsAps.start(0.0);
		fncsAps.stop(259200.0);
		
		AbstractNs3Object ns3 = nodes.get(0);
		
		// Output simulator stop, run, & destroy
		ns3.appendPrintObj("\nSimulator::Stop(Seconds(" + this.stopTime + "));\n");
        // This stuff doesn't seem to vary from sim to sim
		ns3.appendPrintObj("Simulator::Run();\n");
		ns3.appendPrintObj("Simulator::Destroy();\n");
		ns3.appendPrintObj("return 0;\n");
		
		return objects;
	}
	
}
