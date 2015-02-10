/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

/**
 * Triplex Meter
 *
 * @author nord229
 */
public class TriplexMeter extends TriplexNode {

    @Override
    public String getGldObjectType() {
        return "triplex_meter";
    }

}
