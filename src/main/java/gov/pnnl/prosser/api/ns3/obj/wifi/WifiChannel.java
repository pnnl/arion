/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
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
 *******************************************************************************/

package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.enums.MobilityModel;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.Channel;
import gov.pnnl.prosser.api.ns3.obj.MobilityHelper;

import java.util.ArrayList;
import java.util.List;

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

        final String namePostfix = getName();

        ssid = new Ssid("ssid_" + name);

        wifiChannelHelper = new YansWifiChannelHelper("wifiChanHelper_" + namePostfix);
        // TODO implement these as enums and create getters/setters
        wifiChannelHelper.setPropagationDelay();
        wifiChannelHelper.addPropagationLoss();

        wifiPhyHelper = new YansWifiPhyHelper("wifiPhyHelper_" + namePostfix);
        // TODO will need to move these after wifiChannelHelper methods implemented as enums
        wifiPhyHelper.defaultParams();
        wifiPhyHelper.setPcapDataLinkType("YansWifiPhyHelper::DLT_IEEE802_11_RADIO"); // TODO create enums for this
        wifiPhyHelper.setChannel(wifiChannelHelper);

        wifiMacHelperAdhoc = new NqosWifiMacHelper("nqosWifiMacHelperADHOC_" + namePostfix, WifiMacType.Adhoc);
        wifiMacHelperAp = new NqosWifiMacHelper("nqosWifiMacHelperAP_" + namePostfix, WifiMacType.Ap);
        wifiMacHelperSta = new NqosWifiMacHelper("nqosWifiMacHelperSTA_" + namePostfix, WifiMacType.Sta);

        mobilityHelper = new MobilityHelper("mobilityHelper_" + namePostfix);
        wifiHelper = new WifiHelper("wifiHelper_" + namePostfix);
    }

    public WifiPhyStandard getWifiPhyStandard() {
        return phyStandard;
    }

    public void setWifiPhyStandard(WifiPhyStandard phyStandard) {
        this.phyStandard = phyStandard;
        wifiHelper.setStandard(phyStandard);
    }

    public WifiPhyMode getWifiPhyMode() {
        return phyMode;
    }

    // TODO check for compatibility of phyMode with phyStandard, or leave up to user?
    public void setWifiPhyMode(WifiPhyMode phyMode) {
        this.phyMode = phyMode;
        wifiHelper.setRemoteStationManager(phyMode);
    }

    public Ssid getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid.setSsid(ssid);
        for (NqosWifiMacHelper wifiMacHelper :
                new NqosWifiMacHelper[] {wifiMacHelperAdhoc, wifiMacHelperAp, wifiMacHelperSta}) {
            wifiMacHelper.defaultParams();
            wifiMacHelper.setType(wifiMacHelper.getWifiMacType(), this.ssid, false);
        }
    }

    public MobilityModel getMobilityModel() {
        return mobilityModel;
    }

    public void setMobilityModel(MobilityModel mobilityModel) {
        this.mobilityModel = mobilityModel;

        mobilityHelper.setPositionAllocator(0.0, 0.0, 0.1, 0.1, 10, "\"RowFirst\"");
        mobilityHelper.setMobilityModel(mobilityModel);
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
