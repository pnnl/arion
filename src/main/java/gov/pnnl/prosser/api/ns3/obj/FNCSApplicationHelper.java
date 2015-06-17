/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.Vector;

/**
 * This class is used to connect the ns-3 simulator to the FNCS instance to allow 
 * communication among the various simulators in the Prosser experiment.
 * 
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
	 * @param stopTime
	 */
	public void setApps(Vector<String> names, NodeContainer gldNodes, 
						StringMap<String, String> marketToControllerMapName, 
						double stopTime) {
		appendPrintInfo("ApplicationContainer fncsAps = " + this.getName()
				+ ".SetApps (" + names.getName() + ", "
				+ gldNodes.getName() + ", "
				+ marketToControllerMapName.getName() + ");\n");
		appendPrintInfo("fncsAps.Start ( Seconds(0.0));\n");
		appendPrintInfo("fncsAps.Stop ( Seconds(" + stopTime + "));\n");
	}
	
}
