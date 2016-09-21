package com.cee.ljr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.report.generator.WeeklyStatusReportGenerator;

public class LogcopJiraReports {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		// weekly status report generation
		String weekEndDate = "9/11/2016";
		String wsrReportPath = null;
		WeeklyStatusReportGenerator srGenerator = ctx.getBean(WeeklyStatusReportGenerator.class);
		srGenerator.generateV2(weekEndDate, wsrReportPath);
		
		//String sprintNumber = "13";
		//srGenerator.generateV1(weekEndDate);
		
	}
	
	

}
