/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.Clock.ClockBuilder;
import gov.pnnl.prosser.api.gld.module.ModuleBuilder;
import gov.pnnl.prosser.api.gld.obj.ClimateObject.ClimateBuilder;
import gov.pnnl.prosser.api.gld.obj.Recorder.RecorderBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nord229
 *
 */
public class ExperimentBuilder {

    public static ClockBuilder clock() {
        return new ClockBuilder();
    }

    public static ModuleBuilder module() {
        return new ModuleBuilder();
    }

    public static GLDSettingsBuilder gldSettings() {
        return new GLDSettingsBuilder();
    }

    public static class GLDSettingsBuilder {
        protected final Map<String, String> map = new HashMap<>();

        public GLDSettingsBuilder put(final String key, final String value) {
            map.put(key, value);
            return this;
        }

        public Map<String, String> build() {
            return map;
        }
    }

    public static ClimateBuilder climate() {
        return new ClimateBuilder();
    }

    public static RecorderBuilder recorder() {
        return new RecorderBuilder();
    }

}
