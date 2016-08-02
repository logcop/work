package com.cee.wsr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({ "com.cee.wsr" })
@PropertySource({"properties/app.properties", "properties/status-report.properties"})
public class ApplicationConfig {

	/**
	 * Ensures that placeholders are replaced with property values
	 */
	@Bean
	static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
