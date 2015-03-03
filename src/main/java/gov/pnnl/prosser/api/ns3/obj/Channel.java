/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.Ns3Simulator;
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
	 * The Ns3Simulator that this Channel belongs to
	 */
	private Ns3Simulator owner;
	
	/**
	 * The Controllers attached to this Channel
	 */
	// TODO Should we just have list of Controllers? Since controllers contain Auction object, it would work
	private List<NetworkCapable> controllers;
	
	
	/**
	 * Creates a new Channel
	 */
	public Channel() {
		this.controllers = new ArrayList<>();
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
	public List<NetworkCapable> getControllers() {
		return controllers;
	}

	/**
	 * @param gldObj the gldObj to add to this Channel
	 */
	public void add(NetworkCapable gldObj) {
		this.controllers.add(gldObj);
	}

}
