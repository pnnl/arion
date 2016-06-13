/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

/**
 * Marker for Residential Enduse objects
 *
 * @author nord229
 */
public abstract class ResidentialEnduse extends AbstractGldObject {

    /**
     * time skew applied to schedule operations involving this object
     */
    private Long scheduleSkew;

    public ResidentialEnduse(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureResidentialModule();
    }

    /**
     * Get the time skew applied to schedule operations involving this object
     * 
     * @return the scheduleSkew
     */
    public Long getScheduleSkew() {
        return scheduleSkew;
    }

    /**
     * Set the time skew applied to schedule operations involving this object
     * 
     * @param scheduleSkew
     *            the scheduleSkew to set
     */
    public void setScheduleSkew(final Long scheduleSkew) {
        this.scheduleSkew = scheduleSkew;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "schedule_skew", this.scheduleSkew);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.scheduleSkew);
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
        final ResidentialEnduse other = (ResidentialEnduse) obj;
        return Objects.equals(this.scheduleSkew, other.scheduleSkew);
    }

}
