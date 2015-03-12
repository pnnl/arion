/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

/**
 * Polyphase Meter
 * 
 * @author nord229
 */
public class Meter extends Node {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "meter";
    }

}
