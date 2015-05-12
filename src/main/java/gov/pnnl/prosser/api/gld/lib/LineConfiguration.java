/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * General Line Configuration
 *
 * @author nord229
 */
public abstract class LineConfiguration<C extends Conductor> extends PowerflowLibrary {

    public LineConfiguration(final GldSimulator simulator) {
        super(simulator);
    }
}
