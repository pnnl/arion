/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * @author nord229
 *
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

    public ZIPLoad() {
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
    public String getGLDObjectType() {
        return "ZIPload";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.appendProperty(sb, "heat_fraction", this.heatFraction);
        GLDUtils.appendProperty(sb, "base_power", this.basePower, "kW");
        GLDUtils.appendProperty(sb, "power_pf", this.powerPf);
        GLDUtils.appendProperty(sb, "power_fraction", this.powerFraction);
        GLDUtils.appendProperty(sb, "current_pf", this.currentPf);
        GLDUtils.appendProperty(sb, "current_fraction", this.currentFraction);
        GLDUtils.appendProperty(sb, "impedance_pf", this.impedancePf);
        GLDUtils.appendProperty(sb, "impedance_fraction", this.impedanceFraction);
    }

}
