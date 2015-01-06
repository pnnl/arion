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
    private final TransformerConfiguration configuration;

    public Transformer() {
        this.configuration = null;
    }

    public Transformer(final EnumSet<PhaseCode> phases, final Node from, final Node to, final TransformerConfiguration configuration) {
        super(phases, from, to);
        this.configuration = configuration;
    }

    public Transformer(final String name, final EnumSet<PhaseCode> phases, final Node from, final Node to, final TransformerConfiguration configuration) {
        super(name, phases, from, to);
        this.configuration = configuration;
    }

    public Transformer(final Builder builder) {
        super(builder);
        this.configuration = builder.configuration;
    }

    /**
     * @return the configuration
     */
    public TransformerConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String getGLDObjectType() {
        return "transformer";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.writeProperty(sb, "configuration", this.configuration);
    }

    public static class Builder extends LinkObject.AbstractBuilder<Transformer, Builder> {
        private TransformerConfiguration configuration;

        public Builder configuration(final TransformerConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Transformer build() {
            return new Transformer(this);
        }
    }

}
