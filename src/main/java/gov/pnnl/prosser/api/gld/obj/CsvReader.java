/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;

/**
 * @author nord229
 *
 */
public class CsvReader extends AbstractProsserObject {

    private String filename;

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     *            the filename to set
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    @Override
    public String getGldObjectType() {
        return "csv_reader";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        // TODO Auto-generated method stub
        GldUtils.writeProperty(sb, "filename", filename);
    }

}
