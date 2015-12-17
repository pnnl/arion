/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class StringMap<T1, T2> extends AbstractNs3Object {

	/**
	 * @param name
	 */
	public StringMap(String name) {
		this.setName(name);
	}

	/**
	 * @param name the String name of this Map
	 */
	@Override
	public void setName(String name) {
		this.setNameString(name);
		appendPrintInfo("map<string, string> " + this.getName() + ";\n");
	}
	
	/**
	 * 
	 * @param key a String; 
	 * 			should be the marketNetworkInterface name
	 * @param value the String to assign to key; 
	 * 			should be the controllerNetworkInterface prefix name
	 */
	public void put(String key, String value) {
		appendPrintInfo(this.getName() + "[\"" + key + "\"] = \"" + value + "\";\n");
	}
	
}
