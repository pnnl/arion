/**
 *
 */
package gov.pnnl.prosser.api.lib;

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
    private final double resistance;

    /**
     * geometric mean radius of the cable
     */
    private final double geometricMeanRadius;

    public TriplexLineConductor() {
        this.resistance = 0;
        this.geometricMeanRadius = 0;
    }

    public TriplexLineConductor(final String name, final double resistance, final double geometricMeanRadius) {
        super(name);
        this.resistance = resistance;
        this.geometricMeanRadius = geometricMeanRadius;
    }

    public TriplexLineConductor(final Builder builder) {
        super(builder);
        this.resistance = builder.resistance;
        this.geometricMeanRadius = builder.geometricMeanRadius;
    }

    /**
     * @return the resistance
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * @return the geometricMeanRadius
     */
    public double getGeometricMeanRadius() {
        return geometricMeanRadius;
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

    public static class Builder extends Conductor.AbstractBuilder<TriplexLineConductor, Builder> {

        private double resistance;

        private double geometricMeanRadius;

        public Builder resistance(final double resistance) {
            this.resistance = resistance;
            return this;
        }

        public Builder geometricMeanRadius(final double geometricMeanRadius) {
            this.geometricMeanRadius = geometricMeanRadius;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TriplexLineConductor build() {
            return new TriplexLineConductor(this);
        }

    }

}
