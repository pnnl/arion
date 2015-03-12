/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldSerializable;
import gov.pnnl.prosser.api.gld.GldUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract GridLabD Class reference, (allows declaring variables to be referenced?)
 * 
 * @author nord229
 *
 */
public abstract class AbstractGldClass implements GldSerializable {

    private final Map<String, String> map = new HashMap<>();

    /**
     * Add a field to this class
     * 
     * @param name
     *            field name
     * @param type
     *            type (double, etc.)
     */
    public void addField(final String name, final String type) {
        map.put(name, type);
    }

    /**
     * Remove field by name
     * 
     * @param name
     *            field name
     */
    public void removeField(final String name) {
        map.remove(name);
    }

    /**
     * Clear this class reference of field declarations
     */
    public void clear() {
        map.clear();
    }

    /**
     * Get the class name representing this class in the GridLabD file
     * 
     * @return the class name
     */
    protected abstract String getGldClassName();

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("class ").append(this.getGldClassName()).append(" {\n");
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            GldUtils.writeProperty(sb, entry.getValue(), entry.getKey());
        }
        sb.append("}\n");
    }

}
