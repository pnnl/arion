/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.Clock;
import gov.pnnl.prosser.api.gld.module.Module;

import java.util.List;
import java.util.Map;

/**
 * Experiment Interface
 *
 * Users will implement this interface to declare a configuration they want to use for Prosser to generate configuration files
 *
 * @author nord229
 */
public interface Experiment {

    public List<AbstractProsserObject> getExperimentObjects();

    public Clock getGLDClock();

    public List<Module> getGLDModules();

    public Map<String, String> getGLDSettings();
}
