/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

import java.util.Objects;

import org.apache.commons.math3.complex.Complex;

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
    
    /**
     * The positive sequense voltage
     */
    private Complex positiveSequenceVoltage;

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
	 * @return the positiveSequenceVoltage
	 */
	public Complex getPositiveSequenceVoltage() {
		return positiveSequenceVoltage;
	}

	/**
	 * @param positiveSequenceVoltage the positiveSequenceVoltage to set
	 */
	public void setPositiveSequenceVoltage(Complex positiveSequenceVoltage) {
		this.positiveSequenceVoltage = positiveSequenceVoltage;
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
        writeProperty(sb, "positive_sequence_voltage", positiveSequenceVoltage);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), referencePhase, positiveSequenceVoltage);
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
        final Substation other = (Substation) obj;
        return Objects.equals(this.referencePhase, other.referencePhase)
                && Objects.equals(this.positiveSequenceVoltage, other.positiveSequenceVoltage);
    }

}
