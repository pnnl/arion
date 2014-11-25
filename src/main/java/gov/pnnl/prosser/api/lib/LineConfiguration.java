/**
 *
 */
package gov.pnnl.prosser.api.lib;

/**
 * General Line Configuration
 *
 * @author nord229
 */
public abstract class LineConfiguration<T extends Conductor> extends PowerflowLibrary {

    public LineConfiguration() {
    }

    public LineConfiguration(final String name) {
        super(name);
    }

}
