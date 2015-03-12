/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.SolverMethod;
import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.module.ClimateModule;
import gov.pnnl.prosser.api.gld.module.Comm;
import gov.pnnl.prosser.api.gld.module.Market;
import gov.pnnl.prosser.api.gld.module.Module;
import gov.pnnl.prosser.api.gld.module.PowerflowModule;
import gov.pnnl.prosser.api.gld.module.Residential;
import gov.pnnl.prosser.api.gld.module.Tape;
import gov.pnnl.prosser.api.gld.obj.AbstractGldClass;
import gov.pnnl.prosser.api.gld.obj.AuctionClass;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.CsvReader;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.gld.obj.Load;
import gov.pnnl.prosser.api.gld.obj.Meter;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.PlayerClass;
import gov.pnnl.prosser.api.gld.obj.PlayerObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.gld.obj.Substation;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.TriplexNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GLD Simulator
 *
 * Users will use this class to generate a configuration for a GLM file
 *
 * @author nord229
 */
public class GldSimulator {

    private final String name;

    private final List<AbstractGldObject> objects = new ArrayList<>();

    private GldClock clock;

    private final List<Module> modules = new ArrayList<>();

    private final Map<String, String> settings = new HashMap<>();

    private final List<String> includes = new ArrayList<>();

    private final List<AbstractGldClass> classes = new ArrayList<>();

    /**
     * Default constructor, requires the name
     *
     * @param name
     *            Name of the simulator, will be used when naming the file on disk
     */
    public GldSimulator(final String name) {
        this.name = name;
    }

    /**
     * Get the Name of this simulator object - Used when naming the file on disk in an Experiment
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the Simulator objects that comprise this simulation
     *
     * @return the objects
     */
    public List<AbstractGldObject> getObjects() {
        return this.objects;
    }

    /**
     * Get the Clock for this simulation
     *
     * @return the clock
     */
    public GldClock getClock() {
        return this.clock;
    }

    /**
     * Get the Modules for this simulation
     *
     * @return the modules
     */
    public List<Module> getModules() {
        return this.modules;
    }

    /**
     * Get the Settings for this simulation
     *
     * @return the settings
     */
    public Map<String, String> getSettings() {
        return this.settings;
    }

    /**
     * Get the includes definitions for this simulator
     * Includes are extra GLM files that contribute to this simulator
     */
    public List<String> getIncludes() {
        return this.includes;
    }

    /**
     * Get the class definitions for this simulator
     * Class definitions seem to provide public fields for certain objects
     *
     * @return the classes
     */
    public List<AbstractGldClass> getClasses() {
        return this.classes;
    }

    /**
     * Create, set the clock on this object and return a clock object
     *
     * @return the clock
     */
    public GldClock clock() {
        this.clock = new GldClock();
        return clock;
    }

    /**
     * Add the market module to this simulator
     */
    public void marketModule() {
        this.modules.add(new Market());
    }

    /**
     * Add the tape module to this simulator
     */
    public void tapeModule() {
        this.modules.add(new Tape());
    }

    /**
     * Add the comm module to this simulator
     */
    public void commModule() {
        this.modules.add(new Comm());
    }

    /**
     * Add the climate module to this simulator
     */
    public void climateModule() {
        this.modules.add(new ClimateModule());
    }

    /**
     * Add the residential module to this simulator
     *
     * @param implicitEnduses
     *            the residential enduses string, can be null
     */
    public void residentialModule(final String implicitEnduses) {
        this.modules.add(new Residential(implicitEnduses));
    }

    /**
     * Add the powerflow module to this simulator
     *
     * @param solverMethod
     *            the powerflow solver methodology, can be null
     * @param nrIterationLimit
     *            the Newton-Raphson iteration limit (per GridLAB-D iteration), can be null
     */
    public void powerflowModule(final SolverMethod solverMethod, final Long nrIterationLimit) {
        this.modules.add(new PowerflowModule(solverMethod, nrIterationLimit));
    }

    /**
     * Add a setting to this simulator
     *
     * @param key
     *            the setting key
     * @param value
     *            the setting value
     */
    public void addSetting(final String key, final String value) {
        this.settings.put(key, value);
    }

    /**
     * Add includes to this simulator
     *
     * @param includes
     *            the glm file includes to add
     */
    public void addIncludes(final String... includes) {
        this.includes.addAll(Arrays.asList(includes));
    }

    /**
     * Create a Player Class definition
     * This will also add it to the internal classes list
     *
     * @return the created player class
     */
    public PlayerClass playerClass() {
        final PlayerClass playerClass = new PlayerClass();
        this.classes.add(playerClass);
        return playerClass;
    }

    /**
     * Create an Auction Class definition
     * This will also add it to the internal classes list
     *
     * @return the created auction class
     */
    public AuctionClass auctionClass() {
        final AuctionClass auctionClass = new AuctionClass();
        this.classes.add(auctionClass);
        return auctionClass;
    }

    /**
     * Create a Recorder
     * This will also add it to the internal classes list
     * 
     * @return the created object
     */
    public Recorder recorder() {
        final Recorder recorder = new Recorder();
        this.objects.add(recorder);
        return recorder;
    }

    /**
     * Private method to encapsulate common constructor functionality
     *
     * @param object
     *            the object to use when setting common properties
     * @param name
     *            the name to set on the object
     * @return the object reference from the object parameter
     */
    private <T extends AbstractGldObject> T setupObject(final T object, final String name) {
        object.setName(name);
        object.setSimulator(this);
        this.objects.add(object);
        return object;
    }

    /**
     * Create a Player Object
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public PlayerObject playerObject(final String name) {
        return setupObject(new PlayerObject(), name);
    }

    /**
     * Create a Climate Object
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public ClimateObject climateObject(final String name) {
        return setupObject(new ClimateObject(), name);
    }

    /**
     * Create a Auction Object
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public AuctionObject auctionObject(final String name) {
        return setupObject(new AuctionObject(), name);
    }

    /**
     * Create a Transformer Configuration
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TransformerConfiguration transformerConfiguration(final String name) {
        return setupObject(new TransformerConfiguration(), name);
    }

    /**
     * Create a Triplex Line Conductor
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TriplexLineConductor triplexLineConductor(final String name) {
        return setupObject(new TriplexLineConductor(), name);
    }

    /**
     * Create a Triple Line Configuration
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TriplexLineConfiguration triplexLineConfiguration(final String name) {
        return setupObject(new TriplexLineConfiguration(), name);
    }

    /**
     * Create a Substation
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Substation substation(final String name) {
        return setupObject(new Substation(), name);
    }

    /**
     * Create a Meter
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Meter meter(final String name) {
        return setupObject(new Meter(), name);
    }

    /**
     * Create a Transformer
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Transformer transformer(final String name) {
        return setupObject(new Transformer(), name);
    }

    /**
     * Create a Load
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Load load(final String name) {
        return setupObject(new Load(), name);
    }

    /**
     * Create a Triple Meter
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TriplexMeter triplexMeter(final String name) {
        return setupObject(new TriplexMeter(), name);
    }

    /**
     * Create a Triple Line
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TriplexLine triplexLine(final String name) {
        return setupObject(new TriplexLine(), name);
    }

    /**
     * Create a House
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public House house(final String name) {
        return setupObject(new House(), name);
    }

    /**
     * Create a CSV Reader
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public CsvReader csvReader(final String name) {
        return setupObject(new CsvReader(), name);
    }

    /**
     * Create a Overhead Line Conductor
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public OverheadLineConductor overheadLineConductor(final String name) {
        return setupObject(new OverheadLineConductor(), name);
    }

    /**
     * Create a Line Spacing
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public LineSpacing lineSpacing(final String name) {
        return setupObject(new LineSpacing(), name);
    }

    /**
     * Create a Overhead Line Configuration
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public StandardLineConfiguration<OverheadLineConductor> overheadLineConfiguration(final String name) {
        return setupObject(new StandardLineConfiguration<OverheadLineConductor>(), name);
    }

    /**
     * Create a Node
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Node node(final String name) {
        return setupObject(new Node(), name);
    }

    /**
     * Create a Overhead Line
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public OverheadLine overheadLine(final String name) {
        return setupObject(new OverheadLine(), name);
    }

    /**
     * Create a Triplex Node
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public TriplexNode triplexNode(final String name) {
        return setupObject(new TriplexNode(), name);
    }

}
