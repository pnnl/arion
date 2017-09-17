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
package gov.pnnl.prosser.api.gld.lib;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Underground Line Conductor definition
 *
 * @author sund130
 */
public class UndergroundLineConductor extends Conductor {
    private double outerDiameter;
    private double conductorGmr;
    private double conductorDiameter;
    private double conductorResistance;
    private double neutralGmr;
    private double neutralResistance;
    private double neutralDiameter;
    private int neutralStrands;
    private double shieldGmr;
    private double shieldResistance;

    public UndergroundLineConductor(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the outer diameter
     *
     * @return the outerDiameter
     */
    public double getOuterDiameter() {
        return outerDiameter;
    }

	/**
	 * Set the outer diameter
	 *
	 * @param outerDiameter the outerDiameter to set
	 */
	public void setOuterDiameter(double outerDiameter) {
		this.outerDiameter = outerDiameter;
	}

    /**
     * Get the conductor gmr
     *
     * @return the conductor gmr
     */
    public double getConductorGmr() {
        return conductorGmr;
    }

    /**
     * Set the conductor gmr
     *
     * @param conductorGmr
     *            the conductorGmr to set
     */
    public void setConductorGmr(final double conductorGmr) {
        this.conductorGmr = conductorGmr;
    }

    /**
     * Get the conductor diameter
     *
     * @return the conductor diameter
     */
    public double getConductorDiameter() {
        return conductorDiameter;
    }

    /**
     * Set the conductor diameter
     *
     * @param diameter
     *            the conductor diameter to set
     */
    public void setConductorDiameter(final double diameter) {
        this.conductorDiameter = diameter;
    }

    /**
	 * @return the conductorResistance
	 */
	public double getConductorResistance() {
		return conductorResistance;
	}

	/**
	 * @param conductorResistance the conductorResistance to set
	 */
	public void setConductorResistance(double conductorResistance) {
		this.conductorResistance = conductorResistance;
	}

	/**
	 * @return the neutralGmr
	 */
	public double getNeutralGmr() {
		return neutralGmr;
	}

	/**
	 * @param neutralGmr the neutralGmr to set
	 */
	public void setNeutralGmr(double neutralGmr) {
		this.neutralGmr = neutralGmr;
	}

	/**
	 * @return the neutralResistance
	 */
	public double getNeutralResistance() {
		return neutralResistance;
	}

	/**
	 * @param neutralResistance the neutralResistance to set
	 */
	public void setNeutralResistance(double neutralResistance) {
		this.neutralResistance = neutralResistance;
	}

	/**
	 * @return the neutralDiameter
	 */
	public double getNeutralDiameter() {
		return neutralDiameter;
	}

	/**
	 * @param neutralDiameter the neutralDiameter to set
	 */
	public void setNeutralDiameter(double neutralDiameter) {
		this.neutralDiameter = neutralDiameter;
	}

	/**
	 * @return the neutralStrands
	 */
	public int getNeutralStrands() {
		return neutralStrands;
	}

	/**
	 * @param neutralStrands the neutralStrands to set
	 */
	public void setNeutralStrands(int neutralStrands) {
		this.neutralStrands = neutralStrands;
	}

	/**
	 * @return the shieldGmr
	 */
	public double getShieldGmr() {
		return shieldGmr;
	}

	/**
	 * @param shieldGmr the shieldGmr to set
	 */
	public void setShieldGmr(double shieldGmr) {
		this.shieldGmr = shieldGmr;
	}

	/**
	 * @return the shieldResistance
	 */
	public double getShieldResistance() {
		return shieldResistance;
	}

	/**
	 * @param shieldResistance the shieldResistance to set
	 */
	public void setShieldResistance(double shieldResistance) {
		this.shieldResistance = shieldResistance;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "underground_line_conductor";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "outer_diameter", this.outerDiameter);
        writeProperty(sb, "conductor_gmr", this.conductorGmr);
        writeProperty(sb, "conductor_diameter", this.conductorDiameter);
        writeProperty(sb, "conductor_resistance", this.conductorResistance);
        writeProperty(sb, "neutral_gmr", this.neutralGmr);
        writeProperty(sb, "neutral_resistance", this.neutralResistance);
        writeProperty(sb, "neutral_diameter", this.neutralDiameter);
        writeProperty(sb, "neutral_strands", this.neutralStrands);
        writeProperty(sb, "shield_gmr", this.shieldGmr);
        writeProperty(sb, "shield_resistance", this.shieldResistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outerDiameter, conductorGmr, conductorDiameter, conductorResistance, neutralGmr,
                neutralResistance, neutralDiameter, neutralStrands, shieldGmr, shieldResistance);
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
        final UndergroundLineConductor other = (UndergroundLineConductor) obj;
        return Objects.equals(this.outerDiameter, other.outerDiameter)
                && Objects.equals(this.conductorGmr, other.conductorGmr)
                && Objects.equals(this.conductorDiameter, other.conductorDiameter)
                && Objects.equals(this.conductorResistance, other.conductorResistance)
                && Objects.equals(this.neutralGmr, other.neutralGmr)
                && Objects.equals(this.neutralResistance, other.neutralResistance)
                && Objects.equals(this.neutralDiameter, other.neutralDiameter)
                && Objects.equals(this.neutralStrands, other.neutralStrands)
                && Objects.equals(this.shieldGmr, other.shieldGmr)
                && Objects.equals(this.shieldResistance, other.shieldResistance);
    }

}
