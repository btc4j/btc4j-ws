/*
 The MIT License (MIT)
 
 Copyright (c) 2013, 2014 by Guillermo Gonzalez, btc4j.org

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package org.btc4j.ws.impl;

import java.util.logging.Logger;

import org.btc4j.ws.BtcDaemonServicePort;
import org.btc4j.ws.BtcException;
import org.btc4j.ws.HelpReq;
import org.btc4j.ws.HelpResp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@javax.jws.WebService(
                      serviceName = "BtcDaemonService",
                      portName = "BtcDaemonServicePort",
                      targetNamespace = "http://www.btc4j.org/ws/",
                      endpointInterface = "org.btc4j.ws.BtcDaemonServicePort")
                     
public class BtcDaemonServicePortImpl implements BtcDaemonServicePort {
    private static final Logger LOG = Logger.getLogger(BtcDaemonServicePortImpl.class.getName());

    public HelpResp help(HelpReq params) throws BtcException    { 
        try {
        	LOG.info("params: " + params);
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            LOG.info("auth: " + auth);
            LOG.info("principal: " + auth.getPrincipal());
            LOG.info("credentials: " + auth.getCredentials());
            HelpResp _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
