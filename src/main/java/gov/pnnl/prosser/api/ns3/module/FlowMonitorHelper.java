package gov.pnnl.prosser.api.ns3.module;

/**
 * This module includes the FlowMonitorHelper, allowing tracking and extraction
 * of traffic/packet flow information.
 *
 * Created by happ546 on 1/6/2016.
 */
public class FlowMonitorHelper extends Module {

    /**
     * Sets name to flow-monitor-helper; sets ns3 flag to true
     */
    public FlowMonitorHelper() {
        this.name = "flow-monitor-module";
        this.ns3 = true;
    }
}
