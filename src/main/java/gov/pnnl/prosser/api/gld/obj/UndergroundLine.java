/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;
import gov.pnnl.prosser.api.gld.lib.UndergroundLineConductor;

/**
 * Underground Line Object
 *
 * @author sund130
 */
public class UndergroundLine extends Line<UndergroundLineConductor, LineConfiguration<UndergroundLineConductor>> {

    public UndergroundLine(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "underground_line";
    }

}
