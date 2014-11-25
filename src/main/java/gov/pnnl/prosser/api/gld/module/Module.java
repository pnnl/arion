/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.GLDSerializable;

/**
 * GridLabD Modules
 *
 * @author nord229
 */
public abstract class Module implements GLDSerializable {

    public abstract String getGLDObjectType();

    public abstract boolean hasProperties();

    @Override
    public void writeGLDString(final StringBuilder sb) {
        sb.append("module ").append(getGLDObjectType());
        if (hasProperties()) {
            sb.append(" {\n");
            this.writeGLDProperties(sb);
            sb.append("}\n");
        } else {
            sb.append(";\n");
        }
    }

    protected abstract void writeGLDProperties(final StringBuilder sb);
}
