/**
 *
 */
package gov.pnnl.prosser.api.lib;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * Generic Powerflow Library Objects
 *
 * @author nord229
 */
public abstract class PowerflowLibrary extends AbstractProsserObject {

    public PowerflowLibrary() {
    }

    public PowerflowLibrary(final String name) {
        super(name);
    }

    public <T extends PowerflowLibrary, Z extends AbstractBuilder<T, Z>> PowerflowLibrary(final AbstractBuilder<T, Z> builder) {
        super(builder);
    }

    public static abstract class AbstractBuilder<T extends PowerflowLibrary, Z extends AbstractBuilder<T, Z>> extends AbstractProsserObject.AbstractBuilder<T, Z> {

    }
}
