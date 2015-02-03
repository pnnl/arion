/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;

import java.util.ArrayList;

/**
 * The ns-3 Node is the basic building block of the network simulation
 * Devices can be installed on them to provide various communication methods and 
 * applications can be installed on the devices to provide various communication abilities 
 * (for example: point-to-point, WiFi, Internet protocols, etc.)
 * 
 * @author happ546
 *
 */
public class Node extends AbstractNs3Object {
	
	/**
	 * A list of the applications installed on this Node
	 */
	private ArrayList<Application> applications;
	/**
	 * A list of the network devices installed on this Node
	 */
	private ArrayList<NetDevice> devices;
	/**
	 * The GridLab-D object installed on this Node (if present)
	 */
	private AbstractProsserObject gldObject; //TODO remove if not used
	/**
	 * The ControllerNetworkInterface installed on this Node (if present)
	 * The CNI indicates this Node is connected to a GLD House object
	 */
	private ControllerNetworkInterface controllerNetworkInterface;
	
	/**
	 * Creates a new, empty Node
	 */
	public Node() {
		this.devices = new ArrayList<NetDevice>();
		this.applications = new ArrayList<Application>();
		this.gldObject = null;
	}
	
	/**
	 * 
	 * @param app the Application to install on this Node
	 */
	public void addApplication(Application app) {
		applications.add(app);
	}
	
	/**
	 * @return the list of installed applications on this Node
	 */
	public ArrayList<Application> getApplications() {
		return applications;
	}

	/**
	 * 
	 * @param device the NetDevice to install on this Node
	 */
	public void addDevice(NetDevice device) {
		devices.add(device);
	}
	
	/**
	 * @return the list of devices for this Node
	 */
	public ArrayList<NetDevice> getDevices() {
		return devices;
	}
	
	/**
	 * 
	 * @param gldObject the GridLab-D object to install on this Node
	 */
	public void addGld(AbstractProsserObject gldObject) {
		this.gldObject = gldObject;
	}

	/**
	 * @return the GridLab-D object for this Node
	 */
	public AbstractProsserObject getGldObject() {
		return gldObject;
	}

	/**
	 * @return the controllerNetworkInterface
	 */
	public ControllerNetworkInterface getControllerNetworkInterface() {
		return controllerNetworkInterface;
	}

	/**
	 * @param controllerNetworkInterface the controllerNetworkInterface to set
	 */
	public void setControllerNetworkInterface(ControllerNetworkInterface controllerNetworkInterface) {
		this.controllerNetworkInterface = controllerNetworkInterface;
	}
	
}
