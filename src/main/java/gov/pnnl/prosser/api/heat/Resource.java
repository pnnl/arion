/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nord229
 *
 */
public class Resource {
    
    private String type;
    
    private final Map<String, Object> properties = new HashMap<>();

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
