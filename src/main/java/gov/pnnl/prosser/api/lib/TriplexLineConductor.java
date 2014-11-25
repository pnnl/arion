/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

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

    public TriplexLineConductor() {
    }

    public TriplexLineConductor(final String name, final double resistance, final double geometricMeanRadius) {
        super(name);
        this.resistance = resistance;
        this.geometricMeanRadius = geometricMeanRadius;
    }

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
        GLDUtils.appendProperty(sb, "resistance", this.resistance);
        GLDUtils.appendProperty(sb, "geometric_mean_radius", this.geometricMeanRadius);
    }

}
