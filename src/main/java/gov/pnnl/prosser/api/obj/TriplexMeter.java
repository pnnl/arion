/**
 *
 */
package gov.pnnl.prosser.api.obj;

import java.util.EnumSet;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    public TriplexMeter() {
    }

    public TriplexMeter(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
    }

    public TriplexMeter(final Builder builder) {
        super(builder);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_meter";
    }

    public static class Builder extends TriplexNode.AbstractBuilder<TriplexMeter, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TriplexMeter build() {
            return new TriplexMeter(this);
        }

    }

}
