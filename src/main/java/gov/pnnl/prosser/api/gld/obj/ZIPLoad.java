/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
 *    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
 *    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 *    and may permit others to do so, subject to the following conditions:
 *    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
 *       the following disclaimers.
 *    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *       the following disclaimer in the documentation and/or other materials provided with the distribution.
 *    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
 *       form whatsoever without the express written consent of Battelle.
 * 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 *    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *                                PACIFIC NORTHWEST NATIONAL LABORATORY
 *                                            operated by
 *                                              BATTELLE
 *                                              for the
 *                                  UNITED STATES DEPARTMENT OF ENERGY
 *                                   under Contract DE-AC05-76RL01830
 *******************************************************************************/
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), heatFraction, basePower, basePowerFn, powerPf, powerFraction, currentPf, currentFraction,
                impedancePf, impedanceFraction);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZIPLoad other = (ZIPLoad) obj;
        return Objects.equals(this.heatFraction, other.heatFraction)
                && Objects.equals(this.basePower, other.basePower)
                && Objects.equals(this.basePowerFn, other.basePowerFn)
                && Objects.equals(this.powerPf, other.powerPf)
                && Objects.equals(this.powerFraction, other.powerFraction)
                && Objects.equals(this.currentPf, other.currentPf)
                && Objects.equals(this.currentFraction, other.currentFraction)
                && Objects.equals(this.impedancePf, other.impedancePf)
                && Objects.equals(this.impedanceFraction, other.impedanceFraction);
    }

}
