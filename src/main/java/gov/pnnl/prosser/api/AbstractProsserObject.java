/**
 *
 */
package gov.pnnl.prosser.api;

import java.util.Objects;

/**
 * Object to encompass shared properties for all objects
 *
 * @author nord229
 */
public abstract class AbstractProsserObject implements GldSerializable {

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
    private GldSimulator simulator;

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

    /**
     * Get the Simulator reference
     * 
     * @return the simulator
     */
    public GldSimulator getSimulator() {
        return simulator;
    }

    /**
     * Set the Simulator reference
     * 
     * @param simulator
     *            the simulator to set
     */
    public void setSimulator(final GldSimulator simulator) {
        this.simulator = simulator;
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
        final AbstractProsserObject other = (AbstractProsserObject) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.groupId, other.groupId);
    }

    /**
     * The Object type to use when referencing in GLM files
     * 
     * @return the object type string
     */
    public abstract String getGldObjectType();

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("object ").append(getGldObjectType()).append(" {\n");
        GldUtils.writeProperty(sb, "name", this.name);
        GldUtils.writeProperty(sb, "groupid", this.groupId);
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

}
