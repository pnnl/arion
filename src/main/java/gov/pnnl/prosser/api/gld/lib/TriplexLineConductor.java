/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.gld.GldUtils;

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
        GldUtils.writeProperty(sb, "resistance", this.resistance);
        GldUtils.writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius);
    }

}
