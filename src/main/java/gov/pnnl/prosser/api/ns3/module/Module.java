/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * @author happ546
 *
 */
public class Module {
	
	private String name;
	
	/**
	 * @param name the name for this Module
	 */
	public Module (String name) {
		this.name = name;
	}

	/**
	 * Write the #include line for this module's header file
	 * TODO Add support for non-ns3 includes (C++ library stuff)
	 * @param sb
	 */
	public void writeNs3String(final StringBuilder sb) {
		sb.append("#include \"ns3/" + this.name + ".h\"\n");
	}

}
