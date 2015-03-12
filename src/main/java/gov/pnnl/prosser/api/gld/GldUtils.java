/**
 *
 */
package gov.pnnl.prosser.api.gld;

import java.text.DecimalFormat;

import org.apache.commons.math3.complex.Complex;

/**
 * Common utilities for writing GridLabD files
 *
 * @author nord229
 *
 */
public abstract class GldUtils {

    private static final DecimalFormat complexFormat;

    private static final DecimalFormat doubleFormat;

    static {
        complexFormat = new DecimalFormat("0.000#");
        complexFormat.setMaximumFractionDigits(10);
        doubleFormat = new DecimalFormat("0.#");
        doubleFormat.setMaximumFractionDigits(10);
    }

    public static void writeSetting(final StringBuilder sb, final String key, final String value) {
        sb.append("#set ");
        sb.append(key);
        sb.append('=');
        sb.append(value);
        sb.append(";\n");
    }

    public static void writeInclude(final StringBuilder sb, final String include) {
        sb.append("#include \"");
        sb.append(include);
        sb.append("\";\n");
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final String propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Double propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Long propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Integer propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Complex propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final AbstractGldObject propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.getName(), null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Enum<?> propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.name(), null);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final String propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Double propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(doubleFormat.format(propValue));

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Long propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Integer propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    public static void writeProperty(final StringBuilder sb, final String propName, final Complex propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        if (propValue.getReal() >= 0) {
            sb.append('+');
        }
        sb.append(complexFormat.format(propValue.getReal()));
        if (propValue.getImaginary() >= 0) {
            sb.append('+');
        }
        sb.append(complexFormat.format(propValue.getImaginary()));
        sb.append('j');

        writePropUnitsAndTrailer(sb, propUnits);
    }

    private static void writePropName(final StringBuilder sb, final String propName) {
        sb.append('\t').append(propName).append(' ');
    }

    private static void writePropUnitsAndTrailer(final StringBuilder sb, final String propUnits) {
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }
}
