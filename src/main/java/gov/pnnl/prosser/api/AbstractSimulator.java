/**
 * 
 */
package gov.pnnl.prosser.api;

/**
 * @author nord229
 *
 */
public abstract class AbstractSimulator {

    private final String name;

    public AbstractSimulator(String name) {
        this.name = name;
    }

    /**
     * Get the Name of this simulator object - Used when naming the file on disk in an Experiment
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

}
