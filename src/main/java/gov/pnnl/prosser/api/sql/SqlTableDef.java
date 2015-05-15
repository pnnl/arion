/**
 * 
 */
package gov.pnnl.prosser.api.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author nord229
 *
 */
public class SqlTableDef {
    private String name;

    private final List<SqlColumnDef> sqlColumnDefs = new ArrayList<>();
    
    public SqlTableDef(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sqlColumnDefs
     */
    public List<SqlColumnDef> getSqlColumnDefs() {
        return sqlColumnDefs;
    }

    public SqlColumnDef sqlColumnDef(final String columnName) {
        final SqlColumnDef sqlColumnDef = new SqlColumnDef(columnName);
        this.sqlColumnDefs.add(sqlColumnDef);
        return sqlColumnDef;
    }
}