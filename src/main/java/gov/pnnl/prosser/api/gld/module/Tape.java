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

    @Override
    public String getGLDObjectType() {
        return "tape";
    }

    @Override
    public boolean hasProperties() {
        return false;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
    }

}
