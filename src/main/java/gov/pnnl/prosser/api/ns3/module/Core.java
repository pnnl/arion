/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * The core module for ns-3; contains the scheduler/events, 
 * time, simulator, and tracing components, among others.
 * Needed for every simulation.
 * 
 * @author happ546
 *
 */
public class Core extends Module {
	
	/**
	 * Sets name to core-module; sets ns3 flag to true
	 */
	public Core() {
		this.name = "core-module";
		this.ns3 = true;
	}

}
