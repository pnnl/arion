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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.BusType;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.InstallationType;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.RegulatorControl;
import gov.pnnl.prosser.api.gld.enums.RegulatorType;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.Regulator;
import gov.pnnl.prosser.api.gld.obj.Switch;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexNode;


/**
 * @author sund130
 *
 */
public class Ieee8500NodeTestFeeder {
    private final GldSimulator simulator;
    private final int region;
    private final String name;
    private final boolean useHousesInsteadOfLoads;

    private final String acsr397 = "397_ACSR";
    private final String acsr2_0 = "2/0_ACSR";
    private final String acsr4 = "4_ACSR";
    private final String acsr2 = "2_ACSR";
    private final String acsr1_0 = "1/0_ACSR";
    private final String wpal4 = "4_WPAL";
    private final String tpx1_0 = "1/0_TPX";
    private final String tpx4_0 = "4/0_TPX";
    private final String dpx4 = "4_DPX";
    private final String wcs1_0 = "1/0_3W_CS";
    private final String tpx4 = "4_TPX";
    private final String wpal6 = "6_WPAL";
    private final String wpal2 = "2_WPAL";
    private final String wpal2_0 = "2/0_WPAL";
    private final String defaultConductor = "DEFAULT";
    private final String cu600 = "600_CU";

    private Map<String, OverheadLineConductor> overheadLineConductors;
    private Map<String, LineSpacing> lineSpacings;
    private Map<String, StandardLineConfiguration<OverheadLineConductor>> lineConfigurations;
    private Map<String, Node> nodes;
    private TransformerConfiguration transformerConfiguration;
    private Transformer transformer;
    private Map<Integer, RegulatorConfiguration> regulatorConfigurations;
    private Map<Integer, Regulator> regulators;
    private Map<Integer, OverheadLine> overheadLines;
    private Map<String, Switch> switches;

    private List<String> lineCsv = new ArrayList<String>();
    private List<String> loadCsv = new ArrayList<String>();
    private List<String> triplexLineCsv = new ArrayList<String>();

    public Ieee8500NodeTestFeeder(final GldSimulator simulator, final String name,
            final boolean useHousesInsteadOfLoads) {
        this(simulator, name, 1, useHousesInsteadOfLoads);
    }

    public Ieee8500NodeTestFeeder(final GldSimulator simulator, final String name, final int region,
            final boolean useHousesInsteadOfLoads) {
        this.simulator = simulator;
        this.name = name;
        this.region = region;
        this.useHousesInsteadOfLoads = useHousesInsteadOfLoads;
        this.overheadLineConductors = new HashMap<String, OverheadLineConductor>();
        this.lineSpacings = new HashMap<String, LineSpacing>();
        this.lineConfigurations = new HashMap<String, StandardLineConfiguration<OverheadLineConductor>>();
        this.nodes = new HashMap<String, Node>();
        this.regulatorConfigurations = new HashMap<Integer, RegulatorConfiguration>();
        this.regulators = new HashMap<Integer, Regulator>();
        this.overheadLines = new HashMap<Integer, OverheadLine>();
        this.switches = new HashMap<String, Switch>();

        this.readCsv(this.lineCsv, "IEEE_8500_Feeder_Lines.csv", 3);
        this.readCsv(this.loadCsv, "IEEE_8500_Feeder_Loads.csv", 5);
        this.readCsv(this.triplexLineCsv, "IEEE_8500_Feeder_Triplex_Lines.csv", 4);

        // create simulator objects
        this.createOverheadLineConductors();
        this.createLineSpacings();
        this.createLineConfigurations();
        this.createNodes();
        this.createTriplexNodes();
        this.createTransformerConfigurationAndTransformer();
        this.createRegulatorConfigurations();
        this.createRegulators();
        this.createOverheadLines();
        this.createSwitches();
        this.createTriplexLines();
    }

    private void createOverheadLineConductors() {
        String nameFormat = "overhead_line_conductor_%s";
        Map<String, double[]> conductorValues = new HashMap<String, double[]>();

        conductorValues.put(this.acsr397, new double[] { .73152, .159692 });
        conductorValues.put(this.acsr2_0, new double[] { .155448, .53003 });
        conductorValues.put(this.acsr4, new double[] { .133096, 1.572497 });
        conductorValues.put(this.acsr2, new double[] { .127508, 1.010163 });
        conductorValues.put(this.acsr1_0, new double[] { .13589, .646847 });
        conductorValues.put(this.wpal4, new double[] { .13589, .646847 });
        conductorValues.put(this.tpx1_0, new double[] { .34798, .114332 });
        conductorValues.put(this.tpx4_0, new double[] { .480568, .301837 });
        conductorValues.put(this.dpx4, new double[] { .21336, 1.522297 });
        conductorValues.put(this.wcs1_0, new double[] { .34798, .603674 });
        conductorValues.put(this.tpx4, new double[] { .21336, 1.522297 });
        conductorValues.put(this.wpal6, new double[] { .120142, 2.43012 });
        conductorValues.put(this.wpal2, new double[] { .127508, 1.050198 });
        conductorValues.put(this.wpal2_0, new double[] { .155448, .53003 });
        conductorValues.put(this.defaultConductor, new double[] { .88392, .121789 });
        conductorValues.put(this.cu600, new double[] { .8382, .062323 });

        for (Map.Entry<String, double[]> conductor : conductorValues.entrySet()) {
            OverheadLineConductor overheadLineConductor = this.simulator
                    .overheadLineConductor(String.format(nameFormat, conductor.getKey()));
            overheadLineConductor.setGeometricMeanRadius(conductor.getValue()[0]);
            overheadLineConductor.setResistance(conductor.getValue()[1]);
            this.overheadLineConductors.put(conductor.getKey(), overheadLineConductor);
        }
    }

    private void createLineSpacings() {
        String stringFormat = "line_spacing_%s";

        String key1 = "SinglePhaseA";
        LineSpacing lineSpacings1 = this.simulator.lineSpacing(String.format(stringFormat, key1));
        lineSpacings1.setDistanceAToN(2.3062);
        this.lineSpacings.put(key1, lineSpacings1);

        String key2 = "SinglePhaseB";
        LineSpacing lineSpacings2 = this.simulator.lineSpacing(String.format(stringFormat, key2));
        lineSpacings2.setDistanceBToN(2.3062);
        this.lineSpacings.put(key2, lineSpacings2);

        String key3 = "SinglePhaseC";
        LineSpacing lineSpacings3 = this.simulator.lineSpacing(String.format(stringFormat, key3));
        lineSpacings3.setDistanceCToN(2.3062);
        this.lineSpacings.put(key3, lineSpacings3);

        String key4 = "TwoPhaseAC";
        LineSpacing lineSpacings4 = this.simulator.lineSpacing(String.format(stringFormat, key4));
        lineSpacings4.setDistanceAToC(1.2192);
        lineSpacings4.setDistanceAToN(1.70388);
        lineSpacings4.setDistanceCToN(1.5911);
        this.lineSpacings.put(key4, lineSpacings4);

        String key5 = "ThreePhaseABC";
        LineSpacing lineSpacings5 = this.simulator.lineSpacing(String.format(stringFormat, key5));
        lineSpacings5.setDistanceAToB(.97584);
        lineSpacings5.setDistanceAToC(1.1292);
        lineSpacings5.setDistanceBToC(.762);
        lineSpacings5.setDistanceAToN(1.70388);
        lineSpacings5.setDistanceBToN(2.1336);
        lineSpacings5.setDistanceCToN(1.5911);
        this.lineSpacings.put(key5, lineSpacings5);
    }

    private void createLineConfigurations() {
        // format is [name, phaseAConductor, phaseBConductor, phaseCConductor,
        // phaseNConductor]
        // any null conductor is not present in the line configuration
        List<String[]> lineConfigurationData = new ArrayList<String[]>();
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx4_ACSR", null, this.acsr4, null, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR4_ACSR", null, null, this.acsr4, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-x2_ACSRx2_ACSR", null, this.acsr2, null, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx4_WPAL", null, this.acsr4, null, this.wpal4 });
        lineConfigurationData.add(new String[] { "3PH_H-2/0_ACSR2/0_ACSR2/0_ACSR2_ACSR", this.acsr2_0, this.acsr2_0,
                this.acsr2_0, this.acsr2 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR4_ACSR4_ACSR4_ACSR", this.acsr4, this.acsr4, this.acsr4, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-4_WPALxx2_WPAL", this.wpal4, null, null, this.wpal2 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR2_ACSR2_ACSR4_WPAL", this.acsr4, this.acsr2, this.acsr2, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx4_ACSR", this.acsr4, null, null, this.acsr4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR4_ACSR4_ACSR4_WPAL", this.acsr4, this.acsr4, this.acsr4, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-2_ACSRxx2_ACSR", this.acsr2, null, null, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-4_WPALxx4_ACSR", this.wpal4, null, null, this.acsr4 });
        lineConfigurationData.add(new String[] { "3PH_H-397_ACSR397_ACSR397_ACSR2/0_ACSR", this.acsr397, this.acsr397,
                this.acsr397, this.acsr2_0 });
        lineConfigurationData.add(new String[] { "1PH-2_ACSRxx4_ACSR", this.acsr2, null, null, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-xx2_ACSR2_ACSR", null, null, this.acsr2, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-xx4_WPAL4_WPAL", null, null, this.wpal4, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-x4_WPALx4_WPAL", null, this.wpal4, null, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-xx4_WPAL4_ACSR", null, null, this.wpal4, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-x2_ACSRx1/0_TPX", null, this.acsr2, null, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx4_WPAL", this.acsr4, null, null, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR1/0_TPX", null, null, this.acsr4, this.tpx1_0 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_WPAL4_WPAL4_WPAL4_ACSR", this.wpal4, this.wpal4, this.wpal4, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-x4_WPALx4_ACSR", null, this.wpal4, null, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-4_WPALxx4_WPAL", this.wpal4, null, null, this.wpal4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_WPAL4_WPAL4_WPAL4_WPAL", this.wpal4, this.wpal4, this.wpal4, this.wpal4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-2_ACSR2_ACSR2_ACSR2_ACSR", this.acsr2, this.acsr2, this.acsr2, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx1/0_TPX", this.acsr4, null, null, this.tpx1_0 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR2_ACSR2_ACSR4_ACSR", this.acsr4, this.acsr2, this.acsr2, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR4_WPAL", null, null, this.acsr4, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx2_ACSR", this.acsr4, null, null, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-x4_WPALx1/0_TPX", null, this.wpal4, null, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR1/0_ACSR", null, null, this.acsr4, this.acsr1_0 });
        lineConfigurationData.add(new String[] { "1PH-2_ACSRxx4_WPAL", this.acsr2, null, null, this.wpal4 });
        lineConfigurationData.add(new String[] { "1PH-xx2/0_ACSR1/0_TPX", null, null, this.acsr2_0, this.tpx1_0 });
        lineConfigurationData
                .add(new String[] { "2PH_H-2_ACSRx2_ACSR2_ACSR", this.acsr2, null, this.acsr2, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-2_WPALxx2_WPAL", this.wpal2, null, null, this.wpal2 });
        lineConfigurationData.add(new String[] { "1PH-2_ACSRxx4/0_TPX", this.acsr2, null, null, this.tpx4_0 });
        lineConfigurationData.add(new String[] { "3PH_H-397_ACSR397_ACSR397_ACSR4_WPAL", this.acsr397, this.acsr397,
                this.acsr397, this.wpal4 });
        lineConfigurationData.add(new String[] { "3PH_H-397_ACSR397_ACSR397_ACSR397_ACSR", this.acsr397, this.acsr397,
                this.acsr397, this.acsr397 });
        lineConfigurationData.add(new String[] { "1PH-xx2_ACSR1/0_TPX", null, null, this.acsr2, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx2_WPAL", null, this.acsr4, null, this.wpal2 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx4_DPX", null, this.acsr4, null, this.dpx4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-2_ACSR2_ACSR4_ACSR4_ACSR", this.acsr2, this.acsr2, this.acsr4, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-4_WPALxx1/0_TPX", this.wpal4, null, null, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx1/0_TPX", null, this.acsr4, null, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx1/0_3W_CS", this.acsr4, null, null, this.wcs1_0 });
        lineConfigurationData.add(new String[] { "1PH-x2_ACSRx4_ACSR", null, this.acsr2, null, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-x2_ACSRx1/0_3W_CS", null, this.acsr2, null, this.wcs1_0 });
        lineConfigurationData.add(new String[] { "3PH_H-2/0_ACSR2/0_ACSR2/0_ACSR2/0_ACSR", this.acsr2_0, this.acsr2_0,
                this.acsr2_0, this.acsr2_0 });
        lineConfigurationData.add(new String[] { "1PH-2_ACSRxx1/0_TPX", this.acsr2, null, null, this.tpx1_0 });
        lineConfigurationData.add(
                new String[] { "3PH_H-4_WPAL4_WPAL4_WPAL1/0_TPX", this.wpal4, this.wpal4, this.wpal4, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-x4_WPALx2_ACSR", null, this.wpal4, null, this.acsr2 });
        lineConfigurationData.add(new String[] { "3PH_H-2/0_ACSR2/0_ACSR2/0_ACSR2_WPAL", this.acsr2_0, this.acsr2_0,
                this.acsr2_0, this.wpal2 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR2_ACSR", null, null, this.acsr4, this.acsr2 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx4_TPX", null, this.acsr4, null, this.tpx4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR4_ACSR4_ACSR4_TPX", this.acsr4, this.acsr4, this.acsr4, this.tpx4 });
        lineConfigurationData.add(new String[] { "1PH-4_ACSRxx6_WPAL", this.acsr4, null, null, this.wpal6 });
        lineConfigurationData.add(new String[] { "1PH-xx4_ACSR4_TPX", null, null, this.acsr4, this.tpx4 });
        lineConfigurationData.add(new String[] { "3PH_H-397_ACSR397_ACSR397_ACSR2/0_WPAL", this.acsr397, this.acsr397,
                this.acsr397, this.wpal2_0 });
        lineConfigurationData.add(new String[] { "1PH-2/0_ACSRxx2_ACSR", this.acsr2_0, null, null, this.acsr2 });
        lineConfigurationData.add(new String[] { "3PH_H-2/0_ACSR2/0_ACSR2/0_ACSR4_ACSR", this.acsr2_0, this.acsr2_0,
                this.acsr2_0, this.acsr4 });
        lineConfigurationData.add(new String[] { "1PH-xx4_WPAL1/0_TPX", null, null, this.wpal4, this.tpx1_0 });
        lineConfigurationData.add(new String[] { "1PH-xx6_WPAL6_WPAL", null, null, this.wpal6, this.wpal6 });
        lineConfigurationData.add(new String[] { "1PH-x2_ACSRx4_TPX", null, this.acsr2, null, this.tpx4 });
        lineConfigurationData
                .add(new String[] { "3PH_H-4_ACSR4_ACSR4_ACSR2_WPAL", this.acsr4, this.acsr4, this.acsr4, this.wpal2 });
        lineConfigurationData.add(new String[] { "1PH-x4_ACSRx1/0_3W_CS", null, this.acsr4, null, this.wcs1_0 });
        lineConfigurationData.add(new String[] { "1PH-xx2_ACSR4_DPX", null, null, this.acsr2, this.dpx4 });
        lineConfigurationData
                .add(new String[] { "3P_1/0_AXNJ_DB", this.acsr1_0, this.acsr1_0, this.acsr1_0, this.acsr1_0 });
        lineConfigurationData.add(new String[] { "1P_1/0_AXNJ_DB_A", this.acsr1_0, null, null, this.acsr1_0 });
        lineConfigurationData.add(new String[] { "1P_1/0_AXNJ_DB_B", null, this.acsr1_0, null, this.acsr1_0 });
        lineConfigurationData.add(new String[] { "1P_1/0_AXNJ_DB_C", null, null, this.acsr1_0, this.acsr1_0 });
        lineConfigurationData.add(new String[] { "CAP_LINE", this.cu600, this.cu600, this.cu600, this.cu600 });
        lineConfigurationData.add(new String[] { "3PH-Connector", this.cu600, this.cu600, this.cu600, this.cu600 });

        for (String[] dataValue : lineConfigurationData) {
            StandardLineConfiguration<OverheadLineConductor> lineConfiguration = this.simulator
                    .overheadLineConfiguration(dataValue[0]);
            EnumSet<PhaseCode> phases = EnumSet.noneOf(PhaseCode.class);

            // set appropriate phases
            if (dataValue[1] != null) {
                lineConfiguration.setPhaseAConductor(this.overheadLineConductors.get(dataValue[1]));
                phases.add(PhaseCode.A);
            }

            if (dataValue[2] != null) {
                lineConfiguration.setPhaseBConductor(this.overheadLineConductors.get(dataValue[2]));
                phases.add(PhaseCode.B);
            }

            if (dataValue[3] != null) {
                lineConfiguration.setPhaseCConductor(this.overheadLineConductors.get(dataValue[3]));
                phases.add(PhaseCode.C);
            }

            if (dataValue[4] != null) {
                lineConfiguration.setPhaseNConductor(this.overheadLineConductors.get(dataValue[4]));
                phases.add(PhaseCode.N);
            }

            // get line spacing name
            String lineSpacingName = "";

            if (phases.equals(EnumSet.of(PhaseCode.A, PhaseCode.N))) {
                lineSpacingName = "SinglePhaseA";
            } else if (phases.equals(EnumSet.of(PhaseCode.B, PhaseCode.N))) {
                lineSpacingName = "SinglePhaseB";
            } else if (phases.equals(EnumSet.of(PhaseCode.C, PhaseCode.N))) {
                lineSpacingName = "SinglePhaseC";
            } else if (phases.equals(EnumSet.of(PhaseCode.A, PhaseCode.B, PhaseCode.N))) {
                lineSpacingName = "TwoPhaseAB";
            } else if (phases.equals(EnumSet.of(PhaseCode.A, PhaseCode.C, PhaseCode.N))) {
                lineSpacingName = "TwoPhaseAC";
            } else if (phases.equals(EnumSet.of(PhaseCode.B, PhaseCode.C, PhaseCode.N))) {
                lineSpacingName = "TwoPhaseBC";
            } else if (phases.equals(PhaseCode.ABCN)) {
                lineSpacingName = "ThreePhaseABC";
            }

            lineConfiguration.setSpacing(this.lineSpacings.get(lineSpacingName));
            this.lineConfigurations.put(dataValue[0], lineConfiguration);
        }
    }

    private void createNodes() {
        String stringFormat = "node_%s";
        Double nominalVoltage = 7199.558;

        if (!this.lineCsv.isEmpty()) {
            for (int i = 0; i < this.lineCsv.size(); i++) {
                String[] lineAttributes = this.lineCsv.get(i).split(",");
                EnumSet<PhaseCode> phases = EnumSet.of(PhaseCode.N);

                if (lineAttributes[2].contains("A")) {
                    phases.add(PhaseCode.A);
                }

                if (lineAttributes[2].contains("B")) {
                    phases.add(PhaseCode.B);
                }

                if (lineAttributes[2].contains("C")) {
                    phases.add(PhaseCode.C);
                }

                // from nodes
                if (!this.nodes.containsKey(lineAttributes[1])) {
                    Node node = this.simulator.node(String.format(stringFormat, lineAttributes[1]));
                    node.setPhases(phases);
                    // TODO do I need ABC here? Not specified in
                    // IEEE_8500gener_whouses.m
                    // node.setVoltageA(2401.7771, 0);
                    // node.setVoltageB(-1200.8886, -2080);
                    // node.setVoltageC(-1200.8886, 2080);
                    node.setNominalVoltage(nominalVoltage);
                    this.nodes.put(lineAttributes[1], node);
                }

                // to nodes
                if (!this.nodes.containsKey(lineAttributes[3])) {
                    Node node = this.simulator.node(String.format(stringFormat, lineAttributes[3]));
                    node.setPhases(phases);
                    node.setNominalVoltage(nominalVoltage);
                    this.nodes.put(lineAttributes[3], node);
                }

                // other nodes
                String nodeName = "regxfmr_HVMV_Sub_LSB";
                Node node = this.simulator.node(String.format(stringFormat, nodeName));
                node.setPhases(PhaseCode.ABCN);
                node.setNominalVoltage(nominalVoltage);
                this.nodes.put(nodeName, node);

                String nodeName2 = "HVMV_Sub_HSB";
                Node node2 = this.simulator.node(String.format(stringFormat, nodeName2));
                node2.setPhases(PhaseCode.ABCN);
                node2.setBusType(BusType.SWING);
                node2.setVoltageA(69512, -.7);
                node2.setVoltageB(69557, -120.7);
                node2.setVoltageC(69595, 119.3);
                node2.setNominalVoltage(69512.0);
                this.nodes.put(nodeName2, node2);
            }
        }
    }

    private void createTriplexNodes() {
        String stringFormat = "triplex_node_%s";
        int busColumn = 1;
        int phaseColumn = 3;

        for (int i = 0; i < this.triplexLineCsv.size(); i++) {
            String[] lineAttributes = this.triplexLineCsv.get(i).split(",");
            EnumSet<PhaseCode> phases = PhaseCode.S;

            if (lineAttributes[phaseColumn].endsWith("A")) {
                phases.add(PhaseCode.A);
            } else if (lineAttributes[phaseColumn].endsWith("B")) {
                phases.add(PhaseCode.B);
            } else if (lineAttributes[phaseColumn].endsWith("C")) {
                phases.add(PhaseCode.C);
            }

            TriplexNode node = this.simulator.triplexNode(String.format(stringFormat, lineAttributes[busColumn]));
            node.setPhases(phases);
            node.setNominalVoltage(120.0);
            this.nodes.put(lineAttributes[busColumn], node);
        }

        busColumn = 2;

        for (int i = 0; i < this.loadCsv.size(); i++) {
            String[] nodeAttributes = this.loadCsv.get(i).split(",");
            EnumSet<PhaseCode> phases = PhaseCode.S;

            if (nodeAttributes[busColumn].endsWith("A")) {
                phases.add(PhaseCode.A);
            } else if (nodeAttributes[busColumn].endsWith("B")) {
                phases.add(PhaseCode.B);
            } else if (nodeAttributes[busColumn].endsWith("C")) {
                phases.add(PhaseCode.C);
            }

            if (!this.nodes.containsKey(nodeAttributes[busColumn])) {
                // TODO should loads have power_1 and power_2 instance
                // variables?
                TriplexNode node = this.simulator.triplexNode(String.format(stringFormat, nodeAttributes[busColumn]));
                node.setPhases(phases);
                node.setNominalVoltage(120.0);
                this.nodes.put(nodeAttributes[busColumn], node);
            }
        }
    }

    private void createTransformerConfigurationAndTransformer() {
        this.transformerConfiguration = this.simulator.transformerConfiguration("transformer_configuration_1");
        this.transformerConfiguration.setConnectionType(ConnectionType.DELTA_GWYE);
        this.transformerConfiguration.setInstallationType(InstallationType.PADMOUNT);
        this.transformerConfiguration.setPowerRating(27500.0);
        this.transformerConfiguration.setPrimaryVoltage(115);
        this.transformerConfiguration.setSecondaryVoltage(12.47);
        // TODO are these needed?
        // this.transformerConfiguration.setResistance(.1551);
        // this.transformerConfiguration.setReactance(.01344);

        this.transformer = this.simulator.transformer("transformer_HVMV_Sub", this.transformerConfiguration);
        this.transformer.setPhases(PhaseCode.ABCN);
        this.transformer.setFrom(this.nodes.get("HVMV_Sub_HSB"));
        this.transformer.setTo(this.nodes.get("regxfmr_HVMV_Sub_LSB"));
    }

    private void createRegulatorConfigurations() {
        String stringFormat = "regulator_configuration_%d";

        int key1 = 1;
        RegulatorConfiguration regulatorConfiguration1 = this.simulator
                .regulatorConfiguration(String.format(stringFormat, key1));
        regulatorConfiguration1.setBandCenter(125);
        regulatorConfiguration1.setTimeDelay(60);
        this.regulatorConfigurations.put(key1, regulatorConfiguration1);

        int key2 = 2;
        RegulatorConfiguration regulatorConfiguration2 = this.simulator
                .regulatorConfiguration(String.format(stringFormat, key2));
        regulatorConfiguration2.setBandCenter(374 / 3.0);
        regulatorConfiguration2.setTimeDelay(120);
        this.regulatorConfigurations.put(key2, regulatorConfiguration2);

        int key3 = 3;
        RegulatorConfiguration regulatorConfiguration3 = this.simulator
                .regulatorConfiguration(String.format(stringFormat, key3));
        regulatorConfiguration3.setBandCenter(374 / 3.0);
        regulatorConfiguration3.setTimeDelay(75);
        this.regulatorConfigurations.put(key3, regulatorConfiguration3);

        int key4 = 4;
        RegulatorConfiguration regulatorConfiguration4 = this.simulator
                .regulatorConfiguration(String.format(stringFormat, key4));
        regulatorConfiguration4.setBandCenter(125);
        regulatorConfiguration4.setTimeDelay(90);
        this.regulatorConfigurations.put(key4, regulatorConfiguration4);

        // set variables that are the same for all regulator configurations
        for (RegulatorConfiguration regulator : this.regulatorConfigurations.values()) {
            regulator.setConnectionType(ConnectionType.WYE_WYE);
            regulator.setBandWidth(2);
            regulator.setRegulatorControl(RegulatorControl.LINE_DROP_COMP);
            regulator.setCurrentTransducerRatio(0);
            regulator.setPowerTransducerRatio(60);
            regulator.setCompensatorRSettings(new double[] { 0, 0, 0 });
            regulator.setCompensatorXSettings(new double[] { 0, 0, 0 });
            regulator.setRaiseTaps(16);
            regulator.setLowerTaps(16);
            regulator.setRegulation(.1);
            regulator.setRegulatorType(RegulatorType.B);
        }
    }

    private void createRegulators() {
        String stringFormat = "regulator_%d";

        int key1 = 1;
        Regulator regulator1 = this.simulator.regulator(String.format(stringFormat, key1),
                this.regulatorConfigurations.get(key1));
        regulator1.setPhases(PhaseCode.ABCN);
        regulator1.setFrom(this.nodes.get("regxfmr_HVMV_Sub_LSB"));
        regulator1.setTo(this.nodes.get("_HVMV_Sub_LSB"));
        this.regulators.put(key1, regulator1);

        int key2 = 2;
        Regulator regulator2 = this.simulator.regulator(String.format(stringFormat, key2),
                this.regulatorConfigurations.get(key2));
        regulator2.setPhases(PhaseCode.ABCN);
        regulator2.setFrom(this.nodes.get("regxfmr_190-8593"));
        regulator2.setTo(this.nodes.get("190-8593"));
        this.regulators.put(key2, regulator2);

        int key3 = 3;
        Regulator regulator3 = this.simulator.regulator(String.format(stringFormat, key3),
                this.regulatorConfigurations.get(key3));
        regulator3.setPhases(PhaseCode.ABCN);
        regulator3.setFrom(this.nodes.get("regxfmr_190-8581"));
        regulator3.setTo(this.nodes.get("190-8581"));
        this.regulators.put(key3, regulator3);

        int key4 = 4;
        Regulator regulator4 = this.simulator.regulator(String.format(stringFormat, key4),
                this.regulatorConfigurations.get(key4));
        regulator4.setPhases(PhaseCode.ABCN);
        regulator4.setFrom(this.nodes.get("regxfmr_190-7361"));
        regulator4.setTo(this.nodes.get("190-7361"));
        this.regulators.put(key4, regulator4);
    }

    private void createOverheadLines() {
        String stringFormat = "overhead_line_%s";

        if (!this.lineCsv.isEmpty()) {
            for (int i = 0; i < this.lineCsv.size(); i++) {
                String[] lineAttributes = this.lineCsv.get(i).split(",");

                if (!(lineAttributes[0].contains("CAP") || lineAttributes[0].contains("_sw"))) {
                    EnumSet<PhaseCode> phases = EnumSet.of(PhaseCode.N);

                    if (lineAttributes[2].contains("A")) {
                        phases.add(PhaseCode.A);
                    }

                    if (lineAttributes[2].contains("B")) {
                        phases.add(PhaseCode.B);
                    }

                    if (lineAttributes[2].contains("C")) {
                        phases.add(PhaseCode.C);
                    }

                    if (lineAttributes[7].equals("1P_1/0_AXNJ_DB")) {
                        OverheadLine overheadLine = this.simulator.overheadLine(
                                String.format(stringFormat, lineAttributes[0]), phases,
                                this.nodes.get(lineAttributes[1]), this.nodes.get(lineAttributes[3]),
                                Double.parseDouble(lineAttributes[5]),
                                (StandardLineConfiguration<OverheadLineConductor>) this.lineConfigurations
                                        .get(lineAttributes[7] + "_" + lineAttributes[2]));
                        this.overheadLines.put(i, overheadLine);
                    } else {
                        OverheadLine overheadLine = this.simulator.overheadLine(
                                String.format(stringFormat, lineAttributes[0]), phases,
                                this.nodes.get(lineAttributes[1]), this.nodes.get(lineAttributes[3]),
                                Double.parseDouble(lineAttributes[5]),
                                (StandardLineConfiguration<OverheadLineConductor>) this.lineConfigurations
                                        .get(lineAttributes[7]));
                        this.overheadLines.put(i, overheadLine);
                    }
                }
            }

            // additional capacitor lines
            this.overheadLines.put(this.lineCsv.size(),
                    this.simulator.overheadLine(String.format(stringFormat, "CAP_1"), PhaseCode.ABCN,
                            this.nodes.get("Q16642"),
                            this.nodes
                                    .get("Q16642_CAP"),
                            .01, (StandardLineConfiguration<OverheadLineConductor>) this.lineConfigurations
                                    .get("CAP_LINE")));

            this.overheadLines.put(this.lineCsv.size() + 1,
                    this.simulator.overheadLine(String.format(stringFormat, "CAP_2"), PhaseCode.ABCN,
                            this.nodes.get("Q16483"),
                            this.nodes
                                    .get("Q16483_CAP"),
                            .001, (StandardLineConfiguration<OverheadLineConductor>) this.lineConfigurations
                                    .get("CAP_LINE")));

            this.overheadLines.put(this.lineCsv.size() + 2,
                    this.simulator.overheadLine(String.format(stringFormat, "CAP_3"), PhaseCode.ABCN,
                            this.nodes.get("L2823592"),
                            this.nodes
                                    .get("L2823592_CAP"),
                            .01, (StandardLineConfiguration<OverheadLineConductor>) this.lineConfigurations
                                    .get("CAP_LINE")));
        }
    }

    private void createSwitches() {
        String stringFormat = "switch_%s";

        for (int i = 0; i < this.lineCsv.size(); i++) {
            String[] lineAttributes = this.lineCsv.get(i).split(",");

            if (lineAttributes[0].contains("_sw") && !(lineAttributes[0].contains("WF586_48332_sw")
                    || lineAttributes[0].contains("V7995_48332_sw") || lineAttributes[0].contains("WD701_48332_sw"))) {
                String name = String.format(stringFormat, lineAttributes[0]);
                EnumSet<PhaseCode> phases = EnumSet.of(PhaseCode.N);
                SwitchStatus status = SwitchStatus.CLOSED;

                if (lineAttributes[2].contains("A")) {
                    phases.add(PhaseCode.A);
                }

                if (lineAttributes[2].contains("B")) {
                    phases.add(PhaseCode.B);
                }

                if (lineAttributes[2].contains("C")) {
                    phases.add(PhaseCode.C);
                }

                if (lineAttributes.length > 8 && lineAttributes[8].equalsIgnoreCase("open")) {
                    status = SwitchStatus.OPEN;
                }

                this.switches.put(name, this.simulator.switchLinkObject(name, phases, this.nodes.get(lineAttributes[1]),
                        this.nodes.get(lineAttributes[3]), status));
            }
        }
    }

    private void createTriplexLines() {
        // triplex line conductor
        TriplexLineConductor triplexLineConductor = this.simulator.triplexLineConductor("4/0triplex");
        triplexLineConductor.setResistance(1.535);
        triplexLineConductor.setGeometricMeanRadius(.0111);
        // TODO IEEE_8500gener_whouses.m defines rating not currently in
        // TriplexLineConductor

        // triplex line configurations
        Map<String, TriplexLineConfiguration> triplexConfigurations = new HashMap<String, TriplexLineConfiguration>();

        String triplexConfiguration1Name = "4/0Triplex";
        TriplexLineConfiguration triplexConfiguration1 = this.simulator
                .triplexLineConfiguration(triplexConfiguration1Name);
        triplexConfiguration1.setPhase1Conductor(triplexLineConductor);
        triplexConfiguration1.setPhase2Conductor(triplexLineConductor);
        triplexConfiguration1.setPhaseNConductor(triplexLineConductor);
        triplexConfiguration1.setInsulationThickness(.08);
        triplexConfiguration1.setDiameter(.368);
        triplexConfigurations.put(triplexConfiguration1Name, triplexConfiguration1);

        String triplexConfiguration2Name = "750_Triplex";
        TriplexLineConfiguration triplexConfiguration2 = this.simulator
                .triplexLineConfiguration(triplexConfiguration2Name);
        triplexConfiguration2.setPhase1Conductor(triplexLineConductor);
        triplexConfiguration2.setPhase2Conductor(triplexLineConductor);
        triplexConfiguration2.setPhaseNConductor(triplexLineConductor);
        triplexConfiguration2.setInsulationThickness(.08);
        triplexConfiguration2.setDiameter(.368);
        triplexConfigurations.put(triplexConfiguration2Name, triplexConfiguration2);

        // triplex lines
        for (int i = 0; i < this.triplexLineCsv.size(); i++) {
            String[] lineAttributes = this.triplexLineCsv.get(i).split(",");
            EnumSet<PhaseCode> phases = PhaseCode.S;

            if (lineAttributes[3].endsWith("A")) {
                phases.add(PhaseCode.A);
            } else if (lineAttributes[3].endsWith("B")) {
                phases.add(PhaseCode.B);
            } else if (lineAttributes[3].endsWith("C")) {
                phases.add(PhaseCode.C);
            }

            TriplexLine triplexLine = this.simulator.triplexLine(lineAttributes[0]);
            triplexLine.setPhases(phases);
            triplexLine.setFrom(this.nodes.get(lineAttributes[1]));
            triplexLine.setTo(this.nodes.get(lineAttributes[3]));

            if (this.useHousesInsteadOfLoads) {
                triplexLine.setLength(25 - 20 * Math.random());
            } else {
                triplexLine.setLength(Double.parseDouble(lineAttributes[6]));
            }

            triplexLine.setConfiguration(triplexConfigurations.get(lineAttributes[5]));
        }
    }

    private void readCsv(List<String> csvStorage, String fileName, int numberOfLinesToIgnore) {
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader(Paths.get("res/" + fileName).toString()));

            String[] linesOfCsv = csvReader.lines().toArray(String[]::new);

            //trim and remove empty lines
            for (String line : linesOfCsv) {
                String trimmedLine = line.replaceAll("\\s+", "");

                if (!trimmedLine.isEmpty()) {
                    csvStorage.add(trimmedLine);
                }
            }

            //remove header lines
            for (int i = 0; i < numberOfLinesToIgnore; i++) {
                csvStorage.remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return;
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
