/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;

/**
 * Triplex Line
 *
 * @author nord229
 */
public class TriplexLine extends Line<TriplexLineConductor, TriplexLineConfiguration> {

    @Override
    public String getGldObjectType() {
        return "triplex_line";
    }

}
