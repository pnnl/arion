/**
 *
 */
package gov.pnnl.prosser.api.obj;

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
    private final Double nominalVoltage;

    /**
     * Never null
     */
    private final EnumSet<PhaseCode> phases;

    public PowerflowObject() {
        this.nominalVoltage = null;
        this.phases = EnumSet.noneOf(PhaseCode.class);
    }

    public PowerflowObject(final String name, final EnumSet<PhaseCode> phases) {
        super(name);
        this.phases = phases;
        this.nominalVoltage = null;
    }

    public PowerflowObject(final EnumSet<PhaseCode> phases) {
        this.phases = phases;
        this.nominalVoltage = null;
    }

    public PowerflowObject(final String name, final EnumSet<PhaseCode> phases, final Double nominalVoltage) {
        super(name);
        this.phases = phases;
        this.nominalVoltage = nominalVoltage;
    }

    public <T extends PowerflowObject, Z extends AbstractBuilder<T, Z>> PowerflowObject(final AbstractBuilder<T, Z> builder) {
        super(builder);
        this.phases = builder.phases;
        this.nominalVoltage = builder.nominalVoltage;
    }

    /**
     * @return the nominalVoltage
     */
    public Double getNominalVoltage() {
        return nominalVoltage;
    }

    /**
     * @return the phases
     */
    public EnumSet<PhaseCode> getPhases() {
        return phases;
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
    protected void writeGLDProperties(final StringBuilder sb) {
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

    public static abstract class AbstractBuilder<T extends PowerflowObject, Z extends AbstractBuilder<T, Z>> extends AbstractProsserObject.AbstractBuilder<T, Z> {

        private Double nominalVoltage;

        private EnumSet<PhaseCode> phases;

        public Z nominalVoltage(final double nominalVoltage) {
            this.nominalVoltage = nominalVoltage;
            return self();
        }

        public Z phases(final EnumSet<PhaseCode> phases) {
            this.phases = phases;
            return self();
        }
    }
}
