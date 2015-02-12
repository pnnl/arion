/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;

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

    /**
     * Get the Name of this simulator object - Used when naming the file on disk in an Experiment
     */
    public String getName();

    /**
     * Get the Simulator objects that comprise this simulation
     */
    public List<AbstractProsserObject> getObjects();

    /**
     * Get the Clock for this simulation
     */
    public GldClock getClock();

    /**
     * Get the Modules for this simulation
     */
    public List<Module> getModules();

    /**
     * Get the Settings for this simulation
     */
    public Map<String, String> getSettings();

    public String[] getIncludes();

    public List<AbstractGldClass> getClasses();
}
