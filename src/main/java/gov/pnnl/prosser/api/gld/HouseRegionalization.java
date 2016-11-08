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
*
*
 * Provides generalation of house parameter
 * diversity for various regions of the country
 */
package gov.pnnl.prosser.api.gld;


/**
 * Provides generalization of house parameter
 * diversity for various regions of the country
 *
 * @author fish334
 *
 */

import gov.pnnl.prosser.api.gld.enums.Region;
public class HouseRegionalization {
	private static Region[] regions = Region.values();

	private static Region getRegion(int i) {
		return regions[i-1];
	}

	private static final String[] wthr = {"CA-San_francisco","IL-Chicago","AZ-Phoenix","TN-Nashville","FL-Miami","HI-Honolulu"};
	private static final String[] tmZn = {"PST+8PDT","CST+6CDT","MST+7MDT","CST+6CDT","EST+5EDT","HST10"};

	/**
	 * thermal percentage[region][type of home][age of home]
	 * type of home: 1: single family home, 2: apartments, 3: mobile homes
	 * age of home:
	 * 		single family home: 1:pre-1940, 2:1940-1949, 3:1950-1959, 4:1960-1969, 5:1970-1979, 6:1980-1989, 7:19-2005
	 * 		apartment:			1:pre-1960, 2:1960-1989, 3:1990-2005
	 * 		mobile home:		1:pre-1960, 2:1960-1989, 3:1990-2005
	 */
	private static final double[][][] thermalPercentage = {
			{
				{0.0805,0.0724,0.1090,0.0867,0.1384,0.1264,0.1297},
				{0.0356,0.1223,0.0256,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0554,0.0181,0.0000,0.0000,0.0000,0.0000}
			},
			{
				{0.1574,0.0702,0.1290,0.0971,0.0941,0.0744,0.1532},
				{0.0481,0.0887,0.0303,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0372,0.0202,0.0000,0.0000,0.0000,0.0000}
			},
			{
				{0.0448,0.0252,0.0883,0.0843,0.1185,0.1315,0.2411},
				{0.0198,0.1159,0.0478,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0524,0.0302,0.0000,0.0000,0.0000,0.0000}
			},
			{
				{0.0526,0.0337,0.0806,0.0827,0.1081,0.1249,0.2539},
				{0.0217,0.1091,0.0502,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0491,0.0333,0.0000,0.0000,0.0000,0.0000}
			},
			{
				{0.0526,0.0337,0.0806,0.0827,0.1081,0.1249,0.2539},
				{0.0217,0.1091,0.0502,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0491,0.0333,0.0000,0.0000,0.0000,0.0000}
			},
			{
				{0.0526,0.0337,0.0806,0.0827,0.1081,0.1249,0.2539},
				{0.0217,0.1091,0.0502,0.0000,0.0000,0.0000,0.0000},
				{0.0000,0.0491,0.0333,0.0000,0.0000,0.0000,0.0000}
			},
	};

	/**
	 * thermal properties[type of home][age of home][properties]
	 * properties: 1:R-ceil, 2:R-wall, 3:R-floor, 4:window layers, 5:window glass, 6:glazing treatment, 7:window frame, 8:R-door, 9:air infiltration, 10:COP high, 11: COP low
	 */
	private static final double[][][] thrmlPrprts = {
			{
				{16.0,10.0,10.0,1.0,1.0,1.0,1.0,3.0,0.75,2.8,2.4},
				{19.0,11.0,12.0,2.0,1.0,1.0,1.0,3.0,0.75,3.0,2.5},
				{19.0,14.0,16.0,2.0,1.0,1.0,1.0,3.0,0.5,3.2,2.6},
				{30.0,17.0,19.0,2.0,1.0,1.0,2.0,3.0,0.5,3.4,2.8},
				{34.0,19.0,20.0,2.0,1.0,1.0,2.0,3.0,0.5,3.6,3.0},
				{36.0,22.0,22.0,2.0,2.0,1.0,2.0,5.0,0.25,3.8,3.0},
				{48.0,28.0,30.0,3.0,2.0,2.0,4.0,11.0,0.25,4.0,3.0}
			},
			{
				{13.4,11.7,9.4,1.0,1.0,1.0,1.0,2.2,0.75,2.8,1.9},
				{20.3,11.7,12.7,2.0,1.0,2.0,2.0,2.7,0.25,3.0,2.0},
				{28.7,14.3,12.7,2.0,2.0,3.0,4.0,6.3,0.125,3.2,2.1}
			},
			{
				{13.4,9.2,11.7,1.0,1.0,1.0,1.0,2.2,0.75,2.8,1.9},
				{13.4,9.2,11.7,1.0,1.0,1.0,1.0,2.2,0.75,2.8,1.9},
				{24.1,11.7,18.1,2.0,2.0,1.0,2.0,3.0,0.75,3.5,2.2}
			}
	};

	/**
	 * floor area[region][type of house]
	 * the average floor area
	 */
	private static final double[][] flrAr = {
			{2209.0,820.0,1054.0},
			{2951.0,798.0,1035.0},
			{2370.0,764.0,1093.0},
			{2655.0,901.0,1069.0},
			{2655.0,901.0,1069.0},
			{2655.0,901.0,1069.0}
	};

	/**
	 * The percentage of one story homes for each region
	 */
	private static final double[] nStry = {0.6887,0.5210,0.7745,0.7046,0.7043,0.7043};

	/**
	 * the average cooling setpoints[type of house][region][setpoint properties]
	 * setpoint properties: 1:night time percentage, 2:night time average difference, 3: high bin value, 4: low bin value
	 */
	private static final double[][][] clngStpnt = {
			{
				{0.098,0.96,69.0,65.0},
				{0.140,0.96,70.0,70.0},
				{0.166,0.96,73.0,71.0},
				{0.306,0.96,76.0,74.0},
				{0.206,0.96,79.0,77.0},
				{0.084,0.96,85.0,80.0}
			},
			{
				{0.155,0.49,69.0,65.0},
				{0.207,0.49,70.0,70.0},
				{0.103,0.49,73.0,71.0},
				{0.310,0.49,76.0,74.0},
				{0.155,0.49,79.0,77.0},
				{0.069,0.49,85.0,80.0}
			},
			{
				{0.138,0.97,69.0,65.0},
				{0.172,0.97,70.0,70.0},
				{0.172,0.97,73.0,71.0},
				{0.276,0.97,76.0,74.0},
				{0.138,0.97,79.0,77.0},
				{0.103,0.97,85.0,80.0}
			},
	};

	/**
	 * the average heating setpoints[type of house][region][setpoint properties]
	 * setpoint properties: 1:night time percentage, 2:night time average difference, 3: high bin value, 4: low bin value
	 */
	private static final double[][][] htngStpnt = {
			{
				{0.141,0.80,63.0,59.0},
				{0.204,0.80,66.0,64.0},
				{0.231,0.80,69.0,67.0},
				{0.163,0.80,70.0,70.0},
				{0.120,0.80,73.0,71.0},
				{0.141,0.80,79.0,74.0}
			},
			{
				{0.085,0.20,63.0,59.0},
				{0.132,0.20,66.0,64.0},
				{0.147,0.20,69.0,67.0},
				{0.279,0.20,70.0,70.0},
				{0.109,0.20,73.0,71.0},
				{0.248,0.20,79.0,74.0}
			},
			{
				{0.129,0.88,63.0,59.0},
				{0.177,0.88,66.0,64.0},
				{0.161,0.88,69.0,67.0},
				{0.274,0.88,70.0,70.0},
				{0.081,0.88,73.0,71.0},
				{0.177,0.88,79.0,74.0}
			},
	};

	private static final double[] prcGs = {0.0,0.0,0.0,0.0,0.0,0.0};
	private static final double[] prcPmp = {1.0,1.0,1.0,1.0,1.0,1.0};
	private static final double[] prcRs = {0.0,0.0,0.0,0.0,0.0,0.0};
	private static final double[] vrSzngFctr = {0.1,0.2,0.2,0.3,0.3,0.3};
	private static final double[] prcPlPmps = {0.0904,0.0591,0.0818,0.0657,0.0657,0.0657};
	private static final double[] prcWh = {0.7455,0.7485,0.6520,0.3572,0.3572,0.3572};

	private static final double[][] whSz = {
			{0.0000,0.3333,0.6667},
			{0.1459,0.5836,0.2706},
			{0.2072,0.5135,0.2793},
			{0.2259,0.5267,0.2475},
			{0.2259,0.5267,0.2475},
			{0.2259,0.5267,0.2475}
	};

	private double[][][] thermalProperties;
	private double[][] thermalPercentages;
	private String weather;
	private String timeZone;
	private double[][][] coolingSetpoint;
	private double[][][] heatingSetpoint;
	private double percGas;
	private double percPump;
	private double percRes;
	private double percAc;
	private double percPoolPumps;
	private double[] floorArea;
	private double percWh;
	private double[] whSize;
	private int noCoolSch;
	private int noHeatSch;
	private int noWaterSch;
	private double[] oneStory;
	private double overSizingFactor;
	private Region region;

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	/**
	 * @return the thermalProperties
	 */
	public double[][][] getThermalProperties() {
		return thermalProperties;
	}
	/**
	 * @param thermalProperties the thermalProperties to set
	 */
	public void setThermalProperties(double[][][] thermalProperties) {
		this.thermalProperties = thermalProperties;
	}
	/**
	 * @return the thermalPercentages
	 */
	public double[][] getThermalPercentages() {
		return thermalPercentages;
	}
	/**
	 * @param thermalPercentages the thermalPercentages to set
	 */
	public void setThermalPercentages(double[][] thermalPercentages) {
		this.thermalPercentages = thermalPercentages;
	}
	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timeZone;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timeZone = timezone;
	}
	/**
	 * @return the coolingSetpoint
	 */
	public double[][][] getCoolingSetpoint() {
		return coolingSetpoint;
	}
	/**
	 * @param coolingSetpoint the coolingSetpoint to set
	 */
	public void setCoolingSetpoint(double[][][] coolingSetpoint) {
		this.coolingSetpoint = coolingSetpoint;
	}
	/**
	 * @return the heatingSetpoint
	 */
	public double[][][] getHeatingSetpoint() {
		return heatingSetpoint;
	}
	/**
	 * @param heatingSetpoint the heatingSetpoint to set
	 */
	public void setHeatingSetpoint(double[][][] heatingSetpoint) {
		this.heatingSetpoint = heatingSetpoint;
	}
	/**
	 * @return the percGas
	 */
	public double getPercGas() {
		return percGas;
	}
	/**
	 * @param percGas the percGas to set
	 */
	public void setPercGas(double percGas) {
		this.percGas = percGas;
	}
	/**
	 * @return the percPump
	 */
	public double getPercPump() {
		return percPump;
	}
	/**
	 * @param percPump the percPump to set
	 */
	public void setPercPump(double percPump) {
		this.percPump = percPump;
	}
	/**
	 * @return the percRes
	 */
	public double getPercRes() {
		return percRes;
	}
	/**
	 * @param percRes the percRes to set
	 */
	public void setPercRes(double percRes) {
		this.percRes = percRes;
	}
	/**
	 * @return the perAc
	 */
	public double getPercAc() {
		return percAc;
	}
	/**
	 * @param perAc the perAc to set
	 */
	public void setPercAc(double perAc) {
		this.percAc = perAc;
	}
	/**
	 * @return the percPoolPumps
	 */
	public double getPercPoolPumps() {
		return percPoolPumps;
	}
	/**
	 * @param percPoolPumps the percPoolPumps to set
	 */
	public void setPercPoolPumps(double percPoolPumps) {
		this.percPoolPumps = percPoolPumps;
	}
	/**
	 * @return the floorArea
	 */
	public double[] getFloorArea() {
		return floorArea;
	}
	/**
	 * @param floorArea the floorArea to set
	 */
	public void setFloorArea(double[] floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * @return the percWh
	 */
	public double getPercWh() {
		return percWh;
	}
	/**
	 * @param percWh the percWh to set
	 */
	public void setPercWh(double percWh) {
		this.percWh = percWh;
	}
	/**
	 * @return the whSize
	 */
	public double[] getWhSize() {
		return whSize;
	}
	/**
	 * @param whSize the whSize to set
	 */
	public void setWhSize(double[] whSize) {
		this.whSize = whSize;
	}
	/**
	 * @return the noCoolSch
	 */
	public int getNoCoolSch() {
		return noCoolSch;
	}
	/**
	 * @param noCoolSch the noCoolSch to set
	 */
	public void setNoCoolSch(int noCoolSch) {
		this.noCoolSch = noCoolSch;
	}
	/**
	 * @return the noHeatSch
	 */
	public int getNoHeatSch() {
		return noHeatSch;
	}
	/**
	 * @param noHeatSch the noHeatSch to set
	 */
	public void setNoHeatSch(int noHeatSch) {
		this.noHeatSch = noHeatSch;
	}
	/**
	 * @return the noWaterSch
	 */
	public int getNoWaterSch() {
		return noWaterSch;
	}
	/**
	 * @param noWaterSch the noWaterSch to set
	 */
	public void setNoWaterSch(int noWaterSch) {
		this.noWaterSch = noWaterSch;
	}
	/**
	 * @return the oneStory
	 */
	public double[] getOneStory() {
		return oneStory;
	}
	/**
	 * @param oneStory the oneStory to set
	 */
	public void setOneStory(double[] oneStory) {
		this.oneStory = oneStory;
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

	public HouseRegionalization(int reg){
		this.region = getRegion(reg);
		this.thermalProperties = thrmlPrprts;
		this.thermalPercentages = thermalPercentage[reg-1];
		this.weather = wthr[reg-1];
		this.timeZone = tmZn[reg-1];
		this.coolingSetpoint = clngStpnt;
		this.heatingSetpoint = htngStpnt;
		this.percGas = prcGs[reg-1];
		this.percPump = prcPmp[reg-1];
		this.percRes = prcRs[reg-1];
		this.percAc = 1.0;
		this.percPoolPumps = prcPlPmps[reg-1];
		this.floorArea = flrAr[reg-1];
		this.percWh = prcWh[reg-1];
		this.whSize = whSz[reg - 1];
		this.noCoolSch = 8;
		this.noHeatSch = 6;
		this.noWaterSch = 6;
		this.oneStory = nStry;
		this.overSizingFactor = vrSzngFctr[reg-1];
	}
}
