/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * The LteHelper class simplifies setting up an LTE network, handling 
 * the creation and attribute-setting of the wireless channel and connecting 
 * the UE (user equipment) end-point devices to the ENB backbone.
 * @author happ546
 *
 */
public class LteHelper extends AbstractNs3Object {
	
	/**
	 * Overrides the AbstractNs3Object setName method because pointers are needed in the c++ 
	 * output file to produce acceptable ns-3 code
	 * @param name the String to set the name of this LteHelper to
	 */
	@Override
	public void setName(String name) {
		this.setNameString(name);
		appendPrintObj("\n\tPtr<" + this.getClass().getSimpleName() + "> " + this.getName() 
						+ " = CreateObject<" + this.getClass().getSimpleName() + ">();\n");
	}

	/**
	 * Installs an LTE protocol stack on the Nodes in sourceNodes 
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to 
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices 
	 * 				after they have an LTE stack installed on them
	 * @param enbOrUe a string passed by the installUeDevice or installEnbDevice 
	 * 				indicating which device type is contained in the sourceNodes
	 */
	public void install(NodeContainer sourceNodes, 
				NetDeviceContainer destinationContainer, 
				String enbOrUe) {
		destinationContainer.addNodes(sourceNodes);
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + "->Install" 
						+ enbOrUe + "Device(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * Installs an LTE protocol stack on the eNB(s)
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to 
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices 
	 * 				after they have an LTE stack installed on them
	 */
	public void installEnbDevice(NodeContainer sourceNodes, 
				NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Enb");
	}
	
	/**
	 * Installs an LTE protocol stack on the UE(s)
	 * @param sourceNodes the NodeContainer holding the UE or ENB nodes to 
	 * 				install the LTE stack on
	 * @param destinationContainer the NetDeviceContainer to hold the LTE devices 
	 * 				after they have an LTE stack installed on them
	 */
	public void installUeDevice(NodeContainer sourceNodes, 
				NetDeviceContainer destinationContainer) {
		this.install(sourceNodes, destinationContainer, "Ue");
	}

	/**
	 * Attaches the UE (user equipment) nodes to the eNB (base station) nodes
	 * @param ueNodes the NetDeviceContainer of UE nodes
	 * @param enbNodes the NetDeviceContainer of eNB nodes
	 */
	public void attach(NetDeviceContainer ueDevices, 
				NetDeviceContainer enbDevices, int index) {
		//TODO any actual data storage here
		this.appendPrintObj(this.getName() + "->Attach(" + ueDevices.getName() + ", " 
							+ enbDevices.getName() + ".Get(" + index + "));\n");
	}
	
	/**
	 * Adds the given EpsBearer to the given NetDeviceContainer of LTE devices
	 * 
	 * @param ueDevices the NetDeviceContainer of LTE devices
	 * @param bearer the EpsBearer to attach to ueDevices
	 */
	public void activateDataRadioBearer(NetDeviceContainer ueDevices, EpsBearer bearer) {
		this.appendPrintObj(this.getName() + "->ActivateDataRadioBearer(" + ueDevices.getName() 
							+ ", " + bearer.getName() + ");\n");
	}
	

}
