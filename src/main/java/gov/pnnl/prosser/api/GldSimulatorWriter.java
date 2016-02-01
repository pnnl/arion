/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.FncsMsg;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.sql.SqlFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
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
public abstract class GldSimulatorWriter {

    /**
     * Write a GLD Simulator to a file
     * 
     * @param path
     *            the file to write the simulator
     * @param gldSimulator
     *            the simulator to write
     * @throws IOException
     *             when we can't write to file
     */
    public static void writeGldSimulator(final Path path, final GldSimulator gldSimulator) throws IOException {
        Files.createDirectories(path);
        final Map<String, String> properties = gldSimulator.getSettings();
        final Set<Path> includes = new HashSet<>(gldSimulator.getIncludes());
        final GldClock clock = Objects.requireNonNull(gldSimulator.getClock(), "GLD clock must be non null");
        final Collection<Module> modules = gldSimulator.getModules();
        final List<AbstractGldClass> classes = gldSimulator.getClasses();
        final List<AbstractGldObject> objects = gldSimulator.getObjects();
        final StringBuilder sb = new StringBuilder();
        final StringBuilder gldFncsConfig = new StringBuilder();
        sb.append("//\n");
        sb.append("// BEGIN\n");
        sb.append("//\n\n");
        properties.forEach((k, v) -> {
            writeSetting(sb, k, v);
        });
        includes.forEach((i) -> {
            writeInclude(sb, i.getFileName().toString());
            try {
                Files.copy(i, path.resolve(i.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to copy includes source to destination", e);
            }
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
            final SqlFile sqlFile = new SqlFile(gldSimulator.getName());
            for (AbstractGldObject o : objects) {
                sb.append('\n');
                o.writeGldString(sb);
                o.createSqlObjects(sqlFile);
                if (o instanceof House) {
                    House house = (House)o;
                    house.getController().writeFncs2Directives(gldFncsConfig, gldSimulator.getName(), gldSimulator.getNs3Sim().getName());
                }
                try {
                    o.writeExternalFiles(path);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to copy object file source to destination", e);
                }
            }
            if (!sqlFile.getSqlTableDefs().isEmpty()) {
                final StringBuilder sql = new StringBuilder();
                sql.append("CREATE DATABASE \"" + sqlFile.getDatabaseName() + "\";\n");
                sql.append("USE \"" + sqlFile.getDatabaseName() + "\";\n\n");
                sqlFile.getSqlTableDefs().forEach(t -> {
                    sql.append("CREATE TABLE \"" + t.getName() + "\" (\n");
                    sql.append("    time    DATETIME,\n");
                    t.getSqlColumnDefs().forEach(c -> {
                        sql.append("    ");
                        sql.append(c.getName());
                        sql.append("    ");
                        sql.append(c.getType());
                        sql.append(",\n");
                    });
                    sql.delete(sql.length() - 2, sql.length());
                    sql.append(");");
                });
                try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve("create.sql"), StandardCharsets.UTF_8)) {
                    writer.write(sql.toString());
                }
            }
        }
        try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve(gldSimulator.getName() + ".glm"), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
        try (final BufferedWriter confWriter = Files.newBufferedWriter(path.resolve(gldSimulator.getName() + ".txt"), StandardCharsets.UTF_8)) {
            confWriter.write(gldFncsConfig.toString());
        }
    }

    public static void writeSetting(final StringBuilder sb, final String key, final String value) {
        sb.append("#set ");
        sb.append(key);
        sb.append('=');
        sb.append(value);
        sb.append("\n");
    }

    public static void writeInclude(final StringBuilder sb, final String include) {
        sb.append("#include \"");
        sb.append(include);
        sb.append("\"\n");
    }
}
