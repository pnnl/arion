/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * This class is used to setup the FNCS simulator for the Prosser
 * experiment.
 * 
 * @author happ546
 *
 */
public class FncsSimulator extends AbstractNs3Object {
	
	/**
	 * Creates a new FncsSimulator with the given name
	 * @param name
	 */
	public FncsSimulator(String name) {
		this.setName(name);
        pointerize();
        unref();
        setImplementation();
	}
	
	/**
	 * Sets the name of this FncsSimulator and outputs the appropriate 
	 * ns-3 C++ initialization code
	 * @param name
	 */
	@Override
	public void setName(String name) {
		super.setNameString(name);
		appendConstructorInfo(this.getClass().getSimpleName() + " *"
				+ this.getName() + " = new FncsSimulator();\n");
	}
	
	/**
     * Sets the implementation of this simulator
     */
	public void setImplementation() {
		appendConstructorInfo("Simulator::SetImplementation(hb2);\n");
	}
	
	/**
	 * Used with a Pointer encapsulation of a FncsSimulator
	 * Decrements the Pointer's internal reference count
	 */
	public void unref() {
		appendConstructorInfo(this.getName() + "->Unref();\n");
	}

	/**
	 * Wraps this simulator in a pointer
	 */
	public void pointerize() {
		appendConstructorInfo("Ptr<FncsSimulator> hb2 (" + getName() + ");\n");
	}
}
