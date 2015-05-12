/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Generic Conductor - marker class for generics
 *
 * @author nord229
 */
public abstract class Conductor extends PowerflowLibrary {

    public Conductor(final GldSimulator simulator) {
        super(simulator);
    }
}
