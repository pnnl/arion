/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
*    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
*    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
*    and may permit others to do so, subject to the following conditions:
*    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
*       the following disclaimers.
*    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
*       the following disclaimer in the documentation and/or other materials provided with the distribution.
*    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
*       form whatsoever without the express written consent of Battelle.
* 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
*    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
*    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
*    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*                                PACIFIC NORTHWEST NATIONAL LABORATORY
*                                            operated by
*                                              BATTELLE
*                                              for the
*                                  UNITED STATES DEPARTMENT OF ENERGY
*                                   under Contract DE-AC05-76RL01830
*/

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
