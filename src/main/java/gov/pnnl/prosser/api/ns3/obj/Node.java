/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;

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
	 * Create a nameless Node.
	 */
	public Node() 
	{
		super();
	}
	
	/**
	 * Creates a new Node encapsulated in a smart pointer
	 * @param name
	 */
	public Node(String name) 
	{
		setNameString(name);
		getAsPointer();
	}

	/**
	 *
	 * @return name
	 * 			the name of this Node
	 */
	@Override
	public String getName() 
	{
		return this.getPointerName();
	}
	
	/**
	 * 
	 * @param device
	 */
	
	public void addDevice(NetDevice device)
	{
		appendPrintInfo(this.getName() + "->AddDevice(" + device.getName() + ");\n");
	}
	
	
	public Ipv4 getObjectIpv4()
	{
		
		String varName = "Ipv4_" + java.util.UUID.randomUUID().toString().replace("-", "");
		Ipv4 temp = new Ipv4(varName); //prints constructor to file
		appendPrintInfo("Ptr<Ipv4> " + varName + " = " +
							this.getName() + "->GetObject<Ipv4> ();\n");
		return temp;
	}
	
	/**
	 * Retrieve the index-th NetDevice associated to this node.
	 * 
	 * @param index	the index of the requested NetDevice
	 * @return the requested NetDevice.
	 */
	public NetDevice getDevice(int index)
	{
		String varName = "NetDevice_" + java.util.UUID.randomUUID().toString().replace("-", "");
		NetDevice temp = new NetDevice(varName);
		
		
		appendPrintInfo(varName + " = " + this.getName() + "->GetDevice(" + index + ");\n");
		return temp;
	}
	
}
