/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.MobilityModel;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaHelper;
import gov.pnnl.prosser.api.ns3.obj.internet.InternetStackHelper;
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
	public Router(String name) {
		
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

	/**
	 * Stores the given Channel on this Node and
	 * installs the protocols specified by the Channel type
	 * @param channel
	 */
	public void setChannel(Channel channel) {
		
		channels.add(channel);
		
		if (channel.getType().equals(NetworkType.CSMA)) {
			
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
						this.getNode(), (PointToPointChannel) channel, channel.getDevices());

				// This router is ready for IP stack install
				ready = true;
				
			} else {
				System.out.println("This PointToPointChannel " + channel.getName() + " is already attached to Router " +
						getName() + ".  Router not added to channel.");
			}

			if (pcap) {
				p2pHelper.enablePcapAll(channel.getName());
			}
			if (ascii) {
				p2pHelper.enableAsciiAll(channel.getName());
			}

			// TODO get all parameters (enums/classes) from channel
		} else if (channel.getType().equals(NetworkType.WIFI)) {
			YansWifiChannel chan = (YansWifiChannel) channel;

            WifiHelper wifiHelper = chan.getWifiHelper();
			wifiHelper.setStandard(chan.getWifiPhyStandard());

			// TODO implement parameters for these with enums or classes
            YansWifiChannelHelper wifiChannelHelper = chan.getWifiChannelHelper();
			wifiChannelHelper.setPropagationDelay();
			wifiChannelHelper.addPropagationLoss();

            YansWifiPhyHelper wifiPhyHelper = chan.getWifiPhyHelper();
            wifiPhyHelper.defaultParams();
			wifiPhyHelper.setPcapDataLinkType("YansWifiPhyHelper::DLT_IEEE802_11_RADIO"); // TODO create enums for this
			wifiPhyHelper.setChannel(wifiChannelHelper);

            NqosWifiMacHelper wifiMacHelper;
            if (getMacType().equals(WifiMacType.Ap)) {
                wifiMacHelper = chan.getWifiMacHelperAp();
            } else if (getMacType().equals(WifiMacType.Sta)) {
                wifiMacHelper = chan.getWifiMacHelperSta();
            } else {
                wifiMacHelper = chan.getWifiMacHelperAdhoc();
            }
            wifiMacHelper.defaultParams();
			wifiMacHelper.setType(getMacType(), chan.getSsid(), false);

            MobilityHelper mobilityHelper = chan.getMobilityHelper();
			mobilityHelper.setPositionAllocator(0.0, 0.0, 0.1, 0.1, 10, "rowFirst");
			mobilityHelper.setMobilityModel(chan.getMobilityModel());
			mobilityHelper.install(getNode());

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
			nc.addNode(getNode());

			if (channel.getType().equals(NetworkType.P2P)) {
				nc.addNode(((PointToPointChannel) channel).getRouterA().getNode());
			}

			// Install IP stack on Router
			InternetStackHelper stackHelper = new InternetStackHelper("internetStackHelper_" + getName());
			stackHelper.install(nc);

			ipStackInstalled = true;

		}

		// FIXME change to addDevices(devices, channel)?
		//channel.addDevices(devices);
	}


    public void setChannel(YansWifiChannel houseChannel, WifiMacType ap) {
        setMacType(ap);
        setChannel(houseChannel);
    }

	/**
	 *
	 * @param b a boolean flag to set PCAP dump on/off
	 */
	public void setPcap(boolean b) {
		this.pcap = b;
	}

	/**
	 *
	 * @param b a boolean flag to set ASCII tracing on/off
	 */
	public void setAscii(boolean b) { this.ascii = b; }

    public WifiMacType getMacType() {
        return macType;
    }

    public void setMacType(WifiMacType macType) {
        this.macType = macType;
    }
}
