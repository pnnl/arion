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
    
    private final Experiment experiment;
    
    private String outputFolderName = "output";

    public AbstractSimulator(String name, Experiment experiment) {
        this.name = name;
        this.experiment = experiment;
    }

    /**
     * Get the Name of this simulator object - Used when naming the file on disk in an Experiment
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * @return the outputFolderName
     */
    public String getOutputFolderName() {
        return outputFolderName;
    }

    /**
     * @param outputFolderName the outputFolderName to set
     */
    public void setOutputFolderName(String outputFolderName) {
        this.outputFolderName = outputFolderName;
    }

}
