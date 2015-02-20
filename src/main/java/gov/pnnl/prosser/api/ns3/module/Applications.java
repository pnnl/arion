/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * The ns-3 applications module; contains packet-sending components of 
 * ns-3.
 * Needed for simulations using any application.
 * 
 * @author happ546
 *
 */
public class Applications extends Module {

	/**
	 * Sets name to applications-module; sets ns3 flag to true
	 */
	public Applications() {
		this.name = "applications-module";
		this.ns3 = true;
	}
	
}
