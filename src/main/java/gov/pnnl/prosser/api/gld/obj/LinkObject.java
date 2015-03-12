/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Generic Link Object
 *
 * @author nord229
 */
public abstract class LinkObject extends PowerflowObject {

    /**
     * from node - source node
     */
    private Node from;

    /**
     * to_node - load node
     */
    private Node to;

    /**
     * Get the from node - source node
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * Set the Get the from node - source node
     * @param from
     *            the from to set
     */
    public void setFrom(final Node from) {
        this.from = from;
    }

    /**
     * Get the to node - load node
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    /**
     * Set the to node - load node
     * @param to
     *            the to to set
     */
    public void setTo(final Node to) {
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "from", this.from);
        GldUtils.writeProperty(sb, "to", this.to);
    }

}
