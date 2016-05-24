/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

/**
 * Auction specific class declaration
 * 
 * @author nord229
 *
 */
public class AuctionClass extends AbstractGldClass {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldClassName() {
        return "auction_ccsi";
    }
}
