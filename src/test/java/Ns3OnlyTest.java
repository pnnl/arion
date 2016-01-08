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
        
        return GldSimulatorUtils.generateHouse(sim, id, meter, tripLineConf, auction, phase, false, rand, auction.getFncsControllerPrefix());
    }

}
