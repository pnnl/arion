/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.CurveOutput;
import gov.pnnl.prosser.api.gld.enums.SpecialMode;

/**
 * The auction object implements the basic auction
 * 
 * @author nord229
 */
public class AuctionObject extends AbstractGldObject implements NetworkCapable {

    /**
     * unit of quantity
     */
    private String unit;

    /**
     * time period of auction closing (s)
     */
    private Integer period;

    /**
     * maximum price allowed
     */
    private Double priceCap;

    /**
     * transaction log file
     */
    private String transactionLogFile;

    /**
     * curve log file
     */
    private String curveLogFile;

    /**
     * output mode for curve log
     */
    private CurveOutput curveLogInfo;

    /**
     * attached player for the Auction
     */
    private PlayerObject player;

    /**
     * auction special mode
     */
    private SpecialMode specialMode;

    /**
     * attached recorder for the Auction
     */
    private Recorder recorder;

    /**
     * initial price
     */
    private Double initPrice;

    /**
     * initial standard deviation
     */
    private Double initStdev;

    /**
     * should use the future mean price
     */
    private Boolean useFutureMeanPrice;

    /**
     * warmup time
     */
    private Integer warmup;

    /**
     * market network interface average price property
     */
    private String networkAveragePriceProperty;

    /**
     * market network interface standard deviation price property
     */
    private String networkStdevPriceProperty;

    /**
     * market network interface adjust price property
     */
    private String networkAdjustPriceProperty;

    /**
     * controller prefix for FNCS
     * only controllers with this prefix will talk with this auction
     */
    private String fncsControllerPrefix;

    public AuctionObject(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureMarketModule();
        simulator.ensureCommModule();
    }

    /**
     * Get the unit of quantity
     * 
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Set the unit of quantity
     * 
     * @param unit
     *            the unit to set
     */
    public void setUnit(final String unit) {
        this.unit = unit;
    }

    /**
     * Get the time period of auction closing (s)
     * 
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * Set the time period of auction closing (s)
     * 
     * @param period
     *            the period to set
     */
    public void setPeriod(final Integer period) {
        this.period = period;
    }

    /**
     * Get the maximum price allowed
     * 
     * @return the priceCap
     */
    public Double getPriceCap() {
        return priceCap;
    }

    /**
     * Set the maximum price allowed
     * 
     * @param priceCap
     *            the priceCap to set
     */
    public void setPriceCap(final Double priceCap) {
        this.priceCap = priceCap;
    }

    /**
     * Get the transaction log file
     * 
     * @return the transactionLogFile
     */
    public String getTransactionLogFile() {
        return transactionLogFile;
    }

    /**
     * Set the transaction log file
     * 
     * @param transactionLogFile
     *            the transactionLogFile to set
     */
    public void setTransactionLogFile(final String transactionLogFile) {
        this.transactionLogFile = transactionLogFile;
    }

    /**
     * Get the curve log file
     * 
     * @return the curveLogFile
     */
    public String getCurveLogFile() {
        return curveLogFile;
    }

    /**
     * Set the curve log file
     * 
     * @param curveLogFile
     *            the curveLogFile to set
     */
    public void setCurveLogFile(final String curveLogFile) {
        this.curveLogFile = curveLogFile;
    }

    /**
     * Get the output mode for curve log
     * 
     * @return the curveLogInfo
     */
    public CurveOutput getCurveLogInfo() {
        return curveLogInfo;
    }

    /**
     * Set the output mode for curve log
     * 
     * @param curveLogInfo
     *            the curveLogInfo to set
     */
    public void setCurveLogInfo(final CurveOutput curveLogInfo) {
        this.curveLogInfo = curveLogInfo;
    }

    /**
     * Get the attached player for the Auction
     * 
     * @return the player
     */
    public PlayerObject getPlayer() {
        return player;
    }

    /**
     * Get the auction special mode
     * 
     * @return the specialMode
     */
    public SpecialMode getSpecialMode() {
        return specialMode;
    }

    /**
     * Set the auction special mode
     * 
     * @param specialMode
     *            the specialMode to set
     */
    public void setSpecialMode(final SpecialMode specialMode) {
        this.specialMode = specialMode;
    }

    /**
     * Get the attached recorder for the Auction
     * 
     * @return the recorder
     */
    public Recorder getRecorder() {
        return recorder;
    }

    /**
     * Get the initial price
     * 
     * @return the initPrice
     */
    public Double getInitPrice() {
        return initPrice;
    }

    /**
     * Set the initial price
     * 
     * @param initPrice
     *            the initPrice to set
     */
    public void setInitPrice(final Double initPrice) {
        this.initPrice = initPrice;
    }

    /**
     * Get the initial standard deviation
     * 
     * @return the initStdev
     */
    public Double getInitStdev() {
        return initStdev;
    }

    /**
     * Set the initial standard deviation
     * 
     * @param initStdev
     *            the initStdev to set
     */
    public void setInitStdev(final Double initStdev) {
        this.initStdev = initStdev;
    }

    /**
     * Get if should use the future mean price
     * 
     * @return the useFutureMeanPrice
     */
    public Boolean getUseFutureMeanPrice() {
        return useFutureMeanPrice;
    }

    /**
     * Set if should use the future mean price
     * 
     * @param useFutureMeanPrice
     *            the useFutureMeanPrice to set
     */
    public void setUseFutureMeanPrice(final Boolean useFutureMeanPrice) {
        this.useFutureMeanPrice = useFutureMeanPrice;
    }

    /**
     * Get the warmup time
     * 
     * @return the warmup
     */
    public Integer getWarmup() {
        return warmup;
    }

    /**
     * Set the warmup time
     * 
     * @param warmup
     *            the warmup to set
     */
    public void setWarmup(final Integer warmup) {
        this.warmup = warmup;
    }

    /**
     * Get the market network interface average price property
     * 
     * @return the networkAveragePriceProperty
     */
    public String getNetworkAveragePriceProperty() {
        return networkAveragePriceProperty;
    }

    /**
     * Set the market network interface average price property
     * 
     * @param networkAveragePriceProperty
     *            the networkAveragePriceProperty to set
     */
    public void setNetworkAveragePriceProperty(final String networkAveragePriceProperty) {
        this.networkAveragePriceProperty = networkAveragePriceProperty;
    }

    /**
     * Get the market network interface standard deviation price property
     * 
     * @return the networkStdevPriceProperty
     */
    public String getNetworkStdevPriceProperty() {
        return networkStdevPriceProperty;
    }

    /**
     * Set the market network interface standard deviation price property
     * 
     * @param networkStdevPriceProperty
     *            the networkStdevPriceProperty to set
     */
    public void setNetworkStdevPriceProperty(final String networkStdevPriceProperty) {
        this.networkStdevPriceProperty = networkStdevPriceProperty;
    }

    /**
     * Get the market network interface adjust price property
     * 
     * @return the networkAdjustPriceProperty
     */
    public String getNetworkAdjustPriceProperty() {
        return networkAdjustPriceProperty;
    }

    /**
     * Set the market network interface adjust price property
     * 
     * @param networkAdjustPriceProperty
     *            the networkAdjustPriceProperty to set
     */
    public void setNetworkAdjustPriceProperty(final String networkAdjustPriceProperty) {
        this.networkAdjustPriceProperty = networkAdjustPriceProperty;
    }

    /**
     * Get the controller prefix for FNCS
     * only controllers with this prefix will talk with this auction
     * 
     * @return the fncsControllerPrefix
     */
    public String getFncsControllerPrefix() {
        return fncsControllerPrefix;
    }

    /**
     * Set the controller prefix for FNCS
     * only controllers with this prefix will talk with this auction
     * 
     * @param fncsControllerPrefix
     *            the fncsControllerPrefix to set
     */
    public void setFncsControllerPrefix(String fncsControllerPrefix) {
        this.fncsControllerPrefix = fncsControllerPrefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNetworkInterfaceName() {
        return this.getName() + "NI";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "auction";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "unit", unit);
        writeProperty(sb, "period", period);
        writeProperty(sb, "price_cap", priceCap);
        writeProperty(sb, "transaction_log_file", transactionLogFile);
        writeProperty(sb, "curve_log_file", curveLogFile);
        writeProperty(sb, "curve_log_info", curveLogInfo);

        // Market Network Interface
        sb.append("    object market_network_interface {\n    ");
        writeProperty(sb, "name", getNetworkInterfaceName());
        sb.append("    ");
        writeProperty(sb, "average_price_prop", networkAveragePriceProperty);
        sb.append("    ");
        writeProperty(sb, "stdev_price_prop", networkStdevPriceProperty);
        sb.append("    ");
        writeProperty(sb, "adjust_price_prop", networkAdjustPriceProperty);
        sb.append("    };\n");

        // FIXME INSERT PLAYER
        writeProperty(sb, "special_mode", specialMode);
        // FIXME INSERT RECORDER
        writeProperty(sb, "init_price", initPrice);
        writeProperty(sb, "init_stdev", initStdev);
        writeProperty(sb, "use_future_mean_price", useFutureMeanPrice);
        writeProperty(sb, "warmup", warmup);
    }

    /**
     * Create, set and return a player object
     * 
     * @return the player object
     */
    public PlayerObject player() {
        this.player = new PlayerObject(this.simulator);
        return player;
    }

    /**
     * Create, set and return a recorder object
     * 
     * @return the recorder
     */
    public Recorder recorder() {
        this.recorder = new Recorder(this.simulator);
        return recorder;
    }

}
