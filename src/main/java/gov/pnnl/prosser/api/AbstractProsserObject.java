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
    private final String name;

    // TODO find this groupid in the source of GLD to figure out its purpose
    /**
     * Object groupid referenced in files
     */
    private final String groupId;

    public AbstractProsserObject() {
        this.name = null;
        this.groupId = null;
    }

    public AbstractProsserObject(final String name) {
        this.name = name;
        this.groupId = null;
    }

    public <T extends AbstractProsserObject, Z extends AbstractBuilder<T, Z>> AbstractProsserObject(final AbstractBuilder<T, Z> builder) {
        this.name = builder.name;
        this.groupId = builder.groupId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
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

    public abstract String getGLDObjectType();

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("object ").append(getGLDObjectType()).append(" {\n");
        GldUtils.writeProperty(sb, "name", this.name);
        GldUtils.writeProperty(sb, "groupId", this.groupId);
        this.writeGLDProperties(sb);
        sb.append("}\n");
    }

    protected abstract void writeGLDProperties(final StringBuilder sb);

    public static abstract class AbstractBuilder<T extends AbstractProsserObject, Z extends AbstractBuilder<T, Z>> {
        protected String name;

        protected String groupId;

        protected abstract Z self();

        public abstract T build();

        public Z name(final String name) {
            this.name = name;
            return self();
        }

        public Z groupId(final String groupId) {
            this.groupId = groupId;
            return self();
        }
    }

}
