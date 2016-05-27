/**
 * 
 */
package gov.pnnl.prosser.api.fncs;

import gov.pnnl.prosser.api.AbstractSimulator;

/**
 * @author nord229
 *
 */
public class Subscription {
    private String localVariable;

    private AbstractSimulator remoteSimulator;

    private String remoteVariable;

    /**
     * @return the localVariable
     */
    public String getLocalVariable() {
        return localVariable;
    }

    /**
     * @param localVariable
     *            the localVariable to set
     */
    public void setLocalVariable(String localVariable) {
        this.localVariable = localVariable;
    }

    /**
     * @return the remoteSimulator
     */
    public AbstractSimulator getRemoteSimulator() {
        return remoteSimulator;
    }

    /**
     * @param remoteSimulator
     *            the remoteSimulator to set
     */
    public void setRemoteSimulator(AbstractSimulator remoteSimulator) {
        this.remoteSimulator = remoteSimulator;
    }

    /**
     * @return the remoteVariable
     */
    public String getRemoteVariable() {
        return remoteVariable;
    }

    /**
     * @param remoteVariable
     *            the remoteVariable to set
     */
    public void setRemoteVariable(String remoteVariable) {
        this.remoteVariable = remoteVariable;
    }

}
