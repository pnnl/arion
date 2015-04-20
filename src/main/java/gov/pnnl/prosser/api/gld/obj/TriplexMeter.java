/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    /**
     * the parent node this meter is attached to
     */
    private Node parent;

    /**
     * Get the parent node this meter is attached to
     * 
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Set the parent node this meter is attached to
     * 
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_meter";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
    }

}