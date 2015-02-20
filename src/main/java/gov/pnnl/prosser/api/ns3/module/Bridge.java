/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Needed for bridging any connection.
 * 
 * @author happ546
 *
 */
public class Bridge extends Module {

	/**
	 * Sets name to bridge-helper; sets ns3 flag to true
	 */
	public Bridge() {
		this.name = "bridge-helper";
		this.ns3 = true;
	}

}
