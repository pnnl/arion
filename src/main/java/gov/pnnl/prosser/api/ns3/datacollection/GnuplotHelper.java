package gov.pnnl.prosser.api.ns3.datacollection;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.datacollection.probes.Probe;
import gov.pnnl.prosser.api.ns3.enums.KeyLocation;

/**
 * Created by happ546 on 12/21/2015.
 */
public class GnuplotHelper extends AbstractNs3Object {

    public GnuplotHelper(String name) {
        setName(name);
    }

    /**
     * Note: The default file extension is '.png'
     *
     * @param fileName name of gnuplot related files to write with no extension
     * @param title title of the plot
     * @param xLegend legend for the x (horizontal) axis
     * @param yLegend legend for the y (vertical) axis
     */
    public void configurePlot(String fileName, String title,
                              String xLegend, String yLegend) {
        appendPrintInfo(getName() + ".ConfigurePlot (\"" +
                fileName + "\", \"" +
                title + "\", \"" +
                xLegend + "\", \"" +
                yLegend + "\");\n");
    }

    /**
     * Note:  the location of the key/legend for this plot defaults to "KEY_INSIDE" the plot
     *
     * @param probe the probe to use as the data source
     * @param label the label for this data series
     */
    public void plotProbe(Probe probe, String label) {
        appendPrintInfo(getName() + ".PlotProbe (\"" +
                probe.getType() + "\", \"" +
                probe.getPath() + "\", \"" +
                probe.getProbeSource() + "\", \"" +
                label + "\");\n");
    }

    /**
     *
     * @param probe the probe to use as the data source
     * @param label the label for this data series
     * @param keyLoc the location of the key for the plot
     *
     *  typeId	the type ID for the probe used when it is created.
     *  path	Config path to access the probe.
     *  probeTraceSource	the probe trace source to access.
     *  title	the title to be associated to this dataset
     *  keyLocation	the location of the key in the plot.;
     */
    public void plotProbe(Probe probe, String label, KeyLocation keyLoc) {
        appendPrintInfo(getName() + ".PlotProbe (\"" +
                        probe.getType() + "\", \"" +
                        probe.getPath() + "\", \"" +
                        probe.getProbeSource() + "\", \"" +
                        label + "\", " +
                        "GnuplotAggregator::" + keyLoc.toString() + ");\n");
    }
}
