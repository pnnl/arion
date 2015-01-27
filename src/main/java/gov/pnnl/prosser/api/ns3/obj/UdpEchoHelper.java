/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class UdpEchoHelper extends AbstractNs3Object {
	private int port;
	
	/**
	 * Sets the port to use for UDP communication
	 * @param port
	 */
	public void port(int port) {
		this.port = port;
		setPrintObj(getPrintObj() + "(" + this.port + ");\n");
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

}
