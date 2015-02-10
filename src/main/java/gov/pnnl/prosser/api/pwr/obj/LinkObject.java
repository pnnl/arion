/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.GldUtils;

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
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "from", this.from);
        GldUtils.writeProperty(sb, "to", this.to);
    }

}
