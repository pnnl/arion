/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * GridLabD specific experiment writer
 *
 * @author nord229
 */
public class GLDExperimentWriter implements ExperimentWriter {

    @Override
    public void writeExperiment(final Path path, final Experiment experiment) throws IOException {
        final Map<String, String> properties = experiment.getGLDSettings();
        final GldClock clock = Objects.requireNonNull(experiment.getGLDClock(), "GLD clock must be non null");
        final List<Module> modules = experiment.getGLDModules();
        final List<AbstractProsserObject> objects = experiment.getExperimentObjects();
        final StringBuilder sb = new StringBuilder();
        sb.append("//\n");
        sb.append("// BEGIN\n");
        sb.append("//\n\n");
        properties.forEach((k, v) -> {
            GLDUtils.writeGLDSetting(sb, k, v);
        });
        sb.append('\n');
        clock.writeGLDString(sb);
        sb.append('\n');
        if (modules != null) {
            modules.forEach(m -> m.writeGLDString(sb));
        }
        if (objects != null) {
            objects.forEach(o -> {
                sb.append('\n');
                o.writeGLDString(sb);
            });
        }
        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
    }
}
