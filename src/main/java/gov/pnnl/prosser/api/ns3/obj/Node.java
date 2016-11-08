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
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;

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

	private static int ID = 0;
	private int thisID;

	/**
	 * Create a nameless Node.
	 */
	public Node()
	{
		super();
		thisID = ID;
		ID++;
	}

	/**
	 * Creates a new Node encapsulated in a smart pointer
	 * @param name
	 */
	public Node(String name)
	{
		setNameString(name);
		getAsPointer();
		thisID = ID;
		ID++;
	}

	/**
	 *
	 * @return name
	 * 			the name of this Node
	 */
	@Override
	public String getName()
	{
		return this.getPointerName();
	}

	/**
	 *
	 * @param device
	 */

	public void addDevice(NetDevice device)
	{
		appendPrintInfo(this.getName() + "->AddDevice (" + device.getName() + ");\n");
	}


	public Ipv4 getObjectIpv4()
	{

		String varName = "Ipv4_" + java.util.UUID.randomUUID().toString().replace("-", "");
		Ipv4 temp = new Ipv4(varName); //prints constructor to file
		appendPrintInfo("Ptr<Ipv4> " + varName + " = " +
							this.getName() + "->GetObject<Ipv4> ();\n");
		return temp;
	}

	/**
	 * Retrieve the index-th NetDevice associated to this node.
	 *
	 * @param index	the index of the requested NetDevice
	 * @return the requested NetDevice.
	 */
	public NetDevice getDevice(int index)
	{
		String varName = "NetDevice_" + java.util.UUID.randomUUID().toString().replace("-", "");
		NetDevice temp = new NetDevice(varName);
		appendPrintInfo(varName + " = " + this.getName() + "->GetDevice (" + index + ");\n");
		return temp;
	}

	/**
	 * Gets the unique ID of this Node, which also is its index in the ns-3 NodeList.
	 */
	public int getId() {
		appendPrintInfo(getName() + "->GetId ();\n");
		return thisID;
	}

}
