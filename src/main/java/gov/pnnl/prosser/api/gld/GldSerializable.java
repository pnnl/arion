/**
 *
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.sql.SqlFile;

import java.io.IOException;
import java.nio.file.Path;

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
     *            StringBuilder to use when writing
     */
    public void writeGldString(StringBuilder sb);
    
    /**
     * Write the referenced files in this object to the specified path
     * @param path the output path
     */
    public default void writeExternalFiles(Path path) throws IOException {
    }
    
    public default void createSqlObjects(SqlFile file) {
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final String propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Long propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Integer propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final AbstractGldObject propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.getName(), null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Enum<?> propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.name(), null);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final String propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Long propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Integer propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public default void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public default void writePropName(final StringBuilder sb, final String propName) {
        sb.append('\t').append(propName).append(' ');
    }

    public default void writePropUnitsAndTrailer(final StringBuilder sb, final String propUnits) {
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }
    
}
