/**
 *
 */
package gov.pnnl.prosser.api.obj;

import gov.pnnl.prosser.api.lib.TriplexLineConfiguration;

import java.util.EnumSet;

/**
 * Triplex Line
 *
 * @author nord229
 */
public class TriplexLine extends Line<TriplexLineConfiguration> {

    public TriplexLine() {
    }

    public TriplexLine(final EnumSet<PhaseCode> phases, final Node from, final Node to, final double length,
            final TriplexLineConfiguration configuration) {
        super(phases, from, to, length, configuration);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_line";
    }

}
