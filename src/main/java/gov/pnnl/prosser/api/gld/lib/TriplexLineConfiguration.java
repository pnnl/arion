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
 * Triplex Line Configuration
 *
 * @author nord229
 */
public class TriplexLineConfiguration extends LineConfiguration<TriplexLineConductor> {

    /**
     * Conductor for Phase 1
     */
    private TriplexLineConductor phase1Conductor;

    /**
     * Conductor for Phase 2
     */
    private TriplexLineConductor phase2Conductor;

    /**
     * Conductor for Neutral Phase
     */
    private TriplexLineConductor phaseNConductor;

    /**
     * thickness of insulation around cabling in inches
     */
    private double insulationThickness;

    /**
     * total diameter of cable in inches
     */
    private double diameter;

    public TriplexLineConfiguration(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the Conductor for Phase 1
     *
     * @return the phase1Conductor
     */
    public TriplexLineConductor getPhase1Conductor() {
        return phase1Conductor;
    }

    /**
     * Set the Conductor for Phase 1
     *
     * @param phase1Conductor
     *            the phase1Conductor to set
     */
    public void setPhase1Conductor(final TriplexLineConductor phase1Conductor) {
        this.phase1Conductor = phase1Conductor;
    }

    /**
     * Get the Conductor for Phase 2
     *
     * @return the phase2Conductor
     */
    public TriplexLineConductor getPhase2Conductor() {
        return phase2Conductor;
    }

    /**
     * Set the Conductor for Phase 2
     *
     * @param phase2Conductor
     *            the phase2Conductor to set
     */
    public void setPhase2Conductor(final TriplexLineConductor phase2Conductor) {
        this.phase2Conductor = phase2Conductor;
    }

    /**
     * Get the Conductor for Neutral Phase
     *
     * @return the phaseNConductor
     */
    public TriplexLineConductor getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * Set the Conductor for Neutral Phase
     *
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final TriplexLineConductor phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * Get the thickness of insulation around cabling in inches
     *
     * @return the insulationThickness
     */
    public double getInsulationThickness() {
        return insulationThickness;
    }

    /**
     * Set the thickness of insulation around cabling in inches
     *
     * @param insulationThickness
     *            the insulationThickness to set
     */
    public void setInsulationThickness(final double insulationThickness) {
        this.insulationThickness = insulationThickness;
    }

    /**
     * Get the total diameter of cable in inches
     *
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Set the total diameter of cable in inches
     *
     * @param diameter
     *            the diameter to set
     */
    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_line_configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "conductor_1", this.phase1Conductor);
        writeProperty(sb, "conductor_2", this.phase2Conductor);
        writeProperty(sb, "conductor_N", this.phaseNConductor);
        writeProperty(sb, "insulation_thickness", this.insulationThickness);
        writeProperty(sb, "diameter", this.diameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phase1Conductor, phase2Conductor, phaseNConductor, insulationThickness, diameter);
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
        final TriplexLineConfiguration other = (TriplexLineConfiguration) obj;
        return Objects.equals(this.phase1Conductor, other.phase1Conductor)
                && Objects.equals(this.phase2Conductor, other.phase2Conductor)
                && Objects.equals(this.phaseNConductor, other.phaseNConductor)
                && Objects.equals(this.insulationThickness, other.insulationThickness)
                && Objects.equals(this.diameter, other.diameter);
    }

}
