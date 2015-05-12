/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

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

}
