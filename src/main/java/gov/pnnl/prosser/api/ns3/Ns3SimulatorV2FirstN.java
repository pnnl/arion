/**
 *
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
    
    public Ns3SimulatorV2FirstN(final String name) {
        super(name, Paths.get("res/firstN.cc"));
    }
    
    public void addNetwork(String gldSimName, int numHouses, String marketName, String housePrefix) {
        this.networks.add(new AuctionNetwork(gldSimName, numHouses, marketName, housePrefix));
    }
    
    @Override
    public void writeConfig(Path outDir) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("LinkModelGLDNS3.txt"), StandardCharsets.UTF_8)) {
            for(final AuctionNetwork network: this.networks) {
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
            for(final AuctionNetwork network: this.networks) {
                for(int i = 1; i <= network.getNumHouses(); i++) {
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
