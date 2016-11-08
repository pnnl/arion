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
package gov.pnnl.prosser.api.fncs;

import com.google.gson.annotations.SerializedName;

/**
 * @author nord229
 *
 */
public class Fncs {

    @SerializedName("interface")
    private FncsIface iface = FncsIface.ZmqNetworkInterface;

    private String broker;

    @SerializedName("simulator_type")
    private FncsSimType simulatorType;

    @SerializedName("synchronization_algorithm")
    private FncsSyncAlgo syncAlgo;

    @SerializedName("simulator_time_metric")
    private FncsTimeMetric simulatorTimeMetric;

    @SerializedName("packet_lost_period")
    private long packetLostPeriod;

    @SerializedName("sync_params")
    private SyncParams syncParams;

    /**
     * @return the iface
     */
    public FncsIface getIface() {
        return iface;
    }

    /**
     * @param iface
     *            the iface to set
     */
    public void setIface(FncsIface iface) {
        this.iface = iface;
    }

    /**
     * @return the broker
     */
    public String getBroker() {
        return broker;
    }

    /**
     * @param broker
     *            the broker to set
     */
    public void setBroker(String broker) {
        this.broker = broker;
    }

    /**
     * @return the simulatorType
     */
    public FncsSimType getSimulatorType() {
        return simulatorType;
    }

    /**
     * @param simulatorType
     *            the simulatorType to set
     */
    public void setSimulatorType(FncsSimType simulatorType) {
        this.simulatorType = simulatorType;
    }

    /**
     * @return the syncAlgo
     */
    public FncsSyncAlgo getSyncAlgo() {
        return syncAlgo;
    }

    /**
     * @param syncAlgo
     *            the syncAlgo to set
     */
    public void setSyncAlgo(FncsSyncAlgo syncAlgo) {
        this.syncAlgo = syncAlgo;
    }

    /**
     * @return the simulatorTimeMetric
     */
    public FncsTimeMetric getSimulatorTimeMetric() {
        return simulatorTimeMetric;
    }

    /**
     * @param simulatorTimeMetric
     *            the simulatorTimeMetric to set
     */
    public void setSimulatorTimeMetric(FncsTimeMetric simulatorTimeMetric) {
        this.simulatorTimeMetric = simulatorTimeMetric;
    }

    /**
     * @return the packetLostPeriod
     */
    public long getPacketLostPeriod() {
        return packetLostPeriod;
    }

    /**
     * @param packetLostPeriod
     *            the packetLostPeriod to set
     */
    public void setPacketLostPeriod(long packetLostPeriod) {
        this.packetLostPeriod = packetLostPeriod;
    }

    /**
     * @return the syncParams
     */
    public SyncParams getSyncParams() {
        return syncParams;
    }

    /**
     * @param syncParams
     *            the syncParams to set
     */
    public void setSyncParams(SyncParams syncParams) {
        this.syncParams = syncParams;
    }

}
