/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;
import gov.pnnl.prosser.api.ns3.obj.*;
import gov.pnnl.prosser.api.ns3.obj.Node;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4StaticRouting;
import gov.pnnl.prosser.api.ns3.obj.internet.Ipv4StaticRoutingHelper;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointChannel;

import java.nio.file.*;
import java.time.*;
import java.util.*;

/**
 * First FNCS experiment
 *
 * @author nord229
 *
 */
public class Ns3OnlyTest extends Experiment {

    /**
     * Generate the experiment
     */
    @Override
    public void experiment() {
    	        
        Router a = new Router("router_a");
		Router b = new Router("router_b");
		Router c = new Router("router_c");
		
		Channel a2b = new CsmaChannel("a2b");
		Channel b2c = new CsmaChannel("b2c");
		
		a.setChannel(a2b);
		b.setChannel(a2b);
		
		b.setChannel(b2c);
		c.setChannel(b2c);
		
		//a.AddStaticRoute(dest, hop, interFace);
		
		Node na = a.getNode();
		Node nb = b.getNode();
		Node nc = c.getNode();
		
		NetDevice aDevice = na.getDevice(0);
		NetDevice bDevice = nb.getDevice(0);
		NetDevice cDevice = nc.getDevice(0);
		
		
		//get NetDevice, from NetDevice, get Interface, give the interface# to the ipv4 object to get the address
		
		Ipv4 ia = na.getObjectIpv4();
		Ipv4 ib = nb.getObjectIpv4();
		Ipv4 ic = nc.getObjectIpv4();
		
		
		
		Ipv4StaticRoutingHelper staticHelper = new Ipv4StaticRoutingHelper("staticHelper");
		Ipv4StaticRouting isrA = staticHelper.GetStaticRouting(ia);
		Ipv4StaticRouting isrB = staticHelper.GetStaticRouting(ib);
		Ipv4StaticRouting isrC = staticHelper.GetStaticRouting(ic);
		
		//isrA->AddHostRouteTo (, Ipv4Address ("10.1.1.2"), 1);
		
		
		
		
		
		
		// This is example of how network setup could be automated somewhat by user
        // Equal number of houses per backbone router not hardcoded into Prosser
        final int numHouses = 1;
        final int numHousesPerBackbone = 1;
        final int numBackboneRouters = numHouses / numHousesPerBackbone + 1;
        final int numAuctions = 1;

        final Ns3Simulator ns3Sim = this.ns3Simulator("ns3");
        ns3Sim.setup(numAuctions);
		
        // Creates Auction Channels
        final PointToPointChannel auctionChannel0 = new PointToPointChannel("auctionChannel0");
        auctionChannel0.setDataRate("1Gbps");
        auctionChannel0.setDelay("1ms");

        // Create auction Routers with PCAP and ASCII debugging set to true
        final Router auctionRouter0 = ns3Sim.auctionRouter(auctionChannel0, true);


        // Creates the specified number of house Routers and attaches them to backbone Routers
        for (int i = 0; i < numBackboneRouters; i++) {

            // Creates backbone router to connect houses and auction
            Router backboneRouter = null;

            List<Channel> auctionChannels = ns3Sim.getAuctionChannels();
            // Can add more than one auction channel here
            if (i < auctionChannels.size()) {
                backboneRouter = ns3Sim.backboneRouter(auctionChannels.get(i));
            }

            for (int j = 0; j < numHousesPerBackbone; j++) {
                if ((i * numHousesPerBackbone + j) < numHouses) {

                    CsmaChannel houseChannel = new CsmaChannel("csmaHouseChannel_" + i + "_" + j);
                    houseChannel.setDataRate("100Mbps");
                    houseChannel.setDelay("10ms");
                    houseChannel.setIPBase("10.1.1.0");
                    houseChannel.setIPMask("255.255.255.0");//(i / numHousesPerBackbone + 1) + "." + (i % numHousesPerBackbone + 1) + "." + (j + 1) + ".0");

                    // Connects house router and a backbone router to the house channel
                    Router houseRouter = ns3Sim.houseRouter(houseChannel);
                    if (backboneRouter == null) {
                        backboneRouter = ns3Sim.backboneRouter(houseChannel);
                    }
                    else {
                        backboneRouter.setChannel(houseChannel);
                    }
                    // Assign IP address
                    //houseChannel.assignIPAddresses();
                    // Create static routes
                    

                }
            }
        }

        // Assign IP addresses to routers on all channels (House channels)
        for (Channel chan : ns3Sim.getChannels()) {
            chan.assignIPAddresses();
        }

        // Sets up global IPv4 routing tables on each Router
        ns3Sim.setupGlobalRouting();


        final GldSimulator gldSim = this.gldSimulator("fncs_GLD_1node_Feeder_1");

        List<String> auctionNames = ns3Sim.getAuctionNames();

        final AuctionObject auction0 = createMarket(gldSim, auctionNames.get(0));
        auction0.setFncsControllerPrefix();
        auctionChannel0.addAuction(auction0);

        // Specify the climate information
        final ClimateObject climate = gldSim.climateObject("Columbus OH");
        climate.setTmyFile(Paths.get("res/ColumbusWeather2009_2a.csv"));
        climate.addCsvReader("CSVREADER");

        // Add a recorder to the auction for some of the properties on the auction
        final Recorder recorder0 = auction0.recorder("capacity_reference_bid_price", "current_market.clearing_price", "current_market.clearing_quantity");
        recorder0.setLimit(100000000);
        recorder0.setInterval(300L);
        recorder0.setUsingSql(true);


        createTriplex(gldSim, numHouses);

        final List<Channel> houseChannels = ns3Sim.getHouseChannels();

        for (int i = 0; i < numHouses; i++) {
            final House house;
            house = createHouse(gldSim, i, auction0);
            houseChannels.get(i).addController(house.getController());
        }

        // Extra GLD files
//        this.addExtraFiles(Paths.get("res/heat.yaml"));
        // this.addExtraFiles(Paths.get("res/tzinfo.txt"), Paths.get("res/unitfile.txt"));
    }


    private static final Random rand = new Random(13);

    // %% Unchanging? parameters
    // % most of these values were obtained from survey data

    // %house parameters extracting them here
    private static final double smallhome_floorarea_1 = 1100;

    private static final double smallhome_floorarea_2 = 500;

    private static final double largehome_floorarea_1 = 3000;

    private static final double largehome_floorarea_2 = 900;

    private static final double mobilehome_floorarea_1 = 750;

    // % Parameter distributions (same for all) (average +/- range)
    private static final double cool_1 = 1;

    private static final double cool_2 = 0.05;

    private static final double cooloffset_1 = 4;

    private static final double cooloffset_2 = 2;

    private static final double heatoffset_1 = 0;

    private static final double heatoffset_2 = 3;

    private static final double basepwr_1 = 1;

    private static final double basepwr_2 = 0.5;

    private static final double z = 0;

    private static final double i = 0;

    private static final double p = 1;

    private static final double systemPf = 0.97;

    private static final double heatFraction = 0.8;

    private static final double hvacPf = 0.97;

    private static final double scaleFloor = 1;

    private static final double sigmaTstat = 2;

    private static final String marketMean = "current_price_mean_24h";

    private static final String marketStdev = "current_price_stdev_24h";

    private static final int marketPeriod = 300;

    private TriplexMeter tripMeterA;

    private TriplexMeter tripMeterB;

    private TriplexMeter tripMeterC;

    private TriplexLineConfiguration tripLineConf;

    private AuctionObject createMarket(final GldSimulator sim, final String marketName) {
        // final boolean useMarket = true;
        // final String marketName = "Market1";
        // final double percentPenetration = 1;
        // final double sliderSetting = 1;

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
        sim.addIncludes(Paths.get("res/water_and_setpoint_schedule_v3.glm"), Paths.get("res/appliance_schedules.glm"));

        // Modules are setup by default as we add objects

        // Add a player class def to allow references to value from player objects
        final PlayerClass playerClass = sim.playerClass();
        playerClass.addField("value", "double");

        // Add a auction class def to allow references to more fields from other objects
        final AuctionClass auctionClass = sim.auctionClass();
        auctionClass.addField("day_ahead_average", "double");
        auctionClass.addField("day_ahead_std", "double");
        auctionClass.addField(marketMean, "double");
        auctionClass.addField(marketStdev, "double");

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

        // Add a player to the auction for one of its values
        final PlayerObject player = auction.player();
        player.setProperty("fixed_price");
        player.setFile(Paths.get("res/AEP_RT_LMP.player"));
        player.setLoop(1);

        auction.setSpecialMode(SpecialMode.BUYERS_ONLY);

        auction.setInitPrice(0.042676);
        auction.setInitStdev(0.02);
        auction.setUseFutureMeanPrice(false);
        auction.setWarmup(0);

        auction.setFncsControllerPrefix();

        return auction;
    }

    private void createTriplex(final GldSimulator sim, final int numHouses) {
        // Create Player for the Phase A load on the substation
        final PlayerObject phaseALoad = sim.playerObject("phase_A_load");
        phaseALoad.setFile(Paths.get("res/phase_A.player"));
        phaseALoad.setLoop(1);

        // Create Player for the Phase B load on the substation
        final PlayerObject phaseBLoad = sim.playerObject("phase_B_load");
        phaseBLoad.setFile(Paths.get("res/phase_B.player"));
        phaseBLoad.setLoop(1);

        // Create Player for the Phase C load on the substation
        final PlayerObject phaseCLoad = sim.playerObject("phase_C_load");
        phaseCLoad.setFile(Paths.get("res/phase_C.player"));
        phaseCLoad.setLoop(1);

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
        tripLineConf = sim.triplexLineConfiguration("TLCFG");
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
        final Transformer root = sim.transformer("F1_Transformer1", substationConfig);
        root.setPhases(PhaseCode.ABCN);
        root.setGroupId("F1_Network_Trans");
        root.setFrom(substation);
        root.setTo(rootMeter);

        // Create load on the meter
        final Load load = sim.load("F1_unresp_load");
        load.setNominalVoltage(7200.0);
        load.setPhases(PhaseCode.ABCN);
        load.setParent(rootMeter);
        load.setPhaseAConstantReal("phase_A_load.value*0.1");
        load.setPhaseBConstantReal("phase_B_load.value*0.1");
        load.setPhaseCConstantReal("phase_C_load.value*0.1");

        // Create the seperated phase meters for the house groups
        tripMeterA = sim.triplexMeter("F1_triplex_node_A");
        tripMeterA.setPhases(PhaseCode.AS);
        tripMeterA.setNominalVoltage(124.00);

        tripMeterB = sim.triplexMeter("F1_triplex_node_B");
        tripMeterB.setPhases(PhaseCode.BS);
        tripMeterB.setNominalVoltage(124.00);

        tripMeterC = sim.triplexMeter("F1_triplex_node_C");
        tripMeterC.setPhases(PhaseCode.CS);
        tripMeterC.setNominalVoltage(124.00);

        // Create the transformers to feed the house groups
        final Transformer centerTapTransformerA = sim.transformer("F1_center_tap_transformer_A", defaultTransformerA);
        centerTapTransformerA.setPhases(PhaseCode.AS);
        centerTapTransformerA.setTo(tripMeterA);
        centerTapTransformerA.setFrom(rootMeter);

        final Transformer centerTapTransformerB = sim.transformer("F1_center_tap_transformer_B", defaultTransformerB);
        centerTapTransformerB.setPhases(PhaseCode.BS);
        centerTapTransformerB.setTo(tripMeterB);
        centerTapTransformerB.setFrom(rootMeter);

        final Transformer centerTapTransformerC = sim.transformer("F1_center_tap_transformer_C", defaultTransformerC);
        centerTapTransformerC.setPhases(PhaseCode.CS);
        centerTapTransformerC.setTo(tripMeterC);
        centerTapTransformerC.setFrom(rootMeter);
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
    private House createHouse(final GldSimulator sim, final int id, final AuctionObject auction) {
        // Select the phases for our meters
        final int r = rand.nextInt(3);
        final PhaseCode phase;
        final EnumSet<PhaseCode> phases;
        final TriplexMeter meter;
        switch (r) {
            case 0:
                phase = PhaseCode.A;
                phases = PhaseCode.AS;
                meter = tripMeterA;
                break;
            case 1:
                phase = PhaseCode.B;
                phases = PhaseCode.BS;
                meter = tripMeterB;
                break;
            case 2:
                phase = PhaseCode.C;
                phases = PhaseCode.CS;
                meter = tripMeterC;
                break;
            default:
                throw new RuntimeException("Invalid random number");
        }

        // Create the flatrate meter
        // TODO what is a flatrate meter?
        final TriplexMeter tripMeterFlatrate = sim.triplexMeter("F1_tpm_flatrate_" + phase.name() + id);
        tripMeterFlatrate.setNominalVoltage(124.0);
        tripMeterFlatrate.setPhases(phases);
        tripMeterFlatrate.setGroupId("F1_flatrate_meter");

        // Create the line from the transformer
        final TriplexLine tripLine = sim.triplexLine(null);
        tripLine.setFrom(meter);
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

        // Randomize the schedule skew
        long scheduleSkew = Math.round(1200 * rand.nextGaussian());
        if (scheduleSkew > 3600) {
            scheduleSkew = 3600;
        } else if (scheduleSkew < -3600) {
            scheduleSkew = -3600;
        }

        // Create the base house
        final House house = sim.house("F1_house_" + phase.name() + id);
        house.setParent(tripMeterRt);
        house.setScheduleSkew(scheduleSkew);

        final double[] applianceScalar = new double[] { 2, 1, 1, 1, 1, 1, 1, 1 };
        // Determine the type of house
        final double typeRand = rand.nextDouble();
        final HouseType houseType;
        if (typeRand <= 0.3) {
            houseType = HouseType.RESIDENTIAL1;
        } else if (typeRand > 0.3 && typeRand <= 0.58) {
            houseType = HouseType.RESIDENTIAL2;
        } else if (typeRand > 0.58 && typeRand <= 0.75) {
            houseType = HouseType.RESIDENTIAL3;
        } else if (typeRand > 0.75 && typeRand <= 0.86) {
            houseType = HouseType.RESIDENTIAL4;
        } else if (typeRand > 0.86 && typeRand <= 0.96) {
            houseType = HouseType.RESIDENTIAL5;
        } else { // typeRand > 0.96
            houseType = HouseType.RESIDENTIAL5;
        }
        // RESIDENTIAL6 = Apartments are special cases and not covered in the original script (code was commented out)
        // Setup base house details
        setHouseInfo(house, houseType);

        // Create the base controller
        final Controller controller = house.controller("F1_controller_" + phase.name() + id);
        controller.setAuction(auction);
        controller.setScheduleSkew(scheduleSkew);
        controller.setNetworkInterfaceName(auction.getFncsControllerPrefix() + id);
        controller.setAverageTarget(auction.getNetworkAveragePriceProperty());
        controller.setStandardDeviationTarget(auction.getNetworkStdevPriceProperty());
        controller.setPeriod((double) auction.getPeriod());
        // Setup the controller and loads
        setupController(house, controller);
        setupLoads(house, houseType, applianceScalar);

        // If we want to track this item, add a recorder
        // if (track) {
        // final Recorder recorder = house.recorder();
        // recorder.setProperty("cooling_setpoint,air_temperature");
        // recorder.setLimit(100000000);
        // recorder.setInterval(300L);
        // recorder.setFile("F1_house" + id + "_details.csv");
        // }
        return house;
    }

    private static void setHouseInfo(final House house, final HouseType houseType) {
        // Setup the specific values
        switch (houseType) {
            case RESIDENTIAL1:
                setupResidential1(house);
                break;
            case RESIDENTIAL2:
                setupResidential2(house);
                break;
            case RESIDENTIAL3:
                setupResidential3(house);
                break;
            case RESIDENTIAL4:
                setupResidential4(house);
                break;
            case RESIDENTIAL5:
                setupResidential5(house);
                break;
            // case RESIDENTIAL6:
            // setupResidential6(house);
            // break;
            default:
                throw new RuntimeException("Unable to handle house type " + houseType);
        }
        // Setup the common values
        house.setHeatingSystemType(HeatingSystemType.GAS);
        house.setFanType(FanType.ONE_SPEED);
        house.setCoolingSystemType(CoolingSystemType.ELECTRIC);
        house.setHvacPowerFactor(hvacPf);
        house.setHvacBreakerRating(200.0);
        house.setTotalThermalMassPerFloorArea(rand.nextDouble() * 2 + 3);
        final double a;
        final double b;
        if (houseType == HouseType.RESIDENTIAL5) {
            a = 2.0;
            b = 3.5;
        } else if (houseType == HouseType.RESIDENTIAL1 || houseType == HouseType.RESIDENTIAL3) {
            a = 2.6;
            b = 3.8;
        } else {
            a = 3.4;
            b = 4.2;
        }

        final double coolingCopVal = a + (b - a) * rand.nextDouble();

        house.setMotorEfficiency(MotorEfficiency.AVERAGE);
        house.setMotorModel(MotorModel.BASIC);
        house.setCoolingCop(coolingCopVal);

        final double initTemp = 68 + 4 * rand.nextDouble();
        house.setAirTemperature(initTemp);
        house.setMassTemperature(initTemp);
    }

    /**
     * Setup the house for Old/Small
     *
     * @param house
     *            the house to setup
     */
    private static void setupResidential1(final House house) {
        // tankvol_1=45;
        // tankvol_2=5;
        // heatcap_1=4500;
        // heatcap_2=500;
        // wh_type1 = 'old';
        // wh_type2 = 'small';
        // wh_elec = wh_perc_elec_pre;
        // load_scalar = 1;
        setRroof(house, 19, 4);
        setRwall(house, 11, 3);
        setRfloor(house, 11, 1);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5);
        setAirchange(house, 1, 0.2);
        setFloorarea(house, smallhome_floorarea_1, smallhome_floorarea_2, scaleFloor);
    }

    /**
     * Setup the house for New/Small
     *
     * @param house
     *            the house to setup
     */
    private static void setupResidential2(final House house) {
        // tankvol_1=45;
        // tankvol_2=5;
        // heatcap_1=4500;
        // heatcap_2=500;
        // wh_type1 = 'new';
        // wh_type2 = 'small';
        // wh_elec = wh_perc_elec_post;
        // load_scalar = 0.95;
        setRroof(house, 30, 5);
        setRwall(house, 19, 3);
        setRfloor(house, 15, 3);
        house.setRdoors(5.0);
        setRwindows(house, 1.75, 0.5);
        setAirchange(house, 1, 0.2);
        setFloorarea(house, smallhome_floorarea_1, smallhome_floorarea_2, scaleFloor);
    }

    /**
     * Setup the house for Old/Large
     *
     * @param house
     *            the house to setup
     */
    private static void setupResidential3(final House house) {
        // tankvol_1=55;
        // tankvol_2=5;
        // heatcap_1=4500;
        // heatcap_2=500;
        // wh_type1 = 'old';
        // wh_type2 = 'large';
        // wh_elec = wh_perc_elec_pre;
        // load_scalar = 0.85;
        setRroof(house, 19, 4);
        setRwall(house, 11, 3);
        setRfloor(house, 11, 1);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5);
        setAirchange(house, 1, 0.2);
        setFloorarea(house, largehome_floorarea_1, largehome_floorarea_2, scaleFloor);
    }

    /**
     * Setup the house for New/Large
     *
     * @param house
     *            the house to setup
     */
    private static void setupResidential4(final House house) {
        // tankvol_1=55;
        // tankvol_2=5;
        // heatcap_1=4500;
        // heatcap_2=500;
        // wh_type1 = 'new';
        // wh_type2 = 'large';
        // wh_elec = wh_perc_elec_post;
        // load_scalar = 0.8;
        setRroof(house, 30, 5);
        setRwall(house, 19, 3);
        setRfloor(house, 15, 3);
        house.setRdoors(5.0);
        setRwindows(house, 1.75, 0.5);
        setAirchange(house, 1, 0.2);
        setFloorarea(house, largehome_floorarea_1 - 200, largehome_floorarea_2, scaleFloor);
    }

    /**
     * Setup the house for Mobile Homes
     *
     * @param house
     *            the house to setup
     */
    private static void setupResidential5(final House house) {
        // tankvol_1=35;
        // tankvol_2=5;
        // heatcap_1=3500;
        // heatcap_2=500;
        // wh_type1 = 'old';
        // wh_type2 = 'small';
        // wh_elec = wh_perc_elec_post;
        // load_scalar = 0.7;
        setRroof(house, 14, 4);
        setRwall(house, 6, 2);
        setRfloor(house, 5, 1);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5);
        setAirchange(house, 1.4, 0.2);
        setFloorarea(house, mobilehome_floorarea_1, 150, scaleFloor);
    }

    private static void setupController(final House house, final Controller controller) {
        // we need +1 to skip zeros
        final int scheduleCool = rand.nextInt(8) + 1;
        final double coolOffset = (cooloffset_1 - cooloffset_2) + 2 * cooloffset_2 * rand.nextDouble();
        final double coolTemp = (cool_1 - cool_2) + 2 * cool_2 * rand.nextDouble();

        // we need +1 to skip zeros
        final int scheduleHeat = rand.nextInt(8) + 1;
        final double heatOffset = heatoffset_1 + heatoffset_2 * rand.nextDouble();
        final double heatTemp = (cool_1 - cool_2) + 2 * cool_2 * rand.nextDouble();

        house.setCoolingSetpointFn(String.format("cooling%d*%1.3f+%2.2f", scheduleCool, coolTemp, coolOffset));
        house.setHeatingSetpointFn(String.format("heating%d*%1.3f+%2.2f", scheduleHeat, heatTemp, heatOffset));

        // long bidDelay = 30 + Math.round((90 - 30) * rand.nextDouble());
        final double marketTest = rand.nextDouble();
        double coolSlider;
        if (marketTest <= 0.17) {
            coolSlider = 0.0;
        } else if (marketTest <= 0.27) {
            coolSlider = 0.2;
        } else if (marketTest <= 0.62) {
            coolSlider = 0.4;
        } else if (marketTest <= 0.87) {
            coolSlider = 0.6;
        } else if (marketTest <= 0.97) {
            coolSlider = 0.8;
        } else { // marketTest <= 1.0
            coolSlider = 1.0;
        }
        // marketTest = marketTest / 100;

        controller.setUseOverride(UseOverride.ON);
        controller.setOverride("override");
        controller.setBidMode(BidMode.PROXY);
        controller.setProxyDelay(10);
        controller.setControlMode(ControlMode.RAMP);

        // FIXME Bid delay does not apper in our output files?
        // controller.setBidDelay(bidDelay);

        controller.setBaseSetpointFn(String.format("cooling%d*%1.3f+%2.2f", scheduleCool, coolTemp, coolOffset));
        controller.setSetpoint("cooling_setpoint");
        controller.setTarget("air_temperature");
        controller.setDeadband("thermostat_deadband");
        controller.setUsePredictiveBidding(true);

        controller.setDemand("last_cooling_load");
        if (sigmaTstat > 0) {
            final double slider = coolSlider;
            final double crh = 10 - 10 * (1 - slider);
            final double crl = -5 + 5 * (1 - slider);
            final double crh2 = sigmaTstat + (1 - slider) * (3 - sigmaTstat);
            final double crl2 = sigmaTstat + (1 - slider) * (3 - sigmaTstat);
            controller.setRangeHigh(crh);
            controller.setRangeLow(crl);
            controller.setRampHigh(crh2);
            controller.setRampLow(crl2);
        } else {
            controller.setSliderSetting(coolSlider);
            controller.setRangeHigh(5.0);
            controller.setRangeLow(-3.0);
        }
        controller.setTotal("total_load");
        controller.setLoad("hvac_load");
        controller.setState("power_state");

    }

    private static void setupLoads(final House house, final HouseType houseType, final double[] applianceScalar) {
        // Setup the Loads
        final double dryerFlagPerc = rand.nextDouble();
        final double dishwasherFlagPerc = rand.nextDouble();
        final double freezerFlagPerc = rand.nextDouble();
        boolean dryerPresent = true;
        boolean freezerPresent = true;
        boolean dishwasherPresent = true;
        switch (houseType) {
            case RESIDENTIAL1:
            case RESIDENTIAL3:
            case RESIDENTIAL5:
                if (dryerFlagPerc > 0.65) {
                    dryerPresent = false;
                }
                break;
            default:
                break;
        }
        switch (houseType) {
            case RESIDENTIAL1:
            case RESIDENTIAL2:
            case RESIDENTIAL5:
                if (dishwasherFlagPerc > 0.65) {
                    // FIXME in the script this sets dryer present instead of dishwasher present
                    dishwasherPresent = false;
                }
                freezerPresent = false;
                break;
            default:
                break;
        }
        switch (houseType) {
            case RESIDENTIAL3:
            case RESIDENTIAL4:
                if (freezerFlagPerc > 0.5) {
                    freezerPresent = false;
                }
                break;
            default:
                break;
        }
        if (houseType == HouseType.RESIDENTIAL1) {
            dishwasherPresent = false;
        }
        // FIXME there is a crazy if statement in the matlab code that doesn't make sense
        generateLightsLoad(house, applianceScalar[0]);
        generateClothesWasherLoad(house, applianceScalar[1]);
        generateRefrigeratorLoad(house, applianceScalar[2]);
        if (dryerPresent) {
            generateDryerLoad(house, applianceScalar[3]);
        }
        if (freezerPresent) {
            generateFreezerLoad(house, applianceScalar[4]);
        }
        if (dishwasherPresent) {
            generateDishwasherLoad(house, applianceScalar[5]);
        }
        generateRangeLoad(house, applianceScalar[6]);
        generateMicrowaveLoad(house, applianceScalar[7]);
        // FIXME Water heater exists in script but not in our glm demo files?
    }

    /**
     * Generate load on a house for lights
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateLightsLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            final boolean cfl = rand.nextBoolean();
            if (cfl) {
                load.setPowerFraction(0.6);
                load.setImpedanceFraction(0.4);
                load.setCurrentFraction(0.0);
                load.setPowerPf(-0.78);
                load.setImpedancePf(-0.88);
                load.setCurrentPf(0.42);
            } else {
                load.setPowerFraction(0.0);
                load.setImpedanceFraction(1.0);
                load.setCurrentFraction(0.0);
                load.setPowerPf(0.0);
                load.setImpedancePf(1.0);
                load.setCurrentPf(0.0);
            }

            setBasePower(load, house, scalar, "LIGHTS");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for clothes washer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateClothesWasherLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "CLOTHESWASHER");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for refrigerator
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateRefrigeratorLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "REFRIGERATOR");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for dryer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateDryerLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(0.1);
            load.setImpedanceFraction(0.8);
            load.setCurrentFraction(0.1);
            load.setPowerPf(0.9);
            load.setImpedancePf(1.0);
            load.setCurrentPf(0.9);
            setBasePower(load, house, scalar, "DRYER");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for freezer
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateFreezerLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "FREEZER");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for dishwasher
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateDishwasherLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "DISHWASHER");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for range
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateRangeLoad(final House house, final double scalar) {
        // FIXME in the script they reset the scalar for Range on certain types of residential based on string compare
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(0.0);
            load.setImpedanceFraction(1.0);
            load.setCurrentFraction(0.0);
            load.setPowerPf(0.0);
            load.setImpedancePf(1.0);
            load.setCurrentPf(0.0);
            setBasePower(load, house, scalar, "RANGE");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    /**
     * Generate load on a house for microwave
     *
     * @param house
     *            the house reference
     * @param scheduleSkew
     *            the schedule skew
     */
    private static void generateMicrowaveLoad(final House house, final double scalar) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "MICROWAVE");
            load.setHeatFraction(getHeatFrac(heatFraction));
        }
    }

    private static void setRroof(final House house, final double Rroof1, final double Rroof2) {
        house.setRroof((Rroof1 - Rroof2) + 2 * Rroof2 * rand.nextDouble());
    }

    private static void setRwall(final House house, final double Rwall1, final double Rwall2) {
        house.setRwall((Rwall1 - Rwall2) + 2 * Rwall2 * rand.nextDouble());
    }

    private static void setRfloor(final House house, final double Rfloor1, final double Rfloor2) {
        house.setRfloor((Rfloor1 - Rfloor2) + 2 * Rfloor2 * rand.nextDouble());
    }

    private static void setRwindows(final House house, final double Rwindows1, final double Rwindows2) {
        house.setRwindows((Rwindows1 - Rwindows2) + 2 * Rwindows2 * rand.nextDouble());
    }

    private static void setAirchange(final House house, final double airchange1, final double airchange2) {
        house.setAirchangePerHour((airchange1 - airchange2) + 2 * airchange2 * rand.nextDouble());
    }

    private static void setFloorarea(final House house, final double floorarea1, final double floorarea2, final double scaleFloor) {
        house.setFloorArea(scaleFloor * (floorarea1 - floorarea2) + 2 * floorarea2 * rand.nextDouble());
        house.setNumberOfDoors(Math.ceil(house.getFloorArea() / 1000));
    }

    private static void setBasePower(final ZIPLoad load, final House house, final double scalar, final String name) {
        final double basePower = (324.9 / 8907 * Math.pow(house.getFloorArea(), 0.442)) * scalar * ((basepwr_1 - basepwr_2) + 2 * basepwr_2 * rand.nextDouble());
        load.setBasePowerFn(String.format("%s*%.4f", name, basePower));
    }

    private static double getHeatFrac(final double heatFractionScalar) {
        return heatFractionScalar - (0.2) * heatFractionScalar + 2 * (0.2) * heatFractionScalar * rand.nextDouble();
    }

}
