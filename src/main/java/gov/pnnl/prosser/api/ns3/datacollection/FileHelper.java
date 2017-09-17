/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
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
 *******************************************************************************/

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
        int n = split.length + 1;
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
                        probe.getProbeSource() + "\");\n");
    }
}
