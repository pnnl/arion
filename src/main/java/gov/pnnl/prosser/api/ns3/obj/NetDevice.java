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

/**
 * This is a skeleton class for the NetDevice base class.
 * Every networking device (Wi-Fi, CSMA, p2p, etc.) in ns-3 is
 * built off this class.
 *
 * @author happ546
 *
 */
public class NetDevice extends AbstractNs3Object
{
	public NetDevice(String name)
	{
		this.setNameString(name);
		this.getAsPointer();
	}

	public NetDevice() {}

	/**
	 *  returns a string with the c++ variable name that represents the uint32_t interface index for the NetDevice
	 */
	public String getInterface()
	{
		String varName =  "uint32_t_" + java.util.UUID.randomUUID().toString().replace("-", "");
		appendPrintInfo("uint32_t " + varName + " = " +	this.getName() + "->GetIfIndex();\n");
		return varName;
	}
}
