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
    private Node from;

    /**
     * to_node - load node
     */
    private Node to;

    public LinkObject() {
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

    /**
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(final Node from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    /**
     * @param to
     *            the to to set
     */
    public void setTo(final Node to) {
        this.to = to;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        super.writeGLDProperties(sb);
        GLDUtils.appendProperty(sb, "from", this.from);
        GLDUtils.appendProperty(sb, "to", this.to);
    }

}
