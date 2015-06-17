/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/** 
 * The UdpEchoHelper is a base helper class to simplify the setup of 
 * UDP (User Datagram Protocol) ping devices to test network connectivity.
 * 
 * @author happ546
 *
 */
public class UdpEchoHelper extends AbstractNs3Object {
	
	/**
	 * The port number to use
	 */
	private int port;
	
	/**
	 * Sets the port to use for UDP communication
	 * 
	 * @param port
	 */
	public void port(int port) {
		this.port = port;
		appendPrintInfo("(" + this.port + ");\n");
	}

	/**
	 * @return the port to use for UDP communication
	 */
	public int getPort() {
		return port;
	}

}
