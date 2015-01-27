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
