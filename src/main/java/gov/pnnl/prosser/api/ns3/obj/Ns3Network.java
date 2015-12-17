/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.Vector;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.obj.lte.Qci;
import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.module.*;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaHelper;
import gov.pnnl.prosser.api.ns3.obj.internet.*;
import gov.pnnl.prosser.api.ns3.obj.lte.EpsBearer;
import gov.pnnl.prosser.api.ns3.obj.lte.LteHelper;
import gov.pnnl.prosser.api.ns3.obj.lte.PointToPointEpcHelper;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointChannel;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointHelper;
import gov.pnnl.prosser.api.ns3.obj.wifi.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class takes the basic network parameters from the user and constructs all of the 
 * ns-3 backbone for a functional network.
 * 
 * @author happ546
 *
 */
public class Ns3Network {
	
	private String gldNodePrefix;
	private int numBackboneNodes, numChannels;
	private double stopTime;
	private NodeContainer allNodes, fncsNodes;
	private InternetStackHelper iStackHelper;
	private List<Module> modules;
	private List<AuctionObject> auctions;
	private List<Controller> controllers;
	private List<AbstractNs3Object> ns3Objects;
	private List<Channel> channels, houseChannels, auctionChannels;
	private List<String> auctionNames;
	private Vector<String> allNames;

	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 * with default values for some parameters
	 */
	public Ns3Network() {
        this.numBackboneNodes = 0;
        this.numChannels = 0;
		this.stopTime = 31536000.0; // TODO get simulation end time (seconds) from GLD clock
		this.allNodes = new NodeContainer();
		this.fncsNodes = new NodeContainer();
		this.iStackHelper = new InternetStackHelper();
		this.modules = new ArrayList<>();
		this.auctions = new ArrayList<>();
		this.controllers = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
		this.channels = new ArrayList<>();
		this.houseChannels = new ArrayList<>();
		this.auctionChannels = new ArrayList<>();
		this.auctionNames = new ArrayList<>();
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
	 * @return the list of Channels
	 */
	public List<Channel> getChannels() {
		return this.channels;
	}

	/**
	 * @return a List of house Channels for this Ns3Network
	 */
	public List<Channel> getHouseChannels() {
		return houseChannels;
	}

	/**
	 * @return a List of auction Channels for this Ns3Network
	 */
	public List<Channel> getAuctionChannels() {
		return auctionChannels;
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
	 *
	 * @param node
	 * 		the Node to add to the NodeContainer needed by
	 * 			the FNCSApplicationHelper
	 */
	public void addFncsNode(Node node) {
		node.appendPrintInfo(fncsNodes.getName() + ".Add (" + node.getName() + ");");
	}

	/**
	 *
	 * @param auctionChannel
	 * 		the Channel to add to the list of auction Channels
	 */
	public void addAuctionChannel(Channel auctionChannel) {
		auctionChannels.add(auctionChannel);
		addChannel(auctionChannel);
	}

	/**
	 * @param name
	 * 		the String name of the market network interface
	 * 		prefix to add to this list of auction prefixes
	 */
	public void addAuctionName(String name) {
		auctionNames.add(name);
	}

	/**
	 * @return the List of String Auction names
	 */
	public List<String> getAuctionNames() {
		return auctionNames;
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
		return numBackboneNodes;
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
		channels.add(chan);
	}

	/**
	 * Adds a GLD house channel to the network
	 * @param houseChannel the house Channel to add to this
	 *                     Ns3Network
	 */
	public void addHouseChannel(Channel houseChannel) {
		houseChannels.add(houseChannel);
		addChannel(houseChannel);
	}
	
	/**
	 * @param i
	 * 			integer index
	 * @return the Channel at index i
	 */
	private Channel getChannel(int i) {
		return channels.get(i);
	}

	/**
	 * Sets up a FNCSApplicationHelper and ApplicationContainer for the FNCS simulator and
	 * starts the ns-3 simulator
	 */
	public void setupFncsApplicationHelper() {


		// Adds the market (auction) network interface prefix name to the list of
		// all names for the FNCSApplicationHelper
		addAuctionNames();
		// Adds and prints all controller network interface names to list of all allNames
		addControllerNames();

		// A C++ map<string, string> construct mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<>("marketToControllerMap");
		for (Channel chan : auctionChannels) {
			AuctionObject auction = chan.getAuctions().get(0);
			// Maps the Auction NetworkInterfaceName to the GldNodePrefix
			marketToControllerMap.put(auction.getNetworkInterfaceName(), auction.getFncsControllerPrefix());
		}

		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper("fncsHelper");
		fncsHelper.setApps(allNames, fncsNodes, marketToControllerMap, stopTime);

		// Run Simulator then clean up after it's done (according to FncsAps.stop(...))
		fncsHelper.appendPrintInfo("Simulator::Run();\n");
		fncsHelper.appendPrintInfo("Simulator::Destroy();\n");
		fncsHelper.appendPrintInfo("return 0;\n");
	}

	/**
	 * Adds the market network interface name for each auction in
	 * this network to the list of allNames needed for the
	 * FncsApplicationHelper to setup the communication between the
	 * GLD simulator(s) and the ns-3 simulator(s).
	 */
	public void addAuctionNames() {
		for (String name : getAuctionNames()) {
			allNames.addName(name + "NI");
		}
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
	 * Creates a global routing helper to populate the routing tables
	 * of all Routers on this network
	 */
	public void setupGlobalRouting() {
		Ipv4GlobalRoutingHelper globalRtHelper =
				new Ipv4GlobalRoutingHelper("globalRoutingHelper");
		globalRtHelper.populate();
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
	 * Creates the FNCS simulator for ns-3 to use
	 */
	public void setupFncsSimulator() {

        // Setup FNCS simulator
		FncsSimulator fncsSim = new FncsSimulator("fncsSim");
		
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
		fncsNodes = new NodeContainer("fncsNodes");
		// Instantiates global Vector allNames for use by FNCSApplicationHelper
		allNames = new Vector<>("allNames", String.class);
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
	    	//gldNodes.addNode(nodeCont, i);
		}
		
		return names;
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
		//this.setBackboneType(NetworkType.P2P);
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
		//mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel");
		
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
			//mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel");
			mobilityHelper.install(enbNodes);
			
			// Install the LTE protocol stack on the eNB allNodes; not for EPC
			//lteHelper.installEnbDevice(enbNodes, enbDevices); 
			
			lteDeviceContainers.add(enbDevices);
			
			// Create a point-to-point network between the enbNodes
			//createBackbone(p2pHelper, enbNodes, enbDevices, i);
			
			//ipv4AddrHelper.setBase(getAddrBase() + i + ".0", getAddrMask());
			// Assign IP addresses to NetDevices in enbDevices
			ipv4AddrHelper.assign(enbDevices, ipv4Interfaces);
			
			for (int j = 0; j < numEnbNodesPerAuction; j++) {
				
				// Add all Nodes from this NodeContainer to Nodes global list of allNodes
				addNode(enbNodes.getNodeNoPrint(j));
				
				NodeContainer ueNodes = new NodeContainer("ueNodes_" + j);
				ueNodes.create(numUeNodesPerEnbNode);
				ns3Objects.add(ueNodes);
				
				// TODO ConstantPositionMobilityModel sets all allNodes at origin (0,0,0)
				//mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel");
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
}
