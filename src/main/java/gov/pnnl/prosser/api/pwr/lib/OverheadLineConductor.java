/**
 *
 */
package gov.pnnl.prosser.api.pwr.lib;

import gov.pnnl.prosser.api.GldUtils;

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
        GldUtils.writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius, "ft");
        GldUtils.writeProperty(sb, "resistance", this.resistance, "Ohm/mile");
        GldUtils.writeProperty(sb, "diameter", this.diameter, "in");
    }

}
