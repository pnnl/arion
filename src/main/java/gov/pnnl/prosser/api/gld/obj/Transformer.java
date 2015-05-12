/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;

/**
 * Transformer Object
 *
 * @author nord229
 */
public class Transformer extends LinkObject {

    /**
     * Configuration used for transformer setup
     */
    private TransformerConfiguration configuration;

    public Transformer(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the Configuration used for transformer setup
     * 
     * @return the configuration
     */
    public TransformerConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Set the Configuration used for transformer setup
     * 
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final TransformerConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "transformer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "configuration", this.configuration);
    }

}
