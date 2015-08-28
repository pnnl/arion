/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is used to help assign unique IP addresses to IP Net Devices
 * 
 * @author happ546
 *
 */
public class Ipv4AddressHelper extends AbstractNs3Object {
	
	/**
	 * Creates a new Ipv4AddressHelper with the given name
	 * 
	 * @param name
	 */
	public Ipv4AddressHelper(String name) {
		this.setName(name);
	}

	/**
	 * 
	 * @param ipBase the IPv4 address base for DHCP address assignment
	 * @param mask the subnet mask used for DHCP address assignment
	 */
	public void setBase(String ipBase, String mask) {
		appendPrintInfo(this.getName() + ".SetBase (\"" + ipBase +
				"\", \"" + mask + "\");\n");
	}

	/**
	 * 
	 * @param devices the net devices to assign IP addresses to
	 */
	public void assign(NetDeviceContainer devices) {
		long time = System.nanoTime() % 100000;
		String contName = "ipv4IntCont_" + this.getName() + time;
		appendPrintInfo("Ipv4InterfaceContainer " + contName +
				" = " + this.getName() + ".Assign (" + devices.getName() + ");\n");

		// TODO DEBUGGING prints IP addresses to console
/*
		String[] arr = devices.getName().split("_");
		String routerName = (arr.length == 4) ? arr[0] + arr[2] : arr[0];
		appendPrintInfo("for (int i = 0; i < " + contName + ".GetN (); i++) { \n" +
				"    cout << \"" + routerName + ": \" << " + contName + ".GetAddress (i) << endl;\n" +
				"  }\n\n");
*/
	}

	/**
	 * 
	 * @param devices the net devices to assign IP addresses to
	 * @param destinationInterface the Ipv4InterfaceContainer to 
	 * 			hold the IPv4 net devices
	 */
	public void assign(NetDeviceContainer devices, 
			Ipv4InterfaceContainer destinationInterface) {
		appendPrintInfo(destinationInterface.getName() + " = " + this.getName() +
				".Assign (" + devices.getName() + ");\n");
	}

	/**
	 * Increments the network number and resets the IP address counter
	 * to the preset base value.
	 */
	public void newNetwork() {
		appendPrintInfo(this.getName() + ".NewNetwork ();\n");
	}
}
