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

package org.btc4j.ws;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BtcDaemonServiceTest {
	private static final boolean BTCWS_STOP = false;
	private static final String BTCWS_SPRING = "btc4j-ws-test.xml";
	private static final String BTCWS_BEAN = "btcDaemonService";
	private static ClassPathXmlApplicationContext BTCWS_CTX;
	private static BtcDaemonServicePort BTCWS_SVC;
	
	@BeforeClass
	public static void init() throws Exception {
		BTCWS_CTX = new ClassPathXmlApplicationContext(BTCWS_SPRING);
		BTCWS_SVC = (BtcDaemonServicePort) BTCWS_CTX.getBean(BTCWS_BEAN);
	}
	
	@AfterClass
	public static void cleanUp() throws Exception {
		BTCWS_CTX.close();
		if (BTCWS_STOP) {
			String stop = BTCWS_SVC.stop();
			assertNotNull(stop);
			assertTrue(stop.length() >= 0);
		}
	}
	
	@Test
	public void getConnectionCount() throws BtcWsException {
		BigInteger connections = BTCWS_SVC.getConnectionCount();
		assertTrue(connections.compareTo(BigInteger.ZERO) >= 0);
	}
	
	@Test
	public void help() throws BtcWsException {
		String help = BTCWS_SVC.help("");
		assertNotNull(help);
		assertTrue(help.length() >= 0);
		help = BTCWS_SVC.help(null);
		assertNotNull(help);
		assertTrue(help.length() >= 0);
		help = BTCWS_SVC.help("walletlock");
		assertNotNull(help);
		assertTrue(help.length() >= 0);
		help = BTCWS_SVC.help("fakecommand");
		assertNotNull(help);
		assertTrue(help.length() >= 0);
	}

}
