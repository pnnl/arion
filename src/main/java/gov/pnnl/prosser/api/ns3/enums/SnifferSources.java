package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/22/2015.
 */
public enum SnifferSources implements TraceSource {

    Sniffer, //: Trace source simulating a non-promiscuous packet sniffer attached to the device
    PromiscSniffer, //: Trace source simulating a promiscuous packet sniffer attached to the device

}
