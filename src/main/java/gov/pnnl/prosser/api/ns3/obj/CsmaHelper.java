/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author happ546
 *
 */
public class CsmaHelper {
	
	// Data rate can be StringValue or DataRateValue, delay can be StringValue or TimeValue in ns3
	private String datarate, delay, name;
	
	public CsmaHelper(String name) {
		this.name = name;
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
	}
	
	public void delay(String delay) {
		this.delay = delay;
	}
	
	/** 
	 * Output characteristics of this object to given file
	 */
	public void print(FileOutputStream fs) {
//		String result = String.format("DataRate: %s\n", datarate);
//		result += String.format("Delay: %s\n", delay);
		
		String result = "CsmaHelper " + this.name + ";\n";
		result += this.name + ".SetChannelAttribute(\"DataRate\", StringValue(\"" + this.datarate + "\"));\n";
		result += this.name + ".SetChannelAttribute(\"Delay\", StringValue(\"" + this.delay + "\");\n";
		PrintWriter pw = new PrintWriter(fs);
		pw.write(result);
	}
	
}
