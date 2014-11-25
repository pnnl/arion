/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.GLDUtils;

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

    public TriplexLineConfiguration() {
    }

    public TriplexLineConfiguration(final String name, final TriplexLineConductor phase1Conductor,
            final TriplexLineConductor phase2Conductor, final TriplexLineConductor phaseNConductor,
            final double insulationThickness, final double diameter) {
        super(name);
        this.phase1Conductor = phase1Conductor;
        this.phase2Conductor = phase2Conductor;
        this.phaseNConductor = phaseNConductor;
        this.insulationThickness = insulationThickness;
        this.diameter = diameter;
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
        GLDUtils.appendProperty(sb, "conductor_1", this.phase1Conductor);
        GLDUtils.appendProperty(sb, "conductor_2", this.phase2Conductor);
        GLDUtils.appendProperty(sb, "conductor_N", this.phaseNConductor);
        GLDUtils.appendProperty(sb, "insulation_thickness", this.insulationThickness);
        GLDUtils.appendProperty(sb, "diameter", this.diameter);
    }

}
