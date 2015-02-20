/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * @author happ546
 *
 */
public class YansWifiChannel extends AbstractNs3Object {

	/**
	 * @param phy the YansWifiPhy object to add to this YansWifiChannel list of physical devices
	 */
	public void add(Pointer<YansWifiPhy> phy) {
		appendPrintObj(this.getName() + ".Add(" + phy.getName() + ");\n");
	}
	
}
