/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.enums.BusType;

import org.apache.commons.math3.complex.Complex;

/**
 * Node Object
 *
 * @author nord229
 */
public class Node extends PowerflowObject {

    /**
     * bus voltage, Phase A to ground
     */
    private Complex voltageA;

    /**
     * bus voltage, Phase B to ground
     */
    private Complex voltageB;

    /**
     * bus voltage, Phase C to ground
     */
    private Complex voltageC;

    /**
     * type of bus used in this node
     */
    private BusType busType;

    /**
     * Get the bus voltage, Phase A to ground
     * 
     * @return the voltageA
     */
    public Complex getVoltageA() {
        return voltageA;
    }

    /**
     * Set the bus voltage, Phase A to ground
     * 
     * @param voltageA
     *            the voltageA to set
     */
    public void setVoltageA(final Complex voltageA) {
        this.voltageA = voltageA;
    }

    /**
     * Set the bus voltage, Phase A to ground
     * 
     * @param real
     *            the real part of the complex number for VoltageA
     * @param imaginary
     *            the imaginary part of the complex number for VoltageA
     */
    public void setVoltageA(final double real, final double imaginary) {
        this.voltageA = new Complex(real, imaginary);
    }

    /**
     * Get the bus voltage, Phase B to ground
     * 
     * @return the voltageB
     */
    public Complex getVoltageB() {
        return voltageB;
    }

    /**
     * Set the bus voltage, Phase B to ground
     * 
     * @param voltageB
     *            the voltageB to set
     */
    public void setVoltageB(final Complex voltageB) {
        this.voltageB = voltageB;
    }

    /**
     * Set the bus voltage, Phase B to ground
     * 
     * @param real
     *            the real part of the complex number for VoltageB
     * @param imaginary
     *            the imaginary part of the complex number for VoltageB
     */
    public void setVoltageB(final double real, final double imaginary) {
        this.voltageB = new Complex(real, imaginary);
    }

    /**
     * Get the bus voltage, Phase C to ground
     * 
     * @return the voltageC
     */
    public Complex getVoltageC() {
        return voltageC;
    }

    /**
     * Set the bus voltage, Phase C to ground
     * 
     * @param voltageC
     *            the voltageC to set
     */
    public void setVoltageC(final Complex voltageC) {
        this.voltageC = voltageC;
    }

    /**
     * Set the bus voltage, Phase C to ground
     * 
     * @param real
     *            the real part of the complex number for VoltageC
     * @param imaginary
     *            the imaginary part of the complex number for VoltageC
     */
    public void setVoltageC(final double real, final double imaginary) {
        this.voltageC = new Complex(real, imaginary);
    }

    /**
     * Get the type of bus used in this node
     * 
     * @return the busType
     */
    public BusType getBusType() {
        return busType;
    }

    /**
     * Set the type of bus used in this node
     * 
     * @param busType
     *            the busType to set
     */
    public void setBusType(final BusType busType) {
        this.busType = busType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "node";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "voltage_A", this.voltageA);
        GldUtils.writeProperty(sb, "voltage_B", this.voltageB);
        GldUtils.writeProperty(sb, "voltage_C", this.voltageC);
        GldUtils.writeProperty(sb, "bustype", this.busType);
    }

}
