/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.BidMode;
import gov.pnnl.prosser.api.gld.enums.ControlMode;
import gov.pnnl.prosser.api.gld.enums.MarketSetUp;
import gov.pnnl.prosser.api.gld.enums.UseOverride;

/**
 * Market Controller embedded in Houses
 * 
 * @author nord229
 */
public class Controller extends AbstractGldObject {
	/**
	 * Enabling fncs ns3 communication
	 */
	private boolean useFncs;
	
	/**
	 * The auction market to communicate with
	 */
	private AuctionObject auction;
	
	/**
	 * The bid_mode to use
	 */
	private BidMode bidMode;
	
	/**
	 * The bid delay to use
	 */
	private Integer bidDelay;
	
	/**
	 * The control mode to use
	 */
	private ControlMode controlMode;
	
	/**
	 * The base set point if using a schedule
	 */
	private String baseSetpoint;
	
	/**
	 * The set point property inside the house
	 */
	private String setPoint;
	
	/**
	 * The target property inside the house
	 */
	private String target;
	
	/**
	 * the dead band property inside the house
	 */
	private String deadBand;
	
	/**
	 * boolean for using predictive bidding
	 */
	private Boolean usePredictiveBidding;
	
	/**
	 * average property in the market
	 */
	private String averageTarget;
	
	/**
	 * standard deviation property in the market
	 */
	private String standardDeviationTarget;
	
	
	/**
	 * The demand property in the house
	 */
	private String demand;
	
	/**
	 * The range high property
	 */
	private Double rangeHigh;
	
	/**
	 * The range low property
	 */
	private Double rangeLow;
	
	/**
	 * The ramp high property
	 */
	private Double rampHigh;
	
	/**
	 * The ramp low property
	 */
	private Double rampLow;
	
	/**
	 * the total property in the house
	 */
	private String total;
	
	/**
	 * the load property in the house
	 */
	private String load;
	
	/**
	 * The state property in the house
	 */
	private String state;
	
	/**
	 * The schedule skew
	 */
	private Long scheduleSkew;
	
	/**
	 * boolean to send the controller bid function call
	 */
	private boolean sendControllerBid;

	/**
	 * @return the useFncs
	 */
	public boolean isUseFncs() {
		return useFncs;
	}

	/**
	 * @param useFncs the useFncs to set
	 */
	public void setUseFncs(boolean useFncs) {
		this.useFncs = useFncs;
	}

	/**
	 * @return the auction
	 */
	public AuctionObject getAuction() {
		return auction;
	}

	/**
	 * @param auction the auction to set
	 */
	public void setAuction(AuctionObject auction) {
		this.auction = auction;
	}

	/**
	 * @return the bidMode
	 */
	public BidMode getBidMode() {
		return bidMode;
	}

	/**
	 * @param bidMode the bidMode to set
	 */
	public void setBidMode(BidMode bidMode) {
		this.bidMode = bidMode;
	}

	/**
	 * @return the bidDelay
	 */
	public Integer getBidDelay() {
		return bidDelay;
	}

	/**
	 * @param bidDelay the bidDelay to set
	 */
	public void setBidDelay(Integer bidDelay) {
		this.bidDelay = bidDelay;
	}

	/**
	 * @return the controlMode
	 */
	public ControlMode getControlMode() {
		return controlMode;
	}

	/**
	 * @param controlMode the controlMode to set
	 */
	public void setControlMode(ControlMode controlMode) {
		this.controlMode = controlMode;
	}

	/**
	 * @return the baseSetpoint
	 */
	public String getBaseSetpoint() {
		return baseSetpoint;
	}

	/**
	 * @param baseSetpoint the baseSetpoint to set
	 */
	public void setBaseSetpoint(String baseSetpoint) {
		this.baseSetpoint = baseSetpoint;
	}

	/**
	 * @return the setPoint
	 */
	public String getSetPoint() {
		return setPoint;
	}

	/**
	 * @param setPoint the setPoint to set
	 */
	public void setSetPoint(String setPoint) {
		this.setPoint = setPoint;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the deadBand
	 */
	public String getDeadBand() {
		return deadBand;
	}

	/**
	 * @param deadBand the deadBand to set
	 */
	public void setDeadBand(String deadBand) {
		this.deadBand = deadBand;
	}

	/**
	 * @return the usePredictiveBidding
	 */
	public Boolean getUsePredictiveBidding() {
		return usePredictiveBidding;
	}

	/**
	 * @param usePredictiveBidding the usePredictiveBidding to set
	 */
	public void setUsePredictiveBidding(Boolean usePredictiveBidding) {
		this.usePredictiveBidding = usePredictiveBidding;
	}

	/**
	 * @return the averageTarget
	 */
	public String getAverageTarget() {
		return averageTarget;
	}

	/**
	 * @param averageTarget the averageTarget to set
	 */
	public void setAverageTarget(String averageTarget) {
		this.averageTarget = averageTarget;
	}

	/**
	 * @return the standardDeviationTarget
	 */
	public String getStandardDeviationTarget() {
		return standardDeviationTarget;
	}

	/**
	 * @param standardDeviationTarget the standardDeviationTarget to set
	 */
	public void setStandardDeviationTarget(String standardDeviationTarget) {
		this.standardDeviationTarget = standardDeviationTarget;
	}

	/**
	 * @return the demand
	 */
	public String getDemand() {
		return demand;
	}

	/**
	 * @param demand the demand to set
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}

	/**
	 * @return the rangeHigh
	 */
	public Double getRangeHigh() {
		return rangeHigh;
	}

	/**
	 * @param rangeHigh the rangeHigh to set
	 */
	public void setRangeHigh(Double rangeHigh) {
		this.rangeHigh = rangeHigh;
	}

	/**
	 * @return the rangeLow
	 */
	public Double getRangeLow() {
		return rangeLow;
	}

	/**
	 * @param rangeLow the rangeLow to set
	 */
	public void setRangeLow(Double rangeLow) {
		this.rangeLow = rangeLow;
	}

	/**
	 * @return the rampHigh
	 */
	public Double getRampHigh() {
		return rampHigh;
	}

	/**
	 * @param rampHigh the rampHigh to set
	 */
	public void setRampHigh(Double rampHigh) {
		this.rampHigh = rampHigh;
	}

	/**
	 * @return the rampLow
	 */
	public Double getRampLow() {
		return rampLow;
	}

	/**
	 * @param rampLow the rampLow to set
	 */
	public void setRampLow(Double rampLow) {
		this.rampLow = rampLow;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the load
	 */
	public String getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(String load) {
		this.load = load;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	public Controller(final GldSimulator simulator) {
		super(simulator);
		simulator.ensureMarketModule();
	}
	
	

	/**
	 * @return the scheduleSkew
	 */
	public Long getScheduleSkew() {
		return scheduleSkew;
	}

	/**
	 * @param scheduleSkew the scheduleSkew to set
	 */
	public void setScheduleSkew(Long scheduleSkew) {
		this.scheduleSkew = scheduleSkew;
	}

	/**
	 * @param sendControllerBid the sendControllerBid to set
	 */
	public void setSendControllerBid(boolean sendControllerBid) {
		this.sendControllerBid = sendControllerBid;
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
	 */
	@Override
	protected String getGldObjectType() {
		return "controller_ccsi";
	}

	/* (non-Javadoc)
	 * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
	 */
	@Override
	protected void writeGldProperties(StringBuilder sb) {
		writeProperty(sb, "market", auction.getName());
		writeProperty(sb, "period", auction.getPeriod());
		if(useFncs){
			writeProperty(sb, "bid_mode", bidMode);
			writeProperty(sb, "proxy_average", auction.getInitPrice());
			writeProperty(sb, "proxy_standard_deviation", auction.getInitStdev());
			writeProperty(sb, "proxy_market_id", "1");
			writeProperty(sb, "proxy_clear_price", auction.getInitPrice());
			writeProperty(sb, "proxy_initial_price", auction.getInitPrice());
			writeProperty(sb, "proxy_price_cap", auction.getPriceCap());
			writeProperty(sb, "proxy_market_unit", auction.getUnit());
		}
		writeProperty(sb, "control_mode", controlMode);
		writeProperty(sb, "bid_delay", bidDelay);
		writeProperty(sb, "base_setpoint", baseSetpoint);
		writeProperty(sb, "setpoint", setPoint);
		writeProperty(sb, "target", target);
		writeProperty(sb, "deadband", deadBand);
		writeProperty(sb, "target", target);
		writeProperty(sb, "average_target", averageTarget);
		writeProperty(sb, "standard_deviation_target", standardDeviationTarget);
		writeProperty(sb, "demand", demand);
		writeProperty(sb, "total", total);
		writeProperty(sb, "load", load);
		writeProperty(sb, "state", state);
		writeProperty(sb, "use_predictive_bidding", usePredictiveBidding);
		writeProperty(sb, "range_high", rangeHigh);
		writeProperty(sb, "range_low", rangeLow);
		writeProperty(sb, "ramp_high", rampHigh);
		writeProperty(sb, "ramp_low", rampLow);
	}
	
	public void writeFncs2Directives(StringBuilder sb) {
	    String ns3SimName = this.simulator.getNs3Sim() == null ? "" : this.simulator.getNs3Sim().getName();
	    writeRoutePresync(sb, auction.getName(), "current_market.clearing_price", this.getName(), "clearPrice");
	    writeRoutePresync(sb, auction.getName(), "market_id", this.getName(), "mktID");
	    writeRoutePresync(sb, auction.getName(), auction.getNetworkAveragePriceProperty(), this.getName(), "avgPrice");
	    writeRoutePresync(sb, auction.getName(), auction.getNetworkStdevPriceProperty(), this.getName(), "stdevPrice");
	    if(this.auction.getMarketSetUp().equals(MarketSetUp.NORMAL)){
	    	writeSubmitBidState(sb, ns3SimName, this.simulator.getName(), this.getName(), auction.getName());
	    }
	    writeSubscribePresync(sb, this.getName(), "proxy_clear_price", ns3SimName, this.simulator.getName(), auction.getName(),"clearPrice");
	    writeSubscribePresync(sb, this.getName(), "proxy_market_id", ns3SimName, this.simulator.getName(), auction.getName(), "mktID");
	    writeSubscribePresync(sb, this.getName(), "proxy_average", ns3SimName, this.simulator.getName(), auction.getName(), "avgPrice");
	    writeSubscribePresync(sb, this.getName(), "proxy_standard_deviation", ns3SimName, this.simulator.getName(), auction.getName(),"stdevPrice");
	    if(this.auction.getMarketSetUp().equals(MarketSetUp.AGGREGATE)){
	    	writeRouteCommit(sb, this.getName(), "bid_price", this.auction.getName(), "bid_price");
	    	writeRouteCommit(sb, this.getName(), "bid_quantity", this.auction.getName(), "bid_quantity");
	    	writeRouteCommit(sb, this.getName(), "parent_unresponsive_load", this.auction.getName(), "parent_responsive_load");
	    }
	}
	
	private void writeRoutePresync(StringBuilder sb, String auctionName, String auctionProperty, String controllerName, String controllerProperty) {
	    sb.append("route \"presync:");
	    sb.append(auctionName);
	    sb.append('.');
	    sb.append(auctionProperty);
	    sb.append(" -> ");
	    sb.append(controllerName);
	    sb.append('/');
	    sb.append(controllerProperty);
	    sb.append("; 0\";\n");
	}
	
	private void writeSubmitBidState(StringBuilder sb, String ns3SimName, String gldSimName, String controllerName, String auctionName) {
        sb.append("subscribe \"function:auction/submit_bid_state <- ");
        sb.append(ns3SimName);
        sb.append('/');
        sb.append(gldSimName);
        sb.append('/');
        sb.append(controllerName);
        sb.append('@');
        sb.append(auctionName);
        sb.append("/submit_bid_state\";\n");
    }
	
	private void writeSubscribePresync(StringBuilder sb, String controllerName, String proxyProperty, String ns3SimName, String gldSimName, String auctionName, String controllerProperty) {
        sb.append("subscribe \"precommit:");
        sb.append(controllerName);
        sb.append('.');
        sb.append(proxyProperty);
        sb.append(" <- ");
        sb.append(ns3SimName);
        sb.append('/');
        sb.append(gldSimName);
        sb.append('/');
        sb.append(auctionName);
        sb.append('@');
        sb.append(controllerName);
        sb.append('/');
        sb.append(controllerProperty);
        sb.append("\";\n");
    }
	
	private void writeRouteCommit(StringBuilder sb, String controllerName, String controllerProperty, String toName, String key){
		sb.append("route \"commit:");
		sb.append(controllerName);
		sb.append('.');
		sb.append(controllerProperty);
		sb.append(" -> ");
		sb.append(toName);
		sb.append('/');
		sb.append(key);
		sb.append("; 0\";\n");
	}
}
