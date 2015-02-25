/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Channel;
import gov.pnnl.prosser.api.ns3.obj.CsmaChannel;
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
	private List<Module> modules;
	private List<Namespace> namespaces;
	private List<AbstractNs3Object> ns3Objects;
	private List<AbstractProsserObject> gldObjects;

	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters TODO supply user params
	 */
	public void setup() {
		
		// User inputs basic params (Network type, addr base & mask, # of nodes [or infer from gldList?])
		network = new Ns3Network();
		network.setAddrBase("10.1."); // First 2 values of IPV4 address to use as base in IP addr distribution
		network.setAddrMask("255.255.255.0");
		
		network.setBackboneType(NetworkType.CSMA);
		network.setAuctionType(NetworkType.WIFI);
//		network.setBackboneType(NetworkType.CSMA);
//		network.setAuctionType(NetworkType.LTE);
		
		
		//TODO stop time from user
		network.setStopTime(10.0);
	}
	
    /**
     * Gets the Modules used in this simulation
     */
	public List<Module> getModules() {
		return network.getModules();
	}
	
    /**
     * Gets the Namespaces used in this simulation
     */
	public List<Namespace> getNamespaces() {
		List<Namespace> namespaces = new ArrayList<Namespace>();
		namespaces.add(new Namespace("ns3"));
		
		return namespaces;
	}
	
    /**
     * Gets the ns-3 objects used in this simulation
     */
	public List<AbstractNs3Object> getObjects() {
		
		final List<AbstractNs3Object> objects = network.createWifi("10.0.0.0", "4ms");//network.createLte();
		
		// List of ns-3 Nodes to keep track of specific Nodes
		List<Node> nodes = network.getNodes();
		
		return objects;
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
	public void add(AbstractProsserObject obj) {
		//network.addGldObject(obj);
		
		//Channel c = obj.getChannel();
		//network.addChannel(c); // Do more Channel setup here or in Ns3Network?
	}

}
