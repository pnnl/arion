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
public class OverheadLine extends Line<OverheadLineConductor, LineConfiguration<OverheadLineConductor>> {

    public OverheadLine() {
    }

    public OverheadLine(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length, final LineConfiguration<OverheadLineConductor> configuration) {
        super(phases, from, to, length, configuration);
    }

    public OverheadLine(final Builder builder) {
        super(builder);
    }

    @Override
    public String getGLDObjectType() {
        return "overhead_line";
    }

    public static class Builder extends Line.AbstractBuilder<OverheadLineConductor, LineConfiguration<OverheadLineConductor>, OverheadLine, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public OverheadLine build() {
            return new OverheadLine(this);
        }

    }

}
