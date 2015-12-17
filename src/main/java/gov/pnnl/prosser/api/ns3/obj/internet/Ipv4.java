
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NetDevice;

/**
 * 
 * 
 * @author lalo609
 *
 */
public class Ipv4 extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4 encapsulated in a smart pointer
	 * 
	 * @param name
	 */
	public Ipv4(String name) {
		this.setNameString(name);
		this.getAsPointer();
	}
	
	/**
	 * 
	 * @param device the device to add to the list of Ipv4 interfaces which can be used as output interfaces during packet forwarding.
	 * @return the name of the c-variable which will contain the index of the Ipv4 interface added.
	 */
	public String addInterface(NetDevice device)
	{
		//int32_t ifIndexA = ipv4A->AddInterface (devsA.Get (0));
		
		String varName = "int32_t_" + java.util.UUID.randomUUID().toString().replace("-", "");
		appendPrintInfo("int32_t " + varName + " = " +
							this.getName() + "->AddInterface(" + device.getName() + ");\n");
		return varName;
	}
	
	
	/**
	 * 
	 * @param interfaceIndex Interface number of an Ipv4 interface
	 * @param adddress Ipv4InterfaceAddress address to associate with the underlying Ipv4 interface
	 */
	public void addAddress(String interfaceIndex, Ipv4InterfaceAddress address)
	{
		appendPrintInfo(this.getName() + "->AddAddress(" 
									   + interfaceIndex + ", " 
									   + address.getName() 
									   + ");\n");
	}
	
	public void setMetric(String interFace, int metric)
	{
		appendPrintInfo(this.getName() + "->SetMetric(" 
				   + interFace + ", " 
				   + metric    + ");\n");
	}
	
	public void setUp(String interFace)
	{
		appendPrintInfo(this.getName() + "->SetMetric(" + interFace + ");\n");
	}
}
