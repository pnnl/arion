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
	private static String printObj;
	private String printInfo;

	/**
	 * If printObj doesn't already contains text from other ns-3 objects,
	 * initialize it.
	 * Initializes pointer flag to false
	 */
	public AbstractNs3Object() {
		if (AbstractNs3Object.printObj == null) {
			AbstractNs3Object.printObj = "";
		}
		this.printInfo = "";
		this.name = null;

		Ns3Simulator.addObject(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of this AbstractNs3Object and adds the constructor 
	 * text to this object's information field, printObj, 
	 * to later output to a c++ ns-3 file.
	 * Must be called immediately after the constructor.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.appendPrintInfo("\n  " + this.getClass().getSimpleName() +
				" " + this.name + ";\n");
	}
	
	/**
	 * @return the name of this Node prefixed with "pointer_"
	 */
	public String getPointerName() {
		return "pointer_" + this.name;
	}
	
	/**
	 * Sets this AbstractNs3Object's name string
	 * Used by EpsBearer, LteHelper, and other ns-3 objects that supply parameters to 
	 * their ns-3 constructors
	 * @param name the name to set
	 */
	public void setNameString(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param text
	 * 			the text to append to this object's printInfo string for the
	 * 			ns-3 output file
	 */
	
	public void appendPrintInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPrintInfo(text);
		//this.setPrintInfo(this.getPrintInfo() + "  " + text);
	}

	/**
	 * @param text
	 * 			the text to set this object's printInfo string
	 */
	public void setPrintInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPrintInfo(text);
		//this.printInfo = text;
	}

	/**
	 * @return
	 * 			this object's printInfo string
	 */
	public String getPrintInfo() {
		return this.printInfo;
	}

	/**
	 * Append characteristics of this object to given Stringbuilder
	 * @param sb the stringbuilder
	 */
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(getPrintInfo());
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
     * Gets the given AbstractNs3Object as a Ptr to be used in 
     * ns-3 helper methods.  Called automatically in Node and Channel construction.
     */
    protected void getAsPointer() {
    	String objClass = this.getClass().getSimpleName();

    	appendPrintInfo("Ptr<" + objClass + "> " + this.getPointerName() +
				" = " + "CreateObject<" + objClass + ">();\n");
    }
	
}
