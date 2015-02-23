/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Class for c++ namespaces
 *
 * @author happ546
 *
 */
public class Namespace {
	private String name;
	
	/**
	 * @param name the name for this namespace
	 */
	public Namespace(String name) {
		this.name = name;
	}
	
	/**
	 * Write c++ namespace stuff to sb
	 * @param sb
	 */
	public void writeNamespace(StringBuilder sb) {
		sb.append("using namespace " + this.name + ";\n");
	}

}
