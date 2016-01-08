/**
 * 
 */
package gov.pnnl.prosser.api.gld.module;

/**
 * GridLAB-D Connection module used for FNCS 2.0 communication and syncronization
 * 
 * @author fish334
 *
 */
public class Connection extends Module {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGLDObjectType() {
        return "connection";
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
