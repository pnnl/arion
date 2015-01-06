/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * ZipLoad Object
 *
 * @author nord229
 */
public class ZIPLoad extends ResidentialEnduse {

    // FIXME deal with enduse struct somehow

    /**
     * Fraction of ZIPload that is transferred as heat
     */
    private final double heatFraction;

    /**
     * base real power of the overall load in kW
     */
    private final double basePower;

    /**
     * power factor for constant power portion
     */
    private final double powerPf;

    private final double powerFraction;

    /**
     * power factor for constant current portion
     */
    private final double currentPf;

    private final double currentFraction;

    /**
     * power factor for constant impedance portion
     */
    private final double impedancePf;

    private final double impedanceFraction;

    public ZIPLoad() {
        this.heatFraction = 0;
        this.basePower = 0;
        this.powerPf = 0;
        this.powerFraction = 0;
        this.currentPf = 0;
        this.currentFraction = 0;
        this.impedancePf = 0;
        this.impedanceFraction = 0;
    }

    public ZIPLoad(final double heatFraction, final double basePower, final double powerPf, final double powerFraction,
            final double currentPf, final double currentFraction, final double impedancePf, final double impedanceFraction) {
        this.heatFraction = heatFraction;
        this.basePower = basePower;
        this.powerPf = powerPf;
        this.powerFraction = powerFraction;
        this.currentPf = currentPf;
        this.currentFraction = currentFraction;
        this.impedancePf = impedancePf;
        this.impedanceFraction = impedanceFraction;
    }

    public ZIPLoad(final Builder builder) {
        super(builder);
        this.heatFraction = builder.heatFraction;
        this.basePower = builder.basePower;
        this.powerPf = builder.powerPf;
        this.powerFraction = builder.powerFraction;
        this.currentPf = builder.currentPf;
        this.currentFraction = builder.currentFraction;
        this.impedancePf = builder.impedancePf;
        this.impedanceFraction = builder.impedanceFraction;
    }

    /**
     * @return the heatFraction
     */
    public double getHeatFraction() {
        return heatFraction;
    }

    /**
     * @return the basePower
     */
    public double getBasePower() {
        return basePower;
    }

    /**
     * @return the powerPf
     */
    public double getPowerPf() {
        return powerPf;
    }

    /**
     * @return the powerFraction
     */
    public double getPowerFraction() {
        return powerFraction;
    }

    /**
     * @return the currentPf
     */
    public double getCurrentPf() {
        return currentPf;
    }

    /**
     * @return the currentFraction
     */
    public double getCurrentFraction() {
        return currentFraction;
    }

    /**
     * @return the impedancePf
     */
    public double getImpedancePf() {
        return impedancePf;
    }

    /**
     * @return the impedanceFraction
     */
    public double getImpedanceFraction() {
        return impedanceFraction;
    }

    @Override
    public String getGLDObjectType() {
        return "ZIPload";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "heat_fraction", this.heatFraction);
        GLDUtils.writeProperty(sb, "base_power", this.basePower, "kW");
        GLDUtils.writeProperty(sb, "power_pf", this.powerPf);
        GLDUtils.writeProperty(sb, "power_fraction", this.powerFraction);
        GLDUtils.writeProperty(sb, "current_pf", this.currentPf);
        GLDUtils.writeProperty(sb, "current_fraction", this.currentFraction);
        GLDUtils.writeProperty(sb, "impedance_pf", this.impedancePf);
        GLDUtils.writeProperty(sb, "impedance_fraction", this.impedanceFraction);
    }

    public static class Builder extends ResidentialEnduse.AbstractBuilder<ZIPLoad, Builder> {

        private double heatFraction;

        private double basePower;

        private double powerPf;

        private double powerFraction;

        private double currentPf;

        private double currentFraction;

        private double impedancePf;

        private double impedanceFraction;

        public Builder heatFraction(final double heatFraction) {
            this.heatFraction = heatFraction;
            return this;
        }

        public Builder basePower(final double basePower) {
            this.basePower = basePower;
            return this;
        }

        public Builder powerPf(final double powerPf) {
            this.powerPf = powerPf;
            return this;
        }

        public Builder powerFraction(final double powerFraction) {
            this.powerFraction = powerFraction;
            return this;
        }

        public Builder currentPf(final double currentPf) {
            this.currentPf = currentPf;
            return this;
        }

        public Builder currentFraction(final double currentFraction) {
            this.currentFraction = currentFraction;
            return this;
        }

        public Builder impedancePf(final double impedancePf) {
            this.impedancePf = impedancePf;
            return this;
        }

        public Builder impedanceFraction(final double impedanceFraction) {
            this.impedanceFraction = impedanceFraction;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public ZIPLoad build() {
            return new ZIPLoad(this);
        }

    }

}
