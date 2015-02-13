/**
 * 
 */
package gov.pnnl.prosser.api.ns3.enums;

/**
 * Quality of Service Class Indicator types, used by LTE network EPS Bearers, a 
 * radio link between User Equipment (UE) devices and Evolved Node B (eNB) base station devices
 * 
 * @author happ546
 *
 */
public enum Qci {
	
	//TODO Finish documentation

	/**
	 * Guaranteed Bit Rate Conversational Voice
	 * 
	 */
	GBR_CONV_VOICE,
	/**
	 * 
	 */
	GBR_CONV_VIDEO,
	/**
	 * 
	 */
	GBR_GAMING,
	/**
	 * 
	 */
	GBR_NON_CONV_VIDEO,
	/**
	 * 
	 */
	NGBR_IMS,
	/**
	 * 
	 */
	NGBR_VIDEO_TCP_OPERATOR,
	/**
	 * 
	 */
	NGBR_VOICE_VIDEO_GAMING,
	/**
	 * 
	 */
	NGBR_VIDEO_TCP_PREMIUM,
	/**
	 * 
	 */
	NGBR_VIDEO_TCP_DEFAULT;
	
	private String name;
	
	/**
	 * 
	 * @return the name of this QoS Class Indicator
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param name sets the name of this QoS Class Indicator
	 */
	public void setName(String name) {
		this.name = name;
	}
}
