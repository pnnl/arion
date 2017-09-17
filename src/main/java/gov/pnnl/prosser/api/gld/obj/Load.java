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

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Load objects represent static loads and export both voltages and current
 *
 * @author nord229
 */
public class Load extends Node {

    /**
     * constant power load on phase A, real only, specified as W
     */
    private String phaseAConstantReal;

    /**
     * constant power load on phase B, real only, specified as W
     */
    private String phaseBConstantReal;

    /**
     * constant power load on phase C, real only, specified as W
     */
    private String phaseCConstantReal;

    public Load(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the constant power load on phase A, real only, specified as W
     *
     * @return the phaseAConstantReal
     */
    public String getPhaseAConstantReal() {
        return phaseAConstantReal;
    }

    /**
     * Set the constant power load on phase A, real only, specified as W
     *
     * @param phaseAConstantReal
     *            the phaseAConstantReal to set
     */
    public void setPhaseAConstantReal(final String phaseAConstantReal) {
        this.phaseAConstantReal = phaseAConstantReal;
    }

    /**
     * Get the constant power load on phase B, real only, specified as W
     *
     * @return the phaseBConstantReal
     */
    public String getPhaseBConstantReal() {
        return phaseBConstantReal;
    }

    /**
     * Set the constant power load on phase B, real only, specified as W
     *
     * @param phaseBConstantReal
     *            the phaseBConstantReal to set
     */
    public void setPhaseBConstantReal(final String phaseBConstantReal) {
        this.phaseBConstantReal = phaseBConstantReal;
    }

    /**
     * Get the constant power load on phase C, real only, specified as W
     *
     * @return the phaseCConstantReal
     */
    public String getPhaseCConstantReal() {
        return phaseCConstantReal;
    }

    /**
     * Set the constant power load on phase C, real only, specified as W
     *
     * @param phaseCConstantReal
     *            the phaseCConstantReal to set
     */
    public void setPhaseCConstantReal(final String phaseCConstantReal) {
        this.phaseCConstantReal = phaseCConstantReal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "load";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "constant_power_A_real", this.phaseAConstantReal);
        writeProperty(sb, "constant_power_B_real", this.phaseBConstantReal);
        writeProperty(sb, "constant_power_C_real", this.phaseCConstantReal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phaseAConstantReal, phaseBConstantReal, phaseCConstantReal);
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
        final Load other = (Load) obj;
        return Objects.equals(this.phaseAConstantReal, other.phaseAConstantReal)
                && Objects.equals(this.phaseBConstantReal, other.phaseBConstantReal)
                && Objects.equals(this.phaseCConstantReal, other.phaseCConstantReal);
    }

}
