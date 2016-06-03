/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.sql.SqlFile;
import gov.pnnl.prosser.api.thirdparty.enums.SimType;

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
        sb.append('\n');
        clock.writeGldString(sb);
        sb.append('\n');
        if (modules != null) {
            modules.forEach(m -> m.writeGldString(sb));
        }
        includes.forEach((i) -> {
            writeInclude(sb, i.getFileName().toString());
            try {
                Files.copy(i, path.resolve(i.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to copy includes source to destination", e);
            }
        });
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
                    if(house.getController() != null){
                    	house.getController().writeFncs2Directives(gldFncsConfig);
                    }
                }
                if (o instanceof AuctionObject){
                	AuctionObject auction = (AuctionObject)o;
                	try{
                		auction.writeFncs2Directives(gldFncsConfig);
                	} catch(Exception e) {
                		throw new RuntimeException("Missing Simulator", e);
                	}
                }
                try {
                    o.writeExternalFiles(path);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to copy object file source to destination", e);
                }
            }
            if(gldSimulator.getThirdPartySim() != null && gldSimulator.getThirdPartySim().getSimType() == SimType.MATLAB_AGGREGATOR){
	            //TODO: find a better way to print the Aggregator DG subscriptions
	            for(AbstractGldObject dgObject : gldSimulator.getDgList()){
	            	writeSubscribe(gldFncsConfig, "precommit", dgObject.getName(), "constant_power_A_real", gldSimulator.getThirdPartySim().getName(),String.format("%s_power_A", dgObject.getName()));
	            	writeSubscribe(gldFncsConfig, "precommit", dgObject.getName(), "constant_power_B_real", gldSimulator.getThirdPartySim().getName(),String.format("%s_power_B", dgObject.getName()));
	            	writeSubscribe(gldFncsConfig, "precommit", dgObject.getName(), "constant_power_C_real", gldSimulator.getThirdPartySim().getName(),String.format("%s_power_C", dgObject.getName()));
	            }
	            //TODO: find a better way to print the aggregator line publications
	            for(Map.Entry<AbstractGldObject, String> aggLine : gldSimulator.getAggregatorLines().entrySet()){
	            	writePublish(gldFncsConfig, "commit", aggLine.getKey().getName(), "power_out_real", String.format("%s_load", aggLine.getValue()), 0.01);
	            }
	            //TODO: find a better way to print the total_feeder_load
	            AbstractGldObject totalFeederLoadNode = gldSimulator.getGldObjectByName(String.format("%s_node_150", gldSimulator.getName()));
	            if(totalFeederLoadNode != null){
	            	writePublish(gldFncsConfig, "commit", totalFeederLoadNode.getName(), "measured_real_power", "total_feeder_load", 0.01);
	            }	
	            AbstractGldObject networkNode = gldSimulator.getGldObjectByName(String.format("%s_network_node", gldSimulator.getName()));
	            if(networkNode != null){
	            	writePublish(gldFncsConfig, "commit", networkNode.getName(), "distribution_load", "distribution_load", 20000.0);
	            	//TODO: subscribe to MATPOWER Voltage. Optional.
	            }
	            //TODO: find a better way to print the subscriptions for the DG's on the load
	            writeSubscribe(gldFncsConfig, "precommit", "DG_7", "constant_power_A_real", gldSimulator.getThirdPartySim().getName(), "DG_7_power_A");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_7", "constant_power_B_real", gldSimulator.getThirdPartySim().getName(), "DG_7_power_B");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_7", "constant_power_C_real", gldSimulator.getThirdPartySim().getName(), "DG_7_power_C");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_18", "constant_power_A_real", gldSimulator.getThirdPartySim().getName(), "DG_18_power_A");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_18", "constant_power_B_real", gldSimulator.getThirdPartySim().getName(), "DG_18_power_B");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_18", "constant_power_C_real", gldSimulator.getThirdPartySim().getName(), "DG_18_power_C");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_56", "constant_power_A_real", gldSimulator.getThirdPartySim().getName(), "DG_56_power_A");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_56", "constant_power_B_real", gldSimulator.getThirdPartySim().getName(), "DG_56_power_B");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_56", "constant_power_C_real", gldSimulator.getThirdPartySim().getName(), "DG_56_power_C");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_57", "constant_power_A_real", gldSimulator.getThirdPartySim().getName(), "DG_57_power_A");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_57", "constant_power_B_real", gldSimulator.getThirdPartySim().getName(), "DG_57_power_B");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_57", "constant_power_C_real", gldSimulator.getThirdPartySim().getName(), "DG_57_power_C");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_152", "constant_power_A_real", gldSimulator.getThirdPartySim().getName(), "DG_152_power_A");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_152", "constant_power_B_real", gldSimulator.getThirdPartySim().getName(), "DG_152_power_B");
	            writeSubscribe(gldFncsConfig, "precommit", "DG_152", "constant_power_C_real", gldSimulator.getThirdPartySim().getName(), "DG_152_power_C");
	            //TODO: find a bettery to print the dummy subscriptions
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "consensus_iterations", gldSimulator.getThirdPartySim().getName(), "consensus_iterations");
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "theoretical_feeder_load", gldSimulator.getThirdPartySim().getName(), "theoretical_feeder_load");
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "wholesale_LMP", gldSimulator.getThirdPartySim().getName(), "wholesale_LMP");
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "aggregator_1_cleared_quantity", gldSimulator.getThirdPartySim().getName(), "aggregator_1_cleared_quantity");
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "aggregator_2_cleared_quantity", gldSimulator.getThirdPartySim().getName(), "aggregator_2_cleared_quantity");
	            writeSubscribe(gldFncsConfig, "precommit", "dummy", "aggregator_3_cleared_quantity", gldSimulator.getThirdPartySim().getName(), "aggregator_3_cleared_quantity");
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
    
    public static void writeSubscribe(final StringBuilder sb, final String execStage, final String objectName, final String objectProperty, final String simName, final String subTopic){
    	sb.append("subscribe \"");
    	sb.append(execStage);
    	sb.append(":");
    	sb.append(objectName);
    	sb.append(".");
    	sb.append(objectProperty);
    	sb.append(" <- ");
    	sb.append(simName);
    	sb.append("/");
    	sb.append(subTopic);
    	sb.append("\";\n");
    }
    
    public static void writePublish(final StringBuilder sb, final String execStage, final String objectName, final String objectProperty, final String subTopic){
    	sb.append("publish \"");
    	sb.append(execStage);
    	sb.append(":");
    	sb.append(objectName);
    	sb.append(".");
    	sb.append(objectProperty);
    	sb.append(" -> ");
    	sb.append(subTopic);
    	sb.append("\";\n");
    }
    
    public static void writePublish(final StringBuilder sb, final String execStage, final String objectName, final String objectProperty, final String subTopic, final double threshold){
    	sb.append("publish \"");
    	sb.append(execStage);
    	sb.append(":");
    	sb.append(objectName);
    	sb.append(".");
    	sb.append(objectProperty);
    	sb.append(" -> ");
    	sb.append(subTopic);
    	sb.append("; ");
    	sb.append(threshold);
    	sb.append("\";\n");
    }
}
