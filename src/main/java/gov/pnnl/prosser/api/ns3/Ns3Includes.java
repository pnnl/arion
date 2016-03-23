/**
 * 
 */
package gov.pnnl.prosser.api.ns3;

/**
 * @author nord229
 *
 */
public class Ns3Includes {

    /**
     * Time, Simulator
     */
    public static final String CORE = "ns3/core-module.h";

    /**
     * NodeContainer, NetDeviceContainer, AsciiTraceHelper, ApplicationContainer
     */
    public static final String NET = "ns3/network-module.h";

    /**
     * InternetStackHelper, Ipv4AddressHelper
     */
    public static final String INET = "ns3/internet-module.h";

    /**
     * PointToPointHelper
     */
    public static final String P2P = "ns3/point-to-point-module.h";

    /**
     * UdpEchoServerHelper, UdpEchoClientHelper
     */
    public static final String APPS = "ns3/applications-module.h";
}
