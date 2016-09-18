package com.cee.wsr.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.report.StatusReport;
import com.cee.ljr.service.StatusReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class StatusReportServiceTest {
	Logger log = LoggerFactory.getLogger(StatusReportServiceTest.class);
	
	@Autowired
	StatusReportService srService;
	
	@Test
	public void testGetStatusReport() {
		StatusReport report = srService.getWeeklyStatusReport(new Date(), new Date());
		
		Assert.assertNotNull(report);
		log.debug(report.logStats());
	}
}
