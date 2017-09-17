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

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.DistributionType;

/**
 * @author sund130
 *
 */
public class PassiveController extends Controller {
    private String expectationObjectName;
    private String expectationProperty;
    private String observationObjectName;
    private String observationProperty;
    private double observation;
    private double meanObservation;
    private double stdevObservation;
    private double expected;
    private double outputSetpoint;
    private String outputState;
    private AbstractGldObject parent;
    private double period;
    private DistributionType distributionType;
    private double comfortLevel;
    private boolean criticalDay;
    private boolean twoTierCpp;
    private double dailyElasticity;
    private double subElasticityFirstSecond;
    private double subElasticityFirstThird;
    private double firstTierHours;
    private double secondTierHours;
    private double thirdTierHours;
    private double firstTierPrice;
    private double secondTierPrice;
    private double thirdTierPrice;
    private double oldFirstTierPrice;
    private double oldSecondTierPrice;
    private double oldThirdTierPrice;
    private boolean linearizeElasticity;
    private double priceOffset = 10E-6;
    private boolean poolPumpModel;
    private double baseDutyCycle;

    /**
     * @param simulator
     */
    public PassiveController(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @return the expectationObjectName
     */
    public String getExpectationObjectName() {
        return expectationObjectName;
    }

    /**
     * @param expectationObjectName the expectationObjectName to set
     */
    public void setExpectationObjectName(String expectationObjectName) {
        this.expectationObjectName = expectationObjectName;
    }

    /**
     * @return the expectationProperty
     */
    public String getExpectationProperty() {
        return expectationProperty;
    }

    /**
     * @param expectationProperty the expectationProperty to set
     */
    public void setExpectationProperty(String expectationProperty) {
        this.expectationProperty = expectationProperty;
    }

    /**
     * @return the observationObjectName
     */
    public String getObservationObjectName() {
        return observationObjectName;
    }

    /**
     * @param observationObjectName the observationObjectName to set
     */
    public void setObservationObjectName(String observationObjectName) {
        this.observationObjectName = observationObjectName;
    }

    /**
     * @return the observationProperty
     */
    public String getObservationProperty() {
        return observationProperty;
    }

    /**
     * @param observationProperty the observationProperty to set
     */
    public void setObservationProperty(String observationProperty) {
        this.observationProperty = observationProperty;
    }

    /**
     * @return the observation
     */
    public double getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(double observation) {
        this.observation = observation;
    }

    /**
     * @return the meanObservation
     */
    public double getMeanObservation() {
        return meanObservation;
    }

    /**
     * @param meanObservation the meanObservation to set
     */
    public void setMeanObservation(double meanObservation) {
        this.meanObservation = meanObservation;
    }

    /**
     * @return the stdevObservation
     */
    public double getStdevObservation() {
        return stdevObservation;
    }

    /**
     * @param stdevObservation the stdevObservation to set
     */
    public void setStdevObservation(double stdevObservation) {
        this.stdevObservation = stdevObservation;
    }

    /**
     * @return the expected
     */
    public double getExpected() {
        return expected;
    }

    /**
     * @param expected the expected to set
     */
    public void setExpected(double expected) {
        this.expected = expected;
    }

    /**
     * @return the outputSetpoint
     */
    public double getOutputSetpoint() {
        return outputSetpoint;
    }

    /**
     * @param outputSetpoint the outputSetpoint to set
     */
    public void setOutputSetpoint(double outputSetpoint) {
        this.outputSetpoint = outputSetpoint;
    }

    /**
     * @return the outputState
     */
    public String getOutputState() {
        return outputState;
    }

    /**
     * @param outputState the outputState to set
     */
    public void setOutputState(String outputState) {
        this.outputState = outputState;
    }

    /**
     * @return the parent
     */
    public AbstractGldObject getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(AbstractGldObject parent) {
        this.parent = parent;
    }

    /**
     * @return the period
     */
    public double getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(double period) {
        this.period = period;
    }

    /**
     * @return the distributionType
     */
    public DistributionType getDistributionType() {
        return distributionType;
    }

    /**
     * @param distributionType the distributionType to set
     */
    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    /**
     * @return the comfortLevel
     */
    public double getComfortLevel() {
        return comfortLevel;
    }

    /**
     * @param comfortLevel the comfortLevel to set
     */
    public void setComfortLevel(double comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    /**
     * @return the criticalDay
     */
    public boolean isCriticalDay() {
        return criticalDay;
    }

    /**
     * @param criticalDay the criticalDay to set
     */
    public void setCriticalDay(boolean criticalDay) {
        this.criticalDay = criticalDay;
    }

    /**
     * @return the twoTierCpp
     */
    public boolean isTwoTierCpp() {
        return twoTierCpp;
    }

    /**
     * @param twoTierCpp the twoTierCpp to set
     */
    public void setTwoTierCpp(boolean twoTierCpp) {
        this.twoTierCpp = twoTierCpp;
    }

    /**
     * @return the dailyElasticity
     */
    public double getDailyElasticity() {
        return dailyElasticity;
    }

    /**
     * @param dailyElasticity the dailyElasticity to set
     */
    public void setDailyElasticity(double dailyElasticity) {
        this.dailyElasticity = dailyElasticity;
    }

    /**
     * @return the subElasticityFirstSecond
     */
    public double getSubElasticityFirstSecond() {
        return subElasticityFirstSecond;
    }

    /**
     * @param subElasticityFirstSecond the subElasticityFirstSecond to set
     */
    public void setSubElasticityFirstSecond(double subElasticityFirstSecond) {
        this.subElasticityFirstSecond = subElasticityFirstSecond;
    }

    /**
     * @return the subElasticityFirstThird
     */
    public double getSubElasticityFirstThird() {
        return subElasticityFirstThird;
    }

    /**
     * @param subElasticityFirstThird the subElasticityFirstThird to set
     */
    public void setSubElasticityFirstThird(double subElasticityFirstThird) {
        this.subElasticityFirstThird = subElasticityFirstThird;
    }

    /**
     * @return the firstTierHours
     */
    public double getFirstTierHours() {
        return firstTierHours;
    }

    /**
     * @param firstTierHours the firstTierHours to set
     */
    public void setFirstTierHours(double firstTierHours) {
        this.firstTierHours = firstTierHours;
    }

    /**
     * @return the secondTierHours
     */
    public double getSecondTierHours() {
        return secondTierHours;
    }

    /**
     * @param secondTierHours the secondTierHours to set
     */
    public void setSecondTierHours(double secondTierHours) {
        this.secondTierHours = secondTierHours;
    }

    /**
     * @return the thirdTierHours
     */
    public double getThirdTierHours() {
        return thirdTierHours;
    }

    /**
     * @param thirdTierHours the thirdTierHours to set
     */
    public void setThirdTierHours(double thirdTierHours) {
        this.thirdTierHours = thirdTierHours;
    }

    /**
     * @return the firstTierPrice
     */
    public double getFirstTierPrice() {
        return firstTierPrice;
    }

    /**
     * @param firstTierPrice the firstTierPrice to set
     */
    public void setFirstTierPrice(double firstTierPrice) {
        this.firstTierPrice = firstTierPrice;
    }

    /**
     * @return the secondTierPrice
     */
    public double getSecondTierPrice() {
        return secondTierPrice;
    }

    /**
     * @param secondTierPrice the secondTierPrice to set
     */
    public void setSecondTierPrice(double secondTierPrice) {
        this.secondTierPrice = secondTierPrice;
    }

    /**
     * @return the thirdTierPrice
     */
    public double getThirdTierPrice() {
        return thirdTierPrice;
    }

    /**
     * @param thirdTierPrice the thirdTierPrice to set
     */
    public void setThirdTierPrice(double thirdTierPrice) {
        this.thirdTierPrice = thirdTierPrice;
    }

    /**
     * @return the oldFirstTierPrice
     */
    public double getOldFirstTierPrice() {
        return oldFirstTierPrice;
    }

    /**
     * @param oldFirstTierPrice the oldFirstTierPrice to set
     */
    public void setOldFirstTierPrice(double oldFirstTierPrice) {
        this.oldFirstTierPrice = oldFirstTierPrice;
    }

    /**
     * @return the oldSecondTierPrice
     */
    public double getOldSecondTierPrice() {
        return oldSecondTierPrice;
    }

    /**
     * @param oldSecondTierPrice the oldSecondTierPrice to set
     */
    public void setOldSecondTierPrice(double oldSecondTierPrice) {
        this.oldSecondTierPrice = oldSecondTierPrice;
    }

    /**
     * @return the oldThirdTierPrice
     */
    public double getOldThirdTierPrice() {
        return oldThirdTierPrice;
    }

    /**
     * @param oldThirdTierPrice the oldThirdTierPrice to set
     */
    public void setOldThirdTierPrice(double oldThirdTierPrice) {
        this.oldThirdTierPrice = oldThirdTierPrice;
    }

    /**
     * @return the linearizeElasticity
     */
    public boolean isLinearizeElasticity() {
        return linearizeElasticity;
    }

    /**
     * @param linearizeElasticity the linearizeElasticity to set
     */
    public void setLinearizeElasticity(boolean linearizeElasticity) {
        this.linearizeElasticity = linearizeElasticity;
    }

    /**
     * @return the priceOffset
     */
    public double getPriceOffset() {
        return priceOffset;
    }

    /**
     * @param priceOffset the priceOffset to set
     */
    public void setPriceOffset(double priceOffset) {
        this.priceOffset = priceOffset;
    }

    /**
     * @return the poolPumpModel
     */
    public boolean isPoolPumpModel() {
        return poolPumpModel;
    }

    /**
     * @param poolPumpModel the poolPumpModel to set
     */
    public void setPoolPumpModel(boolean poolPumpModel) {
        this.poolPumpModel = poolPumpModel;
    }

    /**
     * @return the baseDutyCycle
     */
    public double getBaseDutyCycle() {
        return baseDutyCycle;
    }

    /**
     * @param baseDutyCycle the baseDutyCycle to set
     */
    public void setBaseDutyCycle(double baseDutyCycle) {
        this.baseDutyCycle = baseDutyCycle;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "passive_controller";
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        super.writeGldProperties(sb);

        if (this.expectationObjectName != null) {
            writeProperty(sb, "expectation_object", this.expectationObjectName);
        }

        if (this.expectationProperty != null) {
            writeProperty(sb, "expectation_property", this.expectationProperty);
        }

        if (this.observationObjectName != null) {
            writeProperty(sb, "observation_object", this.observationObjectName);
        }

        if (this.observationProperty != null) {
            writeProperty(sb, "observation_property", this.observationProperty);
        }

        if (this.observation != 0.0) {
            writeProperty(sb, "observation", this.observation);
        }

        if (this.meanObservation != 0.0) {
            writeProperty(sb, "mean_observation", this.meanObservation);
        }

        if (this.stdevObservation != 0.0) {
            writeProperty(sb, "stdev_observation", this.stdevObservation);
        }

        if (this.expected != 0.0) {
            writeProperty(sb, "expected", this.expected);
        }

        if (this.outputSetpoint != 0.0) {
            writeProperty(sb, "output_setpoint", this.outputSetpoint);
        }

        if (this.outputState != null) {
            writeProperty(sb, "output_state", this.outputState);
        }

        if (this.parent != null) {
            writeProperty(sb, "parent", this.parent);
        }

        if (this.period != 0.0) {
            writeProperty(sb, "period ", this.period);
        }

        if (this.distributionType != null) {
            writeProperty(sb, "distribution_type", this.distributionType);
        }

        if (this.comfortLevel != 0.0) {
            writeProperty(sb, "comfort_level", this.comfortLevel);
        }

        writeProperty(sb, "critical_day", this.criticalDay);
        writeProperty(sb, "two_tier_cpp ", this.twoTierCpp);

        if (this.dailyElasticity != 0.0) {
            writeProperty(sb, "daily_elasticity ", this.dailyElasticity);
        }

        if (this.subElasticityFirstSecond != 0.0) {
            writeProperty(sb, "sub_elasticity_first_second ", this.subElasticityFirstSecond);
        }

        if (this.subElasticityFirstThird != 0.0) {
            writeProperty(sb, "sub_elasticity_first_third ", this.subElasticityFirstThird);
        }

        if (this.firstTierHours != 0.0) {
            writeProperty(sb, "first_tier_hours ", this.firstTierHours);
        }

        if (this.secondTierHours != 0.0) {
            writeProperty(sb, "second_tier_hours ", this.secondTierHours);
        }

        if (this.thirdTierHours != 0.0) {
            writeProperty(sb, "third_tier_hours ", this.thirdTierHours);
        }

        if (this.firstTierPrice != 0.0) {
            writeProperty(sb, "first_tier_price ", this.firstTierPrice);
        }

        if (this.secondTierPrice != 0.0) {
            writeProperty(sb, "second_tier_price", this.secondTierPrice);
        }

        if (this.thirdTierPrice != 0.0) {
            writeProperty(sb, "third_tier_price", this.thirdTierPrice);
        }

        if (this.oldFirstTierPrice != 0.0) {
            writeProperty(sb, "old_first_tier_price", this.oldFirstTierPrice);
        }

        if (this.oldSecondTierPrice != 0.0) {
            writeProperty(sb, "old_second_tier_price", this.oldSecondTierPrice);
        }

        if (this.oldThirdTierPrice != 0.0) {
            writeProperty(sb, "old_third_tier_price", this.oldThirdTierPrice);
        }

        writeProperty(sb, "linearize_elasticity", this.linearizeElasticity);

        if (this.priceOffset != 0.0) {
            writeProperty(sb, "price_offset", this.priceOffset);
        }

        writeProperty(sb, "pool_pump_model", this.poolPumpModel);

        if (this.baseDutyCycle != 0.0) {
            writeProperty(sb, "base_duty_cycle", this.baseDutyCycle);
        }
    }

}
