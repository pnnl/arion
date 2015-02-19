/**
 *
 */

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.gld.obj.MarketNetworkInterface;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;

import java.util.ArrayList;
import java.util.List;

/**
 * @author happ546
 *
 */
public class TestExperimentNs3Simulator implements Ns3Simulator {

    private Ns3Network network;

    private List<MarketNetworkInterface> marketNIs;

    private List<ControllerNetworkInterface> controllerNIs;

    private String gldNodePrefix;

    private List<AuctionObject> auctions;

    private List<Controller> controllers;

    @Override
    public void setup() {

        // User inputs basic parameters
        network = new Ns3Network();
        network.setAuctions(auctions);
        network.setControllers(controllers);
        network.setGldNodePrefix(this.getGldNodePrefix());
        
        // TODO might be better to abstract these into Ns3Network class
        // Number of Nodes representing GLD Houses (each with attached ControllerNetworkInterfaces)
        final int numAuctionNodes = this.getControllers().size();
        // Number of Nodes for network backbone (WiFi APs, CSMA/Ethernet "routers", LTE towers)
        int numBackboneNodes = numAuctionNodes / 20;
        if (numAuctionNodes % 20 > 0) {
            numBackboneNodes++;
        }        
        network.setNumAuctionNodes(numAuctionNodes);
        network.setNumBackboneNodes(numBackboneNodes);
        
        // WiFi and CSMA work; also need to change network.create...() in getObjects()
        network.setAuctionType(NetworkType.WIFI);
        network.setBackboneType(NetworkType.CSMA);
        
        // TODO LTE doesn't work yet
//        network.setAuctionType(NetworkType.LTE);
//        network.setBackboneType(NetworkType.P2P);
        
        network.setAddrBase("10.0.");
        network.setAddrMask("255.255.255.0");
        
    }

    @Override
    public List<Module> getModules() {
        return network.getModules();
    }

    @Override
    public List<Namespace> getNamespaces() {
        final List<Namespace> namespaces = new ArrayList<Namespace>();
        namespaces.add(new Namespace("ns3"));
        // namespaces.add(new Namespace("std")); // TODO only use std for data structures/file IO

        return namespaces;
    }

    @Override
    public List<AbstractNs3Object> getObjects() {

        final List<AbstractNs3Object> objects = /*network.createLte();/**//*network.createCsma("10Mbps", "20ms");/**/network.createWifi("10.0.0.0", "4ms");/**/

        // List of ns-3 Nodes to keep track of specific Nodes
        // final List<Node> nodes = network.getNodes();

        return objects;
    }

    /**
     * @return the marketNI
     */
    public List<MarketNetworkInterface> getMarketNIs() {
        return marketNIs;
    }

    /**
     * @return the auctions
     */
    public List<AuctionObject> getAuctions() {
        return auctions;
    }

    /**
     * @param auctions
     *            the Auctions to set
     */
    public void setAuctions(final List<AuctionObject> auctions) {
        this.auctions = auctions;
    }

    /**
     * @return the controllers
     */
    public List<Controller> getControllers() {
        return controllers;
    }

    /**
     * @param controllers
     *            the Controllers to set
     */
    public void setControllers(final List<Controller> controllers) {
        this.controllers = controllers;
    }

    /**
     * @param marketNI
     *            the marketNI to set
     */
    public void setMarketNIs(final List<MarketNetworkInterface> marketNIs) {
        this.marketNIs = marketNIs;
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
