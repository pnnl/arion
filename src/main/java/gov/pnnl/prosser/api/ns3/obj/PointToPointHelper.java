/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class PointToPointHelper extends AbstractNs3Object {
	private String objInfo;
	private NodeContainer nodes;

	public void install(NodeContainer nodes) {
		this.nodes = nodes;
		objInfo += this.getName() + ".Install(" + nodes.getName() + ");\n";
		
	}
	
	/** 
	 * Append characteristics of this object to given stringbuilder
	 */
	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}
	
}
