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
 * Generic Line Spacing
 *
 * @author nord229
 */
public class LineSpacing extends PowerflowLibrary {

    /**
     * distance between cables A and B in feet
     */
    private double distanceAToB;

    /**
     * distance between cables B and C in feet
     */
    private double distanceBToC;

    /**
     * distance between cables A and C in feet
     */
    private double distanceAToC;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceAToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceBToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceCToN;

    public LineSpacing(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the distance between cables A and B in feet
     *
     * @return the distanceAToB
     */
    public double getDistanceAToB() {
        return distanceAToB;
    }

    /**
     * Set the distance between cables A and B in feet
     *
     * @param distanceAToB
     *            the distanceAToB to set
     */
    public void setDistanceAToB(final double distanceAToB) {
        this.distanceAToB = distanceAToB;
    }

    /**
     * Get the distance between cables B and C in feet
     *
     * @return the distanceBToC
     */
    public double getDistanceBToC() {
        return distanceBToC;
    }

    /**
     * Set the distance between cables B and C in feet
     *
     * @param distanceBToC
     *            the distanceBToC to set
     */
    public void setDistanceBToC(final double distanceBToC) {
        this.distanceBToC = distanceBToC;
    }

    /**
     * Get the distance between cables A and C in feet
     *
     * @return the distanceAToC
     */
    public double getDistanceAToC() {
        return distanceAToC;
    }

    /**
     * Set the distance between cables A and C in feet
     *
     * @param distanceAToC
     *            the distanceAToC to set
     */
    public void setDistanceAToC(final double distanceAToC) {
        this.distanceAToC = distanceAToC;
    }

    /**
     * Get the distance between cables A and Neutral in feet
     *
     * @return the distanceAToN
     */
    public double getDistanceAToN() {
        return distanceAToN;
    }

    /**
     * Set the distance between cables A and Neutral in feet
     *
     * @param distanceAToN
     *            the distanceAToN to set
     */
    public void setDistanceAToN(final double distanceAToN) {
        this.distanceAToN = distanceAToN;
    }

    /**
     * Get the distance between cables B and Neutral in feet
     *
     * @return the distanceBToN
     */
    public double getDistanceBToN() {
        return distanceBToN;
    }

    /**
     * Get the distance between cables B and Neutral in feet
     *
     * @param distanceBToN
     *            the distanceBToN to set
     */
    public void setDistanceBToN(final double distanceBToN) {
        this.distanceBToN = distanceBToN;
    }

    /**
     * Get the distance between cables C and Neutral in feet
     *
     * @return the distanceCToN
     */
    public double getDistanceCToN() {
        return distanceCToN;
    }

    /**
     * Get the distance between cables C and Neutral in feet
     *
     * @param distanceCToN
     *            the distanceCToN to set
     */
    public void setDistanceCToN(final double distanceCToN) {
        this.distanceCToN = distanceCToN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "line_spacing";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "distance_AB", this.distanceAToB);
        writeProperty(sb, "distance_BC", this.distanceBToC);
        writeProperty(sb, "distance_AC", this.distanceAToC);
        writeProperty(sb, "distance_AN", this.distanceAToN);
        writeProperty(sb, "distance_BN", this.distanceBToN);
        writeProperty(sb, "distance_CN", this.distanceCToN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), distanceAToB, distanceBToC, distanceAToC, distanceAToN, distanceBToN, distanceCToN);
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
        final LineSpacing other = (LineSpacing) obj;
        return Objects.equals(this.distanceAToB, other.distanceAToB)
                && Objects.equals(this.distanceBToC, other.distanceBToC)
                && Objects.equals(this.distanceAToC, other.distanceAToC)
                && Objects.equals(this.distanceAToN, other.distanceAToN)
                && Objects.equals(this.distanceBToN, other.distanceBToN)
                && Objects.equals(this.distanceCToN, other.distanceCToN);
    }

}
