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
     * Get the Modules for this simulation
     */
	public List<Module> getModules();
	
    /**
     * Get the Namespaces used for this simulation
     */
	public List<Namespace> getNamespaces();
	
    /**
     * Get the Simulator objects that comprise this simulation
     * TODO Need to modify AbPrObj to include ns3 objs or create new abstract ns3 obj
     */
	public List<AbstractProsserObject> getObjects();
	
}
