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
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for enabling the easy use of the std::vector in the c++ library.
 *
 * @author happ546
 *
 */
public class Vector<T> extends AbstractNs3Object {

	private List<String> names;

	/**
	 * @param name the name of this Vector
	 * @param clazz the type class of this Vector
	 */
	public Vector(String name, Class<T> clazz) {
		this.names = new ArrayList<>();
		this.setNameString(name);
		this.setType(clazz);
	}

	/**
	 * @param clazz the type class of this Vector
	 */
	public void setType(Class<T> clazz) {
		appendPrintInfo("vector<" + clazz.getSimpleName().toLowerCase() + "> " + this.getName() + ";\n");
	}

	/**
	 * Adds the given name to the List of names to output to the ns3 file
	 * @param name
	 */
	public void addName(String name) {
		names.add(name);
		pushBack(name);
	}

	/**
	 * @param name
	 * 			the String name of the ns-3 object to add to this Vector
	 */
	public void pushBack(String name) {
		appendPrintInfo(this.getName() + ".push_back(\"" +
				name + "\");\n");
	}

	/**
	 *
	 * @param obj the NetworkCapable object (AuctionObject or Controller)
	 * 			with the name to add to this StringVector
	 */
	public void pushBack(NetworkCapable obj) {
		pushBack(obj.getNetworkInterfaceName() + "\");\n");
	}

	/**
	 * @param obj the AbstractNs3Object with the name to add to
	 * 			this Vector
	 */
	public void pushBack(AbstractNs3Object obj) {
		pushBack(obj.getName());
	}

	/**
	 * Pushes each Node in nc into the Vector
	 *
	 * @param nc the NodeContainer
	 */
	public void pushBack(NodeContainer nc) {
		for (int i = 0; i < nc.getNumNodes(); i++) {
			pushBack(nc.getNodeNoPrint(i));
		}
	}

}
