package org.wso2.samples.activedirectoryuservalidationservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestResource {
	@PostMapping(path = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Validate User Credentials Against Active Directory",
			description = "This endpoint will return true/false based on whether the user credentials are " +
					"validated against the Active Directory user store",
			tags = "Validate User Against AD")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The request processed successfully",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = Response.class)))
	})
	public ResponseEntity<ResponseBuilder> validateUser(@RequestBody RequestBuilder requestBuilder) {
		ActiveDirectoryUserValidator activeDirectoryUserValidator = new ActiveDirectoryUserValidator();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseBuilder(activeDirectoryUserValidator.validateUser(requestBuilder.getCredentials())));
	}
}
