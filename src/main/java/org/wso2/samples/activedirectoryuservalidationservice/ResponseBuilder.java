package org.wso2.samples.activedirectoryuservalidationservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBuilder {
	@JsonProperty("response")
	private final Response response;

	@JsonCreator
	public ResponseBuilder(@JsonProperty("response") Response response) {
		this.response = response;
	}
}

