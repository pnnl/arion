/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/**
 * The WifiHelper is a helper class to simplify the setup of Wi-Fi networks.
 * 
 * @author happ546
 *
 */
public class WifiHelper extends NetworkHelper {
		
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
	 * Sets the algorithm used by ns-3 to control the data rate on this WiFi network
	 * @param remoteStationManager
	 */
	public void setRemoteStationManager(String remoteStationManager) {
		appendPrintInfo(this.getName() + "SetRemoteStationManager(\""
				+ remoteStationManager + "\");\n");
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
				".Install(" + phy.getName() + ", " + mac.getName() + ", "
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
				".Install(" + phy.getName() + ", " + mac.getName() + ", "
				+ sourceNodes.getName() + ".Get(" + index + "));\n");
	}
	
}
