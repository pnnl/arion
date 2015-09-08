package gov.pnnl.prosser.api.ns3.obj.wifi;

/**
 * Created by happ546 on 9/3/2015.
 */
public enum WifiMacType {

    /**
     * Adhoc/device to device
     */
    Adhoc,

    /**
     * Access Point
     */
    Ap,

    /**
     * Station/non-access point device
     */
    Sta;

    /**
     * @return a string formatted for use in [*]WifiMachHelper.setType function
     */
    @Override
    public String toString() {
        return "ns3::" + name() + "WifiMac";
    }
}
