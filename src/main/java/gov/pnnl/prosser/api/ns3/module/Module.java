/**
 * 
 */
package gov.pnnl.prosser.api.ns3.module;

/**
 * @author happ546
 *
 */
public abstract class Module {
	
	protected String name;
	/**
	 * Set to true if this module is in the ns-3 API, 
	 * false if part of c libraries.
	 * Used by writeNs3String
	 */
	protected boolean ns3;

	/**
	 * Write the #include line for this module's header file
	 * @param sb the StringBuilder to write to
	 */
	public void writeNs3String(final StringBuilder sb) {
		sb.append("#include ");
		if (ns3) {
			sb.append("\"ns3/" + this.name + ".h\"\n");
		} else {
			sb.append("<" + this.name + ">\n");
		}
	}

}
