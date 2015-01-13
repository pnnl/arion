/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.*;
import gov.pnnl.prosser.api.gld.module.*;
import gov.pnnl.prosser.api.lib.*;
import gov.pnnl.prosser.api.obj.*;

import java.time.*;
import java.util.*;

/**
 * Test Experiment
 *
 * @author nord229
 *
 */
public class TestExperiment implements GldSimulator {

    @Override
    public List<AbstractProsserObject> getSimulatorObjects() {
        final double oneMileInFeet = 5280;
        final EnumSet<PhaseCode> phaseAS = EnumSet.copyOf(PhaseCode.S);
        phaseAS.add(PhaseCode.A);

        final List<AbstractProsserObject> objects = new ArrayList<>();
        objects.add(ExperimentBuilder.climate().name("MyClimate").tmyFile("WA-Yakima.tmy2").build());

        final OverheadLineConductor phaseConductor = ExperimentBuilder.overheadLineConductor()
                .name("phase_conductor")
                .geometricMeanRadius(0.0313)
                .resistance(0.1859)
                .diameter(0.927)
                .build();
        objects.add(phaseConductor);

        final OverheadLineConductor neutralConductor = ExperimentBuilder.overheadLineConductor()
                .name("neutral_conductor")
                .geometricMeanRadius(0.00814)
                .resistance(0.492)
                .diameter(0.563)
                .build();
        objects.add(neutralConductor);

        final LineSpacing standardSpacing = ExperimentBuilder.lineSpacing()
                .name("standard_spacing")
                .distanceAToB(7.0)
                .distanceBToC(4.5)
                .distanceAToC(2.5)
                .distanceAToN(5.656854)
                .distanceBToN(5.0)
                .distanceCToN(4.272002)
                .build();
        objects.add(standardSpacing);

        final StandardLineConfiguration<OverheadLineConductor> lineConfig1 = ExperimentBuilder.overheadLineConfiguration()
                .name("line_config1")
                .phaseAConductor(phaseConductor)
                .phaseBConductor(phaseConductor)
                .phaseCConductor(phaseConductor)
                .phaseNConductor(neutralConductor)
                .spacing(standardSpacing)
                .build();
        objects.add(lineConfig1);

        final Node node1 = ExperimentBuilder.node()
                .name("node1")
                .phases(PhaseCode.ABCN)
                .nominalVoltage(7200)
                .voltageA(7199.558, 0.000)
                .voltageB(-3599.779, -6235.000)
                .voltageC(-3599.779, 6235.000)
                .build();
        objects.add(node1);

        final Node node2 = ExperimentBuilder.node()
                .name("node2")
                .phases(PhaseCode.ABCN)
                .nominalVoltage(7200)
                .voltageA(7199.558, 0.000)
                .voltageB(-3599.779, -6235.000)
                .voltageC(-3599.779, 6235.000)
                .build();

        final OverheadLine line1 = ExperimentBuilder.overheadLine()
                .phases(PhaseCode.ABCN)
                .from(node1)
                .to(node2)
                .length(oneMileInFeet)
                .configuration(lineConfig1)
                .build();
        objects.add(line1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node2);

        final TransformerConfiguration transformerConfig1 = ExperimentBuilder.transformerConfiguration()
                .name("transformer_config1")
                .connectionType(ConnectionType.WYE_WYE)
                .powerRating(6000.0)
                .phaseARating(2000.0)
                .phaseBRating(2000.0)
                .phaseCRating(2000.0)
                .primaryVoltage(12470.0)
                .secondaryVoltage(4160.0)
                .impedance(0.01, 0.06)
                .build();
        objects.add(transformerConfig1);

        final Node node3 = ExperimentBuilder.node()
                .name("node3")
                .phases(PhaseCode.ABCN)
                .nominalVoltage(2400)
                .build();

        final Transformer transformer1 = ExperimentBuilder.transformer()
                .phases(PhaseCode.ABCN)
                .from(node2)
                .to(node3)
                .configuration(transformerConfig1)
                .build();
        objects.add(transformer1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node3);

        final Node node4 = ExperimentBuilder.node()
                .name("node4")
                .phases(PhaseCode.ABCN)
                .nominalVoltage(2400)
                .build();

        final OverheadLine line2 = ExperimentBuilder.overheadLine()
                .phases(PhaseCode.ABCN)
                .from(node3)
                .to(node4)
                .length(500)
                .configuration(lineConfig1)
                .build();
        objects.add(line2);
        // TODO For some reason GLD pushes the node here?
        objects.add(node4);

        final TransformerConfiguration transformerConfig2 = ExperimentBuilder.transformerConfiguration()
                .name("transformer_config2")
                .connectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED)
                .installationType(InstallationType.POLETOP)
                .phaseARating(100)
                .primaryVoltage(2401.777)
                .secondaryVoltage(120)
                .impedance(0.00033, 0.0022)
                .shuntImpedance(10000, 10000)
                .build();
        objects.add(transformerConfig2);

        final TriplexNode tripNode1 = ExperimentBuilder.triplexNode()
                .name("trip_node1")
                .phases(phaseAS)
                .nominalVoltage(120)
                .build();

        final Transformer transformer2 = ExperimentBuilder.transformer()
                .name("distribution_transformer")
                .phases(phaseAS)
                .from(node4)
                .to(tripNode1)
                .configuration(transformerConfig2)
                .build();
        objects.add(transformer2);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripNode1);

        final TriplexLineConductor tripConductor1 = ExperimentBuilder.triplexLineConductor()
                .name("trip_conductor1")
                .resistance(0.97)
                .geometricMeanRadius(0.01111)
                .build();

        final TriplexLineConfiguration tripLineConfiguration = ExperimentBuilder.triplexLineConfiguration()
                .name("trip_line_config")
                .phase1Conductor(tripConductor1)
                .phase2Conductor(tripConductor1)
                .phaseNConductor(tripConductor1)
                .insulationThickness(0.08)
                .diameter(0.522)
                .build();
        objects.add(tripLineConfiguration);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripConductor1);

        final TriplexMeter tripMeter1 = ExperimentBuilder.triplexMeter()
                .name("trip_meter1")
                .phases(phaseAS)
                .nominalVoltage(120)
                .build();

        final TriplexLine tripLine = ExperimentBuilder.triplexLine()
                .phases(phaseAS)
                .from(tripNode1)
                .to(tripMeter1)
                .length(25)
                .configuration(tripLineConfiguration)
                .build();
        objects.add(tripLine);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripMeter1);

        final ZIPLoad zipLoad = ExperimentBuilder.zipLoad()
                .heatFraction(0.8)
                .basePower(1)
                .powerPf(-0.9)
                .powerFraction(0.25)
                .currentPf(0.85)
                .currentFraction(0.25)
                .impedancePf(1)
                .impedanceFraction(.5)
                .build();
        final House house = ExperimentBuilder.house()
                .name("house1")
                .parent(tripMeter1)
                .load(zipLoad)
                .build();
        objects.add(house);

        objects.add(ExperimentBuilder.recorder()
                .interval(1800L)
                .file("test_outputs.csv")
                .property("air_temperature")
                .parent(house)
                .build());

        return objects;
    }

    @Override
    public GldClock getGldClock() {
        return ExperimentBuilder.clock()
                .timezone("PST+8PDT")
                .startTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
                .stopTime(LocalDateTime.of(2000, 2, 1, 0, 0, 0))
                .build();
    }

    @Override
    public List<Module> getGldModules() {
        return ExperimentBuilder.module()
                .addPowerflow().solverMethod(SolverMethod.FBS).and()
                .addResidential().implicitEnduses("NONE").and()
                .addClimate()
                .addTape()
                .build();
    }

    @Override
    public Map<String, String> getGldSettings() {
        return ExperimentBuilder.gldSettings()
                .put("savefile", "testSean.xml")
                .put("profiler", "1")
                .build();
    }
}
