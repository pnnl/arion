/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSerializable;
import gov.pnnl.prosser.api.GldUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nord229
 *
 */
public abstract class AbstractGldClass implements GldSerializable {

    private final Map<String, String> map = new HashMap<>();

    public void addField(final String name, final String type) {
        map.put(name, type);
    }

    public void removeField(final String name) {
        map.remove(name);
    }

    public void clear() {
        map.clear();
    }

    protected abstract String getGldClassName();

    @Override
    public void writeGldString(final StringBuilder sb) {
        sb.append("class ").append(this.getGldClassName()).append(" {\n");
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            GldUtils.writeProperty(sb, entry.getValue(), entry.getKey());
        }
        sb.append("}\n");
    }

}
