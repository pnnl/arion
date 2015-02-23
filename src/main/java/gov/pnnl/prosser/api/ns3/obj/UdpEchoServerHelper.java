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
	 * @param name
	 */
	public UdpEchoServerHelper(String name) {
		this.setName(name);
		setPrintObj("UdpEchoServerHelper " + this.getName());
	}

}
