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
	
	private String name;
	private String nodeNamePrefix;
	private boolean pointer;
	private static String printObj;

	/**
	 * Initializes pointer flag to false
	 * Checks if printObj already contains text from other ns-3 objects
	 */
	public AbstractNs3Object() {
		this.name = null;
		this.pointer = false;
		if (AbstractNs3Object.printObj == null) {
			AbstractNs3Object.printObj = "";
		}
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
		this.appendPrintObj("\n\t" + this.getClass().getSimpleName() + " " + this.name + ";\n");
	}

	/**
	 * 
	 * @return the nodeNamePrefix for this Node
	 */
	public String getNodeNamePrefix() {
		return this.nodeNamePrefix;
	}
	
	/**
	 * 
	 * @param prefix the nodeNamePrefix to set for this Node
	 */
	public void setNodeNamePrefix(String prefix) {
		this.nodeNamePrefix = prefix;
	}
	
	/**
	 * Sets this AbstractNs3Object's name string
	 * Used by EpsBearer, LteHelper, and other ns-3 objects that supply parameters to 
	 * their ns-3 constructors
	 * @param name
	 */
	public void setNameString(String name) {
		this.name = name;
	}
	

	/**
	 * @return printObj the string containing the c++ implementation text for all ns-3 objects
	 */
	public String getPrintObj() {
		return AbstractNs3Object.printObj;
	}

	/**
	 * @param text the text printObj (stored c++ implementation text for all ns-3 objects) is set to
	 */
	public void setPrintObj(String text) {
		AbstractNs3Object.printObj = text;
	}
	
	/**
	 * 
	 * @param text the text to append to this object's printObj string
	 */
	public void appendPrintObj(String text) {
		this.setPrintObj(this.getPrintObj() + "\t" + text);
	}
	
 @Override
    public int hashCode() {
        return Objects.hash(this.name, AbstractNs3Object.printObj);
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
        		&& Objects.equals(AbstractNs3Object.printObj, AbstractNs3Object.printObj);
    }
    
    /**
     * @return 
     * 		true if this object is encapsulated in a pointer
     * 		false otherwise
     */
    public boolean getPointer() {
    	return this.pointer;
    }

	/**
	 * @param b true if this object is encapsulated in a pointer
	 * 			false otherwise
	 */
	public void setPointer(boolean b) {
		this.pointer = b;
	}
	
}
