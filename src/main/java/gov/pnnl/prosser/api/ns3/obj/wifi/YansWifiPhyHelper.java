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
package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.module.Network;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;

/**
 * The YansWifiPhyHelper (Yet Another Network Simulator; see http://cutebugs.net/files/wns2-yans.pdf)
 * is a helper class to simplify the setup of YansWifiPhy physical layer implementations of the
 * Wi-Fi 802.11a physical layer.
 *
 * @author happ546
 *
 */
public class YansWifiPhyHelper extends NetworkHelper {

	/**
	 * @param name
	 */
	public YansWifiPhyHelper(String name) {
		this.setName(name);
	}

	/**
	 * Sets the default parameters for this YansWifiPhyHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = YansWifiPhyHelper::Default ();\n");
	}

	/**
	 *
	 * @param channelHelper
	 * 		the YansWifiChannelHelper used to create a channel
	 * 		for this YansWifiPhyHelper
	 */
	public void setChannel(YansWifiChannelHelper channelHelper) {
		appendPrintInfo(this.getName() + ".SetChannel (" + channelHelper.getName() + ".Create ());\n");
	}

	public void setChannel(YansWifiChannel channel) {
		appendPrintInfo(getName() + ".SetChannel (" + channel.getPointerName() + ");\n");
	}

	/**
	 *
	 * @param dlType
	 * 		the PCAP data link type for this YansWifiPhyHelper
	 */
	public void setPcapDataLinkType(String dlType) {
		appendPrintInfo(this.getName() + ".SetPcapDataLinkType (" + dlType + ");\n");
	}

}
