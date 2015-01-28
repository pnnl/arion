package gov.pnnl.prosser.api.test;
/**
 * 
 */

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network.NetworkType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author happ546
 *
 */
public class TestNs3Simulator implements Ns3Simulator {

	@Override
	public List<Module> getModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Namespace> getNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractNs3Object> getObjects() {
		final List<AbstractNs3Object> objects = new ArrayList<>();
		
		// Get list of end devices from Peter
		// List<..AbstractProsserObject?..> gldList = peter'sList;
		
		// User inputs basic params (Network type, addr base & mask, # of nodes [or infer from gldList?])
		Ns3Network network1 = new Ns3Network();
		network1.setType(NetworkType.CSMA);
		network1.setAddrBase("10.1."); // First 2 values of IPV4 address to use as base in IP addr distribution
		network1.setAddrMask("255.255.255.0");
		network1.setNumNodes(200); //TODO Infer this from gldList or user specification?
		//network1.setGldObjects(gldList); //TODO
		network1.build(); // Not a real builder pattern; after necessary params, use network type for type-specific method to construct nodes, install devices/applications, etc.
		
		// List of ns-3 Nodes to keep track of specific ones
		List<Node> nodes = network1.getNodes();
		
		
		/*
		final NodeContainer nodes = new NodeContainer();
		nodes.setName("nodes");
		nodes.create(2);
		objects.add(nodes);
		
		final PointToPointHelper pointToPoint = new PointToPointHelper();
		pointToPoint.setName("pointToPoint");
		pointToPoint.setDeviceAttribute("DataRate", "5Mbps"); //StringValue("5Mbps")
		pointToPoint.setChannelAttribute("Delay", "2ms"); //StringValue(...ms) or TimeValue(...) (ns)
		objects.add(pointToPoint);
		
		final NetDeviceContainer devices = new NetDeviceContainer();
		devices.setName("devices");
		//devices.assignment("pointToPoint.install(nodes)"); //TODO this is hacky...
		objects.add(devices);
		
		final InternetStackHelper stack = new InternetStackHelper();
		stack.setName("stack");
		stack.install(nodes);
		objects.add(stack);
		
		final Ipv4AddressHelper address = new Ipv4AddressHelper();
		address.setName("address");
		address.setBase("10.1.1.0", "255.255.255.0");
		objects.add(address);
		
		final Ipv4InterfaceContainer interfaces = new Ipv4InterfaceContainer();
		interfaces.setName("interfaces");
		//interfaces.assignment("address.assign(devices)"); //TODO
		objects.add(interfaces);
		
		final UdpEchoServerHelper echoServer = new UdpEchoServerHelper();
		echoServer.setName("echoServer");
		echoServer.port(9);
		objects.add(echoServer);
		
		final ApplicationContainer serverApps = new ApplicationContainer();
		serverApps.setName("serverApps");
		//serverApps.assignment("echoServer.Install(nodes.Get(1)"); //TODO
		serverApps.start(1.0, "s");
		serverApps.stop(10.0, "s");
		objects.add(serverApps);
		
		final UdpEchoClientHelper echoClient = new UdpEchoClientHelper();
		echoClient.setName("echoClient");
		//echoClient.setAddress("interfaces.getAddress(1)"); //TODO
		echoClient.port(9);
		echoClient.setAttribute("MaxPackets", 1); //UintegerValue (1)
		echoClient.setAttribute("Interval", 1.0); //TimeValue (Seconds (1.0))
		echoClient.setAttribute("PacketSize", 1024); //UintegerValue (1024)
		objects.add(echoClient);
		
		final ApplicationContainer clientApps = new ApplicationContainer();
		clientApps.setName("serverApps");
		clientApps.assignment("echoClient.Install(nodes.Get(0)"); //TODO
		clientApps.start(2.0, "s");
		clientApps.stop(10.0, "s");
		objects.add(clientApps);
		*/
		
		return objects;
	}
	
}
