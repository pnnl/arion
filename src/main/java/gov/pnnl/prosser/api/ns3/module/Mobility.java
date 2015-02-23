/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Needed for using MobilityModel (WiFi or LTE networks)
 * 
 * @author happ546
 *
 */
public class Mobility extends Module {
	
	/**
	 * Sets name to mobility-module; sets ns3 flag to true
	 */
	public Mobility() {
		this.name = "mobility-module";
		this.ns3 = true;
	}

}
