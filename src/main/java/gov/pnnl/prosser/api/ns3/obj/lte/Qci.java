/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
 *    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
 *    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 *    and may permit others to do so, subject to the following conditions:
 *    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
 *       the following disclaimers.
 *    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *       the following disclaimer in the documentation and/or other materials provided with the distribution.
 *    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
 *       form whatsoever without the express written consent of Battelle.
 * 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 *    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *                                PACIFIC NORTHWEST NATIONAL LABORATORY
 *                                            operated by
 *                                              BATTELLE
 *                                              for the
 *                                  UNITED STATES DEPARTMENT OF ENERGY
 *                                   under Contract DE-AC05-76RL01830
 *******************************************************************************/
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
