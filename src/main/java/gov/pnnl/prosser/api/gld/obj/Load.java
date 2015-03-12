/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * @author nord229
 *
 */
public class Load extends Node {

    private Node parent;

    private String phaseAConstantReal;

    private String phaseBConstantReal;

    private String phaseCConstantReal;

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    /**
     * @return the phaseAConstantReal
     */
    public String getPhaseAConstantReal() {
        return phaseAConstantReal;
    }

    /**
     * @param phaseAConstantReal
     *            the phaseAConstantReal to set
     */
    public void setPhaseAConstantReal(final String phaseAConstantReal) {
        this.phaseAConstantReal = phaseAConstantReal;
    }

    /**
     * @return the phaseBConstantReal
     */
    public String getPhaseBConstantReal() {
        return phaseBConstantReal;
    }

    /**
     * @param phaseBConstantReal
     *            the phaseBConstantReal to set
     */
    public void setPhaseBConstantReal(final String phaseBConstantReal) {
        this.phaseBConstantReal = phaseBConstantReal;
    }

    /**
     * @return the phaseCConstantReal
     */
    public String getPhaseCConstantReal() {
        return phaseCConstantReal;
    }

    /**
     * @param phaseCConstantReal
     *            the phaseCConstantReal to set
     */
    public void setPhaseCConstantReal(final String phaseCConstantReal) {
        this.phaseCConstantReal = phaseCConstantReal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "load";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
        GldUtils.writeProperty(sb, "constant_power_A_real", this.phaseAConstantReal);
        GldUtils.writeProperty(sb, "constant_power_B_real", this.phaseBConstantReal);
        GldUtils.writeProperty(sb, "constant_power_C_real", this.phaseCConstantReal);
    }

}
