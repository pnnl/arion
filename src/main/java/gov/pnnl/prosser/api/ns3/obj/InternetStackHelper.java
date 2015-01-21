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
	private NodeContainer nodes;
	
	public InternetStackHelper() {
		setPrintObj(this.getClass().getTypeName() + " " + this.getName() + ";\n"); //TODO test this generalization for class name
	}

	public void install(NodeContainer nodes) {
		this.nodes = nodes;
		setPrintObj(getPrintObj() + " " + this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	
}
