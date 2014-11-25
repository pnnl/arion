/**
 *
 */
package gov.pnnl.prosser.api;

import java.text.DecimalFormat;

import org.apache.commons.math3.complex.Complex;

/**
 * @author nord229
 *
 */
public class GLDUtils {

    public static final DecimalFormat complexFormat = new DecimalFormat("0.000#");

    public static final DecimalFormat doubleFormat = new DecimalFormat("#");

    static {
        complexFormat.setMaximumFractionDigits(10);
        doubleFormat.setMaximumFractionDigits(10);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final String propValue) {
        appendProperty(sb, propName, propValue, null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Double propValue) {
        appendProperty(sb, propName, propValue, null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Long propValue) {
        appendProperty(sb, propName, propValue, null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Complex propValue) {
        appendProperty(sb, propName, propValue, null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final AbstractProsserObject propValue) {
        if (propValue == null) {
            return;
        }
        appendProperty(sb, propName, propValue.getName(), null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Enum<?> propValue) {
        if (propValue == null) {
            return;
        }
        appendProperty(sb, propName, propValue.name(), null);
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final String propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        sb.append('\t').append(propName).append(' ').append(propValue);
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Double propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        sb.append('\t').append(propName).append(' ').append(doubleFormat.format(propValue));
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Long propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        sb.append('\t').append(propName).append(' ').append(propValue);
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }

    public static void appendProperty(final StringBuilder sb, final String propName, final Complex propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        sb.append('\t').append(propName).append(' ');
        sb.append(complexFormat.format(propValue.getReal()));
        if (propValue.getImaginary() >= 0) {
            sb.append('+');
        }
        sb.append(complexFormat.format(propValue.getImaginary()));
        sb.append('j');
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }
}
