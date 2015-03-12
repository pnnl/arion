/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.gld.GldSerializable;

/**
 * GridLabD Modules
 *
 * @author nord229
 */
public abstract class Module implements GldSerializable {

    /**
     * Get the module name representing this module in the GridLabD file
     * 
     * @return module name
     */
    protected abstract String getGLDObjectType();

    /**
     * Does this module have properties? - used when writing to the GridLabD file
     * 
     * @return true if has properties
     */
    protected abstract boolean hasProperties();

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("module ").append(getGLDObjectType());
        if (hasProperties()) {
            sb.append(" {\n");
            this.writeGLDProperties(sb);
            sb.append("}\n");
        } else {
            sb.append(";\n");
        }
    }

    /**
     * Write the GridLabD properties for this module to the StringBuilder
     * 
     * @param sb
     *            StringBuilder to use when writing
     */
    protected abstract void writeGLDProperties(final StringBuilder sb);
}
