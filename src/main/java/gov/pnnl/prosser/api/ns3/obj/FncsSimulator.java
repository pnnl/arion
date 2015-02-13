/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class FncsSimulator extends AbstractNs3Object {
	
	@Override
	public void setName(String name) {
		super.setNameString(name);
		appendPrintObj(this.getClass().getSimpleName() + " *" + this.getName() + " = new FncsSimulator();\n");
	}
	
	public void setImplementation(Pointer<FncsSimulator> ptr) {
		appendPrintObj("Simulator::SetImplementation(" + ptr.getName() + ");\n");
	}
	
	public void unref() {
		appendPrintObj(this.getName() + "->Unref();\n");
	}

}
