/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * Standard Line configuration for Overhead and other lines
 *
 * @author nord229
 */
public class StandardLineConfiguration<T extends Conductor> extends LineConfiguration<T> {

    private T phaseAConductor;

    private T phaseBConductor;

    private T phaseCConductor;

    private T phaseNConductor;

    private LineSpacing spacing;

    public StandardLineConfiguration() {
    }

    public StandardLineConfiguration(final String name, final T phaseAConductor, final T phaseBConductor,
            final T phaseCConductor, final T phaseNConductor, final LineSpacing spacing) {
        super(name);
        this.phaseAConductor = phaseAConductor;
        this.phaseBConductor = phaseBConductor;
        this.phaseCConductor = phaseCConductor;
        this.phaseNConductor = phaseNConductor;
        this.spacing = spacing;
    }

    /**
     * @return the phaseAConductor
     */
    public T getPhaseAConductor() {
        return phaseAConductor;
    }

    /**
     * @param phaseAConductor
     *            the phaseAConductor to set
     */
    public void setPhaseAConductor(final T phaseAConductor) {
        this.phaseAConductor = phaseAConductor;
    }

    /**
     * @return the phaseBConductor
     */
    public T getPhaseBConductor() {
        return phaseBConductor;
    }

    /**
     * @param phaseBConductor
     *            the phaseBConductor to set
     */
    public void setPhaseBConductor(final T phaseBConductor) {
        this.phaseBConductor = phaseBConductor;
    }

    /**
     * @return the phaseCConductor
     */
    public T getPhaseCConductor() {
        return phaseCConductor;
    }

    /**
     * @param phaseCConductor
     *            the phaseCConductor to set
     */
    public void setPhaseCConductor(final T phaseCConductor) {
        this.phaseCConductor = phaseCConductor;
    }

    /**
     * @return the phaseNConductor
     */
    public T getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final T phaseNConductor) {
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
    public String getGLDObjectType() {
        return "line_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "conductor_A", this.phaseAConductor);
        GLDUtils.writeProperty(sb, "conductor_B", this.phaseBConductor);
        GLDUtils.writeProperty(sb, "conductor_C", this.phaseCConductor);
        GLDUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GLDUtils.writeProperty(sb, "spacing", this.spacing);
    }

}
