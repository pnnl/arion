/**
 * 
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.RepairDistributionType;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;
    
/**
 * @author sund130
 *
 */
public class Fuse extends LinkObject {
    private double currentLimit;
    private double meanReplacementTime;
    private RepairDistributionType repairDistributionType;
    private SwitchStatus status;
    
    /**
     * @param simulator
     */
    public Fuse(GldSimulator simulator) {
        super(simulator);
    }

    /**
     * @return the currentLimit
     */
    public double getCurrentLimit() {
        return currentLimit;
    }

    /**
     * @param currentLimit the currentLimit to set
     */
    public void setCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
    }

    /**
     * @return the meanReplacementTime
     */
    public double getMeanReplacementTime() {
        return meanReplacementTime;
    }

    /**
     * @param meanReplacementTime the meanReplacementTime to set
     */
    public void setMeanReplacementTime(double meanReplacementTime) {
        this.meanReplacementTime = meanReplacementTime;
    }

    /**
     * @return the repairDistributionType
     */
    public RepairDistributionType getRepairDistributionType() {
        return repairDistributionType;
    }

    /**
     * @param repairDistributionType the repairDistributionType to set
     */
    public void setRepairDistributionType(RepairDistributionType repairDistributionType) {
        this.repairDistributionType = repairDistributionType;
    }

    /**
     * @return the status
     */
    public SwitchStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(SwitchStatus status) {
        this.status = status;
    }

    /**
     * @see gov.pnnl.prosser.api.gld.AbstractGldObject#getGldObjectType()
     */
    @Override
    protected String getGldObjectType() {
        return "fuse";
    }
}