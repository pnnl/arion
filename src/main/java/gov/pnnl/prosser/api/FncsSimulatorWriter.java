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

import gov.pnnl.prosser.api.fncs.Fncs;
import gov.pnnl.prosser.api.fncs.FncsSimType;
import gov.pnnl.prosser.api.fncs.FncsSyncAlgo;
import gov.pnnl.prosser.api.fncs.FncsTimeMetric;
import gov.pnnl.prosser.api.fncs.SyncParams;

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
