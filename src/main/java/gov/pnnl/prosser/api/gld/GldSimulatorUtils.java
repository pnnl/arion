/**
 * 
 */
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.BidMode;
import gov.pnnl.prosser.api.gld.enums.ControlMode;
import gov.pnnl.prosser.api.gld.enums.CoolingSystemType;
import gov.pnnl.prosser.api.gld.enums.CurveOutput;
import gov.pnnl.prosser.api.gld.enums.FanType;
import gov.pnnl.prosser.api.gld.enums.HeatingSystemType;
import gov.pnnl.prosser.api.gld.enums.MotorEfficiency;
import gov.pnnl.prosser.api.gld.enums.MotorModel;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.SpecialMode;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.obj.AuctionClass;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.gld.obj.FncsMsg;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.ZIPLoad;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * House Generation Utilites
 * 
 * @author nord229
 */
public abstract class GldSimulatorUtils {

    // %% Unchanging? parameters
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
    
    public static void MakeTransactiveMarket(GldSimulator simulator, String marketName) {
    	MakeTransactiveMarket(simulator, marketName, 300, "kW", 3.78, 0.042676, 0.02);
    }
    public static void MakeTransactiveMarket(GldSimulator simulator, String marketName, int marketPeriod, String marketUnit, double priceCap, double initialPrice, double initialStandardDeviation) {
        String marketMean = "current_price_mean_24h";
        String marketStDev = "current_price_stdev_24h";
        
        // Add an auction class to allow references to more fields from other objects
        AuctionClass auctionClass = simulator.auctionClass();
        auctionClass.addField(marketMean, "double");
        auctionClass.addField(marketStDev, "double");
        
        // Create the FNCS auction
        final AuctionObject auction = simulator.auctionObject(marketName);
        auction.setUnit(marketUnit);
        auction.setPeriod(marketPeriod);
        auction.setPriceCap(priceCap);
        auction.setTransactionLogFile("log_file_" + simulator.getName() + ".csv");
        auction.setCurveLogFile("bid_curve_" + simulator.getName() + ".csv");
        auction.setCurveLogInfo(CurveOutput.EXTRA);
        auction.setNetworkAveragePriceProperty(marketMean);
        auction.setNetworkStdevPriceProperty(marketStDev);
        auction.setSpecialMode(SpecialMode.BUYERS_ONLY);
        auction.setInitPrice(initialPrice);
        auction.setInitStdev(initialStandardDeviation);
        auction.setUseFutureMeanPrice(false);
        auction.setWarmup(0);
        auction.setFncsControllerPrefix();
        
        // Create the FNCS message
        FncsMsg fncsMessage = simulator.fncsMsg(simulator.getName());
        fncsMessage.setParent(auction);
        
        // Get houses and add controllers
        List<AbstractGldObject> houses = simulator.getObjects();
        houses.removeIf(x -> !x.getClass().equals(House.class));
        Random randNumGen = new Random(100);
        for (Iterator<AbstractGldObject> i = houses.iterator(); i.hasNext(); ) {
            House house = (House)i.next();
            
            // Create the base controller
            Controller controller = house.controller(String.format("%s_controller", house.getName()));
            controller.setAuction(auction);
            controller.setScheduleSkew(house.getScheduleSkew());
            controller.setAverageTarget(auction.getNetworkAveragePriceProperty());
            controller.setStandardDeviationTarget(auction.getNetworkStdevPriceProperty());
            controller.setUseFncs(true);
            
            // Setup the controller
            
        }
    }
    
    /**
     * Generate a house to attach to the grid
     *
     * @param sim
     *            the GldSimulator reference to use when creating all the objects
     * @param id
     *            used when naming houses to keep them visible to FNCS
     * @param meter
     *            the base meter to use when connecting the house
     * @param tripLineConf
     *            the configuration to use on the lines connecting the house and other meters
     * @param auction
     *            the configured auction object from GLD
     * @param phase
     *            the base phase to use, supports A, B, or C and will be converted to AS, BS, or CS respectively
     * @param track
     *            track this house's properties with a recorder
     * @param rand
     *            Random number generator object, normally you would create one of these when you start to configure Prosser
     *            and re-use it to produce repeatable randomness when you control the seed
     */
    public static House generateHouse(final GldSimulator sim, final int id, final TriplexMeter meter,
            final TriplexLineConfiguration tripLineConf, final AuctionObject auction, final PhaseCode phase,
            final boolean track, final Random rand, final String controllerPrefix) {
        // Select the phases for our meters
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
        setHouseInfo(house, houseType, rand);

        // Create the base controller
        final Controller controller = house.controller(controllerPrefix + id);
        controller.setAuction(auction);
        controller.setScheduleSkew(scheduleSkew);
        controller.setAverageTarget(auction.getNetworkAveragePriceProperty());
        controller.setStandardDeviationTarget(auction.getNetworkStdevPriceProperty());
        controller.setUseFncs(true);
        // Setup the controller and loads
        setupController(house, controller, rand);
        setupLoads(house, houseType, applianceScalar, rand);

        // If we want to track this item, add a recorder
        if (track) {
            final Recorder recorder = house.recorder("cooling_setpoint", "air_temperature");
            recorder.setLimit(100000000);
            recorder.setInterval(300L);
            recorder.setFile("F1_house" + id + "_details.csv");
        }
        return house;
    }

    private static void setHouseInfo(final House house, final HouseType houseType, final Random rand) {
        // Setup the specific values
        switch (houseType) {
            case RESIDENTIAL1:
                setupResidential1(house, rand);
                break;
            case RESIDENTIAL2:
                setupResidential2(house, rand);
                break;
            case RESIDENTIAL3:
                setupResidential3(house, rand);
                break;
            case RESIDENTIAL4:
                setupResidential4(house, rand);
                break;
            case RESIDENTIAL5:
                setupResidential5(house, rand);
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
    private static void setupResidential1(final House house, final Random rand) {
        setRroof(house, 19, 4, rand);
        setRwall(house, 11, 3, rand);
        setRfloor(house, 11, 1, rand);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5, rand);
        setAirchange(house, 1, 0.2, rand);
        setFloorarea(house, smallhome_floorarea_1, smallhome_floorarea_2, scaleFloor, rand);
    }

    /**
     * Setup the house for New/Small
     * 
     * @param house
     *            the house to setup
     */
    private static void setupResidential2(final House house, final Random rand) {
        setRroof(house, 30, 5, rand);
        setRwall(house, 19, 3, rand);
        setRfloor(house, 15, 3, rand);
        house.setRdoors(5.0);
        setRwindows(house, 1.75, 0.5, rand);
        setAirchange(house, 1, 0.2, rand);
        setFloorarea(house, smallhome_floorarea_1, smallhome_floorarea_2, scaleFloor, rand);
    }

    /**
     * Setup the house for Old/Large
     * 
     * @param house
     *            the house to setup
     */
    private static void setupResidential3(final House house, final Random rand) {
        setRroof(house, 19, 4, rand);
        setRwall(house, 11, 3, rand);
        setRfloor(house, 11, 1, rand);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5, rand);
        setAirchange(house, 1, 0.2, rand);
        setFloorarea(house, largehome_floorarea_1, largehome_floorarea_2, scaleFloor, rand);
    }

    /**
     * Setup the house for New/Large
     * 
     * @param house
     *            the house to setup
     */
    private static void setupResidential4(final House house, final Random rand) {
        setRroof(house, 30, 5, rand);
        setRwall(house, 19, 3, rand);
        setRfloor(house, 15, 3, rand);
        house.setRdoors(5.0);
        setRwindows(house, 1.75, 0.5, rand);
        setAirchange(house, 1, 0.2, rand);
        setFloorarea(house, largehome_floorarea_1 - 200, largehome_floorarea_2, scaleFloor, rand);
    }

    /**
     * Setup the house for Mobile Homes
     * 
     * @param house
     *            the house to setup
     */
    private static void setupResidential5(final House house, final Random rand) {
        setRroof(house, 14, 4, rand);
        setRwall(house, 6, 2, rand);
        setRfloor(house, 5, 1, rand);
        house.setRdoors(3.0);
        setRwindows(house, 1.25, 0.5, rand);
        setAirchange(house, 1.4, 0.2, rand);
        setFloorarea(house, mobilehome_floorarea_1, 150, scaleFloor, rand);
    }

    private static void setupController(final House house, final Controller controller, final Random rand) {
        // we need +1 to skip zeros
        final int scheduleCool = rand.nextInt(8) + 1;
        final double coolOffset = (cooloffset_1 - cooloffset_2) + 2 * cooloffset_2 * rand.nextDouble();
        final double coolTemp = (cool_1 - cool_2) + 2 * cool_2 * rand.nextDouble();

        // we need +1 to skip zeros
        final int scheduleHeat = rand.nextInt(8) + 1;
        final double heatOffset = heatoffset_1 + heatoffset_2 * rand.nextDouble();
        final double heatTemp = (cool_1 - cool_2) + 2 * cool_2 * rand.nextDouble();

//        house.setCoolingSetpointFn(String.format("cooling%d*%1.3f+%2.2f", scheduleCool, coolTemp, coolOffset));
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

//        controller.setUseOverride(UseOverride.ON);
//        controller.setOverride("override");
        controller.setBidMode(BidMode.PROXY);
//        controller.setProxyDelay(10);
        controller.setControlMode(ControlMode.RAMP);

        // FIXME Bid delay does not apper in our output files?
        // controller.setBidDelay(bidDelay);

        controller.setBaseSetpoint(String.format("cooling%d*%1.3f+%2.2f", scheduleCool, coolTemp, coolOffset));
        controller.setSetPoint("cooling_setpoint");
        controller.setTarget("air_temperature");
        controller.setDeadBand("thermostat_deadband");
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
//            controller.setSliderSetting(coolSlider);
            controller.setRangeHigh(5.0);
            controller.setRangeLow(-3.0);
        }
        controller.setTotal("total_load");
        controller.setLoad("hvac_load");
        controller.setState("power_state");

    }

    private static void setupLoads(final House house, final HouseType houseType, final double[] applianceScalar, final Random rand) {
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
        generateLightsLoad(house, applianceScalar[0], rand);
        generateClothesWasherLoad(house, applianceScalar[1], rand);
        generateRefrigeratorLoad(house, applianceScalar[2], rand);
        if (dryerPresent) {
            generateDryerLoad(house, applianceScalar[3], rand);
        }
        if (freezerPresent) {
            generateFreezerLoad(house, applianceScalar[4], rand);
        }
        if (dishwasherPresent) {
            generateDishwasherLoad(house, applianceScalar[5], rand);
        }
        generateRangeLoad(house, applianceScalar[6], rand);
        generateMicrowaveLoad(house, applianceScalar[7], rand);
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
    private static void generateLightsLoad(final House house, final double scalar, final Random rand) {
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

            setBasePower(load, house, scalar, "LIGHTS", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateClothesWasherLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "CLOTHESWASHER", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateRefrigeratorLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "REFRIGERATOR", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateDryerLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(0.1);
            load.setImpedanceFraction(0.8);
            load.setCurrentFraction(0.1);
            load.setPowerPf(0.9);
            load.setImpedancePf(1.0);
            load.setCurrentPf(0.9);
            setBasePower(load, house, scalar, "DRYER", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateFreezerLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "FREEZER", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateDishwasherLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "DISHWASHER", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateRangeLoad(final House house, final double scalar, final Random rand) {
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
            setBasePower(load, house, scalar, "RANGE", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
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
    private static void generateMicrowaveLoad(final House house, final double scalar, final Random rand) {
        if (scalar > 0) {
            final ZIPLoad load = house.addLoad();
            load.setScheduleSkew(house.getScheduleSkew());
            load.setPowerFraction(p);
            load.setImpedanceFraction(z);
            load.setCurrentFraction(i);
            load.setPowerPf(systemPf);
            load.setImpedancePf(systemPf);
            load.setCurrentPf(systemPf);
            setBasePower(load, house, scalar, "MICROWAVE", rand);
            load.setHeatFraction(getHeatFrac(heatFraction, rand));
        }
    }

    private static void setRroof(final House house, final double Rroof1, final double Rroof2, final Random rand) {
        house.setRroof((Rroof1 - Rroof2) + 2 * Rroof2 * rand.nextDouble());
    }

    private static void setRwall(final House house, final double Rwall1, final double Rwall2, final Random rand) {
        house.setRwall((Rwall1 - Rwall2) + 2 * Rwall2 * rand.nextDouble());
    }

    private static void setRfloor(final House house, final double Rfloor1, final double Rfloor2, final Random rand) {
        house.setRfloor((Rfloor1 - Rfloor2) + 2 * Rfloor2 * rand.nextDouble());
    }

    private static void setRwindows(final House house, final double Rwindows1, final double Rwindows2, final Random rand) {
        house.setRwindows((Rwindows1 - Rwindows2) + 2 * Rwindows2 * rand.nextDouble());
    }

    private static void setAirchange(final House house, final double airchange1, final double airchange2, final Random rand) {
        house.setAirchangePerHour((airchange1 - airchange2) + 2 * airchange2 * rand.nextDouble());
    }

    private static void setFloorarea(final House house, final double floorarea1, final double floorarea2, final double scaleFloor, final Random rand) {
        house.setFloorArea(scaleFloor * (floorarea1 - floorarea2) + 2 * floorarea2 * rand.nextDouble());
        house.setNumberOfDoors(Math.ceil(house.getFloorArea() / 1000));
    }

    private static void setBasePower(final ZIPLoad load, final House house, final double scalar, final String name, final Random rand) {
        final double basePower = (324.9 / 8907 * Math.pow(house.getFloorArea(), 0.442)) * scalar * ((basepwr_1 - basepwr_2) + 2 * basepwr_2 * rand.nextDouble());
        load.setBasePowerFn(String.format("%s*%.4f", name, basePower));
    }

    private static double getHeatFrac(final double heatFractionScalar, final Random rand) {
        return heatFractionScalar - (0.2) * heatFractionScalar + 2 * (0.2) * heatFractionScalar * rand.nextDouble();
    }
}
