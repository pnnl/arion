/**
 *
 */
package gov.pnnl.prosser.api.pwr.lib;

import gov.pnnl.prosser.api.GldUtils;

/**
 * Triplex Line Configuration
 *
 * @author nord229
 */
public class TriplexLineConfiguration extends LineConfiguration<TriplexLineConductor> {

    private TriplexLineConductor phase1Conductor;

    private TriplexLineConductor phase2Conductor;

    private TriplexLineConductor phaseNConductor;

    /**
     * total diameter of cable in inches
     */
    private double insulationThickness;

    /**
     * thickness of insulation around cabling in inches
     */
    private double diameter;

    /**
     * @return the phase1Conductor
     */
    public TriplexLineConductor getPhase1Conductor() {
        return phase1Conductor;
    }

    /**
     * @param phase1Conductor
     *            the phase1Conductor to set
     */
    public void setPhase1Conductor(final TriplexLineConductor phase1Conductor) {
        this.phase1Conductor = phase1Conductor;
    }

    /**
     * @return the phase2Conductor
     */
    public TriplexLineConductor getPhase2Conductor() {
        return phase2Conductor;
    }

    /**
     * @param phase2Conductor
     *            the phase2Conductor to set
     */
    public void setPhase2Conductor(final TriplexLineConductor phase2Conductor) {
        this.phase2Conductor = phase2Conductor;
    }

    /**
     * @return the phaseNConductor
     */
    public TriplexLineConductor getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final TriplexLineConductor phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * @return the insulationThickness
     */
    public double getInsulationThickness() {
        return insulationThickness;
    }

    /**
     * @param insulationThickness
     *            the insulationThickness to set
     */
    public void setInsulationThickness(final double insulationThickness) {
        this.insulationThickness = insulationThickness;
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
        return "triplex_line_configuration";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "conductor_1", this.phase1Conductor);
        GldUtils.writeProperty(sb, "conductor_2", this.phase2Conductor);
        GldUtils.writeProperty(sb, "conductor_N", this.phaseNConductor);
        GldUtils.writeProperty(sb, "insulation_thickness", this.insulationThickness);
        GldUtils.writeProperty(sb, "diameter", this.diameter);
    }

}
