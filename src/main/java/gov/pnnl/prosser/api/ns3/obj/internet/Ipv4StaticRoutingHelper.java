/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;



import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Address;

/**
 * This class is used to help setup IPV4 static routing
 * 
 * @author happ546
 *
 */
public class Ipv4StaticRoutingHelper extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4StaticRoutingHelper with the given name
	 * 
	 * @param name
	 */
	public Ipv4StaticRoutingHelper(String name) {
		this.setName(name);
	}

	
	public Ipv4StaticRouting GetStaticRouting(Ipv4 ipv4)
	{
		/* 
	 	 * needs to print out:
	 	 *		Ptr<Ipv4StaticRouting> [unique_name_here]; 
	 	 *	 	[unique_name_here] = ipv4RoutingHelper.GetStaticRouting (ipv4);
	 	 *  
		 *  and needs to return the Ipv4StaticRouting object
		 */
		
		String varName = this.getClass().getSimpleName() + "_" + java.util.UUID.randomUUID().toString().replace("-", "");
		Ipv4StaticRouting temp = new Ipv4StaticRouting(varName); //prints constructor to file
		appendPrintInfo(varName + " = " + 
							this.getName() + ".GetStaticRouting(" + 
							ipv4.getName() + ");\n");
		
		return temp;
	}
	
	
	/**
	 * Adds a default route to the static routing table
	 * 
	 * @param addr the Ipv4Address to use
	 * @param interfaceIndex 
	 */
	public void setDefaultRoute(Ipv4Address addr, int interfaceIndex) {
		appendPrintInfo(this.getName() +
				".SetDefaultRoute(" + addr.getName() + ", " + interfaceIndex);
	}
	
	/**
	 * Add a multicast route to a node and net device
	 * 
	 * @param n 
	 * @param source
	 * @param group
	 * @param input
	 * @param output
	 
	public void AddMulticastRoute (Node n, Ipv4Address source, Ipv4Address group, NetDevice input, NetDeviceContainer output)
	{
		//void 	AddMulticastRoute (Ptr< Node > n, Ipv4Address source, Ipv4Address group, Ptr< NetDevice > input, NetDeviceContainer output)
		appendPrintInfo(this.getName() 
						+ ".AddMulticastRoute(" 
					    + n.getName() + ", " 
					    + source.getName() + ", "
					    + group.getName() + ", "
					    + input.getName() + ", "
					    + output.getName() + ");\n");
	}
	*/
	
	/**
	 * Add a default route to the static routing protocol to forward packets out a particular interface.
	 * Functionally equivalent to: route add 224.0.0.0 netmask 240.0.0.0 dev nd
	 * 
	 * @param n node
	 * @param nd device of the node to add default route
	
	public void SetDefaultMulticastRoute (Node n, NetDevice nd)
	{
		//void 	SetDefaultMulticastRoute (Ptr< Node > n, Ptr< NetDevice > nd)
		appendPrintInfo(this.getName() 
						+ ".SetDefaultMulticastRoute(" 
						+ n.getName() + ", " 
						+ nd.getName() + ");\n");
	} */
}













