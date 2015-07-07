/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author nord229
 *
 */
@JsonTypeName("OS::Neutron::Net")
public class NeutronNetwork extends Resource {
    
    private final NeutronNetworkProperties properties = new NeutronNetworkProperties();
    
    public static class NeutronNetworkProperties {
        private Object name;

        /**
         * @return the name
         */
        public Object getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(Object name) {
            this.name = name;
        }
        
        
    }

//    /**
//     * @return the properties
//     */
//    public NeutronNetworkProperties getProperties() {
//        return properties;
//    }

}
