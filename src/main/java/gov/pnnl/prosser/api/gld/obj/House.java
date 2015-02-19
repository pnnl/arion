/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.gld.enums.CoolingSystemType;
import gov.pnnl.prosser.api.gld.enums.FanType;
import gov.pnnl.prosser.api.gld.enums.HeatingSystemType;
import gov.pnnl.prosser.api.gld.enums.MotorEfficiency;
import gov.pnnl.prosser.api.gld.enums.MotorModel;

/**
 * House Object
 *
 * @author nord229
 */
public class House extends ResidentialEnduse {

    private Node parent;

    private Double Rroof;

    private Double Rwall;

    private Double Rfloor;

    private Double Rdoors;

    private Double Rwindows;

    private Double airchangePerHour;

    private Double hvacPowerFactor;

    private CoolingSystemType coolingSystemType;

    private HeatingSystemType heatingSystemType;

    private FanType fanType;

    private Double hvacBreakerRating;

    private Double totalThermalMassPerFloorArea;

    private MotorEfficiency motorEfficiency;

    private MotorModel motorModel;

    private Double coolingCop;

    private Double floorArea;

    private Double numberOfDoors;

    private Double heatingSetpoint;

    private String heatingSetpointFn;

    private Controller controller;

    private final List<ZIPLoad> loads = new ArrayList<>();

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    /**
     * @return the rroof
     */
    public Double getRroof() {
        return Rroof;
    }

    /**
     * @param rroof
     *            the rroof to set
     */
    public void setRroof(final Double rroof) {
        Rroof = rroof;
    }

    /**
     * @return the rwall
     */
    public Double getRwall() {
        return Rwall;
    }

    /**
     * @param rwall
     *            the rwall to set
     */
    public void setRwall(final Double rwall) {
        Rwall = rwall;
    }

    /**
     * @return the rfloor
     */
    public Double getRfloor() {
        return Rfloor;
    }

    /**
     * @param rfloor
     *            the rfloor to set
     */
    public void setRfloor(final Double rfloor) {
        Rfloor = rfloor;
    }

    /**
     * @return the rdoors
     */
    public Double getRdoors() {
        return Rdoors;
    }

    /**
     * @param rdoors
     *            the rdoors to set
     */
    public void setRdoors(final Double rdoors) {
        Rdoors = rdoors;
    }

    /**
     * @return the rwindows
     */
    public Double getRwindows() {
        return Rwindows;
    }

    /**
     * @param rwindows
     *            the rwindows to set
     */
    public void setRwindows(final Double rwindows) {
        Rwindows = rwindows;
    }

    /**
     * @return the airchangePerHour
     */
    public Double getAirchangePerHour() {
        return airchangePerHour;
    }

    /**
     * @param airchangePerHour
     *            the airchangePerHour to set
     */
    public void setAirchangePerHour(final Double airchangePerHour) {
        this.airchangePerHour = airchangePerHour;
    }

    /**
     * @return the hvacPowerFactor
     */
    public Double getHvacPowerFactor() {
        return hvacPowerFactor;
    }

    /**
     * @param hvacPowerFactor
     *            the hvacPowerFactor to set
     */
    public void setHvacPowerFactor(final Double hvacPowerFactor) {
        this.hvacPowerFactor = hvacPowerFactor;
    }

    /**
     * @return the coolingSystemType
     */
    public CoolingSystemType getCoolingSystemType() {
        return coolingSystemType;
    }

    /**
     * @param coolingSystemType
     *            the coolingSystemType to set
     */
    public void setCoolingSystemType(final CoolingSystemType coolingSystemType) {
        this.coolingSystemType = coolingSystemType;
    }

    /**
     * @return the heatingSystemType
     */
    public HeatingSystemType getHeatingSystemType() {
        return heatingSystemType;
    }

    /**
     * @param heatingSystemType
     *            the heatingSystemType to set
     */
    public void setHeatingSystemType(final HeatingSystemType heatingSystemType) {
        this.heatingSystemType = heatingSystemType;
    }

    /**
     * @return the fanType
     */
    public FanType getFanType() {
        return fanType;
    }

    /**
     * @param fanType
     *            the fanType to set
     */
    public void setFanType(final FanType fanType) {
        this.fanType = fanType;
    }

    /**
     * @return the hvacBreakerRating
     */
    public Double getHvacBreakerRating() {
        return hvacBreakerRating;
    }

    /**
     * @param hvacBreakerRating
     *            the hvacBreakerRating to set
     */
    public void setHvacBreakerRating(final Double hvacBreakerRating) {
        this.hvacBreakerRating = hvacBreakerRating;
    }

    /**
     * @return the totalThermalMassPerFloorArea
     */
    public Double getTotalThermalMassPerFloorArea() {
        return totalThermalMassPerFloorArea;
    }

    /**
     * @param totalThermalMassPerFloorArea
     *            the totalThermalMassPerFloorArea to set
     */
    public void setTotalThermalMassPerFloorArea(final Double totalThermalMassPerFloorArea) {
        this.totalThermalMassPerFloorArea = totalThermalMassPerFloorArea;
    }

    /**
     * @return the motorEfficiency
     */
    public MotorEfficiency getMotorEfficiency() {
        return motorEfficiency;
    }

    /**
     * @param motorEfficiency
     *            the motorEfficiency to set
     */
    public void setMotorEfficiency(final MotorEfficiency motorEfficiency) {
        this.motorEfficiency = motorEfficiency;
    }

    /**
     * @return the motorModel
     */
    public MotorModel getMotorModel() {
        return motorModel;
    }

    /**
     * @param motorModel
     *            the motorModel to set
     */
    public void setMotorModel(final MotorModel motorModel) {
        this.motorModel = motorModel;
    }

    /**
     * @return the coolingCop
     */
    public Double getCoolingCop() {
        return coolingCop;
    }

    /**
     * @param coolingCop
     *            the coolingCop to set
     */
    public void setCoolingCop(final Double coolingCop) {
        this.coolingCop = coolingCop;
    }

    /**
     * @return the floorArea
     */
    public Double getFloorArea() {
        return floorArea;
    }

    /**
     * @param floorArea
     *            the floorArea to set
     */
    public void setFloorArea(final Double floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * @return the numberOfDoors
     */
    public Double getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * @param numberOfDoors
     *            the numberOfDoors to set
     */
    public void setNumberOfDoors(final Double numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * @return the heatingSetpoint
     */
    public Double getHeatingSetpoint() {
        return heatingSetpoint;
    }

    /**
     * @param heatingSetpoint
     *            the heatingSetpoint to set
     */
    public void setHeatingSetpoint(final Double heatingSetpoint) {
        this.heatingSetpoint = heatingSetpoint;
    }

    /**
     * @return the heatingSetpointFn
     */
    public String getHeatingSetpointFn() {
        return heatingSetpointFn;
    }

    /**
     * @param heatingSetpointFn
     *            the heatingSetpointFn to set
     */
    public void setHeatingSetpointFn(final String heatingSetpointFn) {
        this.heatingSetpointFn = heatingSetpointFn;
    }

    /**
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @param controller
     *            the controller to set
     */
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    public void addLoad(final ZIPLoad load) {
        this.loads.add(load);
    }

    @Override
    public String getGldObjectType() {
        return "house";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
        GldUtils.writeProperty(sb, "Rroof", this.Rroof);
        GldUtils.writeProperty(sb, "Rwall", this.Rwall);
        GldUtils.writeProperty(sb, "Rfloor", this.Rfloor);
        GldUtils.writeProperty(sb, "Rdoors", this.Rdoors);
        GldUtils.writeProperty(sb, "Rwindows", this.Rwindows);
        GldUtils.writeProperty(sb, "airchange_per_hour", this.airchangePerHour);
        GldUtils.writeProperty(sb, "hvac_power_factor", this.hvacPowerFactor);
        GldUtils.writeProperty(sb, "cooling_system_type", this.coolingSystemType);
        GldUtils.writeProperty(sb, "heating_system_type", this.heatingSystemType);
        GldUtils.writeProperty(sb, "fan_type", this.fanType);
        GldUtils.writeProperty(sb, "hvac_breaker_rating", this.hvacBreakerRating);
        GldUtils.writeProperty(sb, "total_thermal_mass_per_floor_area", this.totalThermalMassPerFloorArea);
        GldUtils.writeProperty(sb, "motor_efficiency", this.motorEfficiency);
        GldUtils.writeProperty(sb, "motor_model", this.motorModel);
        GldUtils.writeProperty(sb, "cooling_COP", this.coolingCop);
        GldUtils.writeProperty(sb, "floor_area", this.floorArea);
        GldUtils.writeProperty(sb, "number_of_doors", this.numberOfDoors);
        if (heatingSetpointFn != null) {
            GldUtils.writeProperty(sb, "heating_setpoint", this.heatingSetpointFn);
        } else {
            GldUtils.writeProperty(sb, "heating_setpoint", this.heatingSetpoint);
        }
        controller.writeGldString(sb);
        // Handle special case since we need a semicolon here
        sb.insert(sb.length() - 1, ';');
        for (final ZIPLoad load : loads) {
            load.writeGldString(sb);
            // Handle special case since we need a semicolon here
            sb.insert(sb.length() - 1, ';');
        }
    }

}
