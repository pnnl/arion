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
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.pwr.obj.MarketNetworkInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author happ546
 *
 */
public class TestExperimentNs3Simulator implements Ns3Simulator {

    private Ns3Network network;

    private MarketNetworkInterface marketNI;

    private List<ControllerNetworkInterface> controllerNIs;

    private String gldNodePrefix;

    @Override
    public void setup() {
        // Get list of end devices from Peter
        // List<..AbstractProsserObject?..> gldList = peter'sList;

        // User inputs basic params (Network type, addr base & mask, # of nodes)
        network = new Ns3Network();
        network.setType(NetworkType.LTE); // TODO Set up backbone & subnetwork functionality
        network.setAddrBase("10.1."); // First 2 values of IPV4 address to use as base in IP addr distribution
        network.setNumNodes(200); // TODO Infer this from gldList or user specification
        // network.setGldObjects(gldList); //TODO
        network.setMarketNI(this.marketNI);
        network.setControllerNIs(this.controllerNIs);

        // TODO get start and stop times from user
        network.setStartTime(0.0);
        network.setStopTime(10.0);
    }

    @Override
    public List<Module> getModules() {
        // enum? of all commonly used modules that network can select from based on params
        return network.getModules();
    }

    @Override
    public List<Namespace> getNamespaces() {
        final List<Namespace> namespaces = new ArrayList<Namespace>();
        namespaces.add(new Namespace("ns3"));
        // namespaces.add(new Namespace("std")); //TODO need std?

        return namespaces;
    }

    @Override
    public List<AbstractNs3Object> getObjects() {

    	// Not a real builder pattern; after necessary params, use network type for type-specific method to construct nodes, install devices/applications, etc.
        final List<AbstractNs3Object> objects = network.build();

        // List of ns-3 Nodes to keep track of specific Nodes
        final List<Node> nodes = network.getNodes();

        return objects;
    }

    /**
     * @return the marketNI
     */
    public MarketNetworkInterface getMarketNI() {
        return marketNI;
    }

    /**
     * @param marketNI
     *            the marketNI to set
     */
    public void setMarketNI(final MarketNetworkInterface marketNI) {
        this.marketNI = marketNI;
    }

    /**
     * @return the controllerNIs
     */
    public List<ControllerNetworkInterface> getControllerNIs() {
        return controllerNIs;
    }

    /**
     * @param controllerNIs
     *            the controllerNIs to set
     */
    public void setControllerNIs(final List<ControllerNetworkInterface> controllerNIs) {
        this.controllerNIs = controllerNIs;
    }

    /**
     * @return the gldNodePrefix
     */
    public String getGldNodePrefix() {
        return gldNodePrefix;
    }

    /**
     * @param gldNodePrefix
     *            the gldNodePrefix to set
     */
    public void setGldNodePrefix(final String gldNodePrefix) {
        this.gldNodePrefix = gldNodePrefix;
    }

}
