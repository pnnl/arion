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

    public <T extends Conductor, Z extends AbstractBuilder<T, Z>> Conductor(final AbstractBuilder<T, Z> builder) {
        super(builder);
    }

    public static abstract class AbstractBuilder<T extends Conductor, Z extends AbstractBuilder<T, Z>> extends PowerflowLibrary.AbstractBuilder<T, Z> {

    }

}
