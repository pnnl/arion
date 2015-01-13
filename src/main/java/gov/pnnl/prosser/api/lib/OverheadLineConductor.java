/**
 *
 */
package gov.pnnl.prosser.api.lib;

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
    private final double geometricMeanRadius;

    /**
     * resistance in Ohms/mile of the conductor
     */
    private final double resistance;

    /**
     * Diameter of line for capacitance calculations in inches
     */
    private final double diameter;

    public OverheadLineConductor() {
        this.geometricMeanRadius = 0;
        this.resistance = 0;
        this.diameter = 0;
    }

    public OverheadLineConductor(final String name, final double geometricMeanRadius, final double resistance, final double diameter) {
        super(name);
        this.geometricMeanRadius = geometricMeanRadius;
        this.resistance = resistance;
        this.diameter = diameter;
    }

    public OverheadLineConductor(final Builder builder) {
        super(builder);
        this.geometricMeanRadius = builder.geometricMeanRadius;
        this.resistance = builder.resistance;
        this.diameter = builder.diameter;
    }

    /**
     * @return the geometricMeanRadius
     */
    public double getGeometricMeanRadius() {
        return geometricMeanRadius;
    }

    /**
     * @return the resistance
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
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

    public static class Builder extends Conductor.AbstractBuilder<OverheadLineConductor, Builder> {
        private double geometricMeanRadius;

        private double resistance;

        private double diameter;

        public Builder geometricMeanRadius(final double geometricMeanRadius) {
            this.geometricMeanRadius = geometricMeanRadius;
            return this;
        }

        public Builder resistance(final double resistance) {
            this.resistance = resistance;
            return this;
        }

        public Builder diameter(final double diameter) {
            this.diameter = diameter;
            return this;
        }

        @Override
        public OverheadLineConductor build() {
            return new OverheadLineConductor(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
