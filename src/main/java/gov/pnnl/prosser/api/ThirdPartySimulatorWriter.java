/**
 * 
 */
package gov.pnnl.prosser.api;

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
import java.util.TreeMap;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.ImplicitEnduses;
import gov.pnnl.prosser.api.gld.enums.SolverMethod;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.UndergroundLineConductor;
import gov.pnnl.prosser.api.gld.module.ClimateModule;
import gov.pnnl.prosser.api.gld.module.Comm;
import gov.pnnl.prosser.api.gld.module.Connection;
import gov.pnnl.prosser.api.gld.module.Market;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.module.PowerflowModule;
import gov.pnnl.prosser.api.gld.module.Residential;
import gov.pnnl.prosser.api.gld.module.Tape;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;
import gov.pnnl.prosser.api.gld.obj.AuctionClass;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.CsvReader;
import gov.pnnl.prosser.api.gld.obj.FncsMsg;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.gld.obj.Load;
import gov.pnnl.prosser.api.gld.obj.Meter;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.PlayerClass;
import gov.pnnl.prosser.api.gld.obj.PlayerObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.gld.obj.Regulator;
import gov.pnnl.prosser.api.gld.obj.Substation;
import gov.pnnl.prosser.api.gld.obj.Switch;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.TriplexNode;
import gov.pnnl.prosser.api.gld.obj.UndergroundLine;
import gov.pnnl.prosser.api.gld.obj.WaterHeater;
import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;
import gov.pnnl.prosser.api.sql.SqlFile;
import gov.pnnl.prosser.api.thirdparty.enums.SimType;

/**
 * @author fish334
 *
 */
public abstract class ThirdPartySimulatorWriter {
	/**
     * Write a third party simulator to a file
     * 
     * @param path
     *            the file to write the simulator
     * @param thirdPartySimulator
     *            the simulator to write
     * @throws IOException
     *             when we can't write to file
     */
	public static void writeThirdPartySimulator(final Path path, final ThirdPartySimulator thirdPartySimulator) throws IOException {
        Files.createDirectories(path);
        final Set<Path> modelFiles = new HashSet<>(thirdPartySimulator.getModelFiles());
        final StringBuilder thirdPartyFncsConfig = new StringBuilder();
        modelFiles.forEach((i) -> {
            try {
                Files.copy(i, path.resolve(i.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to copy includes source to destination", e);
            }
        });
        thirdPartyFncsConfig.append("name = ");
        thirdPartyFncsConfig.append(thirdPartySimulator.getName());
        thirdPartyFncsConfig.append('\n');
        thirdPartyFncsConfig.append("time_delta = 1s\n");
        thirdPartyFncsConfig.append("broker = ");
        thirdPartyFncsConfig.append(thirdPartySimulator.getBroker());
        thirdPartyFncsConfig.append('\n');
        thirdPartyFncsConfig.append("values\n");
        if(thirdPartySimulator.getSimType().equals(SimType.MATLAB_AGGREGATOR)){
        	GldSimulator gldSim = thirdPartySimulator.getGldSim();
	        if (gldSim != null) {
	        	Map<String, Integer> aggCounters = new TreeMap<>();
	        	for (AbstractGldObject obj : gldSim.getObjects()){
	        		if(obj instanceof AuctionObject){
	        			aggCounters.put(obj.getName(), 0);
	        		}
	        	}
	        	AbstractNs3SimulatorV2 ns3Sim = gldSim.getNs3Sim();
	            for (AbstractGldObject o : gldSim.getObjects()) {
	                if (o instanceof Controller) {
	                    Controller controller = (Controller)o;
	                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "bid_price");
	                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "bid_quantity");
	                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "parent_unresponsive_load");
	                    aggCounters.put(controller.getAuction().getName(), aggCounters.get(controller.getAuction().getName() + 1));
	                }
	            }
	        }	
        } else if(thirdPartySimulator.getSimType().equals(SimType.MATPOWER)){
        	final Map<GldSimulator, String> sims = thirdPartySimulator.getGldSimulators();
        	for(Map.Entry<GldSimulator, String> entry: sims.entrySet()){
        		GldSimulator gSim = entry.getKey();
        		String busName = entry.getValue();
        	}
        }
        try (final BufferedWriter confWriter = Files.newBufferedWriter(path.resolve("fncs.zpl"), StandardCharsets.UTF_8)) {
            confWriter.write(thirdPartyFncsConfig.toString());
        }
    }
	
	private static void writeControllerToMarketVar(StringBuilder sb, int aggCount, AbstractNs3SimulatorV2 ns3Sim, GldSimulator gldSim, Controller controller, String var) {
		sb.append("    ");
		sb.append("controller_");
        sb.append(controller.getAuction().getName());
        sb.append('_');
        sb.append(aggCount);
        sb.append("_");
        sb.append(var);
        sb.append("\n        topic = ");
        sb.append(ns3Sim.getName());
		sb.append('/');
        sb.append(gldSim.getName());
        sb.append('/');
        sb.append(controller.getName());
        sb.append('@');
        sb.append(controller.getAuction().getName());
        sb.append('/');
        sb.append(var);
        sb.append('\n');
        writeOptions(sb, "\"\"", "string", false);
    }
	
	protected static void writeOptions(StringBuilder sb, String def, String type, boolean list) {
        sb.append("        default = ");
        sb.append(def);
        sb.append("\n        type = ");
        sb.append(type);
        sb.append("\n        list = ");
        sb.append(list);
        sb.append('\n');
    }
}
