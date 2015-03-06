/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.NetworkCapable;

/**
 * A class for enabling the easy use of the std::vector in the c++ library.
 * 
 * @author happ546
 *
 */
public class StringVector<T1> extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public StringVector(String name) {
		this.setName(name);
	}

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
	 * @param obj the NetworkCapable object (AuctionObject or Controller) 
	 * 			with the name to add to this StringVector
	 */
	public void pushBack(NetworkCapable obj) {
		appendPrintObj(this.getName() + ".push_back(\"" + 
					obj.getNetworkInterfaceName() + "\");\n");
	}
	
}
