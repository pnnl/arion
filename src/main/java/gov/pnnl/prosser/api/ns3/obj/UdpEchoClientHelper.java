/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class UdpEchoClientHelper extends AbstractNs3Object {
	private Map<String, Double> attributes;
	
	public UdpEchoClientHelper() {
		attributes = new HashMap<String, Double>();
		setPrintObj("UdpEchoClientHelper " + this.getName() + ";\n");
	}

	/**
	 * Set the given attribute to the given value
	 * @param attr
	 * @param value
	 */
	public void setAttribute(String attr, double value) {
		attributes.put(attr, value);
	}
	
	

}
