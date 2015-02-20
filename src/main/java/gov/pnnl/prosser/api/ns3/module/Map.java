/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Contains components for c/c++ map.
 * Needed for all FNCS simulations (FncsApplicationHelper)
 * 
 * @author happ546
 *
 */
public class Map extends Module {
	
	/**
	 * Sets name to map; sets ns3 flag to false
	 */
	public Map() {
		this.name = "map";
		this.ns3 = false;
	}
	
}
