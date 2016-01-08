/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.FncsSimulator;
import gov.pnnl.prosser.api.gld.GldSimulatorUtils;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;
import gov.pnnl.prosser.api.ns3.obj.*;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointChannel;

import java.nio.file.Paths;
import java.time.*;
import java.util.*;

/**
 * First FNCS experiment
 *
 * @author nord229
 *
 */
public class ExperimentFncsTest extends Experiment {

    private static final Random rand = new Random(13);

    /**
     * Generate the experiment
     */
    @Override
    public void experiment() {
        final int numHouses = 1;
        final int numHousesPerChannel = 1;
        final int numChannels = (numHouses % numHousesPerChannel) == 0 ? (numHouses / numHousesPerChannel) + 1 : (numHouses / numHousesPerChannel) + 2;
        System.out.println("Number of channels: " + numChannels); // TODO debugging
        final String controllerNIPrefix = "F1_C_NI";
        final String marketNIPrefix = "Market1NI"; // TODO Peter, is this fine defined here? Needed since creating NW before attaching controllers/market
        final String backboneDataRate = "10Gbps";
        final String backboneDelay = "500ns";

        // Set parameters for Ns3Network and build backend network
        final Ns3Simulator ns3Simulator = this.ns3Simulator("ns3");
        //ns3Simulator.setup(marketNIPrefix);

        // List of Routers for IP address assignment
        List<Router> routers = new ArrayList<>();

        // Create Auction channel and connect it to a router
        PointToPointChannel auctionChannel = new PointToPointChannel("auctionChannel");
        auctionChannel.setDataRate("1Gbps");
        auctionChannel.setDelay("1ms");
        ns3Simulator.addChannel(auctionChannel);
        Router auctionRouter = new Router("auctionRouter_0");
        auctionRouter.setChannel(auctionChannel);
        auctionChannel.setRouterA(auctionRouter);
        routers.add(auctionRouter);

        // This is example of how network setup could be automated somewhat by user
        // Equal number of houses per backbone router not hardcoded into Prosser
        final int numBackboneRouters = numHouses / 126 + 1;
        final int numHousesPerBackbone = numHouses / numBackboneRouters + numHouses % numBackboneRouters;

        // FIXME all interfaces on single node need same network?
        // FIXME do all csma devices on same channel need same network?

        for (int i = 0; i < numBackboneRouters; i++) {

            // Create backbone router to connect houses and auction
            Router backboneRouter = new Router("backboneRouter_" + i);
            // Enable PCAP debugging on backbone router
            backboneRouter.setPcap(true);

            // Can add more than one auction channel here
            if (i == 0) {
                backboneRouter.setChannel(auctionChannel);
            }
            routers.add(backboneRouter);

            for (int j = 0; j < numHousesPerBackbone; j++) {
                CsmaChannel houseChannel = new CsmaChannel("csmaHouseChannel_" + i + "_" + j);
                houseChannel.setDataRate("100Mbps");
                houseChannel.setDelay("10ms");
                ns3Simulator.addChannel(houseChannel);

                // Connect house router and a backbone router to the house channel
                Router houseRouter = new Router("csmaHouseRouter_" + i + "_" + j);
                houseRouter.setChannel(houseChannel);
                backboneRouter.setChannel(houseChannel);
                routers.add(houseRouter);

            }
        }

        // Assign IP addresses to the devices on the routers
        //ns3Simulator.assignIPs(routers);

        final List<Channel> channels = ns3Simulator.getChannels();

        // TODO Add numHousesPerChannel param and use instead of hard-coded "20" to get channelID
        final GldSimulator gldSim = this.gldSimulator("fncs_GLD_1node_Feeder_1");
        populateGldSim(gldSim, numHouses, controllerNIPrefix, channels);

        // Connect Controllers and Auctions to backbone network
        //ns3Simulator.buildFrontend();

        // TODO FNCS Integration
		final FncsSimulator fncsSim = this.fncsSimulator();
        fncsSim.setBroker("localhost");
    }

    /**
     * @param sim
     * 			the Ns3Simulator
     * @param numChannels
     * 			the number of Channels to create in this network
     */
    private void populateNs3Sim(final Ns3Simulator sim, final int numChannels) {

        final String marketNIPrefix = "Market1NI";
		final String backboneDataRate = "10Gbps";
		final String backboneDelay = "500ns";

		// Sets up header stuff and parameters (params not used with below implementation)
		//sim.setup(marketNIPrefix);
		
		// Create auction channel & router
		PointToPointChannel auctionChannel = new PointToPointChannel("auctionChannel");
		auctionChannel.setDataRate("1Gbps");
		auctionChannel.setDelay("1ms");
		sim.addChannel(auctionChannel);
		
		Router auctionRouter = new Router("auctionRouter");
		auctionRouter.setChannel(auctionChannel);
		
		// Create backbone CSMA channel (connect Houses)
		CsmaChannel csmaBackboneChannel = new CsmaChannel("csmaBackboneChannel");
		csmaBackboneChannel.setDataRate(backboneDataRate);
		csmaBackboneChannel.setDelay(backboneDelay);
		
		// Create house channels
		for (int i = 0; i < numChannels; i++) {
			
			CsmaChannel csmaHouseChannel = new CsmaChannel("csmaHouseChannel_" + i);
			csmaHouseChannel.setDataRate("100Mbps");
			csmaHouseChannel.setDelay("1ms");
			
			// Add the house channel to simulator list of channels
			sim.addChannel(csmaHouseChannel);
			
			Router csmaRouter = new Router("csmaHouseRouter_" + i);
			
			// Connect router to house channel
			csmaRouter.setChannel(csmaHouseChannel);
			
			// Connect router to backbone channel
			csmaRouter.setChannel(csmaBackboneChannel);
			
		}
		
	}

	/**
     * The gld simulator for this experiment
     * 
     * @param controllerNIPrefix
     *            Prefix to network controllers in Houses and will be enclosed in the auction object
     * @param channels
     *            the network channels to use - channel 0 will be used for the auction, and then channels other than 0 will have up to 20 controllers on it
     */
    private static void populateGldSim(final GldSimulator sim, final int numHouses, final String controllerNIPrefix, final List<Channel> channels) {
        // final boolean useMarket = true;
        final String marketName = "Market1";
        final int marketPeriod = 300;
        final String marketMean = "current_price_mean_24h";
        final String marketStdev = "current_price_stdev_24h";
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

        // Setup the modules
        sim.marketModule();
        sim.tapeModule();
        sim.commModule();
        sim.climateModule();
        sim.residentialModule(ImplicitEnduses.NONE);
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

        // Specify the climate information
        final ClimateObject climate = sim.climateObject("Columbus OH");
        climate.setTmyFile(Paths.get("res/ColumbusWeather2009_2a.csv"));
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
        auction.setFncsControllerPrefix(controllerNIPrefix);
        channels.get(0).addAuction(auction);

        // Add a player to the auction for one of its values
        final PlayerObject player = auction.player();
        player.setProperty("fixed_price");
        player.setFile(Paths.get("res/AEP_RT_LMP.player"));
        player.setLoop(1);

        auction.setSpecialMode(SpecialMode.BUYERS_ONLY);

        // Add a recorder to the auction for some of the properties on the auction
        // TODO: Need to add code for writing SQL commands for generating tables to collect data from all simulator data generating objects
        // TODO: For instance GridLAB-D has an odbc recorder, processor needs to write the appropirate connection info in the glm and the sql
        // TODO: to create a table in the database to accept the comunication from gld.
        final Recorder recorder = auction.recorder("capacity_reference_bid_price", "current_market.clearing_price", "current_market.clearing_quantity");
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

        // TODO: Move generate house and other convienence methods to a library
        for (int i = 1; i <= numHouses; i++) {
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
            // distributes houses to channels, 20 per channel (1-n)
            int channelId = ((i - 1) / 20) + 1;
            GldSimulatorUtils.generateHouse(sim, i, meter, tripLineConf, auction, phase, false, rand, auction.getFncsControllerPrefix());
        }
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

}
