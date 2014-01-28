
package org.btc4j.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.7
 * 2014-01-28T04:32:33.656-06:00
 * Generated source version: 2.7.7
 */

@WebFault(name = "fault", targetNamespace = "http://www.btc4j.org/ws/")
public class BtcWsException extends Exception {
    
    private org.btc4j.ws.BtcFault fault;

    public BtcWsException() {
        super();
    }
    
    public BtcWsException(String message) {
        super(message);
    }
    
    public BtcWsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BtcWsException(String message, org.btc4j.ws.BtcFault fault) {
        super(message);
        this.fault = fault;
    }

    public BtcWsException(String message, org.btc4j.ws.BtcFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public org.btc4j.ws.BtcFault getFaultInfo() {
        return this.fault;
    }
}
