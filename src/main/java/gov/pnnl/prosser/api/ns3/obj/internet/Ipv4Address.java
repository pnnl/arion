/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * This class represents an IPV4 address.
 * 
 * @author happ546
 *
 */
public class Ipv4Address extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4Address with the given name
	 * 
	 * @param name
	 */
	public Ipv4Address(String name) {
		this.setName(name);
	}

	/**
	 * Sets an Ipv4Address by parsing a the input C-string.  Input address is in format: hhh.xxx.xxx.lll where h is the high byte and l the low byte.
	 * 
	 * @param addr string containing the address as described above.
	 */
	public void Set(String addr)
	{
		appendPrintInfo(this.getName() + ".Set(\"" + addr + "\");\n");
	}
}
