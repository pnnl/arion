/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import java.util.ArrayList;

/**
 * The ns-3 Node is the basic building block of the network sim
 * Devices can be installed on them to provide various comm. methods (and applications installed on the devices
 * to provide various communication abilities (
 * 
 * @author happ546
 *
 */
public class Node {
	private ArrayList<Device> devices;
	private ArrayList<Application> apps;
	
	public Node() {
		this.devices = new ArrayList<Device>();
		this.apps = new ArrayList<Application>();
	}
	
	public void addDevice(Device device) {
		devices.add(device);
	}
	
	public void addApplication(Application app) {
		apps.add(app);
	}
	
}
