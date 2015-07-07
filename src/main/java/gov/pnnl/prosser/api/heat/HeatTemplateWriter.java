/**
 * 
 */
package gov.pnnl.prosser.api.heat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * @author nord229
 *
 */
public class HeatTemplateWriter {
    
    private static final ObjectMapper mapper;
    
    static {
        final YAMLFactory factory = new YAMLFactory();
        factory.disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID);
        factory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        mapper = new ObjectMapper(factory);
    }

    public static void writeHeatTemplate(final Path path, final HeatTemplate template) throws IOException {
        Files.createDirectories(path);
        try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve("heat.yaml"), StandardCharsets.UTF_8)) {
            mapper.writeValue(writer, template);
        }
    }

    public static void main(String... args) throws IOException {
        try (final BufferedReader reader = Files.newBufferedReader(Paths.get("res/heat.yaml"), StandardCharsets.UTF_8)) {
            final HeatTemplate template = HeatTemplateReader.readHeatTemplate(reader);
            template.getDescription();
        }
    }

}
