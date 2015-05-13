/**
 *
 */
package gov.pnnl.prosser.api;

/**
 * FNCS Simulator configuration
 * 
 * @author nord229
 */
public class FncsSimulator {

    private String broker = "localhost";

    /**
     * Get the broker to connect to
     * 
     * @return the broker
     */
    public String getBroker() {
        return broker;
    }

    /**
     * Set the broker to connect to
     * 
     * @param broker
     *            the broker to set
     */
    public void setBroker(String broker) {
        this.broker = broker;
    }

}
