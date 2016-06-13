/**
 *
 */
package gov.pnnl.prosser.api;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;

/**
 * @author nord229
 *
 */
public class HeatTemplateWriter {

    private static final Pattern tokenPattern = Pattern.compile("\\@([^@]*)\\@");

    public static void writeHeatTemplate(final Path path, final Experiment experiment) throws IOException {
        Files.createDirectories(path);
        final String baseTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-base.yaml")), StandardCharsets.UTF_8);
        final String gldTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-gld.yaml")), StandardCharsets.UTF_8);
        final String ns3TemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-ns3.yaml")), StandardCharsets.UTF_8);
        final String fncsTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-fncs.yaml")), StandardCharsets.UTF_8);
//        final String matpowerTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-matpower.yaml")), StandardCharsets.UTF_8);

        Map<String, Object> params = new HashMap<>();
        final StringBuilder sb = new StringBuilder();
        sb.append(baseTemplateString);

        params.put("FNCS", "$fncs: {get_attr: [fncs, first_address]}");
        int i = 0;
        for (final GldSimulator sim : experiment.getGldSimulators()) {
            params.put("ITEM", "gridlabd" + i);
            params.put("EXEC", "gridlabd " + sim.getName());
            params.put("NAME", sim.getName());
            params.put("EXP", experiment.getName());
            params.put("UUID", experiment.getUUID());
            final String gldHeatNode = process(gldTemplateString, params);
            sb.append(gldHeatNode);
            i++;
        }
//TODO: This is janky. The file names are always the same; only the directory names differ; Fix to use different file names
        for (final AbstractNs3SimulatorV2 ns3 : experiment.getNs3SimulatorV2()) {
            params.put("ITEM", "ns3");
            params.put("EXP", experiment.getName());
            params.put("NAME", ns3.getName());
            params.put("EXEC", "./firstN LinkModelGLDNS3.txt");
            params.put("UUID", experiment.getUUID());
            final String ns3HeatNode = process(ns3TemplateString, params);
            sb.append(ns3HeatNode);
        }

        if (experiment.fncsSimulator() != null) {
            params.put("FNCS", "");
            params.put("ITEM", "fncs");
            params.put("EXEC", "fncsbroker " + (experiment.getGldSimulators().size() + experiment.getNs3SimulatorV2().size()));
            params.put("UUID", experiment.getUUID());
            final String fncsHeatNode = process(fncsTemplateString, params);
            sb.append(fncsHeatNode);
        }

        try (final BufferedWriter writer = Files.newBufferedWriter(path.resolve("heat.yaml"), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
    }

    public static String process(String template, Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        Matcher myMatcher = tokenPattern.matcher(template);
        while (myMatcher.find()) {
            String field = myMatcher.group(1);
            myMatcher.appendReplacement(sb, "");
            sb.append(params.get(field));
        }
        myMatcher.appendTail(sb);
        return sb.toString();
    }

}
