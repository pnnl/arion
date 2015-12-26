package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 12/23/2015.
 */
public enum Ipv6L3Protocol {

    Tx, // Send IPv6 packet to outgoing interface.
    Rx, // Receive IPv6 packet from incoming interface.
    Drop, // Drop IPv6 packet
    SendOutgoing, // A newly-generated packet by this node is about to be queued for transmission
    UnicastForward, // A unicast IPv6 packet was received by this node and is being forwarded to another node
    LocalDeliver, // An IPv6 packet was received by/for this node, and it is being forward up the stack

}
