/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author happ546
 *
 */
public class NodeContainer {
	private String name;
	private int numNodes;
		
	public NodeContainer(String name) {
		this.name = name;
	}
	
	/**
	 * Create specified number of nodes in NC
	 * @param numNodes
	 */
	public void create(int numNodes) {
		this.numNodes = numNodes;
	}
	
	/** 
	 * Output characteristics of this object to given file
	 */
	public void print(FileOutputStream fs) {
		String printObj = "NodeContainer " + this.name + ";\n";
		printObj += name + ".Create(" + this.numNodes + ");\n";
		
		PrintWriter pw = new PrintWriter(fs);
		pw.write(printObj);
	}
}
