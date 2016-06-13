/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

/**
 * GridLabD Climate CSV Reader
 * 
 * @author nord229
 *
 */
public class CsvReader extends AbstractGldObject {

    /**
     * the csv filename
     */
    private String filename;

    public CsvReader(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the csv filename
     * 
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the csv filename
     * 
     * @param filename
     *            the filename to set
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "csv_reader";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "filename", filename);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.filename);
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
        final CsvReader other = (CsvReader) obj;
        return Objects.equals(this.filename, other.filename);
    }

}
