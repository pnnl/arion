/**
 * 
 */
package gov.pnnl.prosser.api;

import java.util.Objects;

/**
 * @author happ546
 *
 */
public abstract class AbstractNs3Object {
	
	private String name, printObj;

	public AbstractNs3Object() {
		this.name = null;
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

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of this AbstractNs3Object and adds the constructor text to this object's information field,
	 * printObj, to later output to a c++ ns-3 file.
	 * Must be called immediately after the constructor.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		setPrintObj(this.getClass().getSimpleName() + " " + this.name + ";\n");
	}
	

	/**
	 * @return the printObj
	 */
	public String getPrintObj() {
		return printObj;
	}

	/**
	 * @param text the text printObj (stored c++ implementation text for this object) is set to
	 */
	public void setPrintObj(String text) {
		this.printObj = text;
	}
	
	/**
	 * 
	 * @param text the text to append to this object's printObj string
	 */
	public void appendPrintObj(String text) {
		setPrintObj(getPrintObj() + text);
	}
	
 @Override
    public int hashCode() {
        return Objects.hash(this.name, this.printObj);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractNs3Object other = (AbstractNs3Object) obj;
        return Objects.equals(this.name, other.name) 
        		&& Objects.equals(this.printObj, other.printObj);
    }
	
}
