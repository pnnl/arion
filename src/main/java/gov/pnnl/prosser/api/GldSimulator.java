/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.BusType;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.ImplicitEnduses;
import gov.pnnl.prosser.api.gld.enums.InstallationType;
import gov.pnnl.prosser.api.gld.enums.SolverMethod;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.lib.GldClock;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.UndergroundLineConductor;
import gov.pnnl.prosser.api.gld.module.ClimateModule;
import gov.pnnl.prosser.api.gld.module.Comm;
import gov.pnnl.prosser.api.gld.module.Connection;
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
import gov.pnnl.prosser.api.gld.obj.FncsMsg;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.gld.obj.Load;
import gov.pnnl.prosser.api.gld.obj.Meter;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.PlayerClass;
import gov.pnnl.prosser.api.gld.obj.PlayerObject;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.gld.obj.Regulator;
import gov.pnnl.prosser.api.gld.obj.Substation;
import gov.pnnl.prosser.api.gld.obj.Switch;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.TriplexNode;
import gov.pnnl.prosser.api.gld.obj.UndergroundLine;
import gov.pnnl.prosser.api.gld.obj.WaterHeater;
import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.complex.Complex;

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

    private final Map<Class<? extends Module>, Module> modules = new TreeMap<>((c1, c2) -> c1.getName().compareTo(c2.getName()));

    private final Map<String, String> settings = new TreeMap<>();

    private final List<Path> includes = new ArrayList<>();

    private final List<AbstractGldClass> classes = new ArrayList<>();
    
    private final AbstractNs3SimulatorV2 ns3Sim;
    
    private ThirdPartySimulator thirdPartySim;
    
    private final List<AbstractGldObject> dgList = new ArrayList<>();
    
    private final Map<AbstractGldObject, String> aggregatorLines = new TreeMap<>();
    
    private ThirdPartySimulator transmissionSim;

    /**
	 * @return the transmissionSim
	 */
	public ThirdPartySimulator getTransmissionSim() {
		return transmissionSim;
	}

	/**
	 * @param transmissionSim the transmissionSim to set
	 */
	public void setTransmissionSim(ThirdPartySimulator transmissionSim, double transmissionVoltage) {
		this.transmissionSim = transmissionSim;
		Node node = null;
		//Find the swingBus
		for(AbstractGldObject o : this.getObjects()){
			if(o instanceof Node || o instanceof Meter){
				node = (Node)o;
				if(node.getBusType() != null && node.getBusType().equals(BusType.SWING)){
					break;
				}
				node = null;
			}
		}
		if(node != null){
			node.setBusType(BusType.PQ);
			//create substation transformer configuration
			TransformerConfiguration substationTransformerConfig = this.transformerConfiguration(String.format("%s_substation_transformer_config", this.getName()));
			substationTransformerConfig.setConnectionType(ConnectionType.WYE_WYE);
			substationTransformerConfig.setInstallationType(InstallationType.PADMOUNT);
			substationTransformerConfig.setPrimaryVoltage(transmissionVoltage);
			substationTransformerConfig.setSecondaryVoltage(node.getNominalVoltage());
			substationTransformerConfig.setPowerRating(10000.0);
			substationTransformerConfig.setImpedance(0.00033, 0.0022);
			
			//create substation node
			Substation substation = this.substation(String.format("%s_network_node", this.getName()));
			substation.setBusType(BusType.SWING);
			substation.setPositiveSequenceVoltage(new Complex(transmissionVoltage, 0.0));
			substation.setReferencePhase(PhaseCode.A);
			substation.setNominalVoltage(transmissionVoltage);
			substation.setPhases(PhaseCode.ABCN);
			
			//create substation transformer
			Transformer substationTransformer = this.transformer(String.format("%s_substation_transformer", this.getName()), substationTransformerConfig);
			substationTransformer.setFrom(substation);
			substationTransformer.setTo(node);
			substationTransformer.setPhases(PhaseCode.ABCN);
		}
	}

	/**
	 * @return the aggregatorLines
	 */
	public Map<AbstractGldObject, String> getAggregatorLines() {
		return aggregatorLines;
	}
	
	/**
	 * add to the aggregator line List
	 * @param aggLine
	 * 					The AbstractGldObject to add to the aggregator line list
	 */
	public void addAggregatorLineObject(AbstractGldObject dgObject, String auctionName){
		this.aggregatorLines.put(dgObject, auctionName);
	}

	/**
	 * @return the dgList
	 */
	public List<AbstractGldObject> getDgList() {
		return dgList;
	}
	
	/**
	 * add to the dg List
	 * @param dgObject
	 * 					The AbstractGldObject to add to the dg list
	 */
	public void addDgObject(AbstractGldObject dgObject){
		this.dgList.add(dgObject);
	}

	/**
     * Default constructor, requires the name
     *
     * @param name
     *            Name of the simulator, will be used when naming the file on disk
     */
    public GldSimulator(final String name, final AbstractNs3SimulatorV2 ns3Sim) {
        this.name = name;
        this.ns3Sim = ns3Sim;
    }

    /**
	 * @param thirdPartySim the thirdPartySim to set
	 * @param the nominal voltage at the substation primary.
	 */
	public void setThirdPartySim(ThirdPartySimulator thirdPartySim) {
		this.thirdPartySim = thirdPartySim;
	}

	/**
	 * @return the thirdPartySim
	 */
	public ThirdPartySimulator getThirdPartySim() {
		return thirdPartySim;
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
    public Collection<Module> getModules() {
        return this.modules.values();
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
     * 
     * @return the includes
     */
    public List<Path> getIncludes() {
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
     * @return the ns3Sim
     */
    public AbstractNs3SimulatorV2 getNs3Sim() {
        return ns3Sim;
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
        this.modules.put(Market.class, new Market());
    }

    /**
     * Add default market module if it does not exist
     */
    public void ensureMarketModule() {
        this.modules.computeIfAbsent(Market.class, k -> new Market());
    }

    /**
     * Add the tape module to this simulator
     */
    public void tapeModule() {
        this.modules.put(Tape.class, new Tape());
    }

    /**
     * Add default tape module if it does not exist
     */
    public void ensureTapeModule() {
        this.modules.computeIfAbsent(Tape.class, k -> new Tape());
    }

    /**
     * Add the connection module to this simulator. Only exist for FNCS 2.0
     */
    public void connnectionModule() {
        this.modules.put(Connection.class, new Connection());
    }
    
    /**
     * Add default connection module if it does not exist
     */
    public void ensureConnectionModule() {
        this.modules.computeIfAbsent(Connection.class, k -> new Connection());
    }
    
    /**
     * Add the comm module to this simulator. Only exist for FNCS 1.0
     */
    public void commModule() {
        this.modules.put(Comm.class, new Comm());
    }

    /**
     * Add default comm module if it does not exist
     */
    public void ensureCommModule() {
        this.modules.computeIfAbsent(Comm.class, k -> new Comm());
    }

    /**
     * Add the climate module to this simulator
     */
    public void climateModule() {
        this.modules.put(ClimateModule.class, new ClimateModule());
    }

    /**
     * Add default climate module if it does not exist
     */
    public void ensureClimateModule() {
        this.modules.computeIfAbsent(ClimateModule.class, k -> new ClimateModule());
    }

    /**
     * Add the residential module to this simulator
     *
     * @param implicitEnduses
     *            the list of implicit enduses that are active in houses, can be null
     */
    public void residentialModule(final ImplicitEnduses implicitEnduses) {
        this.modules.put(Residential.class, new Residential(implicitEnduses));
    }

    /**
     * Add default residential module if it does not exist
     */
    public void ensureResidentialModule() {
        this.modules.computeIfAbsent(Residential.class, k -> new Residential(ImplicitEnduses.NONE));
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
        this.modules.put(PowerflowModule.class, new PowerflowModule(solverMethod, nrIterationLimit));
    }

    /**
     * Add default powerflow module if it does not exist
     */
    public void ensurePowerflowModule() {
        this.modules.computeIfAbsent(PowerflowModule.class, k -> new PowerflowModule(SolverMethod.FBS, 100L));
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
    public void addIncludes(final Path... includes) {
        this.includes.addAll(Arrays.asList(includes));
    }
    
    /**
     * Get an gld object by name
     * @param name
     * 				name of the object to find
     * @return the desired object
     */
    public AbstractGldObject getGldObjectByName(String name){
    	AbstractGldObject rv = null;
    	for(AbstractGldObject o : this.objects){
    		if(o.getName().equals(name)){
    			rv = o;
    		}
    	}
    	return rv;
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
        final Recorder recorder = new Recorder(this);
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
        return setupObject(new PlayerObject(this), name);
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
        return setupObject(new ClimateObject(this), name);
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
        return setupObject(new AuctionObject(this), name);
    }
    
    /**
     * Create a fncs msg
     * this will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     * 
     * @param name 
     * 			the name to set
     * @return the created object
     */
    public FncsMsg fncsMsg(final String name) {
    	return setupObject(new FncsMsg(this), name);
    }

    /**
     * Create a Regulator Configuration
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public RegulatorConfiguration regulatorConfiguration(final String name) {
        return setupObject(new RegulatorConfiguration(this), name);
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
        return setupObject(new TransformerConfiguration(this), name);
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
        return setupObject(new TriplexLineConductor(this), name);
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
        return setupObject(new TriplexLineConfiguration(this), name);
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
        return setupObject(new Substation(this), name);
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
        return setupObject(new Meter(this), name);
    }

    /**
     * Create a Regulator
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @param config
     *            the regulator configuration
     * @return the created object
     */
    public Regulator regulator(final String name, final RegulatorConfiguration config) {
        Regulator t = setupObject(new Regulator(this), name);
        t.setRegulatorConfiguration(config);
        return t;
    }

    /**
     * Create a Transformer
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @param config
     *            the transformer configuration
     * @return the created object
     */
    public Transformer transformer(final String name, final TransformerConfiguration config) {
        Transformer t = setupObject(new Transformer(this), name);
        t.setConfiguration(config);
        return t;
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
        return setupObject(new Load(this), name);
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
        return setupObject(new TriplexMeter(this), name);
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
        return setupObject(new TriplexLine(this), name);
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
        return setupObject(new House(this), name);
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
        return setupObject(new CsvReader(this), name);
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
        return setupObject(new OverheadLineConductor(this), name);
    }

    /**
     * Create a Underground Line Conductor
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public UndergroundLineConductor undergroundLineConductor(final String name) {
        return setupObject(new UndergroundLineConductor(this), name);
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
        return setupObject(new LineSpacing(this), name);
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
        return setupObject(new StandardLineConfiguration<OverheadLineConductor>(this), name);
    }

    /**
     * Create a Underground Line Configuration
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public StandardLineConfiguration<UndergroundLineConductor> undergroundLineConfiguration(final String name) {
        return setupObject(new StandardLineConfiguration<UndergroundLineConductor>(this), name);
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
        return setupObject(new Node(this), name);
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
        return setupObject(new OverheadLine(this), name);
    }

    /**
     * Create an Overhead Line
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public OverheadLine overheadLine(final String name, final EnumSet<PhaseCode> phases, final Node fromNode, 
            final Node toNode, final double length, final LineConfiguration<OverheadLineConductor> lineConfiguration) {
        OverheadLine overheadLine = setupObject(new OverheadLine(this), name);
        overheadLine.setPhases(phases);
        overheadLine.setFrom(fromNode);
        overheadLine.setTo(toNode);
        overheadLine.setLength(length);
        overheadLine.setConfiguration(lineConfiguration);
        return overheadLine;
    }
    
    /**
     * Create an Underground Line
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public UndergroundLine undergroundLine(final String name, final EnumSet<PhaseCode> phases, final Node fromNode, 
            final Node toNode, final double length, final LineConfiguration<UndergroundLineConductor> lineConfiguration) {
        UndergroundLine undergroundLine = setupObject(new UndergroundLine(this), name);
        undergroundLine.setPhases(phases);
        undergroundLine.setFrom(fromNode);
        undergroundLine.setTo(toNode);
        undergroundLine.setLength(length);
        undergroundLine.setConfiguration(lineConfiguration);
        return undergroundLine;
    }
    
    /**
     * Create a Switch
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public Switch switchLinkObject(final String name, final EnumSet<PhaseCode> phases, final Node fromNode, 
            final Node toNode, final SwitchStatus status) {
        Switch switchNode = setupObject(new Switch(this), name);
        switchNode.setPhases(phases);
        switchNode.setFrom(fromNode);
        switchNode.setTo(toNode);
        switchNode.setStatus(status);
        return switchNode;
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
        return setupObject(new TriplexNode(this), name);
    }
    
    /**
     * Create a WaterHeate
     * This will add it to the internal objects list, set the name
     * and set the simulator reference to this simulator
     *
     * @param name
     *            the name to set
     * @return the created object
     */
    public WaterHeater waterHeater(final String name) {
        return setupObject(new WaterHeater(this), name);
    }
}
