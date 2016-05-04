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
    
    public static final EnumSet<PhaseCode> AN = EnumSet.of(A, N);
    
    public static final EnumSet<PhaseCode> BN = EnumSet.of(B, N);
    
    public static final EnumSet<PhaseCode> CN = EnumSet.of(C, N);
    
    public static final EnumSet<PhaseCode> ABN = EnumSet.of(A, B, N);
    
    public static final EnumSet<PhaseCode> ACN = EnumSet.of(A, C, N);
    
    public static final EnumSet<PhaseCode> BCN = EnumSet.of(B, C, N);

    /**
     * S is a mask of S1, S2 and SN and this translation is handled by the Gld Writer
     */
    public static final EnumSet<PhaseCode> S = EnumSet.of(S1, S2, SN);

    public static final EnumSet<PhaseCode> AS = EnumSet.of(A, S1, S2, SN);

    public static final EnumSet<PhaseCode> BS = EnumSet.of(B, S1, S2, SN);

    public static final EnumSet<PhaseCode> CS = EnumSet.of(C, S1, S2, SN);

    public static final EnumSet<PhaseCode> NONE = EnumSet.noneOf(PhaseCode.class);
    
    public static final String writeGldProperties(EnumSet<PhaseCode> phaseCodes) {
        if (phaseCodes != null) {
        	final StringBuilder phaseBuilder = new StringBuilder();
            final boolean hasAllSPhases;
            
            if (phaseCodes.containsAll(PhaseCode.S)) {
            	hasAllSPhases = true;
            	phaseBuilder.append("S");
            } else {
            	hasAllSPhases = false;
            }
            
            for (final PhaseCode code : phaseCodes) {
                if (hasAllSPhases) {
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
            
            return phaseBuilder.toString();
        }
        else {
            return "";
        }
    }
}