/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NetDeviceContainer extends AbstractNs3Object {
	private String name, objInfo;
	
	public NetDeviceContainer(String name) {
		this.name = name;
		objInfo = "NetDeviceContainer " + this.name + ";\n";

	}
	
	/** 
	 * Append characteristics of this object to given stringbuilder
	 */
	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}
	
}
