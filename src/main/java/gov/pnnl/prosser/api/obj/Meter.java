/**
 *
 */
package gov.pnnl.prosser.api.obj;

import java.util.EnumSet;

/**
 * @author nord229
 *
 */
public class Meter extends Node {

    public Meter() {
    }

    public Meter(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
    }

    public Meter(final Builder builder) {
        super(builder);
    }

    @Override
    public String getGLDObjectType() {
        return "meter";
    }

    public static class Builder extends Node.AbstractBuilder<Meter, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Meter build() {
            return new Meter(this);
        }

    }
}
