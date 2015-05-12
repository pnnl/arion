/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

/**
 * Generic Powerflow Library Objects
 *
 * @author nord229
 */
public abstract class PowerflowLibrary extends AbstractGldObject {

    public PowerflowLibrary(final GldSimulator simulator) {
        super(simulator);
        simulator.ensurePowerflowModule();
    }

}
