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

    public <T extends Node, Z extends AbstractBuilder<T, Z>> TriplexNode(final AbstractBuilder<T, Z> builder) {
        super(builder);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_node";
    }

    public static abstract class AbstractBuilder<T extends Node, Z extends AbstractBuilder<T, Z>> extends Node.AbstractBuilder<T, Z> {

    }

    public static class Builder extends AbstractBuilder<TriplexNode, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public TriplexNode build() {
            return new TriplexNode(this);
        }

    }

}
