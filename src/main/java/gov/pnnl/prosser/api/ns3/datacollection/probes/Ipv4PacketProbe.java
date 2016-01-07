package gov.pnnl.prosser.api.ns3.datacollection.probes;

/**
 * Created by happ546 on 12/22/2015.
 */
public class Ipv4PacketProbe extends Probe {

    public Ipv4PacketProbe(String name) {
        super(name);
        // TODO should this source go here or with TraceSources?
        //  Probably here, but enum should live in this Ipv4PacketProbe
        setProbeSource(gov.pnnl.prosser.api.ns3.enums.Ipv4PacketProbe.OutputBytes);
    }
}
