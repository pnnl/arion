/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Needed for any simulation using a PointToPoint network type
 * 
 * @author happ546
 *
 */
public class PointToPoint extends Module {
	
	/**
	 * Sets name to point-to-point-module; sets ns3 flag to true
	 */
	public PointToPoint() {
		this.name = "point-to-point-module";
		this.ns3 = true;
	}


}
