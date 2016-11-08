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
import gov.pnnl.prosser.api.ns3.obj.NetDevice;

/**
 *
 *
 * @author lalo609
 *
 */
public class Ipv4 extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4 encapsulated in a smart pointer
	 *
	 * @param name
	 */
	public Ipv4(String name) {
		this.setNameString(name);
		this.getAsPointer();
	}

	/**
	 *
	 * @param device the device to add to the list of Ipv4 interfaces which can be used as output interfaces during packet forwarding.
	 * @return the name of the c-variable which will contain the index of the Ipv4 interface added.
	 */
	public String addInterface(NetDevice device)
	{
		//int32_t ifIndexA = ipv4A->AddInterface (devsA.Get (0));

		String varName = "int32_t_" + java.util.UUID.randomUUID().toString().replace("-", "");
		appendPrintInfo("int32_t " + varName + " = " +
							this.getName() + "->AddInterface(" + device.getName() + ");\n");
		return varName;
	}


	/**
	 *
	 * @param interfaceIndex Interface number of an Ipv4 interface
	 * @param address Ipv4InterfaceAddress address to associate with the underlying Ipv4 interface
	 */
	public void addAddress(String interfaceIndex, Ipv4InterfaceAddress address)
	{
		appendPrintInfo(this.getName() + "->AddAddress("
									   + interfaceIndex + ", "
									   + address.getName()
									   + ");\n");
	}

	public void setMetric(String interFace, int metric)
	{
		appendPrintInfo(this.getName() + "->SetMetric("
				   + interFace + ", "
				   + metric    + ");\n");
	}

	public void setUp(String interFace)
	{
		appendPrintInfo(this.getName() + "->SetMetric(" + interFace + ");\n");
	}
}
