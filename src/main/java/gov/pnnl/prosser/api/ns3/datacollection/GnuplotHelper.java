/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
*    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
*    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
*    and may permit others to do so, subject to the following conditions:
*    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
*       the following disclaimers.
*    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
*       the following disclaimer in the documentation and/or other materials provided with the distribution.
*    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
*       form whatsoever without the express written consent of Battelle.
* 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
*    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
*    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
*    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*                                PACIFIC NORTHWEST NATIONAL LABORATORY
*                                            operated by
*                                              BATTELLE
*                                              for the
*                                  UNITED STATES DEPARTMENT OF ENERGY
*                                   under Contract DE-AC05-76RL01830
*/

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
