/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class ApplicationContainer extends AbstractNs3Object {
	private String unit;
	private double start, stop;
	
	public ApplicationContainer() {
		setPrintObj("ApplicationContainer " + this.getName() + ";\n");
	}

	/**
	 * 
	 * @param start time to start application in double format
	 * @param unit unit of time used (s, ms, us, ns)
	 */
	public void start(double start, String unit) {
		this.start = start;
		this.unit = unit;
	}

	/**
	 * 
	 * @param stop time to stop application in double format
	 * @param unit unit of time used (s, ms, us, ns)
	 */
	public void stop(double stop, String unit) {
		this.stop = stop;
	}

}
