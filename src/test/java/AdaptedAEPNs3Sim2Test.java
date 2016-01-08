/**
 *
 */

import gov.pnnl.prosser.api.*;
import gov.pnnl.prosser.api.gld.*;
import gov.pnnl.prosser.api.gld.enums.*;
import gov.pnnl.prosser.api.gld.lib.*;
import gov.pnnl.prosser.api.gld.obj.*;
import gov.pnnl.prosser.api.ns3.Ns3Simulator2;
import gov.pnnl.prosser.api.ns3.obj.*;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
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
public class AdaptedAEPNs3Sim2Test extends Experiment {

    /**
     * Generate the experiment
     */
    @Override
    public void experiment() {

        // This is example of how network setup could be automated somewhat by user
        // Equal number of houses per backbone router not hardcoded into Prosser
        final int numHouses = 45;
        final int numHousesPerBackbone = 20;
        final int numBackboneRouters = numHouses / numHousesPerBackbone + 1;
        final int numAuctions = 2;

        final GldSimulator gldSim = this.gldSimulator("fncs_GLD_1node_Feeder_1");

        final AuctionObject auction0 = createMarket(gldSim, UUID.randomUUID().toString());
        auction0.setFncsControllerPrefix();

        final AuctionObject auction1 = createMarket(gldSim, UUID.randomUUID().toString());
        auction1.setFncsControllerPrefix();
        
        final Ns3Simulator2 ns3Sim2 = this.ns3Simulator2();
        ns3Sim2.addNetwork(20, auction0.getName(), auction0.getFncsControllerPrefix());
        ns3Sim2.addNetwork(20, auction1.getName(), auction1.getFncsControllerPrefix());
        
        createFncsMsg(gldSim, auction0, numHouses, gldSim.getName() + String.format("_C%d_", numHouses), "ns3Sim");
        // Specify the climate information
        final ClimateObject climate = gldSim.climateObject("Columbus OH");
        climate.setTmyFile(Paths.get("res/ColumbusWeather2009_2a.csv"));
        climate.addCsvReader("CSVREADER");

        // Add a recorder to the auction for some of the properties on the auction
        final Recorder recorder0 = auction0.recorder("capacity_reference_bid_price", "current_market.clearing_price", "current_market.clearing_quantity");
        recorder0.setLimit(100000000);
        recorder0.setInterval(300L);
        recorder0.setUsingSql(true);

        final Recorder recorder1 = auction1.recorder("capacity_reference_bid_price", "current_market.clearing_price", "current_market.clearing_quantity");
        recorder1.setLimit(100000000);
        recorder1.setInterval(300L);
        recorder1.setUsingSql(true);

        createTriplex(gldSim, numHouses);

        for (int i = 0; i < numHouses; i++) {
            final House house;
            if (i <= 20) {
                house = createHouse(gldSim, i, auction0, gldSim.getName() + String.format("_C%d_", numHouses));
            } else {
                house = createHouse(gldSim, i, auction1, gldSim.getName() + String.format("_C%d_", numHouses));
            }

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
//        auctionClass.addField("day_ahead_average", "double");
//        auctionClass.addField("day_ahead_std", "double");
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
    
    private void createFncsMsg(final GldSimulator sim, AuctionObject pObj, final int numHouses, final String controllerPrefix, final String ns3SimName) {
    	final FncsMsg fMsg = sim.fncsMsg(sim.getName());
    	fMsg.setParent(pObj);
    	fMsg.addRoute("\"function:controller/submit_bid_state -> auction/submit_bit_state\";");
    	fMsg.addOption("\"transport:hostname localhost, port 5570\";");
    	fMsg.addConfigure(sim.getName() + ".txt");
    	for(int i = 0; i < numHouses; i++) {
    		fMsg.addRoute(String.format("\"presync:%s.clearingPrice -> %s%d/clearPrice;0\";", pObj.getName(), controllerPrefix, i));
    		fMsg.addRoute(String.format("\"presync:%s.market_id -> %s%d/mktID;0\";", pObj.getName(), controllerPrefix, i));
    		fMsg.addRoute(String.format("\"presync:%s.%s -> %s%d/avgPrice;0\";", pObj.getName(), pObj.getNetworkAveragePriceProperty(), controllerPrefix, i));
    		fMsg.addRoute(String.format("\"presync:%s.%s -> %s%d/stdevPrice;0\";", pObj.getName(), pObj.getNetworkStdevPriceProperty(), controllerPrefix, i));
    		fMsg.addSubscribe(String.format("\"function:auction/submit_bid_state <- %s/%s/%s%d@%s/submit_bid_state\";", ns3SimName, sim.getName(), controllerPrefix, i, pObj.getName()));
    		fMsg.addSubscribe(String.format("\"presync:%s/proxy_clear_price <- %s/%s/%s@%s%d/clearPrice\";", ns3SimName, sim.getName(), pObj.getName(), controllerPrefix, i));
    		fMsg.addSubscribe(String.format("\"presync:%s/proxy_market_id <- %s/%s/%s@%s%d/mktID\";", ns3SimName, sim.getName(), pObj.getName(), controllerPrefix, i));
    		fMsg.addSubscribe(String.format("\"presync:%s/proxy_average <- %s/%s/%s@%s%d/avgPrice\";", ns3SimName, sim.getName(), pObj.getName(), controllerPrefix, i));
    		fMsg.addSubscribe(String.format("\"presync:%s/proxy_standard_deviation <- %s/%s/%s@%s%d/stdevPrice\";", ns3SimName, sim.getName(), pObj.getName(), controllerPrefix, i));
    	}
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
    private House createHouse(final GldSimulator sim, final int id, final AuctionObject auction, final String contPrefix) {
        // Select the phases for our meters
        final int r = rand.nextInt(3);
        final PhaseCode phase;
        final TriplexMeter meter;
        switch (r) {
            case 0:
                phase = PhaseCode.A;
                meter = tripMeterA;
                break;
            case 1:
                phase = PhaseCode.B;
                meter = tripMeterB;
                break;
            case 2:
                phase = PhaseCode.C;
                meter = tripMeterC;
                break;
            default:
                throw new RuntimeException("Invalid random number");
        }
        
        return GldSimulatorUtils.generateHouse(sim, id, meter, tripLineConf, auction, phase, false, rand, contPrefix);
    }
}
