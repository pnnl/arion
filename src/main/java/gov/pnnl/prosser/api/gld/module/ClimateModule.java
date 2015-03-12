/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * GridLabD Climate module
 *
 * @author nord229
 */
public class ClimateModule extends Module {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "climate";
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
