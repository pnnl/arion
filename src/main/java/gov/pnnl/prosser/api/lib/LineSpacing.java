/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * @author nord229
 *
 */
public class LineSpacing extends PowerflowLibrary {

    /**
     * distance between cables A and B in feet
     */
    private double distanceAToB;

    /**
     * distance between cables B and C in feet
     */
    private double distanceBToC;

    /**
     * distance between cables A and C in feet
     */
    private double distanceAToC;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceAToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceBToN;

    /**
     * distance between cables A and Neutral in feet
     */
    private double distanceCToN;

    public LineSpacing() {
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

    /**
     * @return the distanceAToB
     */
    public double getDistanceAToB() {
        return distanceAToB;
    }

    /**
     * @param distanceAToB
     *            the distanceAToB to set
     */
    public void setDistanceAToB(final double distanceAToB) {
        this.distanceAToB = distanceAToB;
    }

    /**
     * @return the distanceBToC
     */
    public double getDistanceBToC() {
        return distanceBToC;
    }

    /**
     * @param distanceBToC
     *            the distanceBToC to set
     */
    public void setDistanceBToC(final double distanceBToC) {
        this.distanceBToC = distanceBToC;
    }

    /**
     * @return the distanceAToC
     */
    public double getDistanceAToC() {
        return distanceAToC;
    }

    /**
     * @param distanceAToC
     *            the distanceAToC to set
     */
    public void setDistanceAToC(final double distanceAToC) {
        this.distanceAToC = distanceAToC;
    }

    /**
     * @return the distanceAToN
     */
    public double getDistanceAToN() {
        return distanceAToN;
    }

    /**
     * @param distanceAToN
     *            the distanceAToN to set
     */
    public void setDistanceAToN(final double distanceAToN) {
        this.distanceAToN = distanceAToN;
    }

    /**
     * @return the distanceBToN
     */
    public double getDistanceBToN() {
        return distanceBToN;
    }

    /**
     * @param distanceBToN
     *            the distanceBToN to set
     */
    public void setDistanceBToN(final double distanceBToN) {
        this.distanceBToN = distanceBToN;
    }

    /**
     * @return the distanceCToN
     */
    public double getDistanceCToN() {
        return distanceCToN;
    }

    /**
     * @param distanceCToN
     *            the distanceCToN to set
     */
    public void setDistanceCToN(final double distanceCToN) {
        this.distanceCToN = distanceCToN;
    }

    @Override
    public String getGLDObjectType() {
        return "line_spacing";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.appendProperty(sb, "distance_AB", this.distanceAToB);
        GLDUtils.appendProperty(sb, "distance_BC", this.distanceBToC);
        GLDUtils.appendProperty(sb, "distance_AC", this.distanceAToC);
        GLDUtils.appendProperty(sb, "distance_AN", this.distanceAToN);
        GLDUtils.appendProperty(sb, "distance_BN", this.distanceBToN);
        GLDUtils.appendProperty(sb, "distance_CN", this.distanceCToN);
    }

}
