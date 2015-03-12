/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Generic Line Spacing
 *
 * @author nord229
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

    /**
     * Get the distance between cables A and B in feet
     * 
     * @return the distanceAToB
     */
    public double getDistanceAToB() {
        return distanceAToB;
    }

    /**
     * Set the distance between cables A and B in feet
     * 
     * @param distanceAToB
     *            the distanceAToB to set
     */
    public void setDistanceAToB(final double distanceAToB) {
        this.distanceAToB = distanceAToB;
    }

    /**
     * Get the distance between cables B and C in feet
     * 
     * @return the distanceBToC
     */
    public double getDistanceBToC() {
        return distanceBToC;
    }

    /**
     * Set the distance between cables B and C in feet
     * 
     * @param distanceBToC
     *            the distanceBToC to set
     */
    public void setDistanceBToC(final double distanceBToC) {
        this.distanceBToC = distanceBToC;
    }

    /**
     * Get the distance between cables A and C in feet
     * 
     * @return the distanceAToC
     */
    public double getDistanceAToC() {
        return distanceAToC;
    }

    /**
     * Set the distance between cables A and C in feet
     * 
     * @param distanceAToC
     *            the distanceAToC to set
     */
    public void setDistanceAToC(final double distanceAToC) {
        this.distanceAToC = distanceAToC;
    }

    /**
     * Get the distance between cables A and Neutral in feet
     * 
     * @return the distanceAToN
     */
    public double getDistanceAToN() {
        return distanceAToN;
    }

    /**
     * Set the distance between cables A and Neutral in feet
     * 
     * @param distanceAToN
     *            the distanceAToN to set
     */
    public void setDistanceAToN(final double distanceAToN) {
        this.distanceAToN = distanceAToN;
    }

    /**
     * Get the distance between cables B and Neutral in feet
     * 
     * @return the distanceBToN
     */
    public double getDistanceBToN() {
        return distanceBToN;
    }

    /**
     * Get the distance between cables B and Neutral in feet
     * 
     * @param distanceBToN
     *            the distanceBToN to set
     */
    public void setDistanceBToN(final double distanceBToN) {
        this.distanceBToN = distanceBToN;
    }

    /**
     * Get the distance between cables C and Neutral in feet
     * 
     * @return the distanceCToN
     */
    public double getDistanceCToN() {
        return distanceCToN;
    }

    /**
     * Get the distance between cables C and Neutral in feet
     * 
     * @param distanceCToN
     *            the distanceCToN to set
     */
    public void setDistanceCToN(final double distanceCToN) {
        this.distanceCToN = distanceCToN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "line_spacing";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "distance_AB", this.distanceAToB);
        GldUtils.writeProperty(sb, "distance_BC", this.distanceBToC);
        GldUtils.writeProperty(sb, "distance_AC", this.distanceAToC);
        GldUtils.writeProperty(sb, "distance_AN", this.distanceAToN);
        GldUtils.writeProperty(sb, "distance_BN", this.distanceBToN);
        GldUtils.writeProperty(sb, "distance_CN", this.distanceCToN);
    }

}
