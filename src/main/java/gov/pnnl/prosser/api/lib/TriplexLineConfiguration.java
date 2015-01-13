/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GldUtils;

/**
 * Triplex Line Configuration
 *
 * @author nord229
 */
public class TriplexLineConfiguration extends LineConfiguration<TriplexLineConductor> {

    private final TriplexLineConductor phase1Conductor;

    private final TriplexLineConductor phase2Conductor;

    private final TriplexLineConductor phaseNConductor;

    /**
     * total diameter of cable in inches
     */
    private final double insulationThickness;

    /**
     * thickness of insulation around cabling in inches
     */
    private final double diameter;

    public TriplexLineConfiguration() {
        this.phase1Conductor = null;
        this.phase2Conductor = null;
        this.phaseNConductor = null;
        this.insulationThickness = 0;
        this.diameter = 0;
    }

    public TriplexLineConfiguration(final String name, final TriplexLineConductor phase1Conductor,
            final TriplexLineConductor phase2Conductor, final TriplexLineConductor phaseNConductor,
            final double insulationThickness, final double diameter) {
        super(name);
        this.phase1Conductor = phase1Conductor;
        this.phase2Conductor = phase2Conductor;
        this.phaseNConductor = phaseNConductor;
        this.insulationThickness = insulationThickness;
        this.diameter = diameter;
    }

    public TriplexLineConfiguration(final Builder builder) {
        super(builder);
        this.phase1Conductor = builder.phase1Conductor;
        this.phase2Conductor = builder.phase2Conductor;
        this.phaseNConductor = builder.phaseNConductor;
        this.insulationThickness = builder.insulationThickness;
        this.diameter = builder.diameter;
    }

    /**
     * @return the insulationThickness
     */
    public double getInsulationThickness() {
        return insulationThickness;
    }

    /**
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_line_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "conductor_1", this.phase1Conductor);
        GldUtils.writeProperty(sb, "conductor_2", this.phase2Conductor);
        GldUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GldUtils.writeProperty(sb, "insulation_thickness", this.insulationThickness);
        GldUtils.writeProperty(sb, "diameter", this.diameter);
    }

    public static class Builder extends LineConfiguration.AbstractBuilder<TriplexLineConductor, TriplexLineConfiguration, Builder> {

        private TriplexLineConductor phase1Conductor;

        private TriplexLineConductor phase2Conductor;

        private TriplexLineConductor phaseNConductor;

        private double insulationThickness;

        private double diameter;

        public Builder phase1Conductor(final TriplexLineConductor phase1Conductor) {
            this.phase1Conductor = phase1Conductor;
            return this;
        }

        public Builder phase2Conductor(final TriplexLineConductor phase2Conductor) {
            this.phase2Conductor = phase2Conductor;
            return this;
        }

        public Builder phaseNConductor(final TriplexLineConductor phaseNConductor) {
            this.phaseNConductor = phaseNConductor;
            return this;
        }

        public Builder insulationThickness(final double insulationThickness) {
            this.insulationThickness = insulationThickness;
            return this;
        }

        public Builder diameter(final double diameter) {
            this.diameter = diameter;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TriplexLineConfiguration build() {
            return new TriplexLineConfiguration(this);
        }

    }

}
