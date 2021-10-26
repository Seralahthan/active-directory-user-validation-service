package org.wso2.samples.activedirectoryuservalidationservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBuilder {
	@JsonProperty("credentials")
	private final Credentials credentials;

	@JsonCreator
	public RequestBuilder(@JsonProperty("credentials") Credentials credentials) {
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}
}
