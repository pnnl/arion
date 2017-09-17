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

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Address;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4InterfaceContainer;

/**
 * The PointToPointEpcHelper is a specialized helper to facilitate easier setup
 * of LTE networks with EPC (Evolved Packet Core; allows IP-type functionality).
 *
 * @author happ546
 *
 */
public class PointToPointEpcHelper extends NetworkHelper {

	/**
	 * @param ueDevices the UE LTE devices to assign IPv4 addresses to
	 * @param destination the Ipv4AddressContainer to hold the created IPv4 interfaces
	 */
	public void assignUeIpv4Address(NetDeviceContainer ueDevices,
									Ipv4InterfaceContainer destination) {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not

		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(destination.getName() + " = " + this.getName() + deref +
		//				"AssignUeIpv4Address(" + ueDevices.getName() + ");\n");
	}

	/**
	 * Gets the PGW (Packet data network GateWay) node of the LTE network
	 * and puts it into the given Pointer Node
	 */
	public void getPgwNode(Pointer<Node> node) {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not

		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(node.getName() + " = " + this.getName() + deref + "GetPgwNode();\n");
	}

	/**
	 * @return the Ipv4Address of the default gateway
	 */
	public Ipv4Address getUeDefaultGatewayAddress() {
		// Sets the dereferencer operator appropriately for pointer encapsulation or not

		// TODO refactor out deref
		//String deref = this.isPointer() ? "->" : ".";
		//appendPrintInfo(this.getName() + deref + "GetUeDefaultGatewayAddress();\n");
		return new Ipv4Address(null);
	}

}
