/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.gld.obj.MarketNetworkInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The ns-3 Node is the basic building block of the network simulation
 * Devices can be installed on them to provide various communication methods and 
 * applications can be installed on the devices to provide various communication abilities 
 * (for example: point-to-point, WiFi, Internet protocols, etc.)
 * 
 * @author happ546
 *
 */
public class Node extends AbstractNs3Object {
	
	/**
	 * A list of the applications installed on this Node
	 */
	private List<Application> applications;
	/**
	 * A list of the network devices installed on this Node
	 */
	private List<NetDevice> devices;
	/**
	 * The ControllerNetworkInterface installed on this Node (if present)
	 * The CNI indicates this Node is connected to (and represents) a GLD House object
	 */
	private ControllerNetworkInterface controllerNetworkInterface;
	/**
	 * The MarketNetworkInterface installed on this Node (if present)
	 * The MNI indicates this Node is attached to a GLD Market
	 */
	private MarketNetworkInterface marketNetworkInterface;
	
	private Controller controller;
	private AuctionObject auction;
	
	/**
	 * Creates a new, empty Node
	 */
	public Node() {
		this.applications = new ArrayList<Application>();
		this.devices = new ArrayList<NetDevice>();
		this.controllerNetworkInterface = null;
		this.marketNetworkInterface = null;
	}
	
	/**
	 * 
	 * @param app the Application to install on this Node
	 */
	public void addApplication(Application app) {
		applications.add(app);
	}
	
	/**
	 * @return the list of installed applications on this Node
	 */
	public List<Application> getApplications() {
		return applications;
	}

	/**
	 * 
	 * @param device the NetDevice to install on this Node
	 */
	public void addDevice(NetDevice device) {
		devices.add(device);
	}
	
	/**
	 * @return the list of devices for this Node
	 */
	public List<NetDevice> getDevices() {
		return devices;
	}

	/**
	 * @return the controllerNetworkInterface
	 */
	public ControllerNetworkInterface getControllerNetworkInterface() {
		return controllerNetworkInterface;
	}

	/**
	 * @param controllerNetworkInterface the controllerNetworkInterface to set
	 */
	public void setControllerNetworkInterface(ControllerNetworkInterface controllerNetworkInterface) {
		this.controllerNetworkInterface = controllerNetworkInterface;
	}
	
	/**
	 * @return the marketNetworkInterface attached to this Node
	 */
	public MarketNetworkInterface getMarketNetworkInterface() {
		return marketNetworkInterface;
	}

	/**
	 * @param marketNetworkInterface the marketNetworkInterface to attach to this Node
	 */
	public void setMarketNetworkInterface(
			MarketNetworkInterface marketNetworkInterface) {
		this.marketNetworkInterface = marketNetworkInterface;
	}
	
	/**
	 * @return this Node's Controller
	 */
	public Controller getController() {
		return this.controller;
	}

	/**
	 * @param controller the Controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * @return this Node's AuctionObject
	 */
	public AuctionObject getAuction() {
		return this.auction;
	}

	/**
	 * @param auction the Auction to set
	 */
	public void setAuction(AuctionObject auction) {
		this.auction = auction;
	}
	
}
