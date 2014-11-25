/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GLDUtils;

import java.util.Objects;

/**
 * @author nord229
 *
 */
public class Recorder extends AbstractProsserObject {

    private Long interval;

    private String file;

    private String property;

    private AbstractProsserObject parent;

    public Recorder() {
    }

    public Recorder(final Long interval, final String file, final String property, final AbstractProsserObject parent) {
        this.interval = interval;
        this.file = file;
        this.property = property;
        this.parent = parent;
    }

    /**
     * @return the interval
     */
    public Long getInterval() {
        return interval;
    }

    /**
     * @param interval
     *            the interval to set
     */
    public void setInterval(final Long interval) {
        this.interval = interval;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property
     *            the property to set
     */
    public void setProperty(final String property) {
        this.property = property;
    }

    /**
     * @return the parent
     */
    public AbstractProsserObject getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final AbstractProsserObject parent) {
        this.parent = parent;
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
        GLDUtils.appendProperty(sb, "interval", this.interval, "s");
        GLDUtils.appendProperty(sb, "file", this.file);
        GLDUtils.appendProperty(sb, "property", this.property);
        GLDUtils.appendProperty(sb, "parent", this.parent);
    }

}
