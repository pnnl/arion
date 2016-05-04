/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;


import gov.pnnl.prosser.api.gld.enums.WaterheaterLocation;

import java.util.Random;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * @author fish334
 *
 */
public class WaterHeater extends ResidentialEnduse {
	/**
	 * The heating element capacity in kW
	 */
	private Double heatingElementCapacity;
	
	/**
	 * The tank thermostat setpoint in degrees F
	 */
	private Double tankSetpoint;
	
	/**
	 * The temperature of the tank in degrees F
	 */
	private Double temperature;
	
	/**
	 * The thermostat deadband in degrees F
	 */
	private Double thermostatDeadband;
	
	/**
	 * The location of the waterheater: Either INSIDE or OUTSIDE
	 */
	private WaterheaterLocation location;
	
	/**
	 * The tank UA in Btu*h/degF
	 */
	private Double tankUA;
	
	/**
	 * The water demand from the waterheater in gallons per minute
	 */
	private Double waterDemand;
	
	/**
	 * The tank volume in gal
	 */
	private Double tankVolume;
	
	/**
	 * The tank diameter in ft
	 */
	private Double tankDiameter;
	
	/**
	 * The tank height in ft
	 */
	private Double tankHeight;
	
	/**
	 * water demand (gal/min)function
	 * if the water demand is a function of another property set that here
	 */
	private String waterDemandFn;
	
	/**
	 * @return the heatingElementCapacity
	 */
	public Double getHeatingElementCapacity() {
		return heatingElementCapacity;
	}

	/**
	 * @param heatingElementCapacity the heatingElementCapacity to set
	 */
	public void setHeatingElementCapacity(final Double heatingElementCapacity) {
		this.heatingElementCapacity = heatingElementCapacity;
	}

	/**
	 * @return the tankSetpoint
	 */
	public Double getTankSetpoint() {
		return tankSetpoint;
	}

	/**
	 * @param tankSetpoint the tankSetpoint to set
	 */
	public void setTankSetpoint(final Double tankSetpoint) {
		this.tankSetpoint = tankSetpoint;
	}

	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(final Double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the thermostatDeadband
	 */
	public Double getThermostatDeadband() {
		return thermostatDeadband;
	}

	/**
	 * @param thermostatDeadband the thermostatDeadband to set
	 */
	public void setThermostatDeadband(final Double thermostatDeadband) {
		this.thermostatDeadband = thermostatDeadband;
	}

	/**
	 * @return the location
	 */
	public WaterheaterLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(final WaterheaterLocation location) {
		this.location = location;
	}

	/**
	 * @return the tankUA
	 */
	public Double getTankUA() {
		return tankUA;
	}

	/**
	 * @param tankUA the tankUA to set
	 */
	public void setTankUA(final Double tankUA) {
		this.tankUA = tankUA;
	}

	/**
	 * @return the waterDemand
	 */
	public Double getWaterDemand() {
		return waterDemand;
	}

	/**
	 * @param waterDemand the waterDemand to set
	 */
	public void setWaterDemand(final Double waterDemand) {
		this.waterDemand = waterDemand;
	}

	/**
	 * @return the tankVolume
	 */
	public Double getTankVolume() {
		return tankVolume;
	}

	/**
	 * @param tankVolume the tankVolume to set
	 */
	public void setTankVolume(final Double tankVolume) {
		this.tankVolume = tankVolume;
	}

	/**
	 * @return the tankDiameter
	 */
	public Double getTankDiameter() {
		return tankDiameter;
	}

	/**
	 * @param tankDiameter the tankDiameter to set
	 */
	public void setTankDiameter(final Double tankDiameter) {
		this.tankDiameter = tankDiameter;
	}

	/**
	 * @return the tankHeight
	 */
	public Double getTankHeight() {
		return tankHeight;
	}
	
	/**
	 * @param tankHeight the tankHeight to set
	 */
	public void setTankHeight(final Double tankHeight) {
		this.tankHeight = tankHeight;
	}

	/**
	 * @return the waterDemandFn
	 */
	public String getWaterDemandFn() {
		return waterDemandFn;
	}

	/**
	 * @param waterDemandFn the waterDemandFn to set
	 */
	public void setWaterDemandFn(final String waterDemandFn) {
		this.waterDemandFn = waterDemandFn;
	}
	
	/**
	 * @param simulator
	 */
	public WaterHeater(GldSimulator simulator) {
		super(simulator);
		this.location = WaterheaterLocation.INSIDE;
		
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
	 */
	@Override
	protected String getGldObjectType() {
		return "waterheater";
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
	 */
	@Override
	protected void writeGldProperties(final StringBuilder sb) {
		super.writeGldProperties(sb);
		writeProperty(sb, "heating_element_capacity", this.heatingElementCapacity, "kW");
		writeProperty(sb, "tank_setpoint", this.tankSetpoint);
		writeProperty(sb, "temperature", this.temperature);
		writeProperty(sb, "thermostat_deadband", this.thermostatDeadband);
		writeProperty(sb, "location", this.location);
		writeProperty(sb, "tank_UA", this.tankUA);
		writeProperty(sb, "water_demand", this.waterDemand);
		writeProperty(sb, "tank_volume", this.tankVolume);
		writeProperty(sb, "tank_diameter", this.tankDiameter);
		writeProperty(sb, "tank_height", this.tankHeight);
	}
}
