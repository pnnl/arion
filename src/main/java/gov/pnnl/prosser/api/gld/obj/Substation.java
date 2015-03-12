/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

/**
 * @author nord229
 *
 */
public class Substation extends Node {

    private PhaseCode referencePhase;

    /**
     * @return the referencePhase
     */
    public PhaseCode getReferencePhase() {
        return referencePhase;
    }

    /**
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
        GldUtils.writeProperty(sb, "reference_phase", "PHASE_" + this.referencePhase.name());
    }

}
