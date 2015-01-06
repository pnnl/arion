/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

import java.util.EnumSet;

/**
 * Generic Link Object
 *
 * @author nord229
 */
public abstract class LinkObject extends PowerflowObject {

    /**
     * from_node - source node
     */
    private final Node from;

    /**
     * to_node - load node
     */
    private final Node to;

    public LinkObject() {
        this.from = null;
        this.to = null;
    }

    public LinkObject(final EnumSet<PhaseCode> phases, final Node from, final Node to) {
        super(phases);
        this.from = from;
        this.to = to;
    }

    public LinkObject(final String name, final EnumSet<PhaseCode> phases, final Node from, final Node to) {
        super(name, phases);
        this.from = from;
        this.to = to;
    }

    public <T extends LinkObject, Z extends AbstractBuilder<T, Z>> LinkObject(final AbstractBuilder<T, Z> builder) {
        super(builder);
        this.from = builder.from;
        this.to = builder.to;
    }

    /**
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.writeProperty(sb, "from", this.from);
        GLDUtils.writeProperty(sb, "to", this.to);
    }

    public static abstract class AbstractBuilder<T extends LinkObject, Z extends AbstractBuilder<T, Z>> extends PowerflowObject.AbstractBuilder<T, Z> {
        private Node from;

        private Node to;

        public Z from(final Node from) {
            this.from = from;
            return self();
        }

        public Z to(final Node to) {
            this.to = to;
            return self();
        }
    }

}
