/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

/**
 * fncs_msg specific class definition
 * 
 * @author fish334
 *
 */
public class FncsMsg extends AbstractGldObject {
	//class properties
	/**
	 *  a list of route message topic strings
	 */
	private final List<String> route = new ArrayList<>();
	/**
	 * A list of publish message topic strings
	 */
	private final List<String> publish = new ArrayList<>();
	/**
	 * A list of subscribe message topic strings
	 */
	private final List<String> subscribe = new ArrayList<>();
	/**
	 * A list of communication option strings
	 */
	private final List<String> option = new ArrayList<>();
	/**
	 * A list of fncs configuration files
	 */
	private final List<String> configure = new ArrayList<>();
	
	//Constructor
	public FncsMsg(final GldSimulator simulator) {
		super(simulator);
		simulator.ensureConnectionModule();
	}
	
	public void addRoute(String str) {
		this.route.add(str);
	}
	
	public void addPublish(String str) {
		this.publish.add(str);
	}
	
	public void addSubscribe(String str) {
		this.subscribe.add(str);
	}
	
	public void addOption(String str) {
		this.option.add(str);
	}
	
	public void addConfigure(String str) {
		this.configure.add(str);
	}
	
	public List<String> getRoute() {
		return route;
	}

	public List<String> getPublish() {
		return publish;
	}

	public List<String> getSubscribe() {
		return subscribe;
	}

	public List<String> getOption() {
		return option;
	}

	public List<String> getConfigure() {
		return configure;
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
	 */
	@Override
	protected String getGldObjectType() {
		return "auction";
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
	 */
	@Override
	protected void writeGldProperties(StringBuilder sb) {
		for(String str : option) {
			writeProperty(sb, "option", str);
		}
		for(String str : configure) {
			writeProperty(sb, "configure", str);
		}
	}
}
