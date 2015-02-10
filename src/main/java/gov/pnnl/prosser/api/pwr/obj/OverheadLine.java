/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.pwr.lib.LineConfiguration;
import gov.pnnl.prosser.api.pwr.lib.OverheadLineConductor;

/**
 * Overhead Line Object
 *
 * @author nord229
 */
public class OverheadLine extends Line<OverheadLineConductor, LineConfiguration<OverheadLineConductor>> {

    @Override
    public String getGldObjectType() {
        return "overhead_line";
    }

}
