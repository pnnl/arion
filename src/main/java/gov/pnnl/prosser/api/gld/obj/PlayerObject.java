/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Tape Player
 * 
 * @author nord229
 */
public class PlayerObject extends AbstractGldObject {

    /**
     * the target (parent) that is written to
     */
    private String property;

    /**
     * the source of the data
     */
    private String file;

    /**
     * number of times the tape is to be repeated
     */
    private Integer loop;

    /**
     * Get the target (parent) that is written to
     * 
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Set the target (parent) that is written to
     * 
     * @param property
     *            the property to set
     */
    public void setProperty(final String property) {
        this.property = property;
    }

    /**
     * Get the source of the data
     * 
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * Set the source of the data
     * 
     * @param file
     *            the file to set
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * Get the number of times the tape is to be repeated
     * 
     * @return the loop
     */
    public Integer getLoop() {
        return loop;
    }

    /**
     * Set the number of times the tape is to be repeated
     * 
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
