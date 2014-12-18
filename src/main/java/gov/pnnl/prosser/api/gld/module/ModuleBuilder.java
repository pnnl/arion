package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.gld.module.PowerflowModule.SolverMethod;

import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder {
    protected final List<Module> modules = new ArrayList<>();

    public ModuleBuilder addClimate() {
        this.modules.add(new ClimateModule());
        return this;
    }

    public ModuleBuilder addTape() {
        this.modules.add(new Tape());
        return this;
    }

    public ModuleBuilder.PowerflowModuleBuilder addPowerflow() {
        final ModuleBuilder.PowerflowModuleBuilder builder = new PowerflowModuleBuilder(this);
        this.modules.add(builder.getModule());
        return builder;
    }

    public ModuleBuilder.ResidentialModuleBuilder addResidential() {
        final ModuleBuilder.ResidentialModuleBuilder builder = new ResidentialModuleBuilder(this);
        this.modules.add(builder.getModule());
        return builder;
    }

    public List<Module> build() {
        return this.modules;
    }

    public static class PowerflowModuleBuilder {

        protected ModuleBuilder parent;

        protected final PowerflowModule module = new PowerflowModule();

        protected PowerflowModuleBuilder(final ModuleBuilder parent) {
            this.parent = parent;
        }

        public PowerflowModule getModule() {
            return module;
        }

        public ModuleBuilder.PowerflowModuleBuilder solverMethod(final SolverMethod solverMethod) {
            this.module.setSolverMethod(solverMethod);
            return this;
        }

        public ModuleBuilder and() {
            return this.parent;
        }
    }

    public static class ResidentialModuleBuilder {
        protected ModuleBuilder parent;

        protected final Residential module = new Residential();

        protected ResidentialModuleBuilder(final ModuleBuilder parent) {
            this.parent = parent;
        }

        public Residential getModule() {
            return module;
        }

        public ModuleBuilder.ResidentialModuleBuilder implicitEnduses(final String implicitEnduses) {
            this.module.setImplicitEnduses(implicitEnduses);
            return this;
        }

        public ModuleBuilder and() {
            return this.parent;
        }
    }
}