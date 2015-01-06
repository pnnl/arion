/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GLDUtils;

import java.util.Objects;

/**
 * GridLabD Recorder Object
 *
 * @author nord229
 */
public class Recorder extends AbstractProsserObject {

    private final Long interval;

    private final String file;

    private final String property;

    private final AbstractProsserObject parent;

    public Recorder() {
        this.interval = null;
        this.file = null;
        this.property = null;
        this.parent = null;
    }

    public Recorder(final Long interval, final String file, final String property, final AbstractProsserObject parent) {
        this.interval = interval;
        this.file = file;
        this.property = property;
        this.parent = parent;
    }

    public Recorder(final Builder builder) {
        super(builder);
        this.interval = builder.interval;
        this.file = builder.file;
        this.property = builder.property;
        this.parent = builder.parent;
    }

    /**
     * @return the interval
     */
    public Long getInterval() {
        return interval;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @return the parent
     */
    public AbstractProsserObject getParent() {
        return parent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.file, this.interval, this.parent, this.property);
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
        final Recorder other = (Recorder) obj;
        return Objects.equals(this.file, other.file)
                && Objects.equals(this.interval, other.interval)
                && Objects.equals(this.parent, other.parent)
                && Objects.equals(this.property, other.property);
    }

    @Override
    public String getGLDObjectType() {
        return "recorder";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "interval", this.interval, "s");
        GLDUtils.writeProperty(sb, "file", this.file);
        GLDUtils.writeProperty(sb, "property", this.property);
        GLDUtils.writeProperty(sb, "parent", this.parent);
    }

    public static class Builder extends AbstractProsserObject.AbstractBuilder<Recorder, Builder> {

        private Long interval;

        private String file;

        private String property;

        private AbstractProsserObject parent;

        public Builder interval(final Long interval) {
            this.interval = interval;
            return this;
        }

        public Builder file(final String file) {
            this.file = file;
            return this;
        }

        public Builder property(final String property) {
            this.property = property;
            return this;
        }

        public Builder parent(final AbstractProsserObject parent) {
            this.parent = parent;
            return this;
        }

        @Override
        public Recorder build() {
            return new Recorder(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
