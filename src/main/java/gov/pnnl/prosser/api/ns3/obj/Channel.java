/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The base class for the ns-3 channels; the connection between 
 * two NetDevices that allow them to communicate.
 * 
 * @author happ546
 *
 */
public class Channel extends AbstractNs3Object {

    private static AtomicInteger instanceCount = new AtomicInteger();

    // TODO Look at combining controllers and auctions into List<NetworkCapable>
	private List<Controller> controllers;
	private List<AuctionObject> auctions;
	private NetDeviceContainer devices;
	private NetworkType type;
	private String delay = "50us";
	private String dataRate = "100Mbps";
	private String ipBase = "";
	private String ipMask = "255.255.255.0";


	/**
	 * Creates a new Channel
	 */
	public Channel() {
		this.controllers = new ArrayList<>();
		this.auctions = new ArrayList<>();
		final int num = instanceCount.incrementAndGet();
		this.devices = new NetDeviceContainer("channelDeviceContainer" + num);
        

        ipBase += (num / 65534 + 1) +
                "." + (num / 254 + 1) +
                "." + (num % 254 + 1) +
                ".0";
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
	 * @return devices
	 * 			the NetDeviceContainer of devices attached to this Channel
	 */
	public NetDeviceContainer getDevices() {
		return devices;
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
	 * @return dataRate
     *          the data rate of this channel
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

	/**
	 * @return ipBase
	 * 			the IPv4 base address of this Channel
	 */
	public String getIPBase() {
		return ipBase;
	}

	/**
	 * Sets the base IPv4 address to assign to this Channel's devices
	 * @param base
	 */
	public void setIPBase(String base) {
		this.ipBase = base;
	}

    /**
	 * @return ipMask
	 * 			the IPv4 subnet mask of this Channel
	 */
	public String getIPMask() {
		return ipMask;
	}

	/**
	 * Sets the subnet mask for the IPv4 address to assign to this Channel's devices
	 * @param mask
	 */
	public void setIPMask(String mask) {
		this.ipMask = mask;
	}

	/**
	 * Assigns IPv4 addresses to all devices attached to this Channel
	 */
	public void assignIPAddresses() {
		Ipv4AddressHelper addressHelper = new Ipv4AddressHelper(getName() + "_addressHelper");
		addressHelper.setBase(getIPBase(), getIPMask());
		addressHelper.assign(devices);
	}
}
