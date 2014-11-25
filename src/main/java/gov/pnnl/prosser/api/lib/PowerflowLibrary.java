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
}
