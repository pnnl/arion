package gov.pnnl.prosser.api.ns3.datacollection;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.obj.NodeContainer;
import gov.pnnl.prosser.api.ns3.obj.Router;

/**
 * Created by happ546 on 1/5/2016.
 */
public class FlowMonitorHelper extends AbstractNs3Object {

    private final String flowMonitorName;
    private static int instanceCount;

    public FlowMonitorHelper(String name) {
        if (instanceCount == 0) {
            instanceCount++;
        } else if (instanceCount > 1) {
            try {
                throw new InstantiationException("Cannot create more than one FlowMonitorHelper " +
                        "per experiment.");
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        setName(name);
        flowMonitorName = "flowMonitor";
        appendPrintInfo("Ptr<FlowMonitor> " + flowMonitorName + ";\n");
    }

    /**
     * Installs a FlowMonitor on each Node in the given NodeContainer
     * @param nc the NodeContainer
     */
    public void install(NodeContainer nc) {
        appendPrintInfo(flowMonitorName + " = " + getName() + ".Install (" + nc.getName() + ");\n");
    }

    /**
     * Installs a FlowMonitor on the Node of the given Router
     * @param r the Router to install the FlowMonitor on
     */
    public void install(Router r) {
        appendPrintInfo(flowMonitorName + " = " + getName() + ".Install (" + r.getNode().getName() + ");\n");
    }

    /**
     * Installs a FlowMonitor on all Nodes in the simulation.
     */
    public void installAll() {
        appendPrintInfo(flowMonitorName + " = " + getName() + ".InstallAll ();\n");
    }

    // TODO implement way to append this AFTER Simulator::Run()
    public void serializeToXmlFile(String fileName, boolean enableHist, boolean enableProbes) {
        appendPostSimInfo(flowMonitorName + "->" + "SerializeToXmlFile (\""
                            + fileName + "\", "
                            + enableHist + ", "
                            + enableProbes + ");\n");
    }
}
