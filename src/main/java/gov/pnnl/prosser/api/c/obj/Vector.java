/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

/**
 * A class for enabling the easy use of the std::vector in the c++ library.
 * 
 * @author happ546
 *
 */
public class Vector<T> extends AbstractNs3Object {

	/**
	 * @param name the name of this Vector
	 * @param clazz the type class of this Vector
	 */
	public Vector(String name, Class<T> clazz) {
		this.setNameString(name);
		this.setType(clazz);
	}

	/**
	 * @param clazz the type class of this Vector
	 */
	public void setType(Class<T> clazz) {
		appendPrintObj("vector<" + clazz.getSimpleName().toLowerCase() + "> " + this.getName() + ";\n");
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
	
	/**
	 * @param obj the AbstractNs3Object with the name to add to 
	 * 			this Vector
	 */
	public void pushBack(AbstractNs3Object obj) {
		appendPrintObj(this.getName() + ".push_back(\"" + 
				obj.getName() + "\");\n");
	}
	
	/**
	 * Pushes each Node in nc into the Vector
	 * 
	 * @param nc the NodeContainer
	 */
	public void pushBack(NodeContainer nc) {
		for (int i = 0; i < nc.getNumNodes(); i++) {
			this.pushBack(nc.getNodeNoPrint(i));
		}
	}
	
}
