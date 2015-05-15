/**
 * 
 */
package gov.pnnl.prosser.api.fncs;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author nord229
 *
 */
public enum FncsSimType {
    @SerializedName("power_grid")
    PowerGrid,
    @SerializedName("communication_network")
    CommunicationNetwork;
}