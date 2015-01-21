/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject.Builder;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author happ546
 *
 */
public class NodeContainer extends AbstractNs3Object {
	/**
	 * The number of nodes in this NodeContainer
	 */
	private int numNodes;
	/**
	 * An array of the Nodes in this NodeContainer
	 */
	private ArrayList<Node> nodes;
	
	/**
	 * Initialize an empty NodeContainer
	 */
	public NodeContainer() {
		this.numNodes = 0;
		this.nodes = new ArrayList<Node>();
	}

	/**
	 * Create specified number of nodes in this NodeContainer
	 * @param numNodes
	 */
	public void create(int numNodes) {
		this.numNodes = numNodes;
		for (int i = 0; i < numNodes; i++) {
			this.addNode();
		}
		setPrintObj(getPrintObj() + (getName() + ".Create(" + this.numNodes + ");\n"));
	}

	/**
	 * Appends a new node to the end of the nodes array
	 */
	public void addNode() {
		Node temp = new Node();
		nodes.add(temp);
	}
	
}
