/**
 *
 */
package gov.pnnl.prosser.api.lib;

/**
 * Generic Conductor - marker class for generics
 *
 * @author nord229
 */
public abstract class Conductor extends PowerflowLibrary {

    public Conductor() {
    }

    public Conductor(final String name) {
        super(name);
    }

}
