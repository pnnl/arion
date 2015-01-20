/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author happ546
 *
 */
public class CsmaHelper extends AbstractNs3Object {
	
	// Data rate can be StringValue or DataRateValue, delay can be StringValue or TimeValue in ns3
	private String datarate, delay, name, objInfo;
	
	public CsmaHelper(String name) {
		this.name = name;
		objInfo = "CsmaHelper " + this.name + ";\n";
	}
	
	public void setChannelAttribute (String attr, String value) {
		attr.toLowerCase();
		if (attr.equals("datarate")) {
			this.datarate = value;
		} else if (attr.equals("delay")) {
			this.delay = value;
		}
	}
	
	public void dataRate(String datarate) {
		this.datarate = datarate;
		objInfo += this.name + ".SetChannelAttribute(\"DataRate\", StringValue(\"" + this.datarate + "\"));\n";
	}
	
	public void delay(String delay) {
		this.delay = delay;
		objInfo += this.name + ".SetChannelAttribute(\"Delay\", StringValue(\"" + this.delay + "\");\n";
	}
	
	/** 
	 * Append characteristics of this object to given stringbuilder
	 */
	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(objInfo);
	}
	
}
