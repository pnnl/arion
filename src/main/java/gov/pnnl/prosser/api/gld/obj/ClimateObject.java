/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;

import java.util.Objects;

/**
 * GridLabD Climate Object
 *
 * @author nord229
 */
public class ClimateObject extends AbstractProsserObject {

    private final String tmyFile;

    public ClimateObject() {
        this.tmyFile = null;
    }

    public ClimateObject(final String name, final String tmyFile) {
        super(name);
        this.tmyFile = tmyFile;
    }

    public ClimateObject(final Builder builder) {
        super(builder);
        this.tmyFile = builder.tmyFile;
    }

    /**
     * @return the tmyFile
     */
    public String getTmyFile() {
        return tmyFile;
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
        GldUtils.writeProperty(sb, "tmyfile", this.tmyFile);
    }

    public static class Builder extends AbstractProsserObject.AbstractBuilder<ClimateObject, Builder> {

        private String tmyFile;

        public Builder tmyFile(final String tmyFile) {
            this.tmyFile = tmyFile;
            return this;
        }

        @Override
        public ClimateObject build() {
            return new ClimateObject(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
