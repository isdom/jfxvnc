package org.jfxvnc.ui.persist;

/*
 * #%L
 * jfxvnc-ui
 * %%
 * Copyright (C) 2015 comtel2000
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


public class HistoryEntry {

    private final String host;
    private final int port;
    private int securityType;
    private String password;
    private String serverName;

    public HistoryEntry(String host, int port) {
	super();
	this.host = host;
	this.port = port;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((host == null) ? 0 : host.hashCode());
	result = prime * result + port;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	HistoryEntry other = (HistoryEntry) obj;
	if (host == null) {
	    if (other.host != null)
		return false;
	} else if (!host.equals(other.host))
	    return false;
	if (port != other.port)
	    return false;
	return true;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getServerName() {
	return serverName;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public String getHost() {
	return host;
    }

    public int getPort() {
	return port;
    }

    public int getSecurityType() {
	return securityType;
    }

    public void setSecurityType(int type) {
	this.securityType = type;
    }

    @Override
    public String toString() {
	return host + ":" + port + (serverName != null ? " (" + serverName + ")" : "");
    }

}
