/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;

/**
 * Marker for Residential Enduse objects
 *
 * @author nord229
 */
public abstract class ResidentialEnduse extends AbstractProsserObject {

    private Long scheduleSkew;

    /**
     * @return the scheduleSkew
     */
    public Long getScheduleSkew() {
        return scheduleSkew;
    }

    /**
     * @param scheduleSkew
     *            the scheduleSkew to set
     */
    public void setScheduleSkew(final Long scheduleSkew) {
        this.scheduleSkew = scheduleSkew;
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "schedule_skew", this.scheduleSkew);
    }

}
