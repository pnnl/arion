/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author nord229
 *
 */
public class HeatTemplate {

    public enum TemplateVersion {
        ICEHOUSE("2013-05-23"),
        JUNO("2014-10-16"),
        KILO("2015-04-30");

        private final String versionDate;

        private TemplateVersion(final String versionDate) {
            this.versionDate = versionDate;
        }

        @JsonValue
        public String getVersionDate() {
            return this.versionDate;
        }
    }

    @JsonProperty("heat_template_version")
    private TemplateVersion heatTemplateVersion;

    private String description;

    private final Map<String, Parameter> parameters = new HashMap<>();

    private final Map<String, Resource> resources = new HashMap<>();
    
    /**
     * @return the heatTemplateVersion
     */
    public TemplateVersion getHeatTemplateVersion() {
        return heatTemplateVersion;
    }

    /**
     * @param heatTemplateVersion the heatTemplateVersion to set
     */
    public void setHeatTemplateVersion(TemplateVersion heatTemplateVersion) {
        this.heatTemplateVersion = heatTemplateVersion;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parameters
     */
    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    /**
     * @return the resources
     */
    public Map<String, Resource> getResources() {
        return resources;
    }

}
