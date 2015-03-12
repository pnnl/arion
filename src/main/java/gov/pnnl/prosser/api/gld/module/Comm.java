/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * GridLabD Communications module
 * 
 * @author nord229
 *
 */
public class Comm extends Module {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "comm";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProperties() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
    }
}
