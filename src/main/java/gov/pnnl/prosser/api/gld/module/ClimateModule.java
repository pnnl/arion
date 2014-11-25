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

    @Override
    public String getGLDObjectType() {
        return "climate";
    }

    @Override
    public boolean hasProperties() {
        return false;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
    }

}
