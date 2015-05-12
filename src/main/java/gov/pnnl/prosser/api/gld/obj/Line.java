/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.lib.Conductor;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;

/**
 * Generic Power Line
 *
 * @author nord229
 */
public abstract class Line<C extends Conductor, Q extends LineConfiguration<C>> extends LinkObject {

    /**
     * length of line in feet
     */
    private double length;

    /**
     * Line Configuration
     */
    private Q configuration;

    public Line(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the length of line in feet
     * 
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Set the length of line in feet
     * 
     * @param length
     *            the length to set
     */
    public void setLength(final double length) {
        this.length = length;
    }

    /**
     * Get the Line Configuration
     * 
     * @return the configuration
     */
    public Q getConfiguration() {
        return configuration;
    }

    /**
     * Set the Line Configuration
     * 
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final Q configuration) {
        this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "length", this.length, "ft");
        writeProperty(sb, "configuration", this.configuration);
    }

}
