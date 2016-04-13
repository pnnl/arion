/**
 * 
 */
package gov.pnnl.prosser.api.gld;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.ConnectionType;
import gov.pnnl.prosser.api.gld.enums.InstallationType;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.RegulatorControl;
import gov.pnnl.prosser.api.gld.enums.RegulatorType;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;
import gov.pnnl.prosser.api.gld.lib.LineSpacing;
import gov.pnnl.prosser.api.gld.lib.OverheadLineConductor;
import gov.pnnl.prosser.api.gld.lib.RegulatorConfiguration;
import gov.pnnl.prosser.api.gld.lib.StandardLineConfiguration;
import gov.pnnl.prosser.api.gld.lib.TransformerConfiguration;
import gov.pnnl.prosser.api.gld.lib.UndergroundLineConductor;
import gov.pnnl.prosser.api.gld.obj.Load;
import gov.pnnl.prosser.api.gld.obj.Node;
import gov.pnnl.prosser.api.gld.obj.OverheadLine;
import gov.pnnl.prosser.api.gld.obj.Regulator;
import gov.pnnl.prosser.api.gld.obj.Switch;
import gov.pnnl.prosser.api.gld.obj.Transformer;
import gov.pnnl.prosser.api.gld.obj.UndergroundLine;

/**
 * @author sund130
 *
 */
public class Ieee123NodeTestFeeder { 
    private final GldSimulator simulator;
    private final int region;
    private final String name;
    private final boolean useHousesInsteadOfLoads;
    
    private Map<Integer, OverheadLineConductor> overheadLineConductors;
    private UndergroundLineConductor undergroundLineConductor;
    private Map<Integer, LineSpacing> lineSpacings;
    private Map<Integer, LineConfiguration<?>> lineConfigurations;
    private Map<Integer, Node> nodes;
    private Map<Integer, Load> loads;
    private TransformerConfiguration transformerConfiguration;
    private Transformer transformer;
    private Map<Integer, RegulatorConfiguration> regulatorConfigurations;
    private Map<Integer, Regulator> regulators;
    private Map<Integer, OverheadLine> overheadLines;
    private Map<Integer, UndergroundLine> undergroundLines;
    private Map<Integer, Switch> switches;

    public Ieee123NodeTestFeeder(final GldSimulator simulator, final String name,
            final boolean useHousesInsteadOfLoads) {
        this(simulator, name, 1, useHousesInsteadOfLoads);
    }

    public Ieee123NodeTestFeeder(final GldSimulator simulator, final String name, final int region, final boolean useHousesInsteadOfLoads) {
        this.simulator = simulator;
        this.name = name;
        this.region = region;
        this.useHousesInsteadOfLoads = useHousesInsteadOfLoads;
        this.overheadLineConductors = new HashMap<Integer, OverheadLineConductor>();
        this.lineSpacings = new HashMap<Integer, LineSpacing>();
        this.lineConfigurations = new HashMap<Integer, LineConfiguration<?>>();
        this.nodes = new HashMap<Integer, Node>();
        this.loads = new HashMap<Integer, Load>();
        this.regulatorConfigurations = new HashMap<Integer, RegulatorConfiguration>();
        this.regulators = new HashMap<Integer, Regulator>();
        this.overheadLines = new HashMap<Integer, OverheadLine>();
        this.undergroundLines = new HashMap<Integer, UndergroundLine>();
        this.switches = new HashMap<Integer, Switch>();
        
        //create simulator objects
        this.createOverheadLineConductors();
        this.createUndergroundLineConductor();
        this.createLineSpacings();
        this.createLineConfigurations();
        this.createNodes();
        this.createLoads();
        this.createTransformerConfigurationAndTransformer();
        this.createRegulatorConfigurations();
        this.createRegulators();
        this.createOverheadLines();
        this.createUndergroundLines();
        this.createSwitches();
    }
    
    public void createOverheadLineConductors() {
        String nameFormat = "overhead_line_conductor_%d";
        
        int key1 = 10001;
        OverheadLineConductor overheadLineConductor1 = this.simulator.overheadLineConductor(String.format(nameFormat, key1));
        overheadLineConductor1.setGeometricMeanRadius(.0244);
        overheadLineConductor1.setResistance(.306);
        this.overheadLineConductors.put(key1, overheadLineConductor1);
        
        int key2 = 10009;
        OverheadLineConductor overheadLineConductor2 = this.simulator.overheadLineConductor(String.format(nameFormat, key2));
        overheadLineConductor2.setGeometricMeanRadius(.00446);
        overheadLineConductor2.setResistance(1.12);
        this.overheadLineConductors.put(key2, overheadLineConductor2);
        
        int key3 = 10002;
        OverheadLineConductor overheadLineConductor3 = this.simulator.overheadLineConductor(String.format(nameFormat, key3));
        overheadLineConductor3.setGeometricMeanRadius(.00814);
        overheadLineConductor3.setResistance(.592);
        this.overheadLineConductors.put(key3, overheadLineConductor3);
    }
    
    public void createUndergroundLineConductor() {
        this.undergroundLineConductor = this.simulator.undergroundLineConductor("underground_line_conductor_10003");
        this.undergroundLineConductor.setOuterDiameter(1.06);
        this.undergroundLineConductor.setConductorGmr(.0111);
        this.undergroundLineConductor.setConductorDiameter(.368);
        this.undergroundLineConductor.setConductorResistance(.97);
        this.undergroundLineConductor.setNeutralGmr(.0111);
        this.undergroundLineConductor.setNeutralResistance(.97);
        this.undergroundLineConductor.setNeutralDiameter(.0640837);
        this.undergroundLineConductor.setNeutralStrands(6);
        this.undergroundLineConductor.setShieldGmr(0);
        this.undergroundLineConductor.setShieldResistance(0);
    }
    
    public void createLineSpacings() {
        double radical2Times4 = 5.656854;
        double radical73over2 = 4.272002;
        String stringFormat = "line_spacing_%d";
        
        int key1 = 5001;
        LineSpacing lineSpacings1 = this.simulator.lineSpacing(String.format(stringFormat, key1));
        lineSpacings1.setDistanceAToB(2.5);
        lineSpacings1.setDistanceAToC(7);
        lineSpacings1.setDistanceBToC(4.5);
        lineSpacings1.setDistanceAToN(radical2Times4);
        lineSpacings1.setDistanceBToN(radical73over2);
        lineSpacings1.setDistanceCToN(5);
        this.lineSpacings.put(key1, lineSpacings1);
        
        int key2 = 5002;
        LineSpacing lineSpacings2 = this.simulator.lineSpacing(String.format(stringFormat, key2));
        lineSpacings2.setDistanceAToB(4.5);
        lineSpacings2.setDistanceAToC(2.5);
        lineSpacings2.setDistanceBToC(7);
        lineSpacings2.setDistanceAToN(5);
        lineSpacings2.setDistanceBToN(radical73over2);
        lineSpacings2.setDistanceCToN(radical2Times4);
        this.lineSpacings.put(key2, lineSpacings2);

        int key3 = 5003;
        LineSpacing lineSpacings3 = this.simulator.lineSpacing(String.format(stringFormat, key3));
        lineSpacings3.setDistanceAToB(7);
        lineSpacings3.setDistanceAToC(4.5);
        lineSpacings3.setDistanceBToC(2.5);
        lineSpacings3.setDistanceAToN(5);
        lineSpacings3.setDistanceBToN(radical2Times4);
        lineSpacings3.setDistanceCToN(radical73over2);
        this.lineSpacings.put(key3, lineSpacings3);

        int key4 = 5004;
        LineSpacing lineSpacings4 = this.simulator.lineSpacing(String.format(stringFormat, key4));
        lineSpacings4.setDistanceAToB(4.5);
        lineSpacings4.setDistanceAToC(7);
        lineSpacings4.setDistanceBToC(2.5);
        lineSpacings4.setDistanceAToN(5);
        lineSpacings4.setDistanceBToN(radical73over2);
        lineSpacings4.setDistanceCToN(radical2Times4);
        this.lineSpacings.put(key4, lineSpacings4);

        int key5 = 5005;
        LineSpacing lineSpacings5 = this.simulator.lineSpacing(String.format(stringFormat, key5));
        lineSpacings5.setDistanceAToB(2.5);
        lineSpacings5.setDistanceAToC(4.5);
        lineSpacings5.setDistanceBToC(7);
        lineSpacings5.setDistanceAToN(radical2Times4);
        lineSpacings5.setDistanceBToN(radical73over2);
        lineSpacings5.setDistanceCToN(5);
        this.lineSpacings.put(key5, lineSpacings5);

        int key6 = 5006;
        LineSpacing lineSpacings6 = this.simulator.lineSpacing(String.format(stringFormat, key6));
        lineSpacings6.setDistanceAToB(7);
        lineSpacings6.setDistanceAToC(2.5);
        lineSpacings6.setDistanceBToC(4.5);
        lineSpacings6.setDistanceAToN(radical2Times4);
        lineSpacings6.setDistanceBToN(radical73over2);
        lineSpacings6.setDistanceCToN(5);
        this.lineSpacings.put(key6, lineSpacings6);

        int key7 = 5007;
        LineSpacing lineSpacings7 = this.simulator.lineSpacing(String.format(stringFormat, key7));
        lineSpacings7.setDistanceAToB(0);
        lineSpacings7.setDistanceAToC(7);
        lineSpacings7.setDistanceBToC(0);
        lineSpacings7.setDistanceAToN(radical2Times4);
        lineSpacings7.setDistanceBToN(0);
        lineSpacings7.setDistanceCToN(5);
        this.lineSpacings.put(key7, lineSpacings7);

        int key8 = 5008;
        LineSpacing lineSpacings8 = this.simulator.lineSpacing(String.format(stringFormat, key8));
        lineSpacings8.setDistanceAToB(7);
        lineSpacings8.setDistanceAToC(0);
        lineSpacings8.setDistanceBToC(0);
        lineSpacings8.setDistanceAToN(radical2Times4);
        lineSpacings8.setDistanceBToN(5);
        lineSpacings8.setDistanceCToN(0);
        this.lineSpacings.put(key8, lineSpacings8);

        int key9 = 5009;
        LineSpacing lineSpacings9 = this.simulator.lineSpacing(String.format(stringFormat, key9));
        lineSpacings9.setDistanceAToB(0);
        lineSpacings9.setDistanceAToC(0);
        lineSpacings9.setDistanceBToC(0);
        lineSpacings9.setDistanceAToN(5);
        lineSpacings9.setDistanceBToN(0);
        lineSpacings9.setDistanceCToN(0);
        this.lineSpacings.put(key9, lineSpacings9);

        int key10 = 50010;
        LineSpacing lineSpacings10 = this.simulator.lineSpacing(String.format(stringFormat, key10));
        lineSpacings10.setDistanceAToB(0);
        lineSpacings10.setDistanceAToC(0);
        lineSpacings10.setDistanceBToC(0);
        lineSpacings10.setDistanceAToN(0);
        lineSpacings10.setDistanceBToN(5);
        lineSpacings10.setDistanceCToN(0);
        this.lineSpacings.put(key10, lineSpacings10);

        int key11 = 50011;
        LineSpacing lineSpacings11 = this.simulator.lineSpacing(String.format(stringFormat, key11));
        lineSpacings11.setDistanceAToB(0);
        lineSpacings11.setDistanceAToC(0);
        lineSpacings11.setDistanceBToC(0);
        lineSpacings11.setDistanceAToN(0);
        lineSpacings11.setDistanceBToN(0);
        lineSpacings11.setDistanceCToN(5);
        this.lineSpacings.put(key11, lineSpacings11);

        int key12 = 50012;
        LineSpacing lineSpacings12 = this.simulator.lineSpacing(String.format(stringFormat, key12));
        lineSpacings12.setDistanceAToB(5);
        lineSpacings12.setDistanceAToC(1);
        lineSpacings12.setDistanceBToC(5);
        lineSpacings12.setDistanceAToN(0);
        lineSpacings12.setDistanceBToN(0);
        lineSpacings12.setDistanceCToN(0);
        this.lineSpacings.put(key12, lineSpacings12);
    }
    
    public void createLineConfigurations() {
        String stringFormat = "line_configuration_%d";
        
        for (int i = 0; i < 6; i++) {
            int key = 1001 + i;
            StandardLineConfiguration<OverheadLineConductor> lineConfiguration = this.simulator.overheadLineConfiguration(String.format(stringFormat, key));
            lineConfiguration.setPhaseAConductor(this.overheadLineConductors.get(10001));
            lineConfiguration.setPhaseBConductor(this.overheadLineConductors.get(10001));
            lineConfiguration.setPhaseCConductor(this.overheadLineConductors.get(10001));
            lineConfiguration.setPhaseNConductor(this.overheadLineConductors.get(10002));
            lineConfiguration.setSpacing(this.lineSpacings.get(5001 + i));
            this.lineConfigurations.put(key, lineConfiguration);
        }        
        
        int key7 = 1007;
        StandardLineConfiguration<OverheadLineConductor> lineConfiguration7 = this.simulator.overheadLineConfiguration(String.format(stringFormat, key7));
        lineConfiguration7.setPhaseAConductor(this.overheadLineConductors.get(10001));
        lineConfiguration7.setPhaseCConductor(this.overheadLineConductors.get(10001));
        lineConfiguration7.setPhaseNConductor(this.overheadLineConductors.get(10002));
        lineConfiguration7.setSpacing(this.lineSpacings.get(5007));
        this.lineConfigurations.put(key7, lineConfiguration7);
        
        int key8 = 1008;
        StandardLineConfiguration<OverheadLineConductor> lineConfiguration8 = this.simulator.overheadLineConfiguration(String.format(stringFormat, key8));
        lineConfiguration8.setPhaseAConductor(this.overheadLineConductors.get(10001));
        lineConfiguration8.setPhaseBConductor(this.overheadLineConductors.get(10001));
        lineConfiguration8.setPhaseNConductor(this.overheadLineConductors.get(10002));
        lineConfiguration8.setSpacing(this.lineSpacings.get(5008));
        this.lineConfigurations.put(key8, lineConfiguration8);
        
        int key9 = 1009;
        StandardLineConfiguration<OverheadLineConductor> lineConfiguration9 = this.simulator.overheadLineConfiguration(String.format(stringFormat, key9));
        lineConfiguration9.setPhaseAConductor(this.overheadLineConductors.get(10009));
        lineConfiguration9.setPhaseNConductor(this.overheadLineConductors.get(10009));
        lineConfiguration9.setSpacing(this.lineSpacings.get(5009));
        this.lineConfigurations.put(key9, lineConfiguration9);
        
        int key10 = 10010;
        StandardLineConfiguration<OverheadLineConductor> lineConfiguration10 = this.simulator.overheadLineConfiguration(String.format(stringFormat, key10));
        lineConfiguration10.setPhaseBConductor(this.overheadLineConductors.get(10009));
        lineConfiguration10.setPhaseNConductor(this.overheadLineConductors.get(10009));
        lineConfiguration10.setSpacing(this.lineSpacings.get(50010));
        this.lineConfigurations.put(key10, lineConfiguration10);
        
        int key11 = 10011;
        StandardLineConfiguration<OverheadLineConductor> lineConfiguration11 = this.simulator.overheadLineConfiguration(String.format(stringFormat, key11));
        lineConfiguration11.setPhaseCConductor(this.overheadLineConductors.get(10009));
        lineConfiguration11.setPhaseNConductor(this.overheadLineConductors.get(10009));
        lineConfiguration11.setSpacing(this.lineSpacings.get(50011));
        this.lineConfigurations.put(key11, lineConfiguration11);
        
        int key12 = 10012;
        StandardLineConfiguration<UndergroundLineConductor> lineConfiguration12 = this.simulator.undergroundLineConfiguration(String.format(stringFormat, key12));
        lineConfiguration12.setPhaseAConductor(this.undergroundLineConductor);
        lineConfiguration12.setPhaseBConductor(this.undergroundLineConductor);
        lineConfiguration12.setPhaseCConductor(this.undergroundLineConductor);
        lineConfiguration12.setSpacing(this.lineSpacings.get(50012));
        this.lineConfigurations.put(key12, lineConfiguration12);
    }
    
    public void createTransformerConfigurationAndTransformer() {
        this.transformerConfiguration = this.simulator.transformerConfiguration("transformer_configuration_400");
        this.transformerConfiguration.setConnectionType(ConnectionType.DELTA_DELTA);
        this.transformerConfiguration.setInstallationType(InstallationType.PADMOUNT);
        this.transformerConfiguration.setPowerRating(150.0);
        this.transformerConfiguration.setPrimaryVoltage(4160);
        this.transformerConfiguration.setSecondaryVoltage(480);
        //TODO are these needed?
        //this.transformerConfiguration.setResistance(.0127);
        //this.transformerConfiguration.setReactance(.0272);
        
        this.transformer = this.simulator.transformer("transformer_611610", this.transformerConfiguration);
        this.transformer.setPhases(PhaseCode.ABCN);
        this.transformer.setFrom(this.nodes.get(611));
        this.transformer.setTo(this.nodes.get(610));
    }

    public void createNodes() {
        String stringFormat = "node_%d";
        int[] nodesWithPhaseCN = { 3, 15 };
        int[] nodesWithPhaseABCN = { 8, 13, 18, 21, 23, 25, 40, 44, 54, 57, 61, 67, 6701, 72,
                78, 81, 89, 91, 93, 97, 101, 105, 108, 135, 149, 1491, 150, 151, 152, 160, 195,
                197, 250, 251, 300, 350, 450, 451, 610, 611 };
        int[] nodesWithPhaseAN = { 14, 1401, 110 };
        int[] nodesWithPhaseACN = { 26, 2601, 27 };
        int[] nodesWithPhaseABN = { 36 };
        Map<EnumSet<PhaseCode>, int[]> phases = new HashMap<EnumSet<PhaseCode>, int[]>();
        
        phases.put(EnumSet.of(PhaseCode.C, PhaseCode.N), nodesWithPhaseCN);
        phases.put(PhaseCode.ABCN, nodesWithPhaseABCN);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.N), nodesWithPhaseAN);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.C, PhaseCode.N), nodesWithPhaseACN);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.B, PhaseCode.N), nodesWithPhaseABN);
        
        for (Iterator<EnumSet<PhaseCode>> i = phases.keySet().iterator(); i.hasNext(); ) {
            EnumSet<PhaseCode> phaseCode = i.next();
            int[] nodes = phases.get(phaseCode);
            
            for (int j = 0; j < nodes.length; j++) {
                int key = nodes[j];
                Node node = this.simulator.node(String.format(stringFormat, key));
                node.setPhases(phaseCode);
                this.setNodeVoltages(node);
                this.nodes.put(key, node);
            }
        }
    }
    
    public void createLoads() {
        String stringFormat = "load_%d";
        int[] nodesWithPhaseCN = { 4, 5, 6, 16, 17, 24, 31, 32, 34, 41, 73, 74, 75, 84, 85, 92,
                102, 103, 104 };
        int[] nodesWithPhaseABCN = { 1, 7, 28, 29, 30, 42, 47, 48, 49, 50, 51, 52, 53, 55, 56,
                60, 77, 79, 80, 82, 83, 86, 87, 95, 98, 99, 100};
        int[] nodesWithPhaseAN = { 9, 10, 11, 19, 20, 33, 37, 45, 46, 68, 69, 70, 71, 88, 94,
                109, 111, 112, 113, 114 };
        int[] nodesWithPhaseBN = { 2, 12, 22, 38, 39, 43, 58, 59, 90, 96, 106, 107 };
        int[] nodesWithPhaseABCD = { 35, 65, 76 };
        int[] nodesWithPhaseABC = { 62, 63, 64, 66 };
        Map<EnumSet<PhaseCode>, int[]> phases = new HashMap<EnumSet<PhaseCode>, int[]>();
        
        phases.put(EnumSet.of(PhaseCode.C, PhaseCode.N), nodesWithPhaseCN);
        phases.put(PhaseCode.ABCN, nodesWithPhaseABCN);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.N), nodesWithPhaseAN);
        phases.put(EnumSet.of(PhaseCode.B, PhaseCode.N), nodesWithPhaseBN);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.B, PhaseCode.C, PhaseCode.GROUND), nodesWithPhaseABCD);
        phases.put(EnumSet.of(PhaseCode.A, PhaseCode.B, PhaseCode.C), nodesWithPhaseABC);
        
        //TODO some loads have constant impedance and current, but current Load class does not
        
        for (Iterator<EnumSet<PhaseCode>> i = phases.keySet().iterator(); i.hasNext(); ) {
            EnumSet<PhaseCode> phaseCode = i.next();
            int[] loads = phases.get(phaseCode);
            
            for (int j = 0; j < loads.length; j++) {
                int key = loads[j];
                Load load = this.simulator.load(String.format(stringFormat, key));
                load.setPhases(phaseCode);
                this.setNodeVoltages(load);
                this.loads.put(key, load);
            }
        }
        
        Load load8389 = this.simulator.load(String.format(stringFormat, 8389));
        load8389.setParent(this.loads.get(83));
        load8389.setPhases(PhaseCode.ABCN);
        this.setNodeVoltages(load8389);
        this.loads.put(8389, load8389);
        
        Load load8898 = this.simulator.load(String.format(stringFormat, 8898));
        load8898.setParent(this.loads.get(88));
        load8898.setPhases(EnumSet.of(PhaseCode.A, PhaseCode.N));
        this.setNodeVoltages(load8898);
        this.loads.put(8898, load8898);
        
        Load load9098 = this.simulator.load(String.format(stringFormat, 9098));
        load9098.setParent(this.loads.get(90));
        load9098.setPhases(EnumSet.of(PhaseCode.B, PhaseCode.N));
        this.setNodeVoltages(load9098);
        this.loads.put(9098, load9098);
        
        Load load9298 = this.simulator.load(String.format(stringFormat, 9298));
        load9298.setParent(this.loads.get(92));
        load9298.setPhases(EnumSet.of(PhaseCode.B, PhaseCode.N));
        this.setNodeVoltages(load9298);
        this.loads.put(9298, load9298);
    }
    
    public void createRegulatorConfigurations() {
        String stringFormat = "regulator_configuration_%d";
        
        int key1 = 1501490;
        RegulatorConfiguration regulatorConfiguration1 = this.simulator.regulatorConfiguration(String.format(stringFormat, key1));
        regulatorConfiguration1.setConnectionType(ConnectionType.WYE_WYE);
        regulatorConfiguration1.setBandCenter(120);
        regulatorConfiguration1.setBandWidth(2);
        regulatorConfiguration1.setTimeDelay(30);
        regulatorConfiguration1.setRaiseTaps(16);
        regulatorConfiguration1.setLowerTaps(16);
        regulatorConfiguration1.setCurrentTransducerRatio(700);
        regulatorConfiguration1.setPowerTransducerRatio(20);
        regulatorConfiguration1.setCompensatorRSettings(new double[] {3, 3, 3});
        regulatorConfiguration1.setCompensatorXSettings(new double[] {7.5, 7.5, 7.5});
        regulatorConfiguration1.setcTPhases(PhaseCode.ABC);
        regulatorConfiguration1.setpTPhases(PhaseCode.ABC);
        regulatorConfiguration1.setRegulation(.1);
        regulatorConfiguration1.setRegulatorControl(RegulatorControl.MANUAL);
        regulatorConfiguration1.setRegulatorType(RegulatorType.A);
        regulatorConfiguration1.setTapPositions(new int[] {7, 7, 7});
        this.regulatorConfigurations.put(key1, regulatorConfiguration1);
        
        int key2 = 2526010;
        RegulatorConfiguration regulatorConfiguration2 = this.simulator.regulatorConfiguration(String.format(stringFormat, key2));
        regulatorConfiguration2.setConnectionType(ConnectionType.WYE_WYE);
        regulatorConfiguration2.setBandCenter(120);
        regulatorConfiguration2.setBandWidth(1);
        regulatorConfiguration2.setTimeDelay(30);
        regulatorConfiguration2.setRaiseTaps(16);
        regulatorConfiguration2.setLowerTaps(16);
        regulatorConfiguration2.setCurrentTransducerRatio(50);
        regulatorConfiguration2.setPowerTransducerRatio(20);
        //TODO are the B settings here 0 when not specified in file?
        regulatorConfiguration2.setCompensatorRSettings(new double[] {.4, 0, .4});
        regulatorConfiguration2.setCompensatorXSettings(new double[] {.4, 0, .4});
        regulatorConfiguration2.setcTPhases(EnumSet.of(PhaseCode.A, PhaseCode.C));
        regulatorConfiguration2.setpTPhases(EnumSet.of(PhaseCode.A, PhaseCode.C));
        regulatorConfiguration2.setRegulation(.1);
        regulatorConfiguration2.setRegulatorControl(RegulatorControl.MANUAL);
        regulatorConfiguration2.setRegulatorType(RegulatorType.A);
        regulatorConfiguration2.setTapPositions(new int[] {0, 0, -1});
        this.regulatorConfigurations.put(key2, regulatorConfiguration2);
        
        int key3 = 914010;
        RegulatorConfiguration regulatorConfiguration3 = this.simulator.regulatorConfiguration(String.format(stringFormat, key3));
        regulatorConfiguration3.setConnectionType(ConnectionType.WYE_WYE);
        regulatorConfiguration3.setBandCenter(120);
        regulatorConfiguration3.setBandWidth(2);
        regulatorConfiguration3.setTimeDelay(30);
        regulatorConfiguration3.setRaiseTaps(16);
        regulatorConfiguration3.setLowerTaps(16);
        regulatorConfiguration3.setCurrentTransducerRatio(50);
        regulatorConfiguration3.setPowerTransducerRatio(20);
        //TODO are the B & C settings here 0 when not specified in file?
        regulatorConfiguration3.setCompensatorRSettings(new double[] {.4, 0, 0});
        regulatorConfiguration3.setCompensatorXSettings(new double[] {.4, 0, 0});
        regulatorConfiguration3.setcTPhases(EnumSet.of(PhaseCode.A));
        regulatorConfiguration3.setpTPhases(EnumSet.of(PhaseCode.A));
        regulatorConfiguration3.setRegulation(.1);
        regulatorConfiguration3.setRegulatorControl(RegulatorControl.MANUAL);
        regulatorConfiguration3.setRegulatorType(RegulatorType.A);
        regulatorConfiguration3.setTapPositions(new int[] {-1, 0, 0});
        this.regulatorConfigurations.put(key3, regulatorConfiguration3);
        
        int key4 = 16067010;
        RegulatorConfiguration regulatorConfiguration4 = this.simulator.regulatorConfiguration(String.format(stringFormat, key4));
        regulatorConfiguration4.setConnectionType(ConnectionType.WYE_WYE);
        regulatorConfiguration4.setBandCenter(124);
        regulatorConfiguration4.setBandWidth(2);
        regulatorConfiguration4.setTimeDelay(30);
        regulatorConfiguration4.setRaiseTaps(16);
        regulatorConfiguration4.setLowerTaps(16);
        regulatorConfiguration4.setCurrentTransducerRatio(300);
        regulatorConfiguration4.setPowerTransducerRatio(20);
        regulatorConfiguration4.setCompensatorRSettings(new double[] {.6, 1.4, .2});
        regulatorConfiguration4.setCompensatorXSettings(new double[] {1.3, 2.6, 1.4});
        regulatorConfiguration4.setcTPhases(PhaseCode.ABC);
        regulatorConfiguration4.setpTPhases(PhaseCode.ABC);
        regulatorConfiguration4.setRegulation(.1);
        regulatorConfiguration4.setRegulatorControl(RegulatorControl.MANUAL);
        regulatorConfiguration4.setRegulatorType(RegulatorType.A);
        regulatorConfiguration4.setTapPositions(new int[] {8, 1, 5});
        this.regulatorConfigurations.put(key4, regulatorConfiguration4);
    }
    
    public void createRegulators() {
        String stringFormat = "regulator_%d";
        
        int key1 = 1501491;
        Regulator regulator1 = this.simulator.regulator(String.format(stringFormat, key1), this.regulatorConfigurations.get(1501490));
        regulator1.setPhases(PhaseCode.ABC);
        regulator1.setFrom(this.nodes.get(150));
        regulator1.setTo(this.nodes.get(1491));
        this.regulators.put(key1, regulator1);
        
        int key2 = 252601;
        Regulator regulator2 = this.simulator.regulator(String.format(stringFormat, key2), this.regulatorConfigurations.get(2526010));
        regulator2.setPhases(EnumSet.of(PhaseCode.A, PhaseCode.C));
        regulator2.setFrom(this.nodes.get(25));
        regulator2.setTo(this.nodes.get(2601));
        this.regulators.put(key2, regulator2);
        
        int key3 = 91401;
        Regulator regulator3 = this.simulator.regulator(String.format(stringFormat, key3), this.regulatorConfigurations.get(914010));
        regulator3.setPhases(EnumSet.of(PhaseCode.A));
        regulator3.setFrom(this.loads.get(9));
        regulator3.setTo(this.nodes.get(1401));
        this.regulators.put(key3, regulator3);
        
        int key4 = 1606701;
        Regulator regulator4 = this.simulator.regulator(String.format(stringFormat, key4), this.regulatorConfigurations.get(16067010));
        regulator4.setPhases(PhaseCode.ABC);
        regulator4.setFrom(this.nodes.get(160));
        regulator4.setTo(this.nodes.get(6701));
        this.regulators.put(key4, regulator4);
    }
    
    @SuppressWarnings("unchecked")
    public void createOverheadLines() {
        String stringFormat = "overhead_line_%d";
        EnumSet<PhaseCode> an = EnumSet.of(PhaseCode.A, PhaseCode.N);
        EnumSet<PhaseCode> bn = EnumSet.of(PhaseCode.B, PhaseCode.N);
        EnumSet<PhaseCode> cn = EnumSet.of(PhaseCode.C, PhaseCode.N);
        EnumSet<PhaseCode> abn = EnumSet.of(PhaseCode.A, PhaseCode.B, PhaseCode.N);
        EnumSet<PhaseCode> acn = EnumSet.of(PhaseCode.A, PhaseCode.C, PhaseCode.N);
        EnumSet<PhaseCode> abcn = PhaseCode.ABCN;
        EnumSet<PhaseCode> abc = PhaseCode.ABC;
        
        int key1 = 12;
        this.overheadLines.put(key1, this.simulator.overheadLine(String.format(stringFormat, key1), bn, 
                this.loads.get(1), this.loads.get(2), 175, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key2 = 13;
        this.overheadLines.put(key2, this.simulator.overheadLine(String.format(stringFormat, key2), cn, 
                this.loads.get(1), this.nodes.get(3), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key3 = 17;
        this.overheadLines.put(key3, this.simulator.overheadLine(String.format(stringFormat, key3), abcn, 
                this.loads.get(1), this.loads.get(7), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key4 = 34;
        this.overheadLines.put(key4, this.simulator.overheadLine(String.format(stringFormat, key4), cn, 
                this.nodes.get(3), this.loads.get(4), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key5 = 35;
        this.overheadLines.put(key5, this.simulator.overheadLine(String.format(stringFormat, key5), cn, 
                this.nodes.get(3), this.loads.get(5), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key6 = 56;
        this.overheadLines.put(key6, this.simulator.overheadLine(String.format(stringFormat, key6), cn, 
                this.loads.get(5), this.loads.get(6), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key7 = 78;
        this.overheadLines.put(key7, this.simulator.overheadLine(String.format(stringFormat, key7), abcn, 
                this.loads.get(7), this.nodes.get(8), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key8 = 812;
        this.overheadLines.put(key8, this.simulator.overheadLine(String.format(stringFormat, key8), bn, 
                this.nodes.get(8), this.loads.get(12), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key9 = 89;
        this.overheadLines.put(key9, this.simulator.overheadLine(String.format(stringFormat, key9), an, 
                this.nodes.get(8), this.loads.get(9), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key10 = 813;
        this.overheadLines.put(key10, this.simulator.overheadLine(String.format(stringFormat, key10), abcn, 
                this.nodes.get(8), this.nodes.get(13), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key11 = 140114;
        this.overheadLines.put(key11, this.simulator.overheadLine(String.format(stringFormat, key11), an, 
                this.nodes.get(1401), this.nodes.get(14), 425, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key12 = 1334;
        this.overheadLines.put(key12, this.simulator.overheadLine(String.format(stringFormat, key12), cn, 
                this.nodes.get(13), this.loads.get(34), 150, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key13 = 1318;
        this.overheadLines.put(key13, this.simulator.overheadLine(String.format(stringFormat, key13), abcn, 
                this.nodes.get(13), this.nodes.get(18), 825, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key14 = 1411;
        this.overheadLines.put(key14, this.simulator.overheadLine(String.format(stringFormat, key14), an, 
                this.nodes.get(14), this.loads.get(11), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key15 = 1410;
        this.overheadLines.put(key15, this.simulator.overheadLine(String.format(stringFormat, key15), an, 
                this.nodes.get(14), this.loads.get(10), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key16 = 1516;
        this.overheadLines.put(key16, this.simulator.overheadLine(String.format(stringFormat, key16), cn, 
                this.nodes.get(15), this.loads.get(16), 375, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key17 = 1517;
        this.overheadLines.put(key17, this.simulator.overheadLine(String.format(stringFormat, key17), cn, 
                this.nodes.get(15), this.loads.get(17), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key18 = 1819;
        this.overheadLines.put(key18, this.simulator.overheadLine(String.format(stringFormat, key18), an, 
                this.nodes.get(18), this.loads.get(19), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key19 = 1821;
        this.overheadLines.put(key19, this.simulator.overheadLine(String.format(stringFormat, key19), abcn, 
                this.nodes.get(18), this.nodes.get(21), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key20 = 1920;
        this.overheadLines.put(key20, this.simulator.overheadLine(String.format(stringFormat, key20), an, 
                this.loads.get(19), this.loads.get(20), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key21 = 2122;
        this.overheadLines.put(key21, this.simulator.overheadLine(String.format(stringFormat, key21), bn, 
                this.nodes.get(21), this.loads.get(22), 525, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key22 = 2123;
        this.overheadLines.put(key22, this.simulator.overheadLine(String.format(stringFormat, key22), abcn, 
                this.nodes.get(21), this.nodes.get(23), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key23 = 2324;
        this.overheadLines.put(key23, this.simulator.overheadLine(String.format(stringFormat, key23), cn, 
                this.nodes.get(23), this.loads.get(24), 550, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key24 = 2325;
        this.overheadLines.put(key24, this.simulator.overheadLine(String.format(stringFormat, key24), abcn, 
                this.nodes.get(23), this.nodes.get(25), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key25 = 260126;
        this.overheadLines.put(key25, this.simulator.overheadLine(String.format(stringFormat, key25), acn, 
                this.nodes.get(2601), this.nodes.get(26), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1007)));
        
        int key26 = 2528;
        this.overheadLines.put(key26, this.simulator.overheadLine(String.format(stringFormat, key26), abcn, 
                this.nodes.get(25), this.loads.get(28), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key27 = 2627;
        this.overheadLines.put(key27, this.simulator.overheadLine(String.format(stringFormat, key27), acn, 
                this.nodes.get(26), this.nodes.get(27), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1007)));
        
        int key28 = 2631;
        this.overheadLines.put(key28, this.simulator.overheadLine(String.format(stringFormat, key28), cn, 
                this.nodes.get(26), this.loads.get(31), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key29 = 2733;
        this.overheadLines.put(key29, this.simulator.overheadLine(String.format(stringFormat, key29), an, 
                this.nodes.get(27), this.loads.get(33), 500, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key30 = 2829;
        this.overheadLines.put(key30, this.simulator.overheadLine(String.format(stringFormat, key30), abcn, 
                this.loads.get(28), this.loads.get(29), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key31 = 2930;
        this.overheadLines.put(key31, this.simulator.overheadLine(String.format(stringFormat, key31), abcn, 
                this.loads.get(29), this.loads.get(30), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key32 = 30250;
        this.overheadLines.put(key32, this.simulator.overheadLine(String.format(stringFormat, key32), abcn, 
                this.loads.get(30), this.nodes.get(250), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1002)));
        
        int key33 = 3132;
        this.overheadLines.put(key33, this.simulator.overheadLine(String.format(stringFormat, key33), cn, 
                this.loads.get(31), this.loads.get(32), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key34 = 3415;
        this.overheadLines.put(key34, this.simulator.overheadLine(String.format(stringFormat, key34), cn, 
                this.loads.get(34), this.nodes.get(15), 100, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key35 = 3536;
        this.overheadLines.put(key35, this.simulator.overheadLine(String.format(stringFormat, key35), abn, 
                this.loads.get(35), this.nodes.get(36), 650, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1008)));
        
        int key36 = 3540;
        this.overheadLines.put(key36, this.simulator.overheadLine(String.format(stringFormat, key36), abcn, 
                this.loads.get(35), this.nodes.get(40), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key37 = 3637;
        this.overheadLines.put(key37, this.simulator.overheadLine(String.format(stringFormat, key37), an, 
                this.nodes.get(36), this.loads.get(37), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key38 = 3638;
        this.overheadLines.put(key38, this.simulator.overheadLine(String.format(stringFormat, key38), bn, 
                this.nodes.get(36), this.loads.get(38), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key39 = 3839;
        this.overheadLines.put(key39, this.simulator.overheadLine(String.format(stringFormat, key39), bn, 
                this.loads.get(38), this.loads.get(39), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key40 = 4041;
        this.overheadLines.put(key40, this.simulator.overheadLine(String.format(stringFormat, key40), cn, 
                this.nodes.get(40), this.loads.get(41), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key41 = 4042;
        this.overheadLines.put(key41, this.simulator.overheadLine(String.format(stringFormat, key41), abcn, 
                this.nodes.get(40), this.loads.get(42), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key42 = 4243;
        this.overheadLines.put(key42, this.simulator.overheadLine(String.format(stringFormat, key42), bn, 
                this.loads.get(42), this.loads.get(43), 500, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key43 = 4244;
        this.overheadLines.put(key43, this.simulator.overheadLine(String.format(stringFormat, key43), abcn, 
                this.loads.get(42), this.nodes.get(44), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key44 = 4445;
        this.overheadLines.put(key44, this.simulator.overheadLine(String.format(stringFormat, key44), an, 
                this.nodes.get(44), this.loads.get(45), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key45 = 4447;
        this.overheadLines.put(key45, this.simulator.overheadLine(String.format(stringFormat, key45), abcn, 
                this.nodes.get(44), this.loads.get(47), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key46 = 4546;
        this.overheadLines.put(key46, this.simulator.overheadLine(String.format(stringFormat, key46), an, 
                this.loads.get(45), this.loads.get(46), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key47 = 4748;
        this.overheadLines.put(key47, this.simulator.overheadLine(String.format(stringFormat, key47), abcn, 
                this.loads.get(47), this.loads.get(48), 150, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key48 = 4749;
        this.overheadLines.put(key48, this.simulator.overheadLine(String.format(stringFormat, key48), abcn, 
                this.loads.get(47), this.loads.get(49), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key49 = 4950;
        this.overheadLines.put(key49, this.simulator.overheadLine(String.format(stringFormat, key49), abcn, 
                this.loads.get(49), this.loads.get(50), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key50 = 5051;
        this.overheadLines.put(key50, this.simulator.overheadLine(String.format(stringFormat, key50), abcn, 
                this.loads.get(50), this.loads.get(51), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key51 = 51151;
        this.overheadLines.put(key51, this.simulator.overheadLine(String.format(stringFormat, key51), abcn, 
                this.loads.get(51), this.nodes.get(151), 700, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key52 = 5253;
        this.overheadLines.put(key52, this.simulator.overheadLine(String.format(stringFormat, key52), abcn, 
                this.loads.get(52), this.loads.get(53), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key53 = 5354;
        this.overheadLines.put(key53, this.simulator.overheadLine(String.format(stringFormat, key53), abcn, 
                this.loads.get(53), this.nodes.get(54), 125, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key54 = 5455;
        this.overheadLines.put(key54, this.simulator.overheadLine(String.format(stringFormat, key54), abcn, 
                this.nodes.get(54), this.loads.get(55), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key55 = 5457;
        this.overheadLines.put(key55, this.simulator.overheadLine(String.format(stringFormat, key55), abcn, 
                this.nodes.get(54), this.nodes.get(57), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key56 = 5556;
        this.overheadLines.put(key56, this.simulator.overheadLine(String.format(stringFormat, key56), abcn, 
                this.loads.get(55), this.loads.get(56), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key57 = 5758;
        this.overheadLines.put(key57, this.simulator.overheadLine(String.format(stringFormat, key57), bn, 
                this.nodes.get(57), this.loads.get(58), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key58 = 5760;
        this.overheadLines.put(key58, this.simulator.overheadLine(String.format(stringFormat, key58), abcn, 
                this.nodes.get(57), this.loads.get(60), 750, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key59 = 5859;
        this.overheadLines.put(key59, this.simulator.overheadLine(String.format(stringFormat, key59), bn, 
                this.loads.get(58), this.loads.get(59), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key60 = 6061;
        this.overheadLines.put(key60, this.simulator.overheadLine(String.format(stringFormat, key60), abcn, 
                this.loads.get(60), this.nodes.get(61), 550, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1005)));
        
        int key66 = 6768;
        this.overheadLines.put(key66, this.simulator.overheadLine(String.format(stringFormat, key66), an, 
                this.nodes.get(67), this.loads.get(68), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key67 = 6772;
        this.overheadLines.put(key67, this.simulator.overheadLine(String.format(stringFormat, key67), abcn, 
                this.nodes.get(67), this.nodes.get(72), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key68 = 6797;
        this.overheadLines.put(key68, this.simulator.overheadLine(String.format(stringFormat, key68), abcn, 
                this.nodes.get(67), this.nodes.get(97), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key69 = 6869;
        this.overheadLines.put(key69, this.simulator.overheadLine(String.format(stringFormat, key69), an, 
                this.loads.get(68), this.loads.get(69), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key70 = 6970;
        this.overheadLines.put(key70, this.simulator.overheadLine(String.format(stringFormat, key70), an, 
                this.loads.get(69), this.loads.get(70), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key71 = 7071;
        this.overheadLines.put(key71, this.simulator.overheadLine(String.format(stringFormat, key71), an, 
                this.loads.get(70), this.loads.get(71), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key72 = 7273;
        this.overheadLines.put(key72, this.simulator.overheadLine(String.format(stringFormat, key72), cn, 
                this.nodes.get(72), this.loads.get(73), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key73 = 7276;
        this.overheadLines.put(key73, this.simulator.overheadLine(String.format(stringFormat, key73), abcn, 
                this.nodes.get(72), this.loads.get(76), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key74 = 7374;
        this.overheadLines.put(key74, this.simulator.overheadLine(String.format(stringFormat, key74), cn, 
                this.loads.get(73), this.loads.get(74), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key75 = 7475;
        this.overheadLines.put(key75, this.simulator.overheadLine(String.format(stringFormat, key75), cn, 
                this.loads.get(74), this.loads.get(75), 400, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key76 = 7677;
        this.overheadLines.put(key76, this.simulator.overheadLine(String.format(stringFormat, key76), abcn, 
                this.loads.get(76), this.loads.get(77), 400, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key77 = 7686;
        this.overheadLines.put(key77, this.simulator.overheadLine(String.format(stringFormat, key77), abcn, 
                this.loads.get(76), this.loads.get(86), 700, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key78 = 7778;
        this.overheadLines.put(key78, this.simulator.overheadLine(String.format(stringFormat, key78), abcn, 
                this.loads.get(77), this.nodes.get(78), 100, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key79 = 7879;
        this.overheadLines.put(key79, this.simulator.overheadLine(String.format(stringFormat, key79), abcn, 
                this.nodes.get(78), this.loads.get(79), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key80 = 7880;
        this.overheadLines.put(key80, this.simulator.overheadLine(String.format(stringFormat, key80), abcn, 
                this.nodes.get(78), this.loads.get(80), 475, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key81 = 8081;
        this.overheadLines.put(key81, this.simulator.overheadLine(String.format(stringFormat, key81), abcn, 
                this.loads.get(80), this.nodes.get(81), 475, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key82 = 8182;
        this.overheadLines.put(key82, this.simulator.overheadLine(String.format(stringFormat, key82), abcn, 
                this.nodes.get(81), this.loads.get(82), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key83 = 8184;
        this.overheadLines.put(key83, this.simulator.overheadLine(String.format(stringFormat, key83), cn, 
                this.nodes.get(81), this.loads.get(84), 675, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key84 = 8283;
        this.overheadLines.put(key84, this.simulator.overheadLine(String.format(stringFormat, key84), abcn, 
                this.loads.get(82), this.loads.get(83), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key85 = 8485;
        this.overheadLines.put(key85, this.simulator.overheadLine(String.format(stringFormat, key85), cn, 
                this.loads.get(84), this.loads.get(85), 475, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key86 = 8687;
        this.overheadLines.put(key86, this.simulator.overheadLine(String.format(stringFormat, key86), abcn, 
                this.loads.get(86), this.loads.get(87), 450, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key87 = 8788;
        this.overheadLines.put(key87, this.simulator.overheadLine(String.format(stringFormat, key87), an, 
                this.loads.get(87), this.loads.get(88), 175, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key88 = 8789;
        this.overheadLines.put(key88, this.simulator.overheadLine(String.format(stringFormat, key88), abcn, 
                this.loads.get(87), this.nodes.get(89), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key89 = 8990;
        this.overheadLines.put(key89, this.simulator.overheadLine(String.format(stringFormat, key89), bn, 
                this.nodes.get(89), this.loads.get(90), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key90 = 8991;
        this.overheadLines.put(key90, this.simulator.overheadLine(String.format(stringFormat, key90), abcn, 
                this.nodes.get(89), this.nodes.get(91), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key91 = 9192;
        this.overheadLines.put(key91, this.simulator.overheadLine(String.format(stringFormat, key91), cn, 
                this.nodes.get(91), this.loads.get(92), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key92 = 9193;
        this.overheadLines.put(key92, this.simulator.overheadLine(String.format(stringFormat, key92), abcn, 
                this.nodes.get(91), this.nodes.get(93), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key93 = 9394;
        this.overheadLines.put(key93, this.simulator.overheadLine(String.format(stringFormat, key93), an, 
                this.nodes.get(93), this.loads.get(94), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key94 = 9395;
        this.overheadLines.put(key94, this.simulator.overheadLine(String.format(stringFormat, key94), abcn, 
                this.nodes.get(93), this.loads.get(95), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key95 = 9596;
        this.overheadLines.put(key95, this.simulator.overheadLine(String.format(stringFormat, key95), bn, 
                this.loads.get(95), this.loads.get(96), 200, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key96 = 9798;
        this.overheadLines.put(key96, this.simulator.overheadLine(String.format(stringFormat, key96), abcn, 
                this.nodes.get(97), this.loads.get(98), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key97 = 9899;
        this.overheadLines.put(key97, this.simulator.overheadLine(String.format(stringFormat, key97), abcn, 
                this.loads.get(98), this.loads.get(99), 550, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key98 = 99100;
        this.overheadLines.put(key98, this.simulator.overheadLine(String.format(stringFormat, key98), abcn, 
                this.loads.get(99), this.loads.get(100), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key99 = 100450;
        this.overheadLines.put(key99, this.simulator.overheadLine(String.format(stringFormat, key99), abcn, 
                this.loads.get(100), this.nodes.get(450), 800, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key100 = 101102;
        this.overheadLines.put(key100, this.simulator.overheadLine(String.format(stringFormat, key100), cn, 
                this.nodes.get(101), this.loads.get(102), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key101 = 101105;
        this.overheadLines.put(key101, this.simulator.overheadLine(String.format(stringFormat, key101), abcn, 
                this.nodes.get(101), this.nodes.get(105), 275, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key102 = 102103;
        this.overheadLines.put(key102, this.simulator.overheadLine(String.format(stringFormat, key102), cn, 
                this.loads.get(102), this.loads.get(103), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key103 = 103104;
        this.overheadLines.put(key103, this.simulator.overheadLine(String.format(stringFormat, key103), cn, 
                this.loads.get(103), this.loads.get(104), 700, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10011)));
        
        int key104 = 105106;
        this.overheadLines.put(key104, this.simulator.overheadLine(String.format(stringFormat, key104), bn, 
                this.nodes.get(105), this.loads.get(106), 225, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key105 = 105108;
        this.overheadLines.put(key105, this.simulator.overheadLine(String.format(stringFormat, key105), abcn, 
                this.nodes.get(105), this.nodes.get(108), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key106 = 106107;
        this.overheadLines.put(key106, this.simulator.overheadLine(String.format(stringFormat, key106), bn, 
                this.loads.get(106), this.loads.get(107), 575, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(10010)));
        
        int key107 = 108109;
        this.overheadLines.put(key107, this.simulator.overheadLine(String.format(stringFormat, key107), an, 
                this.nodes.get(108), this.loads.get(109), 450, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key108 = 108300;
        this.overheadLines.put(key108, this.simulator.overheadLine(String.format(stringFormat, key108), abcn, 
                this.nodes.get(108), this.nodes.get(300), 1000, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
        
        int key109 = 109110;
        this.overheadLines.put(key109, this.simulator.overheadLine(String.format(stringFormat, key109), an, 
                this.loads.get(109), this.nodes.get(110), 300, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key110 = 110111;
        this.overheadLines.put(key110, this.simulator.overheadLine(String.format(stringFormat, key110), an, 
                this.nodes.get(110), this.loads.get(111), 575, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key111 = 110112;
        this.overheadLines.put(key111, this.simulator.overheadLine(String.format(stringFormat, key111), an, 
                this.nodes.get(110), this.loads.get(112), 125, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key112 = 112113;
        this.overheadLines.put(key112, this.simulator.overheadLine(String.format(stringFormat, key112), an, 
                this.loads.get(112), this.loads.get(113), 525, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key113 = 113114;
        this.overheadLines.put(key113, this.simulator.overheadLine(String.format(stringFormat, key113), an, 
                this.loads.get(113), this.loads.get(114), 325, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1009)));
        
        int key114 = 13535;
        this.overheadLines.put(key114, this.simulator.overheadLine(String.format(stringFormat, key114), abcn, 
                this.nodes.get(135), this.loads.get(35), 375, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1004)));
        
        int key115 = 1491;
        this.overheadLines.put(key115, this.simulator.overheadLine(String.format(stringFormat, key115), abcn, 
                this.nodes.get(149), this.loads.get(1), 400, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key116 = 15252;
        this.overheadLines.put(key116, this.simulator.overheadLine(String.format(stringFormat, key116), abcn, 
                this.nodes.get(152), this.loads.get(52), 400, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1001)));
        
        int key117 = 670167;
        this.overheadLines.put(key117, this.simulator.overheadLine(String.format(stringFormat, key117), abcn, 
                this.nodes.get(6701), this.nodes.get(67), 350, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1006)));
        
        int key118 = 197101;
        this.overheadLines.put(key118, this.simulator.overheadLine(String.format(stringFormat, key118), abcn, 
                this.nodes.get(197), this.nodes.get(101), 250, (StandardLineConfiguration<OverheadLineConductor>)this.lineConfigurations.get(1003)));
    }
    
    @SuppressWarnings("unchecked")
    public void createUndergroundLines() {
        String stringFormat = "underground_line_%d";
        
        int key61 = 6062;
        this.undergroundLines.put(key61, this.simulator.undergroundLine(String.format(stringFormat, key61), PhaseCode.ABC, 
                this.loads.get(60), this.loads.get(62), 250, (StandardLineConfiguration<UndergroundLineConductor>)this.lineConfigurations.get(10012)));
        
        int key62 = 6263;
        this.undergroundLines.put(key62, this.simulator.undergroundLine(String.format(stringFormat, key62), PhaseCode.ABC, 
                this.loads.get(62), this.loads.get(63), 175, (StandardLineConfiguration<UndergroundLineConductor>)this.lineConfigurations.get(10012)));
        
        int key63 = 6364;
        this.undergroundLines.put(key63, this.simulator.undergroundLine(String.format(stringFormat, key63), PhaseCode.ABC, 
                this.loads.get(63), this.loads.get(64), 350, (StandardLineConfiguration<UndergroundLineConductor>)this.lineConfigurations.get(10012)));
        
        int key64 = 6465;
        this.undergroundLines.put(key64, this.simulator.undergroundLine(String.format(stringFormat, key64), PhaseCode.ABC, 
                this.loads.get(64), this.loads.get(65), 425, (StandardLineConfiguration<UndergroundLineConductor>)this.lineConfigurations.get(10012)));
        
        int key65 = 6566;
        this.undergroundLines.put(key65, this.simulator.undergroundLine(String.format(stringFormat, key65), PhaseCode.ABC, 
                this.loads.get(65), this.loads.get(66), 325, (StandardLineConfiguration<UndergroundLineConductor>)this.lineConfigurations.get(10012)));
        
    }
    
    public void createSwitches() {
        this.switches.put(13152, this.simulator.switchLinkObject("13152", PhaseCode.ABCN, this.nodes.get(13), this.nodes.get(152), SwitchStatus.CLOSED));
        this.switches.put(18135, this.simulator.switchLinkObject("18135", PhaseCode.ABCN, this.nodes.get(18), this.nodes.get(135), SwitchStatus.CLOSED));
        this.switches.put(60160, this.simulator.switchLinkObject("60160", PhaseCode.ABCN, this.loads.get(60), this.nodes.get(160), SwitchStatus.CLOSED));
        this.switches.put(61611, this.simulator.switchLinkObject("61611", PhaseCode.ABCN, this.nodes.get(61), this.nodes.get(611), SwitchStatus.CLOSED));
        this.switches.put(97197, this.simulator.switchLinkObject("97197", PhaseCode.ABCN, this.nodes.get(97), this.nodes.get(197), SwitchStatus.CLOSED));
        this.switches.put(1491149, this.simulator.switchLinkObject("1491149", PhaseCode.ABCN, this.nodes.get(1491), this.nodes.get(149), SwitchStatus.CLOSED));
        this.switches.put(250251, this.simulator.switchLinkObject("250251", PhaseCode.ABCN, this.nodes.get(250), this.nodes.get(251), SwitchStatus.OPEN));
        this.switches.put(450451, this.simulator.switchLinkObject("450451", PhaseCode.ABCN, this.nodes.get(450), this.nodes.get(451), SwitchStatus.OPEN));
        this.switches.put(5494, this.simulator.switchLinkObject("5494", EnumSet.of(PhaseCode.A, PhaseCode.N), this.nodes.get(54), this.loads.get(94), SwitchStatus.OPEN));
        this.switches.put(151300, this.simulator.switchLinkObject("151300", PhaseCode.ABCN, this.nodes.get(151), this.nodes.get(300), SwitchStatus.OPEN));
        this.switches.put(95195, this.simulator.switchLinkObject("95195", PhaseCode.ABCN, this.loads.get(95), this.nodes.get(195), SwitchStatus.OPEN));
    }
    
    public void setNodeVoltages(Node node) {
        node.setVoltageA(2401.7771, 0);
        node.setVoltageB(-1200.8886, -2080);
        node.setVoltageC(-1200.8886, 2080);
        node.setNominalVoltage(2401.7771);
    }
}
