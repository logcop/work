package com.cee.ljr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.report.StatusReport;

@Component
public class DeveloperSprintReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportGenerator.class);
	
	public void generate(int sprintNumber) {
		StatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		docxGenerator.generateDocument(statusReport);
	}
}
