/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
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
 *******************************************************************************/
package gov.pnnl.prosser.api.gld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.complex.Complex;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.BusType;
import gov.pnnl.prosser.api.gld.enums.CapacitorControl;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.ControlLevel;
import gov.pnnl.prosser.api.gld.enums.InstallationType;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.RegulatorControl;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
import gov.pnnl.prosser.api.gld.lib.Conductor;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConductor;
import gov.pnnl.prosser.api.gld.lib.TriplexLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.UndergroundLineConductor;
import gov.pnnl.prosser.api.gld.obj.Capacitor;
import gov.pnnl.prosser.api.gld.obj.Fuse;
import gov.pnnl.prosser.api.gld.obj.Line;
import gov.pnnl.prosser.api.gld.obj.LinkObject;
import gov.pnnl.prosser.api.gld.obj.Load;
import gov.pnnl.prosser.api.gld.obj.Meter;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.Recorder;
import gov.pnnl.prosser.api.gld.obj.Regulator;
import gov.pnnl.prosser.api.gld.obj.Switch;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.TriplexLine;
import gov.pnnl.prosser.api.gld.obj.TriplexMeter;
import gov.pnnl.prosser.api.gld.obj.TriplexNode;
import gov.pnnl.prosser.api.gld.obj.UndergroundLine;

/**
 * @author sund130
 *
 */
public class GlmParser {
    private GldSimulator simulator;
    private int region;
    private String name;
    private boolean useHousesInsteadOfLoads;

    private Map<String, OverheadLineConductor> overheadLineConductors;
    private Map<String, UndergroundLineConductor> undergroundLineConductors;
    private Map<String, LineSpacing> lineSpacings;
    private Map<String, StandardLineConfiguration<UndergroundLineConductor>> undergroundLineConfigurations;
    private Map<String, StandardLineConfiguration<OverheadLineConductor>> overheadLineConfigurations;
    private Map<String, Node> nodes;
    private Map<String, TransformerConfiguration> transformerConfigurations;
    private Map<String, Transformer> transformers;
    private Map<String, RegulatorConfiguration> regulatorConfigurations;
    private Map<String, Regulator> regulators;
    private Map<String, Switch> switches;
    private Map<String, TriplexLineConductor> triplexLineConductors;
    private Map<String, TriplexLineConfiguration> triplexLineConfigurations;
    private Map<String, TriplexLine> triplexLines;
    private Map<String, UndergroundLine> undergroundLines;
    private Map<String, OverheadLine> overheadLines;
    private Map<String, Recorder> recorders;
    private Map<String, Capacitor> capacitors;
    private Map<String, Fuse> fuses;

    public GlmParser(GldSimulator simulator, String name, int region, boolean useHousesInsteadOfLoads)
    {
        this.simulator = simulator;
        this.name = name;
        this.region = region;
        this.useHousesInsteadOfLoads = useHousesInsteadOfLoads;

        this.overheadLineConductors = new HashMap<String, OverheadLineConductor>();
        this.undergroundLineConductors = new HashMap<String, UndergroundLineConductor>();
        this.lineSpacings = new HashMap<String, LineSpacing>();
        this.undergroundLineConfigurations = new HashMap<String, StandardLineConfiguration<UndergroundLineConductor>>();
        this.overheadLineConfigurations = new HashMap<String, StandardLineConfiguration<OverheadLineConductor>>();
        this.nodes = new HashMap<String, Node>();
        this.transformerConfigurations = new HashMap<String, TransformerConfiguration>();
        this.transformers = new HashMap<String, Transformer>();
        this.regulatorConfigurations = new HashMap<String, RegulatorConfiguration>();
        this.regulators = new HashMap<String, Regulator>();
        this.switches = new HashMap<String, Switch>();
        this.triplexLineConductors = new HashMap<String, TriplexLineConductor>();
        this.triplexLineConfigurations = new HashMap<String, TriplexLineConfiguration>();
        this.triplexLines = new HashMap<String, TriplexLine>();
        this.undergroundLines = new HashMap<String, UndergroundLine>();
        this.overheadLines = new HashMap<String, OverheadLine>();
        this.recorders = new HashMap<String, Recorder>();
        this.capacitors = new HashMap<String, Capacitor>();
        this.fuses = new HashMap<String, Fuse>();
    }

    /**
     * @return the simulator
     */
    public GldSimulator getSimulator() {
        return simulator;
    }

    /**
     * @return the region
     */
    public int getRegion() {
        return region;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the useHousesInsteadOfLoads
     */
    public boolean doesUseHousesInsteadOfLoads() {
        return useHousesInsteadOfLoads;
    }

    public void createFeeder(String glmFileName) {
        BufferedReader glmReader = null;

        try {
            glmReader = new BufferedReader(new FileReader(glmFileName));

            String[] glmLines = glmReader.lines().toArray(String[]::new);

            //make objects without relationships
            for (int i = 0; i < glmLines.length; i++) {
                String currentLine = glmLines[i];

                if (currentLine.trim().startsWith("object")) {
                    //make object
                    if (currentLine.contains("regulator_configuration")) {
                        this.createRegulatorConfiguration(glmLines, i);
                    }
                    else if (currentLine.contains("regulator")) {
                        this.createRegulator(glmLines, i);
                    }
                    else if (currentLine.contains("capacitor")) {
                        this.createCapacitor(glmLines, i);
                    }
                    else if (currentLine.contains("fuse")) {
                        this.createFuse(glmLines, i);
                    }
                    else if (currentLine.contains("triplex_line_configuration")) {
                        this.createTriplexLineConfiguration(glmLines, i);
                    }
                    else if (currentLine.contains("line_configuration")) {
                        this.createLineConfiguration(glmLines, i);
                    }
                    else if (currentLine.contains("line_spacing")) {
                        this.createLineSpacing(glmLines, i);
                    }
                    else if (currentLine.contains("load")) {
                        this.createLoad(glmLines, i);
                    }
                    else if (currentLine.contains("meter")) {
                        this.createMeter(glmLines, i);
                    }
                    else if (currentLine.contains("triplex_meter")) {
                        this.createTriplexMeter(glmLines, i);
                    }
                    else if (currentLine.contains("node")) {
                        this.createNode(glmLines, i);
                    }
                    else if (currentLine.contains("triplex_node")) {
                        this.createTriplexNode(glmLines, i);
                    }
                    else if (currentLine.contains("switch")) {
                        this.createSwitch(glmLines, i);
                    }
                    else if (currentLine.contains("transformer_configuration")) {
                        this.createTransformerConfiguration(glmLines, i);
                    }
                    else if (currentLine.contains("transformer")) {
                        this.createTransformer(glmLines, i);
                    }
                    else if (currentLine.contains("triplex_line_conductor")) {
                        this.createTriplexLineConductor(glmLines, i);
                    }
                    else if (currentLine.contains("underground_line_conductor")) {
                        this.createUndergroundLineConductor(glmLines, i);
                    }
                    else if (currentLine.contains("overhead_line_conductor")) {
                        this.createOverheadLineConductor(glmLines, i);
                    }
                    else if (currentLine.contains("underground_line") || currentLine.contains("overhead_line") || currentLine.contains("triplex_line")) {
                        this.createLine(glmLines, i);
                    }
                    else if (currentLine.contains("recorder")) {
                        this.createRecorder(glmLines, i);
                    }
                }
            }

            //connect related objects
            for (Regulator regulator : this.regulators.values()) {
                if (regulator.getRegulatorConfiguration() != null && this.regulatorConfigurations.containsKey(regulator.getRegulatorConfiguration().getName())) {
                    regulator.setRegulatorConfiguration(this.regulatorConfigurations.get(regulator.getRegulatorConfiguration().getName()));
                }
            }

            for (Capacitor capacitor : this.capacitors.values()) {
                if (capacitor.getParent() != null && this.nodes.containsKey(capacitor.getParent().getName())) {
                    capacitor.setParent(this.nodes.get(capacitor.getParent().getName()));
                }
            }

            for (Fuse fuse : this.fuses.values()) {
                if (fuse.getFrom() != null && this.nodes.containsKey(fuse.getFrom().getName())) {
                    fuse.setFrom(this.nodes.get(fuse.getFrom().getName()));
                }

                if (fuse.getTo() != null && this.nodes.containsKey(fuse.getTo().getName())) {
                    fuse.setTo(this.nodes.get(fuse.getTo().getName()));
                }
            }

            for (StandardLineConfiguration<UndergroundLineConductor> lineConfiguration : this.undergroundLineConfigurations.values()) {
                if (lineConfiguration.getPhaseAConductor() != null && this.undergroundLineConductors.containsKey(lineConfiguration.getPhaseAConductor().getName())) {
                    lineConfiguration.setPhaseAConductor(this.undergroundLineConductors.get(lineConfiguration.getPhaseAConductor().getName()));
                }

                if (lineConfiguration.getPhaseBConductor() != null && this.undergroundLineConductors.containsKey(lineConfiguration.getPhaseBConductor().getName())) {
                    lineConfiguration.setPhaseBConductor(this.undergroundLineConductors.get(lineConfiguration.getPhaseBConductor().getName()));
                }

                if (lineConfiguration.getPhaseCConductor() != null && this.undergroundLineConductors.containsKey(lineConfiguration.getPhaseCConductor().getName())) {
                    lineConfiguration.setPhaseCConductor(this.undergroundLineConductors.get(lineConfiguration.getPhaseCConductor().getName()));
                }

                if (lineConfiguration.getSpacing() != null && this.lineSpacings.containsKey(lineConfiguration.getSpacing().getName())) {
                    lineConfiguration.setSpacing(this.lineSpacings.get(lineConfiguration.getSpacing().getName()));
                }
            }

            for (StandardLineConfiguration<OverheadLineConductor> lineConfiguration : this.overheadLineConfigurations.values()) {
                if (lineConfiguration.getPhaseAConductor() != null && this.overheadLineConductors.containsKey(lineConfiguration.getPhaseAConductor().getName())) {
                    lineConfiguration.setPhaseAConductor(this.overheadLineConductors.get(lineConfiguration.getPhaseAConductor().getName()));
                }

                if (lineConfiguration.getPhaseBConductor() != null && this.overheadLineConductors.containsKey(lineConfiguration.getPhaseBConductor().getName())) {
                    lineConfiguration.setPhaseBConductor(this.overheadLineConductors.get(lineConfiguration.getPhaseBConductor().getName()));
                }

                if (lineConfiguration.getPhaseCConductor() != null && this.overheadLineConductors.containsKey(lineConfiguration.getPhaseCConductor().getName())) {
                    lineConfiguration.setPhaseCConductor(this.overheadLineConductors.get(lineConfiguration.getPhaseCConductor().getName()));
                }

                if (lineConfiguration.getSpacing() != null && this.lineSpacings.containsKey(lineConfiguration.getSpacing().getName())) {
                    lineConfiguration.setSpacing(this.lineSpacings.get(lineConfiguration.getSpacing().getName()));
                }
            }

            for (Node node : this.nodes.values()) {
                if (node.getParent() != null && this.nodes.containsKey(node.getParent().getName())) {
                    node.setParent(this.nodes.get(node.getParent().getName()));
                }
            }

            for (Switch switchObject : this.switches.values()) {
                if (switchObject.getFrom() != null && this.nodes.containsKey(switchObject.getFrom().getName())) {
                    switchObject.setFrom(this.nodes.get(switchObject.getFrom().getName()));
                }

                if (switchObject.getTo() != null && this.nodes.containsKey(switchObject.getTo().getName())) {
                    switchObject.setTo(this.nodes.get(switchObject.getTo().getName()));
                }
            }

            for (Transformer transformer : this.transformers.values()) {
                if (transformer.getFrom() != null && this.nodes.containsKey(transformer.getFrom().getName())) {
                    transformer.setFrom(this.nodes.get(transformer.getFrom().getName()));
                }

                if (transformer.getTo() != null && this.nodes.containsKey(transformer.getTo().getName())) {
                    transformer.setTo(this.nodes.get(transformer.getTo().getName()));
                }

                if (transformer.getConfiguration() != null && this.transformerConfigurations.containsKey(transformer.getConfiguration().getName())) {
                    transformer.setConfiguration(this.transformerConfigurations.get(transformer.getConfiguration().getName()));
                }
            }

            for (TriplexLine line : this.triplexLines.values()) {
                if (line.getFrom() != null && this.nodes.containsKey(line.getFrom().getName())) {
                    line.setFrom(this.nodes.get(line.getFrom().getName()));
                }

                if (line.getTo() != null && this.nodes.containsKey(line.getTo().getName())) {
                    line.setTo(this.nodes.get(line.getTo().getName()));
                }

                if (line.getConfiguration() != null && this.triplexLineConfigurations.containsKey(line.getConfiguration().getName())) {
                    line.setConfiguration(this.triplexLineConfigurations.get(line.getConfiguration().getName()));
                }
            }

            for (UndergroundLine line : this.undergroundLines.values()) {
                if (line.getFrom() != null && this.nodes.containsKey(line.getFrom().getName())) {
                    line.setFrom(this.nodes.get(line.getFrom().getName()));
                }

                if (line.getTo() != null && this.nodes.containsKey(line.getTo().getName())) {
                    line.setTo(this.nodes.get(line.getTo().getName()));
                }

                if (line.getConfiguration() != null && this.undergroundLineConfigurations.containsKey(line.getConfiguration().getName())) {
                    line.setConfiguration((StandardLineConfiguration<UndergroundLineConductor>)this.undergroundLineConfigurations.get(line.getConfiguration().getName()));
                }
            }

            for (OverheadLine line : this.overheadLines.values()) {
                if (line.getFrom() != null && this.nodes.containsKey(line.getFrom().getName())) {
                    line.setFrom(this.nodes.get(line.getFrom().getName()));
                }

                if (line.getTo() != null && this.nodes.containsKey(line.getTo().getName())) {
                    line.setTo(this.nodes.get(line.getTo().getName()));
                }

                if (line.getConfiguration() != null && this.overheadLineConfigurations.containsKey(line.getConfiguration().getName())) {
                    line.setConfiguration((StandardLineConfiguration<OverheadLineConductor>)this.overheadLineConfigurations.get(line.getConfiguration().getName()));
                }
            }

            for (Recorder recorder : this.recorders.values()) {
                //check regulators for parent
                Regulator regulatorParent = this.regulators.get(recorder.getParent().getName());

                if (regulatorParent != null) {
                    recorder.setParent(regulatorParent);
                }
                else {
                    //check capacitors for parent
                    Capacitor capacitorParent = this.capacitors.get(recorder.getParent().getName());

                    if (capacitorParent != null) {
                        recorder.setParent(capacitorParent);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (glmReader != null) {
                try {
                    glmReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Creation functions

    private void createRegulator(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> regulatorLines = new ArrayList<String>();
        String configurationName = "";

        //saving regulator lines, pulling out configuration name
        //as it is needed for regulator constructor
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                if (glmLines[i].trim().startsWith("configuration")) {
                    configurationName = this.propertyValue(glmLines[i], "configuration");
                }
                else
                {
                    regulatorLines.add(glmLines[i]);
                }
            }
            else {
                break;
            }
        }

        //make temp regulator configuration
        RegulatorConfiguration regulatorConfigurationPlaceholder = new RegulatorConfiguration(simulator);
        regulatorConfigurationPlaceholder.setName(configurationName);

        //make regulator
        Regulator regulator = this.simulator.regulator(id, regulatorConfigurationPlaceholder);

        this.setLinkObjectProperties(regulator, regulatorLines);

        this.regulators.put(regulator.getName(), regulator);
    }

    private void createRegulatorConfiguration(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> regulatorConfigurationLines = new ArrayList<String>();

        //save regulator configuration lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                regulatorConfigurationLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make regulator configuration
        RegulatorConfiguration regulatorConfiguration = new RegulatorConfiguration(simulator);
        regulatorConfiguration.setName(id);

        //set regulator configuration properties
        for (int j = 0; j < regulatorConfigurationLines.size(); j++) {
            if (regulatorConfigurationLines.get(j).trim().startsWith("name")) {
                String name = this.propertyValue(regulatorConfigurationLines.get(j), "name");

                regulatorConfiguration.setName(name);
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("Control")) {
                String control = this.propertyValue(regulatorConfigurationLines.get(j), "Control");

                regulatorConfiguration.setRegulatorControl(Enum.valueOf(RegulatorControl.class, control));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("band_center")) {
                String bandCenter = this.propertyValue(regulatorConfigurationLines.get(j), "band_center");

                regulatorConfiguration.setBandCenter(Double.parseDouble(bandCenter));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("band_width")) {
                String bandWidth = this.propertyValue(regulatorConfigurationLines.get(j), "band_width");

                regulatorConfiguration.setBandWidth(Double.parseDouble(bandWidth));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("connect_type")) {
                String connectType = this.propertyValue(regulatorConfigurationLines.get(j), "connect_type");

                regulatorConfiguration.setConnectionType(Enum.valueOf(ConnectionType.class, connectType));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("time_delay")) {
                String timeDelay = this.propertyValue(regulatorConfigurationLines.get(j), "time_delay");

                regulatorConfiguration.setTimeDelay(Double.parseDouble(timeDelay));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("raise_taps")) {
                String raiseTaps = this.propertyValue(regulatorConfigurationLines.get(j), "raise_taps");

                regulatorConfiguration.setRaiseTaps((int)Double.parseDouble(raiseTaps));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("lower_taps")) {
                String lowerTaps = this.propertyValue(regulatorConfigurationLines.get(j), "lower_taps");

                regulatorConfiguration.setLowerTaps((int)Double.parseDouble(lowerTaps));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("regulation")) {
                String regulation = this.propertyValue(regulatorConfigurationLines.get(j), "regulation");

                regulatorConfiguration.setRegulation(Double.parseDouble(regulation));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("tap_pos_A")) {
                String tapA = this.propertyValue(regulatorConfigurationLines.get(j), "tap_pos_A");

                regulatorConfiguration.setTapPositionA((int)Double.parseDouble(tapA));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("tap_pos_B")) {
                String tapB = this.propertyValue(regulatorConfigurationLines.get(j), "tap_pos_B");

                regulatorConfiguration.setTapPositionB((int)Double.parseDouble(tapB));
            }
            else if (regulatorConfigurationLines.get(j).trim().startsWith("tap_pos_C")) {
                String tapC = this.propertyValue(regulatorConfigurationLines.get(j), "tap_pos_C");

                regulatorConfiguration.setTapPositionC((int)Double.parseDouble(tapC));
            }
        }

        this.regulatorConfigurations.put(regulatorConfiguration.getName(), regulatorConfiguration);
    }

    private void createCapacitor(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> capacitorLines = new ArrayList<String>();

        //save capacitor lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                capacitorLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make capacitor
        Capacitor capacitor = new Capacitor(simulator);
        capacitor.setName(id);

        //set capacitor properties
        for (int j = 0; j < capacitorLines.size(); j++) {
            if (capacitorLines.get(j).trim().startsWith("name")) {
                String name = this.propertyValue(capacitorLines.get(j), "name");

                capacitor.setName(name);
            }
            else if (capacitorLines.get(j).trim().startsWith("phases_connected")) {
                capacitor.setPhasesConnected(this.getPhases(capacitorLines.get(j), "phases_connected"));
            }
            else if (capacitorLines.get(j).trim().startsWith("phases")) {
                capacitor.setPhases(this.getPhases(capacitorLines.get(j), "phases"));
            }
            else if (capacitorLines.get(j).trim().startsWith("pt_phase")) {
                capacitor.setPtPhase(this.getPhases(capacitorLines.get(j), "pt_phases"));
            }
            else if (capacitorLines.get(j).trim().startsWith("parent")) {
                String parentName = this.propertyValue(capacitorLines.get(j), "parent");

                Node parent = new Node(this.simulator);
                parent.setName(parentName);

                capacitor.setParent(parent);
            }
            else if (capacitorLines.get(j).trim().startsWith("control_level")) {
                String controlLevel = this.propertyValue(capacitorLines.get(j), "control_level");

                capacitor.setControlLevel(Enum.valueOf(ControlLevel.class, controlLevel));
            }
            else if (capacitorLines.get(j).trim().startsWith("control")) {
                String control = this.propertyValue(capacitorLines.get(j), "control");

                capacitor.setControl(Enum.valueOf(CapacitorControl.class, control));
            }
            else if (capacitorLines.get(j).trim().startsWith("voltage_set_high")) {
                String high = this.propertyValue(capacitorLines.get(j), "voltage_set_high");

                capacitor.setVoltageSetHigh(Double.parseDouble(high));
            }
            else if (capacitorLines.get(j).trim().startsWith("voltage_set_low")) {
                String low = this.propertyValue(capacitorLines.get(j), "voltage_set_low");

                capacitor.setVoltageSetLow(Double.parseDouble(low));
            }
            else if (capacitorLines.get(j).trim().startsWith("capacitor_A")) {
                String capacitorA = this.propertyValue(capacitorLines.get(j), "capacitor_A");

                capacitor.setCapacitorA(Double.parseDouble(capacitorA));
            }
            else if (capacitorLines.get(j).trim().startsWith("capacitor_B")) {
                String capacitorB = this.propertyValue(capacitorLines.get(j), "capacitor_B");

                capacitor.setCapacitorB(Double.parseDouble(capacitorB));
            }
            else if (capacitorLines.get(j).trim().startsWith("capacitor_C")) {
                String capacitorC = this.propertyValue(capacitorLines.get(j), "capacitor_C");

                capacitor.setCapacitorC(Double.parseDouble(capacitorC));
            }
            else if (capacitorLines.get(j).trim().startsWith("switchA")) {
                String switchA = this.propertyValue(capacitorLines.get(j), "switchA");

                capacitor.setSwitchStatusA(Enum.valueOf(SwitchStatus.class, switchA));
            }
            else if (capacitorLines.get(j).trim().startsWith("switchB")) {
                String switchB = this.propertyValue(capacitorLines.get(j), "switchB");

                capacitor.setSwitchStatusB(Enum.valueOf(SwitchStatus.class, switchB));
            }
            else if (capacitorLines.get(j).trim().startsWith("switchC")) {
                String switchC = this.propertyValue(capacitorLines.get(j), "switchC");

                capacitor.setSwitchStatusC(Enum.valueOf(SwitchStatus.class, switchC));
            }
            else if (capacitorLines.get(j).trim().startsWith("time_delay")) {
                String timeDelay = this.propertyValue(capacitorLines.get(j), "time_delay");

                capacitor.setTimeDelay(Double.parseDouble(timeDelay));
            }
            else if (capacitorLines.get(j).trim().startsWith("dwell_time")) {
                String dwellTime = this.propertyValue(capacitorLines.get(j), "dwell_time");

                capacitor.setDwellTime(Double.parseDouble(dwellTime));
            }
            else if (capacitorLines.get(j).trim().startsWith("cap_nominal_voltage")) {
                String capNominalVoltage = this.propertyValue(capacitorLines.get(j), "cap_nominal_voltage");

                capacitor.setCapNominalVoltage(Double.parseDouble(capNominalVoltage));
            }

            else if (capacitorLines.get(j).trim().startsWith("nominal_voltage")) {
                String nominalVoltage = this.propertyValue(capacitorLines.get(j), "nominal_voltage");

                capacitor.setNominalVoltage(Double.parseDouble(nominalVoltage));
            }
        }

        this.capacitors.put(capacitor.getName(), capacitor);
    }

    private void createFuse(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> fuseLines = new ArrayList<String>();

        //save fuse lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                fuseLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make fuse
        Fuse fuse = new Fuse(simulator);
        fuse.setName(id);

        List<Integer> skippedLines = this.setLinkObjectProperties(fuse, fuseLines);

        //set properties
        for (int j = 0; j < skippedLines.size(); j++) {
            if (fuseLines.get(skippedLines.get(j)).trim().startsWith("current_limit")) {
                String currentLimit = this.propertyValue(fuseLines.get(skippedLines.get(j)), "current_limit");

                fuse.setCurrentLimit(Double.parseDouble(currentLimit));
            }
            else if (fuseLines.get(skippedLines.get(j)).trim().startsWith("status")) {
                String status = this.propertyValue(fuseLines.get(skippedLines.get(j)), "status");

                fuse.setStatus(Enum.valueOf(SwitchStatus.class, status));
            }
        }

        this.fuses.put(fuse.getName(), fuse);
    }

    private void createLineConfiguration(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> lineConfigurationLines = new ArrayList<String>();
        boolean isOverheadLineConfiguration = true;

        //save line configuration lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                lineConfigurationLines.add(glmLines[i]);

                if (glmLines[i].contains("underground")) {
                    isOverheadLineConfiguration = false;
                }
            }
            else {
                break;
            }
        }

        //make line configuration
        StandardLineConfiguration lineConfiguration;

        if (isOverheadLineConfiguration) {
            lineConfiguration = new StandardLineConfiguration<OverheadLineConductor>(simulator);
        }
        else {
            lineConfiguration = new StandardLineConfiguration<UndergroundLineConductor>(simulator);
        }

        lineConfiguration.setName(id);

        //set regulator configuration properties
        for (int j = 0; j < lineConfigurationLines.size(); j++) {
            if (lineConfigurationLines.get(j).trim().startsWith("conductor_A")) {
                String conductorA = this.propertyValue(lineConfigurationLines.get(j), "conductor_A");

                Conductor conductor;

                if (isOverheadLineConfiguration) {
                    conductor = new OverheadLineConductor(this.simulator);
                }
                else {
                    conductor = new UndergroundLineConductor(this.simulator);
                }

                conductor.setName(conductorA);

                lineConfiguration.setPhaseAConductor(conductor);
            }
            else if (lineConfigurationLines.get(j).trim().startsWith("conductor_B")) {
                String conductorB = this.propertyValue(lineConfigurationLines.get(j), "conductor_B");

                Conductor conductor;

                if (isOverheadLineConfiguration) {
                    conductor = new OverheadLineConductor(this.simulator);
                }
                else {
                    conductor = new UndergroundLineConductor(this.simulator);
                }

                conductor.setName(conductorB);

                lineConfiguration.setPhaseBConductor(conductor);
            }
            else if (lineConfigurationLines.get(j).trim().startsWith("conductor_C")) {
                String conductorC = this.propertyValue(lineConfigurationLines.get(j), "conductor_C");

                Conductor conductor;

                if (isOverheadLineConfiguration) {
                    conductor = new OverheadLineConductor(this.simulator);
                }
                else {
                    conductor = new UndergroundLineConductor(this.simulator);
                }

                conductor.setName(conductorC);

                lineConfiguration.setPhaseCConductor(conductor);
            }
            else if (lineConfigurationLines.get(j).trim().startsWith("conductor_N")) {
                String conductorN = this.propertyValue(lineConfigurationLines.get(j), "conductor_N");

                Conductor conductor;

                if (isOverheadLineConfiguration) {
                    conductor = new OverheadLineConductor(this.simulator);
                }
                else {
                    conductor = new UndergroundLineConductor(this.simulator);
                }

                conductor.setName(conductorN);

                lineConfiguration.setPhaseNConductor(conductor);
            }
            else if (lineConfigurationLines.get(j).trim().startsWith("spacing")) {
                String spacing = this.propertyValue(lineConfigurationLines.get(j), "spacing");

                LineSpacing lineSpacing = new LineSpacing(this.simulator);
                lineSpacing.setName(spacing);

                lineConfiguration.setSpacing(lineSpacing);
            }
        }

        if (isOverheadLineConfiguration) {
            this.overheadLineConfigurations.put(lineConfiguration.getName(), lineConfiguration);
        }
        else {
            this.undergroundLineConfigurations.put(lineConfiguration.getName(), lineConfiguration);
        }
    }

    private void createLineSpacing(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> lineSpacingLines = new ArrayList<String>();

        //save line spacing lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                lineSpacingLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make line spacing
        LineSpacing lineSpacing = new LineSpacing(simulator);
        lineSpacing.setName(id);

        //set regulator configuration properties
        for (int j = 0; j < lineSpacingLines.size(); j++) {
            if (lineSpacingLines.get(j).trim().startsWith("distance_AB")) {
                String distanceAB = this.propertyValue(lineSpacingLines.get(j), "distance_AB");

                lineSpacing.setDistanceAToB(Double.parseDouble(distanceAB));
            }
            else if (lineSpacingLines.get(j).trim().startsWith("distance_BC")) {
                String distanceBC = this.propertyValue(lineSpacingLines.get(j), "distance_BC");

                lineSpacing.setDistanceBToC(Double.parseDouble(distanceBC));
            }
            else if (lineSpacingLines.get(j).trim().startsWith("distance_AC")) {
                String distanceAC = this.propertyValue(lineSpacingLines.get(j), "distance_AC");

                lineSpacing.setDistanceAToC(Double.parseDouble(distanceAC));
            }
            else if (lineSpacingLines.get(j).trim().startsWith("distance_AN")) {
                String distanceAN = this.propertyValue(lineSpacingLines.get(j), "distance_AN");

                lineSpacing.setDistanceAToN(Double.parseDouble(distanceAN));
            }
            else if (lineSpacingLines.get(j).trim().startsWith("distance_BN")) {
                String distanceBN = this.propertyValue(lineSpacingLines.get(j), "distance_BN");

                lineSpacing.setDistanceBToN(Double.parseDouble(distanceBN));
            }
            else if (lineSpacingLines.get(j).trim().startsWith("distance_CN")) {
                String distanceCN = this.propertyValue(lineSpacingLines.get(j), "distance_CN");

                lineSpacing.setDistanceCToN(Double.parseDouble(distanceCN));
            }
        }

        this.lineSpacings.put(lineSpacing.getName(), lineSpacing);
    }

    private void createLoad(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> loadLines = new ArrayList<String>();

        //save load lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                loadLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make load
        Load load = new Load(simulator);
        load.setName(id);

        List<Integer> skippedLines = this.setNodeProperties(load, loadLines);

        for (int i = 0; i < skippedLines.size(); i++) {
            String line = loadLines.get(skippedLines.get(i));

            if (line.trim().startsWith("constant_power_A")) {
                String constantPowerA = this.propertyValue(line, "constant_power_A");

                load.setPhaseAConstantReal(String.valueOf(this.getComplex(constantPowerA).getReal()));
            }
            else if (line.trim().startsWith("constant_power_B")) {
                String constantPowerB = this.propertyValue(line, "constant_power_B");

                load.setPhaseBConstantReal(String.valueOf(this.getComplex(constantPowerB).getReal()));
            }
            else if (line.trim().startsWith("constant_power_C")) {
                String constantPowerC = this.propertyValue(line, "constant_power_C");

                load.setPhaseCConstantReal(String.valueOf(this.getComplex(constantPowerC).getReal()));
            }
        }

        this.nodes.put(load.getName(), load);
    }

    private void createMeter(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> meterLines = new ArrayList<String>();

        //save load lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                meterLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make meter
        Meter meter = new Meter(simulator);
        meter.setName(id);

        this.setNodeProperties(meter, meterLines);

        this.nodes.put(meter.getName(), meter);
    }

    private void createTriplexMeter(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> meterLines = new ArrayList<String>();

        //save load lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                meterLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make meter
        TriplexMeter meter = this.simulator.triplexMeter(id);

        this.setNodeProperties(meter, meterLines);

        this.nodes.put(meter.getName(), meter);
    }

    private void createNode(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> nodeLines = new ArrayList<String>();

        //save node lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                nodeLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make node
        Node node = new Node(simulator);
        node.setName(id);

        this.setNodeProperties(node, nodeLines);

        this.nodes.put(node.getName(), node);
    }

    private void createTriplexNode(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> nodeLines = new ArrayList<String>();

        //save node lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                nodeLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make node
        TriplexNode node = new TriplexNode(simulator);
        node.setName(id);

        this.setNodeProperties(node, nodeLines);

        this.nodes.put(node.getName(), node);
    }

    private void createSwitch(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> switchLines = new ArrayList<String>();

        //saving regulator lines, pulling out configuration name
        //as it is needed for regulator constructor
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                switchLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make switch
        Switch switchObject = new Switch(this.simulator);
        switchObject.setName(id);

        List<Integer> skippedLines = this.setLinkObjectProperties(switchObject, switchLines);

        for (int i = 0; i < skippedLines.size(); i++) {
            String line = switchLines.get(skippedLines.get(i));

            if (line.trim().startsWith("status")) {
                String status = this.propertyValue(line, "status");

                switchObject.setStatus(Enum.valueOf(SwitchStatus.class, status));
            }
        }

        this.switches.put(switchObject.getName(), switchObject);
    }

    private void createTransformer(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> transformerLines = new ArrayList<String>();
        String configurationName = "";

        //saving transformer lines, pulling out configuration name
        //as it is needed for transformer constructor
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                if (glmLines[i].trim().startsWith("configuration")) {
                    configurationName = this.propertyValue(glmLines[i], "configuration");
                }
                else
                {
                    transformerLines.add(glmLines[i]);
                }
            }
            else {
                break;
            }
        }

        //make temp transformer configuration
        TransformerConfiguration transformerConfigurationPlaceholder = new TransformerConfiguration(simulator);
        transformerConfigurationPlaceholder.setName(configurationName);

        //make transformer
        Transformer transformer = this.simulator.transformer(id, transformerConfigurationPlaceholder);

        this.setLinkObjectProperties(transformer, transformerLines);

        this.transformers.put(transformer.getName(), transformer);
    }

    private void createTransformerConfiguration(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> transformerConfigurationLines = new ArrayList<String>();

        //save transformer configuration lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                transformerConfigurationLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make transformer configuration
        TransformerConfiguration transformerConfiguration = this.simulator.transformerConfiguration(id);

        //set transformer configuration properties
        for (int j = 0; j < transformerConfigurationLines.size(); j++) {
            if (transformerConfigurationLines.get(j).trim().startsWith("connect_type")) {
                String connectType = this.propertyValue(transformerConfigurationLines.get(j), "connect_type");

                transformerConfiguration.setConnectionType(Enum.valueOf(ConnectionType.class, connectType));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("install_type")) {
                String installType = this.propertyValue(transformerConfigurationLines.get(j), "install_type");

                transformerConfiguration.setInstallationType(Enum.valueOf(InstallationType.class, installType));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("primary_voltage")) {
                String primaryVoltage = this.propertyValue(transformerConfigurationLines.get(j), "primary_voltage");

                transformerConfiguration.setPrimaryVoltage(Double.parseDouble(primaryVoltage));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("secondary_voltage")) {
                String secondaryVoltage = this.propertyValue(transformerConfigurationLines.get(j), "secondary_voltage");

                transformerConfiguration.setSecondaryVoltage(Double.parseDouble(secondaryVoltage));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("power_rating")) {
                String powerRating = this.propertyValue(transformerConfigurationLines.get(j), "power_rating");

                transformerConfiguration.setPowerRating(Double.parseDouble(powerRating));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("powerA_rating")) {
                String powerARating = this.propertyValue(transformerConfigurationLines.get(j), "powerA_rating");

                transformerConfiguration.setPhaseARating(Double.parseDouble(powerARating));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("powerB_rating")) {
                String powerBRating = this.propertyValue(transformerConfigurationLines.get(j), "powerB_rating");

                transformerConfiguration.setPhaseBRating(Double.parseDouble(powerBRating));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("powerC_rating")) {
                String powerCRating = this.propertyValue(transformerConfigurationLines.get(j), "powerC_rating");

                transformerConfiguration.setPhaseCRating(Double.parseDouble(powerCRating));
            }
            else if (transformerConfigurationLines.get(j).trim().startsWith("shunt_impedance")) {
                String shuntImpedance = this.propertyValue(transformerConfigurationLines.get(j), "shunt_impedance");

                transformerConfiguration.setShuntImpedance(this.getComplex(shuntImpedance));
            }
        }

        this.transformerConfigurations.put(transformerConfiguration.getName(), transformerConfiguration);
    }

    private void createTriplexLineConductor(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> triplexLineConductorLines = new ArrayList<String>();

        //saving triplex line conductor lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                triplexLineConductorLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make triplex line conductor
        TriplexLineConductor triplexLineConductor = this.simulator.triplexLineConductor(id);

      //set regulator configuration properties
        for (int i = 0; i < triplexLineConductorLines.size(); i++) {
            if (triplexLineConductorLines.get(i).trim().startsWith("name")) {
                String name = this.propertyValue(triplexLineConductorLines.get(i), "name");

                triplexLineConductor.setName(name);
            }
            else if (triplexLineConductorLines.get(i).trim().startsWith("resistance")) {
                String resistance = this.propertyValue(triplexLineConductorLines.get(i), "resistance");

                triplexLineConductor.setResistance(Double.parseDouble(resistance));
            }
            else if (triplexLineConductorLines.get(i).trim().startsWith("geometric_mean_radius")) {
                String radius = this.propertyValue(triplexLineConductorLines.get(i), "geometric_mean_radius");

                triplexLineConductor.setGeometricMeanRadius(Double.parseDouble(radius));
            }
        }

        this.triplexLineConductors.put(triplexLineConductor.getName(), triplexLineConductor);
    }

    private void createTriplexLineConfiguration(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> triplexLineConfigurationLines = new ArrayList<String>();

        //save lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                triplexLineConfigurationLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make triplex line configuration
        TriplexLineConfiguration triplexLineConfiguration = this.simulator.triplexLineConfiguration(id);

        //set properties
        for (int i = 0; i < triplexLineConfigurationLines.size(); i++) {
            if (triplexLineConfigurationLines.get(i).trim().startsWith("conductor_1")) {
                String conductor1Name = this.propertyValue(triplexLineConfigurationLines.get(i), "conductor_1");

                TriplexLineConductor conductor1 = new TriplexLineConductor(this.simulator);
                conductor1.setName(conductor1Name);

                triplexLineConfiguration.setPhase1Conductor(conductor1);
            }
            else if (triplexLineConfigurationLines.get(i).trim().startsWith("conductor_2")) {
                String conductor2Name = this.propertyValue(triplexLineConfigurationLines.get(i), "conductor_2");

                TriplexLineConductor conductor2 = new TriplexLineConductor(this.simulator);
                conductor2.setName(conductor2Name);

                triplexLineConfiguration.setPhase2Conductor(conductor2);
            }
            else if (triplexLineConfigurationLines.get(i).trim().startsWith("conductor_N")) {
                String conductorNName = this.propertyValue(triplexLineConfigurationLines.get(i), "conductor_N");

                TriplexLineConductor conductorN = new TriplexLineConductor(this.simulator);
                conductorN.setName(conductorNName);

                triplexLineConfiguration.setPhaseNConductor(conductorN);
            }
            else if (triplexLineConfigurationLines.get(i).trim().startsWith("insulation_thickness")) {
                String insulationThickness = this.propertyValue(triplexLineConfigurationLines.get(i), "insulation_thickness");

                triplexLineConfiguration.setInsulationThickness(Double.parseDouble(insulationThickness));
            }
            else if (triplexLineConfigurationLines.get(i).trim().startsWith("diameter")) {
                String diameter = this.propertyValue(triplexLineConfigurationLines.get(i), "diameter");

                triplexLineConfiguration.setDiameter(Double.parseDouble(diameter));
            }
        }

        this.triplexLineConfigurations.put(triplexLineConfiguration.getName(), triplexLineConfiguration);
    }

    private void createLine(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> lineLines = new ArrayList<String>();
        boolean isOverheadLine = false;
        boolean isTriplexLine = false;
        boolean isUndergroundLine = false;

        if (glmLines[currentIndex].contains("underground")) {
            isUndergroundLine = true;
        }
        else if (glmLines[currentIndex].contains("overhead")) {
            isOverheadLine = true;
        }
        else if (glmLines[currentIndex].contains("triplex")) {
            isTriplexLine = true;
        }

        //save line lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                lineLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make line
        Line<?,?> line;

        if (isOverheadLine) {
            line = new OverheadLine(this.simulator);
        }
        else if (isUndergroundLine) {
            line = new UndergroundLine(this.simulator);
        }
        else {
            line = new TriplexLine(this.simulator);
        }

        line.setName(id);

        List<Integer> skippedLines = this.setLinkObjectProperties(line, lineLines);

        //set properties
        for (int i = 0; i < skippedLines.size(); i++) {
            String propertyLine = lineLines.get(skippedLines.get(i)).trim();

            if (propertyLine.startsWith("length")) {
                String length = this.propertyValue(propertyLine, "length");

                line.setLength(Double.parseDouble(length));
            }
            else if (propertyLine.trim().startsWith("configuration")) {
                String configurationName = this.propertyValue(propertyLine, "configuration");

                if (isOverheadLine) {
                    StandardLineConfiguration<OverheadLineConductor> configuration = new StandardLineConfiguration<OverheadLineConductor>(this.simulator);
                    configuration.setName(configurationName);

                    ((OverheadLine)line).setConfiguration(configuration);
                }
                else if (isUndergroundLine) {
                    StandardLineConfiguration<UndergroundLineConductor> configuration = new StandardLineConfiguration<UndergroundLineConductor>(this.simulator);
                    configuration.setName(configurationName);

                    ((UndergroundLine)line).setConfiguration(configuration);
                }
                else if (isTriplexLine) {
                    TriplexLineConfiguration configuration = new TriplexLineConfiguration(this.simulator);
                    configuration.setName(configurationName);

                    ((TriplexLine)line).setConfiguration(configuration);
                }
            }
        }

        if (isOverheadLine) {
            this.overheadLines.put(line.getName(), (OverheadLine)line);
        }
        else if (isUndergroundLine) {
            this.undergroundLines.put(line.getName(), (UndergroundLine)line);
        }
        else if (isTriplexLine) {
            this.triplexLines.put(line.getName(), (TriplexLine)line);
        }
    }

    private void createOverheadLineConductor(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> conductorLines = new ArrayList<String>();

        //saving conductor lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                conductorLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make conductor
        OverheadLineConductor conductor = this.simulator.overheadLineConductor(id);

        //set properties
        for (int i = 0; i < conductorLines.size(); i++) {
            if (conductorLines.get(i).trim().startsWith("name")) {
                String name = this.propertyValue(conductorLines.get(i), "name");

                conductor.setName(name);
            }
            else if (conductorLines.get(i).trim().startsWith("geometricMeanRadius")) {
                String geometricMeanRadius = this.propertyValue(conductorLines.get(i), "geometricMeanRadius");

                conductor.setGeometricMeanRadius(Double.parseDouble(geometricMeanRadius));
            }
            else if (conductorLines.get(i).trim().startsWith("resistance")) {
                String resistance = this.propertyValue(conductorLines.get(i), "resistance");

                conductor.setResistance(Double.parseDouble(resistance));
            }
        }

        this.overheadLineConductors.put(conductor.getName(), conductor);
    }

    private void createUndergroundLineConductor(String[] glmLines, int currentIndex) {
        String id = this.idValue(glmLines[currentIndex]);

        List<String> conductorLines = new ArrayList<String>();

        //saving conductor lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}")) {
                conductorLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make conductor
        UndergroundLineConductor conductor = this.simulator.undergroundLineConductor(id);

        //set properties
        for (int i = 0; i < conductorLines.size(); i++) {
            if (conductorLines.get(i).trim().startsWith("name")) {
                String name = this.propertyValue(conductorLines.get(i), "name");

                conductor.setName(name);
            }
            else if (conductorLines.get(i).trim().startsWith("outer_diameter")) {
                String outerDiameter = this.propertyValue(conductorLines.get(i), "outer_diameter");

                conductor.setOuterDiameter(Double.parseDouble(outerDiameter));
            }
            else if (conductorLines.get(i).trim().startsWith("conductor_gmr")) {
                String gmr = this.propertyValue(conductorLines.get(i), "conductor_gmr");

                conductor.setConductorGmr(Double.parseDouble(gmr));
            }
            else if (conductorLines.get(i).trim().startsWith("conductor_diameter")) {
                String diameter = this.propertyValue(conductorLines.get(i), "conductor_diameter");

                conductor.setConductorGmr(Double.parseDouble(diameter));
            }
            else if (conductorLines.get(i).trim().startsWith("conductor_resistance")) {
                String resistance = this.propertyValue(conductorLines.get(i), "conductor_resistance");

                conductor.setConductorResistance(Double.parseDouble(resistance));
            }
            else if (conductorLines.get(i).trim().startsWith("neutral_gmr")) {
                String neutralGmr = this.propertyValue(conductorLines.get(i), "neutral_gmr");

                conductor.setNeutralGmr(Double.parseDouble(neutralGmr));
            }
            else if (conductorLines.get(i).trim().startsWith("neutral_resistance")) {
                String neutralResistance = this.propertyValue(conductorLines.get(i), "neutral_resistance");

                conductor.setNeutralResistance(Double.parseDouble(neutralResistance));
            }
            else if (conductorLines.get(i).trim().startsWith("neutral_diameter")) {
                String neutralDiameter = this.propertyValue(conductorLines.get(i), "neutral_diameter");

                conductor.setNeutralDiameter(Double.parseDouble(neutralDiameter));
            }
            else if (conductorLines.get(i).trim().startsWith("neutral_strands")) {
                String neutralStrands = this.propertyValue(conductorLines.get(i), "neutral_strands");

                conductor.setNeutralStrands((int)Double.parseDouble(neutralStrands));
            }
            else if (conductorLines.get(i).trim().startsWith("shield_gmr")) {
                String shieldGmr = this.propertyValue(conductorLines.get(i), "shield_gmr");

                conductor.setShieldGmr((int)Double.parseDouble(shieldGmr));
            }
            else if (conductorLines.get(i).trim().startsWith("shield_resistance")) {
                String shieldResistance = this.propertyValue(conductorLines.get(i), "shield_resistance");

                conductor.setShieldResistance(Double.parseDouble(shieldResistance));
            }
        }

        this.undergroundLineConductors.put(conductor.getName(), conductor);
    }

    private void createRecorder(String[] glmLines, int currentIndex) {
        List<String> recorderLines = new ArrayList<String>();

        //saving recorder lines
        for (int i = currentIndex + 1; i < glmLines.length; i++) {
            if (!glmLines[i].trim().equals("}") && !glmLines[i].trim().equals("};")) {
                recorderLines.add(glmLines[i]);
            }
            else {
                break;
            }
        }

        //make Recorder
        Recorder recorder = this.simulator.recorder();

        //set properties
        for (int i = 0; i < recorderLines.size(); i++) {
            if (recorderLines.get(i).trim().startsWith("name")) {
                String name = this.propertyValue(recorderLines.get(i), "name");

                recorder.setName(name);
            }
            else if (recorderLines.get(i).trim().startsWith("parent")) {
                String regulatorName = this.propertyValue(recorderLines.get(i), "parent");

                Regulator regulator = new Regulator(this.simulator);
                regulator.setName(regulatorName);

                recorder.setParent(regulator);
            }
            else if (recorderLines.get(i).trim().startsWith("property")) {
                String property = this.propertyValue(recorderLines.get(i), "property");

                String[] properties = property.split(",");

                recorder.properties(properties);
            }
            else if (recorderLines.get(i).trim().startsWith("limit")) {
                String limit = this.propertyValue(recorderLines.get(i), "limit");

                recorder.setLimit((int)Double.parseDouble(limit));
            }
            else if (recorderLines.get(i).trim().startsWith("interval")) {
                String interval = this.propertyValue(recorderLines.get(i), "interval");

                recorder.setInterval(Long.parseLong(interval));
            }
            else if (recorderLines.get(i).trim().startsWith("file")) {
                String file = this.propertyValue(recorderLines.get(i), "file");

                recorder.setFile(file);
            }
        }

        this.recorders.put(String.valueOf(this.recorders.size() + 1), recorder);
    }

    //Helper functions to creation functions

    private String idValue(String fileLine) {
        String objectString = "object";

        int startIndex = fileLine.indexOf(objectString) + objectString.length();
        int endIndex = fileLine.indexOf('{', startIndex);

        String idString = fileLine.substring(startIndex, endIndex).trim();

        return idString;
    }

    private String propertyValue(String fileLine, String propertyName) {
        int startIndex = fileLine.indexOf(propertyName) + propertyName.length();

        fileLine = fileLine.substring(startIndex).trim();

        int endIndex = fileLine.indexOf(';');

        if (fileLine.contains(" ")) {
            endIndex = Math.min(endIndex, fileLine.indexOf(' '));
        }

        return fileLine.substring(0, endIndex).trim();
    }

    private EnumSet<PhaseCode> getPhases(String fileLine, String propertyName) {
        String phaseLetters = this.propertyValue(fileLine, propertyName);

        EnumSet<PhaseCode> phases = EnumSet.noneOf(PhaseCode.class);

        if (phaseLetters.contains("A")) {
            phases.add(PhaseCode.A);
        }

        if (phaseLetters.contains("B")) {
            phases.add(PhaseCode.B);
        }

        if (phaseLetters.contains("C")) {
            phases.add(PhaseCode.C);
        }

        if (phaseLetters.contains("N")) {
            phases.add(PhaseCode.N);
        }

        return phases;
    }

    private Complex getComplex(String complexNumberString) {
        Pattern complexNumberPattern = Pattern.compile("([-+]?\\d+\\.?\\d*|[-+]?\\d*\\.?\\d+)\\s*([-+]?\\d+\\.?\\d*|[-+]?\\d*\\.?\\d+)j");
        Matcher patternMatcher = complexNumberPattern.matcher(complexNumberString);

        if (patternMatcher.find()) {
            return new Complex(Double.parseDouble(patternMatcher.group(1)), Double.parseDouble(patternMatcher.group(2)));
        }
        else {
            return new Complex(0,0);
        }
    }

    private List<Integer> setNodeProperties(Node node, List<String> nodeLines) {
        List<Integer> skippedLines = new ArrayList<Integer>();

        //set load properties
        for (int j = 0; j < nodeLines.size(); j++) {
            if (nodeLines.get(j).trim().startsWith("name")) {
                String name = this.propertyValue(nodeLines.get(j), "name");

                node.setName(name);
            }
            else if (nodeLines.get(j).trim().startsWith("parent")) {
                String parentName = this.propertyValue(nodeLines.get(j), "parent");

                Node parent = new Node(this.simulator);
                parent.setName(parentName);

                node.setParent(parent);
            }
            else if (nodeLines.get(j).trim().startsWith("phases")) {
                node.setPhases(this.getPhases(nodeLines.get(j), "phases"));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_A")) {
                String voltageA = this.propertyValue(nodeLines.get(j), "voltage_A");

                node.setVoltageA(this.getComplex(voltageA));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_B")) {
                String voltageB = this.propertyValue(nodeLines.get(j), "voltage_B");

                node.setVoltageB(this.getComplex(voltageB));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_C")) {
                String voltageC = this.propertyValue(nodeLines.get(j), "voltage_C");

                node.setVoltageC(this.getComplex(voltageC));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_1")) {
                String voltageA = this.propertyValue(nodeLines.get(j), "voltage_1");

                node.setVoltageA(this.getComplex(voltageA));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_2")) {
                String voltageB = this.propertyValue(nodeLines.get(j), "voltage_2");

                node.setVoltageB(this.getComplex(voltageB));
            }
            else if (nodeLines.get(j).trim().startsWith("voltage_3")) {
                String voltageC = this.propertyValue(nodeLines.get(j), "voltage_3");

                node.setVoltageC(this.getComplex(voltageC));
            }
            else if (nodeLines.get(j).trim().startsWith("nominal_voltage")) {
                String nominalVoltage = this.propertyValue(nodeLines.get(j), "nominal_voltage");

                node.setNominalVoltage(Double.parseDouble(nominalVoltage));
            }
            else if (nodeLines.get(j).trim().startsWith("bustype")) {
                String busType = this.propertyValue(nodeLines.get(j), "bustype");

                node.setBusType(Enum.valueOf(BusType.class, busType));
            }
            else {
                skippedLines.add(j);
            }
        }

        return skippedLines;
    }

    private List<Integer> setLinkObjectProperties(LinkObject linkObject, List<String> linkObjectLines) {
        List<Integer> skippedLines = new ArrayList<Integer>();

        //set link object properties
        for (int j = 0; j < linkObjectLines.size(); j++) {
            if (linkObjectLines.get(j).trim().startsWith("name")) {
                String name = this.propertyValue(linkObjectLines.get(j), "name");

                linkObject.setName(name);
            }
            else if (linkObjectLines.get(j).trim().startsWith("from")) {
                String fromName = this.propertyValue(linkObjectLines.get(j), "from");

                Node from = new Node(this.simulator);
                from.setName(fromName);

                linkObject.setFrom(from);
            }
            else if (linkObjectLines.get(j).trim().startsWith("to")) {
                String toName = this.propertyValue(linkObjectLines.get(j), "to");

                Node to = new Node(this.simulator);
                to.setName(toName);

                linkObject.setTo(to);
            }
            else if (linkObjectLines.get(j).trim().startsWith("phases")) {
                linkObject.setPhases(this.getPhases(linkObjectLines.get(j), "phases"));
            }
            else {
                skippedLines.add(j);
            }
        }

        return skippedLines;
    }
}