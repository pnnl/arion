/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;

/**
 * The base class for the ns-3 channels; the connection between 
 * two NetDevices that allow them to communicate.
 * 
 * @author happ546
 *
 */
public class Channel extends AbstractNs3Object {
	
	/**
	 * The base IP address of this Channel (full 4 byte IPv4)
	 */
	private String addressBase;
	
	/**
	 * The Ns3Simulator that this Channel belongs to
	 */
	private Ns3Simulator owner;
	
	/**
	 * The Controllers attached to this Channel
	 */
	// TODO Look at combining controllers and auctions into List<NetworkCapable>
	private List<Controller> controllers;
	
	/**
	 * The Auctions attached to this Channel
	 */
	private List<AuctionObject> auctions;
	
	
	/**
	 * Creates a new Channel
	 */
	public Channel() {
		this.controllers = new ArrayList<>();
		this.auctions = new ArrayList<>();
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
	 * @return the auctions
	 */
	public List<AuctionObject> getAuctions() {
		return auctions;
	}

	/**
	 * @param auctions the auction to add to this Channel
	 */
	public void addAuction(AuctionObject auction) {
		this.auctions.add(auction);
	}

}
