/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.GldUtils;

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

    private BusType busType;

    /**
     * @return the voltageA
     */
    public Complex getVoltageA() {
        return voltageA;
    }

    /**
     * @param voltageA
     *            the voltageA to set
     */
    public void setVoltageA(final Complex voltageA) {
        this.voltageA = voltageA;
    }

    /**
     * @param real
     *            the real part of the complex number for VoltageA
     * @param imaginary
     *            the imaginary part of the complex number for VoltageA
     */
    public void setVoltageA(final double real, final double imaginary) {
        this.voltageA = new Complex(real, imaginary);
    }

    /**
     * @return the voltageB
     */
    public Complex getVoltageB() {
        return voltageB;
    }

    /**
     * @param voltageB
     *            the voltageB to set
     */
    public void setVoltageB(final Complex voltageB) {
        this.voltageB = voltageB;
    }

    /**
     * @param real
     *            the real part of the complex number for VoltageB
     * @param imaginary
     *            the imaginary part of the complex number for VoltageB
     */
    public void setVoltageB(final double real, final double imaginary) {
        this.voltageB = new Complex(real, imaginary);
    }

    /**
     * @return the voltageC
     */
    public Complex getVoltageC() {
        return voltageC;
    }

    /**
     * @param voltageC
     *            the voltageC to set
     */
    public void setVoltageC(final Complex voltageC) {
        this.voltageC = voltageC;
    }

    /**
     * @param real
     *            the real part of the complex number for VoltageC
     * @param imaginary
     *            the imaginary part of the complex number for VoltageC
     */
    public void setVoltageC(final double real, final double imaginary) {
        this.voltageC = new Complex(real, imaginary);
    }

    /**
     * @return the busType
     */
    public BusType getBusType() {
        return busType;
    }

    /**
     * @param busType
     *            the busType to set
     */
    public void setBusType(final BusType busType) {
        this.busType = busType;
    }

    @Override
    public String getGLDObjectType() {
        return "node";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GldUtils.writeProperty(sb, "voltage_A", this.voltageA);
        GldUtils.writeProperty(sb, "voltage_B", this.voltageB);
        GldUtils.writeProperty(sb, "voltage_C", this.voltageC);
        GldUtils.writeProperty(sb, "bustype", this.busType);
    }

}
