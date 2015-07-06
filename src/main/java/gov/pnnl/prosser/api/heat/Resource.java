/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author nord229
 *
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXISTING_PROPERTY, property="type", defaultImpl=Resource.DefaultResource.class)
@JsonSubTypes({@JsonSubTypes.Type(NeutronNetwork.class)})
public class Resource {
    
    private String type;
    
    public static class DefaultResource extends Resource {
        private final Map<String, Object> properties = new HashMap<>();

        /**
         * @return the properties
         */
        public Map<String, Object> getProperties() {
            return properties;
        }
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
