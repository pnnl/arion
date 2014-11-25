/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;
import gov.pnnl.prosser.api.lib.Conductor;
import gov.pnnl.prosser.api.lib.LineConfiguration;

import java.util.EnumSet;

/**
 * Generic Power Line
 *
 * @author nord229
 */
public abstract class Line<T extends LineConfiguration<? extends Conductor>> extends LinkObject {

    /**
     * length of line in feet
     */
    private double length;

    /**
     * Line Configuration
     */
    private T configuration;

    public Line() {
    }

    public Line(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length, final T configuration) {
        super(phases, from, to);
        this.length = length;
        this.configuration = configuration;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(final double length) {
        this.length = length;
    }

    /**
     * @return the configuration
     */
    public T getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final T configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.appendProperty(sb, "length", this.length, "ft");
        GLDUtils.appendProperty(sb, "configuration", this.configuration);
    }

}
