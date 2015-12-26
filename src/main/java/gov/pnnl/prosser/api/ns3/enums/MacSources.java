package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/22/2015.
 */
public enum MacSources implements  TraceSource {

    MacTx, //: Trace source indicating a packet has arrived for transmission by this device
    MacTxDrop, //: Trace source indicating a packet has been dropped by the device before transmission
    MacPromiscRx, //: A packet has been received by this device, has been passed up from the physical layer and is being forwarded up the local protocol stack. This is a promiscuous trace,
    MacRx, //: A packet has been received by this device, has been passed up from the physical layer and is being forwarded up the local protocol stack. This is a non-promiscuous trace,

}
