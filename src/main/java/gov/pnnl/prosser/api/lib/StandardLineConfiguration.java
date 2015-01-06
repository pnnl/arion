/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * Standard Line configuration for Overhead and other lines
 *
 * @author nord229
 */
public class StandardLineConfiguration<C extends Conductor> extends LineConfiguration<C> {

    private final C phaseAConductor;

    private final C phaseBConductor;

    private final C phaseCConductor;

    private final C phaseNConductor;

    private final LineSpacing spacing;

    public StandardLineConfiguration() {
        this.phaseAConductor = null;
        this.phaseBConductor = null;
        this.phaseCConductor = null;
        this.phaseNConductor = null;
        this.spacing = null;
    }

    public StandardLineConfiguration(final String name, final C phaseAConductor, final C phaseBConductor,
            final C phaseCConductor, final C phaseNConductor, final LineSpacing spacing) {
        super(name);
        this.phaseAConductor = phaseAConductor;
        this.phaseBConductor = phaseBConductor;
        this.phaseCConductor = phaseCConductor;
        this.phaseNConductor = phaseNConductor;
        this.spacing = spacing;
    }

    public StandardLineConfiguration(final Builder<C> builder) {
        super(builder);
        this.phaseAConductor = builder.phaseAConductor;
        this.phaseBConductor = builder.phaseBConductor;
        this.phaseCConductor = builder.phaseCConductor;
        this.phaseNConductor = builder.phaseNConductor;
        this.spacing = builder.spacing;
    }

    /**
     * @return the phaseAConductor
     */
    public C getPhaseAConductor() {
        return phaseAConductor;
    }

    /**
     * @return the phaseBConductor
     */
    public C getPhaseBConductor() {
        return phaseBConductor;
    }

    /**
     * @return the phaseCConductor
     */
    public C getPhaseCConductor() {
        return phaseCConductor;
    }

    /**
     * @return the phaseNConductor
     */
    public C getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * @return the spacing
     */
    public LineSpacing getSpacing() {
        return spacing;
    }

    @Override
    public String getGLDObjectType() {
        return "line_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "conductor_A", this.phaseAConductor);
        GLDUtils.writeProperty(sb, "conductor_B", this.phaseBConductor);
        GLDUtils.writeProperty(sb, "conductor_C", this.phaseCConductor);
        GLDUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GLDUtils.writeProperty(sb, "spacing", this.spacing);
    }

    public static class Builder<C extends Conductor> extends LineConfiguration.AbstractBuilder<C, StandardLineConfiguration<C>, Builder<C>> {

        private C phaseAConductor;

        private C phaseBConductor;

        private C phaseCConductor;

        private C phaseNConductor;

        private LineSpacing spacing;

        public Builder<C> phaseAConductor(final C phaseAConductor) {
            this.phaseAConductor = phaseAConductor;
            return this;
        }

        public Builder<C> phaseBConductor(final C phaseBConductor) {
            this.phaseBConductor = phaseBConductor;
            return this;
        }

        public Builder<C> phaseCConductor(final C phaseCConductor) {
            this.phaseCConductor = phaseCConductor;
            return this;
        }

        public Builder<C> phaseNConductor(final C phaseNConductor) {
            this.phaseNConductor = phaseNConductor;
            return this;
        }

        public Builder<C> spacing(final LineSpacing spacing) {
            this.spacing = spacing;
            return this;
        }

        @Override
        public StandardLineConfiguration<C> build() {
            return new StandardLineConfiguration<C>(this);
        }

        @Override
        protected Builder<C> self() {
            return this;
        }
    }

}
