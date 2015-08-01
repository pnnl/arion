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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nord229
 *
 */
public class HeatTemplateWriter {

    private static final Pattern tokenPattern = Pattern.compile("\\@([^@]*)\\@");

    public static void writeHeatTemplate(final Path path, List<GldSimulator> gldSimulators, final Ns3Simulator ns3Simulator, final FncsSimulator fncsSimulator) throws IOException {
        Files.createDirectories(path);
        final String baseTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-base.yaml")), StandardCharsets.UTF_8);
        final String subTemplateString = new String(Files.readAllBytes(Paths.get("res/heat-prosser-sub.yaml")), StandardCharsets.UTF_8);

        Map<String, Object> params = new HashMap<>();
        final StringBuilder sb = new StringBuilder();
        sb.append(baseTemplateString);

        params.put("FNCS", "$fncs: {get_attr: [fncs, first_address]}");
        int i = 0;
        for (final GldSimulator sim : gldSimulators) {
            params.put("ITEM", "gridlabd" + i);
            params.put("EXEC", "gridlabd " + sim.getName());
            final String gldHeatNode = process(subTemplateString, params);
            sb.append(gldHeatNode);
            i++;
        }

        if (ns3Simulator != null) {
            params.put("ITEM", "ns3");
            params.put("EXEC", "./ns3");
            final String ns3HeatNode = process(subTemplateString, params);
            sb.append(ns3HeatNode);
        }

        if (fncsSimulator != null) {
            params.put("FNCS", "");
            params.put("ITEM", "fncs");
            params.put("EXEC", "fncsbroker " + (gldSimulators.size() + 1));
            final String fncsHeatNode = process(subTemplateString, params);
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
