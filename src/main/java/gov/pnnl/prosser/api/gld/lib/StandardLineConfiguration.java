/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Standard Line configuration for Overhead and other lines
 *
 * @author nord229
 */
public class StandardLineConfiguration<C extends Conductor> extends LineConfiguration<C> {

    /**
     * Conductor for Phase A
     */
    private C phaseAConductor;

    /**
     * Conductor for Phase B
     */
    private C phaseBConductor;

    /**
     * Conductor for Phase C
     */
    private C phaseCConductor;

    /**
     * Conductor for Neutral Phase
     */
    private C phaseNConductor;

    /**
     * Line Spacing for this Line
     */
    private LineSpacing spacing;

    /**
     * Get the Conductor for Phase A
     * 
     * @return the phaseAConductor
     */
    public C getPhaseAConductor() {
        return phaseAConductor;
    }

    /**
     * Set the Conductor for Phase A
     * 
     * @param phaseAConductor
     *            the phaseAConductor to set
     */
    public void setPhaseAConductor(final C phaseAConductor) {
        this.phaseAConductor = phaseAConductor;
    }

    /**
     * Get the Conductor for Phase B
     * 
     * @return the phaseBConductor
     */
    public C getPhaseBConductor() {
        return phaseBConductor;
    }

    /**
     * Set the Conductor for Phase B
     * 
     * @param phaseBConductor
     *            the phaseBConductor to set
     */
    public void setPhaseBConductor(final C phaseBConductor) {
        this.phaseBConductor = phaseBConductor;
    }

    /**
     * Get the Conductor for Phase C
     * 
     * @return the phaseCConductor
     */
    public C getPhaseCConductor() {
        return phaseCConductor;
    }

    /**
     * Set the Conductor for Phase C
     * 
     * @param phaseCConductor
     *            the phaseCConductor to set
     */
    public void setPhaseCConductor(final C phaseCConductor) {
        this.phaseCConductor = phaseCConductor;
    }

    /**
     * Get the Conductor for Neutral Phase
     * 
     * @return the phaseNConductor
     */
    public C getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * Set the Conductor for Neutral Phase
     * 
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final C phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * Get the Line Spacing for this Line
     * 
     * @return the spacing
     */
    public LineSpacing getSpacing() {
        return spacing;
    }

    /**
     * Set the Line Spacing for this Line
     * 
     * @param spacing
     *            the spacing to set
     */
    public void setSpacing(final LineSpacing spacing) {
        this.spacing = spacing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "line_configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "conductor_A", this.phaseAConductor);
        GldUtils.writeProperty(sb, "conductor_B", this.phaseBConductor);
        GldUtils.writeProperty(sb, "conductor_C", this.phaseCConductor);
        GldUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GldUtils.writeProperty(sb, "spacing", this.spacing);
    }

}
