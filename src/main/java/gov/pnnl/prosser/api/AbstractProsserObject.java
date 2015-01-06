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
public abstract class AbstractProsserObject implements GLDSerializable {

    /**
     * Object name for referencing in files
     */
    private final String name;

    public AbstractProsserObject() {
        this.name = null;
    }

    public AbstractProsserObject(final String name) {
        this.name = name;
    }

    public <T extends AbstractProsserObject, Z extends AbstractBuilder<T, Z>> AbstractProsserObject(final AbstractBuilder<T, Z> builder) {
        this.name = builder.name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
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
        return Objects.equals(this.name, other.name);
    }

    public abstract String getGLDObjectType();

    @Override
    public void writeGLDString(final StringBuilder sb) {
        sb.append("object ").append(getGLDObjectType()).append(" {\n");
        GLDUtils.writeProperty(sb, "name", this.name);
        this.writeGLDProperties(sb);
        sb.append("}\n");
    }

    protected abstract void writeGLDProperties(final StringBuilder sb);

    public static abstract class AbstractBuilder<T extends AbstractProsserObject, Z extends AbstractBuilder<T, Z>> {
        protected String name;

        protected abstract Z self();

        public abstract T build();

        public Z name(final String name) {
            this.name = name;
            return self();
        }
    }

}
