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

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.BusType;

import java.util.Objects;

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

    public Node(final GldSimulator simulator) {
        super(simulator);
    }

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
        writeProperty(sb, "voltage_A", this.voltageA);
        writeProperty(sb, "voltage_B", this.voltageB);
        writeProperty(sb, "voltage_C", this.voltageC);
        writeProperty(sb, "bustype", this.busType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), voltageA, voltageB, voltageC, busType);
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
        final Node other = (Node) obj;
        return Objects.equals(this.voltageA, other.voltageA)
                && Objects.equals(this.voltageB, other.voltageB)
                && Objects.equals(this.voltageC, other.voltageC)
                && Objects.equals(this.busType, other.busType);
    }

}
