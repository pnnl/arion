/**
 * 
 */
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 * @param <T> 
 *
 */
public class Pointer<T extends AbstractNs3Object> extends AbstractNs3Object {

	private T object;
	
	/**
	 * Create a new Pointer with the given name
	 * 
	 * @param name
	 */
	public Pointer(String name) {
		this.setName(name);
	}
	
	/**
	 * Create a new Pointer with the given name and encapsulate the given object
	 * with it
	 * 
	 * @param name
	 * @param obj
	 */
	public Pointer(String name, AbstractNs3Object obj) {
		this.setName(name);
		this.setType(obj);
	}

	/**
	 * Sets the name of this Pointer without printing any output
	 */
	@Override
	public void setName(String name) {
		super.setNameString(name);
	}
	
	/**
	 * Equivalent to this.pointer = obj;
	 * 
	 * @param obj the AbstractNs3Object to set as this pointer
	 */
	public void assign(AbstractNs3Object obj) {
		appendPrintObj(this.getName() + " = " + obj.getName() + ";\n");
	}
	
	/**
	 * Creates a Pointer for the given AbstractNs3Object 
	 * 
	 * @param obj the AbstractNs3Object to wrap in a pointer
	 */
	public void encapsulate(AbstractNs3Object obj) {
		appendPrintObj("\n\tPtr<" + obj.getClass().getSimpleName() + "> " 
					+ this.getName() + "(" + obj.getName() + ");\n");
	}
	
	/**
	 * @param obj the AbstractNs3Object to set this Pointer type to
	 */
	public void setType(AbstractNs3Object obj) {
		appendPrintObj("\n\tPtr<" + obj.getClass().getSimpleName() + "> " + this.getName() + ";\n");
	}
	
	/**
	 * Creates an object of the type of the given object
	 * 
	 * @param obj
	 */
	public void createObject(T obj) {
		this.object = obj;
		this.object.setPointerFlag(true);
		appendPrintObj("\n\tPtr<" + obj.getClass().getSimpleName() + "> " 
					+ this.getName() + " = CreateObject<" + obj.getClass().getSimpleName() + ">();\n");
	}
	
	/**
	 * @return the AbstractNs3Object this Pointer points to
	 */
	public AbstractNs3Object getObject() {
		return this.object;
	}
	
}
