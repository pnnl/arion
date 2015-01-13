/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GldUtils;

/**
 * Generic Line Spacing
 *
 * @author nord229
 */
public class LineSpacing extends PowerflowLibrary {

    /**
     * distance between cables A and B in feet
     */
    private final double distanceAToB;

    /**
     * distance between cables B and C in feet
     */
    private final double distanceBToC;

    /**
     * distance between cables A and C in feet
     */
    private final double distanceAToC;

    /**
     * distance between cables A and Neutral in feet
     */
    private final double distanceAToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private final double distanceBToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private final double distanceCToN;

    public LineSpacing() {
        this.distanceAToB = 0;
        this.distanceBToC = 0;
        this.distanceAToC = 0;
        this.distanceAToN = 0;
        this.distanceBToN = 0;
        this.distanceCToN = 0;
    }

    public LineSpacing(final String name, final double distanceAToB, final double distanceBToC, final double distanceAToC,
            final double distanceAToN, final double distanceBToN, final double distanceCToN) {
        super(name);
        this.distanceAToB = distanceAToB;
        this.distanceBToC = distanceBToC;
        this.distanceAToC = distanceAToC;
        this.distanceAToN = distanceAToN;
        this.distanceBToN = distanceBToN;
        this.distanceCToN = distanceCToN;
    }

    public LineSpacing(final Builder builder) {
        super(builder);
        this.distanceAToB = builder.distanceAToB;
        this.distanceBToC = builder.distanceBToC;
        this.distanceAToC = builder.distanceAToC;
        this.distanceAToN = builder.distanceAToN;
        this.distanceBToN = builder.distanceBToN;
        this.distanceCToN = builder.distanceCToN;
    }

    /**
     * @return the distanceAToB
     */
    public double getDistanceAToB() {
        return distanceAToB;
    }

    /**
     * @return the distanceBToC
     */
    public double getDistanceBToC() {
        return distanceBToC;
    }

    /**
     * @return the distanceAToC
     */
    public double getDistanceAToC() {
        return distanceAToC;
    }

    /**
     * @return the distanceAToN
     */
    public double getDistanceAToN() {
        return distanceAToN;
    }

    /**
     * @return the distanceBToN
     */
    public double getDistanceBToN() {
        return distanceBToN;
    }

    /**
     * @return the distanceCToN
     */
    public double getDistanceCToN() {
        return distanceCToN;
    }

    @Override
    public String getGLDObjectType() {
        return "line_spacing";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "distance_AB", this.distanceAToB);
        GldUtils.writeProperty(sb, "distance_BC", this.distanceBToC);
        GldUtils.writeProperty(sb, "distance_AC", this.distanceAToC);
        GldUtils.writeProperty(sb, "distance_AN", this.distanceAToN);
        GldUtils.writeProperty(sb, "distance_BN", this.distanceBToN);
        GldUtils.writeProperty(sb, "distance_CN", this.distanceCToN);
    }

    public static class Builder extends PowerflowLibrary.AbstractBuilder<LineSpacing, Builder> {
        private double distanceAToB;

        private double distanceBToC;

        private double distanceAToC;

        private double distanceAToN;

        private double distanceBToN;

        private double distanceCToN;

        public Builder distanceAToB(final double distanceAToB) {
            this.distanceAToB = distanceAToB;
            return this;
        }

        public Builder distanceBToC(final double distanceBToC) {
            this.distanceBToC = distanceBToC;
            return this;
        }

        public Builder distanceAToC(final double distanceAToC) {
            this.distanceAToC = distanceAToC;
            return this;
        }

        public Builder distanceAToN(final double distanceAToN) {
            this.distanceAToN = distanceAToN;
            return this;
        }

        public Builder distanceBToN(final double distanceBToN) {
            this.distanceBToN = distanceBToN;
            return this;
        }

        public Builder distanceCToN(final double distanceCToN) {
            this.distanceCToN = distanceCToN;
            return this;
        }

        @Override
        public LineSpacing build() {
            return new LineSpacing(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
