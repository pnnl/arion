/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.GldUtils;

import java.util.Objects;

/**
 * GridLabD Residential module
 *
 * @author nord229
 */
public class Residential extends Module {

    private final String implicitEnduses;

    public Residential() {
        this.implicitEnduses = null;
    }

    public Residential(final String implicitEnduses) {
        this.implicitEnduses = implicitEnduses;
    }

    /**
     * @return the implicitEnduses
     */
    public String getImplicitEnduses() {
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

    @Override
    public String getGLDObjectType() {
        return "residential";
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "implicit_enduses", this.implicitEnduses);
    }

}
