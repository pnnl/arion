/**
 *
 */
package gov.pnnl.prosser.api.pwr.lib;

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
    private ConnectionType connectionType;

    /**
     * Defines location of the transformer installation
     */
    private InstallationType installationType;

    /**
     * primary voltage level in L-L value kV
     */
    private double primaryVoltage;

    /**
     * secondary voltage level kV
     */
    private double secondaryVoltage;

    /**
     * kVA rating of transformer, total
     */

    private Double powerRating;

    /**
     * kVA rating of transformer, phase A
     */
    private double phaseARating;

    /**
     * kVA rating of transformer, phase B
     */
    private Double phaseBRating;

    /**
     * kVA rating of transformer, phase C
     */
    private Double phaseCRating;

    /**
     * Series impedance, pu
     */
    private Complex impedance;

    /**
     * Shunt impedance on primary side, pu
     */
    private Complex shuntImpedance;

    /**
     * @return the connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * @param connectionType
     *            the connectionType to set
     */
    public void setConnectionType(final ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * @return the installationType
     */
    public InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * @param installationType
     *            the installationType to set
     */
    public void setInstallationType(final InstallationType installationType) {
        this.installationType = installationType;
    }

    /**
     * @return the primaryVoltage
     */
    public double getPrimaryVoltage() {
        return primaryVoltage;
    }

    /**
     * @param primaryVoltage
     *            the primaryVoltage to set
     */
    public void setPrimaryVoltage(final double primaryVoltage) {
        this.primaryVoltage = primaryVoltage;
    }

    /**
     * @return the secondaryVoltage
     */
    public double getSecondaryVoltage() {
        return secondaryVoltage;
    }

    /**
     * @param secondaryVoltage
     *            the secondaryVoltage to set
     */
    public void setSecondaryVoltage(final double secondaryVoltage) {
        this.secondaryVoltage = secondaryVoltage;
    }

    /**
     * @return the powerRating
     */
    public Double getPowerRating() {
        return powerRating;
    }

    /**
     * @param powerRating
     *            the powerRating to set
     */
    public void setPowerRating(final Double powerRating) {
        this.powerRating = powerRating;
    }

    /**
     * @return the phaseARating
     */
    public double getPhaseARating() {
        return phaseARating;
    }

    /**
     * @param phaseARating
     *            the phaseARating to set
     */
    public void setPhaseARating(final double phaseARating) {
        this.phaseARating = phaseARating;
    }

    /**
     * @return the phaseBRating
     */
    public Double getPhaseBRating() {
        return phaseBRating;
    }

    /**
     * @param phaseBRating
     *            the phaseBRating to set
     */
    public void setPhaseBRating(final Double phaseBRating) {
        this.phaseBRating = phaseBRating;
    }

    /**
     * @return the phaseCRating
     */
    public Double getPhaseCRating() {
        return phaseCRating;
    }

    /**
     * @param phaseCRating
     *            the phaseCRating to set
     */
    public void setPhaseCRating(final Double phaseCRating) {
        this.phaseCRating = phaseCRating;
    }

    /**
     * @return the impedance
     */
    public Complex getImpedance() {
        return impedance;
    }

    /**
     * @param impedance
     *            the impedance to set
     */
    public void setImpedance(final Complex impedance) {
        this.impedance = impedance;
    }

    /**
     * @param real
     *            the real part of the complex number for impedance
     * @param imaginary
     *            the imaginary part of the complex number for impedance
     */
    public void setImpedance(final double real, final double imaginary) {
        this.impedance = new Complex(real, imaginary);
    }

    /**
     * @return the shuntImpedance
     */
    public Complex getShuntImpedance() {
        return shuntImpedance;
    }

    /**
     * @param shuntImpedance
     *            the shuntImpedance to set
     */
    public void setShuntImpedance(final Complex shuntImpedance) {
        this.shuntImpedance = shuntImpedance;
    }

    /**
     * @param real
     *            the real part of the complex number for shunt impedance
     * @param imaginary
     *            the imaginary part of the complex number for shunt impedance
     */
    public void setShuntImpedance(final double real, final double imaginary) {
        this.shuntImpedance = new Complex(real, imaginary);
    }

    @Override
    public String getGldObjectType() {
        return "transformer_configuration";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
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

}
