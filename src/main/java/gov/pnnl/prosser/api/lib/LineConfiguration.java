/**
 *
 */
package gov.pnnl.prosser.api.lib;

/**
 * General Line Configuration
 *
 * @author nord229
 */
public abstract class LineConfiguration<C extends Conductor> extends PowerflowLibrary {

    public LineConfiguration() {
    }

    public LineConfiguration(final String name) {
        super(name);
    }

    public <T extends LineConfiguration<C>, Z extends AbstractBuilder<C, T, Z>> LineConfiguration(final AbstractBuilder<C, T, Z> builder) {
        super(builder);
    }

    public static abstract class AbstractBuilder<C extends Conductor, T extends LineConfiguration<C>, Z extends AbstractBuilder<C, T, Z>> extends PowerflowLibrary.AbstractBuilder<T, Z> {

    }

}
