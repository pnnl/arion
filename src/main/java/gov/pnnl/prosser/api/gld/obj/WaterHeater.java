/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
*    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
*    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
*    and may permit others to do so, subject to the following conditions:
*    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
*       the following disclaimers.
*    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
*       the following disclaimer in the documentation and/or other materials provided with the distribution.
*    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
*       form whatsoever without the express written consent of Battelle.
* 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
*    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
*    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
*    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*                                PACIFIC NORTHWEST NATIONAL LABORATORY
*                                            operated by
*                                              BATTELLE
*                                              for the
*                                  UNITED STATES DEPARTMENT OF ENERGY
*                                   under Contract DE-AC05-76RL01830
*/
package gov.pnnl.prosser.api.gld.obj;


import gov.pnnl.prosser.api.gld.enums.WaterheaterLocation;

import java.util.Objects;

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

	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), heatingElementCapacity, tankSetpoint, temperature, thermostatDeadband, location,
                tankUA, waterDemand, tankVolume, tankDiameter, tankHeight, waterDemandFn);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WaterHeater other = (WaterHeater) obj;
        return Objects.equals(this.heatingElementCapacity, other.heatingElementCapacity)
                && Objects.equals(this.tankSetpoint, other.tankSetpoint)
                && Objects.equals(this.temperature, other.temperature)
                && Objects.equals(this.thermostatDeadband, other.thermostatDeadband)
                && Objects.equals(this.location, other.location)
                && Objects.equals(this.tankUA, other.tankUA)
                && Objects.equals(this.waterDemand, other.waterDemand)
                && Objects.equals(this.tankVolume, other.tankVolume)
                && Objects.equals(this.tankDiameter, other.tankDiameter)
                && Objects.equals(this.tankHeight, other.tankHeight)
                && Objects.equals(this.waterDemandFn, other.waterDemandFn);
    }
}
