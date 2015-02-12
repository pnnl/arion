/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.pwr.obj.Controller;
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;

/**
 * A class for enabling the easy use of the std::vector in the c++ library.
 * 
 * @author happ546
 *
 */
public class StringVector<T1> extends AbstractNs3Object {

	/**
	 * @param name the name of this Vector
	 */
	@Override
	public void setName(String name) {
		this.setNameString(name);
		appendPrintObj("std::vector<string> " + this.getName() + ";\n");
	}
	
	/**
	 * 
	 * @param controller the Controller with the name to add to this StringVector
	 */
	public void pushBack(Controller controller) {
		appendPrintObj(this.getName() + ".push_back(\"" + controller.getName() + "\");\n"); // TODO confirm controller.getName gets CNI prefix needed for FNCSAppsHelper
	}
	
}
