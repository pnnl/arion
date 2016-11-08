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

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Standard Line configuration for Overhead and other lines
 *
 * @author nord229
 */
public class StandardLineConfiguration<C extends Conductor> extends LineConfiguration<C> {

    /**
     * Conductor for Phase A
     */
    private C phaseAConductor;

    /**
     * Conductor for Phase B
     */
    private C phaseBConductor;

    /**
     * Conductor for Phase C
     */
    private C phaseCConductor;

    /**
     * Conductor for Neutral Phase
     */
    private C phaseNConductor;

    /**
     * Line Spacing for this Line
     */
    private LineSpacing spacing;

    public StandardLineConfiguration(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the Conductor for Phase A
     *
     * @return the phaseAConductor
     */
    public C getPhaseAConductor() {
        return phaseAConductor;
    }

    /**
     * Set the Conductor for Phase A
     *
     * @param phaseAConductor
     *            the phaseAConductor to set
     */
    public void setPhaseAConductor(final C phaseAConductor) {
        this.phaseAConductor = phaseAConductor;
    }

    /**
     * Get the Conductor for Phase B
     *
     * @return the phaseBConductor
     */
    public C getPhaseBConductor() {
        return phaseBConductor;
    }

    /**
     * Set the Conductor for Phase B
     *
     * @param phaseBConductor
     *            the phaseBConductor to set
     */
    public void setPhaseBConductor(final C phaseBConductor) {
        this.phaseBConductor = phaseBConductor;
    }

    /**
     * Get the Conductor for Phase C
     *
     * @return the phaseCConductor
     */
    public C getPhaseCConductor() {
        return phaseCConductor;
    }

    /**
     * Set the Conductor for Phase C
     *
     * @param phaseCConductor
     *            the phaseCConductor to set
     */
    public void setPhaseCConductor(final C phaseCConductor) {
        this.phaseCConductor = phaseCConductor;
    }

    /**
     * Get the Conductor for Neutral Phase
     *
     * @return the phaseNConductor
     */
    public C getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * Set the Conductor for Neutral Phase
     *
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final C phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * Get the Line Spacing for this Line
     *
     * @return the spacing
     */
    public LineSpacing getSpacing() {
        return spacing;
    }

    /**
     * Set the Line Spacing for this Line
     *
     * @param spacing
     *            the spacing to set
     */
    public void setSpacing(final LineSpacing spacing) {
        this.spacing = spacing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "line_configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "conductor_A", this.phaseAConductor);
        writeProperty(sb, "conductor_B", this.phaseBConductor);
        writeProperty(sb, "conductor_C", this.phaseCConductor);
        writeProperty(sb, "conductor_N", this.phaseNConductor);
        writeProperty(sb, "spacing", this.spacing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phaseAConductor, phaseBConductor, phaseCConductor, phaseNConductor, spacing);
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
        final StandardLineConfiguration<?> other = (StandardLineConfiguration<?>) obj;
        return Objects.equals(this.phaseAConductor, other.phaseAConductor)
                && Objects.equals(this.phaseBConductor, other.phaseBConductor)
                && Objects.equals(this.phaseCConductor, other.phaseCConductor)
                && Objects.equals(this.phaseNConductor, other.phaseNConductor)
                && Objects.equals(this.spacing, other.spacing);
    }

}
