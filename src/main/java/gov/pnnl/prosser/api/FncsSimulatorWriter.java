/**
 * 
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.Fncs.FncsSimType;
import gov.pnnl.prosser.api.Fncs.FncsSyncAlgo;
import gov.pnnl.prosser.api.Fncs.FncsTimeMetric;
import gov.pnnl.prosser.api.Fncs.SyncParams;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * FNCS Simulator writer utility class
 * 
 * @author nord229
 */
public abstract class FncsSimulatorWriter {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Write the FNCS simulator files to the output path, this method assumes ns3 and gld are in use
     * 
     * @param path
     *            the output path
     * @param sim
     *            the simulator to use for configuration
     * @param numGldSims
     *            the number of GridLab-D simulators that will be in use
     * @throws IOException
     *             when writing to a file fails
     */
    public static void writeSimulator(final Path path, final FncsSimulator sim, final int numGldSims) throws IOException {
        final String broker = sim.getBroker();
        final Fncs ns3 = new Fncs();
        ns3.setBroker(broker);
        ns3.setSimulatorType(FncsSimType.CommunicationNetwork);
        ns3.setSyncAlgo(FncsSyncAlgo.CommunicatorSimulatorSyncalgo);
        ns3.setSimulatorTimeMetric(FncsTimeMetric.nanoseconds);
        ns3.setPacketLostPeriod(51000000000L);
        try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve("configns3.json"), StandardCharsets.UTF_8)) {
            gson.toJson(ns3, writer);
        }

        final Fncs gld = new Fncs();
        gld.setBroker(broker);
        gld.setSimulatorType(FncsSimType.PowerGrid);
        gld.setSyncAlgo(FncsSyncAlgo.GracePeriodSyncAlgo);
        gld.setSimulatorTimeMetric(FncsTimeMetric.seconds);
        gld.setPacketLostPeriod(2300000000L);
        final SyncParams syncParams = new SyncParams();
        syncParams.setNumPowerGridSims(numGldSims);
        gld.setSyncParams(syncParams);

        try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve("configgld.json"), StandardCharsets.UTF_8)) {
            gson.toJson(gld, writer);
        }
    }
}
