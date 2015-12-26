package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/21/2015.
 */
public enum FileFormat {

    FORMATTED,

    COMMA_SEPARATED,

    SPACE_SEPARATED,

    TAB_SEPARATED;

    public String toString() {
        return name();
    }
}
