package org.wso2.samples.activedirectoryuservalidationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ActiveDirectoryUserValidationServiceApplication {

	public static void main(String[] args) {
		ApplicationConfigLoader applicationConfigLoader = new ApplicationConfigLoader();
		SpringApplication.run(ActiveDirectoryUserValidationServiceApplication.class, args);
	}

}
