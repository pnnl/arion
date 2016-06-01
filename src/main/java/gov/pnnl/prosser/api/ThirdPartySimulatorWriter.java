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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import gov.pnnl.prosser.api.fncs.Subscription;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;
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
	        	Map<String, Integer> aggCounters = new HashMap<>();
	        	for (AbstractGldObject obj : gldSim.getObjects()){
	        		if(obj instanceof AuctionObject){
	        			aggCounters.put(obj.getName(), 0);
	        			for(Map.Entry<String, Integer> entry : aggCounters.entrySet()){
	        				//System.out.println(entry.getKey());
	        				//System.out.println(entry.getValue());
	        			}
	        			writeSubscribe(thirdPartyFncsConfig, String.format("%s_load", obj.getName()),gldSim.getName(),String.format("%s_load", obj.getName()));
	        		}
	        	}
	        	AbstractNs3SimulatorV2 ns3Sim = gldSim.getNs3Sim();
	            for (AbstractGldObject o : gldSim.getObjects()) {
	                if (o instanceof House) {
	                	House house = (House)o;
	                    Controller controller = house.getController();
	                    if(controller != null){
	                    	//AbstractGldObject auction = controller.getAuction();
	                    	//System.out.println(auction.getName());
	                    	//System.out.println(aggCounters.get(auction.getName()))
		                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "bid_price");
		                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "bid_quantity");
		                    writeControllerToMarketVar(thirdPartyFncsConfig, aggCounters.get(controller.getAuction().getName()), ns3Sim, gldSim, controller, "parent_unresponsive_load");
		                    
		                    aggCounters.put(controller.getAuction().getName(), aggCounters.get(controller.getAuction().getName()) + 1);
	                    }
	                }
	            }
	            writeSubscribe(thirdPartyFncsConfig, "total_feeder_load", gldSim.getName(), "total_feeder_load");
	            for(final Subscription sub: thirdPartySimulator.getUserSubscriptions()) {
	                writeSubscribe(thirdPartyFncsConfig, sub.getLocalVariable(), sub.getRemoteSimulator().getName(), sub.getRemoteVariable());
	            }
	        }
        } else if(thirdPartySimulator.getSimType().equals(SimType.MATPOWER)){
        	final Map<GldSimulator, String> sims = thirdPartySimulator.getGldSimulators();
        	for(Map.Entry<GldSimulator, String> entry: sims.entrySet()){
        		GldSimulator gSim = entry.getKey();
        		String busName = entry.getValue();
        		AbstractGldObject networkNode = gSim.getGldObjectByName(String.format("%s_network_node", gSim.getName()));
        		if(networkNode != null){
        			writeSubscribe(thirdPartyFncsConfig, busName, gSim.getName(), "distribution_load");
        		}
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
	
	protected static void writeSubscribe(final StringBuilder sb, final String shortKey, final String simName, final String subTopic){
		sb.append("    ");
		sb.append(shortKey);
		sb.append("\n        topic = ");
		sb.append(simName);
		sb.append("/");
		sb.append(subTopic);
		sb.append("\n");
		writeOptions(sb, "0 + 0 + j MVA", "complex", false);
	}
}
