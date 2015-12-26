package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/22/2015.
 */
public enum PhySources implements  TraceSource {



    PhyTxBegin, //: Trace source indicating a packet has begun transmitting over the channel
    PhyTxEnd, //: Trace source indicating a packet has been completely transmitted over the channel
    PhyTxDrop, //: Trace source indicating a packet has been dropped by the device during transmission
    PhyRxEnd, //: Trace source indicating a packet has been completely received by the device
    PhyRxDrop, //: Trace source indicating a packet has been dropped by the device during reception

}
