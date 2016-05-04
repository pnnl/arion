/**
 *
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.sql.SqlFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
    
    private Recorder recorder;
    
    /**
     * the target (parent) that is referenced
     */
    private AbstractGldObject parent;
    
    private final List<AbstractGldObject> children = new ArrayList<>();

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
        if(this.recorder != null) {
            this.recorder.setName(this.name + "_recorder");
        }
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

    /**
     * Get the Recorder
     * 
     * @return the recorder
     */
    public Recorder getRecorder() {
        return recorder;
    }

    /**
     * Create and set the recorder on this object
     * 
     * @return the recorder
     */
    public Recorder recorder(String... properties) {
        this.recorder = new Recorder(this.simulator);
        this.recorder.properties(properties);
        this.recorder.setName(this.name + "_recorder");
        return recorder;
    }
    
    /**
     * Get the target (parent) that is referenced
     * 
     * @return the parent
     */
    public AbstractGldObject getParent() {
        return parent;
    }

    /**
     * Set the target (parent) that is referenced
     * 
     * @param parent
     *            the parent to set
     */
    public void setParent(final AbstractGldObject parent) {
        this.parent = parent;
        this.parent.addChild(this);
    }
    
    public void addChild(final AbstractGldObject child) {
        this.children.add(child);
    }
    
    public List<AbstractGldObject> getChildren() {
        return this.children;
    }
    
    @Override
    public void createSqlObjects(SqlFile file) {
        if (this.recorder != null) {
            this.recorder.createSqlObjects(file);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.groupId, this.recorder, this.parent);
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
                && Objects.equals(this.groupId, other.groupId)
                && Objects.equals(this.recorder, other.recorder)
                && Objects.equals(this.parent, other.parent);
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
        writeProperty(sb, "parent", this.parent);
        this.writeGldProperties(sb);
        if (recorder != null) {
            recorder.writeGldString(sb);
            // Handle special case since we need a semicolon here
            sb.insert(sb.length() - 1, ';');
        }
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
