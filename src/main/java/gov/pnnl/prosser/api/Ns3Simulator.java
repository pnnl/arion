/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator {
	
	private Ns3Network network;
	private List<Module> modules;
	private List<Namespace> namespaces;
	private List<AbstractNs3Object> objects;

	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters TODO supply user params
	 */
	public void setup() {
		
		// User inputs basic params (Network type, addr base & mask, # of nodes [or infer from gldList?])
		network = new Ns3Network();
		network.setAddrBase("10.1."); // First 2 values of IPV4 address to use as base in IP addr distribution
		network.setAddrMask("255.255.255.0");
		
		//TODO stop time from user
		network.setStopTime(10.0);
	}
	
    /**
     * Gets the Modules used in this simulation
     */
	public List<Module> getModules() {
		// enum? of all commonly used modules that network can select from based on params
		return network.getModules();
	}
	
    /**
     * Gets the Namespaces used in this simulation
     */
	public List<Namespace> getNamespaces() {
		List<Namespace> namespaces = new ArrayList<Namespace>();
		namespaces.add(new Namespace("ns3"));
		//namespaces.add(new Namespace("std")); //TODO need std?
		
		return namespaces;
	}
	
    /**
     * Gets the ns-3 objects used in this simulation
     */
	public List<AbstractNs3Object> getObjects() {
		
		final List<AbstractNs3Object> objects = network.build(); // Not a real builder pattern; after necessary params, use network type for type-specific method to construct nodes, install devices/applications, etc.
		
		// List of ns-3 Nodes to keep track of specific Nodes
		List<Node> nodes = network.getNodes();
		
		return objects;
	}

}
