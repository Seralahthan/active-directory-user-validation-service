package org.wso2.samples.activedirectoryuservalidationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ActiveDirectoryUserValidator {
	@Value("${ad.hostname}")
	private String adHostName;

	@Value("${ad.port}")
	private String adPort;

	@Value("${ad.baseDN}")
	private String adBaseDN;

	@Value("${ad.userSearchBase}")
	private String adUserSearchBase;

	@Value("${ad.userDN}")
	private String adUserDN;

	@Value("${ad.userPassword}")
	private String adUserPassword;

	@Value("${ad.userObjectClass}")
	private String adUserObjectClass;

	@Value("${ad.userNameAttribute}")
	private String adUserNameAttribute;

	private static LdapTemplate ldapTemplate;
	private static String userObjectClass;
	private static String userNameAttribute;
	private static String userSearchBase;

	public LdapContextSource loadActiveDirectoryContext() {
		LdapContextSource ctxSrc = new LdapContextSource();
		String ldapUrl = "ldaps://" + adHostName + ":" + adPort;
		ctxSrc.setUrl(ldapUrl);
		ctxSrc.setBase(adBaseDN);
		ctxSrc.setUserDn(adUserDN);
		ctxSrc.setPassword(adUserPassword);
		ctxSrc.afterPropertiesSet();
		return ctxSrc;
	}

	@Bean
	@PostConstruct
	public void createActiveDirectoryTemplate() {
		ldapTemplate = new LdapTemplate(loadActiveDirectoryContext());
		userObjectClass = adUserObjectClass;
		userNameAttribute = adUserNameAttribute;
		userSearchBase = adUserSearchBase;
	}

	public Response validateUser(Credentials cred) {
		String userName = cred.getUsername();
		String password = cred.getPassword();

		boolean isAuthenticated = authenticateUser(userName, password);

		return new Response(String.valueOf(isAuthenticated));
	}

	public boolean authenticateUser(String userName, String password) {
		String filter = "(&(objectclass=" + userObjectClass + ")(" + userNameAttribute + "=" + userName + "))";
		System.out.println("This is is ldap filter: " + filter);
		System.out.println("This is is ldap user search base: " + userSearchBase);
		return ldapTemplate.authenticate(userSearchBase, filter, password);
	}
}
