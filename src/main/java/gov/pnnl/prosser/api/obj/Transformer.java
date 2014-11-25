/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;
import gov.pnnl.prosser.api.lib.TransformerConfiguration;

import java.util.EnumSet;

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

    public Transformer() {
    }

    public Transformer(final EnumSet<PhaseCode> phases, final Node from, final Node to, final TransformerConfiguration configuration) {
        super(phases, from, to);
        this.configuration = configuration;
    }

    public Transformer(final String name, final EnumSet<PhaseCode> phases, final Node from, final Node to, final TransformerConfiguration configuration) {
        super(name, phases, from, to);
        this.configuration = configuration;
    }

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
        GLDUtils.appendProperty(sb, "configuration", this.configuration);
    }

}
