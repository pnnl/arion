/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * GridLabD specific experiment writer
 *
 * @author nord229
 */
public class GldSimulatorWriter {

    public static void writeGldSimulator(final Path path, final GldSimulator gldSimulator) throws IOException {
        final Map<String, String> properties = gldSimulator.getSettings();
        final Set<String> includes = new HashSet<>(Arrays.asList(gldSimulator.getIncludes()));
        final GldClock clock = Objects.requireNonNull(gldSimulator.getClock(), "GLD clock must be non null");
        final List<Module> modules = gldSimulator.getModules();
        final List<AbstractGldClass> classes = gldSimulator.getClasses();
        final List<AbstractProsserObject> objects = gldSimulator.getObjects();
        final StringBuilder sb = new StringBuilder();
        sb.append("//\n");
        sb.append("// BEGIN\n");
        sb.append("//\n\n");
        properties.forEach((k, v) -> {
            GldUtils.writeSetting(sb, k, v);
        });
        includes.forEach((i) -> {
            GldUtils.writeInclude(sb, i);
        });
        sb.append('\n');
        clock.writeGldString(sb);
        sb.append('\n');
        if (modules != null) {
            modules.forEach(m -> m.writeGldString(sb));
        }
        if (classes != null) {
            classes.forEach((c) -> {
                c.writeGldString(sb);
            });
        }
        sb.append('\n');
        if (objects != null) {
            objects.forEach(o -> {
                sb.append('\n');
                o.writeGldString(sb);
            });
        }
        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
    }
}
