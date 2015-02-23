/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Needed for any simulations using IP communication protocols
 * 
 * @author happ546
 *
 */
public class Internet extends Module {

	/**
	 * Sets name to internet-module; sets ns3 flag to true
	 */
	public Internet() {
		this.name = "internet-module";
		this.ns3 = true;
	}

}
