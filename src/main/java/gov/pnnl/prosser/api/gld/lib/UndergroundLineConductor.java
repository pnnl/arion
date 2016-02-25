/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

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
     * @param outerDiameter the outerDiameter to set
     */
    public void setGeometricMeanRadius(final double outerDiameter) {
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
    public void setResistance(final double conductorGmr) {
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

}
