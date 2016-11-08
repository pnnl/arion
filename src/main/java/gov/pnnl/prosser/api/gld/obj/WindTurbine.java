/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
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
*/
package gov.pnnl.prosser.api.gld.obj;

import java.lang.reflect.Field;
import java.util.EnumSet;

import org.apache.commons.math3.complex.Complex;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.CoefficientOfPerformanceData;
import gov.pnnl.prosser.api.gld.enums.GeneratorStatus;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.TurbineModel;
import gov.pnnl.prosser.api.gld.enums.WindTurbineGeneratorMode;
import gov.pnnl.prosser.api.gld.enums.WindTurbineGeneratorType;

/**
 * @author sund130
 *
 */
public class WindTurbine extends AbstractGldObject {
    private double avgWindSpeed;
    private double bladeDiameter;
    private double coefficientOfPerformance;
    private CoefficientOfPerformanceData coefficientOfPerformanceData;
    private double coefficientOfPerformanceMax;
    private double coefficientOfPerformanceRated;
    private Complex currentA;
    private Complex currentB;
    private Complex currentC;
    private double cutInWindSpeed;
    private double cutOutWindSpeed;
    private Complex efA;
    private Complex efB;
    private Complex efC;
    private WindTurbineGeneratorMode generatorMode;
    private GeneratorStatus generatorStatus;
    private WindTurbineGeneratorType generatorType;
    private double generatorElectricEfficiency;
    private Complex rotorCurrentGeneratedA;
    private Complex rotorCurrentGeneratedB;
    private Complex rotorCurrentGeneratedC;
    private double  maxSynchronousInducedVoltage;
    private double  maxSynchronousRealPowerCapacity;
    private double  maxSynchronousReactivePowerCapactiy;
    private double  maxInductionInducedVoltage;
    private double  minSynchronousInducedVoltage;
    private double  minSynchronousRealPowerCapacity;
    private double  minSynchronousReactivePowerCapcity;
    private double  minInductionInducedVoltage;
    private int n;
    private double pairDensity;
    private double powerConverted;
    private double desiredPowerFactor;
    private EnumSet<PhaseCode> phases;
    private Complex powerA;
    private Complex powerB;
    private Complex powerC;
    private double pPress;
    private double pTemp;
    private double pWS;
    private double numberOfGearBoxStages;
    private double ratedV;
    private double ratedVA;
    private double coreMagnetizationResistance;
    private double refHeight;
    private double groundingResistance;
    private double roughnessL;
    private double rotorInternalResistance;
    private double internalTransientResistance;
    private double statorInternalResistance;
    private double  storeLastCurrent;
    private long timeAdvance;
    private double totalReactivePower;
    private double totalRealPower;
    private double turbineHeight;
    private TurbineModel turbineModel;
    private Complex voltageA;
    private Complex voltageB;
    private Complex voltageC;
    private Complex vRotorA;
    private Complex vRotorB;
    private Complex vRotorC;
    private double windSpeed;
    private double windSpeedForMaxCoefficientOfPerformance;
    private double ratedWindSpeed;
    private double adjustedWindSpeed;
    private double groundingImpedance;
    private double coreMagnetizationImpedance;
    private double rotoInternalImpedance;
    private double internalTransientImpedance;
    private double statorInternalImpedance;
    private Meter meter;

    /**
     * @param simulator
     */
    public WindTurbine(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @return the avgWindSpeed
     */
    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    /**
     * @param avgWindSpeed the avgWindSpeed to set
     */
    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    /**
     * @return the bladeDiameter
     */
    public double getBladeDiameter() {
        return bladeDiameter;
    }

    /**
     * @param bladeDiameter the bladeDiameter to set
     */
    public void setBladeDiameter(double bladeDiameter) {
        this.bladeDiameter = bladeDiameter;
    }

    /**
     * @return the coefficientOfPerformance
     */
    public double getCoefficientOfPerformance() {
        return coefficientOfPerformance;
    }

    /**
     * @param coefficientOfPerformance the coefficientOfPerformance to set
     */
    public void setCoefficientOfPerformance(double coefficientOfPerformance) {
        this.coefficientOfPerformance = coefficientOfPerformance;
    }

    /**
     * @return the coefficientOfPerformanceData
     */
    public CoefficientOfPerformanceData getCoefficientOfPerformanceData() {
        return coefficientOfPerformanceData;
    }

    /**
     * @param coefficientOfPerformanceData the coefficientOfPerformanceData to set
     */
    public void setCoefficientOfPerformanceData(CoefficientOfPerformanceData coefficientOfPerformanceData) {
        this.coefficientOfPerformanceData = coefficientOfPerformanceData;
    }

    /**
     * @return the coefficientOfPerformanceMax
     */
    public double getCoefficientOfPerformanceMax() {
        return coefficientOfPerformanceMax;
    }

    /**
     * @param coefficientOfPerformanceMax the coefficientOfPerformanceMax to set
     */
    public void setCoefficientOfPerformanceMax(double coefficientOfPerformanceMax) {
        this.coefficientOfPerformanceMax = coefficientOfPerformanceMax;
    }

    /**
     * @return the coefficientOfPerformanceRated
     */
    public double getCoefficientOfPerformanceRated() {
        return coefficientOfPerformanceRated;
    }

    /**
     * @param coefficientOfPerformanceRated the coefficientOfPerformanceRated to set
     */
    public void setCoefficientOfPerformanceRated(double coefficientOfPerformanceRated) {
        this.coefficientOfPerformanceRated = coefficientOfPerformanceRated;
    }

    /**
     * @return the currentA
     */
    public Complex getCurrentA() {
        return currentA;
    }

    /**
     * @param currentA the currentA to set
     */
    public void setCurrentA(Complex currentA) {
        this.currentA = currentA;
    }

    /**
     * @return the currentB
     */
    public Complex getCurrentB() {
        return currentB;
    }

    /**
     * @param currentB the currentB to set
     */
    public void setCurrentB(Complex currentB) {
        this.currentB = currentB;
    }

    /**
     * @return the currentC
     */
    public Complex getCurrentC() {
        return currentC;
    }

    /**
     * @param currentC the currentC to set
     */
    public void setCurrentC(Complex currentC) {
        this.currentC = currentC;
    }

    /**
     * @return the cutInWindSpeed
     */
    public double getCutInWindSpeed() {
        return cutInWindSpeed;
    }

    /**
     * @param cutInWindSpeed the cutInWindSpeed to set
     */
    public void setCutInWindSpeed(double cutInWindSpeed) {
        this.cutInWindSpeed = cutInWindSpeed;
    }

    /**
     * @return the cutOutWindSpeed
     */
    public double getCutOutWindSpeed() {
        return cutOutWindSpeed;
    }

    /**
     * @param cutOutWindSpeed the cutOutWindSpeed to set
     */
    public void setCutOutWindSpeed(double cutOutWindSpeed) {
        this.cutOutWindSpeed = cutOutWindSpeed;
    }

    /**
     * @return the efA
     */
    public Complex getEfA() {
        return efA;
    }

    /**
     * @param efA the efA to set
     */
    public void setEfA(Complex efA) {
        this.efA = efA;
    }

    /**
     * @return the efB
     */
    public Complex getEfB() {
        return efB;
    }

    /**
     * @param efB the efB to set
     */
    public void setEfB(Complex efB) {
        this.efB = efB;
    }

    /**
     * @return the efC
     */
    public Complex getEfC() {
        return efC;
    }

    /**
     * @param efC the efC to set
     */
    public void setEfC(Complex efC) {
        this.efC = efC;
    }

    /**
     * @return the generatorMode
     */
    public WindTurbineGeneratorMode getGeneratorMode() {
        return generatorMode;
    }

    /**
     * @param generatorMode the generatorMode to set
     */
    public void setGeneratorMode(WindTurbineGeneratorMode generatorMode) {
        this.generatorMode = generatorMode;
    }

    /**
     * @return the generatorStatus
     */
    public GeneratorStatus getGeneratorStatus() {
        return generatorStatus;
    }

    /**
     * @param generatorStatus the generatorStatus to set
     */
    public void setGeneratorStatus(GeneratorStatus generatorStatus) {
        this.generatorStatus = generatorStatus;
    }

    /**
     * @return the generatorType
     */
    public WindTurbineGeneratorType getGeneratorType() {
        return generatorType;
    }

    /**
     * @param generatorType the generatorType to set
     */
    public void setGeneratorType(WindTurbineGeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    /**
     * @return the generatorElectricEfficiency
     */
    public double getGeneratorElectricEfficiency() {
        return generatorElectricEfficiency;
    }

    /**
     * @param generatorElectricEfficiency the generatorElectricEfficiency to set
     */
    public void setGeneratorElectricEfficiency(double generatorElectricEfficiency) {
        this.generatorElectricEfficiency = generatorElectricEfficiency;
    }

    /**
     * @return the rotorCurrentGeneratedA
     */
    public Complex getRotorCurrentGeneratedA() {
        return rotorCurrentGeneratedA;
    }

    /**
     * @param rotorCurrentGeneratedA the rotorCurrentGeneratedA to set
     */
    public void setRotorCurrentGeneratedA(Complex rotorCurrentGeneratedA) {
        this.rotorCurrentGeneratedA = rotorCurrentGeneratedA;
    }

    /**
     * @return the rotorCurrentGeneratedB
     */
    public Complex getRotorCurrentGeneratedB() {
        return rotorCurrentGeneratedB;
    }

    /**
     * @param rotorCurrentGeneratedB the rotorCurrentGeneratedB to set
     */
    public void setRotorCurrentGeneratedB(Complex rotorCurrentGeneratedB) {
        this.rotorCurrentGeneratedB = rotorCurrentGeneratedB;
    }

    /**
     * @return the rotorCurrentGeneratedC
     */
    public Complex getRotorCurrentGeneratedC() {
        return rotorCurrentGeneratedC;
    }

    /**
     * @param rotorCurrentGeneratedC the rotorCurrentGeneratedC to set
     */
    public void setRotorCurrentGeneratedC(Complex rotorCurrentGeneratedC) {
        this.rotorCurrentGeneratedC = rotorCurrentGeneratedC;
    }

    /**
     * @return the maxSynchronousInducedVoltage
     */
    public double getMaxSynchronousInducedVoltage() {
        return maxSynchronousInducedVoltage;
    }

    /**
     * @param maxSynchronousInducedVoltage the maxSynchronousInducedVoltage to set
     */
    public void setMaxSynchronousInducedVoltage(double maxSynchronousInducedVoltage) {
        this.maxSynchronousInducedVoltage = maxSynchronousInducedVoltage;
    }

    /**
     * @return the maxSynchronousRealPowerCapacity
     */
    public double getMaxSynchronousRealPowerCapacity() {
        return maxSynchronousRealPowerCapacity;
    }

    /**
     * @param maxSynchronousRealPowerCapacity the maxSynchronousRealPowerCapacity to set
     */
    public void setMaxSynchronousRealPowerCapacity(double maxSynchronousRealPowerCapacity) {
        this.maxSynchronousRealPowerCapacity = maxSynchronousRealPowerCapacity;
    }

    /**
     * @return the maxSynchronousReactivePowerCapactiy
     */
    public double getMaxSynchronousReactivePowerCapactiy() {
        return maxSynchronousReactivePowerCapactiy;
    }

    /**
     * @param maxSynchronousReactivePowerCapactiy the maxSynchronousReactivePowerCapactiy to set
     */
    public void setMaxSynchronousReactivePowerCapactiy(double maxSynchronousReactivePowerCapactiy) {
        this.maxSynchronousReactivePowerCapactiy = maxSynchronousReactivePowerCapactiy;
    }

    /**
     * @return the maxInductionInducedVoltage
     */
    public double getMaxInductionInducedVoltage() {
        return maxInductionInducedVoltage;
    }

    /**
     * @param maxInductionInducedVoltage the maxInductionInducedVoltage to set
     */
    public void setMaxInductionInducedVoltage(double maxInductionInducedVoltage) {
        this.maxInductionInducedVoltage = maxInductionInducedVoltage;
    }

    /**
     * @return the minSynchronousInducedVoltage
     */
    public double getMinSynchronousInducedVoltage() {
        return minSynchronousInducedVoltage;
    }

    /**
     * @param minSynchronousInducedVoltage the minSynchronousInducedVoltage to set
     */
    public void setMinSynchronousInducedVoltage(double minSynchronousInducedVoltage) {
        this.minSynchronousInducedVoltage = minSynchronousInducedVoltage;
    }

    /**
     * @return the minSynchronousRealPowerCapacity
     */
    public double getMinSynchronousRealPowerCapacity() {
        return minSynchronousRealPowerCapacity;
    }

    /**
     * @param minSynchronousRealPowerCapacity the minSynchronousRealPowerCapacity to set
     */
    public void setMinSynchronousRealPowerCapacity(double minSynchronousRealPowerCapacity) {
        this.minSynchronousRealPowerCapacity = minSynchronousRealPowerCapacity;
    }

    /**
     * @return the minSynchronousReactivePowerCapcity
     */
    public double getMinSynchronousReactivePowerCapcity() {
        return minSynchronousReactivePowerCapcity;
    }

    /**
     * @param minSynchronousReactivePowerCapcity the minSynchronousReactivePowerCapcity to set
     */
    public void setMinSynchronousReactivePowerCapcity(double minSynchronousReactivePowerCapcity) {
        this.minSynchronousReactivePowerCapcity = minSynchronousReactivePowerCapcity;
    }

    /**
     * @return the minInductionInducedVoltage
     */
    public double getMinInductionInducedVoltage() {
        return minInductionInducedVoltage;
    }

    /**
     * @param minInductionInducedVoltage the minInductionInducedVoltage to set
     */
    public void setMinInductionInducedVoltage(double minInductionInducedVoltage) {
        this.minInductionInducedVoltage = minInductionInducedVoltage;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the pairDensity
     */
    public double getPairDensity() {
        return pairDensity;
    }

    /**
     * @param pairDensity the pairDensity to set
     */
    public void setPairDensity(double pairDensity) {
        this.pairDensity = pairDensity;
    }

    /**
     * @return the powerConverted
     */
    public double getPowerConverted() {
        return powerConverted;
    }

    /**
     * @param powerConverted the powerConverted to set
     */
    public void setPowerConverted(double powerConverted) {
        this.powerConverted = powerConverted;
    }

    /**
     * @return the desiredPowerFactor
     */
    public double getDesiredPowerFactor() {
        return desiredPowerFactor;
    }

    /**
     * @param desiredPowerFactor the desiredPowerFactor to set
     */
    public void setDesiredPowerFactor(double desiredPowerFactor) {
        this.desiredPowerFactor = desiredPowerFactor;
    }

    /**
     * @return the phases
     */
    public EnumSet<PhaseCode> getPhases() {
        return phases;
    }

    /**
     * @param phases the phases to set
     */
    public void setPhases(EnumSet<PhaseCode> phases) {
        this.phases = phases;
    }

    /**
     * @return the powerA
     */
    public Complex getPowerA() {
        return powerA;
    }

    /**
     * @param powerA the powerA to set
     */
    public void setPowerA(Complex powerA) {
        this.powerA = powerA;
    }

    /**
     * @return the powerB
     */
    public Complex getPowerB() {
        return powerB;
    }

    /**
     * @param powerB the powerB to set
     */
    public void setPowerB(Complex powerB) {
        this.powerB = powerB;
    }

    /**
     * @return the powerC
     */
    public Complex getPowerC() {
        return powerC;
    }

    /**
     * @param powerC the powerC to set
     */
    public void setPowerC(Complex powerC) {
        this.powerC = powerC;
    }

    /**
     * @return the pPress
     */
    public double getpPress() {
        return pPress;
    }

    /**
     * @param pPress the pPress to set
     */
    public void setpPress(double pPress) {
        this.pPress = pPress;
    }

    /**
     * @return the pTemp
     */
    public double getpTemp() {
        return pTemp;
    }

    /**
     * @param pTemp the pTemp to set
     */
    public void setpTemp(double pTemp) {
        this.pTemp = pTemp;
    }

    /**
     * @return the pWS
     */
    public double getpWS() {
        return pWS;
    }

    /**
     * @param pWS the pWS to set
     */
    public void setpWS(double pWS) {
        this.pWS = pWS;
    }

    /**
     * @return the numberOfGearBoxStages
     */
    public double getNumberOfGearBoxStages() {
        return numberOfGearBoxStages;
    }

    /**
     * @param numberOfGearBoxStages the numberOfGearBoxStages to set
     */
    public void setNumberOfGearBoxStages(double numberOfGearBoxStages) {
        this.numberOfGearBoxStages = numberOfGearBoxStages;
    }

    /**
     * @return the ratedV
     */
    public double getRatedV() {
        return ratedV;
    }

    /**
     * @param ratedV the ratedV to set
     */
    public void setRatedV(double ratedV) {
        this.ratedV = ratedV;
    }

    /**
     * @return the ratedVA
     */
    public double getRatedVA() {
        return ratedVA;
    }

    /**
     * @param ratedVA the ratedVA to set
     */
    public void setRatedVA(double ratedVA) {
        this.ratedVA = ratedVA;
    }

    /**
     * @return the coreMagnetizationResistance
     */
    public double getCoreMagnetizationResistance() {
        return coreMagnetizationResistance;
    }

    /**
     * @param coreMagnetizationResistance the coreMagnetizationResistance to set
     */
    public void setCoreMagnetizationResistance(double coreMagnetizationResistance) {
        this.coreMagnetizationResistance = coreMagnetizationResistance;
    }

    /**
     * @return the refHeight
     */
    public double getRefHeight() {
        return refHeight;
    }

    /**
     * @param refHeight the refHeight to set
     */
    public void setRefHeight(double refHeight) {
        this.refHeight = refHeight;
    }

    /**
     * @return the groundingResistance
     */
    public double getGroundingResistance() {
        return groundingResistance;
    }

    /**
     * @param groundingResistance the groundingResistance to set
     */
    public void setGroundingResistance(double groundingResistance) {
        this.groundingResistance = groundingResistance;
    }

    /**
     * @return the roughnessL
     */
    public double getRoughnessL() {
        return roughnessL;
    }

    /**
     * @param roughnessL the roughnessL to set
     */
    public void setRoughnessL(double roughnessL) {
        this.roughnessL = roughnessL;
    }

    /**
     * @return the rotorInternalResistance
     */
    public double getRotorInternalResistance() {
        return rotorInternalResistance;
    }

    /**
     * @param rotorInternalResistance the rotorInternalResistance to set
     */
    public void setRotorInternalResistance(double rotorInternalResistance) {
        this.rotorInternalResistance = rotorInternalResistance;
    }

    /**
     * @return the internalTransientResistance
     */
    public double getInternalTransientResistance() {
        return internalTransientResistance;
    }

    /**
     * @param internalTransientResistance the internalTransientResistance to set
     */
    public void setInternalTransientResistance(double internalTransientResistance) {
        this.internalTransientResistance = internalTransientResistance;
    }

    /**
     * @return the statorInternalResistance
     */
    public double getStatorInternalResistance() {
        return statorInternalResistance;
    }

    /**
     * @param statorInternalResistance the statorInternalResistance to set
     */
    public void setStatorInternalResistance(double statorInternalResistance) {
        this.statorInternalResistance = statorInternalResistance;
    }

    /**
     * @return the storeLastCurrent
     */
    public double getStoreLastCurrent() {
        return storeLastCurrent;
    }

    /**
     * @param storeLastCurrent the storeLastCurrent to set
     */
    public void setStoreLastCurrent(double storeLastCurrent) {
        this.storeLastCurrent = storeLastCurrent;
    }

    /**
     * @return the timeAdvance
     */
    public long getTimeAdvance() {
        return timeAdvance;
    }

    /**
     * @param timeAdvance the timeAdvance to set
     */
    public void setTimeAdvance(long timeAdvance) {
        this.timeAdvance = timeAdvance;
    }

    /**
     * @return the totalReactivePower
     */
    public double getTotalReactivePower() {
        return totalReactivePower;
    }

    /**
     * @param totalReactivePower the totalReactivePower to set
     */
    public void setTotalReactivePower(double totalReactivePower) {
        this.totalReactivePower = totalReactivePower;
    }

    /**
     * @return the totalRealPower
     */
    public double getTotalRealPower() {
        return totalRealPower;
    }

    /**
     * @param totalRealPower the totalRealPower to set
     */
    public void setTotalRealPower(double totalRealPower) {
        this.totalRealPower = totalRealPower;
    }

    /**
     * @return the turbineHeight
     */
    public double getTurbineHeight() {
        return turbineHeight;
    }

    /**
     * @param turbineHeight the turbineHeight to set
     */
    public void setTurbineHeight(double turbineHeight) {
        this.turbineHeight = turbineHeight;
    }

    /**
     * @return the turbineModel
     */
    public TurbineModel getTurbineModel() {
        return turbineModel;
    }

    /**
     * @param turbineModel the turbineModel to set
     */
    public void setTurbineModel(TurbineModel turbineModel) {
        this.turbineModel = turbineModel;
    }

    /**
     * @return the voltageA
     */
    public Complex getVoltageA() {
        return voltageA;
    }

    /**
     * @param voltageA the voltageA to set
     */
    public void setVoltageA(Complex voltageA) {
        this.voltageA = voltageA;
    }

    /**
     * @return the voltageB
     */
    public Complex getVoltageB() {
        return voltageB;
    }

    /**
     * @param voltageB the voltageB to set
     */
    public void setVoltageB(Complex voltageB) {
        this.voltageB = voltageB;
    }

    /**
     * @return the voltageC
     */
    public Complex getVoltageC() {
        return voltageC;
    }

    /**
     * @param voltageC the voltageC to set
     */
    public void setVoltageC(Complex voltageC) {
        this.voltageC = voltageC;
    }

    /**
     * @return the vRotorA
     */
    public Complex getvRotorA() {
        return vRotorA;
    }

    /**
     * @param vRotorA the vRotorA to set
     */
    public void setvRotorA(Complex vRotorA) {
        this.vRotorA = vRotorA;
    }

    /**
     * @return the vRotorB
     */
    public Complex getvRotorB() {
        return vRotorB;
    }

    /**
     * @param vRotorB the vRotorB to set
     */
    public void setvRotorB(Complex vRotorB) {
        this.vRotorB = vRotorB;
    }

    /**
     * @return the vRotorC
     */
    public Complex getvRotorC() {
        return vRotorC;
    }

    /**
     * @param vRotorC the vRotorC to set
     */
    public void setvRotorC(Complex vRotorC) {
        this.vRotorC = vRotorC;
    }

    /**
     * @return the windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed the windSpeed to set
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return the windSpeedForMaxCoefficientOfPerformance
     */
    public double getWindSpeedForMaxCoefficientOfPerformance() {
        return windSpeedForMaxCoefficientOfPerformance;
    }

    /**
     * @param windSpeedForMaxCoefficientOfPerformance the windSpeedForMaxCoefficientOfPerformance to set
     */
    public void setWindSpeedForMaxCoefficientOfPerformance(double windSpeedForMaxCoefficientOfPerformance) {
        this.windSpeedForMaxCoefficientOfPerformance = windSpeedForMaxCoefficientOfPerformance;
    }

    /**
     * @return the ratedWindSpeed
     */
    public double getRatedWindSpeed() {
        return ratedWindSpeed;
    }

    /**
     * @param ratedWindSpeed the ratedWindSpeed to set
     */
    public void setRatedWindSpeed(double ratedWindSpeed) {
        this.ratedWindSpeed = ratedWindSpeed;
    }

    /**
     * @return the adjustedWindSpeed
     */
    public double getAdjustedWindSpeed() {
        return adjustedWindSpeed;
    }

    /**
     * @param adjustedWindSpeed the adjustedWindSpeed to set
     */
    public void setAdjustedWindSpeed(double adjustedWindSpeed) {
        this.adjustedWindSpeed = adjustedWindSpeed;
    }

    /**
     * @return the groundingImpedance
     */
    public double getGroundingImpedance() {
        return groundingImpedance;
    }

    /**
     * @param groundingImpedance the groundingImpedance to set
     */
    public void setGroundingImpedance(double groundingImpedance) {
        this.groundingImpedance = groundingImpedance;
    }

    /**
     * @return the coreMagnetizationImpedance
     */
    public double getCoreMagnetizationImpedance() {
        return coreMagnetizationImpedance;
    }

    /**
     * @param coreMagnetizationImpedance the coreMagnetizationImpedance to set
     */
    public void setCoreMagnetizationImpedance(double coreMagnetizationImpedance) {
        this.coreMagnetizationImpedance = coreMagnetizationImpedance;
    }

    /**
     * @return the rotoInternalImpedance
     */
    public double getRotoInternalImpedance() {
        return rotoInternalImpedance;
    }

    /**
     * @param rotoInternalImpedance the rotoInternalImpedance to set
     */
    public void setRotoInternalImpedance(double rotoInternalImpedance) {
        this.rotoInternalImpedance = rotoInternalImpedance;
    }

    /**
     * @return the internalTransientImpedance
     */
    public double getInternalTransientImpedance() {
        return internalTransientImpedance;
    }

    /**
     * @param internalTransientImpedance the internalTransientImpedance to set
     */
    public void setInternalTransientImpedance(double internalTransientImpedance) {
        this.internalTransientImpedance = internalTransientImpedance;
    }

    /**
     * @return the statorInternalImpedance
     */
    public double getStatorInternalImpedance() {
        return statorInternalImpedance;
    }

    /**
     * @param statorInternalImpedance the statorInternalImpedance to set
     */
    public void setStatorInternalImpedance(double statorInternalImpedance) {
        this.statorInternalImpedance = statorInternalImpedance;
    }

    /**
     * @return the meter
     */
    public Meter getMeter() {
        return meter;
    }

    /**
     * @param meter the meter to set
     */
    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "windturb_dg";
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        PhaseCode.writeGldProperties(phases);

        if (this.coefficientOfPerformanceData != null) {
            writeProperty(sb, "CP_Data", this.coefficientOfPerformanceData);
        }


        if (this.generatorMode != null) {
            writeProperty(sb, "Gen_mode", this.generatorMode);
        }

        if (this.generatorStatus != null) {
            writeProperty(sb, "Gen_status", this.generatorStatus);
        }

        if (this.generatorType != null) {
            writeProperty(sb, "Gen_type", this.generatorType);
        }

        if (this.turbineModel != null) {
            writeProperty(sb, "Turbine_Model", this.turbineModel);
        }

        if (this.meter != null) {
            writeProperty(sb, "parent", this.meter.getName());
        }

        Field[] fields = getClass().getDeclaredFields(); // get all the fields from your class.
        for (Field f : fields) {                         // iterate over each field...
            try {
                if (f.getClass().equals(double.class) && (double)f.get(this) != 0.0) {
                    writeProperty(sb, f.getName(), (double)f.get(this));
                }
                else if (f.getClass().equals(Complex.class) && !((Complex)f.get(this)).isNaN()) {
                    writeProperty(sb, f.getName(), ((Complex)f.get(this)));
                }
                else if (f.getClass().equals(int.class) && (int)f.get(this) != 0) {
                    writeProperty(sb, f.getName(), (int)f.get(this));
                }
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

}
