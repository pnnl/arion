/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;

/**
 * Overhead Line Object
 *
 * @author nord229
 */
public class OverheadLine extends Line<OverheadLineConductor, LineConfiguration<OverheadLineConductor>> {

    public OverheadLine(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "overhead_line";
    }

}
