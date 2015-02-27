/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.Ns3Simulator;

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
	 * The AbstractProsserObjects (GridLab-D/GLD objects) attached to this Channel
	 */
	private List<AbstractProsserObject> gldObjs;
	
	
	/**
	 * Creates a new Channel
	 */
	public Channel() {
		this.gldObjs = new ArrayList<>();
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
	 * @return the List of GLD objects attached to this Channel
	 */
	public List<AbstractProsserObject> getGldObjs() {
		return gldObjs;
	}

	/**
	 * @param gldObj the gldObj to add to this Channel
	 */
	public void addGldObj(AbstractProsserObject gldObj) {
		this.gldObjs.add(gldObj);
	}

}
