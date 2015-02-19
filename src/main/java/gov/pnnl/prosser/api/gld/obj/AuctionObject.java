/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.NetworkCapable;
import gov.pnnl.prosser.api.gld.enums.CurveOutput;
import gov.pnnl.prosser.api.gld.enums.SpecialMode;

/**
 * @author nord229
 *
 */
public class AuctionObject extends AbstractProsserObject implements NetworkCapable {

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

    private String transactionLogFile;

    private String curveLogFile;

    private CurveOutput curveLogInfo;

    private PlayerObject player;

    private SpecialMode specialMode;

    private Recorder recorder;

    private Double initPrice;

    private Double initStdev;

    private Boolean useFutureMeanPrice;

    private Integer warmup;

    private String networkAveragePriceProperty;

    private String networkStdevPriceProperty;

    private String networkAdjustPriceProperty;

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     *            the unit to set
     */
    public void setUnit(final String unit) {
        this.unit = unit;
    }

    /**
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(final Integer period) {
        this.period = period;
    }

    /**
     * @return the priceCap
     */
    public Double getPriceCap() {
        return priceCap;
    }

    /**
     * @param priceCap
     *            the priceCap to set
     */
    public void setPriceCap(final Double priceCap) {
        this.priceCap = priceCap;
    }

    /**
     * @return the transactionLogFile
     */
    public String getTransactionLogFile() {
        return transactionLogFile;
    }

    /**
     * @param transactionLogFile
     *            the transactionLogFile to set
     */
    public void setTransactionLogFile(final String transactionLogFile) {
        this.transactionLogFile = transactionLogFile;
    }

    /**
     * @return the curveLogFile
     */
    public String getCurveLogFile() {
        return curveLogFile;
    }

    /**
     * @param curveLogFile
     *            the curveLogFile to set
     */
    public void setCurveLogFile(final String curveLogFile) {
        this.curveLogFile = curveLogFile;
    }

    /**
     * @return the curveLogInfo
     */
    public CurveOutput getCurveLogInfo() {
        return curveLogInfo;
    }

    /**
     * @param curveLogInfo
     *            the curveLogInfo to set
     */
    public void setCurveLogInfo(final CurveOutput curveLogInfo) {
        this.curveLogInfo = curveLogInfo;
    }

    /**
     * @return the player
     */
    public PlayerObject getPlayer() {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    public void setPlayer(final PlayerObject player) {
        this.player = player;
    }

    /**
     * @return the specialMode
     */
    public SpecialMode getSpecialMode() {
        return specialMode;
    }

    /**
     * @param specialMode
     *            the specialMode to set
     */
    public void setSpecialMode(final SpecialMode specialMode) {
        this.specialMode = specialMode;
    }

    /**
     * @return the recorder
     */
    public Recorder getRecorder() {
        return recorder;
    }

    /**
     * @param recorder
     *            the recorder to set
     */
    public void setRecorder(final Recorder recorder) {
        this.recorder = recorder;
    }

    /**
     * @return the initPrice
     */
    public Double getInitPrice() {
        return initPrice;
    }

    /**
     * @param initPrice
     *            the initPrice to set
     */
    public void setInitPrice(final Double initPrice) {
        this.initPrice = initPrice;
    }

    /**
     * @return the initStdev
     */
    public Double getInitStdev() {
        return initStdev;
    }

    /**
     * @param initStdev
     *            the initStdev to set
     */
    public void setInitStdev(final Double initStdev) {
        this.initStdev = initStdev;
    }

    /**
     * @return the useFutureMeanPrice
     */
    public Boolean getUseFutureMeanPrice() {
        return useFutureMeanPrice;
    }

    /**
     * @param useFutureMeanPrice
     *            the useFutureMeanPrice to set
     */
    public void setUseFutureMeanPrice(final Boolean useFutureMeanPrice) {
        this.useFutureMeanPrice = useFutureMeanPrice;
    }

    /**
     * @return the warmup
     */
    public Integer getWarmup() {
        return warmup;
    }

    /**
     * @param warmup
     *            the warmup to set
     */
    public void setWarmup(final Integer warmup) {
        this.warmup = warmup;
    }

    /**
     * @return the networkAveragePriceProperty
     */
    public String getNetworkAveragePriceProperty() {
        return networkAveragePriceProperty;
    }

    /**
     * @param networkAveragePriceProperty
     *            the networkAveragePriceProperty to set
     */
    public void setNetworkAveragePriceProperty(final String networkAveragePriceProperty) {
        this.networkAveragePriceProperty = networkAveragePriceProperty;
    }

    /**
     * @return the networkStdevPriceProperty
     */
    public String getNetworkStdevPriceProperty() {
        return networkStdevPriceProperty;
    }

    /**
     * @param networkStdevPriceProperty
     *            the networkStdevPriceProperty to set
     */
    public void setNetworkStdevPriceProperty(final String networkStdevPriceProperty) {
        this.networkStdevPriceProperty = networkStdevPriceProperty;
    }

    /**
     * @return the networkAdjustPriceProperty
     */
    public String getNetworkAdjustPriceProperty() {
        return networkAdjustPriceProperty;
    }

    /**
     * @param networkAdjustPriceProperty
     *            the networkAdjustPriceProperty to set
     */
    public void setNetworkAdjustPriceProperty(final String networkAdjustPriceProperty) {
        this.networkAdjustPriceProperty = networkAdjustPriceProperty;
    }

    @Override
    public String getNetworkInterfaceName() {
        return this.getName() + "NI";
    }

    @Override
    public String getNetwork() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getGldObjectType() {
        return "auction";
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "unit", unit);
        GldUtils.writeProperty(sb, "period", period);
        GldUtils.writeProperty(sb, "price_cap", priceCap);
        GldUtils.writeProperty(sb, "transaction_log_file", transactionLogFile);
        GldUtils.writeProperty(sb, "curve_log_file", curveLogFile);
        GldUtils.writeProperty(sb, "curve_log_info", curveLogInfo);

        // Market Network Interface
        sb.append("    object market_network_interface {\n    ");
        GldUtils.writeProperty(sb, "name", getNetworkInterfaceName());
        sb.append("    ");
        GldUtils.writeProperty(sb, "average_price_prop", networkAveragePriceProperty);
        sb.append("    ");
        GldUtils.writeProperty(sb, "stdev_price_prop", networkStdevPriceProperty);
        sb.append("    ");
        GldUtils.writeProperty(sb, "adjust_price_prop", networkAdjustPriceProperty);
        sb.append("    };\n");

        // FIXME INSERT PLAYER
        GldUtils.writeProperty(sb, "special_mode", specialMode);
        // FIXME INSERT RECORDER
        GldUtils.writeProperty(sb, "init_price", initPrice);
        GldUtils.writeProperty(sb, "init_stdev", initStdev);
        GldUtils.writeProperty(sb, "use_future_mean_price", useFutureMeanPrice);
        GldUtils.writeProperty(sb, "warmup", warmup);
    }

}
