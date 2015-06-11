/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for enabling the easy use of the std::vector in the c++ library.
 * 
 * @author happ546
 *
 */
public class Vector<T> extends AbstractNs3Object {

	private List<String> names;

	/**
	 * @param name the name of this Vector
	 * @param clazz the type class of this Vector
	 */
	public Vector(String name, Class<T> clazz) {
		this.names = new ArrayList<>();
		this.setNameString(name);
		this.setType(clazz);
	}

	/**
	 * Adds the given name to the List of names to output to the ns3 file
	 * @param name
	 */
	public void addName(String name) {
		names.add(name);
	}

	/**
	 * Outputs each stored name string in this Vector's list of names
	 */
	public String printInfo() {
		String result = "";
		for (String name : names) {
			result += pushBack(name);
		}
		return result;
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
		this.pushBack(obj.getName());
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
	
	/**
	 * @param name
	 * @return a String of the given name string pushed into a
	 * 			C++ string vector
	 */
	public String pushBack(String name) {
		return this.getName() + ".push_back(\"" +
				name + "\");\n";
	}
	
}
