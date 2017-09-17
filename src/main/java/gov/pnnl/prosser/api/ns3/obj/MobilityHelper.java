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
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.MobilityModel;

/**
 * This class is used by wireless networks (Wi-Fi, LTE).
 *
 * @author happ546
 *
 */
public class MobilityHelper extends AbstractNs3Object {

	/**
	 * Creates a new MobilityHelper with the given name
	 *
	 * @param name
	 */
	public MobilityHelper(String name) {
		this.setName(name);
	}

	/**
	 *
	 * @param model the mobility model type used by this MobilityHelper
	 * @param mode the mode for the mobility model
	 * @param modeValue the value for the mode attribute
	 * @param speed the ns-3 speed value to use for the mobility model
	 * @param bounds the bounds value used for the mobility model (implemented in ns-3 as shapes:
	 * 			e.g. Rectangle(...))
	 */
	//TODO allow for all model types; and create actual object for bounds if we expect to use this
	public void setMobilityModel(String model, String mode, String modeValue, String speed, String bounds) {
		appendPrintInfo(this.getName() + ".SetMobilityModel (\"" + model + "\", " +
				"\"Mode\", StringValue (\"" + mode + "\"), " +
				"\"Time\", StringValue (\"" + modeValue + "\"), " +
				"\"Speed\", StringValue (\"" + speed + "\"), " +
				"\"Bounds\", " + bounds + ");\n");
	}

	/**
	 *
	 * @param model the mobility model used by this MobilityHelper
	 */
	// TODO allow for all model types
	public void setMobilityModel(MobilityModel model) {
		appendPrintInfo(this.getName() + ".SetMobilityModel (\"" + model.toString() + "\");\n");
	}


	/**
	 * @param node
	 * 		the Node to install this MobilityModel on
	 */
	public void install(Node node) {
		appendPrintInfo(this.getName() + ".Install (" + node.getPointerName() + ");\n");
	}

	/**
	 *
	 * @param nodes the NodeContainer to install this MobilityModel on
	 */
	public void install(NodeContainer nodes) {
		appendPrintInfo(this.getName() + ".Install (" + nodes.getName() + ");\n");
	}

	/**
	 *
	 * @param nodes the NodeContainer holding the Node to install this MobilityModel on
	 * @param index the index of the Node to install this MobilityModel on
	 */
	public void install(NodeContainer nodes, int index) {
		appendPrintInfo(this.getName() + ".Install (" + nodes.getName() + ".Get (" + index + "));\n");
	}

	/**
	 *
	 * @param minX the x-axis start position for the Nodes using this MobilityHelper
	 * 				gets incremented by deltaX for each Node
	 * @param minY the y-axis start position for the Nodes using this MobilityHelper
	 * 				gets incremented by deltaY for each Node
	 * @param deltaX the x-axis spacing for the Nodes using this MobilityHelper
	 * @param deltaY the y-axis spacing for the Nodes using this MobilityHelper
	 * @param gridWidth the integer width of the grid used
	 * @param rowFirst a String of RowFirst or ColumnFirst
	 */
	// TODO allow for other position allocator types
	public void setPositionAllocator(//(String gridPositionAllocator,
									double minX, double minY, double deltaX,
									double deltaY, int gridWidth, String rowFirst) {
		appendPrintInfo(this.getName() +
				".SetPositionAllocator (\"ns3::GridPositionAllocator\", " + // + gridPositionAllocator + "\", " +
				"\"MinX\", DoubleValue (" + minX + "), " +
				"\"MinY\", DoubleValue (" + minY + "), " +
				"\"DeltaX\", DoubleValue (" + deltaX + "), " +
				"\"DeltaY\", DoubleValue (" + deltaY + "), " +
				"\"GridWidth\", UintegerValue (" + gridWidth + "), " +
				"\"LayoutType\", StringValue (" + rowFirst + "));\n");
	}

}
