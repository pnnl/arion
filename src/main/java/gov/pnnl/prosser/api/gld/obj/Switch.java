/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;

import java.util.Objects;

public class Switch extends LinkObject {
	private SwitchStatus status;
	
	/**
	 * @param simulator
	 */
	public Switch(GldSimulator simulator) {
		super(simulator);
	}

	/**
	 * @return the status
	 */
	public SwitchStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(SwitchStatus status) {
		this.status = status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getGldObjectType() {
		return "switch";
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        
        writeProperty(sb, "status", this.status);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
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
        final Switch other = (Switch) obj;
        return Objects.equals(this.status, other.status);
    }
}
