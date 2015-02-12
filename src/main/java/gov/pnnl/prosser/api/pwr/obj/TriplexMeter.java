/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.GldUtils;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    private Node parent;

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    @Override
    public String getGldObjectType() {
        return "triplex_meter";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
    }

}
