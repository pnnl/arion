/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

import java.util.EnumSet;

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

    public Node() {
    }

    public Node(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
    }

    public Node(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage,
            final Complex voltageA, final Complex voltageB, final Complex voltageC) {
        this(name, phases, nominalVoltage);
        this.voltageA = voltageA;
        this.voltageB = voltageB;
        this.voltageC = voltageC;
    }

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

    @Override
    public String getGLDObjectType() {
        return "node";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.writeProperty(sb, "voltage_A", this.voltageA);
        GLDUtils.writeProperty(sb, "voltage_B", this.voltageB);
        GLDUtils.writeProperty(sb, "voltage_C", this.voltageC);
    }

}
