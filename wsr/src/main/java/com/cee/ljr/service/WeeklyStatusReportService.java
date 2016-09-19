/**
 * 
 */
package com.cee.ljr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class WeeklyStatusReportService {
	//private static final Logger log = LoggerFactory.getLogger(WeeklyStatusReportService.class);
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Autowired
	WeeklyStatusReportFactory weeklyStatusReportFactory;
			
	public WeeklyStatusReport getWeeklyStatusReport(Date weekStartDate, Date weekendingDate) {
		
		List<JiraIssue> jiraIssueList = jiraIssueDao.getAllIssues();		
		
		return weeklyStatusReportFactory.getWeeklyStatusReport(jiraIssueList, weekStartDate, weekendingDate);
	}
	
	
	
}
