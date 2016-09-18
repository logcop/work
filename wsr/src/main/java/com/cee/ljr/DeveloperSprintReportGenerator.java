package com.cee.ljr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.domain.report.WeeklyStatusReport;
import com.cee.ljr.service.DeveloperSprintReportService;

@Component
public class DeveloperSprintReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportGenerator.class);
	
	@Autowired
	DeveloperSprintReportService developerSprintReportService;
	
	@Autowired
	
	
	public void generate(int sprintNumber) {
		//WeeklyStatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		//docxGenerator.generateDocument(statusReport);
		DeveloperSprintReport developerSprintReport = developerSprintReportService.getReport(sprintNumber);
	}
}
