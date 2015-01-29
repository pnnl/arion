/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.pwr.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.pwr.lib.TriplexLineConfiguration;

/**
 * Triplex Line
 *
 * @author nord229
 */
public class TriplexLine extends Line<TriplexLineConductor, TriplexLineConfiguration> {

    @Override
    public String getGLDObjectType() {
        return "triplex_line";
    }

}
