/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author nord229
 *
 */
public class HeatTemplateReader {

    public static HeatTemplate readHeatTemplate(final InputStream is) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(is, HeatTemplate.class);
    }

    public static HeatTemplate readHeatTemplate(final Reader reader) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(reader, HeatTemplate.class);
    }

}
