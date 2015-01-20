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
	private String name, ipBase, mask, objInfo;
	
	public Ipv4AddressHelper(String name) {
		this.name = name;
		objInfo += "Ipv4AddressHelper " + this.name + ";\n";
	}
	
	public void setBase(String ipBase, String mask) {
		this.ipBase = ipBase;
		this.mask = mask;
		objInfo += this.name + ".SetBase(\"" + ipBase + "\", " + mask + "\" );\n";
	}

	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}

}
