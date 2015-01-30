/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class LteHelper extends AbstractNs3Object {

	/**
	 * Installs an LTE protocol stack on the given NetDeviceContainer devices
	 * @param enbNodes
	 */
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer, String enbOrUe) {
		destinationContainer.addNodes(sourceNodes);
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + ".Install" + enbOrUe + "Device(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * Install an LTE protocol stack on the eNB(s)
	 * @param sourceNodes
	 * @param destinationContainer
	 */
	public void installEnbDevice(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Enb");
	}
	
	/**
	 * Install an LTE protocol stack on the UE(s)
	 * @param sourceNodes
	 * @param destinationContainer
	 */
	public void installUeDevice(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Ue");
	}

	/**
	 * Attaches the UE (user equipment) nodes to the eNB (base station) nodes
	 * @param ueNodes the NetDeviceContainer of UE nodes
	 * @param enbNodes the NetDeviceContainer of eNB nodes
	 */
	public void attach(NetDeviceContainer ueDevices, NetDeviceContainer enbDevices, int index) {
		//TODO any actual data storage here
		this.appendPrintObj(this.getName() + ".Attach(" + ueDevices.getName() + ", " + enbDevices.getName() + ".Get(" + index + "));\n");
	}	
	

}
