/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains components for FNCSApplicationHelper
 * Needed for any FNCS simulation.
 * 
 * @author happ546
 *
 */
public class FncsApplication extends Module {
	
	/**
	 * Sets name to fncsapplication-helper; sets ns3 flag to true
	 */
	public FncsApplication() {
		this.name = "fncsapplication-helper";
		this.ns3 = true;
	}

}
