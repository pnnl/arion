/**
 *
 */
package gov.pnnl.prosser.api.ns3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import gov.pnnl.prosser.api.AbstractSimulator;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public abstract class AbstractNs3SimulatorV2 extends AbstractSimulator {

    private String broker;

    private Path ccFile;

    public AbstractNs3SimulatorV2(final String name, final Path defaultCcFile) {
        super(name);
        this.ccFile = defaultCcFile;
        this.broker = "tcp://localhost:5570";
    }

    /**
     * @return the broker
     */
    public String getBroker() {
        return broker;
    }

    /**
     * @param broker
     *            the broker to set
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
     * @param ccFile
     *            the ccFile to set
     */
    public void setCcFile(Path ccFile) {
        this.ccFile = ccFile;
    }

    public final void writeSimulator(Path outDir) throws IOException {
        this.writeCcFile(outDir);
        this.writeConfig(outDir);
    }

    protected void writeCcFile(Path outDir) throws IOException {
        Files.copy(this.getCcFile(), outDir.resolve(this.getCcFile().getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }

    protected abstract void writeConfig(Path outDir) throws IOException;

    protected void writeZplHeader(StringBuilder sb) {
        sb.append("name = ");
        sb.append(this.getName());
        sb.append('\n');
        sb.append("time_delta = 1ns\n");
        sb.append("broker = ");
        sb.append(this.getBroker());
        sb.append('\n');
        sb.append("values\n");
    }

    protected static void writeMarketToControllerVar(StringBuilder sb, String gldSimName, String auctionName, String controllerName, String var) {
        sb.append("    ");
        sb.append(gldSimName);
        sb.append('/');
        sb.append(auctionName);
        sb.append('@');
        sb.append(controllerName);
        sb.append('/');
        sb.append(var);
        sb.append("\n        topic = ");
        sb.append(gldSimName);
        sb.append('/');
        sb.append(auctionName);
        sb.append('@');
        sb.append(controllerName);
        sb.append('/');
        sb.append(var);
        sb.append('\n');
        writeOptions(sb, "\"\"", "string", false);
    }

    protected static void writeControllerToMarketVar(StringBuilder sb, String gldSimName, String auctionName, String controllerName, String var) {
        sb.append("    ");
        sb.append(gldSimName);
        sb.append('/');
        sb.append(controllerName);
        sb.append('@');
        sb.append(auctionName);
        sb.append('/');
        sb.append(var);
        sb.append("\n        topic = ");
        sb.append(gldSimName);
        sb.append('/');
        sb.append(controllerName);
        sb.append('@');
        sb.append(auctionName);
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
