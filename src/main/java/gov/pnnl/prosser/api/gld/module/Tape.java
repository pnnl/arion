/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * GridLabD Tape Module
 *
 * @author nord229
 */
public class Tape extends Module {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "tape";
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
