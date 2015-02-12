/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4AddressHelper extends AbstractNs3Object {
	
	/**
	 * 
	 * @param ipBase the IPv4 address base for DHCP address assignment to begin at
	 * @param mask the subnet mask used for the IP addresses set by this Ipv4AddressHelper
	 */
	public void setBase(String ipBase, String mask) {
		appendPrintObj(this.getName() + ".SetBase(\"" + ipBase + 
						"\", \"" + mask + "\");\n");
	}

	/**
	 * 
	 * @param devices the net devices to assign IP addresses to
	 */
	public void assign(NetDeviceContainer devices) {
		appendPrintObj(this.getName() + ".Assign(" + devices.getName() + ");\n");
	}

	/**
	 * 
	 * @param devices the net devices to assign IP addresses to
	 * @param destinationInterface the Ipv4InterfaceContainer to hold the IPv4 net devices
	 */
	public void assign(NetDeviceContainer devices, Ipv4InterfaceContainer destinationInterface) {
		appendPrintObj(destinationInterface.getName() + " = " + this.getName() + 
						".Assign(" + devices.getName() + ");\n");
	}

}
