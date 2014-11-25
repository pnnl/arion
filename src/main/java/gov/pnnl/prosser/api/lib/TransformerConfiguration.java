/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

import org.apache.commons.math3.complex.Complex;

/**
 * Transformer configuration
 *
 * @author nord229
 */
public class TransformerConfiguration extends PowerflowLibrary {

    public enum ConnectionType {
        WYE_WYE,
        DELTA_DELTA,
        DELTA_GWYE,
        SINGLE_PHASE,
        SINGLE_PHASE_CENTER_TAPPED;
    }

    public enum InstallationType {
        POLETOP,
        PADMOUNT,
        VAULT;
    }

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

    public TransformerConfiguration() {
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
    }

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

    @Override
    public String getGLDObjectType() {
        return "transformer_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "connect_type", this.connectionType);
        GLDUtils.writeProperty(sb, "install_type", this.installationType);
        GLDUtils.writeProperty(sb, "power_rating", this.powerRating);
        GLDUtils.writeProperty(sb, "powerA_rating", this.phaseARating, "kVA");
        GLDUtils.writeProperty(sb, "powerB_rating", this.phaseBRating);
        GLDUtils.writeProperty(sb, "powerC_rating", this.phaseCRating);
        GLDUtils.writeProperty(sb, "primary_voltage", this.primaryVoltage);
        GLDUtils.writeProperty(sb, "secondary_voltage", this.secondaryVoltage);
        // GLDUtils.writeProperty(sb, "resistance", this.impedance.getReal());
        // GLDUtils.writeProperty(sb, "reactance", this.impedance.getImaginary());
        GLDUtils.writeProperty(sb, "impedance", this.impedance);
        GLDUtils.writeProperty(sb, "shunt_impedance", this.shuntImpedance);
    }

}
