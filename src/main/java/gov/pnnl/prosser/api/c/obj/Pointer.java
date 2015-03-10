/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.Node;

/**
 * @author happ546
 *
 */
public class Pointer<T extends AbstractNs3Object> extends AbstractNs3Object {

	private T object;
	
	/**
	 * @param name
	 */
	public Pointer(String name) {
		this.setName(name);
	}
	
	/**
	 * @param name
	 * @param obj
	 */
	public Pointer(String name, AbstractNs3Object obj) {
		this.setName(name);
		this.setType(obj);
	}

	@Override
	public void setName(String name) {
		super.setNameString(name);
	}
	
	public void assign(AbstractNs3Object obj) {
		appendPrintObj(this.getName() + " = " + obj.getName() + ";\n");
	}
	
	/**
	 * Creates a Pointer for the given AbstractNs3Object 
	 * 
	 * @param obj the AbstractNs3Object to wrap in a pointer
	 */
	public void encapsulate(AbstractNs3Object obj) {
		appendPrintObj("Ptr<" + obj.getClass().getSimpleName() + "> " 
					+ this.getName() + "(" + obj.getName() + ");\n");
	}
	
	/**
	 * @param obj the AbstractNs3Object to set this Pointer type to
	 */
	public void setType(AbstractNs3Object obj) {
		appendPrintObj("Ptr<" + obj.getClass().getSimpleName() + "> " + this.getName() + ";\n");
	}
	
	/**
	 * @param obj
	 */
	public void createObject(T obj) {
		this.object = obj;
		this.object.setPointer(true);
		appendPrintObj("Ptr<" + obj.getClass().getSimpleName() + "> " 
					+ this.getName() + " = CreateObject<" + obj.getClass().getSimpleName() + ">();\n");
	}
	
	/**
	 * @return the AbstractNs3Object this Pointer points to
	 */
	public AbstractNs3Object getObject() {
		return this.object;
	}
	
}
