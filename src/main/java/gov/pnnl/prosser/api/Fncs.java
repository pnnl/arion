/**
 * 
 */
package gov.pnnl.prosser.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author nord229
 *
 */
public class Fncs {

    @SerializedName("interface")
    private FncsIface iface = FncsIface.ZmqNetworkInterface;

    public enum FncsIface {
        ZmqNetworkInterface;
    }

    private String broker;

    @SerializedName("simulator_type")
    private FncsSimType simulatorType;

    public enum FncsSimType {
        @SerializedName("power_grid")
        PowerGrid,
        @SerializedName("communication_network")
        CommunicationNetwork;
    }

    @SerializedName("synchronization_algorithm")
    private FncsSyncAlgo syncAlgo;

    public enum FncsSyncAlgo {
        GracePeriodSyncAlgo,
        CommunicatorSimulatorSyncalgo;
    }

    @SerializedName("simulator_time_metric")
    private FncsTimeMetric simulatorTimeMetric;

    public enum FncsTimeMetric {
        seconds,
        nanoseconds;
    }

    @SerializedName("packet_lost_period")
    private long packetLostPeriod;

    @SerializedName("sync_params")
    private SyncParams syncParams;

    public static class SyncParams {
        @SerializedName("number_of_power_grid_sims")
        private int numPowerGridSims = 1;

        /**
         * @return the numPowerGridSims
         */
        public int getNumPowerGridSims() {
            return numPowerGridSims;
        }

        /**
         * @param numPowerGridSims
         *            the numPowerGridSims to set
         */
        public void setNumPowerGridSims(int numPowerGridSims) {
            this.numPowerGridSims = numPowerGridSims;
        }

    }

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
