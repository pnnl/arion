/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class UdpEchoServerHelper extends AbstractNs3Object {

	private int port;
	
	/**
	 * Constructs new UdpEchoServerHelper & sets printObj to c++ appropriate constructor
	 */
	public UdpEchoServerHelper() {
		setPrintObj("UdpEchoServerHelper " + this.getName());
	}

	/**
	 * Sets the port to use for UDP communication
	 * @param port
	 */
	public void port(int port) {
		this.port = port;
		setPrintObj(getPrintObj() + "(" + this.port + ");\n");
	}

}
