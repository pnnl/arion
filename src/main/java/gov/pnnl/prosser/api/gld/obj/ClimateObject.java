/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * GridLabD Climate Object
 *
 * @author nord229
 */
public class ClimateObject extends AbstractGldObject {

    /**
     * the TMY file name
     */
    private Path tmyFile;

    /**
     * the file reader to use when loading data, can be null
     */
    private CsvReader reader;

    public ClimateObject(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureClimateModule();
    }

    /**
     * Get the TMY file name
     * 
     * @return the tmyFile
     */
    public Path getTmyFile() {
        return tmyFile;
    }

    /**
     * Set the TMY file name
     * 
     * @param tmyFile
     *            the tmyFile to set
     */
    public void setTmyFile(final Path tmyFile) {
        this.tmyFile = tmyFile;
    }

    /**
     * Get the file reader to use when loading data, can be null
     * 
     * @return the reader
     */
    public CsvReader getReader() {
        return reader;
    }

    /**
     * Set the file reader to use when loading data, can be null
     * 
     * @param reader
     *            the reader to set
     */
    public void setReader(final CsvReader reader) {
        this.reader = reader;
    }

    /**
     * Sets up a CSV reader for this object, will take the current tmy file for the file in the reader
     *
     * @param name
     *            the CSV reader name
     * @return the csv reader
     */
    public CsvReader addCsvReader(final String name) {
        this.reader = this.simulator.csvReader(name);
        this.reader.setFilename(this.tmyFile.getFileName().toString());
        return this.reader;
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "climate";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "tmyfile", this.tmyFile.getFileName().toString());
        writeProperty(sb, "reader", reader);
    }

    @Override
    public void writeExternalFiles(Path path) throws IOException {
        if (tmyFile != null) {
            Files.copy(tmyFile, path.resolve(tmyFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
