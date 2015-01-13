/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GldUtils;

import org.apache.commons.math3.complex.Complex;

/**
 * Transformer configuration
 *
 * @author nord229
 */
public class TransformerConfiguration extends PowerflowLibrary {

    /**
     * connect type enum: Wye-Wye, single-phase, etc.
     */
    private final ConnectionType connectionType;

    /**
     * Defines location of the transformer installation
     */
    private final InstallationType installationType;

    /**
     * primary voltage level in L-L value kV
     */
    private final double primaryVoltage;

    /**
     * secondary voltage level kV
     */
    private final double secondaryVoltage;

    /**
     * kVA rating of transformer, total
     */

    private final Double powerRating;

    /**
     * kVA rating of transformer, phase A
     */
    private final double phaseARating;

    /**
     * kVA rating of transformer, phase B
     */
    private final Double phaseBRating;

    /**
     * kVA rating of transformer, phase C
     */
    private final Double phaseCRating;

    /**
     * Series impedance, pu
     */
    private final Complex impedance;

    /**
     * Shunt impedance on primary side, pu
     */
    private final Complex shuntImpedance;

    public TransformerConfiguration() {
        this.connectionType = null;
        this.installationType = null;
        this.phaseARating = 0;
        this.primaryVoltage = 0;
        this.secondaryVoltage = 0;
        this.impedance = null;
        this.shuntImpedance = null;
        this.powerRating = null;
        this.phaseCRating = null;
        this.phaseBRating = null;
    }

    public TransformerConfiguration(final String name, final ConnectionType connectionType, final Double powerRating,
            final double phaseARating, final Double phaseBRating, final Double phaseCRating, final double primaryVoltage,
            final double secondaryVoltage, final Complex impedance) {
        super(name);
        this.connectionType = connectionType;
        this.powerRating = powerRating;
        this.phaseARating = phaseARating;
        this.phaseBRating = phaseBRating;
        this.phaseCRating = phaseCRating;
        this.primaryVoltage = primaryVoltage;
        this.secondaryVoltage = secondaryVoltage;
        this.impedance = impedance;
        this.shuntImpedance = null;
        this.installationType = null;
    }

    public TransformerConfiguration(final String name, final ConnectionType connectionType, final InstallationType installationType,
            final double phaseARating, final double primaryVoltage, final double secondaryVoltage, final Complex impedance,
            final Complex shuntImpedance) {
        super(name);
        this.connectionType = connectionType;
        this.installationType = installationType;
        this.phaseARating = phaseARating;
        this.primaryVoltage = primaryVoltage;
        this.secondaryVoltage = secondaryVoltage;
        this.impedance = impedance;
        this.shuntImpedance = shuntImpedance;
        this.powerRating = null;
        this.phaseCRating = null;
        this.phaseBRating = null;
    }

    public TransformerConfiguration(final Builder builder) {
        super(builder);
        this.connectionType = builder.connectionType;
        this.installationType = builder.installationType;
        this.primaryVoltage = builder.primaryVoltage;
        this.secondaryVoltage = builder.secondaryVoltage;
        this.powerRating = builder.powerRating;
        this.phaseARating = builder.phaseARating;
        this.phaseBRating = builder.phaseBRating;
        this.phaseCRating = builder.phaseCRating;
        this.impedance = builder.impedance;
        this.shuntImpedance = builder.shuntImpedance;
    }

    /**
     * @return the connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * @return the installationType
     */
    public InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * @return the primaryVoltage
     */
    public double getPrimaryVoltage() {
        return primaryVoltage;
    }

    /**
     * @return the secondaryVoltage
     */
    public double getSecondaryVoltage() {
        return secondaryVoltage;
    }

    /**
     * @return the powerRating
     */
    public Double getPowerRating() {
        return powerRating;
    }

    /**
     * @return the phaseARating
     */
    public double getPhaseARating() {
        return phaseARating;
    }

    /**
     * @return the phaseBRating
     */
    public Double getPhaseBRating() {
        return phaseBRating;
    }

    /**
     * @return the phaseCRating
     */
    public Double getPhaseCRating() {
        return phaseCRating;
    }

    /**
     * @return the impedance
     */
    public Complex getImpedance() {
        return impedance;
    }

    /**
     * @return the shuntImpedance
     */
    public Complex getShuntImpedance() {
        return shuntImpedance;
    }

    @Override
    public String getGLDObjectType() {
        return "transformer_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "connect_type", this.connectionType);
        GldUtils.writeProperty(sb, "install_type", this.installationType);
        GldUtils.writeProperty(sb, "power_rating", this.powerRating);
        GldUtils.writeProperty(sb, "powerA_rating", this.phaseARating, "kVA");
        GldUtils.writeProperty(sb, "powerB_rating", this.phaseBRating);
        GldUtils.writeProperty(sb, "powerC_rating", this.phaseCRating);
        GldUtils.writeProperty(sb, "primary_voltage", this.primaryVoltage);
        GldUtils.writeProperty(sb, "secondary_voltage", this.secondaryVoltage);
        // GLDUtils.writeProperty(sb, "resistance", this.impedance.getReal());
        // GLDUtils.writeProperty(sb, "reactance", this.impedance.getImaginary());
        GldUtils.writeProperty(sb, "impedance", this.impedance);
        GldUtils.writeProperty(sb, "shunt_impedance", this.shuntImpedance);
    }

    public static class Builder extends PowerflowLibrary.AbstractBuilder<TransformerConfiguration, Builder> {

        private ConnectionType connectionType;

        private InstallationType installationType;

        private double primaryVoltage;

        private double secondaryVoltage;

        private Double powerRating;

        private double phaseARating;

        private Double phaseBRating;

        private Double phaseCRating;

        private Complex impedance;

        private Complex shuntImpedance;

        public Builder connectionType(final ConnectionType connectionType) {
            this.connectionType = connectionType;
            return this;
        }

        public Builder installationType(final InstallationType installationType) {
            this.installationType = installationType;
            return this;
        }

        public Builder primaryVoltage(final double primaryVoltage) {
            this.primaryVoltage = primaryVoltage;
            return this;
        }

        public Builder secondaryVoltage(final double secondaryVoltage) {
            this.secondaryVoltage = secondaryVoltage;
            return this;
        }

        public Builder powerRating(final Double powerRating) {
            this.powerRating = powerRating;
            return this;
        }

        public Builder phaseARating(final double phaseARating) {
            this.phaseARating = phaseARating;
            return this;
        }

        public Builder phaseBRating(final Double phaseBRating) {
            this.phaseBRating = phaseBRating;
            return this;
        }

        public Builder phaseCRating(final Double phaseCRating) {
            this.phaseCRating = phaseCRating;
            return this;
        }

        public Builder impedance(final Complex impedance) {
            this.impedance = impedance;
            return this;
        }

        public Builder impedance(final double real, final double imaginary) {
            this.impedance = new Complex(real, imaginary);
            return this;
        }

        public Builder shuntImpedance(final Complex shuntImpedance) {
            this.shuntImpedance = shuntImpedance;
            return this;
        }

        public Builder shuntImpedance(final double real, final double imaginary) {
            this.shuntImpedance = new Complex(real, imaginary);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TransformerConfiguration build() {
            return new TransformerConfiguration(this);
        }

    }

}
