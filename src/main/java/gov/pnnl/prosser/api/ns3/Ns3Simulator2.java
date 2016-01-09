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
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator2 {
    
    private final String name;
    
    private String broker;

    private Path ccFile;
    
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
    
    public Ns3Simulator2(final String name) {
        this.name = name;
        this.ccFile = Paths.get("res/firstN.cc");
        this.broker = "tcp://localhost:5570";
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the broker
     */
    public String getBroker() {
        return broker;
    }

    /**
     * @param broker the broker to set
     */
    public void setBroker(String broker) {
        this.broker = broker;
    }

    /**
     * @return the ccFile
     */
    public Path getCcFile() {
        return ccFile;
    }

    /**
     * @param ccFile the ccFile to set
     */
    public void setCcFile(Path ccFile) {
        this.ccFile = ccFile;
    }
    
    public void addNetwork(String gldSimName, int numHouses, String marketName, String housePrefix) {
        this.networks.add(new AuctionNetwork(gldSimName, numHouses, marketName, housePrefix));
    }
    
    public void writeSimulator(Path outDir) throws IOException {
        Files.copy(this.ccFile, outDir.resolve(this.ccFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("LinkModelGLDNS3.txt"), StandardCharsets.UTF_8)) {
            for(final AuctionNetwork network: this.networks) {
                writer.write(String.format("%d %s %s%n", network.getNumHouses(), network.getMarketName(), network.getHousePrefix()));
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve(this.getName() + ".zpl"), StandardCharsets.UTF_8)) {
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
                    writeMarketToControllerVar(sb, network, i, "var1");
                    writeMarketToControllerVar(sb, network, i, "var2");
                    writeMarketToControllerVar(sb, network, i, "var3");
                    writeMarketToControllerVar(sb, network, i, "var4");
                    writeControllerToMarketVar(sb, network, i, "submit_bid_state");
                }
            }
            writer.write(sb.toString());
        }
    }
    
    private static void writeMarketToControllerVar(StringBuilder sb, AuctionNetwork network, int i, String var) {
        sb.append("    ");
        sb.append(network.getGldSimName());
        sb.append('/');
        sb.append(network.getMarketName());
        sb.append('@');
        sb.append(network.getHousePrefix());
        sb.append(i);
        sb.append('/');
        sb.append(var);
        sb.append('\n');
    }
    
    private static void writeControllerToMarketVar(StringBuilder sb, AuctionNetwork network, int i, String var) {
        sb.append("    ");
        sb.append(network.getGldSimName());
        sb.append('/');
        sb.append(network.getHousePrefix());
        sb.append(i);
        sb.append('@');
        sb.append(network.getMarketName());
        sb.append('/');
        sb.append(var);
        sb.append('\n');
    }
    
}
