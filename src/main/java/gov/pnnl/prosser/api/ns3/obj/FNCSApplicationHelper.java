/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class FNCSApplicationHelper extends AbstractNs3Object {

	public void setApps(String namesListName, NodeContainer gldNodes, 
						String marketToControllerMapName, ApplicationContainer destContainer) {
		appendPrintObj(destContainer.getName() + " = " + this.getName() 
						+ ".SetApps(" + namesListName + ", " 
						+ gldNodes.getName() + ", " + marketToControllerMapName + ");\n");
	}
	
}
