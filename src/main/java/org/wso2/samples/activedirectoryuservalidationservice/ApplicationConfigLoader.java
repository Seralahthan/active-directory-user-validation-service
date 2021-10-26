package org.wso2.samples.activedirectoryuservalidationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;

@Configuration
@Component
public class ApplicationConfigLoader {
	@Value("${server.ssl.trust-store}")
	private String trustStore;

	@Value("${server.ssl.trust-store-password}")
	private String trustStorePassword;

	public ApplicationConfigLoader() {}

	@PostConstruct
	public void loadConfig() {
		String trustStorePathWithoutClasspath = "/" + trustStore.split(":")[1];
		URL url = ApplicationConfigLoader.class.getResource(trustStorePathWithoutClasspath);
		if (url != null) {
			String trustStorePath = url.getPath();
			System.setProperty("javax.net.ssl.trustStore", trustStorePath);
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
			System.setProperty("com.sun.jndi.ldap.object.disableEndpointIdentification","true");
		}
	}
}
