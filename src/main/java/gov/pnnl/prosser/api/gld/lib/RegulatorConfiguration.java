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
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

/**
 * Regulator configuration
 *
 * @author sund130
 */
public class RegulatorConfiguration extends PowerflowLibrary {
    private ConnectionType connectionType;
    private double bandCenter;
    private double bandWidth;
    private double timeDelay;
    private int raiseTaps;
    private int lowerTaps;
    private double currentTransducerRatio;
    private double powerTransducerRatio;
    private double[] compensatorRSettings = new double[3];
    private double[] compensatorXSettings = new double[3];
    private EnumSet<PhaseCode> cTPhases;
    private EnumSet<PhaseCode> pTPhases;
    private double regulation;
    private RegulatorControl regulatorControl;
    private RegulatorType regulatorType;
    private int[] tapPositions = new int[3];

    public RegulatorConfiguration(final GldSimulator simulator) {
        super(simulator);
    }

	/**
     * @return the connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * @param connectionType the connectionType to set
     */
    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * @return the bandCenter
     */
    public double getBandCenter() {
        return bandCenter;
    }

    /**
     * @param bandCenter the bandCenter to set
     */
    public void setBandCenter(double bandCenter) {
        this.bandCenter = bandCenter;
    }

    /**
     * @return the bandWidth
     */
    public double getBandWidth() {
        return bandWidth;
    }

    /**
     * @param bandWidth the bandWidth to set
     */
    public void setBandWidth(double bandWidth) {
        this.bandWidth = bandWidth;
    }

    /**
     * @return the timeDelay
     */
    public double getTimeDelay() {
        return timeDelay;
    }

    /**
     * @param timeDelay the timeDelay to set
     */
    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    /**
     * @return the raiseTaps
     */
    public int getRaiseTaps() {
        return raiseTaps;
    }

    /**
     * @param raiseTaps the raiseTaps to set
     */
    public void setRaiseTaps(int raiseTaps) {
        this.raiseTaps = raiseTaps;
    }

    /**
     * @return the lowerTaps
     */
    public int getLowerTaps() {
        return lowerTaps;
    }

    /**
     * @param lowerTaps the lowerTaps to set
     */
    public void setLowerTaps(int lowerTaps) {
        this.lowerTaps = lowerTaps;
    }

    /**
     * @return the currentTransducerRatio
     */
    public double getCurrentTransducerRatio() {
        return currentTransducerRatio;
    }

    /**
     * @param currentTransducerRatio the currentTransducerRatio to set
     */
    public void setCurrentTransducerRatio(double currentTransducerRatio) {
        this.currentTransducerRatio = currentTransducerRatio;
    }

    /**
     * @return the powerTransducerRatio
     */
    public double getPowerTransducerRatio() {
        return powerTransducerRatio;
    }

    /**
     * @param powerTransducerRatio the powerTransducerRatio to set
     */
    public void setPowerTransducerRatio(double powerTransducerRatio) {
        this.powerTransducerRatio = powerTransducerRatio;
    }

    /**
     * @return the compensatorRSettings
     */
    public double[] getCompensatorRSettings() {
        return compensatorRSettings;
    }

    /**
     * @param compensatorRSettings the compensatorRSettings to set
     */
    public void setCompensatorRSettings(double[] compensatorRSettings) {
        this.compensatorRSettings = compensatorRSettings;
    }
    /**
     * @return the compensatorRSettingA
     */
    public double getCompensatorRSettingA() {
        return compensatorRSettings[0];
    }

    /**
     * @param compensatorRSettingA the compensatorRSettingA to set
     */

    public void setCompensatorRSettingA(double compensatorRSettingA) {
        this.compensatorRSettings[0] = compensatorRSettingA;
    }

    /**
     * @return the compensatorRSettingB
     */
    public double getCompensatorRSettingB() {
        return compensatorRSettings[1];
    }

    /**
     * @param compensatorRSettingB the compensatorRSettingB to set
     */

    public void setCompensatorRSettingB(double compensatorRSettingB) {
        this.compensatorRSettings[1] = compensatorRSettingB;
    }

    /**
     * @return the compensatorRSettingC
     */
    public double getCompensatorRSettingC() {
        return compensatorRSettings[2];
    }

    /**
     * @param compensatorRSettingC the compensatorRSettingC to set
     */

    public void setCompensatorRSettingC(double compensatorRSettingC) {
        this.compensatorRSettings[2] = compensatorRSettingC;
    }

    /**
     * @return the compensatorXSettings
     */
    public double[] getCompensatorXSettings() {
        return compensatorXSettings;
    }

    /**
     * @param compensatorXSettings the compensatorXSettings to set
     */
    public void setCompensatorXSettings(double[] compensatorXSettings) {
        this.compensatorXSettings = compensatorXSettings;
    }

    /**
     * @return the compensatorXSettingA
     */
    public double getCompensatorXSettingA() {
        return compensatorXSettings[0];
    }

    /**
     * @param compensatorXSettingA the compensatorXSettingA to set
     */
    public void setCompensatorXSettingA(double compensatorXSettingA) {
        this.compensatorXSettings[0] = compensatorXSettingA;
    }

    /**
     * @return the compensatorXSettingB
     */
    public double getCompensatorXSettingB() {
        return compensatorXSettings[1];
    }

    /**
     * @param compensatorXSettingB the compensatorXSettingB to set
     */
    public void setCompensatorXSettingB(double compensatorXSettingB) {
        this.compensatorXSettings[1] = compensatorXSettingB;
    }

    /**
     * @return the compensatorXSettingC
     */
    public double getCompensatorXSettingC() {
        return compensatorXSettings[2];
    }

    /**
     * @param compensatorXSettingC the compensatorXSettingC to set
     */
    public void setCompensatorXSettingC(double compensatorXSettingC) {
        this.compensatorXSettings[2] = compensatorXSettingC;
    }

    /**
     * @return the cTPhases
     */
    public EnumSet<PhaseCode> getcTPhases() {
        return cTPhases;
    }

    /**
     * @param cTPhases the cTPhases to set
     */
    public void setcTPhases(EnumSet<PhaseCode> cTPhases) {
        this.cTPhases = cTPhases;
    }

    /**
     * @return the pTPhases
     */
    public EnumSet<PhaseCode> getpTPhases() {
        return pTPhases;
    }

    /**
     * @param pTPhases the pTPhases to set
     */
    public void setpTPhases(EnumSet<PhaseCode> pTPhases) {
        this.pTPhases = pTPhases;
    }

    /**
     * @return the regulation
     */
    public double getRegulation() {
        return regulation;
    }

    /**
     * @param regulation the regulation to set
     */
    public void setRegulation(double regulation) {
        this.regulation = regulation;
    }

    /**
     * @return the regulatorControl
     */
    public RegulatorControl getRegulatorControl() {
        return regulatorControl;
    }

    /**
     * @param regulatorControl the regulatorControl to set
     */
    public void setRegulatorControl(RegulatorControl regulatorControl) {
        this.regulatorControl = regulatorControl;
    }

    /**
     * @return the regulatorType
     */
    public RegulatorType getRegulatorType() {
        return regulatorType;
    }

    /**
     * @param regulatorType the regulatorType to set
     */
    public void setRegulatorType(RegulatorType regulatorType) {
        this.regulatorType = regulatorType;
    }

    /**
     * @return the tapPositions
     */
    public int[] getTapPositions() {
        return tapPositions;
    }

    /**
     * @param tapPositions the tapPositions to set
     */
    public void setTapPositions(int[] tapPositions) {
        this.tapPositions = tapPositions;
    }

    /**
     * @return the tapPositionA
     */
    public int getTapPositionA() {
        return tapPositions[0];
    }

    /**
     * @param tapPositionA the tapPositionA to set
     */
    public void setTapPositionA(int tapPositionA) {
        this.tapPositions[0] = tapPositionA;
    }

    /**
     * @return the tapPositionB
     */
    public int getTapPositionB() {
        return tapPositions[1];
    }

    /**
     * @param tapPositionB the tapPositionB to set
     */
    public void setTapPositionB(int tapPositionB) {
        this.tapPositions[1] = tapPositionB;
    }

    /**
     * @return the tapPositionC
     */
    public int getTapPositionC() {
        return tapPositions[2];
    }

    /**
     * @param tapPositionC the tapPositionC to set
     */
    public void setTapPositionC(int tapPositionC) {
        this.tapPositions[2] = tapPositionC;
    }

    /**
     * {@inheritDoc}
	 */
	@Override
	protected String getGldObjectType() {
	    return "regulator_configuration";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeGldProperties(final StringBuilder sb) {
	    writeProperty(sb, "connect_type", this.connectionType);
	    writeProperty(sb, "band_center", this.bandCenter);
	    writeProperty(sb, "band_width", this.bandWidth);
	    writeProperty(sb, "time_delay", this.timeDelay);
	    writeProperty(sb, "raise_taps", this.raiseTaps);
	    writeProperty(sb, "lower_taps", this.lowerTaps);
	    writeProperty(sb, "current_transducer_ratio", this.currentTransducerRatio);
	    writeProperty(sb, "power_transducer_ratio", this.powerTransducerRatio);
	    writeProperty(sb, "compensator_r_setting_A", this.getCompensatorRSettingA());
	    writeProperty(sb, "compensator_x_setting_A", this.getCompensatorXSettingA());
	    writeProperty(sb, "compensator_r_setting_B", this.getCompensatorRSettingB());
	    writeProperty(sb, "compensator_x_setting_B", this.getCompensatorXSettingB());
	    writeProperty(sb, "compensator_r_setting_C", this.getCompensatorRSettingC());
	    writeProperty(sb, "compensator_x_setting_C", this.getCompensatorXSettingC());
	    writeProperty(sb, "CT_phase", PhaseCode.writeGldProperties(this.cTPhases));
	    writeProperty(sb, "PT_phase", PhaseCode.writeGldProperties(this.pTPhases));
	    writeProperty(sb, "regulation", this.regulation);
	    writeProperty(sb, "Control", this.regulatorControl);
	    writeProperty(sb, "Type", this.regulatorType);
	    writeProperty(sb, "tap_pos_A", this.getTapPositionA());
	    writeProperty(sb, "tap_pos_B", this.getTapPositionB());
	    writeProperty(sb, "tap_pos_C", this.getTapPositionC());
	}

	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionType, bandCenter, bandWidth, timeDelay, raiseTaps, lowerTaps,
                currentTransducerRatio, powerTransducerRatio, compensatorRSettings, compensatorXSettings, cTPhases,
                pTPhases, regulation, regulatorControl, regulatorType, tapPositions);
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
        final RegulatorConfiguration other = (RegulatorConfiguration) obj;
        return Objects.equals(this.connectionType, other.connectionType)
                && Objects.equals(this.bandCenter, other.bandCenter)
                && Objects.equals(this.bandWidth, other.bandWidth)
                && Objects.equals(this.timeDelay, other.timeDelay)
                && Objects.equals(this.raiseTaps, other.raiseTaps)
                && Objects.equals(this.lowerTaps, other.lowerTaps)
                && Objects.equals(this.currentTransducerRatio, other.currentTransducerRatio)
                && Objects.equals(this.powerTransducerRatio, other.powerTransducerRatio)
                && Arrays.equals(this.compensatorRSettings, other.compensatorRSettings)
                && Arrays.equals(this.compensatorXSettings, other.compensatorXSettings)
                && Objects.equals(this.cTPhases, other.cTPhases)
                && Objects.equals(this.pTPhases, other.pTPhases)
                && Objects.equals(this.regulation, other.regulation)
                && Objects.equals(this.regulatorControl, other.regulatorControl)
                && Objects.equals(this.regulatorType, other.regulatorType)
                && Arrays.equals(this.tapPositions, other.tapPositions);
    }

}
