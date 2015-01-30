/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * @author happ546
 *
 */
public class MarketNetworkInterface extends AbstractProsserObject {

    @Override
    public String getGLDObjectType() {
        return "market_network_interface";
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        // TODO Auto-generated method stub
    }

}
