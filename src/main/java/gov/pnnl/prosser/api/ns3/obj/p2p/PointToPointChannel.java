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
package gov.pnnl.prosser.api.ns3.obj.p2p;

import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.obj.Channel;
import gov.pnnl.prosser.api.ns3.obj.Router;

/**
 *
 *
 * @author happ546
 *
 */
public class PointToPointChannel extends Channel {

	/**
	 * Routers this Channel connects
	 */
	private Router routerA, routerB;

	/**
	 * Creates a nameless PointToPointChannel
	 */
	public PointToPointChannel() {
		super();
		routerA = null;
		routerB = null;
		this.setType(NetworkType.P2P);
	}

	/**
	 * Creates a PointToPointChannel with the given name.
	 * Creates a Ptr (smart pointer) for this channel, used in
	 * helper methods.
	 * @param name
	 */
	public PointToPointChannel(String name) {
		this();
		this.setName(name);
		this.getAsPointer();
	}

	/**
	 * Returns one of the end point Routers of this PointToPointChannel
	 *
	 * @return routerA
	 */
	public Router getRouterA() {
		return routerA;
	}

	/**
	 * @param router one of the end point Routers for this p2p channel
	 */
	public void setRouterA(Router router) {
		routerA = router;
		routerA.setNameString(router.getName());
	}

	/**
	 * Returns one of the end point Routers of this PointToPointChannel
	 *
	 * @return routerB
	 */
	public Router getRouterB() {
		return routerB;
	}

	/**
	 * @param router one of the end point Routers for this p2p channel
	 */
	public void setNodeB(Router router) {
		routerB = router;
		routerB.setNameString(router.getName());
	}

	/**
	 * @param device the PointToPointNetDevice to connect to this PointToPointChannel
	 */
	public void attach(PointToPointNetDevice device) {

		// Get time to avoid name conflicts in output ns-3 file
		long currentTime = System.currentTimeMillis();

		String pointer = "Ptr<PointToPointNetDevice> pointToPointNetDevicePointer_"
				+ currentTime + " = " + device.getName() + ";\n";

		appendPrintInfo(this.getName() + ".Attach(" + pointer + ");\n");
	}

	/**
	 * @return true if this PointToPointChannel has 2 end point Routers,
	 * false otherwise
	 */
	public boolean hasTwoNodes() {
		if (getRouterA() != null && getRouterB() != null) {
			return true;
		}
		return false;
	}

}
