/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * The base class for the ns-3 channels; the connection between 
 * two NetDevices that allow them to communicate.
 * 
 * @author happ546
 *
 */
public class Channel extends AbstractNs3Object {

	private String addressBase;
	private Map<String, String> attributes;
	private Ns3Simulator owner;
	// TODO Look at combining controllers and auctions into List<NetworkCapable>
	private List<Controller> controllers;
	private List<AuctionObject> auctions;
	private NetworkType type;
	private String delay;
	private String dataRate;
	private NetDeviceContainer devices;

	
	/**
	 * Creates a new Channel
	 */
	public Channel() {
		this.controllers = new ArrayList<>();
		this.auctions = new ArrayList<>();
		this.attributes = new HashMap<>();
		this.devices = new NetDeviceContainer("channelDeviceContainer" + System.nanoTime() % 1000000);
	}
	
	/**
	 * @return the addressBase
	 */
	public String getAddressBase() {
		return addressBase;
	}

	/**
	 * @param addressBase the addressBase to set
	 */
	public void setAddressBase(String addressBase) {
		this.addressBase = addressBase;
	}
	
	/**
	 * @param key String
	 * @return the attribute value String of the given key
	 */
	public String getAttribute(String key) {
		return this.attributes.get(key);
	}
	
	/**
	 * Sets the specified attribute of this channel to the given value
	 * @param attribute
	 * @param value
	 */
	private void setAttribute(String attribute, String value) {
		this.attributes.put(attribute, value);
		appendPrintObj(this.getName() + ".SetAttribute(\"" + 
				attribute + "\", StringValue(\"" + value + "\"));\n");
	}

	/**
	 * @return the Ns3Simulator parent simulator of this Channel
	 */
	public Ns3Simulator getOwner() {
		return owner;
	}

	/**
	 * @param owner the Ns3Simulator owner to set
	 */
	public void setOwner(Ns3Simulator owner) {
		this.owner = owner;
	}

	/**
	 * @return the List of Controllers attached to this Channel
	 */
	public List<Controller> getControllers() {
		return controllers;
	}

	/**
	 * @param controller the Controller to add to this Channel
	 */
	public void addController(Controller controller) {
		this.controllers.add(controller);
	}

	/**
	 * @return the List of AuctionObjects connected to this Channel
	 */
	public List<AuctionObject> getAuctions() {
		return auctions;
	}

	/**
	 * @param auction the AuctionObject to add to this Channel
	 */
	public void addAuction(AuctionObject auction) {
		this.auctions.add(auction);
	}
	
	/**
	 * @return the type
	 */
	public NetworkType getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(NetworkType type) {
		this.type = type;
	}

	/**
	 * @return the delay of this channel
	 */
	public String getDelay() {
		return delay;
	}

	/**
	 * @param delay
	 */
	public void setDelay(String delay) {
		this.delay = delay;
	}

	/**
	 * @return the data rate of this channel
	 */
	public String getDataRate() {
		return dataRate;
	}

	/**
	 * @param dataRate
	 */
	public void setDataRate(String dataRate) {
		this.dataRate = dataRate;
	}

	// TODO IP Addressing
	public void assignIPAddresses(Ipv4AddressHelper addressHelper) {
		addressHelper.assign(devices);

		// Increments the IP address base for a new network for the next Channel
		addressHelper.newNetwork();
	}

	public void addDevices(NetDeviceContainer tempDev) {
		devices.addDevices(tempDev);
	}
}
