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

import org.apache.commons.math3.complex.Complex;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.*;

/**
 * @author sund130
 *
 */
public class Inverter extends AbstractGldObject {
    private InverterType inverterType;
    private FourQuadrantControlMode fourQuadrantControlMode;
    private GeneratorMode generatorMode;
    private GeneratorStatus generatorStatus;
    private Complex vIn;
    private Complex iIn;
    private double powerFactor;
    private double pOut;
    private double qOut;
    private boolean useMultipointEfficiency;
    private InverterManufacturer inverterManufacturer;
    private double maximumDcPower;
    private double maximumDcVoltage;
    private double minimumDcPower;
    private double c0;
    private double c1;
    private double c2;
    private double c3;
    private PowerflowObject senseObject;
    private double maxChargeRate;
    private double maxDischargeRate;
    private double chargeOnThreshold;
    private double chargeOffThreshold;
    private double dischargeOnThreshold;
    private double dischargeOffThreshold;
    private double excessInputPower;
    private double chargeLockoutTime;
    private double dischargeLockoutTime;
    private double pfRegActivate;
    private double pfRegDeactivate;
    private double pfRegActivateLockoutTime;
    private double chargeThreshold;
    private double dischargeThreshold;
    private double groupMaxChargeRate;
    private double groupMaxDischargeRate;
    private double groupRatedPower;
    private double vBase;
    private double v1;
    private double v2;
    private double v3;
    private double v4;
    private double q1;
    private double q2;
    private double q3;
    private double q4;

    /**
     * @param simulator
     */
    public Inverter(GldSimulator simulator) {
        super(simulator);
    }
    /**
     * @return the inverterType
     */
    public InverterType getInverterType() {
        return inverterType;
    }
    /**
     * @param inverterType
     * Defines type of inverter technology and efficiency of the unit
     */
    public void setInverterType(InverterType inverterType) {
        this.inverterType = inverterType;
    }
    /**
     * @return the fourQuadrantControlMode
     */
    public FourQuadrantControlMode getFourQuadrantControlMode() {
        return fourQuadrantControlMode;
    }
    /**
     * @param fourQuadrantControlMode
     * Control mode of the inverter when inverterType is FOUR_QUADRANT
     */
    public void setFourQuadrantControlMode(FourQuadrantControlMode fourQuadrantControlMode) {
        this.fourQuadrantControlMode = fourQuadrantControlMode;
    }
    /**
     * @return the generatorMode
     */
    public GeneratorMode getGeneratorMode() {
        return generatorMode;
    }
    /**
     * @param generatorMode
     * REQUIRED
     * Control mode of the inverter
     */
    public void setGeneratorMode(GeneratorMode generatorMode) {
        this.generatorMode = generatorMode;
    }
    /**
     * @return the generatorStatus
     */
    public GeneratorStatus getGeneratorStatus() {
        return generatorStatus;
    }
    /**
     * @param generatorStatus
     * Defines if generator is in operation or not
     */
    public void setGeneratorStatus(GeneratorStatus generatorStatus) {
        this.generatorStatus = generatorStatus;
    }
    /**
     * @return the vIn
     */
    public Complex getvIn() {
        return vIn;
    }
    /**
     * @param vIn
     * DC voltage passed in by the DC object
     */
    public void setvIn(Complex vIn) {
        this.vIn = vIn;
    }
    /**
     * @return the iIn
     */
    public Complex getiIn() {
        return iIn;
    }
    /**
     * @param iIn
     * DC current passed in by the DC object
     */
    public void setiIn(Complex iIn) {
        this.iIn = iIn;
    }
    /**
     * @return the powerFactor
     */
    public double getPowerFactor() {
        return powerFactor;
    }
    /**
     * @param powerFactor
     * Defines desired power factor in generator mode CONSTANT PF mode
     * and in four quadrant control mode CONSTANT_PF
     */
    public void setPowerFactor(double powerFactor) {
        this.powerFactor = powerFactor;
    }
    /**
     * @return the pOut
     */
    public double getpOut() {
        return pOut;
    }
    /**
     * @param pOut
     * Value to output in four quadrant control mode CONSTANT_PQ
     */
    public void setpOut(double pOut) {
        this.pOut = pOut;
    }
    /**
     * @return the qOut
     */
    public double getqOut() {
        return qOut;
    }
    /**
     * @param qOut
     * Value to output in four quadrant control mode CONSTANT_PQ
     */
    public void setqOut(double qOut) {
        this.qOut = qOut;
    }
    /**
     * @return the useMultipointEfficiency
     */
    public boolean isUseMultipointEfficiency() {
        return useMultipointEfficiency;
    }
    /**
     * @param useMultipointEfficiency
     * A boolean flag to toggle using Sandia National Laboratory's
     * multipoint efficiency model
     */
    public void setUseMultipointEfficiency(boolean useMultipointEfficiency) {
        this.useMultipointEfficiency = useMultipointEfficiency;
    }
    /**
     * @return the inverterManufacturer
     */
    public InverterManufacturer getInverterManufacturer() {
        return inverterManufacturer;
    }
    /**
     * @param inverterManufacturer
     * Defies default parameters for the multipoint efficiency model for
     * an inverter from manufacturer
     */
    public void setInverterManufacturer(InverterManufacturer inverterManufacturer) {
        this.inverterManufacturer = inverterManufacturer;
    }
    /**
     * @return the maximumDcPower
     */
    public double getMaximumDcPower() {
        return maximumDcPower;
    }
    /**
     * @param maximumDcPower
     * The maximum DC power rating of the inverter,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setMaximumDcPower(double maximumDcPower) {
        this.maximumDcPower = maximumDcPower;
    }
    /**
     * @return the maximumDcVoltage
     */
    public double getMaximumDcVoltage() {
        return maximumDcVoltage;
    }
    /**
     * @param maximumDcVoltage
     * The maximum DC voltage rating of the inverter,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setMaximumDcVoltage(double maximumDcVoltage) {
        this.maximumDcVoltage = maximumDcVoltage;
    }
    /**
     * @return the minimumDcPower
     */
    public double getMinimumDcPower() {
        return minimumDcPower;
    }
    /**
     * @param minimumDcPower
     * The minimum DC voltage rating of the inverter,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setMinimumDcPower(double minimumDcPower) {
        this.minimumDcPower = minimumDcPower;
    }
    /**
     * @return the c0
     */
    public double getC0() {
        return c0;
    }
    /**
     * @param c0
     * The coefficient descibing the parabolic relationship
     * between AC and DC power of the inverter,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setC0(double c0) {
        this.c0 = c0;
    }
    /**
     * @return the c1
     */
    public double getC1() {
        return c1;
    }
    /**
     * @param c1
     * The coefficient allowing the maximum DC power
     * to vary linearly with DC voltage,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setC1(double c1) {
        this.c1 = c1;
    }
    /**
     * @return the c2
     */
    public double getC2() {
        return c2;
    }
    /**
     * @param c2
     *  The coefficient allowing the minimum DC power
     *  to vary linearly with DC voltage,
     *  only used when use_multipoint_efficiency is TRUE
     */
    public void setC2(double c2) {
        this.c2 = c2;
    }
    /**
     * @return the c3
     */
    public double getC3() {
        return c3;
    }
    /**
     * @param c3
     * The coefficient allowing c_0 to vary linearly with DC voltage,
     * only used when use_multipoint_efficiency is TRUE
     */
    public void setC3(double c3) {
        this.c3 = c3;
    }
    /**
     * @return the senseObject
     */
    public PowerflowObject getSenseObject() {
        return senseObject;
    }
    /**
     * @param senseObject
     * FOUR QUADRANT MODEL: name of the object the inverter is
     * trying to mitigate the load on (node/link) in LOAD_FOLLOWING
     */
    public void setSenseObject(PowerflowObject senseObject) {
        this.senseObject = senseObject;
    }
    /**
     * @return the maxChargeRate
     */
    public double getMaxChargeRate() {
        return maxChargeRate;
    }
    /**
     * @param maxChargeRate
     *  FOUR QUADRANT MODEL: name of the object the inverter is
     *  trying to mitigate the load on (node/link) in LOAD_FOLLOWING
     */
    public void setMaxChargeRate(double maxChargeRate) {
        this.maxChargeRate = maxChargeRate;
    }
    /**
     * @return the maxDischargeRate
     */
    public double getMaxDischargeRate() {
        return maxDischargeRate;
    }
    /**
     * @param maxDischargeRate
     * FOUR QUADRANT MODEL: maximum rate the battery can be
     * discharged in LOAD_FOLLOWING
     */
    public void setMaxDischargeRate(double maxDischargeRate) {
        this.maxDischargeRate = maxDischargeRate;
    }
    /**
     * @return the chargeOnThreshold
     */
    public double getChargeOnThreshold() {
        return chargeOnThreshold;
    }
    /**
     * @param chargeOnThreshold
     * FOUR QUADRANT MODEL: power level at which the inverter should
     * try charging the battery in LOAD_FOLLOWING
     */
    public void setChargeOnThreshold(double chargeOnThreshold) {
        this.chargeOnThreshold = chargeOnThreshold;
    }
    /**
     * @return the chargeOffThreshold
     */
    public double getChargeOffThreshold() {
        return chargeOffThreshold;
    }
    /**
     * @param chargeOffThreshold
     * FOUR QUADRANT MODEL: power level at which the inverter should
     * cease charging the battery in LOAD_FOLLOWING
     */
    public void setChargeOffThreshold(double chargeOffThreshold) {
        this.chargeOffThreshold = chargeOffThreshold;
    }
    /**
     * @return the dischargeOnThreshold
     */
    public double getDischargeOnThreshold() {
        return dischargeOnThreshold;
    }
    /**
     * @param dischargeOnThreshold
     * FOUR QUADRANT MODEL: power level at which the inverter should
     * try discharging the battery in LOAD_FOLLOWING
     */
    public void setDischargeOnThreshold(double dischargeOnThreshold) {
        this.dischargeOnThreshold = dischargeOnThreshold;
    }
    /**
     * @return the dischargeOffThreshold
     */
    public double getDischargeOffThreshold() {
        return dischargeOffThreshold;
    }
    /**
     * @param dischargeOffThreshold
     * FOUR QUADRANT MODEL: power level at which the inverter should
     * cease discharging the battery in LOAD_FOLLOWING
     */
    public void setDischargeOffThreshold(double dischargeOffThreshold) {
        this.dischargeOffThreshold = dischargeOffThreshold;
    }
    /**
     * @return the excessInputPower
     */
    public double getExcessInputPower() {
        return excessInputPower;
    }
    /**
     * @param excessInputPower
     * FOUR QUADRANT MODEL: Excess power at the input of the inverter
     * that is otherwise just lost, or could be shunted to a battery
     */
    public void setExcessInputPower(double excessInputPower) {
        this.excessInputPower = excessInputPower;
    }
    /**
     * @return the chargeLockoutTime
     */
    public double getChargeLockoutTime() {
        return chargeLockoutTime;
    }
    /**
     * @param chargeLockoutTime
     *  FOUR QUADRANT MODEL: Lockout time when a charging operation
     *  occurs before another LOAD_FOLLOWING dispatch operation can occur
     */
    public void setChargeLockoutTime(double chargeLockoutTime) {
        this.chargeLockoutTime = chargeLockoutTime;
    }
    /**
     * @return the dischargeLockoutTime
     */
    public double getDischargeLockoutTime() {
        return dischargeLockoutTime;
    }
    /**
     * @param dischargeLockoutTime
     * FOUR QUADRANT MODEL: Lockout time when a discharging operation
     * occurs before another LOAD_FOLLOWING dispatch operation can occur
     */
    public void setDischargeLockoutTime(double dischargeLockoutTime) {
        this.dischargeLockoutTime = dischargeLockoutTime;
    }
    /**
     * @return the pfRegActivate
     */
    public double getPfRegActivate() {
        return pfRegActivate;
    }
    /**
     * @param pfRegActivate
     * FOUR QUADRANT MODEL: Lowest acceptable power-factor level
     * below which power-factor regulation will activate
     */
    public void setPfRegActivate(double pfRegActivate) {
        this.pfRegActivate = pfRegActivate;
    }
    /**
     * @return the pfRegDeactivate
     */
    public double getPfRegDeactivate() {
        return pfRegDeactivate;
    }
    /**
     * @param pfRegDeactivate
     * FOUR QUADRANT MODEL: Lowest acceptable power-factor
     * above which no power-factor regulation is needed
     */
    public void setPfRegDeactivate(double pfRegDeactivate) {
        this.pfRegDeactivate = pfRegDeactivate;
    }
    /**
     * @return the pfRegActivateLockoutTime
     */
    public double getPfRegActivateLockoutTime() {
        return pfRegActivateLockoutTime;
    }
    /**
     * @param pfRegActivateLockoutTime
     * FOUR QUADRANT MODEL: Mandatory pause between the deactivation
     * of power-factor regulation and it reactivation
     */
    public void setPfRegActivateLockoutTime(double pfRegActivateLockoutTime) {
        this.pfRegActivateLockoutTime = pfRegActivateLockoutTime;
    }
    /**
     * @return the chargeThreshold
     */
    public double getChargeThreshold() {
        return chargeThreshold;
    }
    /**
     * @param chargeThreshold
     * FOUR QUADRANT MODEL: Level at which all inverters in the group
     * will begin charging attached batteries. Regulated minimum load level
     */
    public void setChargeThreshold(double chargeThreshold) {
        this.chargeThreshold = chargeThreshold;
    }
    /**
     * @return the dischargeThreshold
     */
    public double getDischargeThreshold() {
        return dischargeThreshold;
    }
    /**
     * @param dischargeThreshold
     * FOUR QUADRANT MODEL: Level at which all inverters in the group
     * will begin discharging attached batteries. Regulated maximum load level
     */
    public void setDischargeThreshold(double dischargeThreshold) {
        this.dischargeThreshold = dischargeThreshold;
    }
    /**
     * @return the groupMaxChargeRate
     */
    public double getGroupMaxChargeRate() {
        return groupMaxChargeRate;
    }
    /**
     * @param groupMaxChargeRate
     * FOUR QUADRANT MODEL: Sum of the charge rates of the batteries
     * involved in the group load-following
     */
    public void setGroupMaxChargeRate(double groupMaxChargeRate) {
        this.groupMaxChargeRate = groupMaxChargeRate;
    }
    /**
     * @return the groupMaxDischargeRate
     */
    public double getGroupMaxDischargeRate() {
        return groupMaxDischargeRate;
    }
    /**
     * @param groupMaxDischargeRate
     * FOUR QUADRANT MODEL: Sum of the discharge rates of the batteries
     * involved in the group load-following
     */
    public void setGroupMaxDischargeRate(double groupMaxDischargeRate) {
        this.groupMaxDischargeRate = groupMaxDischargeRate;
    }
    /**
     * @return the groupRatedPower
     */
    public double getGroupRatedPower() {
        return groupRatedPower;
    }
    /**
     * @param groupRatedPower
     * FOUR QUADRANT MODEL: Sum of the inverter power ratings
     * of the inverters involved in the group power-factor regulation
     */
    public void setGroupRatedPower(double groupRatedPower) {
        this.groupRatedPower = groupRatedPower;
    }
    /**
     * @return the vBase
     */
    public double getvBase() {
        return vBase;
    }
    /**
     * @param vBase
     *  FOUR QUADRANT MODEL: The base voltage on the
     *  grid side of the inverter. Used in VOLT_VAR control mode
     */
    public void setvBase(double vBase) {
        this.vBase = vBase;
    }
    /**
     * @return the v1
     */
    public double getV1() {
        return v1;
    }
    /**
     * @param v1
     * FOUR QUADRANT MODEL: voltage point 1 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setV1(double v1) {
        this.v1 = v1;
    }
    /**
     * @return the v2
     */
    public double getV2() {
        return v2;
    }
    /**
     * @param v2
     * FOUR QUADRANT MODEL: voltage point 2 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setV2(double v2) {
        this.v2 = v2;
    }
    /**
     * @return the v3
     */
    public double getV3() {
        return v3;
    }
    /**
     * @param v3
     * FOUR QUADRANT MODEL: voltage point 3 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setV3(double v3) {
        this.v3 = v3;
    }
    /**
     * @return the v4
     */
    public double getV4() {
        return v4;
    }
    /**
     * @param v4
     * FOUR QUADRANT MODEL: voltage point 4 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setV4(double v4) {
        this.v4 = v4;
    }
    /**
     * @return the q1
     */
    public double getQ1() {
        return q1;
    }
    /**
     * @param q1
     * FOUR QUADRANT MODEL: VAR point 1 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setQ1(double q1) {
        this.q1 = q1;
    }
    /**
     * @return the q2
     */
    public double getQ2() {
        return q2;
    }
    /**
     * @param q2
     * FOUR QUADRANT MODEL: VAR point 2 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setQ2(double q2) {
        this.q2 = q2;
    }
    /**
     * @return the q3
     */
    public double getQ3() {
        return q3;
    }
    /**
     * @param q3
     * FOUR QUADRANT MODEL: VAR point 3 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setQ3(double q3) {
        this.q3 = q3;
    }
    /**
     * @return the q4
     */
    public double getQ4() {
        return q4;
    }
    /**
     * @param q4
     * FOUR QUADRANT MODEL: VAR point 4 in volt/var curve.
     * Used in VOLT_VAR control mode
     */
    public void setQ4(double q4) {
        this.q4 = q4;
    }
    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "inverter";
    }
    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        writeProperty(sb, "generator_mode", this.generatorMode);
        writeProperty(sb, "use_multipoint_efficiency", this.useMultipointEfficiency);

        if (this.inverterType != null) {
            writeProperty(sb, "inverter_type", this.inverterType);
        }

        if (this.fourQuadrantControlMode != null) {
            writeProperty(sb, "four_quadrant_control_mode", this.fourQuadrantControlMode);
        }

        if (this.generatorStatus != null) {
            writeProperty(sb, "generator_status", this.generatorStatus);
        }

        if (this.vIn != null) {
            writeProperty(sb, "V_In", this.vIn);
        }

        if (this.iIn != null) {
            writeProperty(sb, "I_In", this.iIn);
        }

        if (this.generatorMode == GeneratorMode.CONSTANT_PF ||
                this.fourQuadrantControlMode == FourQuadrantControlMode.CONSTANT_PF) {
            writeProperty(sb, "power_factor", this.powerFactor);
        }

        if (this.fourQuadrantControlMode != null) {
            writeProperty(sb, "excess_input_power", this.excessInputPower);
            writeProperty(sb, "pf_reg_activate", this.pfRegActivate);
            writeProperty(sb, "pf_reg_deactivate", this.pfRegDeactivate);
            writeProperty(sb, "pf_reg_activate_lockout_time", this.pfRegActivateLockoutTime);
            writeProperty(sb, "charge_threshold", this.chargeThreshold);
            writeProperty(sb, "discharge_threshold", this.dischargeThreshold);

            if (this.fourQuadrantControlMode == FourQuadrantControlMode.CONSTANT_PQ) {
                writeProperty(sb, "P_Out", this.pOut);
                writeProperty(sb, "Q_Out", this.qOut);
            } else if (this.fourQuadrantControlMode == FourQuadrantControlMode.LOAD_FOLLOWING) {
                writeProperty(sb, "sense_object", this.senseObject);
                writeProperty(sb, "max_charge_rate", this.maxChargeRate);
                writeProperty(sb, "max_discharge_rate", this.maxDischargeRate);
                writeProperty(sb, "charge_on_threshold", this.chargeOnThreshold);
                writeProperty(sb, "charge_off_threshold", this.chargeOffThreshold);
                writeProperty(sb, "discharge_on_threshold", this.dischargeOnThreshold);
                writeProperty(sb, "discharge_off_threshold", this.dischargeOffThreshold);
                writeProperty(sb, "charge_lockout_time", this.chargeLockoutTime);
                writeProperty(sb, "discharge_lockout_time", this.dischargeLockoutTime);
            } else if (this.fourQuadrantControlMode == FourQuadrantControlMode.GROUP_LOAD_FOLLOWING) {
                writeProperty(sb, "group_max_charge_rate", this.groupMaxChargeRate);
                writeProperty(sb, "group_max_discharge_rate", this.groupMaxDischargeRate);
                writeProperty(sb, "group_rated_power", this.groupRatedPower);
            } else if (this.fourQuadrantControlMode == FourQuadrantControlMode.VOLT_VAR) {
                writeProperty(sb, "V_base", this.vBase);
                writeProperty(sb, "V1", this.v1);
                writeProperty(sb, "V2", this.v2);
                writeProperty(sb, "V3", this.v3);
                writeProperty(sb, "V4", this.v4);
                writeProperty(sb, "Q1", this.q1);
                writeProperty(sb, "Q2", this.q2);
                writeProperty(sb, "Q3", this.q3);
                writeProperty(sb, "Q4", this.q4);
            }
        }

        if (this.inverterManufacturer != null) {
            writeProperty(sb, "inverter_manufacturer", this.inverterManufacturer);
        }

        if (this.useMultipointEfficiency) {
            writeProperty(sb, "maximum_dc_power", this.maximumDcPower);
            writeProperty(sb, "maximum_dc_voltage", this.maximumDcPower);
            writeProperty(sb, "minimum_dc_power", this.minimumDcPower);
            writeProperty(sb, "c_0", this.c0);
            writeProperty(sb, "c_1", this.c1);
            writeProperty(sb, "c_2", this.c2);
            writeProperty(sb, "c_3", this.c3);
        }
    }
}
