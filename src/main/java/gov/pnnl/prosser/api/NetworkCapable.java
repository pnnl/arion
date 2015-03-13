/**
 *
 */
package gov.pnnl.prosser.api;

/**
 * Network capable marker interface for objects that talk in NS-3
 * 
 * @author nord229
 *
 */
public interface NetworkCapable {

    /**
     * Get the network interface name that will be passed to FNCS and used in NS-3
     * 
     * @return the network interface name
     */
    public String getNetworkInterfaceName();

}
