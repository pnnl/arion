/**
 * 
 */
package gov.pnnl.prosser.api.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nord229
 *
 */
public class SqlFile {

    private final String databaseName;

    private final List<SqlTableDef> sqlTableDefs = new ArrayList<>();

    public SqlFile(final String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @return the sqlTableDefs
     */
    public List<SqlTableDef> getSqlTableDefs() {
        return sqlTableDefs;
    }
    
    public SqlTableDef sqlTableDef(final String tableName) {
        final SqlTableDef tableDef = new SqlTableDef(tableName);
        this.sqlTableDefs.add(tableDef);
        return tableDef;
    }

}
