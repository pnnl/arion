/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;

/**
 * @author happ546
 *
 */
public class FncsSimulator extends AbstractNs3Object {
	
	/**
	 * @param name
	 */
	public FncsSimulator(String name) {
		this.setName(name);
	}

	@Override
	public void setName(String name) {
		super.setNameString(name);
		appendPrintObj(this.getClass().getSimpleName() + " *" + this.getName() + " = new FncsSimulator();\n");
	}
	
	/**
	 * @param ptr the Pointer to a FncsSimulator
	 */
	public void setImplementation(Pointer<FncsSimulator> ptr) {
		appendPrintObj("Simulator::SetImplementation(" + ptr.getName() + ");\n");
	}
	
	/**
	 * Used with a Pointer encapsulation of a FncsSimulator
	 * Decrements the Pointer's internal reference count
	 */
	public void unref() {
		appendPrintObj(this.getName() + "->Unref();\n");
	}

}
