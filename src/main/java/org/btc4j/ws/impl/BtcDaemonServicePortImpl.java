/*
 The MIT License (MIT)
 
 Copyright (c) 2013, 2014 by ggbusto

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

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.logging.Logger;

import javax.jws.WebService;

import org.btc4j.core.BtcException;
import org.btc4j.daemon.BtcDaemon;
import org.btc4j.ws.BtcDaemonServicePort;
import org.btc4j.ws.BtcFault;
import org.btc4j.ws.BtcWsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebService(serviceName = "BtcDaemonService", portName = "BtcDaemonServicePort", targetNamespace = "http://www.btc4j.org/ws/", endpointInterface = "org.btc4j.ws.BtcDaemonServicePort")
public class BtcDaemonServicePortImpl implements BtcDaemonServicePort {
	private static final Logger LOG = Logger
			.getLogger(BtcDaemonServicePortImpl.class.getName());
	private static final int BTC4JWS_ERROR_CODE = -32078;
	private static final String BTC4JWS_ERROR_MESSAGE = "btc4j-ws error";

	@Autowired
	private URL daemonUrl;

	private BtcWsException btcWsException(Throwable t) {
		LOG.severe(String.valueOf(t));
		BtcFault fault = new BtcFault();
		if (t instanceof BtcException) {
			BtcException e = (BtcException) t;
			fault.setCode(BigInteger.valueOf(e.getCode()));
			fault.setMessage(e.getMessage());
		} else {
			fault.setCode(BigInteger.valueOf(BTC4JWS_ERROR_CODE));
			fault.setMessage(BTC4JWS_ERROR_MESSAGE + ": " + t.getMessage());
		}
		return new BtcWsException(t.getMessage(), fault, t);
	}

	private BtcDaemon getDaemon(String method) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		LOG.info(auth.getName() + "@" + daemonUrl + "/" + method);
		return new BtcDaemon(daemonUrl, String.valueOf(auth.getName()),
				String.valueOf(auth.getCredentials()));
	}

	@Override
	public void backupWallet(String destination) throws BtcWsException {
		try {
			getDaemon("backupWallet").backupWallet(new File(destination));
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}
	
	@Override
	public String dumpPrivateKey(String address) throws BtcWsException {
		try {
			return getDaemon("dumpPrivateKey").dumpPrivateKey(address);
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}
	
	@Override
	public String getAccount(String address) throws BtcWsException {
		try {
			return getDaemon("getAccount").getAccount(address);
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}
	
	@Override
	public String getAccountAddress(String account) throws BtcWsException {
		try {
			return getDaemon("getAccountAddress").getAccountAddress(account);
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}

	@Override
	public String[] getAddressesByAccount(String account) throws BtcWsException {
		try {
			return getDaemon("getAddressesByAccount").getAddressesByAccount(account).toArray(new String[]{});
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}
	
	@Override
	public BigInteger getConnectionCount() throws BtcWsException {
		try {
			return BigInteger.valueOf(getDaemon("getConnectionCount").getConnectionCount());
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}

	@Override
	public String help(String command) throws BtcWsException {
		try {
			return getDaemon("help").help(command);
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}

	@Override
	public String stop() throws BtcWsException {
		try {
			return getDaemon("stop").stop();
		} catch (Throwable t) {
			throw btcWsException(t);
		}
	}
}
