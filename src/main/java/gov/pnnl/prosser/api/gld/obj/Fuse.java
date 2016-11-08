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

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.RepairDistributionType;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;

/**
 * @author sund130
 *
 */
public class Fuse extends LinkObject {
    private double currentLimit;
    private double meanReplacementTime;
    private RepairDistributionType repairDistributionType;
    private SwitchStatus status;

    /**
     * @param simulator
     */
    public Fuse(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @return the currentLimit
     */
    public double getCurrentLimit() {
        return currentLimit;
    }

    /**
     * @param currentLimit the currentLimit to set
     */
    public void setCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
    }

    /**
     * @return the meanReplacementTime
     */
    public double getMeanReplacementTime() {
        return meanReplacementTime;
    }

    /**
     * @param meanReplacementTime the meanReplacementTime to set
     */
    public void setMeanReplacementTime(double meanReplacementTime) {
        this.meanReplacementTime = meanReplacementTime;
    }

    /**
     * @return the repairDistributionType
     */
    public RepairDistributionType getRepairDistributionType() {
        return repairDistributionType;
    }

    /**
     * @param repairDistributionType the repairDistributionType to set
     */
    public void setRepairDistributionType(RepairDistributionType repairDistributionType) {
        this.repairDistributionType = repairDistributionType;
    }

    /**
     * @return the status
     */
    public SwitchStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(SwitchStatus status) {
        this.status = status;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "fuse";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentLimit, meanReplacementTime, repairDistributionType, status);
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
        final Fuse other = (Fuse) obj;
        return Objects.equals(this.currentLimit, other.currentLimit)
                && Objects.equals(this.meanReplacementTime, other.meanReplacementTime)
                && Objects.equals(this.repairDistributionType, other.repairDistributionType)
                && Objects.equals(this.status, other.status);
    }
}