/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.GldClock;
import gov.pnnl.prosser.api.gld.module.ModuleBuilder;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.pwr.lib.LineSpacing;
import gov.pnnl.prosser.api.pwr.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.pwr.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.pwr.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.pwr.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.pwr.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.pwr.obj.House;
import gov.pnnl.prosser.api.pwr.obj.Node;
import gov.pnnl.prosser.api.pwr.obj.OverheadLine;
import gov.pnnl.prosser.api.pwr.obj.Transformer;
import gov.pnnl.prosser.api.pwr.obj.TriplexLine;
import gov.pnnl.prosser.api.pwr.obj.TriplexMeter;
import gov.pnnl.prosser.api.pwr.obj.TriplexNode;
import gov.pnnl.prosser.api.pwr.obj.ZIPLoad;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nord229
 *
 */
public class ExperimentBuilder {

    public static GldClock clock() {
        return new GldClock();
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

    public static ClimateObject climate() {
        return new ClimateObject();
    }

    public static Recorder recorder() {
        return new Recorder();
    }

    public static OverheadLineConductor overheadLineConductor() {
        return new OverheadLineConductor();
    }

    public static OverheadLine overheadLine() {
        return new OverheadLine();
    }

    public static LineSpacing lineSpacing() {
        return new LineSpacing();
    }

    public static StandardLineConfiguration<OverheadLineConductor> overheadLineConfiguration() {
        return new StandardLineConfiguration<OverheadLineConductor>();
    }

    public static Node node() {
        return new Node();
    }

    public static TransformerConfiguration transformerConfiguration() {
        return new TransformerConfiguration();
    }

    public static Transformer transformer() {
        return new Transformer();
    }

    public static TriplexNode triplexNode() {
        return new TriplexNode();
    }

    public static TriplexLine triplexLine() {
        return new TriplexLine();
    }

    public static TriplexLineConductor triplexLineConductor() {
        return new TriplexLineConductor();
    }

    public static TriplexLineConfiguration triplexLineConfiguration() {
        return new TriplexLineConfiguration();
    }

    public static TriplexMeter triplexMeter() {
        return new TriplexMeter();
    }

    public static House house() {
        return new House();
    }

    public static ZIPLoad zipLoad() {
        return new ZIPLoad();
    }

}
