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

/**
 * @author nord229
 *
 */
public class HeatTemplateWriter {

    public static void writeHeatTemplate(final Path path, final HeatTemplate template) throws IOException {
        Files.createDirectories(path);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
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
