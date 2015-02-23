/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is used to connect NS-3 and FNCS together.
 * 
 * @author happ546
 *
 */
public class ApplicationContainer extends AbstractNs3Object {
	
	/**
	 * @param name
	 */
	public ApplicationContainer(String name) {
		this.setName(name);
	}

	private double start, stop;

	/**
	 * 
	 * @param start time (in seconds) to start application in double format
	 */
	public void start(double start) {
		this.start = start;
		appendPrintObj(this.getName() + ".Start(Seconds(" + this.start + "));\n");
	}

	/**
	 * 
	 * @param stop time (in seconds) to stop application in double format
	 */
	public void stop(double stop) {
		this.stop = stop;
		appendPrintObj(this.getName() + ".Stop(Seconds(" + this.stop + "));\n");
	}

}
