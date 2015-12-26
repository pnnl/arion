package gov.pnnl.prosser.api.ns3.datacollection;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.datacollection.probes.Probe;
import gov.pnnl.prosser.api.ns3.enums.FileFormat;

/**
 * Created by happ546 on 12/21/2015.
 */
public class FileHelper extends AbstractNs3Object {

    public FileHelper(String name) {
        setName(name);
    }

    /**
     *
     * @param fileName the name of the output file
     * @param format the format of the output file
     */
    public void configureFile(String fileName, FileFormat format) {
        appendPrintInfo(getName() + ".ConfigureFile (\"" +
                        fileName + "\", " +
                        "FileAggregator::" + format.toString() + ");\n");
    }

    /**
     *
     * @param s a C-style sprintf() formatted String used to specify the ouput format
     *          of the input data for this file
     */
    public void setFormat(String s) {
        String[] split = s.split("\t");
        int n = split.length;
        if (n > 10) {
            throw new IllegalArgumentException("Only 10 data dimensions accepted.");
        }
        appendPrintInfo(getName() + ".Set" + n + "dFormat (\"" + s + "\");\n");
    }

    /**
     *
     * @param strings an optional list of C-style Strings, formatted for sprintf() output
     */
    private void setNdFormat(String... strings) {
        int n = strings.length;
        if (n > 10) {
            throw new IllegalArgumentException("Only 10 data dimensions accepted.");
        }
        appendPrintInfo(getName() + ".Set" + n + "dFormat (\"");
        for (int i = 0; i < n; i++) {
            appendPrintInfo(strings[i]);
        }
        appendPrintInfo("\");\n");
    }

    public void writeProbe(Probe probe) {
        appendPrintInfo(getName() + ".WriteProbe (\"" +
                        probe.getType() + "\", \"" +
                        probe.getPath() + "\", \"" +
                        probe.getSource() + "\");\n");
    }
}
