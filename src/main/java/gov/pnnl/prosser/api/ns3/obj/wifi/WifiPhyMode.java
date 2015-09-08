package gov.pnnl.prosser.api.ns3.obj.wifi;

/**
 * This enumeration specifies the valid Wi-Fi physical modes used by the
 * WifiRemoteStationManager.
 * Created by happ546 on 9/3/2015.
 */
// TODO get official list of all modes
public enum WifiPhyMode {

    /**
     * 802.11a, 802.11g with OFDM
     */
    OfdmRate12Mbps,

    /**
     * 802.11b
     */
    DsssRate1Mbps;

}
