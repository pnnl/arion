/**
 * 
 */
package gov.pnnl.prosser.api;

/**
 * @author happ546
 *
 */
public abstract class AbstractNs3Object {
	
	private String name;
	private String printObj;

	public AbstractNs3Object() {
		this.name = null;
		this.printObj = null;
	}
	
	public AbstractNs3Object(String name) {
		this.name = name;
		this.printObj = null;
	}

	public void writeNs3String(final StringBuilder sb) {
		this.writeNs3Properties(sb);
	}

	/** 
	 * Append characteristics of this object to given stringbuilder
	 * @param StringBuilder
	 */
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(getPrintObj());
	}
	
	//public abstract void assignment(AbstractNs3Object right);

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the printObj
	 */
	public String getPrintObj() {
		return printObj;
	}

	/**
	 * @param printObj the printObj to set
	 */
	public void setPrintObj(String printObj) {
		this.printObj = printObj;
	}
	
}
