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
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Address;

/**
 * This class is used to help setup IPV4 static routing
 *
 * @author happ546
 *
 */
public class Ipv4StaticRoutingHelper extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4StaticRoutingHelper with the given name
	 *
	 * @param name
	 */
	public Ipv4StaticRoutingHelper(String name) {
		this.setName(name);
	}


	public Ipv4StaticRouting GetStaticRouting(Ipv4 ipv4)
	{
		/*
	 	 * needs to print out:
	 	 *		Ptr<Ipv4StaticRouting> [unique_name_here];
	 	 *	 	[unique_name_here] = ipv4RoutingHelper.GetStaticRouting (ipv4);
	 	 *
		 *  and needs to return the Ipv4StaticRouting object
		 */

		String varName = this.getClass().getSimpleName() + "_" + java.util.UUID.randomUUID().toString().replace("-", "");
		Ipv4StaticRouting temp = new Ipv4StaticRouting(varName); //prints constructor to file
		appendPrintInfo(varName + " = " +
							this.getName() + ".GetStaticRouting(" +
							ipv4.getName() + ");\n");

		return temp;
	}


	/**
	 * Adds a default route to the static routing table
	 *
	 * @param addr the Ipv4Address to use
	 * @param interfaceIndex
	 */
	public void setDefaultRoute(Ipv4Address addr, int interfaceIndex) {
		appendPrintInfo(this.getName() +
				".SetDefaultRoute(" + addr.getName() + ", " + interfaceIndex);
	}

	/**
	 * Add a multicast route to a node and net device
	 *
	 * @param n
	 * @param source
	 * @param group
	 * @param input
	 * @param output

	public void AddMulticastRoute (Node n, Ipv4Address source, Ipv4Address group, NetDevice input, NetDeviceContainer output)
	{
		//void 	AddMulticastRoute (Ptr< Node > n, Ipv4Address source, Ipv4Address group, Ptr< NetDevice > input, NetDeviceContainer output)
		appendPrintInfo(this.getName()
						+ ".AddMulticastRoute("
					    + n.getName() + ", "
					    + source.getName() + ", "
					    + group.getName() + ", "
					    + input.getName() + ", "
					    + output.getName() + ");\n");
	}
	*/

	/**
	 * Add a default route to the static routing protocol to forward packets out a particular interface.
	 * Functionally equivalent to: route add 224.0.0.0 netmask 240.0.0.0 dev nd
	 *
	 * @param n node
	 * @param nd device of the node to add default route

	public void SetDefaultMulticastRoute (Node n, NetDevice nd)
	{
		//void 	SetDefaultMulticastRoute (Ptr< Node > n, Ptr< NetDevice > nd)
		appendPrintInfo(this.getName()
						+ ".SetDefaultMulticastRoute("
						+ n.getName() + ", "
						+ nd.getName() + ");\n");
	} */
}













