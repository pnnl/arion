/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.Arrays;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4InterfaceContainer extends AbstractNs3Object {
	private String name, objInfo;
	
	public Ipv4InterfaceContainer(String name) {
		this.name = name;
		this.objInfo += "Ipv4InterfaceContainer " + this.name;
	}

	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}

	@Override
	public void assignment(AbstractNs3Object right) {
		
		
		/*this.name = right.getName();
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		String method = trace[0].getMethodName();
		String[] fullClass = trace[0].getClassName().split(".");
		String className = fullClass[fullClass.length];
		// Would need to get parameter value passed to original method somehow to do this
		this.objInfo = "Ipv4InterfaceContainer " + this.name + "= " + className + "." + method;*/
	}

	@Override
	public String getName() {
		return this.name;
	}

}

/*
NetDeviceContainer devices = new NetDeviceContainer();

devices.assignment(pointToPoint.Install(nodes))
*/