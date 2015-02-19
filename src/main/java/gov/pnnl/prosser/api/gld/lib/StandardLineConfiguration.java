/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldUtils;

/**
 * Standard Line configuration for Overhead and other lines
 *
 * @author nord229
 */
public class StandardLineConfiguration<C extends Conductor> extends LineConfiguration<C> {

    private C phaseAConductor;

    private C phaseBConductor;

    private C phaseCConductor;

    private C phaseNConductor;

    private LineSpacing spacing;

    /**
     * @return the phaseAConductor
     */
    public C getPhaseAConductor() {
        return phaseAConductor;
    }

    /**
     * @param phaseAConductor
     *            the phaseAConductor to set
     */
    public void setPhaseAConductor(final C phaseAConductor) {
        this.phaseAConductor = phaseAConductor;
    }

    /**
     * @return the phaseBConductor
     */
    public C getPhaseBConductor() {
        return phaseBConductor;
    }

    /**
     * @param phaseBConductor
     *            the phaseBConductor to set
     */
    public void setPhaseBConductor(final C phaseBConductor) {
        this.phaseBConductor = phaseBConductor;
    }

    /**
     * @return the phaseCConductor
     */
    public C getPhaseCConductor() {
        return phaseCConductor;
    }

    /**
     * @param phaseCConductor
     *            the phaseCConductor to set
     */
    public void setPhaseCConductor(final C phaseCConductor) {
        this.phaseCConductor = phaseCConductor;
    }

    /**
     * @return the phaseNConductor
     */
    public C getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final C phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * @return the spacing
     */
    public LineSpacing getSpacing() {
        return spacing;
    }

    /**
     * @param spacing
     *            the spacing to set
     */
    public void setSpacing(final LineSpacing spacing) {
        this.spacing = spacing;
    }

    @Override
    public String getGldObjectType() {
        return "line_configuration";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "conductor_A", this.phaseAConductor);
        GldUtils.writeProperty(sb, "conductor_B", this.phaseBConductor);
        GldUtils.writeProperty(sb, "conductor_C", this.phaseCConductor);
        GldUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GldUtils.writeProperty(sb, "spacing", this.spacing);
    }

}
