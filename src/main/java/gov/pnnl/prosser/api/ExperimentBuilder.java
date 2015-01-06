/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.GldClock.GldClockBuilder;
import gov.pnnl.prosser.api.gld.module.ModuleBuilder;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.lib.LineSpacing;
import gov.pnnl.prosser.api.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.obj.House;
import gov.pnnl.prosser.api.obj.Node;
import gov.pnnl.prosser.api.obj.OverheadLine;
import gov.pnnl.prosser.api.obj.Transformer;
import gov.pnnl.prosser.api.obj.TriplexLine;
import gov.pnnl.prosser.api.obj.TriplexMeter;
import gov.pnnl.prosser.api.obj.TriplexNode;
import gov.pnnl.prosser.api.obj.ZIPLoad;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nord229
 *
 */
public class ExperimentBuilder {

    public static GldClockBuilder clock() {
        return new GldClockBuilder();
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

    public static ClimateObject.Builder climate() {
        return new ClimateObject.Builder();
    }

    public static Recorder.Builder recorder() {
        return new Recorder.Builder();
    }

    public static OverheadLineConductor.Builder overheadLineConductor() {
        return new OverheadLineConductor.Builder();
    }

    public static OverheadLine.Builder overheadLine() {
        return new OverheadLine.Builder();
    }

    public static LineSpacing.Builder lineSpacing() {
        return new LineSpacing.Builder();
    }

    public static StandardLineConfiguration.Builder<OverheadLineConductor> overheadLineConfiguration() {
        return new StandardLineConfiguration.Builder<OverheadLineConductor>();
    }

    public static Node.Builder node() {
        return new Node.Builder();
    }

    public static TransformerConfiguration.Builder transformerConfiguration() {
        return new TransformerConfiguration.Builder();
    }

    public static Transformer.Builder transformer() {
        return new Transformer.Builder();
    }

    public static TriplexNode.Builder triplexNode() {
        return new TriplexNode.Builder();
    }

    public static TriplexLine.Builder triplexLine() {
        return new TriplexLine.Builder();
    }

    public static TriplexLineConductor.Builder triplexLineConductor() {
        return new TriplexLineConductor.Builder();
    }

    public static TriplexLineConfiguration.Builder triplexLineConfiguration() {
        return new TriplexLineConfiguration.Builder();
    }

    public static TriplexMeter.Builder triplexMeter() {
        return new TriplexMeter.Builder();
    }

    public static House.Builder house() {
        return new House.Builder();
    }

    public static ZIPLoad.Builder zipLoad() {
        return new ZIPLoad.Builder();
    }

}
