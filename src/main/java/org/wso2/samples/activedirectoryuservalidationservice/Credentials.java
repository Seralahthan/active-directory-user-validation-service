package org.wso2.samples.activedirectoryuservalidationservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
	@JsonProperty("username")
	private final String username;
	@JsonProperty("password")
	private final String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() { return username;}

	public String getPassword() { return password;}
}
