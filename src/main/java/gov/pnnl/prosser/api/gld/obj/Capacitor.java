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

import java.util.EnumSet;
import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.CapacitorControl;
import gov.pnnl.prosser.api.gld.enums.ControlLevel;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;

/**
 * @author sund130
 *
 */
public class Capacitor extends Node {
    private EnumSet<PhaseCode> ptPhase;
    private EnumSet<PhaseCode> phasesConnected;
    private CapacitorControl control;
    private double capacitorA;
    private double capacitorB;
    private double capacitorC;
    private ControlLevel controlLevel;
    private SwitchStatus switchStatusA;
    private SwitchStatus switchStatusB;
    private SwitchStatus switchStatusC;
    private double voltageSetHigh;
    private double voltageSetLow;
    private double timeDelay;
    private double dwellTime;
    private double capNominalVoltage;

    /**
     * @param simulator
     */
    public Capacitor(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @return the ptPhase
     */
    public EnumSet<PhaseCode> getPtPhase() {
        return ptPhase;
    }

    /**
     * @param ptPhase the ptPhase to set
     */
    public void setPtPhase(EnumSet<PhaseCode> ptPhase) {
        this.ptPhase = ptPhase;
    }

    /**
     * @return the phasesConnected
     */
    public EnumSet<PhaseCode> getPhasesConnected() {
        return phasesConnected;
    }

    /**
     * @param phasesConnected the phasesConnected to set
     */
    public void setPhasesConnected(EnumSet<PhaseCode> phasesConnected) {
        this.phasesConnected = phasesConnected;
    }

    /**
     * @return the control
     */
    public CapacitorControl getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(CapacitorControl control) {
        this.control = control;
    }

    /**
     * @return the capacitorA
     */
    public double getCapacitorA() {
        return capacitorA;
    }

    /**
     * @param capacitorA the capacitorA to set
     */
    public void setCapacitorA(double capacitorA) {
        this.capacitorA = capacitorA;
    }

    /**
     * @return the capacitorB
     */
    public double getCapacitorB() {
        return capacitorB;
    }

    /**
     * @param capacitorB the capacitorB to set
     */
    public void setCapacitorB(double capacitorB) {
        this.capacitorB = capacitorB;
    }

    /**
     * @return the capacitorC
     */
    public double getCapacitorC() {
        return capacitorC;
    }

    /**
     * @param capacitorC the capacitorC to set
     */
    public void setCapacitorC(double capacitorC) {
        this.capacitorC = capacitorC;
    }

    /**
     * @return the controlLevel
     */
    public ControlLevel getControlLevel() {
        return controlLevel;
    }

    /**
     * @param controlLevel the controlLevel to set
     */
    public void setControlLevel(ControlLevel controlLevel) {
        this.controlLevel = controlLevel;
    }

    /**
     * @return the switchStatusA
     */
    public SwitchStatus getSwitchStatusA() {
        return switchStatusA;
    }

    /**
     * @param switchStatusA the switchStatusA to set
     */
    public void setSwitchStatusA(SwitchStatus switchStatusA) {
        this.switchStatusA = switchStatusA;
    }

    /**
     * @return the switchStatusB
     */
    public SwitchStatus getSwitchStatusB() {
        return switchStatusB;
    }

    /**
     * @param switchStatusB the switchStatusB to set
     */
    public void setSwitchStatusB(SwitchStatus switchStatusB) {
        this.switchStatusB = switchStatusB;
    }

    /**
     * @return the switchStatusC
     */
    public SwitchStatus getSwitchStatusC() {
        return switchStatusC;
    }

    /**
     * @param switchStatusC the switchStatusC to set
     */
    public void setSwitchStatusC(SwitchStatus switchStatusC) {
        this.switchStatusC = switchStatusC;
    }

    /**
     * @return the voltageSetHigh
     */
    public double getVoltageSetHigh() {
        return voltageSetHigh;
    }

    /**
     * @param voltageSetHigh the voltageSetHigh to set
     */
    public void setVoltageSetHigh(double voltageSetHigh) {
        this.voltageSetHigh = voltageSetHigh;
    }

    /**
     * @return the voltageSetLow
     */
    public double getVoltageSetLow() {
        return voltageSetLow;
    }

    /**
     * @param voltageSetLow the voltageSetLow to set
     */
    public void setVoltageSetLow(double voltageSetLow) {
        this.voltageSetLow = voltageSetLow;
    }

    /**
     * @return the timeDelay
     */
    public double getTimeDelay() {
        return timeDelay;
    }

    /**
     * @param timeDelay the timeDelay to set
     */
    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    /**
     * @return the dwellTime
     */
    public double getDwellTime() {
        return dwellTime;
    }

    /**
     * @param dwellTime the dwellTime to set
     */
    public void setDwellTime(double dwellTime) {
        this.dwellTime = dwellTime;
    }

    /**
     * @return the capNominalVoltage
     */
    public double getCapNominalVoltage() {
        return capNominalVoltage;
    }

    /**
     * @param capNominalVoltage the capNominalVoltage to set
     */
    public void setCapNominalVoltage(double capNominalVoltage) {
        this.capNominalVoltage = capNominalVoltage;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "capacitor";
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        // TODO Auto-generated method stub
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ptPhase, phasesConnected, control, capacitorA, capacitorB, capacitorC, controlLevel,
                switchStatusA, switchStatusB, switchStatusC, voltageSetHigh, voltageSetLow, timeDelay, dwellTime, capNominalVoltage);
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
        final Capacitor other = (Capacitor) obj;
        return Objects.equals(this.ptPhase, other.ptPhase)
                && Objects.equals(this.phasesConnected, other.phasesConnected)
                && Objects.equals(this.control, other.control)
                && Objects.equals(this.capacitorA, other.capacitorA)
                && Objects.equals(this.capacitorB, other.capacitorB)
                && Objects.equals(this.capacitorC, other.capacitorC)
                && Objects.equals(this.controlLevel, other.controlLevel)
                && Objects.equals(this.switchStatusA, other.switchStatusA)
                && Objects.equals(this.switchStatusB, other.switchStatusB)
                && Objects.equals(this.switchStatusC, other.switchStatusC)
                && Objects.equals(this.voltageSetHigh, other.voltageSetHigh)
                && Objects.equals(this.voltageSetLow, other.voltageSetLow)
                && Objects.equals(this.timeDelay, other.timeDelay)
                && Objects.equals(this.dwellTime, other.dwellTime)
                && Objects.equals(this.capNominalVoltage, other.capNominalVoltage);
    }
}