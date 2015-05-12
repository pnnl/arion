/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.gld.enums.ImplicitEnduses;

import java.util.Objects;

/**
 * GridLabD Residential module
 *
 * @author nord229
 */
public class Residential extends Module {

    /**
     * list of implicit enduses that are active in houses
     */
    // FIXME Deal with residential enduses enum flags properly (they use bit mapping)
    private final ImplicitEnduses implicitEnduses;

    /**
     * Default constructor
     */
    public Residential() {
        this.implicitEnduses = null;
    }

    /**
     * Specific constructor
     * 
     * @param implicitEnduses
     *            list of implicit enduses that are active in houses
     */
    public Residential(final ImplicitEnduses implicitEnduses) {
        this.implicitEnduses = implicitEnduses;
    }

    /**
     * Get the list of implicit enduses that are active in houses
     * 
     * @return the implicitEnduses
     */
    public ImplicitEnduses getImplicitEnduses() {
        return implicitEnduses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.implicitEnduses);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Residential other = (Residential) obj;
        return Objects.equals(this.implicitEnduses, other.implicitEnduses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "residential";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProperties() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        writeProperty(sb, "implicit_enduses", this.implicitEnduses);
    }

}
