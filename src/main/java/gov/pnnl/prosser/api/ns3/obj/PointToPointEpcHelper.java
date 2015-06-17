/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * The PointToPointEpcHelper is a specialized helper to facilitate easier setup 
 * of LTE networks with EPC (Evolved Packet Core; allows IP-type functionality).
 * 
 * @author happ546
 *
 */
public class PointToPointEpcHelper extends NetworkHelper {

	/**
	 * @param ueDevices the UE LTE devices to assign IPv4 addresses to
	 * @param destination the Ipv4AddressContainer to hold the created IPv4 interfaces
	 */
	public void assignUeIpv4Address(NetDeviceContainer ueDevices, 
									Ipv4InterfaceContainer destination) {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not
		
		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(destination.getName() + " = " + this.getName() + deref +
		//				"AssignUeIpv4Address(" + ueDevices.getName() + ");\n");
	}
	
	/**
	 * Gets the PGW (Packet data network GateWay) node of the LTE network 
	 * and puts it into the given Pointer Node
	 */
	public void getPgwNode(Pointer<Node> node) {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not
		
		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(node.getName() + " = " + this.getName() + deref + "GetPgwNode();\n");
	}
	
	/**
	 * @return the Ipv4Address of the default gateway
	 */
	public Ipv4Address getUeDefaultGatewayAddress() {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not
		
		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(this.getName() + deref + "GetUeDefaultGatewayAddress();\n");
		return new Ipv4Address(null);
	}
	
}
