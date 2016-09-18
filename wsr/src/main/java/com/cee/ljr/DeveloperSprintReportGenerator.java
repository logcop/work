package com.cee.ljr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.domain.report.WeeklyStatusReport;
import com.cee.ljr.service.DeveloperSprintReportService;
import com.cee.ljr.utils.DevNameUtil;

@Component
public class DeveloperSprintReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportGenerator.class);
	
	@Autowired
	DeveloperSprintReportService developerSprintReportService;
	
	@Autowired
	
	
	public void generateAll(int sprintNumber) {
		//WeeklyStatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		//docxGenerator.generateDocument(statusReport);
		List<String> developerNames = DevNameUtil.getFullName(firstName)
		DeveloperSprintReport developerSprintReport = developerSprintReportService.getReport(sprintNumber);
	}
}
