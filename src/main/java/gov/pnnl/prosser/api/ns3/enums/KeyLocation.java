package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/21/2015.
 */
public enum KeyLocation {

    NO_KEY,

    KEY_INSIDE,

    KEY_ABOVE,

    KEY_BELOW;

    public String toString() {
        return name();
    }
}
