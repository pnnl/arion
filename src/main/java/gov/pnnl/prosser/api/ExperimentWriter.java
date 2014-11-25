/**
 *
 */
package gov.pnnl.prosser.api;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Writer interface for writing Experiments as certain types of configuration
 *
 * @author nord229
 */
public interface ExperimentWriter {

    public void writeExperiment(final Path path, final Experiment experiment) throws IOException;
}
