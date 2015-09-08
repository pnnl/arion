package gov.pnnl.prosser.api.ns3.enums;

/**
 * Created by happ546 on 9/8/2015.
 */
public enum MobilityModel {

    ConstantPosition,

    ConstantVelocity,

    ConstantAcceleration;

    public String toString() {
        return "ns3::" + name() + "MobilityModel";
    }

}
