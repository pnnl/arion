/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;

import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public interface Ns3Simulator {
	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters TODO supply user params
	 */
	public void setup();
	
    /**
     * Gets the Modules used in this simulation
     */
	public List<Module> getModules();
	
    /**
     * Gets the Namespaces used in this simulation
     */
	public List<Namespace> getNamespaces();
	
    /**
     * Gets the ns-3 objects used in this simulation
     */
	public List<AbstractNs3Object> getObjects();

}
