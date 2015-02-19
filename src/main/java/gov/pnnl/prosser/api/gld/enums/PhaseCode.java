/**
 *
 */
package gov.pnnl.prosser.api.gld.enums;

import java.util.EnumSet;

/**
 * Phase Codes references for Powerflow Objects
 * Models a masked field, Enum values are the individual masks and the sets are special references
 * 
 * @author nord229
 *
 */
public enum PhaseCode {
    A,
    B,
    C,
    N,
    S1,
    S2,
    SN,
    GROUND;
    // TODO ABC, ABCN, S are all combinations - what do I do?

    public static final EnumSet<PhaseCode> ABCN = EnumSet.of(A, B, C, N);

    public static final EnumSet<PhaseCode> ABC = EnumSet.of(A, B, C);

    /**
     * S is a mask of S1, S2 and SN and this translation is handled by the Gld Writer
     */
    public static final EnumSet<PhaseCode> S = EnumSet.of(S1, S2, SN);

    public static final EnumSet<PhaseCode> AS = EnumSet.of(A, S1, S2, SN);

    public static final EnumSet<PhaseCode> BS = EnumSet.of(B, S1, S2, SN);

    public static final EnumSet<PhaseCode> CS = EnumSet.of(C, S1, S2, SN);

    public static final EnumSet<PhaseCode> NONE = EnumSet.noneOf(PhaseCode.class);
}