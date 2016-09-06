package com.cee.wsr.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.wsr.config.ApplicationConfig;
import com.cee.wsr.domain.StatusReport;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class StatusReportServiceTest {
	Logger log = LoggerFactory.getLogger(StatusReportServiceTest.class);
	
	@Autowired
	StatusReportService srService;
	
	@Test
	public void testGetStatusReport() {
		StatusReport report = srService.getStatusReport();
		
		Assert.assertNotNull(report);
		log.debug(report.logStats());
		//log.debug("StatusReport = \n {}", report);
	}
}
