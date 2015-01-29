/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import java.util.EnumSet;

/**
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

    public static final EnumSet<PhaseCode> S = EnumSet.of(S1, S2, SN);

    public static final EnumSet<PhaseCode> NONE = EnumSet.noneOf(PhaseCode.class);
}