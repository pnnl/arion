/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GldUtils;

/**
 * House Object
 *
 * @author nord229
 */
public class House extends ResidentialEnduse {

    private final Node parent;

    private final ZIPLoad load;

    public House() {
        this.parent = null;
        this.load = null;
    }

    public House(final String name, final Node parent, final ZIPLoad load) {
        super(name);
        this.parent = parent;
        this.load = load;
    }

    public House(final Builder builder) {
        super(builder);
        this.parent = builder.parent;
        this.load = builder.load;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @return the load
     */
    public ZIPLoad getLoad() {
        return load;
    }

    @Override
    public String getGLDObjectType() {
        return "house";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "parent", this.parent);
        load.writeGldString(sb);
        // Handle special case since we need a semicolon here
        sb.insert(sb.length() - 1, ';');
    }

    public static class Builder extends ResidentialEnduse.AbstractBuilder<House, Builder> {

        private Node parent;

        private ZIPLoad load;

        public Builder parent(final Node parent) {
            this.parent = parent;
            return this;
        }

        public Builder load(final ZIPLoad load) {
            this.load = load;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public House build() {
            return new House(this);
        }

    }

}
