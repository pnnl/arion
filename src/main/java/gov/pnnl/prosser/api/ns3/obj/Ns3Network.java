/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.StringVector;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.pwr.obj.Controller;
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.pwr.obj.MarketNetworkInterface;

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
	 * Type of networks available
	 * 
	 * @author happ546
	 *
	 */
	public enum NetworkType {
		CSMA, LTE, P2P, WIFI
	}
	
	/**
	 * Quality of Service Class Indicator types, used by LTE network EPS Bearers, a 
	 * radio link between User Equipment (UE) devices and Evolved Node B (eNB) base station devices
	 * 
	 * @author happ546
	 *
	 */
	public enum Qci {		
		GBR_CONV_VOICE,
		GBR_CONV_VIDEO,
		GBR_GAMING,
		GBR_NON_CONV_VIDEO,
		NGBR_IMS,
		NGBR_VIDEO_TCP_OPERATOR,
		NGBR_VOICE_VIDEO_GAMING,
		NGBR_VIDEO_TCP_PREMIUM,
		NGBR_VIDEO_TCP_DEFAULT;
		
		private String name;
		
		/**
		 * 
		 * @return the name of this QoS Class Indicator
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * 
		 * @param name sets the name of this QoS Class Indicator
		 */
		public void setName(String name) {
			this.name = name;
		}
	}
	
	private NetworkType type;
	private String addrBase, addrMask;
	private int numNodes;
	private double stopTime;
	private List<Module> modules; // List of ns-3 Modules used in this network
	private List<Node> nodes;
	private List<AbstractProsserObject> gldObjects;
	
//	// TODO need marketNIs & controllerNIs?
//	private List<MarketNetworkInterface> marketNIs;
//	private List<ControllerNetworkInterface> controllerNIs;
	
	private List<AuctionObject> auctions;
	private List<Controller> controllers;
	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 */
	public Ns3Network() {
		this.type = null;
		this.addrBase = "";
		this.addrMask = "";
		this.numNodes = 0;
		this.stopTime = 0.0;
		this.modules = new ArrayList<Module>();
		this.nodes = new ArrayList<Node>();
		this.setGldObjects(new ArrayList<AbstractProsserObject>());
//		this.marketNIs = new ArrayList<MarketNetworkInterface>();
//		this.controllerNIs = new ArrayList<ControllerNetworkInterface>();
		this.auctions = new ArrayList<>();
		this.controllers = new ArrayList<>();
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
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
	}
	
//	/**
//	 * 
//	 * @param marketNI the MarketNetwrokInterface to use for the 
//	 * 			market Nodes in this network
//	 */
//	public void setMarketNIs(List<MarketNetworkInterface> marketNIs) {
//		this.marketNIs = marketNIs;
//	}
//
//	/**
//	 * 
//	 * @param controllerNIs the ControllerNetworkInterface to use for the 
//	 * 			GLD House connected Nodes in this network
//	 */
//	public void setControllerNIs(List<ControllerNetworkInterface> controllerNIs) {
//		this.controllerNIs = controllerNIs;
//	}

	/**
	 * @return the auctions
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
					marketToControllerMap.put(this.getAuctions().get(i).getName(), controller.getName());
				}
			}
		}
		
	}
	
	// TODO add param(s) for bridge between AP nodes (boolean flag [needed or can assume always a bridge?], type of bridge)
	/**
	 * @param numApNodes equal to number of MarketNetworkInterfaces in the mni List
	 * @param numStaNodes total number of station nodes to create; 
	 * 			equal to number of ControllerNetworkInterfaces (GLD houses) in the cni List
	 * @param ipv4Address
	 * @param latency
	 * @return objects a List of all AbstractNs3Objects created for this WiFi network
	 */
	public List<AbstractNs3Object> createWifi(int numApNodesTotal, int numStaNodesTotal, String ipv4Address, String latency) {
		
		int numAuctions = this.getAuctions().size();
		// Node #s passed in are total (for whole simulation), so calculate AP Nodes per Market
		int numApNodesPerAuction = numApNodesTotal / numAuctions;
		// Then calculate station Nodes per AP Node
		int numStaNodesPerAuction = numStaNodesTotal / numAuctions;
		int numStaNodesPerApNode = numStaNodesPerAuction / numApNodesPerAuction;
		
		// List of all AbstractNs3Objects
		List<AbstractNs3Object> objects = new ArrayList<AbstractNs3Object>();
		
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
		
		// Array of all WiFi station nodes passed to setupMarket(...) to add GLD markets to the nodes
		NodeContainer[] staNodeArray = new NodeContainer[numApNodesPerAuction];
		
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
		
		// CSMA helper for CSMA Bridge between AP WiFi nodes
		CsmaHelper csmaHelper = new CsmaHelper();
		csmaHelper.setName("csmaHelper");
		objects.add(csmaHelper);
		
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
		
		for (int j = 0 ; j < numAuctions; j++) {
			
			AuctionObject auction = this.auctions.get(j);
			
			NodeContainer backboneNodes = new NodeContainer();
			backboneNodes.setName("backboneNodes_" + j);
			backboneNodes.create(numApNodesPerAuction);
			stack.install(backboneNodes);
			objects.add(backboneNodes);
			
			NetDeviceContainer backboneDevices = new NetDeviceContainer();
			backboneDevices.setName("backboneDevices");
			csmaHelper.install(backboneNodes, backboneDevices);
			
			
			for (int i = 0; i < numApNodesPerAuction; i++) {
				String ssidBase = "wifi_" + auction.getNetworkInterfaceName() + "_" + j + "_" + i;
				Ssid ssid = new Ssid();
				ssid.setName("ssid_" + j + "_"  + i);
				ssid.setSsid(ssidBase);
				objects.add(ssid);
				
				NodeContainer staNodes = new NodeContainer();
				staNodes.setName("staNodes_" + j + "_"  + i);
				staNodes.create(numStaNodesPerApNode);
				objects.add(staNodes);
				
				// Add staNodes to NodeContainer[] for market creation
				//staNodeArray[i] = staNodes;
				//setupMarket(stack, addressHelper, staNodes);
				
				NetDeviceContainer staDevs = new NetDeviceContainer();
				staDevs.setName("staDev_" + j + "_"  + i);
				objects.add(staDevs);
				NetDeviceContainer apDevs = new NetDeviceContainer();
				apDevs.setName("apDev_" + j + "_"  + i);
				objects.add(apDevs);
				
				Ipv4InterfaceContainer staInterface = new Ipv4InterfaceContainer();
				staInterface.setName("staInterface_" + j + "_"  + i);
				objects.add(staInterface);
				Ipv4InterfaceContainer apInterface = new Ipv4InterfaceContainer();
				apInterface.setName("apInterface_" + j + "_"  + i);
				objects.add(apInterface);
				
				MobilityHelper mobility = new MobilityHelper();
				mobility.setName("mobility_" + j + "_"  + i);
				objects.add(mobility);
				
				BridgeHelper bridge = new BridgeHelper();
				bridge.setName("bridge_" + j + "_"  + i);
				objects.add(bridge);
				
				WifiHelper wifi = new WifiHelper();
				wifi.setName("wifi_" + j + "_"  + i);
				wifi.defaultParams();
				objects.add(wifi);
				
				NqosWifiMacHelper wifiMac = new NqosWifiMacHelper();
				wifiMac.setName("wifiMac_" + j + "_"  + i);
				wifiMac.defaultParams();
				objects.add(wifiMac);
				
				YansWifiChannelHelper wifiChannel = new YansWifiChannelHelper();
				wifiChannel.setName("wifiChannel_" + j + "_"  + i);
				wifiChannel.defaultParams();
				wifiPhy.setChannel(wifiChannel.create());
				objects.add(wifiChannel);
				
			    mobility.setPositionAllocator("ns3::GridPositionAllocator",
			    								wifiX, 0.0, 5.0, 5.0, 1,
			                                    "RowFirst");
			    // setup the AP
			    mobility.setMobilityModel("ns3::ConstantPositionMobilityModel");
			    mobility.install(backboneNodes, i);
			    wifiMac.setType("ns3::ApWifiMac", ssid);
			    // Install a WiFi device on the ith backbone Node to make the AP WiFi device
			    wifi.install(wifiPhy, wifiMac, backboneNodes, i, apDevs);
			    
			    // Install a CSMA Bridge on the AP WiFi device
			    NetDeviceContainer bridgeDevs = new NetDeviceContainer();
			    bridgeDevs.setName("bridgeDevs_" + j + "_"  + i);
			    objects.add(bridgeDevs);
			    
			    NetDeviceContainer temp = new NetDeviceContainer();
			    temp.setName("temp_" + j + "_"  + i);
			    temp.addDevices(apDevs);
			    temp.addDevice(backboneDevices, i);
			    objects.add(temp);
			    
			    bridge.install(backboneNodes, i, temp, bridgeDevs);
	
			    // assign AP IP address to bridge, not wifi
			    addressHelper.assign(bridgeDevs, apInterface);
	
			    // setup the WiFi station nodes
			    stack.install(staNodes);
			    mobility.setMobilityModel("ns3::RandomWalk2dMobilityModel",
			                               "Time", "2s", 
			                               "ns3::ConstantRandomVariable[Constant=1.0]",
			                               "RectangleValue(Rectangle(" + 
			                               wifiX + " , " + wifiX + "+5.0,0.0, (" 
			                               + numStaNodesPerApNode + "+1)*5.0))");
			    mobility.install(staNodes);
			    wifiMac.setType("ns3::StaWifiMac", ssid, false);
			    wifi.install(wifiPhy, wifiMac, staNodes, staDevs);
			    addressHelper.assign(staDevs, staInterface);
			    
			    // FIXME install market on 1st (0th) node of each staNodes NC
			    Node firstNode = staNodes.getNode(0);
			    firstNode.setAuction(auction);
			    gldNodes.addNodeContainer(staNodes);
			    
			    // Install ControllerNetworkInterface on each station Node
			    for (int k = 0; k < staNodes.getNumNodes(); k++) {
			    	// Assign the Controller at index k offset by the index of the Market (j) times the # of AP Nodes per Market
			    	staNodes.getNode(k).setController(this.getControllers().get(j*numApNodesPerAuction + k));
			    }
			    
			    
			    // save everything in containers.
	//		    staNodes.push_back(staNodes);
	//		    apDevices.push_back(apDevs);
	//		    apInterfaces.push_back(apInterface);
	//		    staDevices.push_back(staDevs);
	//		    staInterfaces.push_back(staInterface);
			    
			    String stuff = "\n\tstaNodeVector.push_back(staNodes_" + j + "_"  + i + ");\n" +
					    "\tapDeviceVector.push_back(apDev_" + j + "_"  + i + ");\n" +
					    "\tapInterfaceVector.push_back(apInterface_" + j + "_" + i +");\n" +
					    "\tstaDeviceVector.push_back(staDev_" + j + "_"  + i + ");\n" +
					    "\tstaInterfaceVector.push_back(staInterface_" + j + "_"  + i +");\n";
			    
			    addressHelper.appendPrintObj(stuff);
	
			    wifiX += 20.0;
			}
		}
		
		AbstractNs3Object ns3 = objects.get(0);
		
		String stuff = "\n   Address dest;\n" +
		  "   std::string protocol;\n" +
		 "   if (false)\n" +
		  "   {\n" +
		 "   cout << staInterfaceVector.size() << endl;\n" +
		 "   staInterfaceVector[0].GetAddress(0).Print(cout);\n" + 
		 "    dest = InetSocketAddress(staInterfaceVector.at(1).GetAddress(1), 1025);\n" +
		  "    protocol = \"ns3::UdpSocketFactory\";\n" +
		  "   }\n" +
		 "   else\n" +
		  "   {\n" +
		   "   PacketSocketAddress tmp;\n" +
		   "   tmp.SetSingleDevice (staDeviceVector[0].Get (0)->GetIfIndex ());\n" +
		   "   tmp.SetPhysicalAddress (staDeviceVector[0].Get (0)->GetAddress ());\n" +
		   "   tmp.SetProtocol (0x807);\n" +
		   "   dest = tmp;\n" +
		   "   protocol = \"ns3::PacketSocketFactory\";\n" +
		   "   }\n" + 

		  "   OnOffHelper onoff = OnOffHelper (protocol, dest);\n" +
		  "   onoff.SetConstantRate (DataRate (\"500kb/s\"));\n" +
		  "   ApplicationContainer apps = onoff.Install (staNodeVector[0].Get (0));\n" +
		  "   apps.Start (Seconds (0.5));\n" +
		  "   apps.Stop (Seconds (3.0));\n" +
		  "   cout << apDeviceVector[0].Get(0)->GetAddress().GetLength() << endl;\n" + // TODO debugging
		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", apDeviceVector[0]);\n" +
		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", apDeviceVector[1]);\n" +

		  "   if (true)\n" +
		    "   {\n" +
		      "   AsciiTraceHelper ascii;\n" +
		      "   MobilityHelper::EnableAsciiAll (ascii.CreateFileStream (\"wifi-wired-bridging.mob\"));\n" +
		    "   }\n";

		
		ns3.appendPrintObj(stuff + "\n");
		
		// Add a MarketNetworkInterface to each station Node; populate gldNodes, names, and marketToControllerMap
		//setupMarket(stack, addressHelper, staNodeArray, gldNodes, names, marketToControllerMap);
		
		// For FNCS
		this.addModule("fncs");
		this.addModule("fncsapplication-helper");
		
		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper();
		fncsHelper.setName("fncsHelper");
		
		ApplicationContainer fncsAps = new ApplicationContainer();
		fncsAps.setName("fncsAps");
		
		fncsHelper.setApps(names, gldNodes, marketToControllerMap, fncsAps);
		fncsAps.start(0.0);
		fncsAps.stop(259200.0);
		
		ns3.appendPrintObj("Simulator::Stop(Seconds(" + this.stopTime + "));\n");
		
		// This stuff doesn't seem to vary from sim to sim
		ns3.appendPrintObj("Simulator::Run();\n");
		ns3.appendPrintObj("Simulator::Destroy();\n");
		ns3.appendPrintObj("return 0;\n");
		
		return objects;
		
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * TODO need to evaluate how to create network with more than 1 type (WiFi <--> CSMA <--> LTE; etc)
	 * @return objects a List of all AbstractNs3Objects created in this network
	 */
	public List<AbstractNs3Object> build() {
		
		List<AbstractNs3Object> objects = new ArrayList<AbstractNs3Object>();
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		
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
				
				//TODO get dataRate and delay from user
				String dataRate = "10Mbps";
				String delay = "3ms";
				
				// CSMA channel/device setup
				CsmaHelper csmaHelper = new CsmaHelper();
				csmaHelper.setName("csmaHelper");
				csmaHelper.setChannelAttribute("DataRate", dataRate);
				csmaHelper.setChannelAttribute("Delay", delay);
				objects.add(csmaHelper);
				
				NodeContainer[] csmaNodeConts = new NodeContainer[numConts];
				NetDeviceContainer[] netDeviceConts = new NetDeviceContainer[numConts];
				
				for (int i = 0; i < numConts; i++) {
					NodeContainer csmaNodes = new NodeContainer();
					csmaNodes.setName("csmaNodes_" + i);
					csmaNodes.create(20); // Create 20 Nodes in this NodeContainer
					
					csmaNodeConts[i] = csmaNodes;
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
					
					netDeviceConts[i] = temp;
					objects.add(temp);
					
					// Adds each CSMA Node to the global list of nodes
					for (int j = 0; j < 21; j++) {
						nodes.add(csmaNodes.getNode(j));
					}
				}
				
				// Creates and connects the Market nodes to these backbone Nodes
				//setupMarket(stack, addresses, csmaNodeConts);
				
				break;
				
			case LTE:
				
				this.addModule("lte-module");
				this.addModule("mobility-module");
				
				LteHelper lte = new LteHelper();
				lte.setName("lte");
				objects.add(lte);
				
				List<NetDeviceContainer> lteDeviceContainers = new ArrayList<NetDeviceContainer>();
				
				NodeContainer enbNodes = new NodeContainer();
				enbNodes.setName("enbNodes");
				objects.add(enbNodes);

				
				int numUeNodes = 5; //TODO get real value of LTE user equipment nodes
				int numEnbNodes = 5; //TODO get real value of LTE base (towers) nodes
				
				NetDeviceContainer enbDevices = new NetDeviceContainer();
				enbDevices.setName("enbDevices");
				enbNodes.create(numEnbNodes);
				objects.add(enbDevices);
				
				MobilityHelper mobilityHelper = new MobilityHelper();
				mobilityHelper.setName("mobilityHelper");
				mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); // TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
				mobilityHelper.install(enbNodes);
				
				lte.installEnbDevice(enbNodes, enbDevices); // Install the LTE protocol stack on the eNB nodes
				
				lteDeviceContainers.add(enbDevices);
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
				for (int i = 0; i < numUeNodes; i++) {
					nodes.add(enbNodes.getNode(i));
				}
				
				// Setup QoS Class Indicator 
				Qci q = Qci.GBR_CONV_VOICE;
				q.setName("q");
				
				EpsBearer bearer = new EpsBearer();
				bearer.setName("bearer");
				bearer.setQci(q);
				objects.add(bearer);
				
				for (int i = 0; i < numEnbNodes; i++) {
					NodeContainer ueNodes = new NodeContainer();
					ueNodes.setName("ueNodes_" + i);
					ueNodes.create(numUeNodes);
					objects.add(ueNodes);
					
					mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); // TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
					mobilityHelper.install(ueNodes);
					
					NetDeviceContainer ueDevices = new NetDeviceContainer();
					ueDevices.setName("ueDevices_" + i);
					lte.installUeDevice(ueNodes, ueDevices); // Install the LTE protocol stack on the UE nodes
					lte.attach(ueDevices, enbDevices, i); // Attach the newly created UE devices to an eNB device
					lte.activateDataRadioBearer(ueDevices, bearer);
					objects.add(ueDevices);
					
					lteDeviceContainers.add(ueDevices);
					
					// Add all Nodes from this NodeContainer to Nodes global list of nodes
					for (int j = 0; j < numUeNodes; j++) {
						nodes.add(ueNodes.getNode(j));
					}
				}
				
				break;
				
			case P2P:
				
				this.addModule("point-to-point-module");
				
				NodeContainer p2pNodes = new NodeContainer();
				//TODO finish this
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
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
		
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		// TODO populate all GLD Nodes (houses & markets)
		
		StringVector<String> names = new StringVector<String>();
		names.setName("names");
		// TODO add all GLD Node names to names vector
		
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
