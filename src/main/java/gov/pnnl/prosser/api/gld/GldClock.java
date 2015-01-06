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
public class GldClock implements GLDSerializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String timezone;

    private final LocalDateTime startTime;

    private final LocalDateTime stopTime;

    public GldClock() {
        this.timezone = null;
        this.startTime = null;
        this.stopTime = null;
    }

    public GldClock(final GldClockBuilder clockBuilder) {
        this.timezone = clockBuilder.timezone;
        this.startTime = clockBuilder.startTime;
        this.stopTime = clockBuilder.stopTime;
    }

    /**
     * @return the timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return the stopTime
     */
    public LocalDateTime getStopTime() {
        return stopTime;
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
    public void writeGLDString(final StringBuilder sb) {
        sb.append("clock {\n");
        GLDUtils.writeProperty(sb, "timezone", this.timezone);
        GLDUtils.writeProperty(sb, "starttime", "'" + formatter.format(this.startTime) + "'");
        GLDUtils.writeProperty(sb, "stoptime", "'" + formatter.format(this.stopTime) + "'");
        sb.append("}\n");
    }

    public static class GldClockBuilder {

        protected String timezone;

        protected LocalDateTime startTime;

        protected LocalDateTime stopTime;

        public GldClockBuilder timezone(final String timezone) {
            this.timezone = timezone;
            return this;
        }

        public GldClockBuilder startTime(final LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public GldClockBuilder startTime(final String startTime) {
            this.startTime = LocalDateTime.from(formatter.parse(startTime));
            return this;
        }

        public GldClockBuilder stopTime(final LocalDateTime stopTime) {
            this.stopTime = stopTime;
            return this;
        }

        public GldClockBuilder stopTime(final String stopTime) {
            this.stopTime = LocalDateTime.from(formatter.parse(stopTime));
            return this;
        }

        public GldClock build() {
            return new GldClock(this);
        }
    }

}
