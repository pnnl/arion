/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains set of c/c++ standard exceptions.
 * 
 * @author happ546
 *
 */
public class Stdexcept extends Module {

	/**
	 * Sets name to stdexcept; sets ns3 flag to false
	 */
	public Stdexcept() {
		this.name = "stdexcept";
		this.ns3 = false;
	}
	
}
