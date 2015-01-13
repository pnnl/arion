/**
 *
 */
package gov.pnnl.prosser.api;

/**
 * GridLabD specific serialization interface
 *
 * @author nord229
 */
public interface GldSerializable {

    /**
     * Write the string representation of this object to a StringBuilder
     * 
     * @param sb
     */
    public void writeGldString(StringBuilder sb);
}
