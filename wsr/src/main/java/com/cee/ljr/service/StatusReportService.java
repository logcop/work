/**
 * 
 */
package com.cee.ljr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.ljr.dao.JiraIssueDao;
import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.domain.report.StatusReport;
import com.cee.ljr.domain.report.StatusReportFactory;

/**
 * @author chuck
 *
 */
@Service
public class StatusReportService {
	//private static final Logger log = LoggerFactory.getLogger(StatusReportService.class);
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Autowired
	StatusReportFactory statusReportFactory;
			
	public StatusReport getWeeklyStatusReport(Date weekStartDate, Date weekendingDate) {
		
		List<JiraIssue> jiraIssueList = jiraIssueDao.getAllIssues();		
		
		return statusReportFactory.getWeeklyStatusReport(jiraIssueList, weekStartDate, weekendingDate);
	}
	
	
	
}
