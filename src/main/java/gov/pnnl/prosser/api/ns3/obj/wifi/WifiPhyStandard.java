package gov.pnnl.prosser.api.ns3.obj.wifi;

/**
 * These enumerations specify the valid Wi-Fi physical standards implemented by
 * ns-3.  All documentation for them is sourced from the WifiPhyStandard
 * documentation in the API on the ns-3 website (nsnam.org).
 *
 * Created by happ546 on 9/1/2015.
 */
public enum WifiPhyStandard {

    /**
     * OFDM (Orthogonal Frequency-Division Multiplexing)
     * PHY for the 5 GHz band (Clause 17)
     */
    WIFI_PHY_STANDARD_80211a,

    /**
     * DSSS (Direct-Sequence Spread Spectrum)
     * PHY (Clause 15) and HR/DSSS (High-Rate DSSS) PHY (Clause 18)
     */
    WIFI_PHY_STANDARD_80211b,

    /**
     * ERP-OFDM (Extended Rate Physicals-OFDM)
     * PHY (Clause 19, Section 19.5)
     */
    WIFI_PHY_STANDARD_80211g,

    /**
     * OFDM PHY for the 5 GHz band (Clause 17 with 10 MHz channel bandwidth)
     */
    WIFI_PHY_STANDARD_80211_10MHZ,

    /**
     * OFDM PHY for the 5 GHz band (Clause 17 with 5 MHz channel bandwidth)
     */
    WIFI_PHY_STANDARD_80211_5MHZ,

    /**
     * This is intended to be the configuration used in this paper: Gavin Holland, Nitin Vaidya,
     * and Paramvir Bahl, "A Rate-Adaptive MAC Protocol for Multi-Hop Wireless Networks",
     * in Proc. of ACM MOBICOM, 2001.
     */
    WIFI_PHY_STANDARD_holland,

    /**
     * HT (High Throughput) OFDM PHY for the 2.4 GHz band (clause 20)
     */
    WIFI_PHY_STANDARD_80211n_2_4GHZ,

    /**
     * HT OFDM PHY for the 5 GHz band (clause 20)
     */
    WIFI_PHY_STANDARD_80211n_5GHZ;
}
