package com.cee.ljr.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.report.DeveloperSprintReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DeveloperSprintReportServiceTest {
	private static final String pathToJiraCsvs = "C:/wsr_dev/JIRA1.csv;C:/wsr_dev/JIRA2.csv;C:/wsr_dev/JIRA3.csv";
	
	@Autowired
	DeveloperSprintReportService developerSprintReportService;
	
	@Test
	public void testGetReport() {
		DeveloperSprintReport developerSprintReport = developerSprintReportService.getReport(pathToJiraCsvs,"Chuck", 10);
		
		Assert.assertNotNull(developerSprintReport);
		//ystem.out.println(developerSprintReport);
	}
	
	@Test
	public void testGetAllReports() {
		List<DeveloperSprintReport> developerSprintReports = developerSprintReportService.getReports(pathToJiraCsvs, 10);
		
		Assert.assertNotNull(developerSprintReports);
		Assert.assertFalse(developerSprintReports.isEmpty());
		//ystem.out.println("DEVELOPER SPRINT REPORTS");
		//ystem.out.println("------------------------");
		for (DeveloperSprintReport developerSprintReport : developerSprintReports) {
			//ystem.out.println(developerSprintReport);
		}
		//ystem.out.println(developerSprintReports);
	}
}
