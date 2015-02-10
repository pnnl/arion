/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;

import java.util.EnumSet;
import java.util.Objects;

/**
 * Generic Powerflow Object
 *
 * @author nord229
 */
public abstract class PowerflowObject extends AbstractProsserObject {

    /**
     * Nominal Voltage
     */
    private Double nominalVoltage;

    /**
     * Never null
     */
    private EnumSet<PhaseCode> phases;

    /**
     * @return the nominalVoltage
     */
    public Double getNominalVoltage() {
        return nominalVoltage;
    }

    /**
     * @param nominalVoltage
     *            the nominalVoltage to set
     */
    public void setNominalVoltage(final Double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
    }

    /**
     * @return the phases
     */
    public EnumSet<PhaseCode> getPhases() {
        return phases;
    }

    /**
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

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        final StringBuilder phaseBuilder = new StringBuilder();
        final boolean hasAllOfS;
        if (this.phases.containsAll(PhaseCode.S)) {
            hasAllOfS = true;
        } else {
            hasAllOfS = false;
        }
        for (final PhaseCode code : this.phases) {
            if (hasAllOfS) {
                switch (code) {
                    case S1:
                    case S2:
                    case SN:
                        continue;
                    default:
                        break;

                }
            }
            phaseBuilder.append(code.name());
        }
        if (hasAllOfS) {
            phaseBuilder.append("S");
        }

        GldUtils.writeProperty(sb, "phases", phaseBuilder.toString());
        GldUtils.writeProperty(sb, "nominal_voltage", this.nominalVoltage);
    }
}
