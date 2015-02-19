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

    private String tmyFile;

    private CsvReader reader;

    /**
     * @return the tmyFile
     */
    public String getTmyFile() {
        return tmyFile;
    }

    /**
     * @param tmyFile
     *            the tmyFile to set
     */
    public void setTmyFile(final String tmyFile) {
        this.tmyFile = tmyFile;
    }

    /**
     * @return the reader
     */
    public CsvReader getReader() {
        return reader;
    }

    /**
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
        this.reader = this.getSimulator().csvReader(name);
        this.reader.setFilename(this.tmyFile);
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

    @Override
    public String getGldObjectType() {
        return "climate";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "tmyfile", this.tmyFile);
        GldUtils.writeProperty(sb, "reader", reader);
    }

}
