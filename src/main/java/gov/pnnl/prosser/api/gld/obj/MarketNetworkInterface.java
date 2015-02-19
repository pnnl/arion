/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * @author happ546
 *
 */
public class MarketNetworkInterface extends AbstractProsserObject {

    @Override
    public String getGldObjectType() {
        return "market_network_interface";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        // TODO Auto-generated method stub
    }

}
