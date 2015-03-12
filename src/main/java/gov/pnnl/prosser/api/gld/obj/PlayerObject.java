/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * @author nord229
 *
 */
public class PlayerObject extends AbstractGldObject {

    private String property;

    private String file;

    private Integer loop;

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
     * @return the loop
     */
    public Integer getLoop() {
        return loop;
    }

    /**
     * @param loop
     *            the loop to set
     */
    public void setLoop(final Integer loop) {
        this.loop = loop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "player";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "property", property);
        GldUtils.writeProperty(sb, "file", file);
        GldUtils.writeProperty(sb, "loop", loop);
    }

}
