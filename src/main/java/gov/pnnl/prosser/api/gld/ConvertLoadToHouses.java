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
package gov.pnnl.prosser.api.gld;

import java.util.EnumSet;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.AuxiliaryStrategy;
import gov.pnnl.prosser.api.gld.enums.AuxiliarySystemType;
import gov.pnnl.prosser.api.gld.enums.CoolingSystemType;
import gov.pnnl.prosser.api.gld.enums.HeatingSystemType;
import gov.pnnl.prosser.api.gld.enums.MotorEfficiency;
import gov.pnnl.prosser.api.gld.enums.MotorModel;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.Region;
import gov.pnnl.prosser.api.gld.enums.WaterheaterLocation;
import gov.pnnl.prosser.api.gld.obj.House;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.WaterHeater;
import gov.pnnl.prosser.api.gld.obj.ZIPLoad;

/**
 * @author fish334
 *
 */
public abstract class ConvertLoadToHouses {
	public static double[][][] generateHouses(GldSimulator sim, AbstractGldObject parent, double feederNumberOfHouses, double loadNumberOfHouses, EnumSet<PhaseCode> parentPhases, HouseRegionalization regionData, double[][] thermalIntegrity,double[][] coolSp, double[][] heatSp, Random randNumGen){
		double[][][] rv = generateHouses(sim, parent, feederNumberOfHouses, loadNumberOfHouses, parentPhases, regionData, thermalIntegrity, coolSp, heatSp, randNumGen, true);
		return rv;
	}
	public static double[][][] generateHouses(GldSimulator sim, AbstractGldObject parent, double feederNumberOfHouses, double loadNumberOfHouses, EnumSet<PhaseCode> parentPhases, HouseRegionalization regionData, double[][] thermalIntegrity,double[][] coolSp, double[][] heatSp, Random randNumGen, boolean useThermostatSchedules){
		final Region region = regionData.getRegion();
		final double[][][] thermalProperties = regionData.getThermalProperties();
		final double[][][] coolingSetpoint = regionData.getCoolingSetpoint();
		final double[][][] heatingSetpoint = regionData.getHeatingSetpoint();
		final double percGas = regionData.getPercGas();
		final double percPump = regionData.getPercPump();
		final double percAc = regionData.getPercAc();
		final double[] floorArea = regionData.getFloorArea();
		final double percWh = regionData.getPercWh();
		final double[] whSize = regionData.getWhSize();
		final int noCoolSch = regionData.getNoCoolSch();
		final int noHeatSch = regionData.getNoHeatSch();
		final int noWaterSch = regionData.getNoWaterSch();
		final double[] oneStory = regionData.getOneStory();
		final double overSizingFactor = regionData.getOverSizingFactor();
		double lgVsSm = -0.5 + randNumGen.nextDouble();
		String parentName = parent.getName();
		int i = 0;
		int j = 0;
		int thermInt = 0;
		int rowTi = 0;
		int colTi = 0;
		long skewValue = 0;
		long whSkewValue = 0;
		long ppSkewValue = 0;
		double[] thermalTemp;
		double fArea = 0;
		double storyRand = 0;
		int heightRand = 0;
		double faRand = 0;
		double floor_area = 0;
		int stories = 0;
		double osRand = 0;

		if(parent.getGldObjectType().equals("triplex_node") || parent.getGldObjectType().equals("triplex_meter")){

			for(i = 0; i < loadNumberOfHouses; i++){
				TriplexMeter tpm = sim.triplexMeter(String.format("tpm%d_%s", i, parentName));
				tpm.setParent(parent);
				tpm.setPhases(parentPhases);
				tpm.setGroupId("Residential_Meter");
				tpm.setNominalVoltage(120.0);
				int count = 0;
				//calculate Schedule Skews
				skewValue = (long)(2700.0*randNumGen.nextGaussian());
				if(skewValue < -8100){
					skewValue = -8100;
				} else if(skewValue > 8100) {
					skewValue = 8100;
				}
				whSkewValue = (long)(3.0*2700.0*randNumGen.nextGaussian());
				if(whSkewValue < -6*8100){
					whSkewValue = -6*8100;
				} else if(whSkewValue > 6*8100) {
					whSkewValue = 6*8100;
				}
				ppSkewValue = (long)(128.0*2700.0*randNumGen.nextGaussian());
				if(ppSkewValue < -128*8100){
					ppSkewValue = -128*8100;
				} else if(ppSkewValue > 128*8100) {
					ppSkewValue = 128*8100;
				}
				thermInt = (int)Math.ceil(3 * 7 * randNumGen.nextDouble());
				rowTi = thermInt % 3;
				colTi = thermInt % 7;
				while(thermalIntegrity[rowTi][colTi] < 1.0) {
					thermInt = (int)Math.ceil(3 * 7 * randNumGen.nextDouble());
					rowTi = thermInt % 3;
					colTi = thermInt % 7;
				}
				//calculate floor area height and number of stories
				thermalIntegrity[rowTi][colTi] = thermalIntegrity[rowTi][colTi] - 1.0;
				thermalTemp = thermalProperties[rowTi][colTi];
				fArea = floorArea[rowTi];
				storyRand = randNumGen.nextDouble();
				heightRand = randNumGen.nextInt(1) + 1;
				faRand = randNumGen.nextDouble();
				if(rowTi == 0) {// Single Family Homes
					floor_area = fArea + ((fArea / 2.0) * faRand * ((double)rowTi - 3.0))/3.0;
					if(storyRand < oneStory[region.ordinal()]) {
						stories = 1;
					} else {
						stories = 2;
					}
				} else {
					floor_area = fArea + ((fArea / 2.0) * (0.5 - faRand));
					stories = 1;
					heightRand = 0;
				}
				floor_area = (1 + lgVsSm) * floor_area;
				if(floor_area > 4000.0) {
					floor_area = 3800 + (faRand * 200.0);
				} else if(floor_area < 300.0) {
					floor_area = 300 + (faRand * 100.0);
				}
				osRand = overSizingFactor * (0.8 + (0.4 * randNumGen.nextDouble()));
				double rRoof = thermalTemp[0] * (0.8 + (0.4 * randNumGen.nextDouble()));
				double rWall = thermalTemp[1] * (0.8 + (0.4 * randNumGen.nextDouble()));
				double rFloor = thermalTemp[2] * (0.8 + (0.4 * randNumGen.nextDouble()));
				double rDoors = thermalTemp[7] * (0.8 + (0.4 * randNumGen.nextDouble()));
				double airChangePerHour = thermalTemp[8] * (0.8 + (0.4 * randNumGen.nextDouble()));
				double cCOP = thermalTemp[10] + (randNumGen.nextDouble() * (thermalTemp[9] - thermalTemp[10]));
				double hCOP = cCOP;
				double initTemp = 68.0 + (4.0 * randNumGen.nextDouble());
				double massFloor = 2.5 + (1.5 * randNumGen.nextDouble());
				double heatType = randNumGen.nextDouble();
				double coolType = randNumGen.nextDouble();
				int coolingSet = (int)Math.ceil(noCoolSch * randNumGen.nextDouble());
				int heatingSet = (int)Math.ceil(noHeatSch * randNumGen.nextDouble());

				//create house object
				House resHouse = sim.house(String.format("house%d_%s", i, parentName));
				resHouse.setParent(tpm);
				resHouse.setGroupId("Residential");
				resHouse.setScheduleSkew(skewValue);
				resHouse.setFloorArea(floor_area);
				resHouse.setNumberOfStories(stories);
				resHouse.setCeilingHeight(8.0 + heightRand);
				resHouse.setOverSizingFactor(osRand);
				resHouse.setRroof(rRoof);
				resHouse.setRwall(rWall);
				resHouse.setRfloor(rFloor);
				resHouse.setGlazingLayers((int)thermalTemp[3]);
				resHouse.setGlassType((int)thermalTemp[4]);
				resHouse.setGlazingTreatment((int)thermalTemp[5]);
				resHouse.setWindowFrame((int)thermalTemp[6]);
				resHouse.setRdoors(rDoors);
				resHouse.setAirchangePerHour(airChangePerHour);
				resHouse.setCoolingCop(cCOP);
				resHouse.setAirTemperature(initTemp);
				resHouse.setMassTemperature(initTemp);
				resHouse.setTotalThermalMassPerFloorArea(massFloor);
				if(heatType <= percGas) {
					resHouse.setHeatingSystemType(HeatingSystemType.GAS);
					if(coolType <= percAc) {
						resHouse.setCoolingSystemType(CoolingSystemType.ELECTRIC);
					} else {
						resHouse.setCoolingSystemType(CoolingSystemType.NONE);
					}
				} else if(heatType <= (percGas + percPump)) {
					resHouse.setHeatingSystemType(HeatingSystemType.HEAT_PUMP);
					resHouse.setHeatingCOP(hCOP);
					resHouse.setCoolingSystemType(CoolingSystemType.ELECTRIC);
					resHouse.setAuxiliaryStrategy(AuxiliaryStrategy.DEADBAND);
					resHouse.setAuxiliarySystemType(AuxiliarySystemType.ELECTRIC);
					resHouse.setMotorModel(MotorModel.BASIC);
					resHouse.setMotorEfficiency(MotorEfficiency.AVERAGE);
				} else if(floor_area*(8.0 + heightRand) > 12000.0) {
					resHouse.setHeatingSystemType(HeatingSystemType.GAS);
					if(coolType <= percAc) {
						resHouse.setCoolingSystemType(CoolingSystemType.ELECTRIC);
					} else {
						resHouse.setCoolingSystemType(CoolingSystemType.NONE);
					}
				} else {
					resHouse.setHeatingSystemType(HeatingSystemType.RESISTANCE);
					if(coolType <= percAc) {
						resHouse.setCoolingSystemType(CoolingSystemType.ELECTRIC);
						resHouse.setMotorModel(MotorModel.BASIC);
						resHouse.setMotorEfficiency(MotorEfficiency.GOOD);
					} else {
						resHouse.setCoolingSystemType(CoolingSystemType.NONE);
					}
				}
				resHouse.setHvacBreakerRating(1000.0);
				int cooling_set = (int)Math.ceil(noCoolSch * randNumGen.nextDouble());
				double[][] coolsp = coolingSetpoint[rowTi];
				int noCoolBins = coolsp.length;
				int coolBin = randNumGen.nextInt(noCoolBins);
				while(coolSp[coolBin][rowTi] < 1 && count < 20){
					coolBin = randNumGen.nextInt(noCoolBins);
					count = count + 1;
				}
				coolSp[coolBin][rowTi] = coolSp[coolBin][rowTi] - 1;
				int heating_set = (int)Math.ceil(noHeatSch * randNumGen.nextDouble());
				double[][] heatsp = heatingSetpoint[rowTi];
				int noHeatBins = heatsp.length;
				int heatBin = randNumGen.nextInt(noHeatBins);
				count = 0;
				while((heatSp[heatBin][rowTi] < 1 && count < 20) || heatsp[heatBin][2] >= coolsp[coolBin][3]){
					heatBin = randNumGen.nextInt(noHeatBins);
					count = count +1;
				}
				heatSp[heatBin][rowTi] = heatSp[heatBin][rowTi] - 1;
				double coolNight = (coolsp[coolBin][2] - coolsp[coolBin][3])*randNumGen.nextDouble() + coolsp[coolBin][3] + 1;
				double heatNight = (heatsp[heatBin][2] - heatsp[heatBin][3])*randNumGen.nextDouble() + heatsp[heatBin][3] + 1;
				double coolNightDiff = coolsp[coolBin][1]*2*randNumGen.nextDouble();
				double heatNightDiff = heatsp[heatBin][1]*2*randNumGen.nextDouble();
				if(useThermostatSchedules){
					resHouse.setCoolingSetpointFn(String.format("cooling%d*%1.3f+%2.2f", coolingSet, coolNightDiff, coolNight));
					resHouse.setHeatingSetpointFn(String.format("heating%d*%1.3f+%2.2f", heatingSet, heatNightDiff, heatNight));
				} else {
					resHouse.setCoolingSetpointFn(String.format("%2.2f", coolNight));
					resHouse.setHeatingSetpointFn(String.format("%2.2f", heatNight));
				}
				//Scale all of the end-use loads
				double scalar1 = 324.9 * Math.pow(floor_area, 0.442) / 8907;
				double scalar2 = 0.8 + (0.4 * randNumGen.nextDouble());
				double scalar3 = 0.8 + (0.4 * randNumGen.nextDouble());
				double respScalar = scalar1 * scalar2;
				double unrespScalar = scalar1 * scalar3;
				ZIPLoad respLoad = resHouse.addLoad();
				respLoad.setName(String.format("house%d_resp_%s", i, parentName));
				respLoad.setParent(resHouse);
				respLoad.setScheduleSkew(resHouse.getScheduleSkew());
				respLoad.setBasePowerFn(String.format("responsive_loads*%f", respScalar));
				respLoad.setHeatFraction(0.8);
				respLoad.setPowerPf(1.0);
				respLoad.setCurrentPf(1.0);
				respLoad.setImpedancePf(1.0);
				respLoad.setPowerFraction(0.4);
				respLoad.setCurrentFraction(0.4);
				respLoad.setImpedanceFraction(0.2);
				ZIPLoad unrespLoad = resHouse.addLoad();
				unrespLoad.setName(String.format("house%d_unresp_%s", i, parentName));
				unrespLoad.setParent(resHouse);
				unrespLoad.setScheduleSkew(resHouse.getScheduleSkew());
				unrespLoad.setBasePowerFn(String.format("unresponsive_loads*%f", unrespScalar));
				unrespLoad.setHeatFraction(0.8);
				unrespLoad.setPowerPf(1.0);
				unrespLoad.setCurrentPf(1.0);
				unrespLoad.setImpedancePf(1.0);
				unrespLoad.setPowerFraction(0.4);
				unrespLoad.setCurrentFraction(0.4);
				unrespLoad.setImpedanceFraction(0.2);
				//Add Waterheater
				double heatElement = 3.0 + (0.5*randNumGen.nextDouble());
				double tankSet = 120.0 + (16.0*randNumGen.nextDouble());
				double tankTemp = tankSet + randNumGen.nextDouble();
				double thermDead = 4.0 + (4.0*randNumGen.nextDouble());
				double tankUa = 2.0 + (2.0*randNumGen.nextDouble());
				int waterSch = (int)Math.ceil(noWaterSch*randNumGen.nextDouble());
				double waterVar = 0.95 + (0.1*randNumGen.nextDouble());
				double whSizeTest = randNumGen.nextDouble();
				double whSizeRand = randNumGen.nextInt(3);
				double whsize = 0.0;
				if(heatType > (1 - percWh)){
					WaterHeater wh = sim.waterHeater(String.format("house%d_wh_%s", i, parentName));
					wh.setParent(resHouse);
					wh.setScheduleSkew(whSkewValue);
					wh.setHeatingElementCapacity(heatElement);
					wh.setTankSetpoint(tankSet);
					wh.setTemperature(tankTemp);
					wh.setThermostatDeadband(thermDead);
					wh.setLocation(WaterheaterLocation.INSIDE);
					wh.setTankUA(tankUa);
					if(whSizeTest < whSize[0]){
						whsize = 20.0 + ((whSizeRand - 1.0)*5.0);
						wh.setTankVolume(whsize);
						wh.setWaterDemandFn(String.format("small_%d*%f", waterSch, waterVar));
					} else if(whSizeTest < (whSize[0] + whSize[1])){
						whsize = 30.0 + ((whSizeRand - 1.0)*10.0);
						wh.setTankVolume(whsize);
						if(floor_area < 2000.0){
							wh.setWaterDemandFn(String.format("small_%d*%f", waterSch, waterVar));
						} else {
							wh.setWaterDemandFn(String.format("large_%d*%f", waterSch, waterVar));
						}
					} else if(floor_area > 2000.0){
						whsize = 50.0 + ((whSizeRand - 1.0)*10.0);
						wh.setTankVolume(whsize);
						wh.setWaterDemandFn(String.format("large_%d*%f", waterSch, waterVar));
					} else {
						whsize = 30.0 + ((whSizeRand - 1.0)*10.0);
						wh.setTankVolume(whsize);
						wh.setWaterDemandFn(String.format("large_%d*%f", waterSch, waterVar));
					}
				}
			}
			double[][][] retval = {thermalIntegrity, coolSp, heatSp};
			return retval;
		} else {
			throw new RuntimeException(String.format("ConvertLoadToHouses.generateHouses(): Function attempted to attach house objects to the parent %s. %s is not a TriplexNode or a TriplexMeter class.", parent.getName(), parent.getName()));
		}
	}
}