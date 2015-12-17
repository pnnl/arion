/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * @author lalo609
 *
 */
public class Ipv4InterfaceAddress extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4InterfaceAddress with the given name
	 * 
	 * @param name
	 */
	public Ipv4InterfaceAddress(String name) {
		this.setName(name);
	}
	
	public Ipv4InterfaceAddress	(String name, Ipv4Address local, Ipv4Mask mask)
	{
		this.setName(name);
		this.setLocal(local);
		this.setMask(mask);
	}

	/**
	 * Set the network mask
	 * @param mask
	 */
	private void setMask(Ipv4Mask mask) 
	{
		appendPrintInfo(this.getName() + "->SetMask(" + mask.getName() + ");\n");
	}

	/**
	 * Set local address
	 * @param local
	 */
	private void setLocal(Ipv4Address local) 
	{
		appendPrintInfo(this.getName() + "->SetLocal(" + local.getName() + ");\n");		
	}

}
