/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains c++ and c standard libary stuff
 * 
 * @author happ546
 *
 */
public class Cstdlib extends Module {

	/**
	 * Sets name to cstdlib; sets ns3 flag to false
	 */
	public Cstdlib() {
		this.name = "cstdlib";
		this.ns3 = false;
	}
	
}
