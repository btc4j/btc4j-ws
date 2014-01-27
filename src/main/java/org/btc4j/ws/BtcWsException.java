
package org.btc4j.ws;

import javax.xml.ws.WebFault;

@WebFault(name = "fault", targetNamespace = "http://www.btc4j.org/ws/")
public class BtcWsException extends Exception {
	private static final long serialVersionUID = 3945324880543237247L;
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
