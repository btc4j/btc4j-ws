package org.btc4j.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2014-01-28T04:32:33.703-06:00
 * Generated source version: 2.7.7
 * 
 */
@WebService(targetNamespace = "http://www.btc4j.org/ws/", name = "BtcDaemonServicePort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BtcDaemonServicePort {

    @WebMethod
    public void backupWallet(
        @WebParam(partName = "destination", name = "destination", targetNamespace = "")
        java.lang.String destination
    ) throws BtcWsException;
    
    @WebResult(name = "privateKey", targetNamespace = "", partName = "privateKey")
    @WebMethod
    public java.lang.String dumpPrivateKey(
        @WebParam(partName = "address", name = "address", targetNamespace = "")
        java.lang.String address
    ) throws BtcWsException;

    @WebResult(name = "account", targetNamespace = "", partName = "account")
    @WebMethod
    public java.lang.String getAccount(
        @WebParam(partName = "address", name = "address", targetNamespace = "")
        java.lang.String address
    ) throws BtcWsException;
    
    @WebResult(name = "address", targetNamespace = "", partName = "address")
    @WebMethod
    public java.lang.String getAccountAddress(
        @WebParam(partName = "account", name = "account", targetNamespace = "")
        java.lang.String account
    ) throws BtcWsException;
    
    @XmlList
    @WebResult(name = "addresses", targetNamespace = "", partName = "addresses")
    @WebMethod
    public java.lang.String[] getAddressesByAccount(
        @WebParam(partName = "account", name = "account", targetNamespace = "")
        java.lang.String account
    ) throws BtcWsException;

    @WebResult(name = "connections", targetNamespace = "", partName = "connections")
    @WebMethod
    public java.math.BigInteger getConnectionCount() throws BtcWsException;

    @WebResult(name = "help", targetNamespace = "", partName = "help")
    @WebMethod
    public java.lang.String help(
        @WebParam(partName = "command", name = "command", targetNamespace = "")
        java.lang.String command
    ) throws BtcWsException;

    @WebResult(name = "message", targetNamespace = "", partName = "message")
    @WebMethod
    public java.lang.String stop() throws BtcWsException;
}