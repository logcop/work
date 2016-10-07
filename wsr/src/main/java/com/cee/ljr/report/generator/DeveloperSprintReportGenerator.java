package com.cee.ljr.report.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.document.generator.DeveloperSprintReportXlsxGenerator;
import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.service.DeveloperSprintReportService;

@Component
public class DeveloperSprintReportGenerator {
	private static final Logger log = LoggerFactory.getLogger(DeveloperSprintReportGenerator.class);
	
	@Autowired
	DeveloperSprintReportService dsrService;
	
	@Autowired
	DeveloperSprintReportXlsxGenerator dsrGenerator;
	
	
	public void generateAll(int sprintNumber, String pathToReports, String pathToJiraCsvs) {
		//WeeklyStatusReport statusReport = srService.getWeeklyStatusReport(weekStartDate, weekEndingDate);
		//docxGenerator.generateDocument(statusReport);
		//List<String> developerNames = DevNameUtil.getFullName(firstName)
		List<DeveloperSprintReport> dsrList = dsrService.getReports(pathToJiraCsvs, sprintNumber);
		
		for (DeveloperSprintReport developerSprintReport : dsrList) {
			dsrGenerator.generate(developerSprintReport, pathToReports);
		}
		
	}
}
