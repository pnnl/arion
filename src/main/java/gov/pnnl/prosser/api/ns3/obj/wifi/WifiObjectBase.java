package gov.pnnl.prosser.api.ns3.obj.wifi;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * Created by happ546 on 9/1/2015.
 */
public class WifiObjectBase extends AbstractNs3Object {

    public void configureStandard(WifiPhyStandard std) {
        appendPrintInfo(getName() + "->ConfigureStandard (" + std.toString() + ")\n");
    }
}
