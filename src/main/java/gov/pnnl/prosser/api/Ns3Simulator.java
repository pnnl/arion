/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Channel;
import gov.pnnl.prosser.api.ns3.obj.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.CsmaHelper;
import gov.pnnl.prosser.api.ns3.obj.InternetStackHelper;
import gov.pnnl.prosser.api.ns3.obj.Ipv4AddressHelper;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;
import gov.pnnl.prosser.api.ns3.obj.PointToPointChannel;
import gov.pnnl.prosser.api.ns3.obj.YansWifiChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator {
	
	private Ns3Network network;
	private List<Namespace> namespaces;
	private List<AbstractNs3Object> ns3Objects;
	
	/**
	 * Create a new Ns3Simulator
	 */
	public Ns3Simulator() {
		this.network = null;
		this.namespaces = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
	}

	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters
	 * @param numChannels
	 * @param addressBase
	 * @param addressMask 
	 * @param backboneDataRate 
	 * @param backboneDelay 
	 * @param stopTime 
	 */
	public void setup(final int numChannels, final String addressBase, 
						final String addressMask, final String backboneDataRate, 
						final String backboneDelay, final double stopTime) {
		
		network = new Ns3Network();
		
		namespaces.add(new Namespace("ns3"));
		namespaces.add(new Namespace("std"));
		
		network.setNumChannels(numChannels);

		network.setAddrBase(addressBase); // First 2 values of IPV4 address to use as base in IP addr distribution
		network.setAddrMask(addressMask);
		
		network.setBackboneDataRate(backboneDataRate);
		network.setBackboneDelay(backboneDelay);
		
		network.setStopTime(stopTime);
		
		network.setupFncsSimulator();
		
		//network.buildBackbone();
	}
	
	/**
	 * Creates the FncsApplicationHelper used to connect the GLD and ns-3 simulators
	 */
	public void setupFncsApplicationHelper() {
		network.setupFncsApplicationHelper();
	}
	
    /**
     * Gets the Modules used in this simulation
     * @return the list of Modules
     */
	public List<Module> getModules() {
		return network.getModules();
	}
	
    /**
     * Gets the Namespaces used in this simulation
     * @return the list of Namespaces
     */
	public List<Namespace> getNamespaces() {
		return this.namespaces;
	}
	
    /**
     * Gets the ns-3 objects used in this simulation
     * @return the list of AbstractNs3Objects
     */
	public List<AbstractNs3Object> getObjects() {		
		return ns3Objects;
	}

	/**
	 * @param auctions
	 */
	public void setAuctions(List<AuctionObject> auctions) {
		network.setAuctions(auctions);
	}

	/**
	 * @param controllers
	 */
	public void setControllers(List<Controller> controllers) {
		network.setControllers(controllers);
	}

	/**
	 * @param controllerPrefix
	 */
	public void setGldNodePrefix(String controllerPrefix) {
		network.setGldNodePrefix(controllerPrefix);
	}
	
	/**
	 * @param type
	 * @return an instance of the specified subclass of Channel
	 */
	public Channel channel(NetworkType type) {
		if (type.name().equalsIgnoreCase("csma")) {
			return new CsmaChannel();
		} else if (type.name().equalsIgnoreCase("p2p")) {
			return new PointToPointChannel();
		} else {
			return new YansWifiChannel();
		}
	}
	
	/**
	 * TODO get purpose of this method; add obj to list of GLD objects to pass to Ns3Network or setup a Channel and pass it to Ns3Network?
	 * @param obj the AbstractProsserObject to add to this Ns3Simulator
	 */
	public void add(AbstractGldObject obj) {
		//network.addGldObject(obj);
		
		//Channel c = obj.getChannel();
		//network.addChannel(c); // Do more Channel setup here or in Ns3Network?
	}

	/**
	 * @return the list of Channels for the Ns3Network
	 */
	public List<Channel> getChannels() {
		return this.network.getChannels();
	}

	/**
	 * Build the Controller and Auction nodes and connect to backbone network
	 */
	public void buildFrontend() {
		this.ns3Objects = network.buildFrontend();
	}
	

	// TODO discuss if these are right approach to making ns-3 part less wizardy
	
	
	/**
	 * @param name
	 * @return a new Pointer<Node>
	 */
	public Pointer<Node> nodePtr(String name) {
		return new Pointer<Node>(name, new Node());
	}


	/**
	 * @param name
	 * @return a new CsmaHelper
	 */
	public CsmaHelper csmaHelper(String name) {
		return new CsmaHelper(name);
	}


	/**
	 * @param name
	 * @return a new Pointer<CsmaChannel>
	 */
	public Pointer<CsmaChannel> csmaChannelPointer(String name) {
		return new Pointer<CsmaChannel>(name, new CsmaChannel());
	}


	/**
	 * @param name
	 * @return a new InternetStackHelper
	 */
	public InternetStackHelper internetStackHelper(String name) {
		return new InternetStackHelper(name);
	}


	/**
	 * @param name
	 * @return a new NetDeviceContainer
	 */
	public NetDeviceContainer netDeviceContainer(String name) {
		return new NetDeviceContainer(name);
	}


	/**
	 * @param name
	 * @return a new Ipv4AddressHelper
	 */
	public Ipv4AddressHelper ipv4AddressHelper(String name) {
		return new Ipv4AddressHelper(name);
	}


	/**
	 * @param name
	 * @return a new CsmaChannel
	 */
	public CsmaChannel csmaChannel(String name) {
		return new CsmaChannel(name);
	}
	
	/**
	 * @param name
	 */
	public void addName(String name) {
		this.network.addName(name);
	}


	/**
	 * @param type the NetworkType of the router to create
	 * @return router a new router of the specified type
	 */
	public Node createRouter(NetworkType type) {
		
		String netType = type.toString();
		long time = System.currentTimeMillis() % 1000;
		
		Node router = new Node("router_" + netType + "_" + time);
		
		InternetStackHelper stackHelper = new InternetStackHelper("internetStackHelper_" + time);		
		stackHelper.install(router);
		
		return router;
		
	}

	/**
	 * Add this Channel to the network
	 * @param channel
	 */
	public void addChannel(Channel channel) {
		this.network.addChannel(channel);
		this.ns3Objects.add(channel);
	}

}
