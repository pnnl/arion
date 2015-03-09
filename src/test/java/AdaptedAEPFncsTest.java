/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;
import gov.pnnl.prosser.api.ns3.obj.Channel;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * First FNCS experiment
 *
 * @author nord229
 *
 */
public class AdaptedAEPFncsTest {

    private static final Random rand = new Random(13);

    // %% Unchanging? parameters
    // % most of these values were obtained from survey data
    private static final double heat_gas_perc_pre = 0.85;
    private static final double heat_pump_perc_pre = 0.15;

    private static final double heat_gas_perc_post = 0.70;
    private static final double heat_pump_perc_post = 0.30;

    private static final double cool_none_pre = 0.0;
    private static final double cool_pump_pre = 0.0;
    private static final double cool_electric_pre = 1 - cool_none_pre - cool_pump_pre;

    private static final double cool_none_post = 0.0;
    private static final double cool_pump_post = 0.0;
    private static final double cool_electric_post = 1 - cool_none_post - cool_pump_post;

    private static final double wh_perc_elec_pre = 0;
    private static final double wh_perc_elec_post = 0;

    // %house parameters extracting them here
    private static final double smallhome_floorarea_1 = 1100;
    private static final double smallhome_floorarea_2 = 500;

    private static final double largehome_floorarea_1 = 3000;
    private static final double largehome_floorarea_2 = 900;

    private static final double mobilehome_floorarea_1 = 750;

    // % Parameter distributions (same for all) (average +/- range)
    private static final double wtrdem_1 = 1.0;
    private static final double wtrdem_2 = 0.2;
    private static final double cool_1 = 1;
    private static final double cool_2 = 0.05;
    private static final double tankset_1 = 125;
    private static final double tankset_2 = 5;
    private static final double thermdb_1 = 2.0;
    private static final double thermdb_2 = 0.2;
    private static final double cooloffset_1 = 4;
    private static final double cooloffset_2 = 2;
    private static final double heatoffset_1 = 0;
    private static final double heatoffset_2 = 3;
    private static final double basepwr_1 = 1;
    private static final double basepwr_2 = 0.5;
    private static final double tankUA_1 = 2.0;
    private static final double tankUA_2 = 0.2;

    public static void main(final String[] args) throws IOException {
        final Path outPath = Paths.get(args[0]).toRealPath();
        final int numHouses = 1;
        final int numChannels = (numHouses / 20) + 2;
        final String controllerNIPrefix = "F1_C_NI";
        
        
        final List<Channel> channels = new ArrayList<>();
        final GldSimulator gldSim = constructGldSim(numHouses, controllerNIPrefix, channels);
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

        final Ns3Simulator ns3Simulator = new Ns3Simulator();
        // ns3Simulator.setAuctions(auctions);
        // ns3Simulator.setControllers(controllers);
        // ns3Simulator.setGldNodePrefix(controllerPrefix);
        Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve("ns3.cc"), ns3Simulator);
        System.out.println("Written!");
        // TODO FNCS Integration
    }

    /**
     * The gld simulator for this experiment
     * @param controllerNIPrefix Prefix to network controllers in Houses and will be enclosed in the auction object
     * @param channels the network channels to use - channel 0 will be used for the auction, and then channels other than 0 will have up to 20 controllers on it
     *
     * @return
     */
    private static GldSimulator constructGldSim(final int numHouses, final String controllerNIPrefix, final List<Channel> channels) {
        // final boolean useMarket = true;
        final String marketName = "Market1";
        final int marketPeriod = 300;
        final String marketMean = "current_price_mean_24h";
        final String marketStdev = "current_price_stdev_24h";
        // final double percentPenetration = 1;
        // final double sliderSetting = 1;

        // Construct the simulator
        final GldSimulator sim = new GldSimulator("fncs_GLD_1node_Feeder_1");

        // Setup the clock
        final GldClock clock = sim.clock();
        clock.setTimezone("EST+5EDT");
        clock.setStartTime(LocalDateTime.of(2009, 07, 21, 00, 00));
        clock.setStopTime(LocalDateTime.of(2009, 07, 23, 00, 00));

        // Add settings, configures simulation
        sim.addSetting("profiler", "1");
        sim.addSetting("double_format", "%+.12lg");
        sim.addSetting("randomseed", "10");
        sim.addSetting("relax_naming_rules", "1");
        // sim.addSetting("minimum_timestep", "1");

        // Add includes, extra glm files that help with simulation
        sim.addIncludes("water_and_setpoint_schedule_v3.glm", "appliance_schedules.glm");

        // Setup the modules
        sim.marketModule();
        sim.tapeModule();
        sim.commModule();
        sim.climateModule();
        sim.residentialModule("NONE");
        sim.powerflowModule(SolverMethod.FBS, 100L);

        // Add a player class def to allow references to value from player objects
        final PlayerClass playerClass = sim.playerClass();
        playerClass.addField("value", "double");

        // Add a auction class def to allow references to more fields from other objects
        final AuctionClass auctionClass = sim.auctionClass();
        auctionClass.addField("day_ahead_average", "double");
        auctionClass.addField("day_ahead_std", "double");
        auctionClass.addField(marketMean, "double");
        auctionClass.addField(marketStdev, "double");

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
        final AuctionObject auction = sim.auctionObject(marketName);
        auction.setUnit("kW");
        auction.setPeriod(marketPeriod);
        auction.setPriceCap(3.78);
        auction.setTransactionLogFile("log_file_" + sim.getName() + ".csv");
        auction.setCurveLogFile("bid_curve_" + sim.getName() + ".csv");
        auction.setCurveLogInfo(CurveOutput.EXTRA);
        auction.setNetworkAveragePriceProperty(marketMean);
        auction.setNetworkStdevPriceProperty(marketStdev);
        auction.setNetworkAdjustPriceProperty("adjust_price");
        auction.setFncsControllerPrefix(controllerNIPrefix);
        channels.get(0).addAuction(auction);

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
        recorder.setFile("baseprice_clearedprice_clearedquantity_" + sim.getName() + ".csv");

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
        substationConfig.setPowerRating(3 * numHouses * 5.0);
        substationConfig.setPhaseARating(numHouses * 5.0);
        substationConfig.setPhaseBRating(numHouses * 5.0);
        substationConfig.setPhaseCRating(numHouses * 5.0);
        substationConfig.setImpedance(0.0015, 0.00675);

        // Create the transformer configuration for each phase from the substation
        final TransformerConfiguration defaultTransformerA = sim.transformerConfiguration("default_transformer_A");
        defaultTransformerA.setPhaseARating(numHouses * 5.0);
        setupSubstationTransformerBase(defaultTransformerA, numHouses);

        final TransformerConfiguration defaultTransformerB = sim.transformerConfiguration("default_transformer_B");
        defaultTransformerB.setPhaseBRating(numHouses * 5.0);
        setupSubstationTransformerBase(defaultTransformerB, numHouses);

        final TransformerConfiguration defaultTransformerC = sim.transformerConfiguration("default_transformer_C");
        defaultTransformerC.setPhaseCRating(numHouses * 5.0);
        setupSubstationTransformerBase(defaultTransformerC, numHouses);

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
        for (int i = 1; i < numHouses; i++) {
            TriplexMeter meter;
            PhaseCode phase;
            if (i <= numHouses / 3) {
                meter = tripMeterA;
                phase = PhaseCode.A;
            } else if (i <= (numHouses * 2) / 3) {
                meter = tripMeterB;
                phase = PhaseCode.B;
            } else {
                meter = tripMeterC;
                phase = PhaseCode.C;
            }
            int channelId = ((i - 1) / 20) + 1;
            // TODO only put 20 houses on each channel 1-n
            generateHouse(sim, i, meter, tripLineConf, auction, phase, controllerNIPrefix, channels.get(channelId));
        }

        return sim;
    }

    /**
     * Common properties for the seperated phase transformers
     *
     * @param config
     *            Config to modify
     */
    private static void setupSubstationTransformerBase(final TransformerConfiguration config, final int numHouses) {
        config.setConnectionType(ConnectionType.SINGLE_PHASE_CENTER_TAPPED);
        config.setInstallationType(InstallationType.PADMOUNT);
        config.setPrimaryVoltage(7200);
        config.setSecondaryVoltage(124);
        config.setPowerRating(numHouses * 5.0);
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
            final TriplexLineConfiguration tripLineConf, final AuctionObject auction, final PhaseCode phase, final String controllerNIPrefix, final Channel channel) {
        final EnumSet<PhaseCode> phases;
        switch (phase) {
            case A:
                phases = PhaseCode.AS;
                break;
            case B:
                phases = PhaseCode.BS;
                break;
            case C:
                phases = PhaseCode.CS;
                break;
            default:
                throw new RuntimeException("Phase code unsupported " + phase);
        }
        // Create the flatrate meter
        // TODO what is a flatrate meter?
        final TriplexMeter tripMeterFlatrate = sim.triplexMeter("F1_tpm_flatrate_" + phase.name() + id);
        tripMeterFlatrate.setNominalVoltage(124.0);
        tripMeterFlatrate.setPhases(phases);
        tripMeterFlatrate.setGroupId("F1_flatrate_meter");

        // Create the line from the transformer
        final TriplexLine tripLine = sim.triplexLine(null);
        tripLine.setFrom(tripMeterA);
        tripLine.setTo(tripMeterFlatrate);
        tripLine.setGroupId("F1_Triplex_Line");
        tripLine.setPhases(phases);
        tripLine.setLength(10);
        tripLine.setConfiguration(tripLineConf);

        // Create the rt meter
        // TODO what is an rt meter?
        final TriplexMeter tripMeterRt = sim.triplexMeter("F1_tpm_rt_" + phase.name() + id);
        tripMeterRt.setNominalVoltage(124.0);
        tripMeterRt.setPhases(phases);
        tripMeterRt.setGroupId("F1_rt_meter");
        tripMeterRt.setParent(tripMeterFlatrate);

        final double typeRand = rand.nextDouble();
        int houseType;
        if (typeRand <= 0.3) {
            houseType = 1;
        } else if (typeRand > 0.3 && typeRand <= 0.58) {
            houseType = 2;
        } else if (typeRand > 0.58 && typeRand <= 0.75) {
            houseType = 3;
        } else if (typeRand > 0.75 && typeRand <= 0.86) {
            houseType = 4;
        } else if (typeRand > 0.86 && typeRand <= 0.96) {
            houseType = 5;
        } else { // typeRand > 0.96
            houseType = 5;
        }
        //dryer_flag_perc  = rand();
        //dishwasher_flag_perc = rand();
        //freezer_flag_perc = rand();
        double houseLoad = 1;
        double houseVA = 1;

        final long scheduleSkew = -685L;

        // Create the house
        final House house = sim.house("F1_house_" + phase.name() + id);
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
        final Controller controller = house.controller("F1_controller_" + phase.name() + id);
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
        controller.setNetworkInterfaceName(controllerNIPrefix + id);
        channel.addController(controller);

        // Generate the loads on the house
        generateLightsLoad(house, scheduleSkew);
        generateClothesWasherLoad(house, scheduleSkew);
        generateRefrigeratorLoad(house, scheduleSkew);
        generateDryerLoad(house, scheduleSkew);
        generateFreezerLoad(house, scheduleSkew);
        generateRangeLoad(house, scheduleSkew);
        generateMicrowaveLoad(house, scheduleSkew);
    }

    private static void setupResidential1(final House house, final double scaleFloor, final double hvacPowerFactor) {
//        % OLD/SMALL
//        % Distribution of parameters (average +/- range)    
//    %     cool_none_pre = 0.20;
//    %     cool_pump_pre = 0.0;
//    %     cool_electric_pre = 1 - cool_none_pre - cool_pump_pre;
//        Rroof_1=19;
//        Rroof_2=4;
//        Rwall_1=11;
//        Rwall_2=3;             
//        Rfloor_1=11;
//        Rfloor_2=1;
//        Rdoors=3;
//        Rwindows_1=1.25;
//        Rwindows_2=0.5;
//        airchange_1=1;
//        airchange_2=0.2;      
//        floorarea_1=smallhome_floorarea_1;
//        floorarea_2=smallhome_floorarea_2;     
//        tankvol_1=45;
//        tankvol_2=5;
//        heatcap_1=4500;
//        heatcap_2=500;       
//        wh_type1 = 'old';
//        wh_type2 = 'small';
//        hp_perc=heat_pump_perc_pre;
//        g_perc=heat_gas_perc_pre;
//        c_perc=cool_electric_pre;
//        wh_elec = wh_perc_elec_pre;
//        load_scalar = 1;
        setRroof(house, 19, 4);
        setRwall(house, 11, 3);
        setRfloor(house, 11, 1);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5);
        setAirchange(house, 1, 0.2);
        setGenericHouseInfo(house, "Residential1", hvacPowerFactor);
        setFloorarea(house, smallhome_floorarea_1, smallhome_floorarea_2, scaleFloor);
        final double initTemp = 68 + 4*rand.nextDouble();
        house.setAirTemperature(initTemp);
        house.setMassTemperature(initTemp);
    }
    
    private static void setGenericHouseInfo(final House house, final String houseTag, final double hvacPowerFactor) {
        final CoolingSystemType cType = CoolingSystemType.ELECTRIC;
        house.setHvacPowerFactor(hvacPowerFactor);
        house.setCoolingSystemType(cType);
        house.setHeatingSystemType(HeatingSystemType.GAS);
        house.setFanType(FanType.ONE_SPEED);
        house.setHvacBreakerRating(200.0);
        house.setTotalThermalMassPerFloorArea(rand.nextDouble()*2 + 3);
        final double a;
        final double b;
        if(houseTag.equals("Residential5")) {
            a = 2.0;
            b = 3.5; 
        }else if (houseTag.equals("Residential1") || houseTag.equals("Residential3")) {
            a = 2.6;
            b = 3.8;
        }else{
            a = 3.4;
            b = 4.2;
        }

        final double tempRandNum = a + (b-a)*rand.nextDouble();

        if (cType == CoolingSystemType.ELECTRIC) {
            house.setMotorEfficiency(MotorEfficiency.AVERAGE);
            house.setMotorModel(MotorModel.BASIC);
            house.setCoolingCop(tempRandNum);
        }
    }
    
    private static void setRroof(final House house, final double Rroof1, final double Rroof2) {
        house.setRroof((Rroof1-Rroof2)+2*Rroof2*rand.nextDouble());
    }
    
    private static void setRwall(final House house, final double Rwall1, final double Rwall2) {
        house.setRwall((Rwall1-Rwall2)+2*Rwall2*rand.nextDouble());
    }
    
    private static void setRfloor(final House house, final double Rfloor1, final double Rfloor2) {
        house.setRfloor((Rfloor1-Rfloor2)+2*Rfloor2*rand.nextDouble());
    }
    
    private static void setRwindows(final House house, final double Rwindows1, final double Rwindows2) {
        house.setRwindows((Rwindows1-Rwindows2)+2*Rwindows2*rand.nextDouble());
    }
    
    private static void setAirchange(final House house, final double airchange1, final double airchange2) {
        house.setAirchangePerHour((airchange1-airchange2)+2*airchange2*rand.nextDouble());
    }
    
    private static void setFloorarea(final House house, final double floorarea1, final double floorarea2, final double scaleFloor) {
        house.setFloorArea(scaleFloor * (floorarea1-floorarea2)+2*floorarea2*rand.nextDouble());
        house.setNumberOfDoors(Math.ceil(house.getFloorArea()/1000));
    }
    
    private static void setTankvol(final House house, final double Rwindows1, final double Rwindows2) {
        house.setRwindows((Rwindows1-Rwindows2)+2*Rwindows2*rand.nextDouble());
    }
    
    private static void setHeatcap(final House house, final double Rwindows1, final double Rwindows2) {
        house.setRwindows((Rwindows1-Rwindows2)+2*Rwindows2*rand.nextDouble());
    }
    
    private static void setupResidential2(final House house) {
//      % NEW/SMALL
//        % Distribution of parameters (average +/- range)   
//        Rroof_1=30;
//        Rroof_2=5;                
//        Rwall_1=19;
//        Rwall_2=3;             
//        Rfloor_1=15;
//        Rfloor_2=3;              
//        Rdoors=5;
//        Rwindows_1=1.75;
//        Rwindows_2=0.5;      
//        airchange_1=1;
//        airchange_2=0.2;      
//        floorarea_1=smallhome_floorarea_1;
//        floorarea_2=smallhome_floorarea_2;      
//        tankvol_1=45;
//        tankvol_2=5;            
//        heatcap_1=4500;
//        heatcap_2=500;
//        wh_type1 = 'new';
//        wh_type2 = 'small';
//        hp_perc=heat_pump_perc_post; 
//        g_perc=heat_gas_perc_post; 
//        c_perc=cool_electric_post;
//        wh_elec = wh_perc_elec_post;
//        load_scalar = 0.95;
  }
    private static void setupResidential3(final House house) {
//      % OLD/LARGE 
//        % Distribution of parameters (average +/- range)
//        Rroof_1=19;
//        Rroof_2=4;                
//        Rwall_1=11;
//        Rwall_2=3;             
//        Rfloor_1=11;
//        Rfloor_2=1;              
//        Rdoors=3;
//        Rwindows_1=1.25;
//        Rwindows_2=0.5;      
//        airchange_1=1;
//        airchange_2=0.2;      
//        floorarea_1=largehome_floorarea_1;
//        floorarea_2=largehome_floorarea_2;  
//        tankvol_1=55;
//        tankvol_2=5;            
//        heatcap_1=4500;
//        heatcap_2=500;     
//        wh_type1 = 'old';
//        wh_type2 = 'large';
//        hp_perc=heat_pump_perc_pre; 
//        g_perc=heat_gas_perc_pre; 
//        c_perc=cool_electric_pre;
//        wh_elec = wh_perc_elec_pre;
//        load_scalar = 0.85;
  }
    
    private static void setupResidential4(final House house) {
//      % NEW/LARGE
//        % Distribution of parameters (average +/- range)
//        Rroof_1=30;
//        Rroof_2=5;                
//        Rwall_1=19;
//        Rwall_2=3;             
//        Rfloor_1=15;
//        Rfloor_2=3;              
//        Rdoors=5;
//        Rwindows_1=1.75;
//        Rwindows_2=0.5;      
//        airchange_1=1;
//        airchange_2=0.2;      
//        floorarea_1=largehome_floorarea_1-200;
//        floorarea_2=largehome_floorarea_2;      
//        tankvol_1=55;
//        tankvol_2=5;            
//        heatcap_1=4500;
//        heatcap_2=500;
//        wh_type1 = 'new';
//        wh_type2 = 'large';
//        % we'll assume there are no 3000 sq ft new homes w/ resistive heat
//        hp_perc=heat_pump_perc_post; 
//        g_perc=1-hp_perc; 
//        c_perc=cool_electric_post;
//        wh_elec = wh_perc_elec_post;
//        load_scalar = 0.8;
  }
    
    private static void setupResidential5(final House house) {
//      % Mobile homes
//        % Distribution of parameters (average +/- range)        
//        Rroof_1=14;
//        Rroof_2=4;                
//        Rwall_1=6;Rwall_2=2;             
//        Rfloor_1=5;
//        Rfloor_2=1;               
//        Rdoors=3;
//        Rwindows_1=1.25;
//        Rwindows_2=0.5;      
//        airchange_1=1.4;
//        airchange_2=0.2;      
//        floorarea_1=mobilehome_floorarea_1;
//        floorarea_2=150;     
//        tankvol_1=35;
//        tankvol_2=5;            
//        heatcap_1=3500;
//        heatcap_2=500;   
//        wh_type1 = 'old';
//        wh_type2 = 'small';
//    %     hp_perc=0.0; g_perc=0.90; c_perc=cool_electric_pre;
//    %     wh_elec = wh_perc_elec_pre;
//        hp_perc=heat_pump_perc_post; 
//        g_perc=1-hp_perc; 
//        c_perc=cool_electric_post;
//        wh_elec = wh_perc_elec_post;
//        load_scalar = 0.7;    
  }
    
    private static void setupResidential6(final House house) {
//      % Distribution of parameters (average +/- range)
//        Rroof_1=14;
//        Rroof_2=4;                
//        Rwall_1=6;
//        Rwall_2=2;             
//        Rfloor_1=5;
//        Rfloor_2=1;               
//        Rdoors=3;
//        Rwindows_1=1.25;
//        Rwindows_2=0.5;      
//        airchange_1=1.4;
//        airchange_2=0.2;      
//        floorarea_1=500;
//        floorarea_2=200;    
//        tankvol_1=35;
//        tankvol_2=5;            
//        heatcap_1=3500;
//        heatcap_2=500;
//        wh_type1 = 'old';
//        wh_type2 = 'small';
//        hp_perc=heat_pump_perc_pre; 
//        g_perc=heat_gas_perc_pre; 
//        c_perc=cool_electric_pre;
//        wh_elec = wh_perc_elec_pre;
//        load_scalar = 0.5;
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
