/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.BidMode;
import gov.pnnl.prosser.api.gld.enums.ControlMode;
import gov.pnnl.prosser.api.gld.enums.UseOverride;

/**
 * Market Controller embedded in Houses
 * 
 * @author nord229
 */
public class Controller extends AbstractGldObject implements NetworkCapable {

    /**
     * use override
     */
    private UseOverride useOverride;

    /**
     * override property
     */
    private String override;

    /**
     * connected auction
     */
    private AuctionObject auction;

    /**
     * time skew applied to schedule operations involving this object
     */
    private Long scheduleSkew;

    /**
     * bid mode
     */
    private BidMode bidMode;

    /**
     * proxy delay
     */
    private Integer proxyDelay;

    /**
     * the control mode to use for determining controller action
     */
    private ControlMode controlMode;

    /**
     * the base setpoint to base control off of
     */
    private Double baseSetpoint;

    /**
     * the base setpoint to base control off of function
     * if the base setpoint is a function of another property set that here
     */
    private String baseSetpointFn;

    /**
     * the controlled property (e.g., heating setpoint)
     */
    private String setpoint;

    /**
     * the observed property (e.g., air temperature)
     */
    private String target;

    /**
     * deadband
     */
    private String deadband;

    /**
     * should use predictive bidding
     */
    private Boolean usePredictiveBidding;

    /**
     * average target
     */
    private String averageTarget;

    /**
     * standard deviation target
     */
    private String standardDeviationTarget;

    /**
     * interval of time between market clearings (s)
     */
    private Double period;

    /**
     * the controlled load when on
     */
    private String demand;

    /**
     * the setpoint limit on the high side
     */
    private Double rangeHigh;

    /**
     * the setpoint limit on the low side
     */
    private Double rangeLow;

    /**
     * the comfort response above the setpoint (degF)
     */
    private Double rampHigh;

    /**
     * the comfort response below the setpoint (degF)
     */
    private Double rampLow;

    /**
     * the uncontrolled load (if any)
     */
    private String total;

    /**
     * the current controlled load
     */
    private String load;

    /**
     * the state property of the controlled load
     */
    private String state;

    /**
     * the slider setting
     */
    private Double sliderSetting;

    /**
     * controller network interface name
     */
    private String networkInterfaceName;

    public Controller(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureMarketModule();
        simulator.ensureCommModule();
    }

    /**
     * Get if using override
     * 
     * @return the useOverride
     */
    public UseOverride getUseOverride() {
        return useOverride;
    }

    /**
     * Set if using override
     * 
     * @param useOverride
     *            the useOverride to set
     */
    public void setUseOverride(final UseOverride useOverride) {
        this.useOverride = useOverride;
    }

    /**
     * Get the override property
     * 
     * @return the override
     */
    public String getOverride() {
        return override;
    }

    /**
     * Set the override property
     * 
     * @param override
     *            the override to set
     */
    public void setOverride(final String override) {
        this.override = override;
    }

    /**
     * Get the connected auction
     * 
     * @return the auction
     */
    public AuctionObject getAuction() {
        return auction;
    }

    /**
     * Set the connected auction
     * 
     * @param auction
     *            the auction to set
     */
    public void setAuction(final AuctionObject auction) {
        this.auction = auction;
    }

    /**
     * Get the time skew applied to schedule operations involving this object
     * 
     * @return the scheduleSkew
     */
    public Long getScheduleSkew() {
        return scheduleSkew;
    }

    /**
     * Set the time skew applied to schedule operations involving this object
     * 
     * @param scheduleSkew
     *            the scheduleSkew to set
     */
    public void setScheduleSkew(final Long scheduleSkew) {
        this.scheduleSkew = scheduleSkew;
    }

    /**
     * Get the bid mode
     * 
     * @return the bidMode
     */
    public BidMode getBidMode() {
        return bidMode;
    }

    /**
     * Set the bid mode
     * 
     * @param bidMode
     *            the bidMode to set
     */
    public void setBidMode(final BidMode bidMode) {
        this.bidMode = bidMode;
    }

    /**
     * Get the proxy delay
     * 
     * @return the proxyDelay
     */
    public Integer getProxyDelay() {
        return proxyDelay;
    }

    /**
     * Set the proxy delay
     * 
     * @param proxyDelay
     *            the proxyDelay to set
     */
    public void setProxyDelay(final Integer proxyDelay) {
        this.proxyDelay = proxyDelay;
    }

    /**
     * Get the control mode to use for determining controller action
     * 
     * @return the controlMode
     */
    public ControlMode getControlMode() {
        return controlMode;
    }

    /**
     * Set the control mode to use for determining controller action
     * 
     * @param controlMode
     *            the controlMode to set
     */
    public void setControlMode(final ControlMode controlMode) {
        this.controlMode = controlMode;
    }

    /**
     * Get the base setpoint to base control off of
     * 
     * @return the baseSetpoint
     */
    public Double getBaseSetpoint() {
        return baseSetpoint;
    }

    /**
     * Set the base setpoint to base control off of
     * 
     * @param baseSetpoint
     *            the baseSetpoint to set
     */
    public void setBaseSetpoint(final Double baseSetpoint) {
        this.baseSetpoint = baseSetpoint;
    }

    /**
     * Get the base setpoint to base control off of function
     * if the base setpoint is a function of another property set that here
     * 
     * @return the baseSetpointFn
     */
    public String getBaseSetpointFn() {
        return baseSetpointFn;
    }

    /**
     * Set the base setpoint to base control off of function
     * if the base setpoint is a function of another property set that here
     * 
     * @param baseSetpointFn
     *            the baseSetpointFn to set
     */
    public void setBaseSetpointFn(final String baseSetpointFn) {
        this.baseSetpointFn = baseSetpointFn;
    }

    /**
     * Get the controlled property (e.g., heating setpoint)
     * 
     * @return the setpoint
     */
    public String getSetpoint() {
        return setpoint;
    }

    /**
     * Set the controlled property (e.g., heating setpoint)
     * 
     * @param setpoint
     *            the setpoint to set
     */
    public void setSetpoint(final String setpoint) {
        this.setpoint = setpoint;
    }

    /**
     * Get the observed property (e.g., air temperature)
     * 
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Set the observed property (e.g., air temperature)
     * 
     * @param target
     *            the target to set
     */
    public void setTarget(final String target) {
        this.target = target;
    }

    /**
     * Get the deadband
     * 
     * @return the deadband
     */
    public String getDeadband() {
        return deadband;
    }

    /**
     * Set the deadband
     * 
     * @param deadband
     *            the deadband to set
     */
    public void setDeadband(final String deadband) {
        this.deadband = deadband;
    }

    /**
     * Get if should use predictive bidding
     * 
     * @return the usePredictiveBidding
     */
    public Boolean getUsePredictiveBidding() {
        return usePredictiveBidding;
    }

    /**
     * Set if should use predictive bidding
     * 
     * @param usePredictiveBidding
     *            the usePredictiveBidding to set
     */
    public void setUsePredictiveBidding(final Boolean usePredictiveBidding) {
        this.usePredictiveBidding = usePredictiveBidding;
    }

    /**
     * Get the average target
     * 
     * @return the averageTarget
     */
    public String getAverageTarget() {
        return averageTarget;
    }

    /**
     * Set the average target
     * 
     * @param averageTarget
     *            the averageTarget to set
     */
    public void setAverageTarget(final String averageTarget) {
        this.averageTarget = averageTarget;
    }

    /**
     * Get the standard deviation target
     * 
     * @return the standardDeviationTarget
     */
    public String getStandardDeviationTarget() {
        return standardDeviationTarget;
    }

    /**
     * Set the standard deviation target
     * 
     * @param standardDeviationTarget
     *            the standardDeviationTarget to set
     */
    public void setStandardDeviationTarget(final String standardDeviationTarget) {
        this.standardDeviationTarget = standardDeviationTarget;
    }

    /**
     * Get the interval of time between market clearings (s)
     * 
     * @return the period
     */
    public Double getPeriod() {
        return period;
    }

    /**
     * Set the interval of time between market clearings (s)
     * 
     * @param period
     *            the period to set
     */
    public void setPeriod(final Double period) {
        this.period = period;
    }

    /**
     * Get the the controlled load when on
     * 
     * @return the demand
     */
    public String getDemand() {
        return demand;
    }

    /**
     * Set the the controlled load when on
     * 
     * @param demand
     *            the demand to set
     */
    public void setDemand(final String demand) {
        this.demand = demand;
    }

    /**
     * Get the setpoint limit on the high side
     * 
     * @return the rangeHigh
     */
    public Double getRangeHigh() {
        return rangeHigh;
    }

    /**
     * Set the setpoint limit on the high side
     * 
     * @param rangeHigh
     *            the rangeHigh to set
     */
    public void setRangeHigh(final Double rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    /**
     * Get the setpoint limit on the low side
     * 
     * @return the rangeLow
     */
    public Double getRangeLow() {
        return rangeLow;
    }

    /**
     * Set the setpoint limit on the low side
     * 
     * @param rangeLow
     *            the rangeLow to set
     */
    public void setRangeLow(final Double rangeLow) {
        this.rangeLow = rangeLow;
    }

    /**
     * Get the comfort response above the setpoint (degF)
     * 
     * @return the rampHigh
     */
    public Double getRampHigh() {
        return rampHigh;
    }

    /**
     * Set the comfort response above the setpoint (degF)
     * 
     * @param rampHigh
     *            the rampHigh to set
     */
    public void setRampHigh(final Double rampHigh) {
        this.rampHigh = rampHigh;
    }

    /**
     * Get the comfort response below the setpoint (degF)
     * 
     * @return the rampLow
     */
    public Double getRampLow() {
        return rampLow;
    }

    /**
     * Set the comfort response below the setpoint (degF)
     * 
     * @param rampLow
     *            the rampLow to set
     */
    public void setRampLow(final Double rampLow) {
        this.rampLow = rampLow;
    }

    /**
     * Get the uncontrolled load (if any)
     * 
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * Set the uncontrolled load (if any)
     * 
     * @param total
     *            the total to set
     */
    public void setTotal(final String total) {
        this.total = total;
    }

    /**
     * Get the current controlled load
     * 
     * @return the load
     */
    public String getLoad() {
        return load;
    }

    /**
     * Set the current controlled load
     * 
     * @param load
     *            the load to set
     */
    public void setLoad(final String load) {
        this.load = load;
    }

    /**
     * Get the state property of the controlled load
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state property of the controlled load
     * 
     * @param state
     *            the state to set
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Get the slider setting
     * 
     * @return the sliderSetting
     */
    public Double getSliderSetting() {
        return sliderSetting;
    }

    /**
     * Set the slider setting
     * 
     * @param sliderSetting
     *            the sliderSetting to set
     */
    public void setSliderSetting(Double sliderSetting) {
        this.sliderSetting = sliderSetting;
    }

    /**
     * {@inheritDoc}
     * 
     * Get the controller network interface name
     */
    @Override
    public String getNetworkInterfaceName() {
        return this.networkInterfaceName;
    }

    /**
     * Set the controller network interface name
     * 
     * @param networkInterfaceName
     *            the name to set
     */
    public void setNetworkInterfaceName(final String networkInterfaceName) {
        this.networkInterfaceName = networkInterfaceName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "controller";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "use_override", useOverride);
        writeProperty(sb, "override", override);
        writeProperty(sb, "market", auction);
        writeProperty(sb, "schedule_skew", scheduleSkew);
        writeProperty(sb, "bid_mode", bidMode);
        writeProperty(sb, "proxy_delay", proxyDelay);

        // Controller Network Interface
        sb.append("    object controller_network_interface {\n    ");
        writeProperty(sb, "name", getNetworkInterfaceName());
        sb.append("    ");
        writeProperty(sb, "destination", auction.getNetworkInterfaceName());
        sb.append("    };\n");

        writeProperty(sb, "control_mode", controlMode);
        if (baseSetpointFn != null) {
            writeProperty(sb, "base_setpoint", baseSetpointFn);
        } else {
            writeProperty(sb, "base_setpoint", baseSetpoint);
        }
        writeProperty(sb, "setpoint", setpoint);
        writeProperty(sb, "target", target);
        writeProperty(sb, "deadband", deadband);
        writeProperty(sb, "use_predictive_bidding", usePredictiveBidding);
        writeProperty(sb, "average_target", averageTarget);
        writeProperty(sb, "period", period);
        writeProperty(sb, "demand", demand);
        writeProperty(sb, "range_high", rangeHigh);
        writeProperty(sb, "range_low", rangeLow);
        writeProperty(sb, "ramp_high", rampHigh);
        writeProperty(sb, "ramp_low", rampLow);
        writeProperty(sb, "total", total);
        writeProperty(sb, "load", load);
        writeProperty(sb, "state", state);
        writeProperty(sb, "slider_setting", sliderSetting);
    }

}
