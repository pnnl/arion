/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;

/**
 * @author sund130
 *
 */
public class Regulator extends LinkObject {
    private RegulatorConfiguration configuration;
    
    public Regulator (final GldSimulator simulator) {
        super(simulator);
    }
    
    /**
     * @return the configuration
     */
    public RegulatorConfiguration getRegulatorConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setRegulatorConfiguration(RegulatorConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "regulator";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        
        writeProperty(sb, "configuration", this.configuration);
    }
}
