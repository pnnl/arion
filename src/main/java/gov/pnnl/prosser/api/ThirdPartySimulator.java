/**
 * 
 */
package gov.pnnl.prosser.api;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import gov.pnnl.prosser.api.thirdparty.enums.SimType;

/**
 * @author fish334
 *
 */
public class ThirdPartySimulator {
	private final String name;
	
	private String broker;
	
	private final List<Path> modelFiles = new ArrayList<Path>();
	
	private GldSimulator gldSim;
	
	private Map<GldSimulator, String> gldSimulators = new TreeMap<>();
	
	private final SimType simType;

	/**
	 * @param name
	 * @param The simulation type: either MATPOWER, MATLAB_AGGREGATOR
	 * 
	 */
	public ThirdPartySimulator(String name, SimType simType) {
		super();
		this.name = name;
		this.broker = "tcp://localhost:5570";
		this.simType = simType;
	}

	/**
	 * @return the broker
	 */
	public String getBroker() {
		return broker;
	}

	/**
	 * @param broker the broker to set
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}

	/**
	 * @return the gldSim
	 */
	public GldSimulator getGldSim() {
		return gldSim;
	}

	/**
	 * @param gldSim the gldSim to set
	 */
	public void setGldSim(GldSimulator gldSim) {
		this.gldSim = gldSim;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * add model files for the simulator
	 * @param Path
	 */
	public void addModelFiles(final Path... modelFiles){
		this.modelFiles.addAll(Arrays.asList(modelFiles));
	}
	
	/**
	 * Get the model files for the simulator
	 * @return the model files
	 */
	public List<Path> getModelFiles(){
		return this.modelFiles;
	}
	
	/**
	 * @return the gldSimulators
	 */
	public Map<GldSimulator, String> getGldSimulators() {
		return gldSimulators;
	}
	
	/**
	 * add a gridlabd to matpower bus connection
	 * @param gldSim
	 * 				The GldSimulator being connected to the matpower model
	 * @param busName
	 * 				The name of the bus being connected to the GldSimulator
	 */
	public void addGldSimulator(GldSimulator gldSim, String busName){
		this.gldSimulators.put(gldSim, busName);
	}

	/**
	 * @return the simType
	 */
	public SimType getSimType() {
		return simType;
	}
}
