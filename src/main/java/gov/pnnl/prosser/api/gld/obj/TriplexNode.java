/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Triplex Node
 *
 * @author nord229
 */
public class TriplexNode extends Node {

    public TriplexNode(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_node";
    }

}
