/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

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

    public LinkObject(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the from node - source node
     * 
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * Set the Get the from node - source node
     * 
     * @param from
     *            the from to set
     */
    public void setFrom(final Node from) {
        this.from = from;
        this.from.addChild(this);
    }

    /**
     * Get the to node - load node
     * 
     * @return the to
     */
    public Node getTo() {
        return to;
    }

    /**
     * Set the to node - load node
     * 
     * @param to
     *            the to to set
     */
    public void setTo(final Node to) {
        this.to = to;
        this.addChild(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "from", this.from);
        writeProperty(sb, "to", this.to);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinkObject other = (LinkObject) obj;
        return Objects.equals(this.from, other.from)
                && Objects.equals(this.to, other.to);
    }

}
