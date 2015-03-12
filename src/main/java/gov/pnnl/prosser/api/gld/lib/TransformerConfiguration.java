/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.InstallationType;

import org.apache.commons.math3.complex.Complex;

/**
 * Transformer configuration
 *
 * @author nord229
 */
public class TransformerConfiguration extends PowerflowLibrary {

    /**
     * connection type enum: Wye-Wye, single-phase, etc.
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
    private Double phaseARating;

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

    public TransformerConfiguration(final TransformerConfiguration config) {
        this.setName(config.getName());
        this.setGroupId(config.getGroupId());
        this.connectionType = config.connectionType;
        this.installationType = config.installationType;
        this.primaryVoltage = config.primaryVoltage;
        this.secondaryVoltage = config.secondaryVoltage;
        this.powerRating = config.powerRating;
        this.phaseARating = config.phaseARating;
        this.phaseBRating = config.phaseBRating;
        this.phaseCRating = config.phaseCRating;
        this.impedance = config.impedance;
        this.shuntImpedance = config.shuntImpedance;
    }

    /**
     * Get the connection type enum: Wye-Wye, single-phase, etc.
     * 
     * @return the connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * Set the connection type enum: Wye-Wye, single-phase, etc.
     * 
     * @param connectionType
     *            the connectionType to set
     */
    public void setConnectionType(final ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * Get the location of the transformer installation
     * 
     * @return the installationType
     */
    public InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * Set the location of the transformer installation
     * 
     * @param installationType
     *            the installationType to set
     */
    public void setInstallationType(final InstallationType installationType) {
        this.installationType = installationType;
    }

    /**
     * Get the primary voltage level in L-L value kV
     * 
     * @return the primaryVoltage
     */
    public double getPrimaryVoltage() {
        return primaryVoltage;
    }

    /**
     * Set the primary voltage level in L-L value kV
     * 
     * @param primaryVoltage
     *            the primaryVoltage to set
     */
    public void setPrimaryVoltage(final double primaryVoltage) {
        this.primaryVoltage = primaryVoltage;
    }

    /**
     * Get the secondary voltage level kV
     * 
     * @return the secondaryVoltage
     */
    public double getSecondaryVoltage() {
        return secondaryVoltage;
    }

    /**
     * Set the secondary voltage level kV
     * 
     * @param secondaryVoltage
     *            the secondaryVoltage to set
     */
    public void setSecondaryVoltage(final double secondaryVoltage) {
        this.secondaryVoltage = secondaryVoltage;
    }

    /**
     * Get the kVA rating of transformer, total
     * 
     * @return the powerRating
     */
    public Double getPowerRating() {
        return powerRating;
    }

    /**
     * Set the kVA rating of transformer, total
     * 
     * @param powerRating
     *            the powerRating to set
     */
    public void setPowerRating(final Double powerRating) {
        this.powerRating = powerRating;
    }

    /**
     * Get the kVA rating of transformer, phase A
     * 
     * @return the phaseARating
     */
    public Double getPhaseARating() {
        return phaseARating;
    }

    /**
     * Set the kVA rating of transformer, phase A
     * 
     * @param phaseARating
     *            the phaseARating to set
     */
    public void setPhaseARating(final Double phaseARating) {
        this.phaseARating = phaseARating;
    }

    /**
     * Get the kVA rating of transformer, phase B
     * 
     * @return the phaseBRating
     */
    public Double getPhaseBRating() {
        return phaseBRating;
    }

    /**
     * Set the kVA rating of transformer, phase B
     * 
     * @param phaseBRating
     *            the phaseBRating to set
     */
    public void setPhaseBRating(final Double phaseBRating) {
        this.phaseBRating = phaseBRating;
    }

    /**
     * Get the kVA rating of transformer, phase C
     * 
     * @return the phaseCRating
     */
    public Double getPhaseCRating() {
        return phaseCRating;
    }

    /**
     * Set the kVA rating of transformer, phase C
     * 
     * @param phaseCRating
     *            the phaseCRating to set
     */
    public void setPhaseCRating(final Double phaseCRating) {
        this.phaseCRating = phaseCRating;
    }

    /**
     * Get the Series impedance, pu
     * 
     * @return the impedance
     */
    public Complex getImpedance() {
        return impedance;
    }

    /**
     * Set the Series impedance, pu
     * 
     * @param impedance
     *            the impedance to set
     */
    public void setImpedance(final Complex impedance) {
        this.impedance = impedance;
    }

    /**
     * Set the Series impedance, pu
     * 
     * @param real
     *            the real part of the complex number for impedance
     * @param imaginary
     *            the imaginary part of the complex number for impedance
     */
    public void setImpedance(final double real, final double imaginary) {
        this.impedance = new Complex(real, imaginary);
    }

    /**
     * Get the Shunt impedance on primary side, pu
     * 
     * @return the shuntImpedance
     */
    public Complex getShuntImpedance() {
        return shuntImpedance;
    }

    /**
     * Set the Shunt impedance on primary side, pu
     * 
     * @param shuntImpedance
     *            the shuntImpedance to set
     */
    public void setShuntImpedance(final Complex shuntImpedance) {
        this.shuntImpedance = shuntImpedance;
    }

    /**
     * Set the Shunt impedance on primary side, pu
     * 
     * @param real
     *            the real part of the complex number for shunt impedance
     * @param imaginary
     *            the imaginary part of the complex number for shunt impedance
     */
    public void setShuntImpedance(final double real, final double imaginary) {
        this.shuntImpedance = new Complex(real, imaginary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "transformer_configuration";
    }

    /**
     * {@inheritDoc}
     */
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
