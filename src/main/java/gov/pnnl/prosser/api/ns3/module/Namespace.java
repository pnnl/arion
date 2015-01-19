/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * Class for c++ namespaces
 * Should probably go elsewhere (not module package)
 * Should have separate class for each Namespace? (probably only ns3 & std)
 * @author happ546
 *
 */
public class Namespace {
	private String name;
	
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
