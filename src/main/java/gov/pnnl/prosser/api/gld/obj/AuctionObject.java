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
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.CurveOutput;
import gov.pnnl.prosser.api.gld.enums.MarketSetUp;
import gov.pnnl.prosser.api.gld.enums.SpecialMode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

/**
 * The auction object implements the basic auction
 *
 * @author nord229
 */
public class AuctionObject extends AbstractGldObject {

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
//    private String networkAdjustPriceProperty;

    /**
     * controller prefix for FNCS
     * only controllers with this prefix will talk with this auction
     */
    private String fncsControllerPrefix;

    /**
     * market set up type
     * Set this to the particular setup market interaction desired
     */
    private MarketSetUp marketSetUp;

    public AuctionObject(final GldSimulator simulator) {
        super(simulator);
        this.marketSetUp = MarketSetUp.NORMAL;
        simulator.ensureMarketModule();
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
	 * @return the marketSetUp
	 */
	public MarketSetUp getMarketSetUp() {
		return marketSetUp;
	}

	/**
	 * @param marketSetUp the marketSetUp to set
	 */
	public void setMarketSetUp(MarketSetUp marketSetUp) {
		this.marketSetUp = marketSetUp;
	}

	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unit, period, priceCap, transactionLogFile, curveLogFile, curveLogInfo,
                player, specialMode, initPrice, initStdev, useFutureMeanPrice, warmup, networkAveragePriceProperty,
                networkStdevPriceProperty, fncsControllerPrefix, marketSetUp);
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
        final AuctionObject other = (AuctionObject) obj;
        return Objects.equals(this.unit, other.unit)
                && Objects.equals(this.period, other.period)
                && Objects.equals(this.priceCap, other.priceCap)
                && Objects.equals(this.transactionLogFile, other.transactionLogFile)
                && Objects.equals(this.curveLogFile, other.curveLogFile)
                && Objects.equals(this.curveLogInfo, other.curveLogInfo)
                && Objects.equals(this.player, other.player)
                && Objects.equals(this.specialMode, other.specialMode)
                && Objects.equals(this.initPrice, other.initPrice)
                && Objects.equals(this.initStdev, other.initStdev)
                && Objects.equals(this.useFutureMeanPrice, other.useFutureMeanPrice)
                && Objects.equals(this.warmup, other.warmup)
                && Objects.equals(this.networkAveragePriceProperty, other.networkAveragePriceProperty)
                && Objects.equals(this.networkStdevPriceProperty, other.networkStdevPriceProperty)
                && Objects.equals(this.fncsControllerPrefix, other.fncsControllerPrefix)
                && Objects.equals(this.marketSetUp, other.marketSetUp);
    }

    /**
     * Set the controller prefix for FNCS to a unique id.
     * only controllers with this prefix will talk with this auction
     *
     * @return the unique fncsControllerPrefix
     *
     */
    public String setFncsControllerPrefix() {
    	UUID myUUID = UUID.randomUUID();
		final String fncsPrefix = "fncsController_" + myUUID.toString().replace("-", "") + "NI";
    	setFncsControllerPrefix(fncsPrefix);
    	return fncsPrefix;
    }

    public void writeFncs2Directives(StringBuilder sb) {
    	if(this.getMarketSetUp().equals(MarketSetUp.AGGREGATE)){
    		if(this.simulator.getThirdPartySim() != null){
    			writeSubscritpionPrecommit(sb, "fixed_price", this.simulator.getThirdPartySim().getName(), "price");
    		} else {
    			throw new RuntimeException(String.format("GldSimulaator, %s, is missing a ThirdPartySimulator in order to set up the market", this.simulator.getName()));
    		}
    	}
    }

    private void writeSubscritpionPrecommit(StringBuilder sb, String auctionProperty, String thirdPartySimName, String key){
    	sb.append("subscribe \"precommit:");
    	sb.append(this.getName());
    	sb.append('.');
    	sb.append(auctionProperty);
    	sb.append(" <- ");
    	sb.append(thirdPartySimName);
    	sb.append('/');
    	sb.append(this.getName());
    	sb.append('_');
    	sb.append(key);
    	sb.append("\";\n");
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public String getNetworkInterfaceName() {
//        return this.getName() + "NI";
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "auction_ccsi";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "unit", unit);
        writeProperty(sb, "period", period);
        writeProperty(sb, "price_cap", priceCap);
        if(this.marketSetUp.equals(MarketSetUp.NORMAL)){
	        writeProperty(sb, "transaction_log_file", transactionLogFile);
	        writeProperty(sb, "curve_log_file", curveLogFile);
	        writeProperty(sb, "curve_log_info", curveLogInfo);

	        if (player != null) {
	            player.writeGldString(sb);
	            // Handle special case since we need a semicolon here
	            sb.insert(sb.length() - 1, ';');
	        }
        }
        writeProperty(sb, "special_mode", specialMode);
        writeProperty(sb, "init_price", initPrice);
        writeProperty(sb, "init_stdev", initStdev);
        writeProperty(sb, "use_future_mean_price", useFutureMeanPrice);
        writeProperty(sb, "warmup", warmup);
    }

    @Override
    public void writeExternalFiles(Path path) throws IOException {
        if (player != null) {
            this.player.writeExternalFiles(path);
        }
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

}
