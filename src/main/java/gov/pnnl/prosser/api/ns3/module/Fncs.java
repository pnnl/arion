/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * The FNCS module for ns-3; allows the use of the FNCS simulator.
 * Needed for all FNCS simulations.
 * 
 * @author happ546
 *
 */
public class Fncs extends Module {
	
	/**
	 * Sets name to fncs; sets ns3 flag to true
	 */
	public Fncs() {
		this.name = "fncs";
		this.ns3 = true;
	}

}
