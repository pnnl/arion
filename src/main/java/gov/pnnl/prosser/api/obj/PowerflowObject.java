/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GLDUtils;

import java.util.EnumSet;
import java.util.Objects;

/**
 * Generic Powerflow Object
 *
 * @author nord229
 */
public abstract class PowerflowObject extends AbstractProsserObject {

    // TODO ABC, ABCN, S are all combinations - what do I do?
    public enum PhaseCode {
        A,
        B,
        C,
        N,
        S1,
        S2,
        SN,
        GROUND;

        public static final EnumSet<PhaseCode> ABCN = EnumSet.of(A, B, C, N);

        public static final EnumSet<PhaseCode> ABC = EnumSet.of(A, B, C);

        public static final EnumSet<PhaseCode> S = EnumSet.of(S1, S2, SN);

        public static final EnumSet<PhaseCode> NONE = EnumSet.noneOf(PhaseCode.class);
    }

    /**
     * Nominal Voltage
     */
    private Double nominalVoltage;

    /**
     * Never null
     */
    private EnumSet<PhaseCode> phases;

    public PowerflowObject() {
    }

    public PowerflowObject(final EnumSet<PhaseCode> phases) {
        this.phases = phases;
    }

    public PowerflowObject(final String name, final EnumSet<PhaseCode> phases) {
        super(name);
        this.phases = phases;
    }

    public PowerflowObject(final String name, final EnumSet<PhaseCode> phases, final Double nominalVoltage) {
        this(name, phases);
        this.nominalVoltage = nominalVoltage;
    }

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

        GLDUtils.writeProperty(sb, "phases", phaseBuilder.toString());
        GLDUtils.writeProperty(sb, "nominal_voltage", this.nominalVoltage);
    }
}
