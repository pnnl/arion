/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

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
        writeProperty(sb, "route", "\"function:controller/submit_bid_state -> auction/submit_bit_state\"");
        writeProperty(sb, "option", "\"transport:hostname localhost, port 5570\"");
        writeProperty(sb, "configure", this.simulatorName + ".txt");
    }
}
