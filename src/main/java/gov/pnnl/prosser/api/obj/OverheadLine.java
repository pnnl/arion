/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.lib.LineConfiguration;
import gov.pnnl.prosser.api.lib.OverheadLineConductor;

import java.util.EnumSet;

/**
 * Overhead Line Object
 *
 * @author nord229
 */
public class OverheadLine extends Line<LineConfiguration<OverheadLineConductor>> {

    public OverheadLine() {
    }

    public OverheadLine(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length, final LineConfiguration<OverheadLineConductor> configuration) {
        super(phases, from, to, length, configuration);
    }

    @Override
    public String getGLDObjectType() {
        return "overhead_line";
    }

}
