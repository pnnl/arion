/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.module.*;
import gov.pnnl.prosser.api.gld.obj.*;

import java.time.*;
import java.util.*;

/**
 * @author nord229
 *
 */

// TODO: More documentation please; hard to parse without; at minimum at top of each class and any methods with more than 25 lines

// TODO: Three important things Sean's Fancy List, SQL stuff, object simulator intersection, extending instead of implementing experiment

// TODO: This should extend Experiment not implement simulators
public class GldBaseFncsTest implements GldSimulator {

    private static final Random rng = new Random(11);

    private static final int numHouses = 300;

    @Override
    public String getName() {
        return "fncs_GLD_100node_Feeder_1";
    }

    @Override
    public List<AbstractProsserObject> getObjects() {
        // TODO: Why is this necessary? Please explain.
        final EnumSet<PhaseCode> phaseAS = EnumSet.copyOf(PhaseCode.S);
        phaseAS.add(PhaseCode.A);
        final EnumSet<PhaseCode> phaseBS = EnumSet.copyOf(PhaseCode.S);
        phaseBS.add(PhaseCode.B);
        final EnumSet<PhaseCode> phaseCS = EnumSet.copyOf(PhaseCode.S);
        phaseCS.add(PhaseCode.C);
        final List<AbstractProsserObject> objects = new ArrayList<>();
        // TODO: Add constructor with SetName parameter
        final PlayerObject phaseALoad = new PlayerObject();
        phaseALoad.setName("phase_A_load");
        // TODO: This should be a path. We may need to create a copy of this file and package as they will not be running on the compiler box
        phaseALoad.setFile("phase_A.player");
        phaseALoad.setLoop(1);
        objects.add(phaseALoad);

        final PlayerObject phaseBLoad = new PlayerObject();
        phaseBLoad.setName("phase_B_load");
        phaseBLoad.setFile("phase_B.player");
        phaseBLoad.setLoop(1);
        objects.add(phaseBLoad);

        final PlayerObject phaseCLoad = new PlayerObject();
        phaseCLoad.setName("phase_C_load");
        phaseCLoad.setFile("phase_C.player");
        phaseCLoad.setLoop(1);
        objects.add(phaseCLoad);
        // TODO: Is it possible to overload one reader and it decide which to used based on file type?
        final CsvReader reader = new CsvReader();
        reader.setName("CSVREADER");
        reader.setFilename("ColumbusWeather2009_2a.csv");
        objects.add(reader);
        // TODO: Why two references to this file? Is this correct? Seems redundant
        final ClimateObject climate = new ClimateObject();
        climate.setName("Columbus OH");
        climate.setTmyFile("ColumbusWeather2009_2a.csv");
        climate.setReader(reader);
        objects.add(climate);

        final AuctionObject auction = new AuctionObject();
        auction.setName("Market1");
        auction.setUnit("kW");
        auction.setPeriod(300);
        auction.setPriceCap(3.78);
        auction.setTransactionLogFile("log_file_fncs_GLD_300node_feeder_1_base_case.csv");
        auction.setCurveLogFile("bid_curve_fncs_GLD_300node_feeder_1_base_case.csv");
        auction.setCurveLogInfo(CurveOutput.EXTRA);
        auction.setNetworkAveragePriceProperty("current_price_mean_24h");
        auction.setNetworkStdevPriceProperty("current_price_stdev_24h");
        auction.setNetworkAdjustPriceProperty("adjust_price");

        final PlayerObject player = new PlayerObject();
        player.setProperty("fixed_price");
        player.setFile("AEP_RT_LMP.player");
        player.setLoop(1);
        auction.setPlayer(player);

        auction.setSpecialMode(SpecialMode.BUYERS_ONLY);

        // TODO: Need to add code for writing SQL commands for generating tables to collect data from all simulator data generating objects
        // TODO: For instance GridLAB-D has an odbc recorder, processor needs to write the appropirate connection info in the glm and the sql to create a table in the database to accept the comunication from gld.
        final Recorder recorder = new Recorder();
        recorder.setProperty("capacity_reference_bid_price, current_market.clearing_price, current_market.clearing_quantity");
        recorder.setLimit(100000000);
        recorder.setInterval(300L);
        recorder.setFile("baseprice_clearedprice_clearedquantity_fncs_GLD_300node_feeder_1.csv");
        auction.setRecorder(recorder);

        auction.setInitPrice(0.042676);
        auction.setInitStdev(0.02);
        auction.setUseFutureMeanPrice(false);
        auction.setWarmup(0);
        // TODO: Create Sean's Fancy List to add this to list at construction time (cross between factory and list)
        objects.add(auction);

        final TransformerConfiguration substationConfig = new TransformerConfiguration();
        substationConfig.setName("substation_config");
        substationConfig.setConnectionType(ConnectionType.WYE_WYE);
        substationConfig.setInstallationType(InstallationType.PADMOUNT);
        substationConfig.setPrimaryVoltage(7200);
        substationConfig.setSecondaryVoltage(7200);
        substationConfig.setPowerRating(4500.0);
        substationConfig.setPhaseARating(1500.0);
        substationConfig.setPhaseBRating(1500.0);
        substationConfig.setPhaseCRating(1500.0);
        substationConfig.setImpedance(0.0015, 0.00675);
        objects.add(substationConfig);

        final TransformerConfiguration defaultTransformerBase = new TransformerConfiguration();
        // TODO: whe pwr namespace for enums? shouldn't this just be under GLD? What if eneryg+ has a similar enum?
        defaultTransformerBase.setConnectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED);
        defaultTransformerBase.setInstallationType(InstallationType.PADMOUNT);
        defaultTransformerBase.setPrimaryVoltage(7200);
        defaultTransformerBase.setSecondaryVoltage(124);
        defaultTransformerBase.setPowerRating(1500.0);
        defaultTransformerBase.setImpedance(0.015, 0.0675);

        final TransformerConfiguration defaultTransformerA = new TransformerConfiguration(defaultTransformerBase);
        defaultTransformerA.setName("default_transformer_A");
        defaultTransformerA.setPhaseARating(1500.0);
        objects.add(defaultTransformerA);

        final TransformerConfiguration defaultTransformerB = new TransformerConfiguration(defaultTransformerBase);
        defaultTransformerB.setName("default_transformer_B");
        defaultTransformerB.setPhaseBRating(1500.0);
        objects.add(defaultTransformerB);

        final TransformerConfiguration defaultTransformerC = new TransformerConfiguration(defaultTransformerBase);
        defaultTransformerC.setName("default_transformer_C");
        defaultTransformerC.setPhaseCRating(1500.0);
        objects.add(defaultTransformerC);

        final TriplexLineConductor tripLineCond1AA = new TriplexLineConductor();
        tripLineCond1AA.setName("Name_1_0_AA_triplex");
        tripLineCond1AA.setResistance(0.57);
        tripLineCond1AA.setGeometricMeanRadius(0.0111);
        objects.add(tripLineCond1AA);

        final TriplexLineConfiguration tripLineConf = new TriplexLineConfiguration();
        tripLineConf.setName("TLCFG");
        tripLineConf.setPhase1Conductor(tripLineCond1AA);
        tripLineConf.setPhase2Conductor(tripLineCond1AA);
        tripLineConf.setPhaseNConductor(tripLineCond1AA);
        tripLineConf.setInsulationThickness(0.08);
        tripLineConf.setDiameter(0.368);
        objects.add(tripLineConf);

        final Substation substation = new Substation();
        substation.setBusType(BusType.SWING);
        substation.setName("substation_root");
        substation.setNominalVoltage(7200.0);
        substation.setReferencePhase(PhaseCode.A);
        substation.setPhases(PhaseCode.ABCN);
        substation.setVoltageA(7200.0, 0.0);
        substation.setVoltageB(-3600.0, -6235.3829);
        substation.setVoltageC(-3600.0, 6235.3829);
        // FIXME add recorder
        objects.add(substation);

        final Meter rootMeter = new Meter();
        rootMeter.setName("F1_transformer_meter");
        rootMeter.setPhases(PhaseCode.ABCN);
        rootMeter.setNominalVoltage(7200.0);

        final Transformer root = new Transformer();
        root.setPhases(PhaseCode.ABCN);
        root.setGroupId("F1_Network_Trans");
        root.setName("F1_Transformer1");
        root.setFrom(substation);
        root.setTo(rootMeter);
        root.setConfiguration(substationConfig);
        objects.add(root);

        objects.add(rootMeter);

        final Load load = new Load();
        load.setName("F1_unresp_load");
        load.setNominalVoltage(7200.0);
        load.setPhases(PhaseCode.ABCN);
        load.setParent(rootMeter);
        load.setPhaseAConstantReal("phase_A_load.value*0.1");
        load.setPhaseBConstantReal("phase_B_load.value*0.1");
        load.setPhaseCConstantReal("phase_C_load.value*0.1");
        objects.add(load);

        final TriplexMeter tripMeterA = new TriplexMeter();
        tripMeterA.setName("F1_triplex_node_A");
        tripMeterA.setPhases(phaseAS);
        tripMeterA.setNominalVoltage(124.00);

        final TriplexMeter tripMeterB = new TriplexMeter();
        tripMeterB.setName("F1_triplex_node_B");
        tripMeterB.setPhases(phaseBS);
        tripMeterB.setNominalVoltage(124.00);

        final TriplexMeter tripMeterC = new TriplexMeter();
        tripMeterC.setName("F1_triplex_node_C");
        tripMeterC.setPhases(phaseCS);
        tripMeterC.setNominalVoltage(124.00);

        final Transformer centerTapTransformerA = new Transformer();
        centerTapTransformerA.setName("F1_center_tap_transformer_A");
        centerTapTransformerA.setPhases(phaseAS);
        centerTapTransformerA.setTo(tripMeterA);
        centerTapTransformerA.setFrom(rootMeter);
        centerTapTransformerA.setConfiguration(defaultTransformerA);
        objects.add(centerTapTransformerA);

        final Transformer centerTapTransformerB = new Transformer();
        centerTapTransformerB.setName("F1_center_tap_transformer_B");
        centerTapTransformerB.setPhases(phaseBS);
        centerTapTransformerB.setTo(tripMeterB);
        centerTapTransformerB.setFrom(rootMeter);
        centerTapTransformerB.setConfiguration(defaultTransformerB);
        objects.add(centerTapTransformerB);

        final Transformer centerTapTransformerC = new Transformer();
        centerTapTransformerC.setName("F1_center_tap_transformer_C");
        centerTapTransformerC.setPhases(phaseCS);
        centerTapTransformerC.setTo(tripMeterC);
        centerTapTransformerC.setFrom(rootMeter);
        centerTapTransformerC.setConfiguration(defaultTransformerC);
        objects.add(centerTapTransformerC);

        objects.add(tripMeterA);
        objects.add(tripMeterB);
        objects.add(tripMeterC);

        // Base Setup done, houses below
        // TODO: Move generate house and other convienence methods to a library
        objects.addAll(this.generateHouseObjects(1, tripMeterA, tripLineConf, auction));

        return objects;
    }

    // TODO: Never force overrides in the users experiment
    @Override
    public GldClock getClock() {
        final GldClock clock = new GldClock();
        clock.setTimezone("EST+5EDT");
        clock.setStartTime(LocalDateTime.of(2009, 07, 21, 00, 00));
        clock.setStopTime(LocalDateTime.of(2009, 07, 23, 00, 00));
        return clock;
    }

    @Override
    // TODO: This should probably just be a list in Experiment
    public List<Module> getModules() {
        return ExperimentBuilder.module()
                .addMarket()
                .addTape()
                .addComm()
                .addClimate()
                .addResidential().implicitEnduses("NONE").and()
                .addPowerflow().solverMethod(SolverMethod.FBS).nrIterationLimit(100L).and()
                .build();
    }

    @Override
    public Map<String, String> getSettings() {
        final Map<String, String> map = new HashMap<>();
        map.put("profiler", "1");
        map.put("double_format", "%+.12lg");
        map.put("randomseed", "10");
        map.put("relax_naming_rules", "1");
        return map;
    }

    @Override
    public String[] getIncludes() {
        return new String[] { "water_and_setpoint_schedule_v3.glm", "appliance_schedules.glm" };
    }

    @Override
    public List<AbstractGldClass> getClasses() {
        final List<AbstractGldClass> classes = new ArrayList<>();

        final PlayerClass player = new PlayerClass();
        player.addField("value", "double");
        classes.add(player);

        final AuctionClass auction = new AuctionClass();
        auction.addField("day_ahead_average", "double");
        auction.addField("day_ahead_std", "double");
        auction.addField("current_price_mean_24h", "double");
        auction.addField("current_price_stdev_24h", "double");
        classes.add(auction);
        return classes;
    }

    private List<AbstractProsserObject> generateHouseObjects(final int id, final TriplexMeter tripMeterA,
            final TriplexLineConfiguration tripLineConf, final AuctionObject auction) {
        final EnumSet<PhaseCode> phaseAS = EnumSet.copyOf(PhaseCode.S);
        phaseAS.add(PhaseCode.A);
        final List<AbstractProsserObject> objects = new ArrayList<>();

        final TriplexMeter tripMeterFlatrate = new TriplexMeter();
        tripMeterFlatrate.setNominalVoltage(124.0);
        tripMeterFlatrate.setPhases(phaseAS);
        tripMeterFlatrate.setName("F1_tpm_flatrate_A" + id);
        tripMeterFlatrate.setGroupId("F1_flatrate_meter");

        final TriplexLine tripLine = new TriplexLine();
        tripLine.setFrom(tripMeterA);
        tripLine.setTo(tripMeterFlatrate);
        tripLine.setGroupId("F1_Triplex_Line");
        tripLine.setPhases(phaseAS);
        tripLine.setLength(10);
        tripLine.setConfiguration(tripLineConf);
        objects.add(tripLine);
        objects.add(tripMeterFlatrate);

        final TriplexMeter tripMeterRt = new TriplexMeter();
        tripMeterRt.setNominalVoltage(124.0);
        tripMeterRt.setPhases(phaseAS);
        tripMeterRt.setName("F1_tpm_rt_A" + id);
        tripMeterRt.setGroupId("F1_rt_meter");
        tripMeterRt.setParent(tripMeterFlatrate);
        objects.add(tripMeterRt);

        final long scheduleSkew = -685L;

        final House house = new House();
        house.setName("F1_H_" + id);
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

        final Controller controller = new Controller();
        controller.setName("F1_C_" + id);
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
        house.setController(controller);

        house.addLoad(this.generateLightsLoad(scheduleSkew));
        house.addLoad(this.generateClothesWasherLoad(scheduleSkew));
        house.addLoad(this.generateRefrigeratorLoad(scheduleSkew));
        house.addLoad(this.generateDryerLoad(scheduleSkew));
        house.addLoad(this.generateFreezerLoad(scheduleSkew));
        house.addLoad(this.generateRangeLoad(scheduleSkew));
        house.addLoad(this.generateMicrowaveLoad(scheduleSkew));
        objects.add(house);

        return objects;
    }

    private ZIPLoad generateLightsLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("LIGHTS*1.8752");
        load.setPowerFraction(0.600000);
        load.setImpedanceFraction(0.400000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(-0.780);
        load.setCurrentPf(0.420);
        load.setImpedancePf(-0.880);
        load.setHeatFraction(0.91);
        return load;
    }

    private ZIPLoad generateClothesWasherLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("CLOTHESWASHER*0.4354");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.70);
        return load;
    }

    private ZIPLoad generateRefrigeratorLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("REFRIGERATOR*0.7763");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.86);
        return load;
    }

    private ZIPLoad generateDryerLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("DRYER*1.0019");
        load.setPowerFraction(0.100000);
        load.setImpedanceFraction(0.800000);
        load.setCurrentFraction(0.100000);
        load.setPowerPf(0.900);
        load.setCurrentPf(0.900);
        load.setImpedancePf(1.000);
        load.setHeatFraction(0.77);
        return load;
    }

    private ZIPLoad generateFreezerLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("FREEZER*0.9110");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.80);
        return load;
    }

    private ZIPLoad generateRangeLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("RANGE*1.0590");
        load.setPowerFraction(0.000000);
        load.setImpedanceFraction(1.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.000);
        load.setCurrentPf(0.000);
        load.setImpedancePf(1.000);
        load.setHeatFraction(0.86);
        return load;
    }

    private ZIPLoad generateMicrowaveLoad(final long scheduleSkew) {
        final ZIPLoad load = new ZIPLoad();
        load.setScheduleSkew(scheduleSkew);
        load.setBasePowerFn("MICROWAVE*0.6381");
        load.setPowerFraction(1.000000);
        load.setImpedanceFraction(0.000000);
        load.setCurrentFraction(0.000000);
        load.setPowerPf(0.970);
        load.setCurrentPf(0.970);
        load.setImpedancePf(0.970);
        load.setHeatFraction(0.94);
        return load;
    }

}
