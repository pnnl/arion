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

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.AuxiliaryStrategy;
import gov.pnnl.prosser.api.gld.enums.AuxiliarySystemType;
import gov.pnnl.prosser.api.gld.enums.CoolingSystemType;
import gov.pnnl.prosser.api.gld.enums.FanType;
import gov.pnnl.prosser.api.gld.enums.HeatingSystemType;
import gov.pnnl.prosser.api.gld.enums.MotorEfficiency;
import gov.pnnl.prosser.api.gld.enums.MotorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * House Object
 *
 * @author nord229
 */
public class House extends ResidentialEnduse {

    /**
     * roof R-value (degF.sf.h/Btu)
     */
    private Double Rroof;

    /**
     * wall R-value (degF.sf.h/Btu)
     */
    private Double Rwall;

    /**
     * floor R-value (degF.sf.h/Btu)
     */
    private Double Rfloor;

    /**
     * door R-value (degF.sf.h/Btu)
     */
    private Double Rdoors;

    /**
     * window R-value (degF.sf.h/Btu)
     */
    private Double Rwindows;

    /**
     * number of air-changes per hour
     */
    private Double airchangePerHour;

    /**
     * power factor of hvac (unit)
     */
    private Double hvacPowerFactor;

    /**
     * cooling system type
     */
    private CoolingSystemType coolingSystemType;

    /**
     * heating system type
     */
    private HeatingSystemType heatingSystemType;

    /**
     * fan type
     */
    private FanType fanType;

    /**
     * determines the amount of current the HVAC circuit breaker can handle (A)
     */
    private Double hvacBreakerRating;

    /**
     * total thermal mass per floor area (Btu/degF.sf)
     */
    private Double totalThermalMassPerFloorArea;

    /**
     * motor efficiency
     */
    private MotorEfficiency motorEfficiency;

    /**
     * motor model
     */
    private MotorModel motorModel;

    /**
     * system cooling performance coefficient (Btu/kWh)
     */
    private Double coolingCop;

    /**
     * home conditioned floor area (sf)
     */
    private Double floorArea;

    /**
     * ratio of door area to wall area
     */
    private Double numberOfDoors;

    /**
     * thermostat heating setpoint (degF)
     */
    private Double heatingSetpoint;

    /**
     * thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property set that here
     */
    private String heatingSetpointFn;

    /**
     * thermostat cooling setpoint (degF)
     */
    private Double coolingSetpoint;

    /**
     * thermostat cooling setpoint (degF) function
     * if the cooling setpoint is a function of another property set that here
     */
    private String coolingSetpointFn;

    /**
     * indoor air temperature (degF)
     */
    private Double airTemperature;

    /**
     * interior mass temperature (degF)
     */
    private Double massTemperature;

    /**
     * FNCS controller
     */
    private Controller controller;

    /**
     * number of stories
     */
    private Integer numberOfStories;

    /**
     * ceiling height (ft)
     */
    private Double ceilingHeight;

    /**
     * over sizing factor
     */
    private Double overSizingFactor;

    /**
     * glazing layers
     */
    private Integer glazingLayers;

    /**
     * glass type
     */
    private Integer glassType;

    /**
     * glazing Treatment
     */
    private Integer glazingTreatment;

    /**
     * window frame
     */
    private Integer windowFrame;

    /**
     * heating COP
     */
    private Double heatingCOP;

    /**
     * auxiliary_strategy
     */
    private AuxiliaryStrategy auxiliaryStrategy;

    /**
     * auxiliary_system_type
     */
    private AuxiliarySystemType auxiliarySystemType;

    /**
	 * @return the auxiliarySystemType
	 */
	public AuxiliarySystemType getAuxiliarySystemType() {
		return auxiliarySystemType;
	}

	/**
	 * @param auxiliarySystemType the auxiliarySystemType to set
	 */
	public void setAuxiliarySystemType(AuxiliarySystemType auxiliarySystemType) {
		this.auxiliarySystemType = auxiliarySystemType;
	}

    /**
	 * @return the auxiliaryStrategy
	 */
	public AuxiliaryStrategy getAuxiliaryStrategy() {
		return auxiliaryStrategy;
	}

	/**
	 * @param auxiliaryStrategy the auxiliaryStrategy to set
	 */
	public void setAuxiliaryStrategy(AuxiliaryStrategy auxiliaryStrategy) {
		this.auxiliaryStrategy = auxiliaryStrategy;
	}

	/**
	 * @return the heatingCOP
	 */
	public Double getHeatingCOP() {
		return heatingCOP;
	}

	/**
	 * @param heatingCOP the heatingCOP to set
	 */
	public void setHeatingCOP(Double heatingCOP) {
		this.heatingCOP = heatingCOP;
	}

	/**
	 * @return the overSizingFactor
	 */
	public double getOverSizingFactor() {
		return overSizingFactor;
	}

	/**
	 * @param overSizingFactor the overSizingFactor to set
	 */
	public void setOverSizingFactor(double overSizingFactor) {
		this.overSizingFactor = overSizingFactor;
	}

	/**
	 * @return the numberOfStories
	 */
	public Integer getNumberOfStories() {
		return numberOfStories;
	}

	/**
	 * @param numberOfStories the numberOfStories to set
	 */
	public void setNumberOfStories(Integer numberOfStories) {
		this.numberOfStories = numberOfStories;
	}

	/**
	 * @return the ceilingHeight
	 */
	public Double getCeilingHeight() {
		return ceilingHeight;
	}

	/**
	 * @param ceilingHeight the ceilingHeight to set
	 */
	public void setCeilingHeight(Double ceilingHeight) {
		this.ceilingHeight = ceilingHeight;
	}

	/**
	 * @return the glazingLayers
	 */
	public Integer getGlazingLayers() {
		return glazingLayers;
	}

	/**
	 * @param glazingLayers the glazingLayers to set
	 */
	public void setGlazingLayers(Integer glazingLayers) {
		this.glazingLayers = glazingLayers;
	}

	/**
	 * @return the glassType
	 */
	public Integer getGlassType() {
		return glassType;
	}

	/**
	 * @param glassType the glassType to set
	 */
	public void setGlassType(Integer glassType) {
		this.glassType = glassType;
	}

	/**
	 * @return the glazingTreatment
	 */
	public Integer getGlazingTreatment() {
		return glazingTreatment;
	}

	/**
	 * @param glazingTreatment the glazingTreatment to set
	 */
	public void setGlazingTreatment(Integer glazingTreatment) {
		this.glazingTreatment = glazingTreatment;
	}

	/**
	 * @return the windowFrame
	 */
	public Integer getWindowFrame() {
		return windowFrame;
	}

	/**
	 * @param windowFrame the windowFrame to set
	 */
	public void setWindowFrame(Integer windowFrame) {
		this.windowFrame = windowFrame;
	}

	/**
	 * @param overSizingFactor the overSizingFactor to set
	 */
	public void setOverSizingFactor(Double overSizingFactor) {
		this.overSizingFactor = overSizingFactor;
	}

	/**
     * the loads on the house (lights, etc.)
     */
    private final List<ZIPLoad> loads = new ArrayList<>();

    public House(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the roof R-value (degF.sf.h/Btu)
     *
     * @return the rroof
     */
    public Double getRroof() {
        return Rroof;
    }

    /**
     * Set the roof R-value (degF.sf.h/Btu)
     *
     * @param rroof
     *            the rroof to set
     */
    public void setRroof(final Double rroof) {
        Rroof = rroof;
    }

    /**
     * Get the wall R-value (degF.sf.h/Btu)
     *
     * @return the rwall
     */
    public Double getRwall() {
        return Rwall;
    }

    /**
     * Set the wall R-value (degF.sf.h/Btu)
     *
     * @param rwall
     *            the rwall to set
     */
    public void setRwall(final Double rwall) {
        Rwall = rwall;
    }

    /**
     * Get the floor R-value (degF.sf.h/Btu)
     *
     * @return the rfloor
     */
    public Double getRfloor() {
        return Rfloor;
    }

    /**
     * Set the floor R-value (degF.sf.h/Btu)
     *
     * @param rfloor
     *            the rfloor to set
     */
    public void setRfloor(final Double rfloor) {
        Rfloor = rfloor;
    }

    /**
     * Get the door R-value (degF.sf.h/Btu)
     *
     * @return the rdoors
     */
    public Double getRdoors() {
        return Rdoors;
    }

    /**
     * Set the door R-value (degF.sf.h/Btu)
     *
     * @param rdoors
     *            the rdoors to set
     */
    public void setRdoors(final Double rdoors) {
        Rdoors = rdoors;
    }

    /**
     * Get the window R-value (degF.sf.h/Btu)
     *
     * @return the rwindows
     */
    public Double getRwindows() {
        return Rwindows;
    }

    /**
     * Set the window R-value (degF.sf.h/Btu)
     *
     * @param rwindows
     *            the rwindows to set
     */
    public void setRwindows(final Double rwindows) {
        Rwindows = rwindows;
    }

    /**
     * Get the number of air-changes per hour
     *
     * @return the airchangePerHour
     */
    public Double getAirchangePerHour() {
        return airchangePerHour;
    }

    /**
     * Set the number of air-changes per hour
     *
     * @param airchangePerHour
     *            the airchangePerHour to set
     */
    public void setAirchangePerHour(final Double airchangePerHour) {
        this.airchangePerHour = airchangePerHour;
    }

    /**
     * Get the power factor of hvac (unit)
     *
     * @return the hvacPowerFactor
     */
    public Double getHvacPowerFactor() {
        return hvacPowerFactor;
    }

    /**
     * Set the power factor of hvac (unit)
     *
     * @param hvacPowerFactor
     *            the hvacPowerFactor to set
     */
    public void setHvacPowerFactor(final Double hvacPowerFactor) {
        this.hvacPowerFactor = hvacPowerFactor;
    }

    /**
     * Get the cooling system type
     *
     * @return the coolingSystemType
     */
    public CoolingSystemType getCoolingSystemType() {
        return coolingSystemType;
    }

    /**
     * Set the cooling system type
     *
     * @param coolingSystemType
     *            the coolingSystemType to set
     */
    public void setCoolingSystemType(final CoolingSystemType coolingSystemType) {
        this.coolingSystemType = coolingSystemType;
    }

    /**
     * Get the heating system type
     *
     * @return the heatingSystemType
     */
    public HeatingSystemType getHeatingSystemType() {
        return heatingSystemType;
    }

    /**
     * Set the heating system type
     *
     * @param heatingSystemType
     *            the heatingSystemType to set
     */
    public void setHeatingSystemType(final HeatingSystemType heatingSystemType) {
        this.heatingSystemType = heatingSystemType;
    }

    /**
     * Get the fan type
     *
     * @return the fanType
     */
    public FanType getFanType() {
        return fanType;
    }

    /**
     * Set the fan type
     *
     * @param fanType
     *            the fanType to set
     */
    public void setFanType(final FanType fanType) {
        this.fanType = fanType;
    }

    /**
     * Get the amount of current the HVAC circuit breaker can handle (A)
     *
     * @return the hvacBreakerRating
     */
    public Double getHvacBreakerRating() {
        return hvacBreakerRating;
    }

    /**
     * Set the amount of current the HVAC circuit breaker can handle (A)
     *
     * @param hvacBreakerRating
     *            the hvacBreakerRating to set
     */
    public void setHvacBreakerRating(final Double hvacBreakerRating) {
        this.hvacBreakerRating = hvacBreakerRating;
    }

    /**
     * Get the total thermal mass per floor area (Btu/degF.sf)
     *
     * @return the totalThermalMassPerFloorArea
     */
    public Double getTotalThermalMassPerFloorArea() {
        return totalThermalMassPerFloorArea;
    }

    /**
     * Set the total thermal mass per floor area (Btu/degF.sf)
     *
     * @param totalThermalMassPerFloorArea
     *            the totalThermalMassPerFloorArea to set
     */
    public void setTotalThermalMassPerFloorArea(final Double totalThermalMassPerFloorArea) {
        this.totalThermalMassPerFloorArea = totalThermalMassPerFloorArea;
    }

    /**
     * Get the motor efficiency
     *
     * @return the motorEfficiency
     */
    public MotorEfficiency getMotorEfficiency() {
        return motorEfficiency;
    }

    /**
     * Set the motor efficiency
     *
     * @param motorEfficiency
     *            the motorEfficiency to set
     */
    public void setMotorEfficiency(final MotorEfficiency motorEfficiency) {
        this.motorEfficiency = motorEfficiency;
    }

    /**
     * Get the motor model
     *
     * @return the motorModel
     */
    public MotorModel getMotorModel() {
        return motorModel;
    }

    /**
     * Set the motor model
     *
     * @param motorModel
     *            the motorModel to set
     */
    public void setMotorModel(final MotorModel motorModel) {
        this.motorModel = motorModel;
    }

    /**
     * Get the system cooling performance coefficient (Btu/kWh)
     *
     * @return the coolingCop
     */
    public Double getCoolingCop() {
        return coolingCop;
    }

    /**
     * Set the system cooling performance coefficient (Btu/kWh)
     *
     * @param coolingCop
     *            the coolingCop to set
     */
    public void setCoolingCop(final Double coolingCop) {
        this.coolingCop = coolingCop;
    }

    /**
     * Get the home conditioned floor area (sf)
     *
     * @return the floorArea
     */
    public Double getFloorArea() {
        return floorArea;
    }

    /**
     * Set the home conditioned floor area (sf)
     *
     * @param floorArea
     *            the floorArea to set
     */
    public void setFloorArea(final Double floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * Get the ratio of door area to wall area
     *
     * @return the numberOfDoors
     */
    public Double getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Set the ratio of door area to wall area
     *
     * @param numberOfDoors
     *            the numberOfDoors to set
     */
    public void setNumberOfDoors(final Double numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Get the thermostat heating setpoint (degF)
     *
     * @return the heatingSetpoint
     */
    public Double getHeatingSetpoint() {
        return heatingSetpoint;
    }

    /**
     * Set the thermostat heating setpoint (degF)
     *
     * @param heatingSetpoint
     *            the heatingSetpoint to set
     */
    public void setHeatingSetpoint(final Double heatingSetpoint) {
        this.heatingSetpoint = heatingSetpoint;
    }

    /**
     * Get the thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property it will be set here
     *
     * @return the heatingSetpointFn
     */
    public String getHeatingSetpointFn() {
        return heatingSetpointFn;
    }

    /**
     * Set the thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property set that here
     *
     * @param heatingSetpointFn
     *            the heatingSetpointFn to set
     */
    public void setHeatingSetpointFn(final String heatingSetpointFn) {
        this.heatingSetpointFn = heatingSetpointFn;
    }

    /**
     * Get the thermostat cooling setpoint (degF)
     *
     * @return the coolingSetpoint
     */
    public Double getCoolingSetpoint() {
        return coolingSetpoint;
    }

    /**
     * Set the thermostat cooling setpoint (degF)
     *
     * @param coolingSetpoint
     *            the coolingSetpoint to set
     */
    public void setCoolingSetpoint(Double coolingSetpoint) {
        this.coolingSetpoint = coolingSetpoint;
    }

    /**
     * Get the thermostat cooling setpoint (degF) function
     * if the cooling setpoint is a function of another property it will be set here
     *
     * @return the coolingSetpointFn
     */
    public String getCoolingSetpointFn() {
        return coolingSetpointFn;
    }

    /**
     * Set the thermostat cooling setpoint (degF) function
     * if the cooling setpoint is a function of another property set it here
     *
     * @param coolingSetpointFn
     *            the coolingSetpointFn to set
     */
    public void setCoolingSetpointFn(String coolingSetpointFn) {
        this.coolingSetpointFn = coolingSetpointFn;
    }

    /**
     * Get the indoor air temperature (degF)
     *
     * @return the airTemperature
     */
    public Double getAirTemperature() {
        return airTemperature;
    }

    /**
     * Set the indoor air temperature (degF)
     *
     * @param airTemperature
     *            the airTemperature to set
     */
    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    /**
     * Get the interior mass temperature (degF)
     *
     * @return the massTemperature
     */
    public Double getMassTemperature() {
        return massTemperature;
    }

    /**
     * Set the interior mass temperature (degF)
     *
     * @param massTemperature
     *            the massTemperature to set
     */
    public void setMassTemperature(Double massTemperature) {
        this.massTemperature = massTemperature;
    }

    /**
     * Get the FNCS controller
     *
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Set the FNCS controller
     *
     * @param controller
     *            the controller to set
     */
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     * Add a ZIPLoad to this House
     *
     * @param load
     *            the load to add
     */
    public void addLoad(final ZIPLoad load) {
        this.loads.add(load);
    }

    /**
     * Create and Add a Zip load to this house
     *
     * @return the Zip load
     */
    public ZIPLoad addLoad() {
        final ZIPLoad load = new ZIPLoad(this.simulator);
        this.loads.add(load);
        return load;
    }

    /**
     * Create and set the controller on this house
     *
     * @param name
     *            the controller name
     * @return the controller
     */
    public Controller controller(final String name) {
        this.controller = new Controller(this.simulator);
        controller.setName(name);
        return controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "house";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        writeProperty(sb, "Rroof", this.Rroof);
        writeProperty(sb, "Rwall", this.Rwall);
        writeProperty(sb, "Rfloor", this.Rfloor);
        writeProperty(sb, "Rdoors", this.Rdoors);
        writeProperty(sb, "Rwindows", this.Rwindows);
        writeProperty(sb, "airchange_per_hour", this.airchangePerHour);
        writeProperty(sb, "hvac_power_factor", this.hvacPowerFactor);
        writeProperty(sb, "cooling_system_type", this.coolingSystemType);
        writeProperty(sb, "heating_system_type", this.heatingSystemType);
        writeProperty(sb, "fan_type", this.fanType);
        writeProperty(sb, "hvac_breaker_rating", this.hvacBreakerRating);
        writeProperty(sb, "total_thermal_mass_per_floor_area", this.totalThermalMassPerFloorArea);
        writeProperty(sb, "motor_efficiency", this.motorEfficiency);
        writeProperty(sb, "motor_model", this.motorModel);
        writeProperty(sb, "cooling_COP", this.coolingCop);
        writeProperty(sb, "heating_COP", this.heatingCOP);
        writeProperty(sb, "floor_area", this.floorArea);
        writeProperty(sb, "number_of_doors", this.numberOfDoors);
        writeProperty(sb, "number_of_stories", this.numberOfStories);
        writeProperty(sb, "ceiling_height", this.ceilingHeight);
        writeProperty(sb, "over_sizing_factor", this.overSizingFactor);
        writeProperty(sb, "glazing_layers", this.glazingLayers);
        writeProperty(sb, "glass_type", this.glassType);
        writeProperty(sb, "glazing_treatment", this.glazingTreatment);
        writeProperty(sb, "window_frame", this.windowFrame);
        writeProperty(sb, "auxiliary_strategy", this.auxiliaryStrategy);
        writeProperty(sb, "auxiliary_system_type", this.auxiliarySystemType);
        writeProperty(sb, "air_temperature", this.airTemperature);
        writeProperty(sb, "mass_temperature", this.massTemperature);
        if (heatingSetpointFn != null) {
            writeProperty(sb, "heating_setpoint", this.heatingSetpointFn);
        } else {
            writeProperty(sb, "heating_setpoint", this.heatingSetpoint);
        }
        if (coolingSetpointFn != null) {
            writeProperty(sb, "cooling_setpoint", this.coolingSetpointFn);
        } else {
            writeProperty(sb, "cooling_setpoint", this.coolingSetpoint);
        }
        if (controller != null) {
            controller.writeGldString(sb);
            // Handle special case since we need a semicolon here
            sb.insert(sb.length() - 1, ';');
        }
        for (final ZIPLoad load : loads) {
            load.writeGldString(sb);
            // Handle special case since we need a semicolon here
            sb.insert(sb.length() - 1, ';');
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Rroof, Rwall, Rfloor, Rdoors, Rwindows, airchangePerHour, hvacPowerFactor, coolingSystemType,
                heatingSystemType, fanType, hvacBreakerRating, totalThermalMassPerFloorArea, motorEfficiency, motorModel, coolingCop,
                floorArea, numberOfDoors, heatingSetpoint, heatingSetpointFn, coolingSetpoint, coolingSetpointFn, airTemperature,
                massTemperature, controller, numberOfStories, ceilingHeight, overSizingFactor, glazingLayers, glassType, glazingTreatment,
                windowFrame, heatingCOP, auxiliaryStrategy, auxiliarySystemType);
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
        final House other = (House) obj;
        return Objects.equals(this.Rroof, other.Rroof) && Objects.equals(this.Rwall, other.Rwall)
                && Objects.equals(this.Rfloor, other.Rfloor) && Objects.equals(this.Rdoors, other.Rdoors)
                && Objects.equals(this.Rwindows, other.Rwindows) && Objects.equals(this.airchangePerHour, other.airchangePerHour)
                && Objects.equals(this.hvacPowerFactor, other.hvacPowerFactor)
                && Objects.equals(this.coolingSystemType, other.coolingSystemType)
                && Objects.equals(this.heatingSystemType, other.heatingSystemType)
                && Objects.equals(this.fanType, other.fanType) && Objects.equals(this.hvacBreakerRating, other.hvacBreakerRating)
                && Objects.equals(this.totalThermalMassPerFloorArea, other.totalThermalMassPerFloorArea)
                && Objects.equals(this.motorEfficiency, other.motorEfficiency)
                && Objects.equals(this.motorModel, other.motorModel) && Objects.equals(this.coolingCop, other.coolingCop)
                && Objects.equals(this.floorArea, other.floorArea) && Objects.equals(this.numberOfDoors, other.numberOfDoors)
                && Objects.equals(this.heatingSetpoint, other.heatingSetpoint)
                && Objects.equals(this.heatingSetpointFn, other.heatingSetpointFn)
                && Objects.equals(this.coolingSetpoint, other.coolingSetpoint)
                && Objects.equals(this.coolingSetpointFn, other.coolingSetpointFn)
                && Objects.equals(this.airTemperature, other.airTemperature)
                && Objects.equals(this.massTemperature, other.massTemperature)
                && Objects.equals(this.controller, other.controller) && Objects.equals(this.numberOfStories, other.numberOfStories)
                && Objects.equals(this.ceilingHeight, other.ceilingHeight) && Objects.equals(this.overSizingFactor, other.overSizingFactor)
                && Objects.equals(this.glazingLayers, other.glazingLayers) && Objects.equals(this.glassType, other.glassType)
                && Objects.equals(this.glazingTreatment, other.glazingTreatment) && Objects.equals(this.windowFrame, other.windowFrame)
                && Objects.equals(this.heatingCOP, other.heatingCOP) && Objects.equals(this.auxiliaryStrategy, other.auxiliaryStrategy)
                && Objects.equals(this.auxiliarySystemType, other.auxiliarySystemType);
    }

}
