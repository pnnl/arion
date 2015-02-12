/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;

import java.util.Objects;

/**
 * GridLabD Recorder Object
 *
 * @author nord229
 */
public class Recorder extends AbstractProsserObject {

    private Long interval;

    private String file;

    private String property;

    private AbstractProsserObject parent;

    private Integer limit;

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

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit
     *            the limit to set
     */
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.file, this.interval, this.parent, this.property, this.limit);
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
                && Objects.equals(this.property, other.property)
                && Objects.equals(this.limit, other.limit);
    }

    @Override
    public String getGldObjectType() {
        return "recorder";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "interval", this.interval, "s");
        GldUtils.writeProperty(sb, "file", this.file);
        GldUtils.writeProperty(sb, "property", this.property);
        GldUtils.writeProperty(sb, "parent", this.parent);
        GldUtils.writeProperty(sb, "limit", this.limit);
    }

}
