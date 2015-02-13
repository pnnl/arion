/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Pointer<T> extends AbstractNs3Object {

	@Override
	public void setName(String name) {
		super.setNameString(name);
	}
	
	/**
	 * Creates a Pointer for the given AbstractNs3Object 
	 * 
	 * @param obj the AbstractNs3Object to wrap in a pointer
	 */
	public void encapsulate(AbstractNs3Object obj) {
		appendPrintObj("Ptr<" + obj.getClass().getSimpleName() + "> " + this.getName() + "(" + obj.getName() + ");\n");

	}
	
}
