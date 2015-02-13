/**
 *
 */
package gov.pnnl.prosser.api.pwr.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.pwr.enums.BidMode;
import gov.pnnl.prosser.api.pwr.enums.ControlMode;
import gov.pnnl.prosser.api.pwr.enums.UseOverride;

/**
 * @author nord229
 *
 */
public class Controller extends AbstractProsserObject implements NetworkCapable {

    private UseOverride useOverride;

    private String override;

    private AuctionObject auction;

    private Long scheduleSkew;

    private BidMode bidMode;

    private Integer proxyDelay;

    private ControlMode controlMode;

    private Double baseSetpoint;

    private String baseSetpointFn;

    private String setpoint;

    private String target;

    private String deadband;

    private Boolean usePredictiveBidding;

    private String averageTarget;

    private String standardDeviationTarget;

    private Double period;

    private String demand;

    private Double rangeHigh;

    private Double rangeLow;

    private Double rampHigh;

    private Double rampLow;

    private String total;

    private String load;

    private String state;

    private String namePrefix;

    private Integer id;

    /**
     * @return the useOverride
     */
    public UseOverride getUseOverride() {
        return useOverride;
    }

    /**
     * @param useOverride
     *            the useOverride to set
     */
    public void setUseOverride(final UseOverride useOverride) {
        this.useOverride = useOverride;
    }

    /**
     * @return the override
     */
    public String getOverride() {
        return override;
    }

    /**
     * @param override
     *            the override to set
     */
    public void setOverride(final String override) {
        this.override = override;
    }

    /**
     * @return the auction
     */
    public AuctionObject getAuction() {
        return auction;
    }

    /**
     * @param auction
     *            the auction to set
     */
    public void setAuction(final AuctionObject auction) {
        this.auction = auction;
    }

    /**
     * @return the scheduleSkew
     */
    public Long getScheduleSkew() {
        return scheduleSkew;
    }

    /**
     * @param scheduleSkew
     *            the scheduleSkew to set
     */
    public void setScheduleSkew(final Long scheduleSkew) {
        this.scheduleSkew = scheduleSkew;
    }

    /**
     * @return the bidMode
     */
    public BidMode getBidMode() {
        return bidMode;
    }

    /**
     * @param bidMode
     *            the bidMode to set
     */
    public void setBidMode(final BidMode bidMode) {
        this.bidMode = bidMode;
    }

    /**
     * @return the proxyDelay
     */
    public Integer getProxyDelay() {
        return proxyDelay;
    }

    /**
     * @param proxyDelay
     *            the proxyDelay to set
     */
    public void setProxyDelay(final Integer proxyDelay) {
        this.proxyDelay = proxyDelay;
    }

    /**
     * @return the controlMode
     */
    public ControlMode getControlMode() {
        return controlMode;
    }

    /**
     * @param controlMode
     *            the controlMode to set
     */
    public void setControlMode(final ControlMode controlMode) {
        this.controlMode = controlMode;
    }

    /**
     * @return the baseSetpoint
     */
    public Double getBaseSetpoint() {
        return baseSetpoint;
    }

    /**
     * @param baseSetpoint
     *            the baseSetpoint to set
     */
    public void setBaseSetpoint(final Double baseSetpoint) {
        this.baseSetpoint = baseSetpoint;
    }

    /**
     * @return the baseSetpointFn
     */
    public String getBaseSetpointFn() {
        return baseSetpointFn;
    }

    /**
     * @param baseSetpointFn
     *            the baseSetpointFn to set
     */
    public void setBaseSetpointFn(final String baseSetpointFn) {
        this.baseSetpointFn = baseSetpointFn;
    }

    /**
     * @return the setpoint
     */
    public String getSetpoint() {
        return setpoint;
    }

    /**
     * @param setpoint
     *            the setpoint to set
     */
    public void setSetpoint(final String setpoint) {
        this.setpoint = setpoint;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(final String target) {
        this.target = target;
    }

    /**
     * @return the deadband
     */
    public String getDeadband() {
        return deadband;
    }

    /**
     * @param deadband
     *            the deadband to set
     */
    public void setDeadband(final String deadband) {
        this.deadband = deadband;
    }

    /**
     * @return the usePredictiveBidding
     */
    public Boolean getUsePredictiveBidding() {
        return usePredictiveBidding;
    }

    /**
     * @param usePredictiveBidding
     *            the usePredictiveBidding to set
     */
    public void setUsePredictiveBidding(final Boolean usePredictiveBidding) {
        this.usePredictiveBidding = usePredictiveBidding;
    }

    /**
     * @return the averageTarget
     */
    public String getAverageTarget() {
        return averageTarget;
    }

    /**
     * @param averageTarget
     *            the averageTarget to set
     */
    public void setAverageTarget(final String averageTarget) {
        this.averageTarget = averageTarget;
    }

    /**
     * @return the standardDeviationTarget
     */
    public String getStandardDeviationTarget() {
        return standardDeviationTarget;
    }

    /**
     * @param standardDeviationTarget
     *            the standardDeviationTarget to set
     */
    public void setStandardDeviationTarget(final String standardDeviationTarget) {
        this.standardDeviationTarget = standardDeviationTarget;
    }

    /**
     * @return the period
     */
    public Double getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(final Double period) {
        this.period = period;
    }

    /**
     * @return the demand
     */
    public String getDemand() {
        return demand;
    }

    /**
     * @param demand
     *            the demand to set
     */
    public void setDemand(final String demand) {
        this.demand = demand;
    }

    /**
     * @return the rangeHigh
     */
    public Double getRangeHigh() {
        return rangeHigh;
    }

    /**
     * @param rangeHigh
     *            the rangeHigh to set
     */
    public void setRangeHigh(final Double rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    /**
     * @return the rangeLow
     */
    public Double getRangeLow() {
        return rangeLow;
    }

    /**
     * @param rangeLow
     *            the rangeLow to set
     */
    public void setRangeLow(final Double rangeLow) {
        this.rangeLow = rangeLow;
    }

    /**
     * @return the rampHigh
     */
    public Double getRampHigh() {
        return rampHigh;
    }

    /**
     * @param rampHigh
     *            the rampHigh to set
     */
    public void setRampHigh(final Double rampHigh) {
        this.rampHigh = rampHigh;
    }

    /**
     * @return the rampLow
     */
    public Double getRampLow() {
        return rampLow;
    }

    /**
     * @param rampLow
     *            the rampLow to set
     */
    public void setRampLow(final Double rampLow) {
        this.rampLow = rampLow;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(final String total) {
        this.total = total;
    }

    /**
     * @return the load
     */
    public String getLoad() {
        return load;
    }

    /**
     * @param load
     *            the load to set
     */
    public void setLoad(final String load) {
        this.load = load;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * @return the namePrefix
     */
    public String getNamePrefix() {
        return namePrefix;
    }

    /**
     * @param namePrefix
     *            the namePrefix to set
     */
    public void setNamePrefix(final String namePrefix) {
        this.namePrefix = namePrefix;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public String getNetworkInterfaceName() {
        if (namePrefix != null) {
            return this.namePrefix + "NI" + id;
        } else {
            return this.getName() + "NI";
        }
    }

    @Override
    public String getNetwork() {
        return null;
    }

    @Override
    public String getGldObjectType() {
        return "controller";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "use_override", useOverride);
        GldUtils.writeProperty(sb, "override", override);
        GldUtils.writeProperty(sb, "market", auction);
        GldUtils.writeProperty(sb, "schedule_skew", scheduleSkew);
        GldUtils.writeProperty(sb, "bid_mode", bidMode);
        GldUtils.writeProperty(sb, "proxy_delay", proxyDelay);

        // Controller Network Interface
        sb.append("    object controller_network_interface {\n    ");
        GldUtils.writeProperty(sb, "name", getNetworkInterfaceName());
        sb.append("    ");
        GldUtils.writeProperty(sb, "destination", auction.getNetworkInterfaceName());
        sb.append("    };\n");

        GldUtils.writeProperty(sb, "control_mode", controlMode);
        if (baseSetpointFn != null) {
            GldUtils.writeProperty(sb, "base_setpoint", baseSetpointFn);
        } else {
            GldUtils.writeProperty(sb, "base_setpoint", baseSetpoint);
        }
        GldUtils.writeProperty(sb, "setpoint", setpoint);
        GldUtils.writeProperty(sb, "target", target);
        GldUtils.writeProperty(sb, "deadband", deadband);
        GldUtils.writeProperty(sb, "use_predictive_bidding", usePredictiveBidding);
        GldUtils.writeProperty(sb, "average_target", averageTarget);
        GldUtils.writeProperty(sb, "period", period);
        GldUtils.writeProperty(sb, "demand", demand);
        GldUtils.writeProperty(sb, "range_high", rangeHigh);
        GldUtils.writeProperty(sb, "range_low", rangeLow);
        GldUtils.writeProperty(sb, "ramp_high", rampHigh);
        GldUtils.writeProperty(sb, "ramp_low", rampLow);
        GldUtils.writeProperty(sb, "total", total);
        GldUtils.writeProperty(sb, "load", load);
        GldUtils.writeProperty(sb, "state", state);
    }

}
