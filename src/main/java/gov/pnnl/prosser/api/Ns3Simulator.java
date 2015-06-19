/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator {
	
	private Ns3Network network;
	private String name;
	private List<Namespace> namespaces;
	private static List<AbstractNs3Object> ns3Objects;
	
	/**
	 * Create a new Ns3Simulator
	 */
	public Ns3Simulator(String name) {
		this.network = null;
		this.name = name;
		this.namespaces = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
	}

	/**
	 *
	 * @param object the AbstractNs3Object to add to this Ns3Simulator's
	 *               list of ns-3 objects
	 */
	public static void addObject(AbstractNs3Object object) {
		ns3Objects.add(object);
	}

	/**
	 *
	 * @return name
	 * 				the name of this Ns3Simulator
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters
	 * @param marketNIPrefix
     *          the GridlabD market network interface prefix
	 */
	public void setup(final String marketNIPrefix) {
		
		network = new Ns3Network();
		
		namespaces.add(new Namespace("ns3"));
		namespaces.add(new Namespace("std"));

		network.setupFncsSimulator(marketNIPrefix);
		
	}

	/**
	 * Creates the FncsApplicationHelper used to connect the GLD and ns-3 simulators
	 */
	public void setupFncsApplicationHelper() {
		network.setupFncsApplicationHelper();
	}


    /**
     * Populates global routing tables on each Router in the network
     */
    public void setupGlobalRouting() {
        network.setupGlobalRouting();
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
	 * @param type
	 * @return an instance of the specified subclass of Channel
	 */
	public Channel channel(NetworkType type) {
		if (type.equals(NetworkType.CSMA)) {
			return new CsmaChannel();
		} else if (type.equals(NetworkType.P2P)) {
			return new PointToPointChannel();
		} else {
			return new YansWifiChannel();
		}
	}

	/**
	 * @return the list of Channels for the Ns3Network
	 */
	public List<Channel> getChannels() {
		return this.network.getChannels();
	}

	/**
	 * @return the list of house Channels for the Ns3Network
	 */
	public List<Channel> getHouseChannels() {
		return this.network.getHouseChannels();
	}

	/**
	 * Adds this Channel to the network
	 * @param channel
	 */
	public void addChannel(Channel channel) {
		this.network.addChannel(channel);
	}

	/**
	 * Adds this House Channel to the network
	 * @param houseChannel
	 */
	public void addHouseChannel(Channel houseChannel) {
		this.network.addHouseChannel(houseChannel);
	}

    /**
     * Adds the GLD Controller names to the ns3 global list of names
     * for the FncsApplicationHelper setup
     */
    public void addControllerNames() { this.network.addControllerNames();	}

    /**
     * Build the Controller and Auction nodes and connect to backbone network
     */
    public void buildFrontend() {
        this.ns3Objects = network.buildFrontend();
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
}
