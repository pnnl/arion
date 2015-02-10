/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * @author happ546
 *
 */
public class ControllerNetworkInterface extends AbstractProsserObject {

    private MarketNetworkInterface marketNI;

    /**
     * @return the marketNI
     */
    public MarketNetworkInterface getMarketNI() {
        return marketNI;
    }

    /**
     * @param marketNI
     *            the marketNI to set
     */
    public void setMarketNI(final MarketNetworkInterface marketNI) {
        this.marketNI = marketNI;
    }

    @Override
    public String getGldObjectType() {
        return "controller_network_interface";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        // TODO Auto-generated method stub

    }

}
