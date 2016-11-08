/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
*    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
*    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
*    and may permit others to do so, subject to the following conditions:
*    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
*       the following disclaimers.
*    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
*       the following disclaimer in the documentation and/or other materials provided with the distribution.
*    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
*       form whatsoever without the express written consent of Battelle.
* 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
*    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
*    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
*    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*                                PACIFIC NORTHWEST NATIONAL LABORATORY
*                                            operated by
*                                              BATTELLE
*                                              for the
*                                  UNITED STATES DEPARTMENT OF ENERGY
*                                   under Contract DE-AC05-76RL01830
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
	        			writeSubscribe(thirdPartyFncsConfig, String.format("%s_load", obj.getName()),gldSim.getName(),String.format("%s_load", obj.getName()), "0", "double");
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
	            writeSubscribe(thirdPartyFncsConfig, "total_feeder_load", gldSim.getName(), "total_feeder_load", "0", "double");
	            for(final Subscription sub: thirdPartySimulator.getUserSubscriptions()) {
	                writeSubscribe(thirdPartyFncsConfig, sub.getLocalVariable(), sub.getRemoteSimulator().getName(), sub.getRemoteVariable(), "0", "double");
	            }
	        }
        } else if(thirdPartySimulator.getSimType().equals(SimType.MATPOWER)){
        	final Map<GldSimulator, String> sims = thirdPartySimulator.getGldSimulators();
        	for(Map.Entry<GldSimulator, String> entry: sims.entrySet()){
        		GldSimulator gSim = entry.getKey();
        		String busName = entry.getValue();
        		AbstractGldObject networkNode = gSim.getGldObjectByName(String.format("%s_network_node", gSim.getName()));
        		if(networkNode != null){
        			writeSubscribe(thirdPartyFncsConfig, busName, gSim.getName(), "distribution_load", "0 + 0 j MVA", "complex");
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

	protected static void writeSubscribe(final StringBuilder sb, final String shortKey, final String simName, final String subTopic, String defaultValue, String valueType){
		sb.append("    ");
		sb.append(shortKey);
		sb.append("\n        topic = ");
		sb.append(simName);
		sb.append("/");
		sb.append(subTopic);
		sb.append("\n");
		writeOptions(sb, defaultValue, valueType, false);
	}
}
