/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;

import java.util.List;
import java.util.Map;

/**
 * GLD Simulator Interface
 *
 * Users will implement this interface to declare a configuration they want to use for Prosser to generate configuration files
 *
 * @author nord229
 */
public interface GldSimulator {

    public List<AbstractProsserObject> getSimulatorObjects();

    public GldClock getGldClock();

    public List<Module> getGldModules();

    public Map<String, String> getGldSettings();
}
