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
		
	}
	
	public void setBase(String ipBase, String mask) {
		this.ipBase = ipBase;
		this.mask = mask;
		appendPrintObj(".SetBase(\"" + ipBase + "\", " + mask + "\" );\n");
	}

	public void assign(NetDeviceContainer devices) {
		// TODO Auto-generated method stub
		appendPrintObj(this.getName() + ".Assign(" + devices.getName() + ");\n");
		
	}

}
