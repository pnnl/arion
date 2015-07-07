/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * @author nord229
 *
 */
public class HeatTemplateReader {
    
    private static final ObjectMapper mapper;
    
    static {
        final YAMLFactory factory = new YAMLFactory();
        factory.disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID);
        factory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        mapper = new ObjectMapper(factory);
    }

    public static HeatTemplate readHeatTemplate(final InputStream is) throws IOException {
        return mapper.readValue(is, HeatTemplate.class);
    }

    public static HeatTemplate readHeatTemplate(final Reader reader) throws IOException {
        return mapper.readValue(reader, HeatTemplate.class);
    }

}
