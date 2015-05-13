/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.GldSerializable;

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
    private Path file;

    /**
     * number of times the tape is to be repeated
     */
    private Integer loop;

    public PlayerObject(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureTapeModule();
    }

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
    public Path getFile() {
        return file;
    }

    /**
     * Set the source of the data
     * 
     * @param file
     *            the file to set
     */
    public void setFile(final Path file) {
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
        writeProperty(sb, "property", property);
        writeProperty(sb, "file", file.getFileName().toString());
        writeProperty(sb, "loop", loop);
    }

    @Override
    public void writeExternalFiles(Path path) throws IOException {
        if(file != null) {
            Files.copy(file, path.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
