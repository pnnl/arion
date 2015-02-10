/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;

/**
 * @author nord229
 *
 */
public class AuctionObject extends AbstractProsserObject {

    @Override
    public String getGldObjectType() {
        return "auction";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        // TODO Auto-generated method stub

    }

}
