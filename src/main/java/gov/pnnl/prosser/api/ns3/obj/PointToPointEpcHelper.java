/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/**
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
		// Set the dereferencer operator appropriately for pointer encapsulation or not
		String deref = this.getPointer() ? "->" : ".";
		appendPrintObj(destination.getName() + " = " + this.getName() + deref + 
						"AssignUeIpv4Address(" + ueDevices.getName() + ");\n");
	}
	
	/**
	 * Gets the PGW (Packet data network GateWay) node of the LTE network 
	 * and puts it into the given Pointer
	 */
	public void getPgwNode(Pointer<PointToPointEpcHelper> ptr) {
		// Set the dereferencer operator appropriately for pointer encapsulation or not
		String deref = this.getPointer() ? "->" : ".";
		appendPrintObj(ptr.getName() + " = " + this.getName() + deref + "GetPgwNode();\n");
	}
	
	/**
	 * @return the Ipv4Address of the default gateway
	 */
	public Ipv4Address getUeDefaultGatewayAddress() {
		// Set the dereferencer operator appropriately for pointer encapsulation or not
		String deref = this.getPointer() ? "->" : ".";
		appendPrintObj(this.getName() + deref + "GetUeDefaultGatewayAddress();\n");
		return new Ipv4Address();
	}
	
}
