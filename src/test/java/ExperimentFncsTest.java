/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * First FNCS experiment
 *
 * @author nord229
 *
 */
public class ExperimentFncsTest {

    public static void main(final String[] args) throws IOException {
        final Path outPath = Paths.get(args[0]).toRealPath();
        final GldSimulator gldSim = constructGldSim();
        GldSimulatorWriter.writeGldSimulator(outPath.resolve("prosser.glm"), gldSim);
        // TODO: No passing of information between simulators here; not maintainable approach; force objects to create simulator relationships
        // For this test there will be one object in each list but in general there could be multiple
        final List<AuctionObject> auctions = new ArrayList<>();
        final List<Controller> controllers = new ArrayList<>();
        gldSim.getObjects().forEach((o) -> {
            if (o instanceof AuctionObject) {
                auctions.add((AuctionObject) o);
            } else if (o instanceof House) {
                controllers.add(((House) o).getController());
            }
        });

        final String controllerPrefix = "F1_C_NI";
        final Ns3Simulator ns3Simulator = new Ns3Simulator();
//        ns3Simulator.setAuctions(auctions);
//        ns3Simulator.setControllers(controllers);
//        ns3Simulator.setGldNodePrefix(controllerPrefix);
        Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve("ns3.cc"), ns3Simulator);
        System.out.println("Written!");
        // TODO FNCS Integration
    }

    /**
     * The gld simulator for this experiment
     *
     * @return
     */
    private static GldSimulator constructGldSim() {
        // Construct the simulator
        final GldSimulator sim = new GldSimulator("fncs_GLD_100node_Feeder_1");

        // Setup the clock
        final GldClock clock = sim.clock();
        clock.setTimezone("EST+5EDT");
        clock.setStartTime(LocalDateTime.of(2009, 07, 21, 00, 00));
        clock.setStopTime(LocalDateTime.of(2009, 07, 23, 00, 00));

        // Setup the modules
        sim.marketModule();
        sim.tapeModule();
        sim.commModule();
        sim.climateModule();
        sim.residentialModule("NONE");
        sim.powerflowModule(SolverMethod.FBS, 100L);

        // Add settings, configures simulation
        sim.addSetting("profiler", "1");
        sim.addSetting("double_format", "%+.12lg");
        sim.addSetting("randomseed", "10");
        sim.addSetting("relax_naming_rules", "1");

        // Add includes, extra glm files that help with simulation
        sim.addIncludes("water_and_setpoint_schedule_v3.glm", "appliance_schedules.glm");

        // Add a player class def to allow references to value from player objects
        final PlayerClass playerClass = sim.playerClass();
        playerClass.addField("value", "double");

        // Add a auction class def to allow references to more fields from other objects
        final AuctionClass auctionClass = sim.auctionClass();
        auctionClass.addField("day_ahead_average", "double");
        auctionClass.addField("day_ahead_std", "double");
        auctionClass.addField("current_price_mean_24h", "double");
        auctionClass.addField("current_price_stdev_24h", "double");

        // Create Player for the Phase A load on the substation
        final PlayerObject phaseALoad = sim.playerObject("phase_A_load");
        // TODO: This should be a path. We may need to create a copy of this file and package as they will not be running on the compiler box
        phaseALoad.setFile("phase_A.player");
        phaseALoad.setLoop(1);

        // Create Player for the Phase B load on the substation
        final PlayerObject phaseBLoad = sim.playerObject("phase_B_load");
        phaseBLoad.setFile("phase_B.player");
        phaseBLoad.setLoop(1);

        // Create Player for the Phase C load on the substation
        final PlayerObject phaseCLoad = sim.playerObject("phase_C_load");
        phaseCLoad.setFile("phase_C.player");
        phaseCLoad.setLoop(1);

        // Specify the climate information
        final ClimateObject climate = sim.climateObject("Columbus OH");
        climate.setTmyFile("ColumbusWeather2009_2a.csv");
        climate.addCsvReader("CSVREADER");

        // Create the FNCS auction
        final AuctionObject auction = sim.auctionObject("Market1");
        auction.setUnit("kW");
        auction.setPeriod(300);
        auction.setPriceCap(3.78);
        auction.setTransactionLogFile("log_file_fncs_GLD_300node_feeder_1_base_case.csv");
        auction.setCurveLogFile("bid_curve_fncs_GLD_300node_feeder_1_base_case.csv");
        auction.setCurveLogInfo(CurveOutput.EXTRA);
        auction.setNetworkAveragePriceProperty("current_price_mean_24h");
        auction.setNetworkStdevPriceProperty("current_price_stdev_24h");
        auction.setNetworkAdjustPriceProperty("adjust_price");

        // Add a player to the auction for one of its values
        final PlayerObject player = auction.player();
        player.setProperty("fixed_price");
        player.setFile("AEP_RT_LMP.player");
        player.setLoop(1);

        auction.setSpecialMode(SpecialMode.BUYERS_ONLY);

        // Add a recorder to the auction for some of the properties on the auction
        // TODO: Need to add code for writing SQL commands for generating tables to collect data from all simulator data generating objects
        // TODO: For instance GridLAB-D has an odbc recorder, processor needs to write the appropirate connection info in the glm and the sql
        // TODO: to create a table in the database to accept the comunication from gld.
        final Recorder recorder = auction.recorder();
        recorder.setProperty("capacity_reference_bid_price, current_market.clearing_price, current_market.clearing_quantity");
        recorder.setLimit(100000000);
        recorder.setInterval(300L);
        recorder.setFile("baseprice_clearedprice_clearedquantity_fncs_GLD_300node_feeder_1.csv");

        auction.setInitPrice(0.042676);
        auction.setInitStdev(0.02);
        auction.setUseFutureMeanPrice(false);
        auction.setWarmup(0);

        // Create a transformer configuration for the substation
        final TransformerConfiguration substationConfig = sim.transformerConfiguration("substation_config");
        substationConfig.setConnectionType(ConnectionType.WYE_WYE);
        substationConfig.setInstallationType(InstallationType.PADMOUNT);
        substationConfig.setPrimaryVoltage(7200);
        substationConfig.setSecondaryVoltage(7200);
        substationConfig.setPowerRating(4500.0);
        substationConfig.setPhaseARating(1500.0);
        substationConfig.setPhaseBRating(1500.0);
        substationConfig.setPhaseCRating(1500.0);
        substationConfig.setImpedance(0.0015, 0.00675);

        // Create the transformer configuration for each phase from the substation
        final TransformerConfiguration defaultTransformerA = sim.transformerConfiguration("default_transformer_A");
        defaultTransformerA.setPhaseARating(1500.0);
        setupSubstationTransformerBase(defaultTransformerA);

        final TransformerConfiguration defaultTransformerB = sim.transformerConfiguration("default_transformer_B");
        defaultTransformerB.setPhaseBRating(1500.0);
        setupSubstationTransformerBase(defaultTransformerB);

        final TransformerConfiguration defaultTransformerC = sim.transformerConfiguration("default_transformer_C");
        defaultTransformerC.setPhaseCRating(1500.0);
        setupSubstationTransformerBase(defaultTransformerC);

        // Create the triplex line conductor used everywhere
        final TriplexLineConductor tripLineCond1AA = sim.triplexLineConductor("Name_1_0_AA_triplex");
        tripLineCond1AA.setResistance(0.57);
        tripLineCond1AA.setGeometricMeanRadius(0.0111);

        // Create the triplex line configuration used everywhere
        final TriplexLineConfiguration tripLineConf = sim.triplexLineConfiguration("TLCFG");
        tripLineConf.setPhase1Conductor(tripLineCond1AA);
        tripLineConf.setPhase2Conductor(tripLineCond1AA);
        tripLineConf.setPhaseNConductor(tripLineCond1AA);
        tripLineConf.setInsulationThickness(0.08);
        tripLineConf.setDiameter(0.368);

        // Create the base substation
        final Substation substation = sim.substation("substation_root");
        substation.setBusType(BusType.SWING);
        substation.setNominalVoltage(7200.0);
        substation.setReferencePhase(PhaseCode.A);
        substation.setPhases(PhaseCode.ABCN);
        substation.setVoltageA(7200.0, 0.0);
        substation.setVoltageB(-3600.0, -6235.3829);
        substation.setVoltageC(-3600.0, 6235.3829);
        // FIXME add recorder

        // Create the root meter
        final Meter rootMeter = sim.meter("F1_transformer_meter");
        rootMeter.setPhases(PhaseCode.ABCN);
        rootMeter.setNominalVoltage(7200.0);

        // Create the root transformer
        final Transformer root = sim.transformer("F1_Transformer1");
        root.setPhases(PhaseCode.ABCN);
        root.setGroupId("F1_Network_Trans");
        root.setFrom(substation);
        root.setTo(rootMeter);
        root.setConfiguration(substationConfig);

        // Create load on the meter
        final Load load = sim.load("F1_unresp_load");
        load.setNominalVoltage(7200.0);
        load.setPhases(PhaseCode.ABCN);
        load.setParent(rootMeter);
        load.setPhaseAConstantReal("phase_A_load.value*0.1");
        load.setPhaseBConstantReal("phase_B_load.value*0.1");
        load.setPhaseCConstantReal("phase_C_load.value*0.1");

        // Create the seperated phase meters for the house groups
        final TriplexMeter tripMeterA = sim.triplexMeter("F1_triplex_node_A");
        tripMeterA.setPhases(PhaseCode.AS);
        tripMeterA.setNominalVoltage(124.00);

        final TriplexMeter tripMeterB = sim.triplexMeter("F1_triplex_node_B");
        tripMeterB.setPhases(PhaseCode.BS);
        tripMeterB.setNominalVoltage(124.00);

        final TriplexMeter tripMeterC = sim.triplexMeter("F1_triplex_node_C");
        tripMeterC.setPhases(PhaseCode.CS);
        tripMeterC.setNominalVoltage(124.00);

        // Create the transformers to feed the house groups
        final Transformer centerTapTransformerA = sim.transformer("F1_center_tap_transformer_A");
        centerTapTransformerA.setPhases(PhaseCode.AS);
        centerTapTransformerA.setTo(tripMeterA);
        centerTapTransformerA.setFrom(rootMeter);
        centerTapTransformerA.setConfiguration(defaultTransformerA);

        final Transformer centerTapTransformerB = sim.transformer("F1_center_tap_transformer_B");
        centerTapTransformerB.setPhases(PhaseCode.BS);
        centerTapTransformerB.setTo(tripMeterB);
        centerTapTransformerB.setFrom(rootMeter);
        centerTapTransformerB.setConfiguration(defaultTransformerB);

        final Transformer centerTapTransformerC = sim.transformer("F1_center_tap_transformer_C");
        centerTapTransformerC.setPhases(PhaseCode.CS);
        centerTapTransformerC.setTo(tripMeterC);
        centerTapTransformerC.setFrom(rootMeter);
        centerTapTransformerC.setConfiguration(defaultTransformerC);

        // TODO: Move generate house and other convienence methods to a library
        generateHouse(sim, 1, tripMeterA, tripLineConf, auction);

        return sim;
    }

    /**
     * Common properties for the seperated phase transformers
     *
     * @param config
     *            Config to modify
     */
    private static void setupSubstationTransformerBase(final TransformerConfiguration config) {
        config.setConnectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED);
        config.setInstallationType(InstallationType.PADMOUNT);
        config.setPrimaryVoltage(7200);
        config.setSecondaryVoltage(124);
        config.setPowerRating(1500.0);
        config.setImpedance(0.015, 0.0675);
    }

    /**
     * Generate a house to attach to the grid
     *
     * @param sim
     *            Gld simulator reference
     * @param id
     *            The id of this house
     * @param tripMeterA
     *            The meter to connect to
     * @param tripLineConf
     *            The line configuration to use
     * @param auction
     *            the Auction to connect the controller to
     */
    private static void generateHouse(final GldSimulator sim, final int id, final TriplexMeter tripMeterA,
            final TriplexLineConfiguration tripLineConf, final AuctionObject auction) {
        // Create the flatrate meter
        // TODO what is a flatrate meter?
        final TriplexMeter tripMeterFlatrate = sim.triplexMeter("F1_tpm_flatrate_A" + id);
        tripMeterFlatrate.setNominalVoltage(124.0);
        tripMeterFlatrate.setPhases(PhaseCode.AS);
        tripMeterFlatrate.setGroupId("F1_flatrate_meter");

        // Create the line from the transformer
        final TriplexLine tripLine = sim.triplexLine(null);
        tripLine.setFrom(tripMeterA);
        tripLine.setTo(tripMeterFlatrate);
        tripLine.setGroupId("F1_Triplex_Line");
        tripLine.setPhases(PhaseCode.AS);
        tripLine.setLength(10);
        tripLine.setConfiguration(tripLineConf);

        // Create the rt meter
        // TODO what is an rt meter?
        final TriplexMeter tripMeterRt = sim.triplexMeter("F1_tpm_rt_A" + id);
        tripMeterRt.setNominalVoltage(124.0);
        tripMeterRt.setPhases(PhaseCode.AS);
        tripMeterRt.setGroupId("F1_rt_meter");
        tripMeterRt.setParent(tripMeterFlatrate);

        final long scheduleSkew = -685L;

        // Create the house
        final House house = sim.house("F1_H_" + id);
        house.setParent(tripMeterRt);
        house.setScheduleSkew(scheduleSkew);
        house.setRroof(33.69);
        house.setRwall(17.71);
        house.setRfloor(17.02);
        house.setRdoors(5.0);
        house.setRwindows(1.81);
        house.setAirchangePerHour(0.80);
        house.setHvacPowerFactor(0.97);
        house.setCoolingSystemType(CoolingSystemType.ELECTRIC);
        house.setHeatingSystemType(HeatingSystemType.GAS);
        house.setFanType(FanType.ONE_SPEED);
        house.setHvacBreakerRating(200.0);
        house.setTotalThermalMassPerFloorArea(3.01);
        house.setMotorEfficiency(MotorEfficiency.AVERAGE);
        house.setMotorModel(MotorModel.BASIC);
        house.setCoolingCop(3.90);
        house.setFloorArea(1040.0);
        house.setNumberOfDoors(2.0);
        house.setHeatingSetpointFn("heating7*1.017+2.41");

        // Create the controller
        final Controller controller = house.controller("F1_C_" + id);
        controller.setUseOverride(UseOverride.ON);
        controller.setOverride("override");
        controller.setAuction(auction);
        controller.setScheduleSkew(scheduleSkew);
        controller.setBidMode(BidMode.PROXY);
        controller.setProxyDelay(10);
        controller.setControlMode(ControlMode.RAMP);
        controller.setBaseSetpointFn("cooling8*0.977+3.56");
        controller.setSetpoint("cooling_setpoint");
        controller.setTarget("air_temperature");
        controller.setDeadband("thermostat_deadband");
        controller.setUsePredictiveBidding(true);
        controller.setAverageTarget("current_price_mean_24h");
        controller.setStandardDeviationTarget("current_price_stdev_24h");
        controller.setPeriod(300.0);
        controller.setDemand("last_cooling_load");
        controller.setRangeHigh(4.000);
        controller.setRangeLow(-2.000);
        controller.setRampHigh(2.600);
        controller.setRampLow(2.600);
        controller.setTotal("total_load");
        controller.setLoad("hvac_load");
        controller.setState("power_state");
        controller.setNamePrefix("F1_C_");
        controller.setId(id);

        // Generate the loads on the house
        generateLightsLoad(house, scheduleSkew);
        generateClothesWasherLoad(house, scheduleSkew);
        generateRefrigeratorLoad(house, scheduleSkew);
        generateDryerLoad(house, scheduleSkew);
        generateFreezerLoad(house, scheduleSkew);
        generateRangeLoad(house, scheduleSkew);
        generateMicrowaveLoad(house, scheduleSkew);
    }

    /**
     * Generate load on a house for lights
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateLightsLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("LIGHTS*1.8752");
        load.setPowerFraction(0.600000);
        load.setImpedanceFraction(0.400000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(-0.780);
        load.setCurrentPf(0.420);
        load.setImpedancePf(-0.880);
        load.setHeatFraction(0.91);
    }

    /**
     * Generate load on a house for clothes washer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateClothesWasherLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("CLOTHESWASHER*0.4354");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.70);
    }

    /**
     * Generate load on a house for refrigerator
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateRefrigeratorLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("REFRIGERATOR*0.7763");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.86);
    }

    /**
     * Generate load on a house for dryer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateDryerLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("DRYER*1.0019");
        load.setPowerFraction(0.100000);
        load.setImpedanceFraction(0.800000);
        load.setCurrentFraction(0.100000);
        load.setPowerPf(0.900);
        load.setCurrentPf(0.900);
        load.setImpedancePf(1.000);
        load.setHeatFraction(0.77);
    }

    /**
     * Generate load on a house for freezer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateFreezerLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("FREEZER*0.9110");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.80);
    }

    /**
     * Generate load on a house for range
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateRangeLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("RANGE*1.0590");
        load.setPowerFraction(0.000000);
        load.setImpedanceFraction(1.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.000);
        load.setCurrentPf(0.000);
        load.setImpedancePf(1.000);
        load.setHeatFraction(0.86);
    }

    /**
     * Generate load on a house for microwave
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateMicrowaveLoad(final House house, final long scheduleSkew) {
        final ZIPLoad load = house.addLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("MICROWAVE*0.6381");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.94);
    }
}
