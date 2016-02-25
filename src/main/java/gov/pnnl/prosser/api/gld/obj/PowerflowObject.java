/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

import java.util.EnumSet;
import java.util.Objects;

/**
 * Generic Powerflow Object
 *
 * @author nord229
 */
public abstract class PowerflowObject extends AbstractGldObject {

    /**
     * Nominal Voltage
     */
    private Double nominalVoltage;

    /**
     * Phases this object is using
     */
    private EnumSet<PhaseCode> phases;

    public PowerflowObject(final GldSimulator simulator) {
        super(simulator);
        simulator.ensurePowerflowModule();
    }

    /**
     * Get the Nominal Voltage
     * 
     * @return the nominalVoltage
     */
    public Double getNominalVoltage() {
        return nominalVoltage;
    }

    /**
     * Set the Nominal Voltage
     * 
     * @param nominalVoltage
     *            the nominalVoltage to set
     */
    public void setNominalVoltage(final Double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
    }

    /**
     * Get the Phases this object is using
     * 
     * @return the phases
     */
    public EnumSet<PhaseCode> getPhases() {
        return phases;
    }

    /**
     * Set the Phases this object is using
     * 
     * @param phases
     *            the phases to set
     */
    public void setPhases(final EnumSet<PhaseCode> phases) {
        this.phases = phases;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.nominalVoltage, this.phases);
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
        final PowerflowObject other = (PowerflowObject) obj;
        return Objects.equals(this.nominalVoltage, other.nominalVoltage)
                && Objects.equals(this.phases, other.phases);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "phases", PhaseCode.writeGldProperties(this.phases));
        writeProperty(sb, "nominal_voltage", this.nominalVoltage);
    }
}
