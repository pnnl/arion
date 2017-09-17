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
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.gld.enums.SolverMethod;

import java.util.Objects;

/**
 * GridLabD Powerflow Module
 *
 * @author nord229
 */
public class PowerflowModule extends Module {

    /**
     * powerflow solver methodology
     */
    private final SolverMethod solverMethod;

    /**
     * Newton-Raphson iteration limit (per GridLAB-D iteration)
     */
    private final Long nrIterationLimit;

    public PowerflowModule() {
        this.solverMethod = null;
        this.nrIterationLimit = null;
    }

    /**
     * Specific constructor
     *
     * @param solverMethod
     *            powerflow solver methodology
     * @param nrIterationLimit
     *            Newton-Raphson iteration limit (per GridLAB-D iteration)
     */
    public PowerflowModule(final SolverMethod solverMethod, final Long nrIterationLimit) {
        this.solverMethod = solverMethod;
        this.nrIterationLimit = nrIterationLimit;
    }

    /**
     * Get the powerflow solver methodology
     *
     * @return the solverMethod
     */
    public SolverMethod getSolverMethod() {
        return solverMethod;
    }

    /**
     * Get the Newton-Raphson iteration limit (per GridLAB-D iteration)
     *
     * @return the nrIterationLimit
     */
    public Long getNrIterationLimit() {
        return nrIterationLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solverMethod, nrIterationLimit);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PowerflowModule other = (PowerflowModule) obj;
        return Objects.equals(this.solverMethod, other.solverMethod)
                && Objects.equals(this.nrIterationLimit, other.nrIterationLimit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "powerflow";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProperties() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        writeProperty(sb, "solver_method", this.solverMethod);
        writeProperty(sb, "NR_iteration_limit", this.nrIterationLimit);
    }

}
