/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

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

    public OverheadLineConductor(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the radius of the conductor in feet
     * 
     * @return the geometricMeanRadius
     */
    public double getGeometricMeanRadius() {
        return geometricMeanRadius;
    }

    /**
     * Set the radius of the conductor in feet
     * 
     * @param geometricMeanRadius
     *            the geometricMeanRadius to set
     */
    public void setGeometricMeanRadius(final double geometricMeanRadius) {
        this.geometricMeanRadius = geometricMeanRadius;
    }

    /**
     * Get the resistance in Ohms/mile of the conductor
     * 
     * @return the resistance
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * Set the resistance in Ohms/mile of the conductor
     * 
     * @param resistance
     *            the resistance to set
     */
    public void setResistance(final double resistance) {
        this.resistance = resistance;
    }

    /**
     * Get the Diameter of line for capacitance calculations in inches
     * 
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Set the Diameter of line for capacitance calculations in inches
     * 
     * @param diameter
     *            the diameter to set
     */
    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "overhead_line_conductor";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "geometric_mean_radius", this.geometricMeanRadius, "ft");
        writeProperty(sb, "resistance", this.resistance, "Ohm/mile");
        writeProperty(sb, "diameter", this.diameter, "in");
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), geometricMeanRadius, resistance, diameter);
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
        final OverheadLineConductor other = (OverheadLineConductor) obj;
        return Objects.equals(this.geometricMeanRadius, other.geometricMeanRadius)
                && Objects.equals(this.resistance, other.resistance)
                && Objects.equals(this.diameter, other.diameter);
    }

}
