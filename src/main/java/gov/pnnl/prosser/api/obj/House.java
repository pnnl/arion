/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.GLDUtils;

/**
 * House Object
 *
 * @author nord229
 */
public class House extends ResidentialEnduse {

    private Node parent;

    private ZIPLoad load;

    public House() {
    }

    public House(final String name, final Node parent, final ZIPLoad load) {
        super(name);
        this.parent = parent;
        this.load = load;
    }

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

    /**
     * @return the load
     */
    public ZIPLoad getLoad() {
        return load;
    }

    /**
     * @param load
     *            the load to set
     */
    public void setLoad(final ZIPLoad load) {
        this.load = load;
    }

    @Override
    public String getGLDObjectType() {
        return "house";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.appendProperty(sb, "parent", this.parent);
        load.writeGLDString(sb);
        // Handle special case since we need a semicolon here
        sb.insert(sb.length() - 1, ';');
    }

}
