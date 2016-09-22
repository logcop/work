package com.cee.ljr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.report.generator.DeveloperSprintReportGenerator;

public class LogcopJiraReports {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		// weekly status report generation
		String weekEndDate = "9/11/2016";
		String pathToReports = null;
		//WeeklyStatusReportGenerator wsrGenerator = ctx.getBean(WeeklyStatusReportGenerator.class);
		//wsrGenerator.generateV2(weekEndDate, pathToReports);
		
		int sprintNumber = 10;
		DeveloperSprintReportGenerator dsrGenerator = ctx.getBean(DeveloperSprintReportGenerator.class);
		dsrGenerator.generateAll(sprintNumber, pathToReports);
		
	}
	
	

}
