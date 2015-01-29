/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.*;
import gov.pnnl.prosser.api.gld.module.*;
import gov.pnnl.prosser.api.gld.obj.*;
import gov.pnnl.prosser.api.pwr.lib.*;
import gov.pnnl.prosser.api.pwr.obj.*;

import java.time.*;
import java.util.*;

/**
 * Test Experiment
 *
 * @author nord229
 *
 */
public class TestGldSimulator implements GldSimulator {

    @Override
    public List<AbstractProsserObject> getObjects() {
        final double oneMileInFeet = 5280;
        final EnumSet<PhaseCode> phaseAS = EnumSet.copyOf(PhaseCode.S);
        phaseAS.add(PhaseCode.A);

        final List<AbstractProsserObject> objects = new ArrayList<>();
        final ClimateObject climate = ExperimentBuilder.climate();
        climate.setName("MyClimate");
        climate.setTmyFile("WA-Yakima.tmy2");
        objects.add(climate);

        final OverheadLineConductor phaseConductor = ExperimentBuilder.overheadLineConductor();
        phaseConductor.setName("phase_conductor");
        phaseConductor.setGeometricMeanRadius(0.0313);
        phaseConductor.setResistance(0.1859);
        phaseConductor.setDiameter(0.927);
        objects.add(phaseConductor);

        final OverheadLineConductor neutralConductor = ExperimentBuilder.overheadLineConductor();
        neutralConductor.setName("neutral_conductor");
        neutralConductor.setGeometricMeanRadius(0.00814);
        neutralConductor.setResistance(0.492);
        neutralConductor.setDiameter(0.563);
        objects.add(neutralConductor);

        final LineSpacing standardSpacing = ExperimentBuilder.lineSpacing();
        standardSpacing.setName("standard_spacing");
        standardSpacing.setDistanceAToB(7.0);
        standardSpacing.setDistanceBToC(4.5);
        standardSpacing.setDistanceAToC(2.5);
        standardSpacing.setDistanceAToN(5.656854);
        standardSpacing.setDistanceBToN(5.0);
        standardSpacing.setDistanceCToN(4.272002);
        objects.add(standardSpacing);

        final StandardLineConfiguration<OverheadLineConductor> lineConfig1 = ExperimentBuilder.overheadLineConfiguration();
        lineConfig1.setName("line_config1");
        lineConfig1.setPhaseAConductor(phaseConductor);
        lineConfig1.setPhaseBConductor(phaseConductor);
        lineConfig1.setPhaseCConductor(phaseConductor);
        lineConfig1.setPhaseNConductor(neutralConductor);
        lineConfig1.setSpacing(standardSpacing);
        objects.add(lineConfig1);

        final Node node1 = ExperimentBuilder.node();
        node1.setName("node1");
        node1.setPhases(PhaseCode.ABCN);
        node1.setNominalVoltage(7200.0);
        node1.setVoltageA(7199.558, 0.000);
        node1.setVoltageB(-3599.779, -6235.000);
        node1.setVoltageC(-3599.779, 6235.000);
        objects.add(node1);

        final Node node2 = ExperimentBuilder.node();
        node2.setName("node2");
        node2.setPhases(PhaseCode.ABCN);
        node2.setNominalVoltage(7200.0);
        node2.setVoltageA(7199.558, 0.000);
        node2.setVoltageB(-3599.779, -6235.000);
        node2.setVoltageC(-3599.779, 6235.000);

        final OverheadLine line1 = ExperimentBuilder.overheadLine();
        line1.setPhases(PhaseCode.ABCN);
        line1.setFrom(node1);
        line1.setTo(node2);
        line1.setLength(oneMileInFeet);
        line1.setConfiguration(lineConfig1);
        objects.add(line1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node2);

        final TransformerConfiguration transformerConfig1 = ExperimentBuilder.transformerConfiguration();
        transformerConfig1.setName("transformer_config1");
        transformerConfig1.setConnectionType(ConnectionType.WYE_WYE);
        transformerConfig1.setPowerRating(6000.0);
        transformerConfig1.setPhaseARating(2000.0);
        transformerConfig1.setPhaseBRating(2000.0);
        transformerConfig1.setPhaseCRating(2000.0);
        transformerConfig1.setPrimaryVoltage(12470.0);
        transformerConfig1.setSecondaryVoltage(4160.0);
        transformerConfig1.setImpedance(0.01, 0.06);
        objects.add(transformerConfig1);

        final Node node3 = ExperimentBuilder.node();
        node3.setName("node3");
        node3.setPhases(PhaseCode.ABCN);
        node3.setNominalVoltage(2400.0);

        final Transformer transformer1 = ExperimentBuilder.transformer();
        transformer1.setPhases(PhaseCode.ABCN);
        transformer1.setFrom(node2);
        transformer1.setTo(node3);
        transformer1.setConfiguration(transformerConfig1);
        objects.add(transformer1);
        // TODO For some reason GLD pushes the node here?
        objects.add(node3);

        final Node node4 = ExperimentBuilder.node();
        node4.setName("node4");
        node4.setPhases(PhaseCode.ABCN);
        node4.setNominalVoltage(2400.0);

        final OverheadLine line2 = ExperimentBuilder.overheadLine();
        line2.setPhases(PhaseCode.ABCN);
        line2.setFrom(node3);
        line2.setTo(node4);
        line2.setLength(500);
        line2.setConfiguration(lineConfig1);
        objects.add(line2);
        // TODO For some reason GLD pushes the node here?
        objects.add(node4);

        final TransformerConfiguration transformerConfig2 = ExperimentBuilder.transformerConfiguration();
        transformerConfig2.setName("transformer_config2");
        transformerConfig2.setConnectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED);
        transformerConfig2.setInstallationType(InstallationType.POLETOP);
        transformerConfig2.setPhaseARating(100);
        transformerConfig2.setPrimaryVoltage(2401.777);
        transformerConfig2.setSecondaryVoltage(120);
        transformerConfig2.setImpedance(0.00033, 0.0022);
        transformerConfig2.setShuntImpedance(10000, 10000);
        objects.add(transformerConfig2);

        final TriplexNode tripNode1 = ExperimentBuilder.triplexNode();
        tripNode1.setName("trip_node1");
        tripNode1.setPhases(phaseAS);
        tripNode1.setNominalVoltage(120.0);

        final Transformer transformer2 = ExperimentBuilder.transformer();
        transformer2.setName("distribution_transformer");
        transformer2.setPhases(phaseAS);
        transformer2.setFrom(node4);
        transformer2.setTo(tripNode1);
        transformer2.setConfiguration(transformerConfig2);
        objects.add(transformer2);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripNode1);

        final TriplexLineConductor tripConductor1 = ExperimentBuilder.triplexLineConductor();
        tripConductor1.setName("trip_conductor1");
        tripConductor1.setResistance(0.97);
        tripConductor1.setGeometricMeanRadius(0.01111);

        final TriplexLineConfiguration tripLineConfiguration = ExperimentBuilder.triplexLineConfiguration();
        tripLineConfiguration.setName("trip_line_config");
        tripLineConfiguration.setPhase1Conductor(tripConductor1);
        tripLineConfiguration.setPhase2Conductor(tripConductor1);
        tripLineConfiguration.setPhaseNConductor(tripConductor1);
        tripLineConfiguration.setInsulationThickness(0.08);
        tripLineConfiguration.setDiameter(0.522);
        objects.add(tripLineConfiguration);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripConductor1);

        final TriplexMeter tripMeter1 = ExperimentBuilder.triplexMeter();
        tripMeter1.setName("trip_meter1");
        tripMeter1.setPhases(phaseAS);
        tripMeter1.setNominalVoltage(120.0);

        final TriplexLine tripLine = ExperimentBuilder.triplexLine();
        tripLine.setPhases(phaseAS);
        tripLine.setFrom(tripNode1);
        tripLine.setTo(tripMeter1);
        tripLine.setLength(25);
        tripLine.setConfiguration(tripLineConfiguration);
        objects.add(tripLine);
        // TODO For some reason GLD pushes the node here?
        objects.add(tripMeter1);

        final ZIPLoad zipLoad = ExperimentBuilder.zipLoad();
        zipLoad.setHeatFraction(0.8);
        zipLoad.setBasePower(1);
        zipLoad.setPowerPf(-0.9);
        zipLoad.setPowerFraction(0.25);
        zipLoad.setCurrentPf(0.85);
        zipLoad.setCurrentFraction(0.25);
        zipLoad.setImpedancePf(1);
        zipLoad.setImpedanceFraction(.5);
        final House house = ExperimentBuilder.house();
        house.setName("house1");
        house.setParent(tripMeter1);
        house.setLoad(zipLoad);
        objects.add(house);

        final Recorder recorder = ExperimentBuilder.recorder();
        recorder.setInterval(1800L);
        recorder.setFile("test_outputs.csv");
        recorder.setProperty("air_temperature");
        recorder.setParent(house);
        objects.add(recorder);

        return objects;
    }

    @Override
    public GldClock getClock() {
        final GldClock clock = ExperimentBuilder.clock();
        clock.setTimezone("PST+8PDT");
        clock.setStartTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        clock.setStopTime(LocalDateTime.of(2000, 2, 1, 0, 0, 0));
        return clock;
    }

    @Override
    public List<Module> getModules() {
        return ExperimentBuilder.module()
                .addPowerflow().solverMethod(SolverMethod.FBS).and()
                .addResidential().implicitEnduses("NONE").and()
                .addClimate()
                .addTape()
                .build();
    }

    @Override
    public Map<String, String> getSettings() {
        return ExperimentBuilder.gldSettings()
                .put("savefile", "testSean.xml")
                .put("profiler", "1")
                .build();
    }

    @Override
    public String getName() {
        return "test_gld_simulator";
    }
}
