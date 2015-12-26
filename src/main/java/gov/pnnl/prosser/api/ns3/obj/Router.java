/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.MobilityModel;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaHelper;
import gov.pnnl.prosser.api.ns3.obj.internet.InternetStackHelper;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointChannel;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointHelper;
import gov.pnnl.prosser.api.ns3.obj.wifi.*;


/**
 * @author happ546
 *
 */
public class Router extends AbstractNs3Object {
	
	private Node node;
	private List<Channel> channels;
	private boolean ipStackInstalled, ready, pcap, ascii;
    private WifiMacType macType;

    /**
	 * Create a new Router with the given Name
	 * @param name
	 */
	public Router(String name) 
	{	
		setNameString(name);
		node = new Node(name);
		channels = new ArrayList<>();
		ipStackInstalled = false;
		ready = false;
		pcap = false;
		ascii = false;
        macType = WifiMacType.Adhoc;
	}

	/**
	 * @return node
	 * 			the node attached to this Router
	 */
	public Node getNode() {
		return this.node;
	}

	public void AddStaticRoute(String dest, String hop, int interFace) {
		this.AddStaticRoute(dest, hop, interFace, 0);
	}
	
	public void AddStaticRoute(String dest, String hop, int interFace, int metric) {
		Node myNode = this.getNode();
		
		String Ipv4Object 
			= "Ipv4_" 
			+ java.util.UUID.randomUUID().toString().replace("-", "");
		
		String Ipv4StaticRoutingHelperObject 
			= "Ipv4StaticRoutingHelper_" 
			+ java.util.UUID.randomUUID().toString().replace("-", "");
		
		String Ipv4StaticRoutingObject 
			= "Ipv4StaticRouting_" 
			+ java.util.UUID.randomUUID().toString().replace("-", "");
		
	
		appendPrintInfo("Ptr<Ipv4> " + Ipv4Object + " = " +	myNode.getName() + "->GetObject<Ipv4> ();\n");
		appendPrintInfo("Ipv4StaticRoutingHelper " + Ipv4StaticRoutingHelperObject + ";\n");
		appendPrintInfo("Ptr<Ipv4StaticRouting> " + Ipv4StaticRoutingObject 
				+ " = " +  Ipv4StaticRoutingHelperObject + ".GetStaticRouting(" + Ipv4Object + ");\n");
		appendPrintInfo(Ipv4StaticRoutingObject + "->AddHostRouteTo( "
				+ "Ipv4Address(\"" + dest + "\"), "
				+ "Ipv4Address(\"" + hop + "\"), "
				+ interFace + ", " + metric + ");\n" );
	}
	
	
	/**
	 *  returns a string with the c++ variable name that represents an Ipv4Address Object 
	 */
	public String getIp4Addr() 
	{
		Node myNode = this.getNode();
		NetDevice myNetDevice = myNode.getDevice(0);
		String ifIndex = myNetDevice.getInterface();
		
		Ipv4 myIpv4 = myNode.getObjectIpv4();
		
		String varName
		= "Ipv4Address_" + java.util.UUID.randomUUID().toString().replace("-", "");
		
		appendPrintInfo("Ipv4Address " + varName + " = " 
					+ myIpv4.getPointerName() + "->GetAddress(" + ifIndex  + ", 0).GetLocal().Get();\n");
		
		return varName;
	}
	
	/**
	 * Stores the given Channel on this Node and
	 * installs the protocols specified by the Channel type
	 * @param channel
	 */
	public void setChannel(Channel channel) {
		
		channels.add(channel);
		
		if (channel.getType().equals(NetworkType.CSMA)) {

			// TODO move network helpers to 1 per channel, then just get() here & use
			//  need to worry about order (helpers can't be created before ALL nodes/routers for them)
			CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + getName() + "_" + channel.getName());
			csmaHelper.install(getNode(), (CsmaChannel) channel, channel.getDevices());

			// This router is ready for IP stack install
			ready = true;

			if (pcap) {
				csmaHelper.enablePcapAll(channel.getName());
			}

			if (ascii) {
				csmaHelper.enableAsciiAll(channel.getName());
			}

		} else if (channel.getType().equals(NetworkType.P2P)) {

			PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + getName() + "_" + channel.getName());

			if (((PointToPointChannel) channel).getRouterA() == null) {
			
				((PointToPointChannel) channel).setRouterA(this);

				// This router is not ready for IP stack install; needs another p2p router attached to channel
				ready = false;

				// Checks if routerA attached to P2PChannel is same as this router
			} else if (!((PointToPointChannel) channel).getRouterA().equals(this)) {
				
				p2pHelper.install(((PointToPointChannel) channel).getRouterA().getNode(),
						getNode(), (PointToPointChannel) channel, channel.getDevices());

				// This router is ready for IP stack install
				ready = true;
				
			} else {
				System.out.println("This PointToPointChannel " + channel.getName() + " is already attached to Router " +
						getName() + ".  Router not added to channel.");
			}

			if (this.pcap) {
				p2pHelper.enablePcapAll(channel.getName());
			}
			if (this.ascii) {
				p2pHelper.enableAsciiAll(channel.getName());
			}

			// TODO allow for other types of WifiMacHelper
		} else if (channel.getType().equals(NetworkType.WIFI)) {
			YansWifiChannel chan = (YansWifiChannel) channel;

            NqosWifiMacHelper wifiMacHelper;
            if (getMacType().equals(WifiMacType.Ap)) {
                wifiMacHelper = chan.getWifiMacHelperAp();
            } else if (getMacType().equals(WifiMacType.Sta)) {
                wifiMacHelper = chan.getWifiMacHelperSta();
            } else {
                wifiMacHelper = chan.getWifiMacHelperAdhoc();
            }

            MobilityHelper mobilityHelper = chan.getMobilityHelper();
			mobilityHelper.install(getNode());

			YansWifiPhyHelper wifiPhyHelper = chan.getWifiPhyHelper();

			WifiHelper wifiHelper = chan.getWifiHelper();
			wifiHelper.install(wifiPhyHelper, wifiMacHelper, getNode(), chan.getDevices());

            if (pcap) {
                wifiPhyHelper.enablePcapAll(channel.getName());
            }
            if (ascii) {
                wifiPhyHelper.enableAsciiAll(channel.getName());
            }
		}
		
		if (!ipStackInstalled && ready) {

			// Create NodeContainer for InternetStackHelper.Install
			NodeContainer nc = new NodeContainer("nodeContainerStackHelper_" + getName());
			nc.addRouter(this);

			if (channel.getType().equals(NetworkType.P2P)) {
				nc.addRouter(((PointToPointChannel) channel).getRouterA());
			}

			// Install IP stack on Router
			InternetStackHelper stackHelper = new InternetStackHelper("internetStackHelper_" + getName());
			stackHelper.install(nc);

			ipStackInstalled = true;
		}
	}

	/**
	 * For Wi-Fi Routers
	 * @param channel
	 * 		the Channel to set (attach) this Router to
	 * @param macType
	 * 		the WifiMacType to set this Router to
	 */
    public void setChannel(YansWifiChannel channel, WifiMacType macType) {
        setMacType(macType);
        setChannel(channel);
    }

	/**
	 * @param b
	 * 		a boolean flag to set PCAP dump on/off
	 */
	public void setPcap(boolean b) {
		pcap = b;
	}

	/**
	 * @param b
	 * 		a boolean flag to set ASCII tracing on/off
	 */
	public void setAscii(boolean b) { ascii = b; }

	/**
	 * @return the WifiMacType of this Router (is meaningless if not a Wi-Fi Router)
	 */
    public WifiMacType getMacType() {
        return macType;
    }

	/**
	 * @param macType
	 * 		the WifiMacType to set this Router to (only useful for Wi-Fi Routers)
	 */
    public void setMacType(WifiMacType macType) {
        this.macType = macType;
    }
}
