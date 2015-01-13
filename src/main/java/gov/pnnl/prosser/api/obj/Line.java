/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.lib.Conductor;
import gov.pnnl.prosser.api.lib.LineConfiguration;

import java.util.EnumSet;

/**
 * Generic Power Line
 *
 * @author nord229
 */
public abstract class Line<C extends Conductor, Q extends LineConfiguration<C>> extends LinkObject {

    /**
     * length of line in feet
     */
    private final double length;

    /**
     * Line Configuration
     */
    private final Q configuration;

    public Line() {
        this.length = 0;
        this.configuration = null;
    }

    public Line(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length, final Q configuration) {
        super(phases, from, to);
        this.length = length;
        this.configuration = configuration;
    }

    public <T extends Line<C, Q>, Z extends AbstractBuilder<C, Q, T, Z>> Line(final AbstractBuilder<C, Q, T, Z> builder) {
        super(builder);
        this.length = builder.length;
        this.configuration = builder.configuration;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @return the configuration
     */
    public Q getConfiguration() {
        return configuration;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GldUtils.writeProperty(sb, "length", this.length, "ft");
        GldUtils.writeProperty(sb, "configuration", this.configuration);
    }

    public static abstract class AbstractBuilder<C extends Conductor, Q extends LineConfiguration<C>, T extends Line<C, Q>, Z extends AbstractBuilder<C, Q, T, Z>> extends LinkObject.AbstractBuilder<T, Z> {

        private double length;

        private Q configuration;

        public Z length(final double length) {
            this.length = length;
            return self();
        }

        public Z configuration(final Q configuration) {
            this.configuration = configuration;
            return self();
        }
    }

}
