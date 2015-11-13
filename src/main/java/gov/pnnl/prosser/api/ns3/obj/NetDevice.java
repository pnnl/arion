/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This is a skeleton class for the NetDevice base class.
 * Every networking device (Wi-Fi, CSMA, p2p, etc.) in ns-3 is 
 * built off this class.
 * 
 * @author happ546
 *
 */
public class NetDevice extends AbstractNs3Object 
{
	public NetDevice(String name) 
	{
		this.setNameString(name);
		this.getAsPointer();
	}
	
	public NetDevice() {}
	
	/**
	 *  returns a string with the c++ variable name that represents the uint32_t interface index for the NetDevice
	 */
	public String getInterface()
	{
		String varName =  "uint32_t_" + java.util.UUID.randomUUID().toString().replace("-", "");
		appendPrintInfo("uint32_t " + varName + " = " +	this.getName() + "->GetIfIndex();\n");
		return varName;
	}
}
