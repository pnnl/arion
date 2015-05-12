/**
 *
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.GldSimulator;

import java.text.DecimalFormat;
import java.util.Objects;

import org.apache.commons.math3.complex.Complex;

/**
 * Object to encompass shared properties for all GridLabD objects
 *
 * @author nord229
 */
public abstract class AbstractGldObject implements GldSerializable {
    
    public static final DecimalFormat complexFormat;

    public static final DecimalFormat doubleFormat;

    static {
        complexFormat = new DecimalFormat("0.000#");
        complexFormat.setMaximumFractionDigits(10);
        doubleFormat = new DecimalFormat("0.#");
        doubleFormat.setMaximumFractionDigits(3);
    }

    /**
     * Object name for referencing in files
     */
    private String name;

    // TODO find this groupid in the source of GLD to figure out its purpose
    /**
     * Object groupid referenced in files
     */
    private String groupId;

    /**
     * Simulator reference
     */
    protected final GldSimulator simulator;
    
    public AbstractGldObject(final GldSimulator simulator) {
    	this.simulator = simulator;
    }

    /**
     * Get the object name for referencing in files
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the object name for referencing in files
     * 
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the object groupid referenced in files
     * 
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Set the object groupid referenced in files
     * 
     * @param groupId
     *            the groupId to set
     */
    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.groupId);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractGldObject other = (AbstractGldObject) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.groupId, other.groupId);
    }

    /**
     * The Object type to use when referencing in GLM files
     * 
     * @return the object type string
     */
    protected abstract String getGldObjectType();

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("object ").append(getGldObjectType()).append(" {\n");
        writeProperty(sb, "name", this.name);
        writeProperty(sb, "groupid", this.groupId);
        this.writeGldProperties(sb);
        sb.append("}\n");
    }

    /**
     * Should write the properties of this object to the StringBuilder
     * 
     * @param sb
     *            the string builder to use
     */
    protected abstract void writeGldProperties(final StringBuilder sb);
    
    

    protected void writeProperty(final StringBuilder sb, final String propName, final Double propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    protected void writeProperty(final StringBuilder sb, final String propName, final Complex propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    protected void writeProperty(final StringBuilder sb, final String propName, final Double propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(doubleFormat.format(propValue));

        writePropUnitsAndTrailer(sb, propUnits);
    }

    protected void writeProperty(final StringBuilder sb, final String propName, final Complex propValue, final String propUnits) {
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

}
