package com.cee.wsr.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	private final String appTitle;
	private final String appAuthor;
	private final String appVersion;
	private final String logUrl;

	@Autowired
	public ApplicationProperties(@Value("${app.title}") String appTitle,
			@Value("${app.author}") String appAuthor,
			@Value("${app.version}") String appVersion,
			@Value("${log.url}") String logUrl) {
		// Validation can go here if needed, throwing IllegalArgumentException
		// if it fails..
		this.appTitle = appTitle;
		this.appAuthor = appAuthor;
		this.appVersion = appVersion;
		this.logUrl = logUrl;
	}

	/**
	 * @return the appTitle
	 */
	public String getAppTitle() {
		return appTitle;
	}

	/**
	 * @return the appAuthor
	 */
	public String getAppAuthor() {
		return appAuthor;
	}

	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}
	
	public String getLogUrl() {
		return System.getProperty("user.dir") + logUrl;
	}
	
	
}
