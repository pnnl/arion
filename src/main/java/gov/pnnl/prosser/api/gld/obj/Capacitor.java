/**
 * 
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