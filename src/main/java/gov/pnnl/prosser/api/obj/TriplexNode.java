/**
 *
 */
package gov.pnnl.prosser.api.obj;

import java.util.EnumSet;

/**
 * Triplex Node
 *
 * @author nord229
 */
public class TriplexNode extends Node {

    public TriplexNode() {
    }

    public TriplexNode(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_node";
    }

}
