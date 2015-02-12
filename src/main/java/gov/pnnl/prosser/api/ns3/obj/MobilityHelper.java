/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class MobilityHelper extends AbstractNs3Object {

	/**
	 * 
	 * @param model the mobility model type used by this MobilityHelper
	 * @param mode the mode for the mobility model
	 * @param modeValue the value for the mode attribute
	 * @param speed the ns-3 speed value to use for the mobility model
	 * @param bounds the bounds value used for the mobility model (implemented in ns-3 as shapes:
	 * 			e.g. Rectangle(...))
	 */
	//TODO create actual object for bounds if we expect to use this
	public void setMobilityModel(String model, String mode, String modeValue, String speed, String bounds) {
		appendPrintObj(this.getName() + ".SetMobilityModel(\"" + model + "\", " +
						"\"Mode\", StringValue(\"" + mode + "\"), " + 
						"\"Time\", StringValue(\"" + modeValue + "\"), " + 
						"\"Speed\", StringValue(\"" + speed + "\"), " +
						"\"Bounds\", " + bounds + ");\n");
	}
	
	/**
	 * 
	 * @param model the mobility model used by this MobilityHelper
	 */
	public void setMobilityModel(String model) {
		appendPrintObj(this.getName() + ".SetMobilityModel(\"" + model + "\");\n");
	}
	
	/**
	 * 
	 * @param nodes the NodeContainer to install this Mobility model on
	 */
	public void install(NodeContainer nodes) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	
	/**
	 * 
	 * @param nodes the NodeContainer holding the Node to install this MobilityModel on
	 * @param index the index of the Node to install this MobilityModel on
	 */
	public void install(NodeContainer nodes, int index) {
		appendPrintObj(this.getName() + ".Install(" + nodes.getName() + ".Get(" + index + "));\n");
	}

	/**
	 * 
	 * @param gridPositionAllocator
	 * @param minX the x-axis start position for the Nodes using this MobilityHelper
	 * 				gets incremented by deltaX for each Node
	 * @param minY the y-axis start position for the Nodes using this MobilityHelper
	 * 				gets incremented by deltaY for each Node
	 * @param deltaX the x-axis spacing for the Nodes using this MobilityHelper
	 * @param deltaY the y-axis spacing for the Nodes using this MobilityHelper
	 * @param gridWidth the integer width of the grid used
	 * @param rowFirst a String of RowFirst or ColumnFirst
	 */
	public void setPositionAllocator(String gridPositionAllocator,
									double minX, double minY, double deltaX,
									double deltaY, int gridWidth, String rowFirst) {
		appendPrintObj(this.getName() + 
				".SetPositionAllocator(\"" + gridPositionAllocator + "\", " +
				"\"MinX\", DoubleValue(" + minX + "), " +
				"\"MinY\", DoubleValue(" + minY + "), " +
				"\"DeltaX\", DoubleValue(" + deltaX + "), " +
				"\"DeltaY\", DoubleValue(" + deltaY + "), " +
				"\"GridWidth\", UintegerValue(" + gridWidth + "), " +
				"\"LayoutType\", StringValue(\"" + rowFirst + "\"));\n");
	}
	
}
