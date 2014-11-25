/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * Marker for Residential Enduse objects
 *
 * @author nord229
 */
public abstract class ResidentialEnduse extends AbstractProsserObject {

    public ResidentialEnduse() {
    }

    public ResidentialEnduse(final String name) {
        super(name);
    }

}
