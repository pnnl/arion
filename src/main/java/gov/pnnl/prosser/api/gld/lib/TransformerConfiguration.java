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
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.InstallationType;

import java.util.Objects;

import org.apache.commons.math3.complex.Complex;

/**
 * Transformer configuration
 *
 * @author nord229
 */
public class TransformerConfiguration extends PowerflowLibrary {

    /**
     * connection type enum: Wye-Wye, single-phase, etc.
     */
    private ConnectionType connectionType;

    /**
     * Defines location of the transformer installation
     */
    private InstallationType installationType;

    /**
     * primary voltage level in L-L value kV
     */
    private double primaryVoltage;

    /**
     * secondary voltage level kV
     */
    private double secondaryVoltage;

    /**
     * kVA rating of transformer, total
     */
    private Double powerRating;

    /**
     * kVA rating of transformer, phase A
     */
    private Double phaseARating;

    /**
     * kVA rating of transformer, phase B
     */
    private Double phaseBRating;

    /**
     * kVA rating of transformer, phase C
     */
    private Double phaseCRating;

    /**
     * Series impedance, pu
     */
    private Complex impedance;

    /**
     * Shunt impedance on primary side, pu
     */
    private Complex shuntImpedance;

    public TransformerConfiguration(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the connection type enum: Wye-Wye, single-phase, etc.
     *
     * @return the connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * Set the connection type enum: Wye-Wye, single-phase, etc.
     *
     * @param connectionType
     *            the connectionType to set
     */
    public void setConnectionType(final ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * Get the location of the transformer installation
     *
     * @return the installationType
     */
    public InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * Set the location of the transformer installation
     *
     * @param installationType
     *            the installationType to set
     */
    public void setInstallationType(final InstallationType installationType) {
        this.installationType = installationType;
    }

    /**
     * Get the primary voltage level in L-L value kV
     *
     * @return the primaryVoltage
     */
    public double getPrimaryVoltage() {
        return primaryVoltage;
    }

    /**
     * Set the primary voltage level in L-L value kV
     *
     * @param primaryVoltage
     *            the primaryVoltage to set
     */
    public void setPrimaryVoltage(final double primaryVoltage) {
        this.primaryVoltage = primaryVoltage;
    }

    /**
     * Get the secondary voltage level kV
     *
     * @return the secondaryVoltage
     */
    public double getSecondaryVoltage() {
        return secondaryVoltage;
    }

    /**
     * Set the secondary voltage level kV
     *
     * @param secondaryVoltage
     *            the secondaryVoltage to set
     */
    public void setSecondaryVoltage(final double secondaryVoltage) {
        this.secondaryVoltage = secondaryVoltage;
    }

    /**
     * Get the kVA rating of transformer, total
     *
     * @return the powerRating
     */
    public Double getPowerRating() {
        return powerRating;
    }

    /**
     * Set the kVA rating of transformer, total
     *
     * @param powerRating
     *            the powerRating to set
     */
    public void setPowerRating(final Double powerRating) {
        this.powerRating = powerRating;
    }

    /**
     * Get the kVA rating of transformer, phase A
     *
     * @return the phaseARating
     */
    public Double getPhaseARating() {
        return phaseARating;
    }

    /**
     * Set the kVA rating of transformer, phase A
     *
     * @param phaseARating
     *            the phaseARating to set
     */
    public void setPhaseARating(final Double phaseARating) {
        this.phaseARating = phaseARating;
    }

    /**
     * Get the kVA rating of transformer, phase B
     *
     * @return the phaseBRating
     */
    public Double getPhaseBRating() {
        return phaseBRating;
    }

    /**
     * Set the kVA rating of transformer, phase B
     *
     * @param phaseBRating
     *            the phaseBRating to set
     */
    public void setPhaseBRating(final Double phaseBRating) {
        this.phaseBRating = phaseBRating;
    }

    /**
     * Get the kVA rating of transformer, phase C
     *
     * @return the phaseCRating
     */
    public Double getPhaseCRating() {
        return phaseCRating;
    }

    /**
     * Set the kVA rating of transformer, phase C
     *
     * @param phaseCRating
     *            the phaseCRating to set
     */
    public void setPhaseCRating(final Double phaseCRating) {
        this.phaseCRating = phaseCRating;
    }

    /**
     * Get the Series impedance, pu
     *
     * @return the impedance
     */
    public Complex getImpedance() {
        return impedance;
    }

    /**
     * Set the Series impedance, pu
     *
     * @param impedance
     *            the impedance to set
     */
    public void setImpedance(final Complex impedance) {
        this.impedance = impedance;
    }

    /**
     * Set the Series impedance, pu
     *
     * @param real
     *            the real part of the complex number for impedance
     * @param imaginary
     *            the imaginary part of the complex number for impedance
     */
    public void setImpedance(final double real, final double imaginary) {
        this.impedance = new Complex(real, imaginary);
    }

    /**
     * Get the Shunt impedance on primary side, pu
     *
     * @return the shuntImpedance
     */
    public Complex getShuntImpedance() {
        return shuntImpedance;
    }

    /**
     * Set the Shunt impedance on primary side, pu
     *
     * @param shuntImpedance
     *            the shuntImpedance to set
     */
    public void setShuntImpedance(final Complex shuntImpedance) {
        this.shuntImpedance = shuntImpedance;
    }

    /**
     * Set the Shunt impedance on primary side, pu
     *
     * @param real
     *            the real part of the complex number for shunt impedance
     * @param imaginary
     *            the imaginary part of the complex number for shunt impedance
     */
    public void setShuntImpedance(final double real, final double imaginary) {
        this.shuntImpedance = new Complex(real, imaginary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "transformer_configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "connect_type", this.connectionType);
        writeProperty(sb, "install_type", this.installationType);
        writeProperty(sb, "power_rating", this.powerRating);
        writeProperty(sb, "powerA_rating", this.phaseARating, "kVA");
        writeProperty(sb, "powerB_rating", this.phaseBRating);
        writeProperty(sb, "powerC_rating", this.phaseCRating);
        writeProperty(sb, "primary_voltage", this.primaryVoltage);
        writeProperty(sb, "secondary_voltage", this.secondaryVoltage);
        // GLDUtils.writeProperty(sb, "resistance", this.impedance.getReal());
        // GLDUtils.writeProperty(sb, "reactance", this.impedance.getImaginary());
        writeProperty(sb, "impedance", this.impedance);
        writeProperty(sb, "shunt_impedance", this.shuntImpedance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionType, installationType, primaryVoltage, secondaryVoltage,
                powerRating, phaseARating, phaseBRating, phaseCRating, impedance, shuntImpedance);
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
        final TransformerConfiguration other = (TransformerConfiguration) obj;
        return Objects.equals(this.connectionType, other.connectionType)
                && Objects.equals(this.installationType, other.installationType)
                && Objects.equals(this.primaryVoltage, other.primaryVoltage)
                && Objects.equals(this.secondaryVoltage, other.secondaryVoltage)
                && Objects.equals(this.powerRating, other.powerRating)
                && Objects.equals(this.phaseARating, other.phaseARating)
                && Objects.equals(this.phaseBRating, other.phaseBRating)
                && Objects.equals(this.phaseCRating, other.phaseCRating)
                && Objects.equals(this.impedance, other.impedance)
                && Objects.equals(this.shuntImpedance, other.shuntImpedance);
    }

}
