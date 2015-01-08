/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * @author nord229
 *
 */
public class Market extends Module {

    @Override
    public String getGLDObjectType() {
        return "market";
    }

    @Override
    public boolean hasProperties() {
        return false;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
    }

}
