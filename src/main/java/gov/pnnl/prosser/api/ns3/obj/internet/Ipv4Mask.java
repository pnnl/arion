/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class represents an IPV4 Network Mask.
 * 
 * @author lalo609
 *
 */
public class Ipv4Mask extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4Address with the given name
	 * 
	 * @param name
	 */
	public Ipv4Mask(String name) {
		this.setName(name);
	}
	
	/**
	 * Sets an Ipv4Mask by parsing a the input string.  Input address is in format: hhh.xxx.xxx.lll where h is the high byte and l the low byte.
	 * 
	 * @param addr string containing the Mask as described above.
	 */
	public void Set(String addr)
	{
		appendPrintInfo(this.getName() + ".Set(\"" + addr + "\");\n");
	}

}
