package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.enums.MobilityModel;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.Channel;
import gov.pnnl.prosser.api.ns3.obj.MobilityHelper;

/**
 * Created by happ546 on 9/3/2015.
 */
public class WifiChannel extends Channel {

    private WifiPhyStandard phyStandard;
    private WifiPhyMode phyMode;
    private Ssid ssid;
    private MobilityModel mobilityModel;
    private WifiHelper wifiHelper;
    private YansWifiChannelHelper wifiChannelHelper;
    private YansWifiPhyHelper wifiPhyHelper;
    private NqosWifiMacHelper wifiMacHelperAdhoc, wifiMacHelperAp, wifiMacHelperSta;
    private MobilityHelper mobilityHelper;

    /**
     * Creates a nameless WifiChannel defaulting to 802.11g with
     * 12Mbps OFDM mode.
     */
    public WifiChannel() {
        super();
        this.setType(NetworkType.WIFI);
        phyStandard = WifiPhyStandard.WIFI_PHY_STANDARD_80211g;
        phyMode = WifiPhyMode.OfdmRate12Mbps;
    }

    /**
     * Creates a named WifiChannel.  Instantiates necessary helpers
     * @param name
     *      the String to name this WifiChannel
     */
    public WifiChannel(String name) {
        this();
        this.setName(name);
        this.getAsPointer();

        String namePostfix = "_" + getName();

        wifiChannelHelper = new YansWifiChannelHelper("wifiChanHelper_" + namePostfix);
        wifiPhyHelper = new YansWifiPhyHelper("wifiPhyHelper_" + namePostfix);
        wifiMacHelperAdhoc = new NqosWifiMacHelper("nqosWifiMacHelperADHOC_" + namePostfix);
        wifiMacHelperAp = new NqosWifiMacHelper("nqosWifiMacHelperAP_" + namePostfix);
        wifiMacHelperSta = new NqosWifiMacHelper("nqosWifiMacHelperSTA_" + namePostfix);
        mobilityHelper = new MobilityHelper("mobilityHelper_" + namePostfix);
        wifiHelper = new WifiHelper("wifiHelper_" + namePostfix);
    }

    public WifiPhyStandard getWifiPhyStandard() {
        return phyStandard;
    }

    public void setWifiPhyStandard(WifiPhyStandard phyStandard) {
        this.phyStandard = phyStandard;
    }

    public WifiPhyMode getWifiPhyMode() {
        return phyMode;
    }

    // TODO check for compatibility of phyMode with phyStandard, or leave up to user?
    public void setWifiPhyMode(WifiPhyMode phyMode) {
        this.phyMode = phyMode;
    }

    public Ssid getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = new Ssid(ssid);
    }

    public MobilityModel getMobilityModel() {
        return mobilityModel;
    }

    public void setMobilityModel(MobilityModel mobilityModel) {
        this.mobilityModel = mobilityModel;
    }

    public WifiHelper getWifiHelper() {
        return wifiHelper;
    }

    public YansWifiChannelHelper getWifiChannelHelper() {
        return wifiChannelHelper;
    }

    public YansWifiPhyHelper getWifiPhyHelper() {
        return wifiPhyHelper;
    }

    public NqosWifiMacHelper getWifiMacHelperAdhoc() {
        return wifiMacHelperAdhoc;
    }

    public NqosWifiMacHelper getWifiMacHelperAp() {
        return wifiMacHelperAp;
    }

    public NqosWifiMacHelper getWifiMacHelperSta() {
        return wifiMacHelperSta;
    }

    public MobilityHelper getMobilityHelper() {
        return mobilityHelper;
    }
}
