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
package gov.pnnl.prosser.api.ns3.obj.csma;

import gov.pnnl.prosser.api.ns3.obj.NetDeviceContainer;
import gov.pnnl.prosser.api.ns3.obj.NetworkHelper;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to help setup CSMA net devices and networks.
 *
 * @author happ546
 *
 */
public class CsmaHelper extends NetworkHelper {

	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;

	/**
	 * Create a new CsmaHelper
	 * @param name the string name
	 */
	public CsmaHelper(String name) {
		this.setName(name);
		this.channelAttributes = new HashMap<>();
		this.deviceAttributes = new HashMap<>();
	}

	/**
	 * @param node the Node to install the CSMA device on and connect to the channel
	 * @param destinationContainer the NetDeviceContainer to store the CsmaNetDevice in
	 */
	public void install(Node node, NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() +
				".Add (" + this.getName() + ".Install (" + node.getName() + "));\n");
	}

	/**
	 * @param node the Node to install the CSMA device on and connect to the channel
	 * @param channel the CsmaChannel to use
	 * @param destinationContainer the NetDeviceContainer to store the CsmaNetDevice in
	 */
	public void install(Node node, CsmaChannel channel,
			NetDeviceContainer destinationContainer) {
		appendPrintInfo(destinationContainer.getName() +
				".Add (" + this.getName() + ".Install (" +
				node.getPointerName() + ", " + channel.getPointerName() + "));\n");
	}

	/**
	 * @param nodes the NodeContainer to install the CSMA devices on and connect to the channel
	 * @param channel the CsmaChannel to use
	 * @param destinationContainer the NetDeviceContainer to store the CsmaNetDevices in
	 */
	public void install(NodeContainer nodes, CsmaChannel channel,
			NetDeviceContainer destinationContainer) {

		appendPrintInfo(destinationContainer.getName() +
				".Add (" + this.getName() + ".Install (" +
				nodes.getName() + ", " + channel.getPointerName() + "));\n");
	}

	/**
	 * Set attributes for this CSMA channel
	 * @param attr the attribute to set the value to
	 * @param value the string value to set
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintInfo(this.getName() + ".SetChannelAttribute (\"" + attr +
				"\", StringValue (\"" + value + "\"));\n");
	}

	/**
	 * Set attributes for this CSMA device
	 * @param attr the attribute to set the value to
	 * @param value the string value to set
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		appendPrintInfo(this.getName() + ".SetDeviceAttribute (\"" + attr +
				"\", StringValue (\"" + value + "\"));\n");
	}

	/**
	 * This method creates an ns3::CsmaChannel with the attributes configured by CsmaHelper::SetChannelAttribute, an ns3::CsmaNetDevice with the attributes configured by CsmaHelper::SetDeviceAttribute and then adds the device to the node and attaches the channel to the device.
	 *
	 * @param node the node to install the device in
	 * @return NetDevicecontainer A container holding the added net device.
	 */
	public NetDeviceContainer install(Node node)
	{
		String varName = this.getClass().getSimpleName() + "_" + java.util.UUID.randomUUID().toString().replace("-", "");
		NetDeviceContainer temp = new NetDeviceContainer(varName);
		appendPrintInfo(varName + " = " + this.getName() + ".Install (" +  node.getName() + ");\n");

		return temp;
	}

}
