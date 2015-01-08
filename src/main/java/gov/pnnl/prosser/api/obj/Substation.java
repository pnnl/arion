/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * @author nord229
 *
 */
public class Substation extends Node {

    private final PhaseCode referencePhase;

    public Substation() {
        this.referencePhase = null;
    }

    public Substation(final Builder builder) {
        super(builder);
        this.referencePhase = builder.referencePhase;
    }

    /**
     * @return the referencePhase
     */
    public PhaseCode getReferencePhase() {
        return referencePhase;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.writeProperty(sb, "reference_phase", "PHASE_" + this.referencePhase.name());
    }

    @Override
    public String getGLDObjectType() {
        return "substation";
    }

    public static class Builder extends Node.AbstractBuilder<Substation, Builder> {

        private PhaseCode referencePhase;

        public Builder referencePhase(final PhaseCode referencePhase) {
            this.referencePhase = referencePhase;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Substation build() {
            return new Substation(this);
        }

    }

}
