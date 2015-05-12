/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

/**
 * Substation object serves as a connecting object between the powerflow and network solvers
 * 
 * @author nord229
 *
 */
public class Substation extends Node {

    /**
     * the reference phase for the positive sequence voltage
     */
    private PhaseCode referencePhase;

    public Substation(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the reference phase for the positive sequence voltage
     * 
     * @return the referencePhase
     */
    public PhaseCode getReferencePhase() {
        return referencePhase;
    }

    /**
     * Set the reference phase for the positive sequence voltage
     * 
     * @param referencePhase
     *            the referencePhase to set
     */
    public void setReferencePhase(final PhaseCode referencePhase) {
        this.referencePhase = referencePhase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "substation";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "reference_phase", "PHASE_" + this.referencePhase.name());
    }

}
