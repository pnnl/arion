/**
 *
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.GldSerializable;
import gov.pnnl.prosser.api.GldUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * GridLabD clock specification
 *
 * @author nord229
 */
public class GldClock implements GldSerializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String timezone;

    private LocalDateTime startTime;

    private LocalDateTime stopTime;

    /**
     * @return the timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * @param timezone
     *            the timezone to set
     */
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the stopTime
     */
    public LocalDateTime getStopTime() {
        return stopTime;
    }

    /**
     * @param stopTime
     *            the stopTime to set
     */
    public void setStopTime(final LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.startTime, this.stopTime, this.timezone);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GldClock other = (GldClock) obj;
        return Objects.equals(this.startTime, other.startTime)
                && Objects.equals(this.stopTime, other.stopTime)
                && Objects.equals(this.timezone, other.timezone);
    }

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("clock {\n");
        GldUtils.writeProperty(sb, "timezone", this.timezone);
        GldUtils.writeProperty(sb, "starttime", "'" + formatter.format(this.startTime) + "'");
        GldUtils.writeProperty(sb, "stoptime", "'" + formatter.format(this.stopTime) + "'");
        sb.append("}\n");
    }

}
