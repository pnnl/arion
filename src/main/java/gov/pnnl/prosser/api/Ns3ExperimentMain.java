/**
 * 
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.test.TestNs3Simulator;

import java.util.List;

/**
 * @author happ546
 *
 */
public class Ns3ExperimentMain {
	
	public static void main(String[] args) {
		
		TestNs3Simulator sim = new TestNs3Simulator();
		
		List<AbstractNs3Object> objects = sim.getObjects();
		
		
	}

}
