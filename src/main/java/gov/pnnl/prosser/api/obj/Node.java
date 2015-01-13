/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GldUtils;

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
    private final Complex voltageA;

    /**
     * bus voltage, Phase B to ground
     */
    private final Complex voltageB;

    /**
     * bus voltage, Phase C to ground
     */
    private final Complex voltageC;

    private final BusType busType;

    public Node() {
        this.voltageA = null;
        this.voltageB = null;
        this.voltageC = null;
        this.busType = null;
    }

    public Node(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
        this.voltageA = null;
        this.voltageB = null;
        this.voltageC = null;
        this.busType = null;
    }

    public Node(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage,
            final Complex voltageA, final Complex voltageB, final Complex voltageC) {
        super(name, phases, nominalVoltage);
        this.voltageA = voltageA;
        this.voltageB = voltageB;
        this.voltageC = voltageC;
        this.busType = null;
    }

    public <T extends Node, Z extends AbstractBuilder<T, Z>> Node(final AbstractBuilder<T, Z> builder) {
        super(builder);
        this.voltageA = builder.voltageA;
        this.voltageB = builder.voltageB;
        this.voltageC = builder.voltageC;
        this.busType = builder.busType;
    }

    /**
     * @return the voltageA
     */
    public Complex getVoltageA() {
        return voltageA;
    }

    /**
     * @return the voltageB
     */
    public Complex getVoltageB() {
        return voltageB;
    }

    /**
     * @return the voltageC
     */
    public Complex getVoltageC() {
        return voltageC;
    }

    /**
     * @return the busType
     */
    public BusType getBusType() {
        return busType;
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

    public static abstract class AbstractBuilder<T extends Node, Z extends AbstractBuilder<T, Z>> extends PowerflowObject.AbstractBuilder<T, Z> {
        private Complex voltageA;

        private Complex voltageB;

        private Complex voltageC;

        private BusType busType;

        public Z voltageA(final Complex voltageA) {
            this.voltageA = voltageA;
            return self();
        }

        public Z voltageA(final double real, final double imaginary) {
            this.voltageA = new Complex(real, imaginary);
            return self();
        }

        public Z voltageB(final Complex voltageB) {
            this.voltageB = voltageB;
            return self();
        }

        public Z voltageB(final double real, final double imaginary) {
            this.voltageB = new Complex(real, imaginary);
            return self();
        }

        public Z voltageC(final Complex voltageC) {
            this.voltageC = voltageC;
            return self();
        }

        public Z voltageC(final double real, final double imaginary) {
            this.voltageC = new Complex(real, imaginary);
            return self();
        }

        public Z busType(final BusType busType) {
            this.busType = busType;
            return self();
        }

    }

    public static class Builder extends AbstractBuilder<Node, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Node build() {
            return new Node(this);
        }

    }

}
