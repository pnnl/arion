package gov.pnnl.prosser.api.ns3.obj.wifi;

/**
 * These enumerations specify the valid PCAP data link types supported by the
 * ns-3 YansWifiPhyHelper class.  All documentation for them is sourced from the
 * YansWifiPhyHelper documentation in the API on the ns-3 website (nsnam.org).
 * See http://wiki.wireshark.org/Development/LibpcapFileFormat for more information
 * on these formats.
 * Created by happ546 on 9/3/2015.
 */
public enum PcapDataLinkType {

    /**
     * IEEE 802.11 Wireless LAN headers on packets
     */
    DLT_IEEE802_11,

    /**
     * Include Prism monitor mode information
     */
    DLT_PRISM_HEADER,

    /**
     * Include Radiotap link layer information
     */
    DLT_IEEE802_11_RADIO;
}
