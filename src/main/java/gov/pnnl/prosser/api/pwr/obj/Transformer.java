/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.pwr.lib.TransformerConfiguration;

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

    @Override
    public String getGLDObjectType() {
        return "transformer";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GldUtils.writeProperty(sb, "configuration", this.configuration);
    }

}
