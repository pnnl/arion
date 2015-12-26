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
    private TraceSource source;

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


}
