/**
 * 
 */
package com.cee.ljr.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.domain.report.WeeklyStatusReport;
import com.cee.ljr.domain.report.WeeklyStatusReportFactory;
import com.cee.ljr.intg.dao.JiraIssueDao;

/**
 * @author chuck
 *
 */
@Service
@PropertySource("classpath:/properties/weekly-status-report.properties")
public class WeeklyStatusReportService {
	private static final Logger log = LoggerFactory.getLogger(WeeklyStatusReportService.class);
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Autowired
	WeeklyStatusReportFactory weeklyStatusReportFactory;
	
	@Value("${report.title}")
	private String reportTitle;
			
	public WeeklyStatusReport getWeeklyStatusReport(Date weekStartDate, Date weekendingDate) {
		log.info("initalizing {}", reportTitle);
		
		List<JiraIssue> jiraIssueList = jiraIssueDao.getAllIssues();		
		WeeklyStatusReport weeklyStatusReport = 
				weeklyStatusReportFactory.getWeeklyStatusReport(jiraIssueList, weekStartDate, weekendingDate);
		
		log.info("{} initialized.", reportTitle);
		
		return weeklyStatusReport;
	}
	
	
	
}
