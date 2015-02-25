/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * The base class for the ns-3 channels; the connection between 
 * two NetDevices that allow them to communicate.
 * 
 * @author happ546
 *
 */
public class Channel extends AbstractNs3Object {
	
	/**
	 * The Ns3Simulator that contains the GLD object this 
	 * Channel is being attached to.
	 */
	private Ns3Simulator owner;
	
	/**
	 * The AbstractProsserObject that this Channel is 
	 * attached to.
	 */
	private AbstractProsserObject gldObj;

	
	/**
	 * @return the owner
	 */
	public Ns3Simulator getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Ns3Simulator owner) {
		this.owner = owner;
	}

	/**
	 * @return the gldObj
	 */
	public AbstractProsserObject getGldObj() {
		return gldObj;
	}

	/**
	 * @param gldObj the gldObj to set
	 */
	public void setGldObj(AbstractProsserObject gldObj) {
		this.gldObj = gldObj;
	}
	
}
