/**
 *
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.GLDSerializable;
import gov.pnnl.prosser.api.GLDUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * GridLabD clock specification
 *
 * @author nord229
 */
public class Clock implements GLDSerializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static class ClockBuilder {
        protected final Clock clock = new Clock();

        public ClockBuilder timezone(final String timezone) {
            this.clock.setTimezone(timezone);
            return this;
        }

        public ClockBuilder startTime(final LocalDateTime startTime) {
            this.clock.setStartTime(startTime);
            return this;
        }

        public ClockBuilder startTime(final String startTime) {
            this.clock.setStartTime(LocalDateTime.from(formatter.parse(startTime)));
            return this;
        }

        public ClockBuilder stopTime(final LocalDateTime stopTime) {
            this.clock.setStopTime(stopTime);
            return this;
        }

        public ClockBuilder stopTime(final String stopTime) {
            this.clock.setStartTime(LocalDateTime.from(formatter.parse(stopTime)));
            return this;
        }

        public Clock build() {
            return this.clock;
        }
    }

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
        final Clock other = (Clock) obj;
        return Objects.equals(this.startTime, other.startTime)
                && Objects.equals(this.stopTime, other.stopTime)
                && Objects.equals(this.timezone, other.timezone);
    }

    @Override
    public void writeGLDString(final StringBuilder sb) {
        sb.append("clock {\n");
        GLDUtils.writeProperty(sb, "timezone", this.timezone);
        GLDUtils.writeProperty(sb, "starttime", "'" + formatter.format(this.startTime) + "'");
        GLDUtils.writeProperty(sb, "stoptime", "'" + formatter.format(this.stopTime) + "'");
        sb.append("}\n");
    }

}
