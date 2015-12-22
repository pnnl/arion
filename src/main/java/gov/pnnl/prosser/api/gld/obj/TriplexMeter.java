/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    public TriplexMeter(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_meter";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
    }

}
