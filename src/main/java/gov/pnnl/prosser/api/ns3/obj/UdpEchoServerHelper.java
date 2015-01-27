/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/**
 * @author happ546
 *
 */
public class UdpEchoServerHelper extends UdpEchoHelper {
	
	/**
	 * Constructs new UdpEchoServerHelper & sets printObj to c++ appropriate constructor
	 */
	public UdpEchoServerHelper() {
		setPrintObj("UdpEchoServerHelper " + this.getName());
	}

}
