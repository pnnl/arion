/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.HashMap;
import java.util.Map;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class Ipv4ListRoutingHelper extends AbstractNs3Object {
	
	private Map<AbstractNs3Object, Integer> routings;
	
	public Ipv4ListRoutingHelper() {
		this.routings = new HashMap<AbstractNs3Object, Integer>();
	}
	
	public void add(AbstractNs3Object staticRouting, int priority) {
		routings.put(staticRouting, priority);
		appendPrintObj(this.getName() + ".Add(" + staticRouting.getName() + ", " + priority + ");\n");
	}

}
