package com.cee.ljr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cee.ljr.config.ApplicationConfig;

public class LogcopJiraReports {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		// weekly status report generation
		WeeklyStatusReportGenerator srGenerator = ctx.getBean(WeeklyStatusReportGenerator.class);
		String weekEndDate = "9/11/2016";
		//String sprintNumber = "13";
		//srGenerator.generateV1(weekEndDate);
		srGenerator.generateV2(weekEndDate);
		
		//developer sprint reports generation
		//initializeProgramSprints();
		
	}
	
	

}
