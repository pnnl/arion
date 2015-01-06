/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.lib.TriplexLineConfiguration;

import java.util.EnumSet;

/**
 * Triplex Line
 *
 * @author nord229
 */
public class TriplexLine extends Line<TriplexLineConductor, TriplexLineConfiguration> {

    public TriplexLine() {
    }

    public TriplexLine(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length,
            final TriplexLineConfiguration configuration) {
        super(phases, from, to, length, configuration);
    }

    public TriplexLine(final Builder builder) {
        super(builder);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_line";
    }

    public static class Builder extends Line.AbstractBuilder<TriplexLineConductor, TriplexLineConfiguration, TriplexLine, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TriplexLine build() {
            return new TriplexLine(this);
        }

    }

}
