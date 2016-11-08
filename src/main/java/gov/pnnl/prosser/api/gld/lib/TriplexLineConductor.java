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
 * Triplex Line Conductor
 *
 * @author nord229
 */
public class TriplexLineConductor extends Conductor {

    /**
     * resistance of cable in ohm/mile
     */
    private double resistance;

    /**
     * geometric mean radius of the cable
     */
    private double geometricMeanRadius;

    public TriplexLineConductor(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the resistance of cable in ohm/mile
     *
     * @return the resistance
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * Set the resistance of cable in ohm/mile
     *
     * @param resistance
     *            the resistance to set
     */
    public void setResistance(final double resistance) {
        this.resistance = resistance;
    }

    /**
     * Get the geometric mean radius of the cable
     *
     * @return the geometricMeanRadius
     */
    public double getGeometricMeanRadius() {
        return geometricMeanRadius;
    }

    /**
     * Set the geometric mean radius of the cable
     *
     * @param geometricMeanRadius
     *            the geometricMeanRadius to set
     */
    public void setGeometricMeanRadius(final double geometricMeanRadius) {
        this.geometricMeanRadius = geometricMeanRadius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_line_conductor";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "resistance", this.resistance);
        writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), resistance, geometricMeanRadius);
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
        final TriplexLineConductor other = (TriplexLineConductor) obj;
        return Objects.equals(this.resistance, other.resistance)
                && Objects.equals(this.geometricMeanRadius, other.geometricMeanRadius);
    }

}
