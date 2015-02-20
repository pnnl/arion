/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains components for LTE networks.
 * Needed for any LTE simulation.
 * 
 * @author happ546
 *
 */
public class Lte extends Module {
	
	/**
	 * Sets name to lte-module; sets ns3 flag to true
	 */
	public Lte() {
		this.name = "lte-module";
		this.ns3 = true;
	}

}
