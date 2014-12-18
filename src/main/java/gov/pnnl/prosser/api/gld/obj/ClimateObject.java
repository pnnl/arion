/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GLDUtils;

import java.util.Objects;

/**
 * GridLabD Climate Object
 *
 * @author nord229
 */
public class ClimateObject extends AbstractProsserObject {

    public static class ClimateBuilder {

        protected final ClimateObject climateObject = new ClimateObject();

        public ClimateBuilder name(final String name) {
            this.climateObject.setName(name);
            return this;
        }

        public ClimateBuilder tmyFile(final String tmyFile) {
            this.climateObject.setTmyFile(tmyFile);
            return this;
        }

        public ClimateObject build() {
            return this.climateObject;
        }
    }

    private String tmyFile;

    public ClimateObject() {
    }

    public ClimateObject(final String name, final String tmyFile) {
        super(name);
        this.tmyFile = tmyFile;
    }

    /**
     * @return the tmyFile
     */
    public String getTmyFile() {
        return tmyFile;
    }

    /**
     * @param tmyFile
     *            the tmyFile to set
     */
    public void setTmyFile(final String tmyFile) {
        this.tmyFile = tmyFile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.tmyFile);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClimateObject other = (ClimateObject) obj;
        return Objects.equals(this.tmyFile, other.tmyFile);
    }

    @Override
    public String getGLDObjectType() {
        return "climate";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "tmyfile", this.tmyFile);
    }

}
