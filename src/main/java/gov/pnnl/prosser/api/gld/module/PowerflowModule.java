/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.gld.enums.SolverMethod;

import java.util.Objects;

/**
 * GridLabD Powerflow Module
 *
 * @author nord229
 */
public class PowerflowModule extends Module {

    private final SolverMethod solverMethod;

    private final Long nrIterationLimit;

    public PowerflowModule() {
        this.solverMethod = null;
        this.nrIterationLimit = null;
    }

    public PowerflowModule(final SolverMethod solverMethod, final Long nrIterationLimit) {
        this.solverMethod = solverMethod;
        this.nrIterationLimit = nrIterationLimit;
    }

    /**
     * @return the solverMethod
     */
    public SolverMethod getSolverMethod() {
        return solverMethod;
    }

    /**
     * @return the nrIterationLimit
     */
    public Long getNrIterationLimit() {
        return nrIterationLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solverMethod, nrIterationLimit);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PowerflowModule other = (PowerflowModule) obj;
        return Objects.equals(this.solverMethod, other.solverMethod)
                && Objects.equals(this.nrIterationLimit, other.nrIterationLimit);
    }

    @Override
    public String getGLDObjectType() {
        return "powerflow";
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "solver_method", this.solverMethod);
        GldUtils.writeProperty(sb, "NR_iteration_limit", this.nrIterationLimit);
    }

}
