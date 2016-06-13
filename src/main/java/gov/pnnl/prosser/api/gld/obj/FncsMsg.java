/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

/**
 * fncs_msg specific class definition
 * 
 * @author fish334
 *
 */
public class FncsMsg extends AbstractGldObject {
    // class properties
    private final String simulatorName;
    
    // Constructor
    public FncsMsg(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureConnectionModule();
        this.simulatorName = simulator.getName();
    }
    
	@Override
    protected String getGldObjectType() {
        return "fncs_msg";
    }

    @Override
    protected void writeGldProperties(StringBuilder sb) {
    	writeProperty(sb, "route", "\"function:controller_ccsi/submit_bid_state_ccsi -> auction_ccsi/submit_bid_state_ccsi\"");
        writeProperty(sb, "option", "\"transport:hostname localhost, port 5570\"");
        writeProperty(sb, "configure", this.simulatorName + ".txt");
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.simulatorName);
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
        final FncsMsg other = (FncsMsg) obj;
        return Objects.equals(this.simulatorName, other.simulatorName);
    }
}
