/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.GldUtils;

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

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GldUtils.writeProperty(sb, "reference_phase", "PHASE_" + this.referencePhase.name());
    }

    @Override
    public String getGLDObjectType() {
        return "substation";
    }

}
