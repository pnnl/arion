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
package gov.pnnl.prosser.api.ns3.obj.internet;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Address;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4Mask;

/**
 * This class is used to help setup IPV4 static routing
 *
 * @author lalo609
 *
 */
public class Ipv4StaticRouting extends AbstractNs3Object {

	/**
	 * Creates a new Ipv4StaticRouting with the given name
	 *
	 * @param name
	 */
	public Ipv4StaticRouting(String name) {
		this.setNameString(name);
		this.getAsPointer();

	}

	/**
	 * Add a host route to the static routing table.
	 *
	 * @param dest The Ipv4Address destination for this route.
	 * @param nextHop The Ipv4Address of the next hop in the route.
	 * @param interFace The network interface index used to send packets to the destination
	 * @param metric Metric of route in case of multiple routes to same destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName()
				+ "->AddHostRouteTo("
			    + dest.getName() + ", "
		    	+ nextHop.getName() + ", "
			    + interFace + ", "
			    + metric + ");\n");
	}

	/**
	 * Add a host route to the static routing table.
	 *
	 * @param dest The Ipv4Address destination for this route.
	 * @param nextHop The Ipv4Address of the next hop in the route.
	 * @param interFace The network interface index used to send packets to the destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, Ipv4Address nextHop, int interFace)
	{
		this.AddHostRouteTo(dest, nextHop, interFace, 0);
	}

	/**
	 * Add a host route to the static routing table.
	 *
	 * @param dest The Ipv4Address destination for this route.
	 * @param interFace The network interface index used to send packets to the destination
	 * @param metric Metric of route in case of multiple routes to same destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, int interFace, int metric)
	{
		appendPrintInfo(this.getName()
				+ "->AddHostRouteTo("
			    + dest.getName() + ", "
			    + interFace + ", "
			    + metric + ");\n");
	}

	/**
	 * Add a host route to the static routing table.
	 *
	 * @param dest The Ipv4Address destination for this route.
	 * @param interFace The network interface index used to send packets to the destination
	 */
	public void AddHostRouteTo (Ipv4Address dest, int interFace)
	{
		this.AddHostRouteTo(dest, interFace, 0);
	}

	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName()
				+ "->AddNetworkRouteTo("
			    + network.getName() + ", "
			    + networkMask.getName() + ", "
			    + nextHop.getName() + ", "
	    		+ interFace + ", "
			    + metric + ");\n");
	}

	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, Ipv4Address nextHop, int interFace)
	{
		this.AddNetworkRouteTo(network, networkMask, nextHop, interFace, 0);
	}

	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, int interFace, int metric)
	{
		appendPrintInfo(this.getName()
				+ "->AddNetworkRouteTo("
			    + network.getName() + ", "
			    + networkMask.getName() + ", "
	    		+ interFace + ", "
			    + metric + ");\n");
	}

	public void AddNetworkRouteTo (Ipv4Address network, Ipv4Mask networkMask, int interFace)
	{
		this.AddNetworkRouteTo(network, networkMask, interFace, 0);
	}


	public void RemoveMulticastRoute (Ipv4Address origin, Ipv4Address group, int inputInterface)
	{
		appendPrintInfo(this.getName()
				+ "->RemoveMulticastRoute("
			    + origin.getName() + ", "
			    + group.getName() + ", "
			    + inputInterface + ");\n");
	}

	public void RemoveMulticastRoute (int index)
	{
		appendPrintInfo(this.getName() + ".RemoveMulticastRoute(" + index + ");\n");
	}


	public void RemoveRoute (int index)
	{
		appendPrintInfo(this.getName() + ".RemoveRoute(" + index + ");\n");
	}

	public void SetDefaultMulticastRoute (int outputInterface)
	{
		appendPrintInfo(this.getName() + ".SetDefaultMulticastRoute(" + outputInterface + ");\n");
	}

	public void SetDefaultRoute (Ipv4Address nextHop, int interFace, int metric)
	{
		appendPrintInfo(this.getName()
				+ "->SetDefaultRoute("
			    + nextHop.getName() + ", "
			    + interFace + ", "
			    + metric + ");\n");
	}

	public void SetDefaultRoute (Ipv4Address nextHop, int interFace)
	{
		this.SetDefaultRoute(nextHop, interFace, 0);
	}
}













