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
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;

/**
 * This class is used to help assign unique IP addresses to IP Net Devices
 *
 * @author happ546
 *
 */
public class Ipv4AddressHelper extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4AddressHelper with the given name
	 *
	 * @param name
	 */
	public Ipv4AddressHelper(String name) {
		this.setName(name);
	}

	/**
	 *
	 * @param ipBase the IPv4 address base for DHCP address assignment
	 * @param mask the subnet mask used for DHCP address assignment
	 */
	public void setBase(String ipBase, String mask) {
		appendPrintInfo(this.getName() + ".SetBase (\"" + ipBase +
				"\", \"" + mask + "\");\n");
	}

	/**
	 *
	 * @param devices the net devices to assign IP addresses to
	 */
	public void assign(NetDeviceContainer devices) {

		String appendedUUID = java.util.UUID.randomUUID().toString().replace("-", "");
		String contName = "ipv4IntCont_" + this.getName() + appendedUUID;

		appendPrintInfo("Ipv4InterfaceContainer " + contName +
				" = " + this.getName() + ".Assign (" + devices.getName() + ");\n");

		// TODO DEBUGGING prints IP addresses to console
/*
		String[] arr = devices.getName().split("_");
		String routerName = (arr.length == 4) ? arr[0] + arr[2] : arr[0];
		appendPrintInfo("for (int i = 0; i < " + contName + ".GetN (); i++) { \n" +
				"    cout << \"" + routerName + ": \" << " + contName + ".GetAddress (i) << endl;\n" +
				"  }\n\n");
*/
	}

	/**
	 *
	 * @param devices the net devices to assign IP addresses to
	 * @param destinationInterface the Ipv4InterfaceContainer to
	 * 			hold the IPv4 net devices
	 */
	public void assign(NetDeviceContainer devices,
			Ipv4InterfaceContainer destinationInterface)
	{
		appendPrintInfo(destinationInterface.getName() + " = " + this.getName() +
				".Assign (" + devices.getName() + ");\n");
	}

	/**
	 * Increments the network number and resets the IP address counter
	 * to the preset base value.
	 */
	public void newNetwork() {
		appendPrintInfo(this.getName() + ".NewNetwork ();\n");
	}
}
