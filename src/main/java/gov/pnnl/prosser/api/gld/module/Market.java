/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * GridLabD Market Module
 * 
 * @author nord229
 *
 */
public class Market extends Module {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "market";
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
