/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.lte;

/**
 * Quality of Service Class Indicator types, used by LTE network EPS Bearers, a 
 * radio link between User Equipment (UE) devices and Evolved Node B (eNB) base station devices
 * 
 * For more information on the QoS Class Indicator standard values, see 
 * 3GPP 23.203 Section 6.1.7.2, located at http://www.qtc.jp/3GPP/Specs/23203-b60.pdf
 * 
 * @author happ546
 *
 */
public enum Qci {
	
	/**
	 * Guaranteed Bit Rate
	 * Conversational Voice
	 * Priority 2
	 * Packet Delay Budget 100ms
	 * Packet Error Loss Rate 0.01
	 * 
	 */
	GBR_CONV_VOICE,
	
	/**
	 * Guaranteed Bit Rate
	 * Conversational Video (Live Streaming)
	 * Priority 4
	 * Packet Delay Budget 150ms
	 * Packet Error Loss Rate 0.001
	 */
	GBR_CONV_VIDEO,
	
	/**
	 * Guaranteed Bit Rate
	 * Real Time Gaming
	 * Priority 3
	 * Packet Delay Budget 50ms
	 * Packet Error Loss Rate 0.001
	 */
	GBR_GAMING,
	
	/**
	 * Guaranteed Bit Rate
	 * Non-Conversational Video (Buffered Streaming)
	 * Priority 5
	 * Packet Delay Budget 300ms
	 * Packet Error Loss Rate 0.000001
	 */
	GBR_NON_CONV_VIDEO,
	
	/**
	 * Non-Guaranteed Bit Rate
	 * IMS Signaling
	 * Priority 1
	 * Packet Delay Budget 100ms
	 * Packet Error Loss Rate 0.000001
	 */
	NGBR_IMS,
	
	/**
	 * Non-Guaranteed Bit Rate
	 * Video (Buffered Streaming), TCP-based (e.g., www, e-mail, 
	 * 		chat, ftp, p2p file sharing, progressive video, etc.) 
	 * Priority 6
	 * Packet Delay Budget 300ms
	 * Packet Error Loss Rate 0.000001
	 */
	NGBR_VIDEO_TCP_OPERATOR,
	
	/**
	 * Non-Guaranteed Bit Rate
	 * Voice, Video (Live Streaming), Interactive Gaming 
	 * Priority 7
	 * Packet Delay Budget 100ms
	 * Packet Error Loss Rate 0.000001
	 */
	NGBR_VOICE_VIDEO_GAMING,
	
	/**
	 * Non-Guaranteed Bit Rate
	 * Video (Buffered Streaming), TCP-based (e.g., www, e-mail, 
	 * 		chat, ftp, p2p file sharing, progressive video, etc.) 
	 * Priority 8
	 * Packet Delay Budget 300ms
	 * Packet Error Loss Rate 0.000001
	 */
	NGBR_VIDEO_TCP_PREMIUM,
	
	/**
	 * Non-Guaranteed Bit Rate
	 * Video (Buffered Streaming), TCP-based (e.g., www, e-mail, 
	 * 		chat, ftp, p2p file sharing, progressive video, etc.) 
	 * Priority 9
	 * Packet Delay Budget 300ms
	 * Packet Error Loss Rate 0.000001
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
