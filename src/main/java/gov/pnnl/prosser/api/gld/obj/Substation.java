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
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

import java.util.Objects;

import org.apache.commons.math3.complex.Complex;

/**
 * Substation object serves as a connecting object between the powerflow and network solvers
 *
 * @author nord229
 *
 */
public class Substation extends Node {

    /**
     * the reference phase for the positive sequence voltage
     */
    private PhaseCode referencePhase;

    /**
     * The positive sequense voltage
     */
    private Complex positiveSequenceVoltage;

	public Substation(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the reference phase for the positive sequence voltage
     *
     * @return the referencePhase
     */
    public PhaseCode getReferencePhase() {
        return referencePhase;
    }

    /**
     * Set the reference phase for the positive sequence voltage
     *
     * @param referencePhase
     *            the referencePhase to set
     */
    public void setReferencePhase(final PhaseCode referencePhase) {
        this.referencePhase = referencePhase;
    }

    /**
	 * @return the positiveSequenceVoltage
	 */
	public Complex getPositiveSequenceVoltage() {
		return positiveSequenceVoltage;
	}

	/**
	 * @param positiveSequenceVoltage the positiveSequenceVoltage to set
	 */
	public void setPositiveSequenceVoltage(Complex positiveSequenceVoltage) {
		this.positiveSequenceVoltage = positiveSequenceVoltage;
	}



    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "substation";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "reference_phase", "PHASE_" + this.referencePhase.name());
        writeProperty(sb, "positive_sequence_voltage", positiveSequenceVoltage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), referencePhase, positiveSequenceVoltage);
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
        final Substation other = (Substation) obj;
        return Objects.equals(this.referencePhase, other.referencePhase)
                && Objects.equals(this.positiveSequenceVoltage, other.positiveSequenceVoltage);
    }

}
