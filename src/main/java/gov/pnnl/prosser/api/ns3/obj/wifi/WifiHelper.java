/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

/**
 * The WifiHelper is a helper class to simplify the setup of Wi-Fi networks.
 * 
 * @author happ546
 *
 */
public class WifiHelper extends AbstractNs3Object {
		
	/**
	 * Creates a new WifiHelper with the given name
	 * 
	 * @param name
	 */
	public WifiHelper(String name) {
		this.setName(name);
	}

	/**
	 * Sets the default parameters for this WifiHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = WifiHelper::Default();\n");
	}

	/**
	 *
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param node
	 * 			the ns-3 Node to install the Wi-Fi stack and devices on
	 * @param destinationContainer the NetDeviceContainer which will hold the installed
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac,
						Node node, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + ".Add (" + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ node.getPointerName() + "));\n");
	}

	/**
	 * 
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param sourceNodes the NodeContainer containing the Nodes on which to install
	 * 			the WiFi devices
	 * @param destinationContainer the NetDeviceContainer which will hold the installed 
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac,
			NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ sourceNodes.getName() + ");\n");
	}

	/**
	 * 
	 * @param phy the physical WiFi helper
	 * @param mac the MAC address WiFi helper
	 * @param sourceNodes the NodeContainer containing the Nodes on which to install
	 * 			the WiFi devices
	 * @param index the index of the Node in sourceNodes to install onto the destinationContainer
	 * @param destinationContainer the NetDeviceContainer which will hold the installed 
	 * 			WiFi devices
	 */
	public void install(YansWifiPhyHelper phy, NqosWifiMacHelper mac, 
			NodeContainer sourceNodes, int index, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() + " = " + this.getName() +
				".Install (" + phy.getName() + ", " + mac.getName() + ", "
				+ sourceNodes.getName() + ".Get (" + index + "));\n");
	}

	/**
	 * Sets the algorithm used by ns-3 to control the data rate on this WiFi network
	 * @param remoteStationManager
	 */
	// TODO allow for all types of station managers
	public void setRemoteStationManager(WifiPhyMode phyMode) {
		appendPrintInfo(this.getName() + ".SetRemoteStationManager (\"ns3::ConstantRateWifiManager\",\n" +
				"\"DataMode\",StringValue (\"" + phyMode.toString() + "\"),\n" +
				"\"ControlMode\",StringValue (\"" + phyMode.toString() + "\");\n");
	}

	/**
	 * Sets the Wi-Fi Physical layer standard to use for this Wi-Fi network
	 * @param std
	 * 		the WifiPhyStandard enum to set
	 */
	public void setStandard(WifiPhyStandard std) {
		appendPrintInfo(getName() + ".SetStandard (" + std.toString() + ");\n");
	}
	
}
