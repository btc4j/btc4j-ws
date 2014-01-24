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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.validate.UsernameTokenValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BtcUsernameTokenValidator extends UsernameTokenValidator {
	@Override
	protected void verifyPlaintextPassword(UsernameToken usernameToken,
			RequestData data) throws WSSecurityException {
		final String username = String.valueOf(usernameToken.getName());
		final String password = String.valueOf(usernameToken.getPassword());
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		try {
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(new UserDetails() {
						private static final long serialVersionUID = -6884000787746976920L;
						@Override
						public Collection<? extends GrantedAuthority> getAuthorities() {
							return authorities;
						}
						@Override
						public String getPassword() {
							return password;
						}
						@Override
						public String getUsername() {
							return username;
						}
						@Override
						public boolean isAccountNonExpired() {
							return true;
						}
						@Override
						public boolean isAccountNonLocked() {
							return true;
						}
						@Override
						public boolean isCredentialsNonExpired() {
							return true;
						}
						@Override
						public boolean isEnabled() {
							return true;
						}
					}, password, authorities));
		} catch (Throwable t) {
			throw new WSSecurityException(t.getMessage(), t);
		}
	}
}