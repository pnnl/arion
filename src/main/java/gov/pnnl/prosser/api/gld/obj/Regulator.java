/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), configuration);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Regulator other = (Regulator) obj;
        return Objects.equals(this.configuration, other.configuration);
    }
}
