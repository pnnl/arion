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

import java.lang.reflect.Field;
import java.util.EnumSet;

import org.apache.commons.math3.complex.Complex;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.enums.GeneratorStatus;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.WindTurbineGeneratorMode;
import gov.pnnl.prosser.api.gld.enums.WindTurbineGeneratorType;

/**
 * @author sund130
 *
 */
public class DieselDistributedGeneration extends AbstractGldObject {
    private WindTurbineGeneratorMode Gen_mode;
    private GeneratorStatus Gen_status;
    private WindTurbineGeneratorType Gen_type;
    private double Rated_kVA;
    private double Rated_kV;
    private double pf;
    private double GenElecEff;
    private double TotalRealPow;
    private double TotalReacPow;
    private double speed;
    private double cylinders;
    private double stroke;
    private double torque;
    private double pressure;
    private double time_operation;
    private double fuel;
    private double w_coolingwater;
    private double inlet_temperature;
    private double outlet_temperature;
    private double air_fuel;
    private double room_temperature;
    private double exhaust_temperature;
    private double cylinder_length;
    private double cylinder_radius;
    private double brake_diameter;
    private double calotific_fuel;
    private double steam_exhaust;
    private double specific_heat_steam;
    private double specific_heat_dry;
    private double indicated_hp;
    private double brake_hp;
    private double thermal_efficiency;
    private double energy_supplied;
    private double heat_equivalent_ip;
    private double energy_coolingwater;
    private double mass_exhaustgas;
    private double energy_exhaustgas;
    private double energy_steam;
    private double total_energy_exhaustgas;
    private double unaccounted_energyloss;
    private double f;
    private double poles;
    private double wm;
    private double Pconv;
    private double Tind;
    private double EA;
    private double Vo;
    private double Rs1;
    private double Xs1;
    private double delta;
    private double IA;
    private double Ploss;
    private double Pout;
    private double effe;
    private double effo;
    private double Rated_V;
    private double Rated_VA;
    private double Rs;
    private double Xs;
    private double Rg;
    private double Xg;
    private Complex voltage_A;
    private Complex voltage_B;
    private Complex voltage_C;
    private Complex current_A;
    private Complex current_B;
    private Complex current_C;
    private Complex EfA;
    private Complex EfB;
    private Complex EfC;
    private Complex power_A;
    private Complex power_B;
    private Complex power_C;
    private Complex power_A_sch;
    private Complex power_B_sch;
    private Complex power_C_sch;
    private Complex EfA_sch;
    private Complex EfB_sch;
    private Complex EfC_sch;
    private int SlackBus;
    EnumSet<PhaseCode> phases;

    /**
     * @param simulator
     */
    public DieselDistributedGeneration(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "diesel_dg";
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#writeGldProperties(java.lang.StringBuilder)
     */
    @Override
    protected void writeGldProperties(StringBuilder sb) {
        PhaseCode.writeGldProperties(phases);

        if (this.Gen_mode != null) {
            writeProperty(sb, "Gen_mode", Gen_mode);
        }

        if (this.Gen_status != null) {
            writeProperty(sb, "Gen_status", Gen_status);
        }

        if (this.Gen_type != null) {
            writeProperty(sb, "Gen_type", Gen_type);
        }

        Field[] fields = getClass().getDeclaredFields(); // get all the fields from your class.
        for (Field f : fields) {                         // iterate over each field...
            try {
                if (f.getClass().equals(double.class) && (double)f.get(this) != 0.0) {
                    writeProperty(sb, f.getName(), (double)f.get(this));
                }
                else if (f.getClass().equals(Complex.class) && !((Complex)f.get(this)).isNaN()) {
                    writeProperty(sb, f.getName(), ((Complex)f.get(this)));
                }
                else if (f.getClass().equals(int.class) && (int)f.get(this) != 0) {
                    writeProperty(sb, f.getName(), (int)f.get(this));
                }
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}
