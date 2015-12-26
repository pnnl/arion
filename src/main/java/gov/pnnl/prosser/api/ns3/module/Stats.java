package gov.pnnl.prosser.api.ns3.module;

/**
 * This module provides access to the stats (statistics)/ Data Collection Framework
 * classes in ns-3.
 *
 * Created by happ546 on 12/23/2015.
 */
public class Stats extends Module {

    /**
     * Sets name to stats-module; sets ns3 flag to true
     */
    public Stats() {
        this.name = "stats-module";
        this.ns3 = true;
    }
}
