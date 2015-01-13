/**
 *
 */
package gov.pnnl.prosser.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author nord229
 *
 */
public class ExperimentWriter {

    public static void writeExperiment(final Path directory, final Experiment experiment) throws IOException {
        if (!Files.isDirectory(directory)) {
            throw new RuntimeException("Experiment writer not passed a directory");
        }
        // TODO write the FNCS configuration here
        // TODO write the NS-3 configuration here
        for (final GldSimulator gldSimulator : experiment.getGldSimulators()) {
            final Path path = directory.resolve(gldSimulator.getName() + ".glm");
            GldSimulatorWriter.writeGldSimulator(path, gldSimulator);
        }
    }

}
