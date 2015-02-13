/**
 * 
 */
package gov.pnnl.prosser.api.ns3.enums;

/**
 * The types of Network supported by Ns3Network
 * 
 * @author happ546
 *
 */
public enum NetworkType {
	
	/**
	 * Carrier Sense Multiple Access
	 * Use this to emulate Ethernet
	 */
	CSMA, 
	
	/**
	 * Long-Term Evolution
	 */
	LTE, 
	
	/**
	 * Point-to-Point
	 */
	P2P, 
	
	/**
	 * WiFi
	 */
	WIFI;
}
