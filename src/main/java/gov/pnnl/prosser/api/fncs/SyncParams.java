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
public class SyncParams {
    @SerializedName("number_of_power_grid_sims")
    private int numPowerGridSims = 1;

    /**
     * @return the numPowerGridSims
     */
    public int getNumPowerGridSims() {
        return numPowerGridSims;
    }

    /**
     * @param numPowerGridSims
     *            the numPowerGridSims to set
     */
    public void setNumPowerGridSims(int numPowerGridSims) {
        this.numPowerGridSims = numPowerGridSims;
    }

}