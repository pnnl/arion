/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;

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
    private double heatFraction;

    /**
     * base real power of the overall load in kW
     */
    private double basePower;

    /**
     * If the base power is a function of another value, enter that here
     */
    private String basePowerFn;

    /**
     * power factor for constant power portion
     */
    private double powerPf;

    /**
     * the fraction of the total power that is constant power
     */
    private double powerFraction;

    /**
     * power factor for constant current portion
     */
    private double currentPf;

    /**
     * the fraction of total power that is constant current
     */
    private double currentFraction;

    /**
     * power factor for constant impedance portion
     */
    private double impedancePf;

    /**
     * the fraction of total power that is constant impedance
     */
    private double impedanceFraction;

    public ZIPLoad(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the Fraction of ZIPload that is transferred as heat
     * 
     * @return the heatFraction
     */
    public double getHeatFraction() {
        return heatFraction;
    }

    /**
     * Set the Fraction of ZIPload that is transferred as heat
     * 
     * @param heatFraction
     *            the heatFraction to set
     */
    public void setHeatFraction(final double heatFraction) {
        this.heatFraction = heatFraction;
    }

    /**
     * Get the base real power of the overall load in kW
     * 
     * @return the basePower
     */
    public double getBasePower() {
        return basePower;
    }

    /**
     * Set the base real power of the overall load in kW
     * 
     * @param basePower
     *            the basePower to set
     */
    public void setBasePower(final double basePower) {
        this.basePower = basePower;
    }

    /**
     * Get the Base Power Function
     * If the base power is a function of another value, it will be here
     * 
     * @return the basePowerFn
     */
    public String getBasePowerFn() {
        return basePowerFn;
    }

    /**
     * Set the Base Power Function
     * If the base power is a function of another value, enter that here
     * 
     * @param basePowerFn
     *            the basePowerFn to set
     */
    public void setBasePowerFn(final String basePowerFn) {
        this.basePowerFn = basePowerFn;
    }

    /**
     * Get the power factor for constant power portion
     * 
     * @return the powerPf
     */
    public double getPowerPf() {
        return powerPf;
    }

    /**
     * Set the power factor for constant power portion
     * 
     * @param powerPf
     *            the powerPf to set
     */
    public void setPowerPf(final double powerPf) {
        this.powerPf = powerPf;
    }

    /**
     * Get the fraction of the total power that is constant power
     * 
     * @return the powerFraction
     */
    public double getPowerFraction() {
        return powerFraction;
    }

    /**
     * Set the fraction of the total power that is constant power
     * 
     * @param powerFraction
     *            the powerFraction to set
     */
    public void setPowerFraction(final double powerFraction) {
        this.powerFraction = powerFraction;
    }

    /**
     * Get the power factor for constant current portion
     * 
     * @return the currentPf
     */
    public double getCurrentPf() {
        return currentPf;
    }

    /**
     * Set the power factor for constant current portion
     * 
     * @param currentPf
     *            the currentPf to set
     */
    public void setCurrentPf(final double currentPf) {
        this.currentPf = currentPf;
    }

    /**
     * Get the fraction of total power that is constant current
     * 
     * @return the currentFraction
     */
    public double getCurrentFraction() {
        return currentFraction;
    }

    /**
     * Set the fraction of total power that is constant current
     * 
     * @param currentFraction
     *            the currentFraction to set
     */
    public void setCurrentFraction(final double currentFraction) {
        this.currentFraction = currentFraction;
    }

    /**
     * Get the power factor for constant impedance portion
     * 
     * @return the impedancePf
     */
    public double getImpedancePf() {
        return impedancePf;
    }

    /**
     * Set the power factor for constant impedance portion
     * 
     * @param impedancePf
     *            the impedancePf to set
     */
    public void setImpedancePf(final double impedancePf) {
        this.impedancePf = impedancePf;
    }

    /**
     * Get the fraction of total power that is constant impedance
     * 
     * @return the impedanceFraction
     */
    public double getImpedanceFraction() {
        return impedanceFraction;
    }

    /**
     * Set the fraction of total power that is constant impedance
     * 
     * @param impedanceFraction
     *            the impedanceFraction to set
     */
    public void setImpedanceFraction(final double impedanceFraction) {
        this.impedanceFraction = impedanceFraction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "ZIPload";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "heat_fraction", this.heatFraction);
        if (basePowerFn != null) {
            writeProperty(sb, "base_power", this.basePowerFn);
        } else {
            writeProperty(sb, "base_power", this.basePower, "kW");
        }
        writeProperty(sb, "power_pf", this.powerPf);
        writeProperty(sb, "power_fraction", this.powerFraction);
        writeProperty(sb, "current_pf", this.currentPf);
        writeProperty(sb, "current_fraction", this.currentFraction);
        writeProperty(sb, "impedance_pf", this.impedancePf);
        writeProperty(sb, "impedance_fraction", this.impedanceFraction);
    }

}
