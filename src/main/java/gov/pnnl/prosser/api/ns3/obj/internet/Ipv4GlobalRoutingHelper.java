package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * Class Ipv4GlobalRoutingHelper is used to set up global routing tables on
 * all Routers on a given network.
 *
 * @author happ546
 */
public class Ipv4GlobalRoutingHelper extends AbstractNs3Object {

    /**
     * @param name the string name for this Ipv4GlobalRoutingHelper
     */
    public Ipv4GlobalRoutingHelper(String name) {
        setName(name);
    }

    /**
     * Populates the routing tables of all Routers on this network with
     * global routing information.
     */
    public void populate() {
        appendPrintInfo(this.getName() + ".PopulateRoutingTables ();\n");
    }
}
