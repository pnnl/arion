/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;

import java.time.*;

/**
 * Original GLD Simulator test from SeanZ
 *
 * @author nord229
 *
 */
public class TestSeanZExperiment extends Experiment {

    /**
     * Generate the experiment
     */
    @Override
    public void generate() {
        final GldSimulator sim = this.gldSimulator("TestForSeanZ");

        final GldClock clock = sim.clock();
        clock.setTimezone("PST+8PDT");
        clock.setStartTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
        clock.setStopTime(LocalDateTime.of(2000, 2, 1, 0, 0, 0));

        sim.climateModule();
        sim.tapeModule();
        sim.powerflowModule(SolverMethod.FBS, null);
        sim.residentialModule(ImplicitEnduses.NONE);

        sim.addSetting("savefile", "testSean.xml");
        sim.addSetting("profiler", "1");

        final double oneMileInFeet = 5280;

        final ClimateObject climate = sim.climateObject("MyClimate");
        climate.setTmyFile("WA-Yakima.tmy2");

        final OverheadLineConductor phaseConductor = sim.overheadLineConductor("phase_conductor");
        phaseConductor.setGeometricMeanRadius(0.0313);
        phaseConductor.setResistance(0.1859);
        phaseConductor.setDiameter(0.927);

        final OverheadLineConductor neutralConductor = sim.overheadLineConductor("neutral_conductor");
        neutralConductor.setGeometricMeanRadius(0.00814);
        neutralConductor.setResistance(0.492);
        neutralConductor.setDiameter(0.563);

        final LineSpacing standardSpacing = sim.lineSpacing("standard_spacing");
        standardSpacing.setDistanceAToB(7.0);
        standardSpacing.setDistanceBToC(4.5);
        standardSpacing.setDistanceAToC(2.5);
        standardSpacing.setDistanceAToN(5.656854);
        standardSpacing.setDistanceBToN(5.0);
        standardSpacing.setDistanceCToN(4.272002);

        final StandardLineConfiguration<OverheadLineConductor> lineConfig1 = sim.overheadLineConfiguration("line_config1");
        lineConfig1.setPhaseAConductor(phaseConductor);
        lineConfig1.setPhaseBConductor(phaseConductor);
        lineConfig1.setPhaseCConductor(phaseConductor);
        lineConfig1.setPhaseNConductor(neutralConductor);
        lineConfig1.setSpacing(standardSpacing);

        final Node node1 = sim.node("node1");
        node1.setPhases(PhaseCode.ABCN);
        node1.setNominalVoltage(7200.0);
        node1.setVoltageA(7199.558, 0.000);
        node1.setVoltageB(-3599.779, -6235.000);
        node1.setVoltageC(-3599.779, 6235.000);

        final Node node2 = sim.node("node2");
        node2.setPhases(PhaseCode.ABCN);
        node2.setNominalVoltage(7200.0);
        node2.setVoltageA(7199.558, 0.000);
        node2.setVoltageB(-3599.779, -6235.000);
        node2.setVoltageC(-3599.779, 6235.000);

        final OverheadLine line1 = sim.overheadLine(null);
        line1.setPhases(PhaseCode.ABCN);
        line1.setFrom(node1);
        line1.setTo(node2);
        line1.setLength(oneMileInFeet);
        line1.setConfiguration(lineConfig1);

        final TransformerConfiguration transformerConfig1 = sim.transformerConfiguration("transformer_config1");
        transformerConfig1.setConnectionType(ConnectionType.WYE_WYE);
        transformerConfig1.setPowerRating(6000.0);
        transformerConfig1.setPhaseARating(2000.0);
        transformerConfig1.setPhaseBRating(2000.0);
        transformerConfig1.setPhaseCRating(2000.0);
        transformerConfig1.setPrimaryVoltage(12470.0);
        transformerConfig1.setSecondaryVoltage(4160.0);
        transformerConfig1.setImpedance(0.01, 0.06);

        final Node node3 = sim.node("node3");
        node3.setPhases(PhaseCode.ABCN);
        node3.setNominalVoltage(2400.0);

        final Transformer transformer1 = sim.transformer(null);
        transformer1.setPhases(PhaseCode.ABCN);
        transformer1.setFrom(node2);
        transformer1.setTo(node3);
        transformer1.setConfiguration(transformerConfig1);

        final Node node4 = sim.node("node4");
        node4.setPhases(PhaseCode.ABCN);
        node4.setNominalVoltage(2400.0);

        final OverheadLine line2 = sim.overheadLine(null);
        line2.setPhases(PhaseCode.ABCN);
        line2.setFrom(node3);
        line2.setTo(node4);
        line2.setLength(500);
        line2.setConfiguration(lineConfig1);

        final TransformerConfiguration transformerConfig2 = sim.transformerConfiguration("transformer_config2");
        transformerConfig2.setConnectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED);
        transformerConfig2.setInstallationType(InstallationType.POLETOP);
        transformerConfig2.setPhaseARating(100.0);
        transformerConfig2.setPrimaryVoltage(2401.777);
        transformerConfig2.setSecondaryVoltage(120);
        transformerConfig2.setImpedance(0.00033, 0.0022);
        transformerConfig2.setShuntImpedance(10000, 10000);

        final TriplexNode tripNode1 = sim.triplexNode("trip_node1");
        tripNode1.setPhases(PhaseCode.AS);
        tripNode1.setNominalVoltage(120.0);

        final Transformer transformer2 = sim.transformer("distribution_transformer");
        transformer2.setPhases(PhaseCode.AS);
        transformer2.setFrom(node4);
        transformer2.setTo(tripNode1);
        transformer2.setConfiguration(transformerConfig2);

        final TriplexLineConductor tripConductor1 = sim.triplexLineConductor("trip_conductor1");
        tripConductor1.setResistance(0.97);
        tripConductor1.setGeometricMeanRadius(0.01111);

        final TriplexLineConfiguration tripLineConfiguration = sim.triplexLineConfiguration("trip_line_config");
        tripLineConfiguration.setPhase1Conductor(tripConductor1);
        tripLineConfiguration.setPhase2Conductor(tripConductor1);
        tripLineConfiguration.setPhaseNConductor(tripConductor1);
        tripLineConfiguration.setInsulationThickness(0.08);
        tripLineConfiguration.setDiameter(0.522);

        final TriplexMeter tripMeter1 = sim.triplexMeter("trip_meter1");
        tripMeter1.setPhases(PhaseCode.AS);
        tripMeter1.setNominalVoltage(120.0);

        final TriplexLine tripLine = sim.triplexLine(null);
        tripLine.setPhases(PhaseCode.AS);
        tripLine.setFrom(tripNode1);
        tripLine.setTo(tripMeter1);
        tripLine.setLength(25);
        tripLine.setConfiguration(tripLineConfiguration);

        final House house = sim.house("house1");
        house.setParent(tripMeter1);

        final ZIPLoad zipLoad = house.addLoad();
        zipLoad.setHeatFraction(0.8);
        zipLoad.setBasePower(1);
        zipLoad.setPowerPf(-0.9);
        zipLoad.setPowerFraction(0.25);
        zipLoad.setCurrentPf(0.85);
        zipLoad.setCurrentFraction(0.25);
        zipLoad.setImpedancePf(1);
        zipLoad.setImpedanceFraction(.5);

        final Recorder recorder = sim.recorder();
        recorder.setInterval(1800L);
        recorder.setFile("test_outputs.csv");
        recorder.setProperty("air_temperature");
        recorder.setParent(house);
    }
}
