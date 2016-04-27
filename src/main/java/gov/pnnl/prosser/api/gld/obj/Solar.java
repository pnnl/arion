/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import org.apache.commons.math3.complex.Complex;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.GeneratorMode;
import gov.pnnl.prosser.api.gld.enums.GeneratorStatus;
import gov.pnnl.prosser.api.gld.enums.Orientation;
import gov.pnnl.prosser.api.gld.enums.PanelType;
import gov.pnnl.prosser.api.gld.enums.SolarPowerModel;
import gov.pnnl.prosser.api.gld.enums.SolarTiltModel;

/**
 * @author sund130
 *
 */
public class Solar extends AbstractGldObject {
    private GeneratorMode generatorMode;
    private GeneratorStatus generatorStatus;
    private PanelType panelType;
    private SolarTiltModel solarTiltModel;
    private SolarPowerModel solarPowerModel;
    private double aCoefficient;
    private double bCoefficient;
    private double dTCoefficient;
    private double tCoefficient;
    private double noct;
    private double tModule;
    private double tAmbient;
    private double ambientTemperature;
    private double insolation;
    private double ratedInsolation;
    private double pMaxTempCoefficient;
    private double vocTempCoefficient;
    private Complex vMax;
    private Complex vocMax;
    private Complex voc;
    private double efficiency;
    private double area;
    private double soiling;
    private double derating;
    private Complex vOut;
    private Complex iOut;
    private Complex vaOut;
    private ClimateObject weather;
    private double shadingFactor;
    private double tiltAngle;
    private double orientationAzimuth;
    private boolean latitudeAngleFix;
    private Orientation orientation;
    private Inverter inverter;
    
    /**
     * @param simulator
     */
    public Solar(GldSimulator simulator) {
        super(simulator);
        
        this.generatorMode = GeneratorMode.SUPPLY_DRIVEN;
        this.generatorStatus = GeneratorStatus.ONLINE;
        this.panelType = PanelType.SINGLE_CRYSTAL_SILICON;
        this.solarTiltModel = SolarTiltModel.DEFAULT;
        this.solarPowerModel = SolarPowerModel.DEFAULT;
        this.aCoefficient = -2.81;
        this.bCoefficient = -.0455;
        this.dTCoefficient = 0.0;
        this.tCoefficient = -.5;
        this.noct = 118.4;
        this.tModule = 69.8;
        this.ambientTemperature = 77.0;
        this.insolation = 0;
        this.ratedInsolation = 92.902;
        this.vMax = new Complex(79.34, 0);
        this.voc = new Complex(91.22, 0);
        this.vocMax = new Complex(91.22, 0);
        this.area = 323.0;
        this.soiling = .95;
        this.derating = .95;
    }

    /**
     * @return the generatorMode
     */
    public GeneratorMode getGeneratorMode() {
        return generatorMode;
    }

    /**
     * @param generatorMode
     * REQUIRED
     * Currently solar must operate in SUPPLY_DRIVEN
     */
    public void setGeneratorMode() {
        this.generatorMode = GeneratorMode.SUPPLY_DRIVEN;
    }

    /**
     * @return the generatorStatus
     */
    public GeneratorStatus getGeneratorStatus() {
        return generatorStatus;
    }

    /**
     * @param generatorStatus
     * REQUIRED
     * the generator status
     */
    public void setGeneratorStatus(GeneratorStatus generatorStatus) {
        this.generatorStatus = generatorStatus;
    }

    /**
     * @return the panelType
     */
    public PanelType getPanelType() {
        return panelType;
    }

    /**
     * @param panelType
     * REQUIRED
     * Uses pre-defined panel technologies
     */
    public void setPanelType(PanelType panelType) {
        this.panelType = panelType;
    }

    /**
     * @return the solarTiltModel
     */
    public SolarTiltModel getSolarTiltModel() {
        return solarTiltModel;
    }

    /**
     * @param solarTiltModel
     * Defines the tilt model to utilize for tilted array calculations. 
     */
    public void setSolarTiltModel(SolarTiltModel solarTiltModel) {
        this.solarTiltModel = solarTiltModel;
    }

    /**
     * @return the solarPowerModel
     */
    public SolarPowerModel getSolarPowerModel() {
        return solarPowerModel;
    }

    /**
     * @param solarPowerModel
     * Defines if the PV array output efficiency should be adjusted
     * for temperatures of the cells using a simple efficiency method,
     * or the SAM simple flat plate efficiency model
     */
    public void setSolarPowerModel(SolarPowerModel solarPowerModel) {
        this.solarPowerModel = solarPowerModel;
    }

    /**
     * @return the aCoefficient
     */
    public double getaCoefficient() {
        return aCoefficient;
    }

    /**
     * @param aCoefficient
     * a coefficient for temperature correction formula
     */
    public void setaCoefficient(double aCoefficient) {
        this.aCoefficient = aCoefficient;
    }

    /**
     * @return the bCoefficient
     */
    public double getbCoefficient() {
        return bCoefficient;
    }

    /**
     * @param bCoefficient
     * b coefficient for temperature correction formula
     */
    public void setbCoefficient(double bCoefficient) {
        this.bCoefficient = bCoefficient;
    }

    /**
     * @return the dTCoefficient
     */
    public double getdTCoefficient() {
        return dTCoefficient;
    }

    /**
     * @param dTCoefficient
     * Temperature difference coefficient for
     * temperature correction formula
     */
    public void setdTCoefficient(double dTCoefficient) {
        this.dTCoefficient = dTCoefficient;
    }

    /**
     * @return the tCoefficient
     */
    public double gettCoefficient() {
        return tCoefficient;
    }

    /**
     * @param tCoefficient
     * Maximum power temperature coefficient for
     * temperature correction formula
     */
    public void settCoefficient(double tCoefficient) {
        this.tCoefficient = tCoefficient;
    }

    /**
     * @return the noct
     */
    public double getNoct() {
        return noct;
    }

    /**
     * @param noct
     * Nominal operating cell temperature
     */
    public void setNoct(double noct) {
        this.noct = noct;
    }

    /**
     * @return the tModule
     */
    public double gettModule() {
        return tModule;
    }

    /**
     * @param tModule
     * Calculated internal temperature of the PV module.
     */
    public void settModule(double tModule) {
        this.tModule = tModule;
    }

    /**
     * @return the tAmbient
     */
    public double gettAmbient() {
        return tAmbient;
    }

    /**
     * @param tAmbient
     * Outside air temperature.
     */
    public void settAmbient(double tAmbient) {
        this.tAmbient = tAmbient;
    }

    /**
     * @return the ambientTemperature
     */
    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    /**
     * @param ambientTemperature
     * Current ambient temperature of air
     */
    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    /**
     * @return the insolation
     */
    public double getInsolation() {
        return insolation;
    }

    /**
     * @param insolation
     * Solar radiation incident upon the solar panel.
     */
    public void setInsolation(double insolation) {
        this.insolation = insolation;
    }

    /**
     * @return the ratedInsolation
     */
    public double getRatedInsolation() {
        return ratedInsolation;
    }

    /**
     * @param ratedInsolation
     * Insolation level that the cell is rated for.
     */
    public void setRatedInsolation(double ratedInsolation) {
        this.ratedInsolation = ratedInsolation;
    }

    /**
     * @return the pMaxTempCoefficient
     */
    public double getpMaxTempCoefficient() {
        return pMaxTempCoefficient;
    }

    /**
     * @param pMaxTempCoefficient
     * Coefficient for the effects of temperature
     * changes on the actual power output. 
     */
    public void setpMaxTempCoefficient(double pMaxTempCoefficient) {
        this.pMaxTempCoefficient = pMaxTempCoefficient;
    }

    /**
     * @return the vocTempCoefficient
     */
    public double getVocTempCoefficient() {
        return vocTempCoefficient;
    }

    /**
     * @param vocTempCoefficient
     *  Coefficient for the effects of temperature
     *  changes on the DC terminal voltage.  
     */
    public void setVocTempCoefficient(double vocTempCoefficient) {
        this.vocTempCoefficient = vocTempCoefficient;
    }

    /**
     * @return the vMax
     */
    public Complex getvMax() {
        return vMax;
    }

    /**
     * @param vMax
     * Defines the maximum operating
     * voltage of the PV module.
     */
    public void setvMax(Complex vMax) {
        this.vMax = vMax;
    }

    /**
     * @return the vocMax
     */
    public Complex getVocMax() {
        return vocMax;
    }

    /**
     * @param vocMax
     * Voc max of the solar module
     */
    public void setVocMax(Complex vocMax) {
        this.vocMax = vocMax;
    }

    /**
     * @return the voc
     */
    public Complex getVoc() {
        return voc;
    }

    /**
     * @param voc
     * Defines the open circuit voltage
     * as specified by the PV manufacturer.
     */
    public void setVoc(Complex voc) {
        this.voc = voc;
    }

    /**
     * @return the efficiency
     */
    public double getEfficiency() {
        return efficiency;
    }

    /**
     * @param efficiency
     * REQUIRED
     * Defines the efficiency of power conversion
     * from the solar insolation to DC power. 
     */
    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area
     * REQUIRED
     * Defines the surface area of the solar module.
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the soiling
     */
    public double getSoiling() {
        return soiling;
    }

    /**
     * @param soiling
     * Soiling of the array factor - 
     * representing dirt on the array
     */
    public void setSoiling(double soiling) {
        this.soiling = soiling;
    }

    /**
     * @return the derating
     */
    public double getDerating() {
        return derating;
    }

    /**
     * @param derating
     * Panel derating to account for manufacturing variances
     */
    public void setDerating(double derating) {
        this.derating = derating;
    }

    /**
     * @return the vOut
     */
    public Complex getvOut() {
        return vOut;
    }

    /**
     * @param vOut
     * DC voltage passed to the inverter object
     */
    public void setvOut(Complex vOut) {
        this.vOut = vOut;
    }

    /**
     * @return the iOut
     */
    public Complex getiOut() {
        return iOut;
    }

    /**
     * @param iOut
     * DC current passed to the inverter object 
     */
    public void setiOut(Complex iOut) {
        this.iOut = iOut;
    }

    /**
     * @return the vaOut
     */
    public Complex getVaOut() {
        return vaOut;
    }

    /**
     * @param vaOut
     * Actual power delivered to the inverter
     */
    public void setVaOut(Complex vaOut) {
        this.vaOut = vaOut;
    }

    /**
     * @return the weather
     */
    public ClimateObject getWeather() {
        return weather;
    }

    /**
     * @param weather
     *  Reference to a climate object from which
     *  temperature, humidity, and solar flux are collected  
     */
    public void setWeather(ClimateObject weather) {
        this.weather = weather;
    }

    /**
     * @return the shadingFactor
     */
    public double getShadingFactor() {
        return shadingFactor;
    }

    /**
     * @param shadingFactor
     * Shading factor for scaling solar power to the array
     */
    public void setShadingFactor(double shadingFactor) {
        this.shadingFactor = shadingFactor;
    }

    /**
     * @return the tiltAngle
     */
    public double getTiltAngle() {
        return tiltAngle;
    }

    /**
     * @param tiltAngle
     * Tilt angle of PV array
     */
    public void setTiltAngle(double tiltAngle) {
        this.tiltAngle = tiltAngle;
    }

    /**
     * @return the orientationAzimuth
     */
    public double getOrientationAzimuth() {
        return orientationAzimuth;
    }

    /**
     * @param orientationAzimuth
     * Facing direction of the PV array
     */
    public void setOrientationAzimuth(double orientationAzimuth) {
        this.orientationAzimuth = orientationAzimuth;
    }

    /**
     * @return the latitudeAngleFix
     */
    public boolean isLatitudeAngleFix() {
        return latitudeAngleFix;
    }

    /**
     * @param latitudeAngleFix
     * Fix tilt angle to installation latitude value
     * (latitude comes from climate data)
     */
    public void setLatitudeAngleFix(boolean latitudeAngleFix) {
        this.latitudeAngleFix = latitudeAngleFix;
    }

    /**
     * @return the orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation
     * Type of panel orientation. Types 
     * DEFAULT and FIXED_AXIS are currently implemented
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the inverter
     */
    public Inverter getInverter() {
        return inverter;
    }

    /**
     * @param inverter
     * REQUIRED
     * Parent inverter
     */
    public void setInverter(Inverter inverter) {
        this.inverter = inverter;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "solar";
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        writeProperty(sb, "generator_mode", this.generatorMode);
        writeProperty(sb, "generator_status", this.generatorStatus);
        writeProperty(sb, "panel_type", this.panelType);
        writeProperty(sb, "efficiency", this.efficiency);
        writeProperty(sb, "parent", this.inverter.getName());
        writeProperty(sb, "area", this.area);
        writeProperty(sb, "latitude_angle_fix", this.latitudeAngleFix);
        
        if (this.solarTiltModel != null) {
            writeProperty(sb, "solar_tilt_model", this.solarTiltModel);
        }
        
        if (this.solarPowerModel != null) {
            writeProperty(sb, "solar_power_model", this.solarPowerModel);
        }
        
        if (this.aCoefficient != 0.0) {
            writeProperty(sb, "a_coeff", this.aCoefficient);
        }
        
        if (this.bCoefficient != 0.0) {
            writeProperty(sb, "b_coeff", this.bCoefficient);
        }
        
        if (this.dTCoefficient != 0.0) {
            writeProperty(sb, "dT_coeff", this.dTCoefficient);
        }
        
        if (this.tCoefficient != 0.0) {
            writeProperty(sb, "T_coeff", this.tCoefficient);
        }
        
        if (this.noct != 0.0) {
            writeProperty(sb, "noct", this.noct);
        }
        
        if (this.tModule != 0.0) {
            writeProperty(sb, "Tmodule", this.tModule);
        }
        
        if (this.tAmbient != 0.0) {
            writeProperty(sb, "Tambient", this.tAmbient);
        }
        
        if (this.ambientTemperature != 0.0) {
            writeProperty(sb, "ambient_temperature", this.ambientTemperature);
        }
        
        if (this.insolation != 0.0) {
            writeProperty(sb, "insolation", this.insolation);
        }
        
        if (this.ratedInsolation != 0.0) {
            writeProperty(sb, "rated_insolation", this.ratedInsolation);
        }
        
        if (this.pMaxTempCoefficient != 0.0) {
            writeProperty(sb, "pmax_temp_coeff", this.pMaxTempCoefficient);
        }
        
        if (this.vocTempCoefficient != 0.0) {
            writeProperty(sb, "voc_temp_coeff", this.vocTempCoefficient);
        }
        
        if (this.vMax != null) {
            writeProperty(sb, "v_max", this.vMax);
        }
        
        if (this.vocMax != null) {
            writeProperty(sb, "voc_max", this.vocMax);
        }
        
        if (this.voc != null) {
            writeProperty(sb, "voc", this.voc);
        }
        
        if (this.soiling != 0.0) {
            writeProperty(sb, "soiling", this.soiling);
        }
        
        if (this.derating != 0.0) {
            writeProperty(sb, "derating", this.derating);
        }
        
        if (this.vOut != null) {
            writeProperty(sb, "v_out", this.vOut);
        }
        
        if (this.iOut != null) {
            writeProperty(sb, "i_out", this.iOut);
        }
        
        if (this.vaOut != null) {
            writeProperty(sb, "va_out", this.vaOut);
        }
        
        if (this.weather != null) {
            writeProperty(sb, "weather", this.weather.getName());
        }
        
        if (this.shadingFactor != 0.0) {
            writeProperty(sb, "shading_factor", this.shadingFactor);
        }
        
        if (this.tiltAngle != 0.0) {
            writeProperty(sb, "tilt_angle", this.tiltAngle);
        }
        
        if (this.orientationAzimuth != 0.0) {
            writeProperty(sb, "orientation_azimuth", this.orientationAzimuth);
        }
        
        if (this.orientation != null) {
            writeProperty(sb, "orientation", this.orientation);
        }
    }
}
