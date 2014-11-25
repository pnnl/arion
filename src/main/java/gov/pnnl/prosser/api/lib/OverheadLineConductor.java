/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * Overhead Line Conductor definition
 *
 * @author nord229
 */
public class OverheadLineConductor extends Conductor {

    /**
     * radius of the conductor in feet
     */
    private double geometricMeanRadius;

    /**
     * resistance in Ohms/mile of the conductor
     */
    private double resistance;

    /**
     * Diameter of line for capacitance calculations in inches
     */
    private double diameter;

    public OverheadLineConductor() {
    }

    public OverheadLineConductor(final String name, final double geometricMeanRadius, final double resistance, final double diameter) {
        super(name);
        this.geometricMeanRadius = geometricMeanRadius;
        this.resistance = resistance;
        this.diameter = diameter;
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
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * @param diameter
     *            the diameter to set
     */
    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    @Override
    public String getGLDObjectType() {
        return "overhead_line_conductor";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius, "ft");
        GLDUtils.writeProperty(sb, "resistance", this.resistance, "Ohm/mile");
        GLDUtils.writeProperty(sb, "diameter", this.diameter, "in");
    }

}
