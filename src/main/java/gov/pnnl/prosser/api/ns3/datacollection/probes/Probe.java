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

package gov.pnnl.prosser.api.ns3.datacollection.probes;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.TraceSource;
import gov.pnnl.prosser.api.ns3.obj.Router;

/**
 * Created by happ546 on 12/21/2015.
 */
public abstract class Probe extends AbstractNs3Object {

    private final String type;
    private String path;
    private TraceSource source, probeSource;

    public Probe(String name) {
        setNameString(name);
        getAsPointer();
        type = "ns3::" + this.getClass().getSimpleName();
    }

    /**
     * @param router the Router to connect this Probe to
     * @param source the TraceSource of obj to connect this Probe to
     */
    // TODO abstract this to allow any AbstractNs3Object to be source object?
    //  this could be a more specific private method for a general
    //  setSource(AbstractNs3Object o, TraceSource s)
    public void setSource(Router router, TraceSource source) {
        this.source = source;
        String srcStr = source.toString();
        String srcPath = source.getClass().getSimpleName();
        // TODO not getting any output in file, no Gnuplot
        path = "/NodeList/" + router.getNode().getId() + "/$ns3::" + srcPath + "/" + srcStr;

        appendPrintInfo(getName() + "->ConnectByObject (\"" +
                        source.toString() + "\", " +
                        router.getNode().getName() + ");\n");
    }

    /**
     * Sets the Probe output source to the given TraceSource
     * @param source
     */
    protected void setProbeSource(TraceSource source) {
        this.probeSource = source;
    }

    /**
     *
     * @return the name of the pointer to this Probe
     */
    @Override
    public String getName()
    {
        return this.getPointerName();
    }

    /**
     *
     * @param obj the AbstractNs3Object to connect this Probe to
     * @param source the TraceSource of obj to connect this Probe to
     */
    public void connectByObject(AbstractNs3Object obj, TraceSource source) {
        appendPrintInfo(getName() + "->ConnectByObject (\"" +
                        source.toString() + "\", " +
                        obj.getName() + ");\n");
    }

    /**
     * @return the trace path for the TraceSource this Probe is connected to
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the type of this Probe
     */
    public String getType() {
        return type;
    }

    /**
     * @return the name of the TraceSource this Probe is connected to
     */
    public String getSource() {
        return source.toString();
    }

    /**
     *
     * @return the name of the TraceSource of this Probe's output
     */
    public String getProbeSource() {
        return probeSource.toString();
    }

}
