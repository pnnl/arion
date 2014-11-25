/**
 *
 */
package gov.pnnl.prosser.api;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author nord229
 *
 */
public interface ExperimentWriter {

    public void writeExperiment(final Path path, final Experiment experiment) throws IOException;
}
