/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class InternetStackHelper extends AbstractNs3Object {
	private String name, objInfo;
	private NodeContainer nodes;
	
	public InternetStackHelper(String name) {
		this.name = name;
		objInfo += this.getClass().getTypeName() + " " + this.name + ";\n"; //TODO test this generalization for class name
	}
	
	public void install(NodeContainer nodes) {
		this.nodes = nodes;
		objInfo += name + ".Install(" + nodes.getName() + ");\n";
	}

	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}
	
}
