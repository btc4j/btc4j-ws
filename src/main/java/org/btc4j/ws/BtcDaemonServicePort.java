package org.btc4j.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.btc4j.org/ws/", name = "BtcDaemonServicePort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BtcDaemonServicePort {

    @WebResult(name = "help", targetNamespace = "", partName = "help")
    @WebMethod
    public java.lang.String help(
        @WebParam(partName = "command", name = "command", targetNamespace = "")
        java.lang.String command
    ) throws BtcWsException;

    @WebResult(name = "connections", targetNamespace = "", partName = "connections")
    @WebMethod
    public java.math.BigInteger getConnectionCount() throws BtcWsException;

    @WebResult(name = "message", targetNamespace = "", partName = "message")
    @WebMethod
    public java.lang.String stop() throws BtcWsException;
}
