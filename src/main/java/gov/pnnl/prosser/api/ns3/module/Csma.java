/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Needed for CSMA networks
 * 
 * @author happ546
 *
 */
public class Csma extends Module {
	
	/**
	 * Sets name to csma-module; sets ns3 flag to true
	 */
	public Csma() {
		this.name = "csma-module";
		this.ns3 = true;
	}

}
