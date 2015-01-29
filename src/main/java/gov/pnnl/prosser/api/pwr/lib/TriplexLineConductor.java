/**
 *
 */
package gov.pnnl.prosser.api.pwr.lib;

import gov.pnnl.prosser.api.GldUtils;

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
     * @return the resistance
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * @param resistance
     *            the resistance to set
     */
    public void setResistance(final double resistance) {
        this.resistance = resistance;
    }

    /**
     * @return the geometricMeanRadius
     */
    public double getGeometricMeanRadius() {
        return geometricMeanRadius;
    }

    /**
     * @param geometricMeanRadius
     *            the geometricMeanRadius to set
     */
    public void setGeometricMeanRadius(final double geometricMeanRadius) {
        this.geometricMeanRadius = geometricMeanRadius;
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_line_conductor";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "resistance", this.resistance);
        GldUtils.writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius);
    }

}
