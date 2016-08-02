package com.cee.wsr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cee.wsr.config.ApplicationConfig;

public class Wsr {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		StatusReportGenerator srGenerator = ctx.getBean(StatusReportGenerator.class);
		srGenerator.generate();
	}

}
