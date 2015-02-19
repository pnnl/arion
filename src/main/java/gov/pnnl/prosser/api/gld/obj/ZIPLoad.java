/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldUtils;

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

    private String basePowerFn;

    /**
     * power factor for constant power portion
     */
    private double powerPf;

    private double powerFraction;

    /**
     * power factor for constant current portion
     */
    private double currentPf;

    private double currentFraction;

    /**
     * power factor for constant impedance portion
     */
    private double impedancePf;

    private double impedanceFraction;

    /**
     * @return the heatFraction
     */
    public double getHeatFraction() {
        return heatFraction;
    }

    /**
     * @param heatFraction
     *            the heatFraction to set
     */
    public void setHeatFraction(final double heatFraction) {
        this.heatFraction = heatFraction;
    }

    /**
     * @return the basePower
     */
    public double getBasePower() {
        return basePower;
    }

    /**
     * @param basePower
     *            the basePower to set
     */
    public void setBasePower(final double basePower) {
        this.basePower = basePower;
    }

    /**
     * @return the basePowerFn
     */
    public String getBasePowerFn() {
        return basePowerFn;
    }

    /**
     * @param basePowerFn
     *            the basePowerFn to set
     */
    public void setBasePowerFn(final String basePowerFn) {
        this.basePowerFn = basePowerFn;
    }

    /**
     * @return the powerPf
     */
    public double getPowerPf() {
        return powerPf;
    }

    /**
     * @param powerPf
     *            the powerPf to set
     */
    public void setPowerPf(final double powerPf) {
        this.powerPf = powerPf;
    }

    /**
     * @return the powerFraction
     */
    public double getPowerFraction() {
        return powerFraction;
    }

    /**
     * @param powerFraction
     *            the powerFraction to set
     */
    public void setPowerFraction(final double powerFraction) {
        this.powerFraction = powerFraction;
    }

    /**
     * @return the currentPf
     */
    public double getCurrentPf() {
        return currentPf;
    }

    /**
     * @param currentPf
     *            the currentPf to set
     */
    public void setCurrentPf(final double currentPf) {
        this.currentPf = currentPf;
    }

    /**
     * @return the currentFraction
     */
    public double getCurrentFraction() {
        return currentFraction;
    }

    /**
     * @param currentFraction
     *            the currentFraction to set
     */
    public void setCurrentFraction(final double currentFraction) {
        this.currentFraction = currentFraction;
    }

    /**
     * @return the impedancePf
     */
    public double getImpedancePf() {
        return impedancePf;
    }

    /**
     * @param impedancePf
     *            the impedancePf to set
     */
    public void setImpedancePf(final double impedancePf) {
        this.impedancePf = impedancePf;
    }

    /**
     * @return the impedanceFraction
     */
    public double getImpedanceFraction() {
        return impedanceFraction;
    }

    /**
     * @param impedanceFraction
     *            the impedanceFraction to set
     */
    public void setImpedanceFraction(final double impedanceFraction) {
        this.impedanceFraction = impedanceFraction;
    }

    @Override
    public String getGldObjectType() {
        return "ZIPload";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "heat_fraction", this.heatFraction);
        if (basePowerFn != null) {
            GldUtils.writeProperty(sb, "base_power", this.basePowerFn);
        } else {
            GldUtils.writeProperty(sb, "base_power", this.basePower, "kW");
        }
        GldUtils.writeProperty(sb, "power_pf", this.powerPf);
        GldUtils.writeProperty(sb, "power_fraction", this.powerFraction);
        GldUtils.writeProperty(sb, "current_pf", this.currentPf);
        GldUtils.writeProperty(sb, "current_fraction", this.currentFraction);
        GldUtils.writeProperty(sb, "impedance_pf", this.impedancePf);
        GldUtils.writeProperty(sb, "impedance_fraction", this.impedanceFraction);
    }

}
