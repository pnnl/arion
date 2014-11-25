/**
 *
 */
package gov.pnnl.prosser.api.test;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.Experiment;
import gov.pnnl.prosser.api.GLDExperimentWriter;
import gov.pnnl.prosser.api.gld.Clock;
import gov.pnnl.prosser.api.gld.module.ClimateModule;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.module.PowerflowModule;
import gov.pnnl.prosser.api.gld.module.PowerflowModule.SolverMethod;
import gov.pnnl.prosser.api.gld.module.Residential;
import gov.pnnl.prosser.api.gld.module.Tape;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.lib.LineSpacing;
import gov.pnnl.prosser.api.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.lib.TransformerConfiguration.ConnectionType;
import gov.pnnl.prosser.api.lib.TransformerConfiguration.InstallationType;
import gov.pnnl.prosser.api.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.obj.House;
import gov.pnnl.prosser.api.obj.Node;
import gov.pnnl.prosser.api.obj.OverheadLine;
import gov.pnnl.prosser.api.obj.PowerflowObject.PhaseCode;
import gov.pnnl.prosser.api.obj.Transformer;
import gov.pnnl.prosser.api.obj.TriplexLine;
import gov.pnnl.prosser.api.obj.TriplexMeter;
import gov.pnnl.prosser.api.obj.TriplexNode;
import gov.pnnl.prosser.api.obj.ZIPLoad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.complex.Complex;

/**
 * Test Experiment
 *
 * @author nord229
 *
 */
public class TestExperiment implements Experiment {

    @Override
    public List<AbstractProsserObject> getExperimentObjects() {
        final double oneMileInFeet = 5280;
        final EnumSet<PhaseCode> phaseAS = EnumSet.copyOf(PhaseCode.S);
        phaseAS.add(PhaseCode.A);

        final List<AbstractProsserObject> objects = new ArrayList<>();
        objects.add(new ClimateObject("MyClimate", "WA-Yakima.tmy2"));

        final OverheadLineConductor phaseConductor = new OverheadLineConductor("phase_conductor", 0.0313, 0.1859, 0.927);
        objects.add(phaseConductor);

        final OverheadLineConductor neutralConductor = new OverheadLineConductor("neutral_conductor", 0.00814, 0.492, 0.563);
        objects.add(neutralConductor);

        final LineSpacing standardSpacing = new LineSpacing("standard_spacing", 7.0, 4.5, 2.5, 5.656854, 5.0, 4.272002);
        objects.add(standardSpacing);

        final StandardLineConfiguration<OverheadLineConductor> lineConfig1 = new StandardLineConfiguration<>("line_config1",
                phaseConductor, phaseConductor, phaseConductor, neutralConductor, standardSpacing);
        objects.add(lineConfig1);

        final Node node1 = new Node("node1", PhaseCode.ABCN, 7200, new Complex(7199.558, 0.000),
                new Complex(-3599.779, -6235.000), new Complex(-3599.779, 6235.000));
        objects.add(node1);

        final Node node2 = new Node("node2", PhaseCode.ABCN, 7200, new Complex(7199.558, 0.000),
                new Complex(-3599.779, -6235.000), new Complex(-3599.779, 6235.000));

        final OverheadLine line1 = new OverheadLine(PhaseCode.ABCN, node1, node2, oneMileInFeet, lineConfig1);
        objects.add(line1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node2);

        final TransformerConfiguration transformerConfig1 = new TransformerConfiguration("transformer_config1",
                ConnectionType.WYE_WYE, 6000.0, 2000.0, 2000.0, 2000.0, 12470.0, 4160.0, new Complex(0.01, 0.06));
        objects.add(transformerConfig1);

        final Node node3 = new Node("node3", PhaseCode.ABCN, 2400);

        final Transformer transformer1 = new Transformer(PhaseCode.ABCN, node2, node3, transformerConfig1);
        objects.add(transformer1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node3);

        final Node node4 = new Node("node4", PhaseCode.ABCN, 2400);

        final OverheadLine line2 = new OverheadLine(PhaseCode.ABCN, node3, node4, 500, lineConfig1);
        objects.add(line2);
        // TODO For some reason GLD pushes the node here?
        objects.add(node4);

        final TransformerConfiguration transformerConfig2 = new TransformerConfiguration("transformer_config2",
                ConnectionType.SINGLE_PHASE_CENTER_TAPPED, InstallationType.POLETOP, 100, 2401.777, 120,
                new Complex(0.00033, 0.0022), new Complex(10000, 10000));
        objects.add(transformerConfig2);

        final TriplexNode tripNode1 = new TriplexNode("trip_node1", phaseAS, 120);

        final Transformer transformer2 = new Transformer("distribution_transformer", phaseAS, node4, tripNode1, transformerConfig2);
        objects.add(transformer2);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripNode1);

        final TriplexLineConductor tripConductor1 = new TriplexLineConductor("trip_conductor1", 0.97, 0.01111);

        final TriplexLineConfiguration tripLineConfiguration = new TriplexLineConfiguration("trip_line_config", tripConductor1,
                tripConductor1, tripConductor1, 0.08, 0.522);
        objects.add(tripLineConfiguration);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripConductor1);

        final TriplexMeter tripMeter1 = new TriplexMeter("trip_meter1", phaseAS, 120);

        final TriplexLine tripLine = new TriplexLine(phaseAS, tripNode1, tripMeter1, 25, tripLineConfiguration);
        objects.add(tripLine);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripMeter1);

        final ZIPLoad zipLoad = new ZIPLoad(0.8, 1, -0.9, 0.25, 0.85, 0.25, 1, .5);
        final House house = new House("house1", tripMeter1, zipLoad);
        objects.add(house);

        final Recorder recorder = new Recorder(1800L, "test_outputs.csv", "air_temperature", house);
        objects.add(recorder);

        return objects;
    }

    @Override
    public Clock getGLDClock() {
        final Clock clock = new Clock();
        clock.setTimezone("PST+8PDT");
        clock.setStartTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        clock.setStopTime(LocalDateTime.of(2000, 2, 1, 0, 0, 0));
        return clock;
    }

    @Override
    public List<Module> getGLDModules() {
        final List<Module> modules = new ArrayList<>();
        modules.add(new PowerflowModule(SolverMethod.FBS));
        modules.add(new Residential("NONE"));
        modules.add(new ClimateModule());
        modules.add(new Tape());
        return modules;
    }

    public static void main(final String... args) throws Exception {
        final Path dir = Paths.get(System.getProperty("user.dir"));
        final Path file = dir.resolve("test/prosser.out");
        if (!Files.exists(file.getParent())) {
            Files.createDirectory(file.getParent());
        }
        final TestExperiment experiment = new TestExperiment();
        final GLDExperimentWriter writer = new GLDExperimentWriter();
        try {
            writer.writeExperiment(file, experiment);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getGLDSettings() {
        final Map<String, String> map = new HashMap<>();
        map.put("savefile", "testSean.xml");
        map.put("profiler", "1");
        return map;
    }
}
