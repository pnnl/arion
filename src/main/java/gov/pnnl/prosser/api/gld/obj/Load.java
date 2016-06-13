/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Load objects represent static loads and export both voltages and current
 * 
 * @author nord229
 */
public class Load extends Node {

    /**
     * constant power load on phase A, real only, specified as W
     */
    private String phaseAConstantReal;

    /**
     * constant power load on phase B, real only, specified as W
     */
    private String phaseBConstantReal;

    /**
     * constant power load on phase C, real only, specified as W
     */
    private String phaseCConstantReal;

    public Load(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the constant power load on phase A, real only, specified as W
     * 
     * @return the phaseAConstantReal
     */
    public String getPhaseAConstantReal() {
        return phaseAConstantReal;
    }

    /**
     * Set the constant power load on phase A, real only, specified as W
     * 
     * @param phaseAConstantReal
     *            the phaseAConstantReal to set
     */
    public void setPhaseAConstantReal(final String phaseAConstantReal) {
        this.phaseAConstantReal = phaseAConstantReal;
    }

    /**
     * Get the constant power load on phase B, real only, specified as W
     * 
     * @return the phaseBConstantReal
     */
    public String getPhaseBConstantReal() {
        return phaseBConstantReal;
    }

    /**
     * Set the constant power load on phase B, real only, specified as W
     * 
     * @param phaseBConstantReal
     *            the phaseBConstantReal to set
     */
    public void setPhaseBConstantReal(final String phaseBConstantReal) {
        this.phaseBConstantReal = phaseBConstantReal;
    }

    /**
     * Get the constant power load on phase C, real only, specified as W
     * 
     * @return the phaseCConstantReal
     */
    public String getPhaseCConstantReal() {
        return phaseCConstantReal;
    }

    /**
     * Set the constant power load on phase C, real only, specified as W
     * 
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
        writeProperty(sb, "constant_power_A_real", this.phaseAConstantReal);
        writeProperty(sb, "constant_power_B_real", this.phaseBConstantReal);
        writeProperty(sb, "constant_power_C_real", this.phaseCConstantReal);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phaseAConstantReal, phaseBConstantReal, phaseCConstantReal);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Load other = (Load) obj;
        return Objects.equals(this.phaseAConstantReal, other.phaseAConstantReal)
                && Objects.equals(this.phaseBConstantReal, other.phaseBConstantReal)
                && Objects.equals(this.phaseCConstantReal, other.phaseCConstantReal);
    }

}
