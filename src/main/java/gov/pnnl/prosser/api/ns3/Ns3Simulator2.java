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

    private Path ccFile;
    
    private final List<AuctionNetwork> networks = new ArrayList<>();
    
    public static class AuctionNetwork {
        
        private final int numHouses;
        
        private final String marketName;
        
        private final String housePrefix;
        
        public AuctionNetwork(int numHouses, String marketName, String housePrefix) {
            this.numHouses = numHouses;
            this.marketName = marketName;
            this.housePrefix = housePrefix;
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
    
    public Ns3Simulator2() {
        this.ccFile = Paths.get("res/firstN.cc");
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
    
    public void addNetwork(int numHouses, String marketName, String housePrefix) {
        this.networks.add(new AuctionNetwork(numHouses, marketName, housePrefix));
    }
    
    public void writeSimulator(Path outDir) throws IOException {
        Files.copy(this.ccFile, outDir.resolve(this.ccFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        try (BufferedWriter writer = Files.newBufferedWriter(outDir.resolve("LinkModelGLDNS3.txt"), StandardCharsets.UTF_8)) {
            for(final AuctionNetwork network: this.networks) {
                writer.write(String.format("%d %s %s%n", network.getNumHouses(), network.getMarketName(), network.getHousePrefix()));
            }
        }
    }

}
