/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.Vector;

/**
 * @author happ546
 *
 */
public class FNCSApplicationHelper extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public FNCSApplicationHelper(String name) {
		this.setName(name);
	}

	/**
	 * @param names ListName a list 
	 * @param gldNodes
	 * @param marketToControllerMapName a Map from the MarketNetworkInterface name String 
	 * 			to the ControllerNetworkInterface name String
	 * @param destContainer the ApplicationContainer to hold the generated application
	 */
	public void setApps(Vector<String> names, NodeContainer gldNodes, 
						StringMap<String, String> marketToControllerMapName, ApplicationContainer destContainer) {
		appendPrintObj(destContainer.getName() + " = " + this.getName() 
						+ ".SetApps(" + names.getName() + ", " 
						+ gldNodes.getName() + ", " + marketToControllerMapName.getName() + ");\n");
	}
	
}
