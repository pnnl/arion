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
package gov.pnnl.prosser.api.gld.enums;

import java.util.EnumSet;

/**
 * Phase Codes references for Powerflow Objects
 * Models a masked field, Enum values are the individual masks and the sets are special references
 *
 * @author nord229
 *
 */
public enum PhaseCode {
    A,
    B,
    C,
    N,
    S1,
    S2,
    SN,
    GROUND;
    // TODO ABC, ABCN, S are all combinations - what do I do?

    public static final EnumSet<PhaseCode> ABCN = EnumSet.of(A, B, C, N);

    public static final EnumSet<PhaseCode> ABC = EnumSet.of(A, B, C);

    public static final EnumSet<PhaseCode> AN = EnumSet.of(A, N);

    public static final EnumSet<PhaseCode> BN = EnumSet.of(B, N);

    public static final EnumSet<PhaseCode> CN = EnumSet.of(C, N);

    public static final EnumSet<PhaseCode> ABN = EnumSet.of(A, B, N);

    public static final EnumSet<PhaseCode> ACN = EnumSet.of(A, C, N);

    public static final EnumSet<PhaseCode> BCN = EnumSet.of(B, C, N);

    /**
     * S is a mask of S1, S2 and SN and this translation is handled by the Gld Writer
     */
    public static final EnumSet<PhaseCode> S = EnumSet.of(S1, S2, SN);

    public static final EnumSet<PhaseCode> AS = EnumSet.of(A, S1, S2, SN);

    public static final EnumSet<PhaseCode> BS = EnumSet.of(B, S1, S2, SN);

    public static final EnumSet<PhaseCode> CS = EnumSet.of(C, S1, S2, SN);

    public static final EnumSet<PhaseCode> NONE = EnumSet.noneOf(PhaseCode.class);

    public static final String writeGldProperties(EnumSet<PhaseCode> phaseCodes) {
        if (phaseCodes != null) {
        	final StringBuilder phaseBuilder = new StringBuilder();
            final boolean hasAllSPhases;

            if (phaseCodes.containsAll(PhaseCode.S)) {
            	hasAllSPhases = true;
            	phaseBuilder.append("S");
            } else {
            	hasAllSPhases = false;
            }

            for (final PhaseCode code : phaseCodes) {
                if (hasAllSPhases) {
                    switch (code) {
                        case S1:
                        case S2:
                        case SN:
                            continue;
                        default:
                            break;

                    }
                }
                phaseBuilder.append(code.name());
            }

            return phaseBuilder.toString();
        }
        else {
            return "";
        }
    }
}