package org.wso2.samples.activedirectoryuservalidationservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	@JsonProperty("status")
	private final String status;

	public Response(String status) {
		this.status = status;
	}
}
