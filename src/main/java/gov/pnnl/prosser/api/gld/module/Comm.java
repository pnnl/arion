/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * @author nord229
 *
 */
public class Comm extends Module {
    @Override
    public String getGLDObjectType() {
        return "comm";
    }

    @Override
    public boolean hasProperties() {
        return false;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
    }
}
