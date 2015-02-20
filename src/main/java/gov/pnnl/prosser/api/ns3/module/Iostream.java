/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains c/c++ standard input/output streams.
 * 
 * @author happ546
 *
 */
public class Iostream extends Module {
	
	/**
	 * Sets name to iostream; sets ns3 flag to false
	 */
	public Iostream() {
		this.name = "iostream";
		this.ns3 = false;
	}

}
