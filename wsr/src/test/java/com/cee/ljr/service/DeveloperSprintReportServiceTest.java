package com.cee.ljr.service;

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

	@Autowired
	DeveloperSprintReportService developerSprintReportService;
	
	@Test
	public void testGetReport() {
		DeveloperSprintReport developerSprintReport = developerSprintReportService.getReport("Chuck", 10);
		
		Assert.assertNotNull(developerSprintReport);
		System.out.println(developerSprintReport);
	}
}
