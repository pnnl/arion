/**
 * 
 */
package gov.pnnl.prosser.api.sql;

/**
 * 
 * @author nord229
 *
 */
public class SqlColumnDef {
    private final String name;

    private String type = "VARCHAR(50)";
    
    public SqlColumnDef(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}