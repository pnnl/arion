/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * This class provides a structure to efficiently hold Nodes in 
 * and make it easier to configure NetDevices on multiple Nodes 
 * simultaneously.
 * 
 * @author happ546
 *
 */
public class NodeContainer extends AbstractNs3Object {

	/**
	 * An array containing the Routers in this NodeContainer
	 */
	private ArrayList<Router> routers;

	/**
	 * An array containing the Nodes in this NodeContainer
	 */
	public ArrayList<Node> nodes;
	
	/**
	 * Creates nameless NodeContainer; used in NetDeviceContainer
	 */
	public NodeContainer() {
		this.routers = new ArrayList<>();
		this.nodes = new ArrayList<>();
	}
	
	/**
	 * Initializes an empty NodeContainer
	 * @param name the string name
	 */
	public NodeContainer(String name) {
		this();
		this.setName(name);
	}

	/**
	 * Creates specified number of routers in this NodeContainer
	 * @param numNodes the number of routers to create
	 */
	public void create(int numNodes) {
		for (int i = 0; i < numNodes; i++) {
			String name = this.getName() + "_" + (i + 1);
			this.addRouterNoPrint(new Router(name.replace('.', '_')));
		}
		appendPrintInfo(this.getName() + ".Create (" + numNodes + ");\n");
	}

	/**
	 * @param router the Node to add to this NodeContainer
	 */
	public void addRouter(Router router) {
		this.addRouterNoPrint(router);
		appendPrintInfo(this.getName() + ".Add (" + router.getPointerName() + ");\n");
	}
	
	/**
	 * 
	 * @param container the NodeContainer containing the Node to add to this NodeContainer
	 * @param index the index of the Node to be added
	 */
	public void addRouter(NodeContainer container, int index) {
		this.addRouter(container.getRouterNoPrint(index));
		appendPrintInfo(this.getName() + ".Add (" + container.getName() + ".Get (" + index + "));\n");
	}

	public void addRouterNoPrint(Router router) {
		this.routers.add(router);
	}

	public void addNode(Node node) {
		this.nodes.add(node);
		appendPrintInfo(this.getName() + ".Add (" + node.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainer(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.routers.add(sourceNodes.getRouterNoPrint(i));
		}
		appendPrintInfo(this.getName() + ".Add (" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NodeContainer
	 */
	public void addNodeContainerNoPrint(NodeContainer sourceNodes) {
		for (int i = 0; i < sourceNodes.getNumNodes(); i++) {
			this.routers.add(sourceNodes.getRouterNoPrint(i));
		}
	}

	public void addNetDeviceContainerNoPrint(NetDeviceContainer devs) {
		for (int i = 0; i < devs.getNumDevices(); i++) {
			this.nodes.add(devs.getDevice(i));
		}
	}

	/**
	 * @return the number of Nodes in this NodeContainer
	 */
	public int getNumNodes() {
		return this.routers.size();
	}

	/**
	 * Returns the Router at the given index without outputting any text to the C++ output file
	 * 
	 * @param index the integer index of the Node to retrieve from the NodeContainer
	 * @return the Router at the given index or null if there is no node at that index
	 */
	public Router getRouterNoPrint(int index) {
		if (index >= this.routers.size() || this.routers.get(index) == null) {
			return null;
		}
		return routers.get(index);
	}

	public Node getNodeNoPrint(int index) {
		if (index >= this.nodes.size() || this.nodes.get(index) == null) {
			return null;
		}
		return nodes.get(index);
	}

}
