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
package gov.pnnl.prosser.api.ns3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.Experiment;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3SimulatorV2FirstN extends AbstractNs3SimulatorV2 {

    private final List<AuctionNetwork> networks = new ArrayList<>();

    public static class AuctionNetwork {

        private final String gldSimName;

        private final int numHouses;

        private final String marketName;

        private final String housePrefix;

        public AuctionNetwork(String gldSimName, int numHouses, String marketName, String housePrefix) {
            this.gldSimName = gldSimName;
            this.numHouses = numHouses;
            this.marketName = marketName;
            this.housePrefix = housePrefix;
        }

        /**
         * @return the gldSimName
         */
        public String getGldSimName() {
            return gldSimName;
        }

        /**
         * @return the numHouses
         */
        public int getNumHouses() {
            return numHouses;
        }

        /**
         * @return the marketName
         */
        public String getMarketName() {
            return marketName;
        }

        /**
         * @return the housePrefix
         */
        public String getHousePrefix() {
            return housePrefix;
        }

    }

    public Ns3SimulatorV2FirstN(final String name, Experiment experiment) {
        super(name, Paths.get("res/firstN.cc"), experiment);
    }

    public void addNetwork(String gldSimName, int numHouses, String marketName, String housePrefix) {
        this.networks.add(new AuctionNetwork(gldSimName, numHouses, marketName, housePrefix));
    }

    @Override
    public void writeConfig(Path outDir) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("LinkModelGLDNS3.txt"), StandardCharsets.UTF_8)) {
            for (final AuctionNetwork network : this.networks) {
                writer.write(String.format("%d %s %s%n", network.getNumHouses(), network.getMarketName(), network.getHousePrefix()));
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("fncs.zpl"), StandardCharsets.UTF_8)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("name = ");
            sb.append(this.getName());
            sb.append('\n');
            sb.append("time_delta = 1ns\n");
            sb.append("broker = ");
            sb.append(this.getBroker());
            sb.append('\n');
            sb.append("values\n");
            for (final AuctionNetwork network : this.networks) {
                for (int i = 1; i <= network.getNumHouses(); i++) {
                    writeControllerToMarketVar(sb, network, i, "submit_bid_state");
                    writeMarketToControllerVar(sb, network, i, "clearPrice");
                    writeMarketToControllerVar(sb, network, i, "mktID");
                    writeMarketToControllerVar(sb, network, i, "avgPrice");
                    writeMarketToControllerVar(sb, network, i, "stdevPrice");
                }
            }
            writer.write(sb.toString());
        }
    }

    private static void writeMarketToControllerVar(StringBuilder sb, AuctionNetwork network, int i, String var) {
        writeMarketToControllerVar(sb, network.getGldSimName(), network.getMarketName(), network.getHousePrefix() + i, var);
    }

    private static void writeControllerToMarketVar(StringBuilder sb, AuctionNetwork network, int i, String var) {
        writeControllerToMarketVar(sb, network.getGldSimName(), network.getMarketName(), network.getHousePrefix() + i, var);
    }

}
