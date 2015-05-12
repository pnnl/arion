/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Polyphase Meter
 * 
 * @author nord229
 */
public class Meter extends Node {

    public Meter(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "meter";
    }

}
