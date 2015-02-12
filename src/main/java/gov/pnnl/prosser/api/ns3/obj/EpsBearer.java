/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network.Qci;

/**
 * @author happ546
 *
 */
public class EpsBearer extends AbstractNs3Object {
	
	/**
	 * Sets the name of this EpsBearer without saving any output
	 */
	@Override
	public void setName(String name) {
		super.setNameString(name);
	}

	/**
	 * Sets the Qci to use
	 * @param q the Qci (Quality of service Class Indicator) used by this EpsBearer
	 */
	public void setQci(Qci q) {
		// Write the Qci constructor string
		this.appendPrintObj("\n\tenum EpsBearer::Qci " + q.getName() + " = EpsBearer::" + q.name() + ";\n");
		// Create an EpsBearer with given Qci
		this.appendPrintObj(this.getClass().getSimpleName() + " " + this.getName() + "(" + q.getName() + ");\n");
	}
	
}
