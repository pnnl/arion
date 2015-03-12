/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;

/**
 * Transformer Object
 *
 * @author nord229
 */
public class Transformer extends LinkObject {

    /**
     * Configuration library used for transformer setup
     */
    private TransformerConfiguration configuration;

    /**
     * @return the configuration
     */
    public TransformerConfiguration getConfiguration() {
        return configuration;
    }

    /**
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
        GldUtils.writeProperty(sb, "configuration", this.configuration);
    }

}
