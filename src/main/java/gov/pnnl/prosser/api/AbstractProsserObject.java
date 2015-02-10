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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
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
        final AbstractProsserObject other = (AbstractProsserObject) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.groupId, other.groupId);
    }

    public abstract String getGldObjectType();

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("object ").append(getGldObjectType()).append(" {\n");
        GldUtils.writeProperty(sb, "name", this.name);
        GldUtils.writeProperty(sb, "groupId", this.groupId);
        this.writeGldProperties(sb);
        sb.append("}\n");
    }

    protected abstract void writeGldProperties(final StringBuilder sb);

}
