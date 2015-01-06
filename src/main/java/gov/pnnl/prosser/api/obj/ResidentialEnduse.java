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

    public <T extends ResidentialEnduse, Z extends AbstractBuilder<T, Z>> ResidentialEnduse(final AbstractBuilder<T, Z> builder) {
        super(builder);
    }

    public static abstract class AbstractBuilder<T extends ResidentialEnduse, Z extends AbstractBuilder<T, Z>> extends AbstractProsserObject.AbstractBuilder<T, Z> {

    }
}
