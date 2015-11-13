/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Address;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Mask;

/**
 * This class is used to help setup IPV4 static routing
 * 
 * @author lalo609
 *
 */
public class Ipv4StaticRouting extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4StaticRouting with the given name
	 * 
	 * @param name
	 */
	public Ipv4StaticRouting(String name) {
		this.setNameString(name);
		this.getAsPointer();

	}

	/**
	 * Add a host route to the static routing table.
	 * 
	 * @param dest The Ipv4Address destination for this route.
	 * @param nextHop The Ipv4Address of the next hop in the route.
	 * @param interface The network interface index used to send packets to the destination
	 * @param metric Metric of route in case of multiple routes to same destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName() 
				+ "->AddHostRouteTo(" 
			    + dest.getName() + ", " 
		    	+ nextHop.getName() + ", " 
			    + interFace + ", "
			    + metric + ");\n");
	}
	
	/**
	 * Add a host route to the static routing table.
	 * 
	 * @param dest The Ipv4Address destination for this route.
	 * @param nextHop The Ipv4Address of the next hop in the route.
	 * @param interface The network interface index used to send packets to the destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, Ipv4Address nextHop, int interFace)
	{
		this.AddHostRouteTo(dest, nextHop, interFace, 0);
	}
	
	/**
	 * Add a host route to the static routing table.
	 * 
	 * @param dest The Ipv4Address destination for this route.
	 * @param interface The network interface index used to send packets to the destination
	 * @param metric Metric of route in case of multiple routes to same destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, int interFace, int metric)
	{
		appendPrintInfo(this.getName() 
				+ "->AddHostRouteTo(" 
			    + dest.getName() + ", " 
			    + interFace + ", "
			    + metric + ");\n");
	}
	
	/**
	 * Add a host route to the static routing table.
	 * 
	 * @param dest The Ipv4Address destination for this route.
	 * @param interface The network interface index used to send packets to the destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, int interFace)
	{
		this.AddHostRouteTo(dest, interFace, 0);
	}
		
	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName() 
				+ "->AddNetworkRouteTo(" 
			    + network.getName() + ", " 
			    + networkMask.getName() + ", "
			    + nextHop.getName() + ", "
	    		+ interFace + ", "
			    + metric + ");\n");
	}
	
	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, Ipv4Address nextHop, int interFace)
	{
		this.AddNetworkRouteTo(network, networkMask, nextHop, interFace, 0);
	}
	
	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, int interFace, int metric)
	{
		appendPrintInfo(this.getName() 
				+ "->AddNetworkRouteTo(" 
			    + network.getName() + ", " 
			    + networkMask.getName() + ", "
	    		+ interFace + ", "
			    + metric + ");\n");
	}
	
	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, int interFace)
	{
		this.AddNetworkRouteTo(network, networkMask, interFace, 0);
	}
	
	
	public void RemoveMulticastRoute (Ipv4Address origin, Ipv4Address group, int inputInterface)
	{
		appendPrintInfo(this.getName() 
				+ "->RemoveMulticastRoute(" 
			    + origin.getName() + ", " 
			    + group.getName() + ", "
			    + inputInterface + ");\n");
	}
 	
	public void RemoveMulticastRoute (int index)
	{
		appendPrintInfo(this.getName() + ".RemoveMulticastRoute(" + index + ");\n");
	}
 	
 
	public void RemoveRoute (int index) 
	{
		appendPrintInfo(this.getName() + ".RemoveRoute(" + index + ");\n");
	}

	public void SetDefaultMulticastRoute (int outputInterface)
	{
		appendPrintInfo(this.getName() + ".SetDefaultMulticastRoute(" + outputInterface + ");\n");
	}
	
	public void SetDefaultRoute (Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName() 
				+ "->SetDefaultRoute(" 
			    + nextHop.getName() + ", " 
			    + interFace + ", "
			    + metric + ");\n");
	}
	
	public void SetDefaultRoute (Ipv4Address nextHop, int interFace)
	{
		this.SetDefaultRoute(nextHop, interFace, 0);
	}
}













