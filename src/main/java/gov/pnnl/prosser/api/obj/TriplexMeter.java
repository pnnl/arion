/**
 *
 */
package gov.pnnl.prosser.api.obj;

import java.util.EnumSet;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    public TriplexMeter() {
    }

    public TriplexMeter(final String name, final EnumSet<PhaseCode> phases, final double nominalVoltage) {
        super(name, phases, nominalVoltage);
    }

    @Override
    public String getGLDObjectType() {
        return "triplex_meter";
    }

}
