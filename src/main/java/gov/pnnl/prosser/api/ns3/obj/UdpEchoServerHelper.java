/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/** 
 * The UdpEchoServerHelper is a server helper class to simplify the setup of 
 * UDP (User Datagram Protocol) ping devices to test network connectivity.
 * 
 * @author happ546
 *
 */
public class UdpEchoServerHelper extends UdpEchoHelper {
	
	/**
	 * Creates a new UdpEchoServerHelper with the given name
	 * 
	 * @param name
	 */
	public UdpEchoServerHelper(String name) {
		this.setName(name);
		appendPrintInfo("UdpEchoServerHelper " + this.getName());
	}

}
