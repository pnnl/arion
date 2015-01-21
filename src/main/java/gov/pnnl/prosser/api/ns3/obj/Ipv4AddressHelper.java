/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4AddressHelper extends AbstractNs3Object {
	private String ipBase, mask;
	
	public Ipv4AddressHelper() {
		setPrintObj("Ipv4AddressHelper " + this.getName() + ";\n");
	}
	
	public void setBase(String ipBase, String mask) {
		this.ipBase = ipBase;
		this.mask = mask;
		setPrintObj(getPrintObj() + this.getName() + ".SetBase(\"" + ipBase + "\", " + mask + "\" );\n");
	}

}
