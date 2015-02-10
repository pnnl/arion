import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.ExperimentBuilder;
import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.GldClock;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.module.SolverMethod;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;
import gov.pnnl.prosser.api.gld.obj.AuctionClass;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.CsvReader;
import gov.pnnl.prosser.api.gld.obj.PlayerClass;
import gov.pnnl.prosser.api.gld.obj.PlayerObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 */

/**
 * @author happ546
 *
 */
public class AdaptedAepFncs2014 implements GldSimulator {

    private static final Random rng = new Random(11);

    private static final int numHouses = 300;

    @Override
    public String getName() {
        return "fncs_GLD_100node_Feeder_1";
    }

    @Override
    public List<AbstractProsserObject> getObjects() {
        final List<AbstractProsserObject> objects = new ArrayList<>();

        final PlayerObject phaseALoad = new PlayerObject();
        phaseALoad.setName("phase_A_load");
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

        final CsvReader reader = new CsvReader();
        reader.setName("CSVREADER");
        reader.setFilename("ColumbusWeather2009_2a.csv");
        objects.add(reader);

        final ClimateObject climate = new ClimateObject();
        climate.setName("Columbus OH");
        climate.setTmyFile("ColumbusWeather2009_2a.csv");
        climate.setReader(reader);
        objects.add(climate);

        final AuctionObject auction = new AuctionObject();

        return objects;
    }

    @Override
    public GldClock getClock() {
        final GldClock clock = new GldClock();
        clock.setTimezone("EST+5EDT");
        clock.setStartTime(LocalDateTime.of(2009, 07, 21, 00, 00));
        clock.setStopTime(LocalDateTime.of(2009, 07, 23, 00, 00));
        return clock;
    }

    @Override
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

    public String[] getIncludes() {
        return new String[] { "water_and_setpoint_schedule_v3.glm", "appliance_schedules.glm" };
    }

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

}
