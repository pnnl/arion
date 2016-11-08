/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
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
*/
package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * The NqosWifiMacHelper (No Quality-of-Service) is a helper class to simplify
 * the setup of NQoS Wi-Fi MAC layers in Wi-Fi networks.
 *
 * @author happ546
 *
 */
public class NqosWifiMacHelper extends AbstractNs3Object {

	private WifiMacType wifiMacType;

	/**
	 * @param name
	 */
	public NqosWifiMacHelper(String name) {
		this.setName(name);
		wifiMacType = null;
	}

	public NqosWifiMacHelper(String name, WifiMacType type) {
		this(name);
		wifiMacType = type;
	}

	/**
	 * Sets the default parameters for this NqosWifiMacHelper (see ns-3 documentation for information)
	 */
	public void defaultParams() {
		appendPrintInfo(this.getName() + " = NqosWifiMacHelper::Default();\n");
	}

	/**
	 *
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 * @param activeProbing used by the StaWifiMac ns-3 class
	 */
	public void setType(WifiMacType type, Ssid ssid, boolean activeProbing) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\", "
				+ "\"Ssid\", SsidValue (" + ssid.getName() + "), "
				+ "\"ActiveProbing\", BooleanValue (" + activeProbing + "));\n");
	}

	/**
	 *
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 * @param ssid the Service Set Identifier to use for the WiFi network created by
	 * 			this NQosWifiMacHelper
	 */
	public void setType(WifiMacType type, Ssid ssid) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\", "
				+ "\"Ssid\", SsidValue (" + ssid.getName() + "));\n");
	}

	/**
	 *
	 * @param type the WifiMacType for this NQosWifiMacHelper (access-point, station, or ad-hoc WiFi nodes)
	 */
	public void setType(WifiMacType type) {
		appendPrintInfo(this.getName() + ".SetType (\"" + type.toString() + "\");\n");
	}

	public WifiMacType getWifiMacType() {
		return wifiMacType;
	}

	public void setWifiMacType(WifiMacType wifiMacType) {
		this.wifiMacType = wifiMacType;
	}
}
